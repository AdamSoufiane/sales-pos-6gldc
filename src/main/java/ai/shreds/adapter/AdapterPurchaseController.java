package ai.shreds.adapter;

import ai.shreds.adapter.AdapterPurchaseRequestDTO;
import ai.shreds.adapter.AdapterPurchaseResponseDTO;
import ai.shreds.application.ApplicationPurchaseServiceInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class AdapterPurchaseController {

    private final ApplicationPurchaseServiceInputPort purchaseService;

    @PostMapping
    public ResponseEntity<AdapterPurchaseResponseDTO> processPurchaseTransaction(@Valid @RequestBody AdapterPurchaseRequestDTO request) {
        log.info("Processing purchase transaction for purchaseNumber: {}", request.getPurchaseNumber());
        try {
            AdapterPurchaseResponseDTO response = purchaseService.processPurchaseTransaction(request);
            log.info("Successfully processed purchase transaction for purchaseNumber: {}", request.getPurchaseNumber());
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            log.error("Bad request for purchaseNumber: {}: {}", request.getPurchaseNumber(), e.getMessage());
            AdapterPurchaseResponseDTO errorResponse = AdapterPurchaseResponseDTO.builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .data(null)
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            log.error("Internal server error for purchaseNumber: {}: {}", request.getPurchaseNumber(), e.getMessage());
            AdapterPurchaseResponseDTO errorResponse = AdapterPurchaseResponseDTO.builder()
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .data(null)
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}