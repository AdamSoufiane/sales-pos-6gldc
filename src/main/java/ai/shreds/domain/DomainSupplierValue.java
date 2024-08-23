package ai.shreds.domain; 
  
 import lombok.Getter; 
 import lombok.NonNull; 
 import lombok.RequiredArgsConstructor; 
 import lombok.ToString; 
  
 /** 
  * Represents the value object for Supplier containing essential attributes such as name, contact information, and address. 
  */ 
 @Getter 
 @ToString 
 @RequiredArgsConstructor 
 public class DomainSupplierValue { 
     @NonNull 
     private final String name; 
     @NonNull 
     private final String contact_info; 
     @NonNull 
     private final String address; 
  
     /** 
      * Constructs a new DomainSupplierValue with the specified name, contact information, and address. 
      * 
      * @param name         the name of the supplier 
      * @param contact_info the contact information of the supplier, must include both phone number and email 
      * @param address      the physical address of the supplier 
      * @throws IllegalArgumentException if any of the required fields are missing or incorrectly formatted 
      */ 
     public DomainSupplierValue(String name, String contact_info, String address) { 
         if (name == null || name.isEmpty()) { 
             throw new IllegalArgumentException("Supplier name is required."); 
         } 
         if (contact_info == null || contact_info.isEmpty()) { 
             throw new IllegalArgumentException("Supplier contact information is required."); 
         } 
         String[] contactParts = contact_info.split(","); 
         if (contactParts.length != 2) { 
             throw new IllegalArgumentException("Supplier contact information must include both a phone number and an email address."); 
         } 
         if (address == null || address.isEmpty()) { 
             throw new IllegalArgumentException("Supplier address is required."); 
         } 
         this.name = name; 
         this.contact_info = contact_info; 
         this.address = address; 
     } 
 }