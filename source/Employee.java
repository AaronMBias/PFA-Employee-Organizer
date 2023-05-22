import javafx.beans.property.SimpleStringProperty;


public class Employee {
    private final SimpleStringProperty emp_id;
    private final SimpleStringProperty emp_birthdate;
private final SimpleStringProperty emp_phone;
private final SimpleStringProperty emp_name;
private final SimpleStringProperty emp_hiredate;
private final SimpleStringProperty emp_salary;
private final SimpleStringProperty emp_certdate;


 
    Employee(String emp_id, String emp_birthdate, String emp_phone, String emp_name, String emp_hiredate, String emp_salary, String emp_certdate) {
         this.emp_id=new SimpleStringProperty(emp_id);
         this.emp_birthdate=new SimpleStringProperty(emp_birthdate);
         this.emp_phone=new SimpleStringProperty(emp_phone);
         this.emp_name=new SimpleStringProperty(emp_name);
         this.emp_hiredate=new SimpleStringProperty(emp_hiredate);
         this.emp_salary=new SimpleStringProperty(emp_salary);
         this.emp_certdate=new SimpleStringProperty(emp_certdate);
      
    }
    
   
    
    public String getEmp_id(){
      return emp_id.get();
      }
    public String getEmp_birthdate(){
      return emp_birthdate.get();
      }
      public String getEmp_phone(){
      return emp_phone.get();
      }
      public String getEmp_name(){
      return emp_name.get();
      }
      public String getEmp_hiredate(){
      return emp_hiredate.get();
      }
public String getEmp_salary(){
      return emp_salary.get();
      }
public String getEmp_certdate(){
      return emp_certdate.get();
      }

      
    
   
}