package ai.shreds.adapter; 
  
 import ai.shreds.application.ApplicationGetAllSuppliersInputPort; 
 import ai.shreds.application.ApplicationGetSupplierByIdInputPort; 
 import ai.shreds.shared.SharedRequestParams; 
 import ai.shreds.shared.SharedSupplierDTO; 
 import lombok.RequiredArgsConstructor; 
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.bind.annotation.GetMapping; 
 import org.springframework.web.bind.annotation.PathVariable; 
 import org.springframework.web.bind.annotation.RequestMapping; 
 import org.springframework.web.bind.annotation.RequestParam; 
 import org.springframework.web.bind.annotation.RestController; 
  
 import java.util.List; 
 import java.util.stream.Collectors; 
  
 @RestController 
 @RequestMapping("/suppliers") 
 @RequiredArgsConstructor 
 public class AdapterSupplierController { 
  
     private final ApplicationGetAllSuppliersInputPort getAllSuppliersInputPort; 
     private final ApplicationGetSupplierByIdInputPort getSupplierByIdInputPort; 
     private final AdapterSupplierMapper supplierMapper; 
  
     /** 
      * Retrieves a list of suppliers based on optional search criteria. 
      * @param name Optional filter for supplier name. 
      * @param contact_info Optional filter for supplier contact information. 
      * @param address Optional filter for supplier address. 
      * @return A list of suppliers that match the search criteria. 
      */ 
     @GetMapping 
     public ResponseEntity<List<AdapterSupplierResponseDTO>> getAllSuppliers( 
             @RequestParam(required = false) String name, 
             @RequestParam(required = false) String contact_info, 
             @RequestParam(required = false) String address) { 
         SharedRequestParams params = new SharedRequestParams(name, contact_info, address); 
         List<SharedSupplierDTO> suppliers = getAllSuppliersInputPort.getAllSuppliers(params); 
         List<AdapterSupplierResponseDTO> response = suppliers.stream() 
                 .map(supplierMapper::toDTO) 
                 .collect(Collectors.toList()); 
         return ResponseEntity.ok(response); 
     } 
  
     /** 
      * Retrieves the details of a specific supplier by its ID. 
      * @param id The unique identifier of the supplier. 
      * @return The supplier details. 
      */ 
     @GetMapping("/{id}") 
     public ResponseEntity<AdapterSupplierResponseDTO> getSupplierById(@PathVariable Long id) { 
         try { 
             SharedSupplierDTO supplier = getSupplierByIdInputPort.getSupplierById(id); 
             AdapterSupplierResponseDTO response = supplierMapper.toDTO(supplier); 
             return ResponseEntity.ok(response); 
         } catch (SupplierNotFoundException e) { 
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
         } catch (Exception e) { 
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); 
         } 
     } 
  
     /** 
      * Handles exceptions and returns appropriate HTTP responses. 
      * @param e The exception that was thrown. 
      * @return A ResponseEntity with an appropriate status code and error message. 
      */ 
     @ExceptionHandler(Exception.class) 
     public ResponseEntity<String> handleException(Exception e) { 
         if (e instanceof SupplierNotFoundException) { 
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier not found."); 
         } else { 
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error."); 
         } 
     } 
 } 
 