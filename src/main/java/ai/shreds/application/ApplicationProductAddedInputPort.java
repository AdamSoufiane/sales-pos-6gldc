package ai.shreds.application;

import ai.shreds.adapter.AdapterProductAddedRequestParams;
import ai.shreds.adapter.AdapterProductAddedResponseDTO;

/**
 * Interface for processing 'ProductAdded' events.
 */
public interface ApplicationProductAddedInputPort {
    /**
     * Processes a 'ProductAdded' event.
     *
     * @param params the parameters of the product added event
     * @return the response after processing the event
     * @throws ApplicationProductAddedException if any error occurs during processing
     */
    AdapterProductAddedResponseDTO processProductAddedEvent(AdapterProductAddedRequestParams params) throws ApplicationProductAddedException;
}