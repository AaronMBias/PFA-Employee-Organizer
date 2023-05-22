import javafx.application.Application ;
import javafx.scene.control.TableView ;
import javafx.scene.control.TableColumn ;
import javafx.scene.control.cell.PropertyValueFactory ;
import javafx.scene.layout.BorderPane ;
import javafx.scene.control.TextField ;
import javafx.scene.control.Button ;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene ;
import javafx.stage.Stage ;
import java.util.List;
import javafx.scene.layout.*; 
import javafx.scene.control.*; 
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;


public class CustomerWindow extends Stage{
      CustomerWindow(List<Customer> customer_list, String selection, String username, String password){
      selection = selection.replaceAll("[^0-9]", ""); 
      Customer selectedCustomer=null;
       for (Customer e: customer_list){
       if (e.getCustomer_id().equals(selection)){
         selectedCustomer=e;
         break;
         }
         }
      
       this.setTitle("Customer Information and Purchased Tickets");
      TableView<Ride> table = new TableView<>();
      TableColumn<Ride, String> dateCol = new TableColumn<>("Datetime");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("ride_datetime"));
       TableColumn<Ride, String> locationCol = new TableColumn<>("Location");
        locationCol.setCellValueFactory(new PropertyValueFactory<>("ride_location"));
        TableColumn<Ride, String> durationCol = new TableColumn<>("Duration (m)");
        durationCol.setCellValueFactory(new PropertyValueFactory<>("ride_duration"));
        TableColumn<Ride, String> idCol = new TableColumn<>("Ride ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("ride_id"));
        TableColumn<Ride, String> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("ride_price"));
                
        table.getColumns().addAll(dateCol, locationCol, durationCol, priceCol, idCol);
        try{
        RideDataAccessor dataAccessor = new RideDataAccessor("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost/pfa_db",username,password); 
        table.getItems().addAll(dataAccessor.getRideList("select ride.ride_datetime, ride.ride_location, ride.ride_id, ride.ride_price, ride.ride_duration from ride, (select ride_id from paysfor, customer where customer.customer_id=paysfor.customer_id and customer.customer_id="+selection+") schedule where ride.ride_id=schedule.ride_id order by ride.ride_datetime",false));
        }
        catch (Exception e){
        System.out.println(e);
         }
         
         VBox vbox = new VBox();
         vbox.getChildren().add(table);
         vbox.setVgrow(table, Priority.ALWAYS);
         
         vbox.setPrefWidth(600);
         vbox.setPadding(new Insets(0, 30, 10, 60));
		   vbox.setAlignment(Pos.CENTER);
      
         GridPane grid = new GridPane();
         HBox labelBoxes[] = new HBox[4];

         try{
         //GeneralQueryAccessor genQuery = new GeneralQueryAccessor("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost/Construct",username,password);
         String name=selectedCustomer.getCustomer_name();
         String id=selectedCustomer.getCustomer_id();
         String phone=selectedCustomer.getCustomer_phone();
         String birthdate=selectedCustomer.getCustomer_birthdate();

         

         String stringList[] = {"Name: "+ name, "ID: "+id, "Phone: "+phone, "Birthdate: "+birthdate};
         

         

         Label labelList[] = new Label[4];
         for (int i=0; i<4; i++){
         
            labelList[i]=new Label(stringList[i]);
            
            labelBoxes[i]= new HBox();
            labelBoxes[i].getChildren().add(labelList[i]); 
             labelBoxes[i].setPadding(new Insets(0, 0, 0, 60));
            grid.add(labelBoxes[i],0,i);
            }
            }
            catch (Exception e){System.out.println(e);}
            grid.setVgap(5);
            grid.add(vbox,0,7,10,1);
         this.setScene(new Scene(grid,750,300));
         this.show();
         }
    	}


