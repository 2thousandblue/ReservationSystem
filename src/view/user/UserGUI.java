package view.user;

import exception.FlightException;
import view.tablemodel.FlightTableModel;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * @author: 我的袜子都是洞
 * @description: 用户操作界面
 * @path: ReservationSystem-view.user-UserGUI
 * @date: 2019-01-20 21:01
 */
public class UserGUI extends JPanel {
    JPanel btPanel = new JPanel();
    // 查票、订票、退票、改票、购票记录、修改个人信息
    JButton selectBtn = new JButton("查询航班");
    JButton bookBtn = new JButton("预定机票");
    JButton cancaerBtn = new JButton("退订机票");
    JButton changeBtn = new JButton("改签机票");
    JButton changeInfoBtn = new JButton("修改个人信息");
    // table模型
    FlightTableModel flight = null;
    JTable flightTable = null;
    // 表头渲染

    // 表格内容渲染
    DefaultTableCellRenderer r = new DefaultTableCellRenderer();

    // 选中航班JLabel提示信息
    JPanel infoPanel = new JPanel();
    JLabel infoLabel = new JLabel("您选择的航班号是：");
    JLabel flightID = new JLabel("2");
    JLabel infoDetail = new JLabel("于 2019-01-18 11:05  从 【北京】 开往 【纽约】 90分钟 的航班");
    private void init () {
        // 箱式布局，垂直排列
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createTitledBorder("航班信息"));
    }

    private void setComponent () {
        // 左对齐
        selectBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        bookBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        cancaerBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        changeBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        changeInfoBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        try {
            // 导入表格模型
            flight = new FlightTableModel();
            flightTable = new JTable(flight);
            // 设置第一列宽度为40
            flightTable.getColumnModel().getColumn(0).setPreferredWidth(40);
            // 设置第二列宽度为150
            flightTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        } catch (FlightException e) {
            e.printStackTrace();
        }
        // 设置单元格居中
        r.setHorizontalAlignment(JLabel.CENTER);
        flightTable.setDefaultRenderer(Object.class, r);

        flightID.setEnabled(false);
    }

    private void addComponent () {
        // 按钮
        btPanel.add(selectBtn);
        btPanel.add(bookBtn);
        btPanel.add(cancaerBtn);
        btPanel.add(changeBtn);
        btPanel.add(changeInfoBtn);

        // 使用scrollpane会自动显示列名
        JScrollPane scrollPane = new JScrollPane(flightTable);
        add(scrollPane, BorderLayout.CENTER);
        add(btPanel);

        infoPanel.add(infoLabel);
        infoPanel.add(flightID);
        infoPanel.add(infoDetail);
        add(infoPanel);

    }

    public UserGUI () {
        init ();
        setComponent();
        addComponent ();

    }

    public static void main(String[] args) {
        JPanel jp = new UserGUI();
        JFrame jf = new JFrame("userGUI");
        jf.setLayout(new BorderLayout());
        jf.setSize(800, 600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.add(jp);
        jf.setVisible(true);
    }
}
