package Views;

import javax.swing.*;

public class ReportView extends View {
    private JPanel panel1;
    private JLabel totalTrains;
    private JLabel totalTrips;
    private JLabel minTicket;
    private JLabel maxTicket;
    private JLabel averageTicket;
    private JLabel revenue;

    public ReportView() {
        super();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent evt) {
                totalTrains.setText("Total Trains: " + controller.getTotalTrains());
                totalTrips.setText("Total Trips: "+controller.getTotalTrips());
                minTicket.setText("Minimum Ticket Price: "+controller.getMinTicketPrice());
                maxTicket.setText("Maximum Ticket Price: "+controller.getMaxTicketPrice());
                averageTicket.setText("Average Ticket Price: "+controller.getAvgTicketPrice());
                revenue.setText("Total Revenue: "+controller.getTotalRevenue());
            }
        });

        super.displayFrame(panel1);

    }
}