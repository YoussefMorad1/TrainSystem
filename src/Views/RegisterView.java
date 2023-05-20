package Views;

import Controllers.Controller;

import javax.swing.*;

public class RegisterView extends View {
    private JPanel panel1;

    private JTextField nameField;
    private JTextField userNameField;
    private JPasswordField passwordField;
    private JTextField ageField;
    private JTextField adressField;
    private JButton registerButton;

    public RegisterView() {
        super();
        super.displayFrame(panel1);
    }

    @Override
    public void addEventListener(Controller controller) {
        super.addEventListener(controller);
        registerButton.addActionListener(e -> controller.tryRegister(nameField.getText(),userNameField.getText(), new String(passwordField.getPassword()), Integer.parseInt(ageField.getText()), adressField.getText()));

    }
}
