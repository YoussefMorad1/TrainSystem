package Controllers;

import Models.DataBase;
import Models.Model;
import Views.View;
import Views.ViewFactory;


import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.time.LocalDateTime;

public class Controller {
    private View view;
    private Model model;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.view.addEventListener(this);
    }

    //    @Override
//    public void actionPerformed(ActionEvent e) {
//        Object source = e.getSource();
//        if (source instanceof JButton) {
//            JButton button = (JButton)e.getSource();
//            if(button.getText().equals("Login") && model.getCurrentState() == State.LOGIN){
//                //tryLogin(); // already handled from inside view
//            }
//            else if(model.getCurrentState() == State.USER_HOME){
//                if(button.getText().equals("Book a ticket")){
//                    changeFromHomeView(State.BOOK_TKT);
//                }
//                else if(button.getText().equals("Cancel a ticket")){
//                    changeFromHomeView(State.DELETE_TKT);
//                }
//                else if(button.getText().equals("Edit profile")){
//                    changeFromHomeView(State.EDIT_INFO);
//                }
//            }
//            else if(model.getCurrentState() == State.BOOK_TKT){
//
//            }
//        }
//    }
//    public void closeDatabaseConnection() {
//        model.getDatabase().closeConnection();
//    }
    public void changeFromHomeView(State type) {
        model.setState(type);
//        view.updateModel(model);
        view = view.getNewView();
        view.addEventListener(this);
    }

    public void tryLogin(String userName, String password, Boolean isAdmin) {
        if (isAdmin != null && model.login(userName, password, isAdmin)) {
//            view.updateModel(model);
            view = view.getNewView();
            view.addEventListener(this);
            // closeDatabaseConnection();
        } else {
            view.showError("Wrong Credentials");
        }
    }

    public void tryRegister(String name, String userName, String password, int age, String address) {
        if (name.isEmpty() || userName.isEmpty() || password.isEmpty() || age == 0 || address.isEmpty()) {
            view.showError("Please fill all the fields");
            return;
        }
        if (model.register(name, userName, password, age, address)) {
            view = view.getNewView();
            view.addEventListener(this);
        } else {
            view.showError("Wrong Credentials");
        }
    }


    public String[] getUserInfo() {
        ResultSet rs = model.getUserInfo();
        String[] info = new String[5];
        try {
            rs.next();
            info[0] = rs.getString("name");
            info[1] = rs.getString("userName");
            info[2] = rs.getString("password");
            info[3] = rs.getString("age");
            info[4] = rs.getString("address");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    public void tryAddTrain(int seats, int classNumber) {
        if (seats == 0 || classNumber == 0) {
            view.showError("Please fill all the fields");
            return;
        } else if (classNumber > 3 || classNumber < 1) {
            view.showError("Class number must be between 1 and 3");
            return;
        }
        if (model.addTrain(seats, classNumber)) {
            view = view.getNewView();
            view.addEventListener(this);
        }

    }

    public void tryUpdateTrain(int id, int seats, int classNumber) {
        if (seats == 0 || classNumber == 0) {
            view.showError("Please fill all the fields");
            return;
        }
        if (model.updateTrain(id, seats, classNumber)) {
            view = view.getNewView();
            view.addEventListener(this);
        }

    }

    public void tryAddTrip(String startLocation, String destination, LocalDateTime startTime, LocalDateTime arrivalTime, int trainId, float price) {
        if (startLocation.isEmpty() || destination.isEmpty() || startTime == null || arrivalTime == null) {
            view.showError("Please fill all the fields");
            return;
        }
        if (model.addTrip(startLocation, destination, startTime, arrivalTime, trainId, price)) {
            view = view.getNewView();
            view.addEventListener(this);
        } else {
            view.showError("Please enter valid train ID");
        }
    }

    public void tryUpdateTrip(int id, String startLocation, String destination, LocalDateTime startTime, LocalDateTime arrivalTime, int trainId, float price) {
        if (startLocation.isEmpty() || destination.isEmpty() || startTime == null || arrivalTime == null || trainId == 0) {
            view.showError("Please fill all the fields");
            return;
        }
        if (model.updateTrip(id, startLocation, destination, startTime, arrivalTime, trainId, price)) {
            view = view.getNewView();
            view.addEventListener(this);
        }
    }

    public void editProfile(String userName, String name, String password, String ageString, String address) {
        int age = 0;
        try {
            age = Integer.parseInt(ageString);
        } catch (Exception e) {
            view.showError("Please enter a valid age");
            return;
        }
        if (name.isEmpty() || userName.isEmpty() || password.isEmpty() || age == 0 || address.isEmpty()) {
            view.showError("Please fill all the fields");
            return;
        }
        if (model.editProfile(userName, name, password, age, address)) {
            view = view.getNewView();
            view.addEventListener(this);
        }
    }





    public void openRegister() {
        model.setState(State.REGISTER);
        view = view.getNewView();
        view.addEventListener(this);
    }

    public void openAddTrain() {
        model.setState(State.ADD_TRAIN);
        view = view.getNewView();
        view.addEventListener(this);
    }

    public void openUpdateTrain() {
        model.setState(State.UPDATE_TRAIN);
        view = view.getNewView();
        view.addEventListener(this);
    }

    public void openAddTrip() {
        model.setState(State.ADD_TRIP);
        view = view.getNewView();
        view.addEventListener(this);
    }

    public void openUpdateTrip() {
        model.setState(State.UPDATE_TRIP);
        view = view.getNewView();
        view.addEventListener(this);
    }
    public void openReport(){
        model.setState(State.REPORT);
        view = view.getNewView();
        view.addEventListener(this);
    }

    public void deleteTicket(int ticketId, String username) {
        DataBase db = new DataBase();
        db.deleteTicket(ticketId, username);
        view = view.getNewView();
        view.addEventListener(this);

    }

    public void bookTkt(int tripId) {
        DataBase db = new DataBase();
        db.bookTkt(getModel().getUserName(), tripId);
        view = view.getNewView();
        view.addEventListener(this);
    }
    public void goBack(){
        State curState = model.getCurrentState(), newState = State.LOGIN;
        if(curState == State.ADD_TRIP || curState == State.ADD_TRAIN || curState == State.UPDATE_TRAIN || curState == State.UPDATE_TRIP)
            newState = State.ADMIN_HOME;
        else if(curState == State.ADMIN_HOME || curState == State.USER_HOME || curState == State.REGISTER)
            model.logout();
        else if(curState == State.BOOK_TKT || curState == State.DELETE_TKT || curState == State.EDIT_INFO)
            newState = State.USER_HOME;
        else if(curState == State.LOGIN){
            view.dispose();
            return;
        }
        model.setState(newState);
        view = view.getNewView();
        view.addEventListener(this);
    }
    public int getTotalTrains()
    {
        DataBase db = new DataBase();
        int totalTrains = db.getTotalTrains();
        return totalTrains;
    }
    public int getTotalTrips()
    {
        DataBase db = new DataBase();
        int totalTrips = db.getTotalTrips();
        return totalTrips;
    }
    public int getMinTicketPrice()
    {
        DataBase db = new DataBase();
        int minTicketPrice = db.getMinTicketPrice();
        return minTicketPrice;
    }
    public int getMaxTicketPrice()
    {
        DataBase db = new DataBase();
        int maxTicketPrice = db.getMaxTicketPrice();
        return maxTicketPrice;
    }
    public int getAvgTicketPrice()
    {
        DataBase db = new DataBase();
        int avgTicketPrice = db.getAvgTicketPrice();
        return avgTicketPrice;
    }
    public int getTotalRevenue()
    {
        DataBase db = new DataBase();
        int totalRevenue = db.getTotalRevenue();
        return totalRevenue;
    }


    public Model getModel() {
        return model;
    }


}
