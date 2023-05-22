import javafx.beans.property.SimpleStringProperty;


public class Paysfor {
    private final SimpleStringProperty ride_id;
    private final SimpleStringProperty customer_id;
private final SimpleStringProperty paysfor_id;
    private final SimpleStringProperty ride_name;
    private final SimpleStringProperty ride_datetime;
private final SimpleStringProperty ride_location;
private final SimpleStringProperty ride_duration;

 
    Paysfor(String ride_id, String customer_id, String paysfor_id, String ride_name, String ride_datetime, String ride_location, String ride_duration) {
         this.ride_id=new SimpleStringProperty(ride_id);
         this.ride_location=new SimpleStringProperty(ride_location);
         this.ride_duration=new SimpleStringProperty(ride_duration);
         this.ride_datetime=new SimpleStringProperty(ride_datetime);
         this.customer_id=new SimpleStringProperty(customer_id);
         this.paysfor_id=new SimpleStringProperty(paysfor_id);
         this.ride_name=new SimpleStringProperty(ride_name);
      
    }
    
   
    
    public String getPaysfor_id(){
      return ride_id.get();
      }
    public String getRide_location(){
      return ride_location.get();
      }
      public String getRide_duration(){
      return ride_duration.get();
      }
      public String getRide_datetime(){
      return ride_datetime.get();
      }
      public String getRide_name(){
      return ride_name.get();
      }
   public String getRide_id(){
         return ride_id.get();
         }
      public String getCustomer_id(){
         return customer_id.get();
         }

      
    
   
}