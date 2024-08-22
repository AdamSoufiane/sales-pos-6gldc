package ai.shreds.adapter;

import ai.shreds.application.ApplicationProductServicePort;
import ai.shreds.application.ApplicationCategoryServicePort;
import ai.shreds.adapter.AdapterProductCreateRequest;
import ai.shreds.adapter.AdapterProductCreateResponse;
import ai.shreds.adapter.AdapterProductUpdateRequest;
import ai.shreds.adapter.AdapterProductUpdateResponse;
import ai.shreds.adapter.AdapterProductDeleteResponse;
import ai.shreds.adapter.AdapterCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@Validated
public class AdapterProductController {

    private static final Logger logger = LoggerFactory.getLogger(AdapterProductController.class);
    private final ApplicationProductServicePort productService;
    private final ApplicationCategoryServicePort categoryService;
    private final AdapterKafkaProducer kafkaProducer;

    @Autowired
    public AdapterProductController(ApplicationProductServicePort productService, ApplicationCategoryServicePort categoryService, AdapterKafkaProducer kafkaProducer) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping
    public ResponseEntity<AdapterProductCreateResponse> createProduct(@RequestBody @Validated AdapterProductCreateRequest request) {
        validateProductRequest(request);
        AdapterCategoryDTO category = categoryService.getCategoryById(request.getCategoryId());
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Creating product with name: {}", request.getName());
        AdapterProductCreateResponse response = productService.createProduct(request);
        kafkaProducer.produceEvent("ProductAdded", response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdapterProductUpdateResponse> updateProduct(@PathVariable UUID id, @RequestBody @Validated AdapterProductUpdateRequest request) {
        validateProductRequest(request);
        AdapterCategoryDTO category = categoryService.getCategoryById(request.getCategoryId());
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Updating product with ID: {}", id);
        AdapterProductUpdateResponse response = productService.updateProduct(id, request);
        kafkaProducer.produceEvent("ProductUpdated", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AdapterProductDeleteResponse> deleteProduct(@PathVariable UUID id) {
        logger.info("Deleting product with ID: {}", id);
        AdapterProductDeleteResponse response = productService.deleteProduct(id);
        kafkaProducer.produceEvent("ProductDeleted", response);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        logger.error("Data integrity violation: {}", e.getMessage());
        return ResponseEntity.status(400).body("Invalid product data");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        logger.error("An error occurred: {}", e.getMessage());
        return ResponseEntity.status(500).body(e.getMessage());
    }

    private void validateProductRequest(Object request) {
        if (request instanceof AdapterProductCreateRequest) {
            AdapterProductCreateRequest createRequest = (AdapterProductCreateRequest) request;
            if (createRequest.getName() == null || createRequest.getName().isEmpty()) {
                throw new IllegalArgumentException("Product name must not be empty");
            }
            if (createRequest.getPrice() == null || createRequest.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Product price must be a positive value");
            }
            if (createRequest.getCategoryId() == null) {
                throw new IllegalArgumentException("Category ID must not be null");
            }
        } else if (request instanceof AdapterProductUpdateRequest) {
            AdapterProductUpdateRequest updateRequest = (AdapterProductUpdateRequest) request;
            if (updateRequest.getName() == null || updateRequest.getName().isEmpty()) {
                throw new IllegalArgumentException("Product name must not be empty");
            }
            if (updateRequest.getPrice() == null || updateRequest.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Product price must be a positive value");
            }
            if (updateRequest.getCategoryId() == null) {
                throw new IllegalArgumentException("Category ID must not be null");
            }
        }
    }
}