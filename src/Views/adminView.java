package Views;

import Controllers.Controller;

import javax.swing.*;

public class adminView extends View{
    private JPanel panel1;
    private JButton trainAddButoon;
    private JButton trainUpdatebutton;
    private JButton addATripButton;
    private JButton updateATripButton;
    private JButton backButton;

    public adminView() {
        super();
        super.displayFrame(panel1);
    }

    @Override
    public void addEventListener(Controller controller) {
        super.addEventListener(controller);
        trainAddButoon.addActionListener(e -> controller.openAddTrain());
        trainUpdatebutton.addActionListener(e -> controller.openUpdateTrain());
        addATripButton.addActionListener(e -> controller.openAddTrip());
        updateATripButton.addActionListener(e -> controller.openUpdateTrip());
        backButton.addActionListener(e->controller.goBack());
    }
}
