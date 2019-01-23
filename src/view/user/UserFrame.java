package view.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import entity.OrderTicket;
import exception.OrderTicketException;
import exception.UserException;
import manage.OrderTicketManage;
import manage.UserManage;
import manage.impl.OrderTicketManageImpl;
import manage.impl.UserManageImpl;
import view.panel.CancerTicketJPanel;
import view.panel.ChangeTicketJPanel;
import view.panel.FlightJPanel;

public class UserFrame extends JFrame{
	String loginname ;
	String username ;
	JPanel buttonPanel = new JPanel();
	
	private JButton selectFlightBtn = new JButton("查询航班");;
    private JButton selectTicketBtn = new JButton("查询订单");
    private JButton changeBtn = new JButton("改签机票");
    private JButton changeInfoBtn = new JButton("修改个人信息");
    
    private JPanel p = null;
    
    private List<JPanel> list = new ArrayList<JPanel>();
    
	private void setComponent () {
		this.setLayout(null);
		buttonPanel.setSize (800, 50);
		buttonPanel.setLocation(0, 0);
	}
	
	private void changePanel (JPanel panel) {
		for (JPanel p : list) {
			UserFrame.this.getContentPane().remove(p);
		}
		list.clear();
		UserFrame.this.add(panel);
		list.add(panel);
		//刷新窗口
		UserFrame.this.setVisible(true);
	}
	
	private void addComponent () {
		p = new FlightJPanel (loginname);
		// 添加到list统一管理
		list.add(p);
		buttonPanel.add (selectFlightBtn);
		buttonPanel.add (selectTicketBtn);
		buttonPanel.add (changeBtn);
		buttonPanel.add (changeInfoBtn);
		add (buttonPanel);
		add (p);
		
	}
	
	private void addListenter () {
		// 查询航班
		selectFlightBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				FlightJPanel panel = new FlightJPanel(UserFrame.this.loginname);
				changePanel(panel);
			}
			
		});
		// 查询订单
		selectTicketBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				OrderTicketManage orderTiecketManage = new OrderTicketManageImpl ();
				try {
					List<OrderTicket> list = orderTiecketManage.listOrderTicket(loginname);
					if (list == null || list.size() == 0) {
						JOptionPane.showMessageDialog(null, "没有订单");
						return;
					} else {
						CancerTicketJPanel panel = new CancerTicketJPanel (UserFrame.this.loginname);
						changePanel(panel);
					}
				} catch (OrderTicketException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				
				
			}
			
		});
		// 改签机票
		changeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				OrderTicketManage orderTiecketManage = new OrderTicketManageImpl ();
				try {
					List<OrderTicket> list = orderTiecketManage.listOrderTicket(loginname);
					if (list == null || list.size() == 0) {
						JOptionPane.showMessageDialog(null, "没有订单");
						return;
					} else {
						ChangeTicketJPanel panel = new ChangeTicketJPanel (UserFrame.this.loginname);
						changePanel(panel);
					}
				} catch (OrderTicketException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				
			}
			
		});
		// 修改个人信息
		changeInfoBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserFrame.this.setVisible(false);
				ChangeUserinfo u = new ChangeUserinfo (UserFrame.this.loginname);
			}
			
		});
	}

	private void init () {
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
	}
	
	private void getUserinfo () {
		UserManage userManage = new UserManageImpl();
		try {
			this.username = userManage.getUsername(loginname);
		} catch (UserException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public UserFrame (String loginname) {
		super("航空订票系统");
		this.loginname = loginname;
		getUserinfo ();
		init ();
		setComponent ();
		addComponent ();
		addListenter ();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		UserFrame userFrame = new UserFrame ("user1");
	}
}
