package Views;

import javax.swing.*;


import Controllers.Controller;



public class AddTrainView extends View{
    private JPanel panel1;
    private JButton addTrainButton;
    private JTextField seatsNumber;
    private JTextField classNumber;
    private JButton backButton;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton thirdRadioButton;


    public AddTrainView() {
        super();
        super.displayFrame(panel1);
    }


    public void addEventListener(Controller controller) {
        super.addEventListener(controller);
        // get the selected radio button

        addTrainButton.addActionListener(e -> controller.tryAddTrain(
                    seatsNumber.getText(),
                radioButton1.isSelected() ? 1 : radioButton2.isSelected() ? 2 : thirdRadioButton.isSelected() ? 3 : 0
                )
        );
        backButton.addActionListener(e -> controller.goBack());
    }

}
