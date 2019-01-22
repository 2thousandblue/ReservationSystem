package view.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import manage.OrderTicketManage;
import manage.impl.OrderTicketManageImpl;
import view.tablemodel.FlightTableModel;
import view.tablemodel.OrderTicketModel;
import entity.OrderTicket;
import exception.FlightException;
import exception.OrderTicketException;

public class ChangeTicketJPanel extends JPanel {
	FlightTableModel orderTicket = null;
    JTable table = null;
    private String username ;
    
    public ChangeTicketJPanel (String username){  
    	this.username = username;
    	setPreferredSize(new Dimension(800, 500));
    	setSize (800, 450);
    	setLocation(0, 50);
    	try {
    		orderTicket = new FlightTableModel();
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
				
				int Yes_or_no = JOptionPane.showConfirmDialog (null, "真的要修改吗？", "修改确认", JOptionPane.YES_NO_OPTION);
				if (Yes_or_no == JOptionPane.YES_OPTION) {
					int order_ticketID=0;
					OrderTicketManage orderTicketManage = new OrderTicketManageImpl ();
					try {
						List<OrderTicket> list = orderTicketManage.listOrderTicket(ChangeTicketJPanel.this.username);
						if (list == null || list.size() == 0) {
							return;
						}
						String[] user_select = new String[list.size()]; 
						for (int i=0; i<list.size(); i++) {
							user_select[i] = String.valueOf(list.get(i).getId());
						}
						String sss =  (String)JOptionPane.showInputDialog(ChangeTicketJPanel.this,
								"请选择你需要修改的订单", 
								"订单选择",
								JOptionPane.WARNING_MESSAGE,
								null,
								user_select,
								user_select[0]
								);
						order_ticketID = Integer.valueOf(sss);
						OrderTicketManage orderTicketMange = new OrderTicketManageImpl();
						orderTicketMange.changeTicket(order_ticketID,ticket_id);
						JOptionPane.showMessageDialog(null, "订单修改成功");
						
					} catch (OrderTicketException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
				return;
            }
        });
    }

}
