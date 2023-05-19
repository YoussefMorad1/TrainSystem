import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private View view;
    private Model model;
    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
        this.view.addEventListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton)e.getSource();
            if(button.getText().equals("Login") && model.getCurrentState() == ControllerTypes.LOGIN){
                tryLogin();
            }
            else if(model.getCurrentState() == ControllerTypes.USER_HOME){
                if(button.getText().equals("Book a ticket")){
                    changeFromHomeView(ControllerTypes.BOOK_TKT);
                }
                else if(button.getText().equals("Cancel a ticket")){
                    changeFromHomeView(ControllerTypes.DELETE_TKT);
                }
                else if(button.getText().equals("Edit profile")){
                    changeFromHomeView(ControllerTypes.EDIT_INFO);
                }
            }

        }
    }
    private void changeFromHomeView(ControllerTypes type){
        model.setState(type);
        view.updateModel(model);
        view = view.getNewView();
        view.addEventListener(this);
    }
    private void tryLogin(){
        if(view.isAdmin() != null && model.login(view.getUserName(), view.getPassword(), view.isAdmin())){
            view.updateModel(model);
            view = view.getNewView();
            view.addEventListener(this);
        }
        else{
            view.showError("Wrong Credentials");
        }
    }
}
