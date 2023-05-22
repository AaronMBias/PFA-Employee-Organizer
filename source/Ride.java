import javafx.beans.property.SimpleStringProperty;


public class Ride {
    private final SimpleStringProperty ride_id;
    private final SimpleStringProperty ride_location;
private final SimpleStringProperty ride_duration;
private final SimpleStringProperty ride_datetime;
private final SimpleStringProperty ride_price;
private final SimpleStringProperty passengers;


 
    Ride(String ride_id, String ride_location, String ride_duration, String ride_datetime, String ride_price, String passengers) {
         this.ride_id=new SimpleStringProperty(ride_id);
         this.ride_location=new SimpleStringProperty(ride_location);
         this.ride_duration=new SimpleStringProperty(ride_duration);
         this.ride_datetime=new SimpleStringProperty(ride_datetime);
         this.ride_price=new SimpleStringProperty(ride_price);
         this.passengers=new SimpleStringProperty(passengers);
      
    }
    
   
    
    public String getRide_id(){
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
      public String getRide_price(){
      return ride_price.get();
      }
   public String getPassengers(){
         return passengers.get();
         }

      
    
   
}