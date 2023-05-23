package Models;

import Controllers.State;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Model {
    private State currentState;
    private String userName;
    Boolean isAdmin;
    public Model(State type) {
        currentState = type;
    }

    public boolean login(String userName, String password, Boolean isAdmin) {
        DataBase db = new DataBase();
        if (db.checkLoginCredentials(userName, password, isAdmin)) {
            this.userName = userName;
            this.isAdmin = isAdmin;
            if (isAdmin)
                currentState = State.ADMIN_HOME;
            else
                currentState = State.USER_HOME;
            return true;
        }
        return false;
    }
    public ResultSet getUserInfo(){
        DataBase db = new DataBase();
        return db.getUserInfo(userName);
    }
    public boolean register(String name, String userName, String password, int age, String address) {
        DataBase db = new DataBase();
        if (db.register(name, userName, password, age, address)) {
            return true;
        }
        return false;
    }

    public boolean addTrain(int seats, int classNumber) {
        DataBase db = new DataBase();
        if (db.addTrain(seats, classNumber)) {
            return true;
        }
        return false;
    }

    public boolean updateTrain(int id, int seats, int classNumber) {
        DataBase db = new DataBase();
        if (db.updateTrain(id, seats, classNumber)) {
            return true;
        }
        return false;
    }

    public boolean editProfile(String name, String userName, String password, int age, String address){
        DataBase db = new DataBase();
        return db.editProfile(userName, name, password, age, address);
    }

    public boolean addTrip(String startLocation, String destination, LocalDateTime startTime, LocalDateTime arrivalTime, int trainId) {
        DataBase db = new DataBase();
        if (db.addTrip(startLocation, destination, startTime, arrivalTime, trainId)) {
            return true;
        }
        return false;
    }

    public boolean updateTrip(int tripId, String startLocation, String destination, LocalDateTime startTime, LocalDateTime arrivalTime, int trainId) {
        DataBase db = new DataBase();
        if (db.updateTrip(tripId, startLocation, destination, startTime, arrivalTime, trainId)) {
            return true;
        }
        return false;
    }
    public void logout(){
        userName = null;
        isAdmin = null;
    }

    public ArrayList<Trip> getUserTrips() {
        return new ArrayList<>();
    }

    public void setState(State type) {
        currentState = type;
    }

    public State getCurrentState() {
        return currentState;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
