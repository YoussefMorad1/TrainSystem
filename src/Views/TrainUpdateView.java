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

    public TrainUpdateView() {
        super();
        super.displayFrame(panel1);
    }

    public void addEventListener(Controller controller) {
        super.addEventListener(controller);
        updateButton.addActionListener(e -> controller.tryUpdateTrain(Integer.parseInt(id.getText()), Integer.parseInt(newSeats.getText()),Integer.parseInt(newClass.getText())));
        backButton.addActionListener(e->controller.goBack());
    }
}
