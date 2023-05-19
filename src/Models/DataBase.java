package Models;

import javax.management.Query;
import java.sql.*;

public class DataBase {
    private Connection connection;
    public DataBase() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;" +
                    "databaseName=TrainSystem;integratedSecurity=true;encrypt=false;");
        } catch (Exception ignored) {}
    }
    public boolean checkLoginCredentials(String UserName, String Pass, boolean isAdmin) {
        String query;
        ResultSet ans;
        if(isAdmin)
            query = "select * from admins where userName = '%s' and [password] = '%s'".formatted(UserName, Pass);
        else
            query = "select * from customers where userName = '%s' and [password] = '%s'".formatted(UserName, Pass);
        try {
            Statement st = connection.createStatement();
            ans = st.executeQuery(query);
            return ans.next();
        }
        catch (Exception ignored){
        }
        return false;
    }
    public ResultSet getUserTrips(String username){
        ResultSet ans;
        String query = "select trips.* from trips, tickets where id = tripId and customerUserName = '%s'".formatted(username);
        try {
            Statement sttmnt = connection.createStatement();
            ans = sttmnt.executeQuery(query);
            return ans;
        }
        catch (Exception ignored){}
        return null;
    }
    public Connection getConnection() {
        return connection;
    }
}
