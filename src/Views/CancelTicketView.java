package Views;

import Controllers.Controller;
import Models.DataBase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CancelTicketView extends View{
    private JPanel panel1;
    private JTable table1;
    Controller controller;
    CancelTicketView(Controller controller){
        super();
        this.controller = controller;
        createUIComponents();
        super.displayFrame(panel1);
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        DefaultTableModel tableModel = new DefaultTableModel();
        String[] columns = {"Trip ID", "Start Location", "Destination", "Start Time",
                "Arrive Time", "Train ID", "Still Available?"};
        for(String str : columns){
            tableModel.addColumn(str);
        }
        tableModel.addRow(columns);
        ResultSet userTrips = new DataBase().getUserTrips(controller.getModel().getUserName());
        try {
            while (userTrips.next()) {
                Object[] arr = new Object[7];
                for (int i = 1; i <= 7; i++){
                    arr[i - 1] = userTrips.getString(i);
                }
                arr[6] = userTrips.getInt(7) == 1? "Yes" : "No";
                tableModel.addRow(arr);
            }
        }
        catch (Exception ignored){}
        table1 = new JTable(tableModel);
    }
}
