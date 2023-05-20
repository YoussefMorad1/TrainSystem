package Models;

import Controllers.State;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Model {
    private State currentState;
    private String userName;
    boolean isAdmin;
    private DataBase database;

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

    public boolean register(String name,String userName,String password,int age,String address)
    {
        DataBase db = new DataBase();
        if(db.register(name,userName,password,age,address))
        {
            return true;
        }
        return false;
    }

    public boolean addTrain(int seats,int classNumber)
    {
        DataBase db = new DataBase();
        if(db.addTrain(seats,classNumber))
        {
            return true;
        }
        return false;
    }

    public boolean updateTrain(int id,int seats,int classNumber)
    {
        DataBase db = new DataBase();
        if(db.updateTrain(id,seats,classNumber))
        {
            return true;
        }
        return false;
    }
    public boolean addTrip(String startLocation,String destination,int availableSeats,String startTime,String arrivalTime,int trainId)
    {
        DataBase db = new DataBase();
        if(db.addTrip(startLocation,destination,availableSeats,startTime,arrivalTime,trainId))
        {
            return true;
        }
        return false;
    }

    public boolean updateTrip(String startLocation,String destination,int availableSeats,String startTime,String arrivalTime,int trainId)
    {
        DataBase db = new DataBase();
        if(db.updateTrip(startLocation,destination,availableSeats,startTime,arrivalTime,trainId))
        {
            return true;
        }
        return false;
    }



    public DataBase getDatabase() {
        return database;
    }
    public ArrayList<Trip> getUserTrips(){
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
