package Views;

import javax.swing.*;
import Controllers.*;

public class LoginView extends View {
    private JPanel mainPanel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JRadioButton adminRadioButton;
    private JRadioButton userRadioButton;
    private JButton loginButton;
    private JButton registerButton;
    private JButton exitButton;

    public LoginView() {
        super();
        super.displayFrame(mainPanel);
    }

    public void addEventListener(Controller controller) {
        super.addEventListener(controller);
        loginButton.addActionListener(e -> controller.tryLogin(getUserName(), getPassword(), isAdmin()));
        registerButton.addActionListener(e -> controller.openRegister());
        exitButton.addActionListener(e->controller.goBack());
    }

    public Boolean isAdmin() {
        if(!adminRadioButton.isSelected() && !userRadioButton.isSelected())
            return null;
        return adminRadioButton.isSelected();
    }

    public String getUserName() {
        return textField1.getText();
    }

    public String getPassword() {
        return new String(passwordField1.getPassword());
    }
}