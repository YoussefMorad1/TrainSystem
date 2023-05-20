package Models;
import java.security.spec.ECField;
import javax.management.Query;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DataBase {
    private Connection connection;
    public DataBase() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=TrainTicketReservationSystem;integratedSecurity=true;");
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
            //closeConnection();
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
           // closeConnection();
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
           // closeConnection();
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
            //closeConnection();
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
            //closeConnection();
            return true;
        }
        catch (Exception ignored)
        {
            System.out.println(ignored.getMessage());
        }
        return false;
    }
    public boolean addTrip(String startLocation,String destination,int availableSeats,String startTime,String arrivalTime,int trainId)
    {
        String query = "insert into trips (startLocation,destination,availableSeats,startTime,arriveTime,available,trainId) values ('%s','%s',%d,'%s','%s',%d,1)".formatted(startLocation,destination,availableSeats,startTime,arrivalTime,trainId);
        try {
            Statement sttmnt = connection.createStatement();
            sttmnt.executeUpdate(query);
           // closeConnection();
            return true;
        }
        catch (Exception ignored)
        {
            System.out.println(ignored.getMessage());
        }
        return false;
    }

    public boolean updateTrip(String startLocation,String destination,int availableSeats,String startTime,String arrivalTime,int trainId)
    {
        String query = "update trips set startLocation = '%s' , destination = '%s' , availableSeats = %d , startTime = '%s' , arriveTime = '%s' , trainId = %d where id = %d".formatted(startLocation,destination,availableSeats,startTime,arrivalTime,trainId);
        try {
            Statement sttmnt = connection.createStatement();
            sttmnt.executeUpdate(query);
           // closeConnection();
            return true;
        }
        catch (Exception ignored)
        {
            System.out.println(ignored.getMessage());
        }
        return false;
    }

    public void bookTkt(String userName, int tripId) {
        // get available seats
        String seatIdQuery = "select availableSeats from trips where id = %d".formatted(tripId);
        int availableSeats = -1;
        try {
            Statement statement = connection.createStatement();
            ResultSet ans = statement.executeQuery(seatIdQuery);
            if(ans.next()){
                availableSeats = ans.getInt(1);
            }
        }
        catch (Exception ignored){
            System.out.println(ignored);
        }
        // insert ticket
        String insertTktQuery = "insert into tickets values(%d, '%s', %d, %d)".formatted(tripId, userName, 150, availableSeats);
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertTktQuery);
        }
        catch (Exception ignored){
            System.out.println(ignored);
        }
        // update available seats
        String updateAvailableSeatsQuery = "update trips set availableSeats = %d where id = %d".formatted(availableSeats - 1, tripId);
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateAvailableSeatsQuery);
            //closeConnection();
        }
        catch (Exception ignored){
            System.out.println(ignored);
        }
    }
    public void deleteTicket(int ticketId, String username)
    {
        String query = "delete from tickets where id = %d and customerUserName = '%s'".formatted(ticketId,username);
        try {
            Statement sttmnt = connection.createStatement();
            sttmnt.executeUpdate(query);
            //closeConnection();
        }
        catch (Exception ignored)
        {
            System.out.println(ignored.getMessage());
        }
    }
    public ResultSet searchTrips(String from, String to, int[] classes, Date date) {
        ResultSet ans;
        String query = "select trips.*, trains.id from trips, trains where trips.trainId = trains.id and available = 1 ";
        if (from != null && !from.isEmpty())
            query += " and startLocation like '%%%s%%' ".formatted(from);
        if (to != null && !to.isEmpty())
            query += " and destination like '%%%s%%' ".formatted(to);
        if (classes != null) {
            if (classes.length == 1)
                query += " and trains.class in (%d) ".formatted(classes[0]);
            else if (classes.length == 2)
                query += " and trains.class in (%d, %d) ".formatted(classes[0], classes[1]);
            else if (classes.length == 3)
                query += " and trains.class in (%d, %d, %d) ".formatted(classes[0], classes[1], classes[2]);
        }
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(date);
            query += " and CONVERT(date, startTime) = '%s' ".formatted(formattedDate);
        }
        try {
            Statement statement = connection.createStatement();
            ans = statement.executeQuery(query);
            return ans;
        } catch (Exception ignored) {
            System.out.println(ignored.getMessage());
        }
        return null;
    }
//    public Connection getConnection() {
//        return connection;
//    }
//    public void closeConnection() {
//        try {
//            if (connection != null) {
//                connection.close();
//            }
//        } catch (SQLException e) {
//            // Handle any errors that occur while closing the connection
//            e.printStackTrace();
//        }
//    }
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
