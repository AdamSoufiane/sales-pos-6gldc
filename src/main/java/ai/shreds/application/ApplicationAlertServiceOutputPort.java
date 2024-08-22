package ai.shreds.application; 
  
 import ai.shreds.shared.SharedAlertDTO; 
  
 public interface ApplicationAlertServiceOutputPort { 
     void sendAlert(SharedAlertDTO alert); 
 } 
  
 // Note: Use Lombok annotations for getters and setters in the implementation class.