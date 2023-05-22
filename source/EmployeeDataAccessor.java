import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.SQLException ;
import java.sql.Statement ;
import java.sql.ResultSet ;

import java.util.List ;
import java.util.ArrayList ;

public class EmployeeDataAccessor {

    private Connection connection ;

    public EmployeeDataAccessor(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(dbURL, user, password);
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
    
    public List<Employee> getEmployeeList(String sentQuery, boolean needExtraInfo) throws SQLException {
        try (
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery(sentQuery);
        ){
            List<Employee> empList = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("emp_name");
                String id = rs.getString("emp_id");
                String birthDate, hireDate, phone, salary, certDate;  
                //if (needExtraInfo){
                 birthDate=rs.getString("emp_birthdate");
                 hireDate=rs.getString("emp_hiredate");
                 phone=rs.getString("emp_phone");
                 salary=rs.getString("emp_salary");
                 certDate=rs.getString("emp_certDate");
               // }
               // else{
                //birthDate=hireDate=phone=salary=certDate=null;
                //}
                Employee emp = new Employee(id, birthDate, phone, name, hireDate, salary, certDate);
                empList.add(emp);
            }
            return empList ;
        } 
    }


}