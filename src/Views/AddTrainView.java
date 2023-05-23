package Views;

import javax.swing.*;


import Controllers.Controller;



public class AddTrainView extends View{
    private JPanel panel1;
    private JButton addTrainButton;
    private JTextField seatsNumber;
    private JTextField classNumber;
    private JButton backButton;


    public AddTrainView() {
        super();
        super.displayFrame(panel1);
    }


    public void addEventListener(Controller controller) {
        super.addEventListener(controller);
        addTrainButton.addActionListener(e -> controller.tryAddTrain(Integer.parseInt(seatsNumber.getText()),Integer.parseInt(classNumber.getText())));
        backButton.addActionListener(e -> controller.goBack());
    }

}
