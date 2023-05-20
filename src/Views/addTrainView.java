package Views;

import javax.swing.*;


import Controllers.Controller;



public class addTrainView extends View{
    private JPanel panel1;
    private JButton addTrainButton;
    private JTextField seatsNumber;
    private JTextField classNumber;


    public addTrainView() {
        super();
        super.displayFrame(panel1);
    }


    public void addEventListener(Controller controller) {
        super.addEventListener(controller);
        addTrainButton.addActionListener(e -> controller.tryAddTrain(Integer.parseInt(seatsNumber.getText()),Integer.parseInt(classNumber.getText())));
    }

}
