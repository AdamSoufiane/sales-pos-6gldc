package ai.shreds.application;

import ai.shreds.adapter.AdapterKafkaProducer;
import ai.shreds.adapter.AdapterPurchaseDataDTO;
import ai.shreds.adapter.AdapterPurchaseRequestDTO;
import ai.shreds.adapter.AdapterPurchaseResponseDTO;
import ai.shreds.adapter.AdapterProductDTO;
import ai.shreds.domain.DomainSupplierValidationPort;
import ai.shreds.domain.DomainProductValidationPort;
import ai.shreds.domain.DomainPurchaseTransactionPort;
import ai.shreds.domain.DomainPurchaseTransactionEntity;
import ai.shreds.domain.DomainPurchaseTransactionException;
import ai.shreds.infrastructure.InfrastructureEntityMapper;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.KafkaRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApplicationPurchaseService implements ApplicationPurchaseServiceInputPort {

    private static final Logger log = LoggerFactory.getLogger(ApplicationPurchaseService.class);
    private final DomainSupplierValidationPort supplierValidationPort;
    private final DomainProductValidationPort productValidationPort;
    private final DomainPurchaseTransactionPort purchaseTransactionPort;
    private final AdapterKafkaProducer kafkaProducer;
    private final InfrastructureEntityMapper entityMapper;

    @Override
    public AdapterPurchaseResponseDTO processPurchaseTransaction(AdapterPurchaseRequestDTO request) {
        try {
            // Validate supplier
            boolean isSupplierValid = supplierValidationPort.validateSupplier(request.getSupplierId());
            if (!isSupplierValid) {
                log.error("Invalid Supplier ID: {}", request.getSupplierId());
                return new AdapterPurchaseResponseDTO(400, null, "Invalid Supplier ID");
            }

            // Validate products
            List<String> productIds = request.getProducts().stream().map(AdapterProductDTO::getProductId).collect(Collectors.toList());
            boolean areProductsValid = productValidationPort.validateProducts(productIds);
            if (!areProductsValid) {
                log.error("Invalid Product IDs: {}", productIds);
                return new AdapterPurchaseResponseDTO(400, null, "Invalid Product IDs");
            }

            // Map to domain entity
            DomainPurchaseTransactionEntity domainTransaction = entityMapper.mapAdapterToDomain(request);

            // Save transaction
            purchaseTransactionPort.savePurchaseTransaction(domainTransaction);

            // Send Kafka message asynchronously
            sendKafkaMessageAsync("ProductAdded", domainTransaction);

            // Prepare response
            AdapterPurchaseDataDTO responseData = entityMapper.mapDomainToAdapter(domainTransaction);
            return new AdapterPurchaseResponseDTO(200, responseData, null);
        } catch (DomainPurchaseTransactionException e) {
            log.error("Error occurred during transaction processing: ", e);
            return new AdapterPurchaseResponseDTO(500, null, "Error occurred during transaction processing");
        } catch (Exception e) {
            log.error("Internal Server Error: ", e);
            return new AdapterPurchaseResponseDTO(500, null, "Internal Server Error");
        }
    }

    private Future<SendResult<String, String>> sendKafkaMessageAsync(String topic, DomainPurchaseTransactionEntity transaction) {
        String message = transaction.toString();
        Message<String> msg = MessageBuilder.withPayload(message).build();
        Future<SendResult<String, String>> future = kafkaProducer.sendMessage(topic, message);
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                handleKafkaException(ex);
            } else {
                log.info("Message sent to topic " + result.getRecordMetadata().topic() + "\n" + "with offset " + result.getRecordMetadata().offset());
            }
        });
        return future;
    }

    private void handleKafkaException(Throwable ex) {
        if (ex instanceof KafkaException) {
            KafkaException ke = (KafkaException) ex;
            log.error("Kafka exception occurred: ", ke);
        } else if (ex instanceof KafkaRuntimeException) {
            KafkaRuntimeException kre = (KafkaRuntimeException) ex;
            log.error("Kafka runtime exception occurred: ", kre);
        } else if (ex instanceof ExecutionException) {
            ExecutionException ee = (ExecutionException) ex;
            Throwable cause = ee.getCause();
            if (cause instanceof RuntimeException) {
                RuntimeException re = (RuntimeException) cause;
                log.error("Runtime exception occurred during Kafka message sending: ", re);
            } else {
                log.error("Unknown exception occurred during Kafka message sending: ", ex);
            }
        } else {
            log.error("Unknown exception occurred during Kafka message sending: ", ex);
        }
    }
}