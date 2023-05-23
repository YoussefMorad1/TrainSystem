package Views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportV2 extends View{
    private JPanel mainPanel;
    private JButton backButton;
    private JButton label1Button;
    private JButton label2Button;
    private JButton label3Button;
    private JButton label4Button;
    private JButton label5Button;
    private JButton label6Button;

    public ReportV2() {
        super();
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent evt) {
//                totalTrains.setText("Total Trains: " + controller.getTotalTrains());
//                totalTrips.setText("Total Trips: "+controller.getTotalTrips());
//                minTicket.setText("Minimum Ticket Price: "+controller.getMinTicketPrice());
//                maxTicket.setText("Maximum Ticket Price: "+controller.getMaxTicketPrice());
//                averageTicket.setText("Average Ticket Price: "+controller.getAvgTicketPrice());
//                revenue.setText("Total Revenue: "+controller.getTotalRevenue());
                // first disable all buttons

                // i want to change the text of the buttons without changing the layout and the style of the buttons
                label1Button.setText("Total Trains: " + controller.getTotalTrains());
                label2Button.setText("Total Trips: "+controller.getTotalTrips());
                label3Button.setText("Min. Ticket Price: "+controller.getMinTicketPrice());
                label4Button.setText("Max. Ticket Price: "+controller.getMaxTicketPrice());
                label5Button.setText("Avg. Ticket Price: "+controller.getAvgTicketPrice());
                label6Button.setText("Total Revenue: "+controller.getTotalRevenue());

                // disable hover effect

            }
        });

        super.displayFrame(mainPanel);
    }

    public void addEventListener(Controllers.Controller controller) {
        super.addEventListener(controller);
        backButton.addActionListener(e->controller.goBack());
    }
}
