import javax.swing.*;

public class ModelFactory {
    public static Model createModel(ControllerTypes type) {
        if (type == ControllerTypes.LOGIN) {
            return new LoginModel();
        }
        return null;
    }
}
