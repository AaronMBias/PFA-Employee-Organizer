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


public class EmployeeWindow extends Stage{
      EmployeeWindow(List<Employee> emp_list, String selection, String username, String password){
      selection = selection.replaceAll("[^0-9]", ""); 
      Employee selectedEmployee=null;
       for (Employee e: emp_list){
       if (e.getEmp_id().equals(selection)){
         selectedEmployee=e;
         break;
         }
         }
      
       this.setTitle("Employee Information and Upcoming Schedule");
      TableView<Ride> table = new TableView<>();
      TableColumn<Ride, String> dateCol = new TableColumn<>("Datetime");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("ride_datetime"));
       TableColumn<Ride, String> locationCol = new TableColumn<>("Location");
        locationCol.setCellValueFactory(new PropertyValueFactory<>("ride_location"));
        TableColumn<Ride, String> durationCol = new TableColumn<>("Duration (m)");
        durationCol.setCellValueFactory(new PropertyValueFactory<>("ride_duration"));
        TableColumn<Ride, String> idCol = new TableColumn<>("Ride ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("ride_id"));
                
        table.getColumns().addAll(dateCol, locationCol, durationCol, idCol);
        try{
        RideDataAccessor dataAccessor = new RideDataAccessor("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost/pfa_db",username,password); 
        table.getItems().addAll(dataAccessor.getRideList("select ride.ride_datetime, ride.ride_location, ride.ride_id, ride.ride_duration, ride.ride_price from ride, (select ride_id from operates, employee where employee.emp_id=operates.emp_id and employee.emp_id="+selection+") schedule where ride.ride_id=schedule.ride_id order by ride.ride_datetime",false));
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
         HBox labelBoxes[] = new HBox[7];

         try{
         //GeneralQueryAccessor genQuery = new GeneralQueryAccessor("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost/Construct",username,password);
         String name=selectedEmployee.getEmp_name();
         String id=selectedEmployee.getEmp_id();
         String phone=selectedEmployee.getEmp_phone();
         String birthdate=selectedEmployee.getEmp_birthdate();
         String hiredate=selectedEmployee.getEmp_hiredate();
         String salary=selectedEmployee.getEmp_salary();
         String certdate=selectedEmployee.getEmp_certdate();
         

         String stringList[] = {"Name: "+ name, "ID: "+id, "Phone: "+phone, "Birthdate: "+birthdate, "Hire Date: "+hiredate, "Salary: "+salary, "Certification Date: "+certdate};
         

         

         Label labelList[] = new Label[7];
         for (int i=0; i<7; i++){
         
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


