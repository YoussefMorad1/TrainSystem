package Views;

import javax.swing.*;
import Controllers.*;
public class trainUpdateView extends View {
    private JPanel panel1;
    private JTextField id;
    private JTextField newSeats;
    private JTextField newClass;
    private JButton updateButton;

    public trainUpdateView() {
        super();
        super.displayFrame(panel1);
    }

    public void addEventListener(Controller controller) {
        super.addEventListener(controller);
        updateButton.addActionListener(e -> controller.tryUpdateTrain(Integer.parseInt(id.getText()), Integer.parseInt(newSeats.getText()),Integer.parseInt(newClass.getText())));
    }
}