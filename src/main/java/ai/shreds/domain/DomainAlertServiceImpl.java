package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedAlertDTO; 
 import lombok.RequiredArgsConstructor; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.stereotype.Service; 
  
 /** 
  * Implementation of the DomainAlertServicePort interface. 
  * This class is responsible for triggering alerts when inventory levels fall below a specified threshold. 
  */ 
 @Service 
 @RequiredArgsConstructor 
 public class DomainAlertServiceImpl implements DomainAlertServicePort { 
  
     private static final Logger logger = LoggerFactory.getLogger(DomainAlertServiceImpl.class); 
  
     private final DomainAlertServicePort alertService; 
  
     /** 
      * Triggers an alert when inventory levels fall below a specified threshold. 
      * 
      * @param alert the alert details including product ID, alert message, threshold, and current quantity. 
      */ 
     @Override 
     public void triggerAlert(SharedAlertDTO alert) { 
         try { 
             logger.info("Triggering alert for product ID: {}", alert.getProductId()); 
             alertService.sendAlert(alert); 
         } catch (Exception e) { 
             logger.error("Error while triggering alert for product ID: {}", alert.getProductId(), e); 
             throw new AlertTriggerException("Failed to trigger alert for product ID: " + alert.getProductId(), e); 
         } 
     } 
 } 
  
 class AlertTriggerException extends RuntimeException { 
     public AlertTriggerException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 } 
 