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
    public boolean register(String name,String userName,String password,int age,String address)
    {
        String query = "insert into customers (name,userName,password,age,address) values ('%s','%s','%s',%d,'%s')".formatted(name,userName,password,age,address);
        try {
            Statement sttmnt = connection.createStatement();
            sttmnt.executeUpdate(query);
            return true;
        }
        catch (Exception ignored)
        {
            System.out.println(ignored.getMessage());
        }
        return false;
    }
    public void deleteTicket(int ticketId, String username){
        String query = "delete from tickets where tripId = %d and customerUserName = '%s'".formatted(ticketId, username);
        try {
            Statement sttmnt = connection.createStatement();
            sttmnt.executeUpdate(query);
        }
        catch (Exception ignored)
        {
            System.out.println(ignored.getMessage());
        }
    }
    public Connection getConnection() {
        return connection;
    }
}
