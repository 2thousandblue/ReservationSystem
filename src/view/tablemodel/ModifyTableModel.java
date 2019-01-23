package view.tablemodel;

import entity.Flight;
import exception.FlightException;
import manage.FlightManage;
import manage.impl.FlightManageImpl;
import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * @author: 我的袜子都是洞
 * @description: 航班表格模型
 * @path: ReservationSystem-view.tablemodel-FlightTableModel
 * @date: 2019-01-20 16:15
 */
public class ModifyTableModel extends AbstractTableModel {
    // 列名
    String[] columnName = new String[]{"航班号", "出发时间", "飞行时间", "出发地", "目的地", "余票", "票价"};
    // 表格具体数据
    String[][] flightDate;
    FlightManage flightManage = null;
    List<Flight> flights = null;

    public ModifyTableModel () throws FlightException {
        // 航班信息管理实现类
        flightManage = new FlightManageImpl();
        // 获得所有航班信息
        flights = flightManage.listCanModify();
        // 把航班信息变成二维数组
        flightDate = new String[flights.size()][];
        for (int i=0; i<flights.size(); i++) {
            Flight flight = flights.get(i);
            flightDate[i] = new String[]{
                    String.valueOf(flight.getId()),
                    flight.getTakeoff_time(),
                    flight.getFlying_time(),
                    flight.getStart_place(),
                    flight.getEnd_place(),
                    String.valueOf(flight.getTicket()),
                    String.valueOf(flight.getPrice())};
        }
    }

    /**
     * 返回一共多少行
     */
    @Override
    public int getRowCount() {
        return flightDate.length;
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
        return flightDate[rowIndex][columnIndex];
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
