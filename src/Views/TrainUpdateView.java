package Views;

import javax.swing.*;
import Controllers.*;

public class TrainUpdateView extends View {
    private JPanel panel1;
    private JTextField id;
    private JTextField newSeats;
    private JTextField newClass;
    private JButton updateButton;
    private JButton backButton;
    private JRadioButton firstRadioButton;
    private JRadioButton secondRadioButton;
    private JRadioButton thirdRadioButton;
    private int trainId;

    public TrainUpdateView(int trainId, Controller controller) {
        super();
        this.trainId = trainId;
        this.controller = controller;
        showTrainInfo();
        super.displayFrame(panel1);
    }
    public void showTrainInfo()
    {
        String[] info = controller.getTrainInfo(trainId);
        id.setText(info[0]);
        newSeats.setText(info[1]);
        if(info[2].equals("1"))
            firstRadioButton.setSelected(true);
        else if(info[2].equals("2"))
            secondRadioButton.setSelected(true);
        else
            thirdRadioButton.setSelected(true);
        id.setEditable(false);
    }

    public void addEventListener(Controller controller) {
        super.addEventListener(controller);
        updateButton.addActionListener(e -> controller.tryUpdateTrain(Integer.parseInt(id.getText()), newSeats.getText(),
                firstRadioButton.isSelected() ? 1 : secondRadioButton.isSelected() ? 2 : 3));
        backButton.addActionListener(e->controller.goBack());
    }
}
