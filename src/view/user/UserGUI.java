package view.user;
import exception.FlightException;
import exception.OrderTicketException;
import view.tablemodel.FlightTableModel;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import manage.impl.OrderTicketManageImpl;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author: 我的袜子都是洞
 * @description: 用户操作界面
 * @path: ReservationSystem-view.user-UserGUI
 * @date: 2019-01-20 21:01
 */
public class UserGUI extends JPanel {
	private String loginname;
    private JPanel btPanel = new JPanel();
    // 查票、订票、退票、改票、购票记录、修改个人信息
    private JButton bookBtn = new JButton("预定机票");
    private JButton selectBtn = new JButton("查询订单");
    private JButton cancaerBtn = new JButton("退订机票");
    private JButton changeBtn = new JButton("改签机票");
    private JButton changeInfoBtn = new JButton("修改个人信息");
    // table模型
    private FlightTableModel flight = null;
    private JTable flightTable = null;
    // 表头渲染

    // 表格内容渲染
    private DefaultTableCellRenderer r = new DefaultTableCellRenderer();

    // 选中航班JLabel提示信息
    private JPanel infoPanel = new JPanel();
    private JLabel infoLabel = new JLabel("您选择的航班是：");
    private int flightID = 0;
    private JLabel infoDetail = new JLabel("于 2019-01-18 11:05  从 【北京】 开往 【纽约】 90分钟 的航班");
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
    }

    private void addComponent () {
        // 按钮
        btPanel.add(bookBtn);
        btPanel.add(selectBtn);
        btPanel.add(cancaerBtn);
        btPanel.add(changeBtn);
        btPanel.add(changeInfoBtn);

        // 使用scrollpane会自动显示列名
        JScrollPane scrollPane = new JScrollPane(flightTable);
        add(scrollPane, BorderLayout.CENTER);
        add(scrollPane);
        add(btPanel);

        infoPanel.add(infoLabel);
        infoPanel.add(infoDetail);
        add(infoPanel);

    }
    
    public void addListenter () {
    	flightTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// 获取哪一行被选中
				int row = flightTable.getSelectedRow();
				// 更新label
				flightID = row+1;
				// 出发时间
				String takeoff_time = (String)flight.getValueAt(row, 1);
				// 出发地
				String start_place = (String)flight.getValueAt(row, 3);
				// 目的地
				String end_place = (String)flight.getValueAt(row, 4);
				// 飞行时间
				String flying_time = (String)flight.getValueAt(row, 2);
				String flightInfo = "于 "+takeoff_time+"  从 【"+start_place+"】 开往 【"+end_place+"】 "+flying_time+" 的航班";
				// 更新label
				infoDetail.setText(flightInfo);
			}
    	});
    	bookBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				OrderTicketManageImpl orderTicketManageImpl = new OrderTicketManageImpl ();
				if ("".equals(UserGUI.this.loginname) || UserGUI.this.loginname==null) {
					JOptionPane.showMessageDialog(null,"姓名获取失败");
					return ;
				}
				if (flightID == 0) {
					JOptionPane.showMessageDialog(null,"请选择一个航班");
					return ;
				}
				try {
					boolean isSuccess = orderTicketManageImpl.bookTicket(UserGUI.this.loginname, flightID);
					if (isSuccess) {
						JOptionPane.showMessageDialog(null,"订票成功");
					} else {
						JOptionPane.showMessageDialog(null,"订票失败");
					}
				} catch (OrderTicketException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
    		
    	});
    	
    }
    
    public UserGUI (String loginname) {
    	this.loginname = loginname;
        init ();
        setComponent ();
        addComponent ();
        addListenter ();
    }
    
    public void getGUI (UserGUI usergui) {
        JFrame jf = new JFrame("航空订票系统");
        jf.setLayout(new BorderLayout());
        jf.setSize(800, 600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.add(usergui);
        jf.setVisible(true);
    }
    
    public static void main(String[] args) {
    	UserGUI usergui = new UserGUI ("user1");
    	usergui.getGUI(usergui);
	}
}