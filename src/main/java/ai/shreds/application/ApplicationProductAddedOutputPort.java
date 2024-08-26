package ai.shreds.application;

import ai.shreds.adapter.AdapterProductAddedRequestParams;

/**
 * This interface defines the contract for updating the inventory in the InventoryDB
 * when a 'ProductAdded' event is processed.
 */
public interface ApplicationProductAddedOutputPort {
    /**
     * Updates the inventory in the InventoryDB with the new product information.
     *
     * @param params The parameters containing the product added event data.
     */
    void updateInventory(AdapterProductAddedRequestParams params);
}