package Views;

import javax.swing.*;
import Controllers.*;

import java.sql.ResultSet;
import java.sql.Statement;

public class TrainUpdateView extends View {
    private JPanel panel1;
    private JTextField id;
    private JTextField newSeats;
    private JTextField newClass;
    private JButton updateButton;
    private JButton backButton;
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
        newClass.setText(info[2]);
        id.setEditable(false);
    }

    public void addEventListener(Controller controller) {
        super.addEventListener(controller);
        updateButton.addActionListener(e -> controller.tryUpdateTrain(Integer.parseInt(id.getText()), newSeats.getText(),Integer.parseInt(newClass.getText())));
        backButton.addActionListener(e->controller.goBack());
    }
}
