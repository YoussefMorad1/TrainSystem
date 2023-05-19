import java.sql.SQLException;

public class Main {
    public static void main(String[] args)  {
        Model model = new Model(ControllerTypes.LOGIN);
        View view = new LoginView(model);
        Controller controller = new Controller(model, view);
    }
}