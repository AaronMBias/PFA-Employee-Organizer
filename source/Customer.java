import javafx.beans.property.SimpleStringProperty;


public class Customer {
    private final SimpleStringProperty customer_id;
    private final SimpleStringProperty customer_birthdate;
private final SimpleStringProperty customer_phone;
private final SimpleStringProperty customer_name;



 
    Customer(String customer_id, String customer_birthdate, String customer_phone, String customer_name) {
         this.customer_id=new SimpleStringProperty(customer_id);
         this.customer_birthdate=new SimpleStringProperty(customer_birthdate);
         this.customer_phone=new SimpleStringProperty(customer_phone);
         this.customer_name=new SimpleStringProperty(customer_name);
      
    }
    
   
    
    public String getCustomer_id(){
      return customer_id.get();
      }
    public String getCustomer_birthdate(){
      return customer_birthdate.get();
      }
      public String getCustomer_phone(){
      return customer_phone.get();
      }
      public String getCustomer_name(){
      return customer_name.get();
      }

    
   
}