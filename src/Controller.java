import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private ControllerTypes currentState;
    private LoginView loginView;
    private LoginModel loginModel;
    private UserHomeView userHomeView;
    private UserHomeModel userHomeModel;
    private String userName = null;
    boolean isAdmin = false;
    public Controller(ControllerTypes type){
        this.currentState = type;
        if(type == ControllerTypes.LOGIN){
            this.loginView = new LoginView();
            this.loginModel = new LoginModel();
            loginView.addEventListener(this);
        }
        else if(type == ControllerTypes.USER_HOME){
            this.userHomeView = new UserHomeView();
            this.userHomeModel = new UserHomeModel();
            userHomeView.addEventListener(this);
        }
//        else if(type == ControllerTypes.BOOK_TKT){
//            this.bookTktView = new BookTktView();
//            this.bookTktModel = new BookTktModel();
//            bookTktView.addEventListener(this);
//        }
//        else if(type == ControllerTypes.DELETE_TKT){
//            this.deleteTktView = new DeleteTktView();
//            this.deleteTktModel = new DeleteTktModel();
//            deleteTktView.addEventListener(this);
//        } else if (type == ControllerTypes.EDIT_INFO) {
//            this.editInfoView = new EditInfoView();
//            this.editInfoModel = new EditInfoModel();
//            editInfoView.addEventListener(this);
//        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton)e.getSource();
            if(button.getText().equals("Login") && currentState == ControllerTypes.LOGIN){
                tryLogin();
            }
            else if(currentState == ControllerTypes.USER_HOME){
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
        userHomeView.dispose();
        Controller newController = new Controller(type);
        copy(newController);
    }
    private void tryLogin(){
        Controller newController;
        if(loginModel.login(loginView.getUserName(), loginView.getPassword(), loginView.isAdmin())){
            this.userName = loginView.getUserName();
            this.isAdmin = loginView.isAdmin();
            this.loginView.dispose();
            if (loginView.isAdmin())
                newController = new Controller(ControllerTypes.ADMIN_HOME);
            else
                newController = new Controller(ControllerTypes.USER_HOME);
            copy(newController);
        }
    }
    private void copy(Controller other){
       this.currentState = other.currentState;

       this.loginModel = other.loginModel;
       this.loginView = other.loginView;

       this.userHomeModel = other.userHomeModel;
       this.userHomeView = other.userHomeView;

       this.userName = other.userName;
       this.isAdmin = other.isAdmin;
       // .. Youssef, Add the new attributes here!
    }
}
