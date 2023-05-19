import javax.swing.*;

public class ViewFactory {
    public static JFrame createView(ControllerTypes type, Controller controller) {
        if (type == ControllerTypes.LOGIN) {
            LoginView view = new LoginView();
            view.addEventListener(controller);
            return view;
        }
        return null;
    }
}
