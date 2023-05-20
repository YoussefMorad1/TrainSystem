package Models;

import javax.management.Query;
import java.sql.*;
import java.util.Date;

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
    public ResultSet searchTrips(String from, String to, int[] classes, Date date) {
        ResultSet ans;
        String query = "select trips.*, trains.id from trips, trains where trips.trainId = trains.id";
        if (from != null && !from.isEmpty())
            query += " and startLocation like '%%%s%%' ".formatted(from);
        if (to != null && !to.isEmpty())
            query += " and destination like '%%%s%%' ".formatted(to);
        if(classes != null) {
            if (classes.length == 1)
                query += " and trains.class in (%d) ".formatted(classes[0]);
            else if (classes.length == 2)
                query += " and trains.class in (%d, %d) ".formatted(classes[0], classes[1]);
            else if (classes.length == 3)
                query += " and trains.class in (%d, %d, %d) ".formatted(classes[0], classes[1], classes[2]);
        }
        if (date != null)
            query += " and startTime > '%s' ".formatted(date);
        try {
            Statement sttmnt = connection.createStatement();
            ans = sttmnt.executeQuery(query);
            return ans;
        } catch (Exception ignored) {
            System.out.println(ignored);
        }
        return null;
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
}
