package ai.shreds.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the response for the 'ProductAdded' event in the domain layer.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainProductAddedEventResponse {
    private String message;
}