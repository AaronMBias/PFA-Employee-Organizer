import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.SQLException ;
import java.sql.Statement ;
import java.sql.ResultSet ;

import java.util.List ;
import java.util.ArrayList ;

public class CustomerDataAccessor {

    private Connection connection ;

    public CustomerDataAccessor(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(dbURL, user, password);
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
    
    public List<Customer> getCustomerList(String sentQuery) throws SQLException {
        try (
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery(sentQuery);
        ){
            List<Customer> customerList = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("customer_name");
                String id = rs.getString("customer_id");
                String birthDate=rs.getString("customer_birthdate");
                String phone=rs.getString("customer_phone");
                Customer customer = new Customer(id, birthDate, phone,name);
                customerList.add(customer);
            }
            return customerList ;
        } 
    }


}