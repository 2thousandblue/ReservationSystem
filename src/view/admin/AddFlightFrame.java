package view.admin;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import manage.FlightManage;
import manage.impl.FlightManageImpl;
import entity.Flight;
import exception.FlightException;

public class AddFlightFrame  extends JFrame{
    JPanel takeoff_time = new JPanel();
    JPanel flying_time = new JPanel();
    JPanel start_place = new JPanel();
    JPanel end_place = new JPanel();
    JPanel ticket = new JPanel();
    JPanel price = new JPanel();
    
    // Label文字
    JLabel takeoff_timeLabel = new JLabel("起飞时间:");
    JLabel flying_timeLabel = new JLabel("飞行时间：");
    JLabel start_placeLabel = new JLabel("出发地：");
    JLabel end_placeLabel = new JLabel("目的地:");
    JLabel ticketLabel = new JLabel("票数:"); 
    JLabel priceLabel = new JLabel("价格:");

    // 输入框
    JTextField takeoff_timeField = new JTextField(20);
    JTextField flying_timeField = new JTextField(20);
    JTextField start_placeField = new JTextField(20);
    JTextField end_placeField = new JTextField(20);
    JTextField ticketField = new JTextField(20);
    JTextField priceField = new JTextField(20);

    // 按钮
    JPanel btnPanel = new JPanel();
    JButton changeButton = new JButton("添加航班");
    JButton backButton = new JButton("返回");
	private void init () {
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	private void setComponent () {
		setLayout(new GridLayout(10, 1));
		
	}
	
	private void addComponent () {
		takeoff_time.add(takeoff_timeLabel);
		takeoff_time.add(takeoff_timeField);
		flying_time.add(flying_timeLabel);
		flying_time.add(flying_timeField);
		start_place.add(start_placeLabel);
		start_place.add(start_placeField);
		end_place.add(end_placeLabel);
		end_place.add(end_placeField);
		ticket.add(ticketLabel);
		ticket.add(ticketField);
		price.add(priceLabel);
		price.add(priceField);
		btnPanel.add(changeButton);
		btnPanel.add(backButton);
		add(takeoff_time);
		add(flying_time);
		add(start_place);
		add(end_place);
		add(ticket);
		add(price);
		add(btnPanel);
	}
	
	private void addListenter () {
		changeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String takeoff_time = takeoff_timeField.getText();
				if (takeoff_time.length()==0) {
					JOptionPane.showMessageDialog(null,"起飞时间不能为空");
					return;
				}
				String flying_time = flying_timeField.getText();
				if (flying_time.length()==0) {
					JOptionPane.showMessageDialog(null,"飞行时间不能为空");
					return;
				}
				String start_place = start_placeField.getText();
				if (start_place.length()==0) {
					JOptionPane.showMessageDialog(null,"出发地不能为空");
					return;
				}
				String end_place = end_placeField.getText();
				if (end_place.length()==0) {
					JOptionPane.showMessageDialog(null,"目的地不能为空");
					return;
				}
				String ticket = ticketField.getText();
				int ticket_int = Integer.valueOf(ticket);
				if(ticket_int<=0) {
					JOptionPane.showMessageDialog(null,"票数输入错误");
					return;
				}
				String price = priceField.getText();
				Double price_dou = Double.valueOf(price);
				if (price_dou <= 0) {
					JOptionPane.showMessageDialog(null,"价格输入错误");
					return;
				}
				Flight newflight = new Flight();
				newflight.setTakeoff_time(takeoff_time);
				newflight.setFlying_time(flying_time);
				newflight.setStart_place(start_place);
				newflight.setEnd_place(end_place);
				newflight.setTicket(ticket_int);
				newflight.setPrice(price_dou);
				FlightManage flightManage = new FlightManageImpl();
				try {
					boolean f = flightManage.saveFlight(newflight);
					if (f) {
						JOptionPane.showMessageDialog(null, "添加成功");
						return ;
					} else {
						JOptionPane.showMessageDialog(null, "添加失败");
						return ;
					}
				} catch (FlightException e) {
					JOptionPane.showMessageDialog(null,e.getMessage());
				}
			};
    		
    	});
		// 返回按钮
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddFlightFrame.this.setVisible(false);
				AdminFrame adminFrame = new AdminFrame ();
			}
			
		});
	}
	
	public AddFlightFrame () {
		super("添加航班信息");
		init ();
		setComponent ();
		addComponent ();
		addListenter ();
		setVisible(true);
	}
	public static void main(String[] args) {
		new AddFlightFrame ();
	}
}
