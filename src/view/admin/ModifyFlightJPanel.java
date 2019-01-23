package view.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import manage.OrderTicketManage;
import manage.impl.OrderTicketManageImpl;
import view.tablemodel.FlightTableModel;
import view.tablemodel.ModifyTableModel;
import exception.FlightException;

public class ModifyFlightJPanel  extends JPanel{
	ModifyTableModel flight = null;
    JTable table = null;
    
    public ModifyFlightJPanel (){  
    	setPreferredSize(new Dimension(800, 500));
    	setSize (800, 450);
    	setLocation(0, 50);
    	
    	try {
    		flight = new ModifyTableModel();
            table = new JTable(flight);
            setComponent ();
            addComponent();
            addListenter ();
    	} catch (FlightException e) {
    		JOptionPane.showMessageDialog(null, e.getMessage());
    	}
    }
    
    private void setComponent () { 
        setLayout(new FlowLayout());
    }
    
    private void addListenter () {
    	table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
            	// 获取哪一行被选中
				int row = table.getSelectedRow();
				// 订单号
				int flight_id = Integer.valueOf((String)table.getValueAt(row, 0)) ;
				String info = "是否修改航班:"+flight_id+"";
				int Yes_or_no = JOptionPane.showConfirmDialog (null, info, "修改航班", JOptionPane.YES_NO_OPTION);
				if (Yes_or_no == JOptionPane.YES_OPTION) {
					new ChangeFlightFrame(flight_id);
				}
				return;
            }
        });
    }
    
    private void addComponent () {
        // 使用scrollpane会自动显示列名
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
    
}
