package Models;

import javax.management.Query;
import java.sql.*;

public class DataBase {
    private Connection connection;
    public DataBase() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src\\Models\\trainSystem.db");
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
            closeConnection();
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
            closeConnection();
            return true;
        }
        catch (Exception ignored)
        {
            System.out.println(ignored.getMessage());
        }
        return false;
    }
    public boolean addTrain(int seats,int classNumber)
    {
        String query = "insert into trains (seats,class) values (%d,%d)".formatted(seats,classNumber);
        try {
            Statement sttmnt = connection.createStatement();
            sttmnt.executeUpdate(query);
            closeConnection();
            return true;
        }
        catch (Exception ignored)
        {
            System.out.println(ignored.getMessage());
        }
        return false;
    }

    public boolean updateTrain(int id,int seats,int classNumber)
    {
        String query = "update trains set seats = %d , class = %d where id = %d".formatted(seats,classNumber,id);
        try {
            Statement sttmnt = connection.createStatement();
            sttmnt.executeUpdate(query);
            closeConnection();
            return true;
        }
        catch (Exception ignored)
        {
            System.out.println(ignored.getMessage());
        }
        return false;
    }
    public Connection getConnection() {
        return connection;
    }
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // Handle any errors that occur while closing the connection
            e.printStackTrace();
        }
    }
}
