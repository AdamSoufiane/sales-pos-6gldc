package ai.shreds.adapter;

import ai.shreds.shared.SharedEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for encapsulating the response message for the 'ProductAdded' event.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdapterProductAddedResponseDTO extends SharedEntityDTO {
    private String message;
}