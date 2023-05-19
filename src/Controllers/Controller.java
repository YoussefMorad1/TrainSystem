package Controllers;

import Models.Model;
import Views.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller  {
    private View view;
    private Model model;
    public Controller(Model model, View view){
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
    public void changeFromHomeView(State type){
        model.setState(type);
//        view.updateModel(model);
        view = view.getNewView();
        view.addEventListener(this);
    }
    public void tryLogin(String userName, String password, Boolean isAdmin){
        if(isAdmin != null && model.login(userName, password, isAdmin)){
//            view.updateModel(model);
            view = view.getNewView();
            view.addEventListener(this);
        }
        else{
            view.showError("Wrong Credentials");
        }
    }
    public void openRegister(){
        model.setState(State.REGISTER);
        view = view.getNewView();
        view.addEventListener(this);
    }
    public void deleteTicket(String ticketId, String username){
        ;
    }
    public Model getModel() {
        return model;
    }
}
