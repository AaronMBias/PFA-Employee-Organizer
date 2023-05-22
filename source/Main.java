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
import java.util.Scanner;
import javafx.scene.text.Font;


public class Main extends Application{
   private static String username;
   private static String password;
    private static EmployeeDataAccessor dataAccessor ;
    private static RideDataAccessor rideDataAccessor;
    private static CustomerDataAccessor customerDataAccessor;
private static List<Employee> emp_list;
private static List<Ride> ride_list;
private static List<Customer> customer_list;
      
      @Override public void start(Stage stage){
      try{
      dataAccessor = new EmployeeDataAccessor("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost/pfa_db",username,password);
      
      emp_list = dataAccessor.getEmployeeList("select * from employee order by emp_name",true);
      rideDataAccessor = new RideDataAccessor("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost/pfa_db",username,password);
      ride_list=rideDataAccessor.getRideList("select ride.ride_id, ride.ride_location, ride.ride_duration, ride.ride_datetime, ride.ride_price, t1.pass_num, t2.datetime from ride, (select ride.ride_id, count(paysfor_id) pass_num from paysfor, ride where paysfor.ride_id=ride.ride_id group by ride.ride_id) t1, (select ride_datetime, ride_id, str_to_date(ride_datetime,'%a, %d %b %Y %H:%i:%s') datetime from ride group by ride_id) t2 where t1.ride_id=ride.ride_id and t2.ride_id=ride.ride_id group by ride.ride_id order by datetime;",true);
      
      customerDataAccessor = new CustomerDataAccessor("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost/pfa_db",username,password);
      customer_list=customerDataAccessor.getCustomerList("select * from customer order by customer_name");
      }
      catch (Exception e){
      System.out.println("--Invalid Credentials--");
      System.exit(0);
      } 
      System.out.println("Connected");
         GridPane grid=new GridPane();
      HBox empHBox= new HBox();
      HBox rideBox = new HBox();
      HBox customerBox = new HBox();
      rideBox.setPrefWidth(200);
      rideBox.setAlignment(Pos.CENTER);
      rideBox.setPadding(new Insets(10));
      empHBox.setPrefWidth(200);
      empHBox.setAlignment(Pos.CENTER);
      empHBox.setPadding(new Insets(10));
      customerBox.setPrefWidth(200);
      customerBox.setAlignment(Pos.CENTER);
      customerBox.setPadding(new Insets(10));
      final ComboBox employeeComboBox = new ComboBox();
      final ComboBox rideComboBox = new ComboBox();
      final ComboBox customerComboBox = new ComboBox();
      empHBox.getChildren().add(employeeComboBox);
      rideBox.getChildren().add(rideComboBox);
      customerBox.getChildren().add(customerComboBox);
      
      for (Employee e: emp_list){
         employeeComboBox.getItems().add(e.getEmp_id()+" "+e.getEmp_name());
         }
         for (Ride r: ride_list){
         rideComboBox.getItems().add(r.getRide_id()+" "+r.getRide_datetime());
         }
         for (Customer c: customer_list){
         customerComboBox.getItems().add(c.getCustomer_id()+" "+c.getCustomer_name());
         }
         
       Label empLabel = new Label("Employee");
       Label rideLabel = new Label("Ride");
       Label customerLabel = new Label("Customer");
       HBox labelBox1 = new HBox();
       HBox labelBox2 = new HBox();
       HBox labelBox3 = new HBox();
       labelBox1.getChildren().add(empLabel);
       labelBox2.getChildren().add(rideLabel);
       labelBox1.setAlignment(Pos.CENTER);
       labelBox2.setAlignment(Pos.CENTER);
       labelBox3.getChildren().add(customerLabel);
       labelBox3.setAlignment(Pos.CENTER);
        grid.add(empHBox,0,2);
        grid.add(rideBox,1,2);
        grid.add(labelBox1,0,1);
        grid.add(labelBox2,1,1);
        grid.add(customerBox, 2,2);
        grid.add(labelBox3,2,1);
        HBox buttonBox1 = new HBox();
        HBox buttonBox2 = new HBox();
        HBox buttonBox3 = new HBox();
        Button pickEmp = new Button("Select");
        Button pickRide = new Button("Select");
        Button pickCustomer = new Button("Select");
        
        
        pickEmp.setOnAction(e->{new EmployeeWindow(emp_list,(String) employeeComboBox.getValue(),username, password);});
        
        pickRide.setOnAction(e->{new RideWindow(ride_list,(String) rideComboBox.getValue(), username, password);});
        
        pickCustomer.setOnAction(e->{new CustomerWindow(customer_list,(String) customerComboBox.getValue(), username, password);});
        
        
        buttonBox1.setAlignment(Pos.CENTER);
        buttonBox2.setAlignment(Pos.CENTER);
        buttonBox3.setAlignment(Pos.CENTER);
        buttonBox1.getChildren().add(pickEmp);
        buttonBox2.getChildren().add(pickRide);
         buttonBox3.getChildren().add(pickCustomer);
        grid.add(buttonBox1,0,3);
        grid.add(buttonBox2,1,3);
        grid.add(buttonBox3,2,3);
        HBox titleBox=new HBox();
        Label titleLabel = new Label("PFA's Employee Organizer");
		  titleLabel.setFont(new Font("Cambria", 32));
        titleBox.getChildren().add(titleLabel);
        titleBox.setPadding(new Insets(20, 10, 30, 10));
		titleBox.setAlignment(Pos.CENTER);
      grid.add(titleBox,0,0,3,1);
        
		grid.setPadding(new Insets(5, 5, 5, 5));
          Scene scene = new Scene(grid, 400, 300); 
		
		stage.setScene(scene); 
		stage.show(); 
      }
public static void main(String args[]) {
      Scanner console = new Scanner(System.in);
      System.out.print("mysql Username: " );
      username=console.nextLine();
      System.out.print("mysql Password: " );
      password=console.nextLine();
		Application.launch(args);
      }
      
   public String getUsername(){
   return username;
      }
   public String getPassword(){
      return password;
      }
	}
   
   


	