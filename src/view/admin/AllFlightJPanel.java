package view.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import view.tablemodel.FlightTableModel;
import exception.FlightException;

public class AllFlightJPanel  extends JPanel{
	FlightTableModel flight = null;
    JTable table = null;
    
    public AllFlightJPanel (){  
    	setPreferredSize(new Dimension(800, 500));
    	setSize (800, 450);
    	setLocation(0, 50);
    	
    	try {
    		flight = new FlightTableModel();
            table = new JTable(flight);
            setComponent ();
            addComponent();
            
    	} catch (FlightException e) {
    		JOptionPane.showMessageDialog(null, e.getMessage());
    	}
    }
    
    private void setComponent () { 
        setLayout(new FlowLayout());
    }
    
    private void addComponent () {
        // 使用scrollpane会自动显示列名
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
    
}
