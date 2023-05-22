import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.SQLException ;
import java.sql.Statement ;
import java.sql.ResultSet ;

import java.util.List ;
import java.util.ArrayList ;

public class RideDataAccessor {

    private Connection connection ;

    public RideDataAccessor(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(dbURL, user, password);
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
    
    public List<Ride> getRideList(String sentQuery, boolean needPassengers) throws SQLException {
        try (
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery(sentQuery);
        ){
            List<Ride> rideList = new ArrayList<>();
            while (rs.next()) {
                String location = rs.getString("ride_location");
                String id = rs.getString("ride_id");
                String duration=rs.getString("ride_duration");
                String datetime=rs.getString("ride_datetime");
                String price=rs.getString("ride_price");
                String passengers;
                if (needPassengers)
                passengers=""+rs.getInt("pass_num"); 
                else
                passengers=null;
                Ride ride = new Ride(id,location, duration, datetime, price, passengers);
                rideList.add(ride);
            }
            return rideList ;
        } 
    }


}