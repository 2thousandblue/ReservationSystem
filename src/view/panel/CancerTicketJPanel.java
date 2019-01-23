package view.panel;

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
import view.tablemodel.OrderTicketModel;
import exception.FlightException;

public class CancerTicketJPanel extends JPanel {
	OrderTicketModel orderTicket = null;
    JTable table = null;
    
    public CancerTicketJPanel (String loginname){  
    	setPreferredSize(new Dimension(800, 500));
    	setSize (800, 450);
    	setLocation(0, 50);
    	
    	try {
    		orderTicket = new OrderTicketModel(loginname);
            table = new JTable(orderTicket);
            setComponent ();
            addComponent();
            addListener();
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
    private void addListener () {
        // selection监听器监听表格哪行被选中
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
            	// 获取哪一行被选中
				int row = table.getSelectedRow();
				// 订单号
				int ticket_id = Integer.valueOf((String)orderTicket.getValueAt(row, 0)) ;
				String takeoff_time = (String)orderTicket.getValueAt(row, 1);
				String start_place = (String)orderTicket.getValueAt(row, 3);
				String end_place = (String)orderTicket.getValueAt(row, 4);
				String flying_time = (String)orderTicket.getValueAt(row, 2);
				String flightInfo = "是否取消于 "+takeoff_time+"  从 【"+start_place+"】 开往 【"+end_place+"】 "+flying_time+" 的航班";
				int Yes_or_no = JOptionPane.showConfirmDialog (null, flightInfo, "是否取消订单", JOptionPane.YES_NO_OPTION);
				if (Yes_or_no == JOptionPane.YES_OPTION) {
					try {
						OrderTicketManage orderTicketMange = new OrderTicketManageImpl();
						orderTicketMange.cancerTicket(ticket_id);
						JOptionPane.showMessageDialog(null, "订单取消成功");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage());
					}
					
				}
				return;
            }
        });
    }

}
