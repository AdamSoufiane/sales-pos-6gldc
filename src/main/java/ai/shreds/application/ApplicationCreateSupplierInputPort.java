package ai.shreds.application;

import ai.shreds.adapter.AdapterCreateSupplierRequest;
import ai.shreds.shared.SharedSupplierDTO;

/**
 * Interface for creating supplier records.
 * This interface will be implemented by the ApplicationSupplierService class.
 */
public interface ApplicationCreateSupplierInputPort {
    SharedSupplierDTO createSupplier(AdapterCreateSupplierRequest request);
}