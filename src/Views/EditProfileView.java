package Views;

import javax.swing.*;
import Controllers.Controller;

public class EditProfileView extends View{
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton button1;
    private JButton backButton;

    public EditProfileView(Controller controller) {
        super();
        this.controller = controller;
        showUserInfo();
        super.displayFrame(panel1);
    }

    public void addEventListener(Controller controller) {
        super.addEventListener(controller);
        button1.addActionListener(e -> controller.editProfile(textField1.getText(), textField2.getText(), textField3.getText(), textField4.getText(), textField5.getText()));
        backButton.addActionListener(e->controller.goBack());
    }



    private void showUserInfo(){
        String[] info = controller.getUserInfo();
        textField1.setText(info[0]);
        textField2.setText(info[1]);
        textField3.setText(info[2]);
        textField4.setText(info[3]);
        textField5.setText(info[4]);
        // disable editing username
        textField1.setEditable(false);
    }
}
