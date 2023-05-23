package Models;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.security.spec.ECField;
import javax.management.Query;


public class DataBase {
    private Connection connection;

    public DataBase() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src\\Models\\trainSystem.db");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public boolean checkLoginCredentials(String UserName, String Pass, boolean isAdmin) {
        String query;
        ResultSet ans;
        if (isAdmin)
            query = "select * from admins where userName = '%s' and [password] = '%s'".formatted(UserName, Pass);
        else
            query = "select * from customers where userName = '%s' and [password] = '%s'".formatted(UserName, Pass);
        try {
            Statement st = connection.createStatement();
            ans = st.executeQuery(query);
//            closeConnection();
            return ans.next();
        } catch (Exception exception) {
        }
        return false;
    }

    public ResultSet getUserTrips(String username) {
        ResultSet ans;
        String query = "select trips.* from trips, tickets where id = tripId and customerUserName = '%s'".formatted(username);
        try {
            Statement sttmnt = connection.createStatement();
            ans = sttmnt.executeQuery(query);
//             closeConnection();
            return ans;
        } catch (Exception exception) {
        }
        return null;
    }
    public ResultSet getAllTrips(){
        ResultSet ans;
        String query = "select * from trips".formatted();
        try {
            Statement sttmnt = connection.createStatement();
            ans = sttmnt.executeQuery(query);
           //  closeConnection();
            return ans;
        } catch (Exception exception) {
        }
        return null;
    }
    public ResultSet getAllTrains(){
        ResultSet ans;
        String query = "select * from trains".formatted();
        try {
            Statement sttmnt = connection.createStatement();
            ans = sttmnt.executeQuery(query);
             closeConnection();
            return ans;
        } catch (Exception exception) {
        }
        return null;
    }

    public boolean register(String name, String userName, String password, int age, String address) {
        String query = "insert into customers (name,userName,password,age,address) values ('%s','%s','%s',%d,'%s')".formatted(name, userName, password, age, address);
        try {
            Statement sttmnt = connection.createStatement();
            sttmnt.executeUpdate(query);
             closeConnection();
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }

    public boolean addTrain(int seats, int classNumber) {
        String query = "insert into trains (seats,class) values (%d,%d)".formatted(seats, classNumber);
        try {
            Statement sttmnt = connection.createStatement();
            sttmnt.executeUpdate(query);
            closeConnection();
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }

    public boolean updateTrain(int id, int seats, int classNumber) {
        String query = "update trains set seats = %d , class = %d where id = %d".formatted(seats, classNumber, id);
        try {
            Statement sttmnt = connection.createStatement();
            sttmnt.executeUpdate(query);
            closeConnection();
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }

    public boolean addTrip(String startLocation, String destination, LocalDateTime startTime, LocalDateTime arrivalTime, int trainId, float price) {
        // first get the number of availableSeats from the trains relation
        int availableSeats = getTrainSeats(trainId);
        if(availableSeats == -1)
            return false;
        String query = "insert into trips (startLocation,destination,availableSeats,startTime,arriveTime,available,trainId,price) values ('%s','%s',%d,'%s','%s',1, %d, %f)"
                .formatted(startLocation, destination, availableSeats, startTime.toLocalDate() + " " +
                        startTime.toLocalTime(), arrivalTime.toLocalDate() + " " + arrivalTime.toLocalTime(), trainId, price);
        try {
            Statement sttmnt = connection.createStatement();
            sttmnt.executeUpdate(query);
//             closeConnection();
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }
    private int getTrainSeats(int trainId){
        String availableSeatsQuery = "select seats from trains where id = %d".formatted(trainId);
        int availableSeats = -1;
        try {
            Statement statement = connection.createStatement();
            ResultSet seatsResultSet = statement.executeQuery(availableSeatsQuery);
//            closeConnection();
            if (seatsResultSet.next()) {
                availableSeats = seatsResultSet.getInt(1);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return availableSeats;
    }
    public boolean updateTrip(int tripId, String startLocation, String destination, LocalDateTime startTime, LocalDateTime arrivalTime, int trainId, float price) {
        int availableSeats = getTrainSeats(trainId);
        if(availableSeats == -1)
            return false;
        String query = "update trips set startLocation = '%s' , destination = '%s' , availableSeats = %d , startTime = '%s' , arriveTime = '%s' , trainId = %d , price = %f where id = %d"
                .formatted(startLocation, destination, availableSeats, startTime.toLocalDate()  + " " + startTime.toLocalTime(),
                        arrivalTime.toLocalDate() + " " + arrivalTime.toLocalTime(), trainId, price, tripId);
        try {
            Statement sttmnt = connection.createStatement();
            sttmnt.executeUpdate(query);
//             closeConnection();
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }
    public ResultSet getTripInfo(int tripId) {
        String query = "select * from trips where id = %d".formatted(tripId);
        try {
            Statement statement = connection.createStatement();
            ResultSet ans = statement.executeQuery(query);
//            closeConnection();
            return ans;
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return null;
    }
    public ResultSet getTrainInfo(int trainId) {
        String query = "select * from trains where id = %d".formatted(trainId);
        try {
            Statement statement = connection.createStatement();
            ResultSet ans = statement.executeQuery(query);
//            closeConnection();
            return ans;
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return null;
    }

    public boolean bookTkt(String userName, int tripId) {
        // get available seats
        String seatIdQuery = "select availableSeats from trips where id = %d".formatted(tripId);
        int availableSeats = -1;
        try {
            Statement statement = connection.createStatement();
            ResultSet ans = statement.executeQuery(seatIdQuery);

            if (ans.next()) {
                availableSeats = ans.getInt(1);
            }
        } catch (Exception exception) {
            System.out.println(exception);
            return false;
        }
        // insert ticket
        String insertTktQuery = "insert into tickets values(%d, '%s', %d, %d)".formatted(tripId, userName, 150, availableSeats);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertTktQuery);

        } catch (Exception exception) {
            System.out.println(exception);
            return false;
        }
        // update available seats
        String updateAvailableSeatsQuery = "update trips set availableSeats = %d where id = %d".formatted(availableSeats - 1, tripId);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateAvailableSeatsQuery);
//            closeConnection();
        } catch (Exception exception) {
            System.out.println(exception);
            return false;
        }
        return true;
    }

    public void deleteTicket(int ticketId, String username) {
        String query = "delete from tickets where tripId = %d and customerUserName = '%s'".formatted(ticketId, username);
        try {
            Statement sttmnt = connection.createStatement();
            sttmnt.executeUpdate(query);
//            closeConnection();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public ResultSet searchTrips(String from, String to, int[] classes, Date date) {
        ResultSet ans;
        String query = "select trips.*, trains.class from trips, trains where trips.trainId = trains.id and available = 1 ".formatted();
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
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    public ResultSet getUserInfo(String username) {
        ResultSet ans;
        String query = "select * from customers where userName = '%s'".formatted(username);
        try {
            Statement statement = connection.createStatement();
            ans = statement.executeQuery(query);
            return ans;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    public boolean editProfile(String username, String name, String password, int age, String address) {
        String query = "update customers set name = '%s', password = '%s', age = %d, address = '%s' where userName = '%s'"
                .formatted(name, password, age, address, username);
        try {
            Statement sttmnt = connection.createStatement();
            sttmnt.executeUpdate(query);
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }
    public int getTotalTrains()
    {
        String query = "select count(id) from trains";
       return (executeAggregateQuery(query));
    }
    public int getTotalTrips()
    {
        String query = "select count(id) from trips";
        return (executeAggregateQuery(query));
    }
    public int getMinTicketPrice()
    {
        String query = "select min(price) from trips";
        return (executeAggregateQuery(query));
    }
    public int getMaxTicketPrice()
    {
        String query = "select max(price) from trips";
        return (executeAggregateQuery(query));
    }
    public int getAvgTicketPrice()
    {
        String query = "select avg(price) from trips";
        return (executeAggregateQuery(query));
    }
    public int getTotalRevenue()
    {
        String query = "select sum(price) from tickets";
        return (executeAggregateQuery(query));
    }
    public int executeAggregateQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            ResultSet ans = statement.executeQuery(query);

            int result = -1;
            if (ans.next()) {
                result = ans.getInt(1);
            }

            ans.close(); // Close the ResultSet
            return result;
        } catch (SQLException exception) {
            System.out.println(exception);
            return -1;
        }
    }


    //    public int executeAggregateQuery(String query) {
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet ans = statement.executeQuery(query);
//            closeConnection();
//            if (ans.next()) {
//                return ans.getInt(1);
//            }
//        } catch (Exception exception) {
//            System.out.println(exception);
//            return -1;
//        }
//        return -1;
//    }
    //    public Connection getConnection() {
//        return connection;
//    }


    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return connection;
    }



    public boolean isValidTrainId(int trainId) {
        String query = "select * from trains where id = %d".formatted(trainId);
        try {
            Statement statement = connection.createStatement();
            ResultSet ans = statement.executeQuery(query);
            if (ans.next()) {
                return true;
            }
        } catch (Exception exception) {
            System.out.println(exception);
            return false;
        }
        return false;
    }

//    public void closeConnection() {
//        try {
//            if (connection != null) {
//                connection.close();
//            }
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//        }
//    }
}
