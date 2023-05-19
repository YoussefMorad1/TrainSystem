package Models;

import Controllers.State;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Model {
    private State currentState;
    private String userName;
    boolean isAdmin;

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
