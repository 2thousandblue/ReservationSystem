package view.flight;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import exception.FlightException;
import view.tablemodel.FlightTableModel;

/**
 * @author: 我的袜子都是洞
 * @description:
 * @path: ReservationSystem-view.flight-FlightGui
 * @date: 2019-01-20 15:06
 */
public class FlightGui extends JFrame {
    FlightTableModel flight = null;
    JTable table = null;
    FlightGui(String title) throws FlightException {
        super(title);
        flight = new FlightTableModel();
        table = new JTable(flight);
        init();
        setComponent ();
        addComponent();
        addListener();
        setVisible(true);
    }
    private void init () {
        setSize(600, 400);
        // 设置窗口位置居中
        setLocationRelativeTo(null);
        // 设置布局
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void setComponent () {
        // 设置第一列宽度为40
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        // 设置第二列宽度为150
        table.getColumnModel().getColumn(1).setPreferredWidth(150);

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
                // 被选中的行
                int row = table.getSelectedRow();
                String str = (String)flight.getValueAt(row,0);
                System.out.println("选中的行号是："+row+",航班号是："+str);
            }
        });
    }
    public static void main(String[] args) throws FlightException {
        new FlightGui("航班信息");
    }
}

