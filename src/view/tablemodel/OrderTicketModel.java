package view.tablemodel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import manage.OrderTicketManage;
import manage.impl.OrderTicketManageImpl;
import entity.OrderTicket;
import exception.FlightException;
import exception.OrderTicketException;

public class OrderTicketModel  extends AbstractTableModel {
	// 列名
    String[] columnName = new String[]{"订单号", "航班号", "出发时间", "出发地", "目的地", "票价", "姓名","身份证号"};
    // 表格具体数据
    String[][] ticketsDate;
    OrderTicketManage orderTicketManage = null;
    List<OrderTicket> tickets = null;

    public OrderTicketModel (String loginname) throws FlightException {
        // 航班信息管理实现类
    	orderTicketManage = new OrderTicketManageImpl();
        // 获得所有航班信息
    	try {
			tickets = orderTicketManage.listOrderTicket(loginname);
			// 把航班信息变成二维数组
	    	ticketsDate = new String[tickets.size()][];
	        for (int i=0; i<tickets.size(); i++) {
	            OrderTicket ticket = tickets.get(i);
	            ticketsDate[i] = new String[]{
	                    String.valueOf(ticket.getId()),
	                    String.valueOf(ticket.getFlight_id()),
	                    ticket.getTakeoff_time(),
	                    ticket.getStart_place(),
	                    ticket.getEnd_place(),
	                    String.valueOf(ticket.getPrice()),
	                    ticket.getUsername(),
	                    ticket.getIdentity()
	            };
	        }
    	} catch (OrderTicketException e) {
			e.printStackTrace();
		}
        
    }

    /**
     * 返回一共多少行
     */
    @Override
    public int getRowCount() {
        return ticketsDate.length;
    }

    /**
     * 返回一共多少列
     */
    @Override
    public int getColumnCount() {
        return columnName.length;
    }

    /**
     * 返回每一个单元格的值
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return ticketsDate[rowIndex][columnIndex];
    }

    /**
     * 获取每一列的名称
     */
    @Override
    public String getColumnName(int column) {
        return columnName[column];
    }

    /**
     * 单元格是否可以修改
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
