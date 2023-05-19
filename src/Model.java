import java.lang.module.ModuleDescriptor;

public class Model {
    private ControllerTypes currentState;
    private String userName;
    private String password;
    boolean isAdmin;
    public Model(ControllerTypes type){
        currentState = type;
    }
    public boolean login(String userName, String password, Boolean isAdmin) {
        DataBase db = new DataBase();
        if(db.checkLoginCredentials(userName, password, isAdmin)){
            this.userName = userName;
            this.isAdmin = isAdmin;
            this.password = password;
            if (isAdmin)
                currentState = ControllerTypes.ADMIN_HOME;
            else
                currentState = ControllerTypes.USER_HOME;
            return true;
        }
        return false;
    }
    public void setState(ControllerTypes type){
        currentState = type;
    }
    public ControllerTypes getCurrentState(){
        return currentState;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
