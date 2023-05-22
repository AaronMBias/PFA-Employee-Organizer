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


public class RideWindow extends Stage{
      RideWindow(List<Ride> ride_list, String selection, String username, String password){
      selection = selection.split(" ",2)[0]; 
      Ride selectedRide=null;
       for (Ride e: ride_list){
       if (e.getRide_id().equals(selection)){
         selectedRide=e;
         break;
         }
         }
      
       this.setTitle("Ride Information and Operator List");
      TableView<Employee> table = new TableView<>();
      TableColumn<Employee, String> empNameCol = new TableColumn<>("Employee Name");
        empNameCol.setCellValueFactory(new PropertyValueFactory<>("emp_name"));
       TableColumn<Employee, String> empIdCol = new TableColumn<>("Employee ID");
        empIdCol.setCellValueFactory(new PropertyValueFactory<>("emp_id"));

                
        table.getColumns().addAll(empNameCol, empIdCol);
        try{
        EmployeeDataAccessor dataAccessor = new EmployeeDataAccessor("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost/pfa_db",username,password); 
        table.getItems().addAll(dataAccessor.getEmployeeList("select emp_name, ride_id, employee.emp_id, emp_birthdate, emp_hiredate, emp_salary, emp_certdate, emp_phone from employee, operates where operates.emp_ID=employee.emp_id and ride_id="+selection,false));
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
         HBox labelBoxes[] = new HBox[6];

         try{
         //GeneralQueryAccessor genQuery = new GeneralQueryAccessor("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost/Construct",username,password);
         String location=selectedRide.getRide_location();
         String id=selectedRide.getRide_id();
         String duration=selectedRide.getRide_duration();
         String datetime=selectedRide.getRide_datetime();
         String price=selectedRide.getRide_price();
         String passengers=selectedRide.getPassengers();
         

         String stringList[] = {"Location: "+ location, "ID: "+id, "Duration: "+duration+" min", "Datetime: "+datetime, "Price: "+"$"+price, "# Passengers: "+passengers};
         

         

         Label labelList[] = new Label[6];
         for (int i=0; i<6; i++){
         
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


