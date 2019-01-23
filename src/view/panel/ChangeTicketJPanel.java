package view.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import manage.FlightManage;
import manage.OrderTicketManage;
import manage.impl.FlightManageImpl;
import manage.impl.OrderTicketManageImpl;
import view.tablemodel.OrderTicketModel;
import entity.Flight;
import entity.OrderTicket;
import exception.FlightException;
import exception.OrderTicketException;

public class ChangeTicketJPanel extends JPanel {
	OrderTicketModel orderTicket = null;
    JTable table = null;
    private String loginname ;
    
    public ChangeTicketJPanel (String loginname){  
    	this.loginname = loginname;
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
				
				int Yes_or_no = JOptionPane.showConfirmDialog (null, "真的要修改吗？", "修改确认", JOptionPane.YES_NO_OPTION);
				if (Yes_or_no == JOptionPane.YES_OPTION) {
					FlightManage flightManage = new FlightManageImpl ();
					try {
						List<Flight> list = flightManage.listCanFlight();
						if (list == null || list.size() == 0) {
							return;
						}
						String[] user_select = new String[list.size()]; 
						Map<String, Integer> hashMap = new HashMap<String, Integer>();
						
						for (int i=0; i<list.size(); i++) {
							String s = list.get(i).getStart_place()+"->"+list.get(i).getEnd_place()+" "+list.get(i).getTakeoff_time();
							hashMap.put(s, list.get(i).getId());
							user_select[i] = s;
						}
						
						String flightIdStr =  (String)JOptionPane.showInputDialog(ChangeTicketJPanel.this,
								"请选择航班", 
								"航班选择",
								JOptionPane.WARNING_MESSAGE,
								null,
								user_select,
								user_select[0]
								);
						if (flightIdStr==null || "".equals(flightIdStr)) {
							return ;
						}
						
						// 用户选择的目标航班
						int TargetFlightId = hashMap.get(flightIdStr);
						OrderTicketManage orderTicketMange = new OrderTicketManageImpl();
						orderTicketMange.changeTicket(ticket_id,TargetFlightId);
						JOptionPane.showMessageDialog(null, "订单修改成功");
						
					} catch (FlightException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
//					}
					} catch (OrderTicketException e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}
				return;
            }
        });
    }

}
