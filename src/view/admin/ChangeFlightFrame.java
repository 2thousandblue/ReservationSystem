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

public class ChangeFlightFrame  extends JFrame{
	private Flight flight;
	JPanel flightID = new JPanel();
    JPanel takeoff_time = new JPanel();
    JPanel flying_time = new JPanel();
    JPanel start_place = new JPanel();
    JPanel end_place = new JPanel();
    JPanel ticket = new JPanel();
    JPanel price = new JPanel();
    
    // Label文字
    JLabel flightIdLabel = new JLabel("航班号:");
    JLabel takeoff_timeLabel = new JLabel("起飞时间:");
    JLabel flying_timeLabel = new JLabel("飞行时间：");
    JLabel start_placeLabel = new JLabel("出发地：");
    JLabel end_placeLabel = new JLabel("目的地:");
    JLabel ticketLabel = new JLabel("票数:"); 
    JLabel priceLabel = new JLabel("价格:");


    // 输入框
    JLabel flightIdField = new JLabel("");
    JTextField takeoff_timeField = new JTextField(20);
    JTextField flying_timeField = new JTextField(20);
    JTextField start_placeField = new JTextField(20);
    JTextField end_placeField = new JTextField(20);
    JTextField ticketField = new JTextField(20);
    JTextField priceField = new JTextField(20);

    // 按钮
    JPanel btnPanel = new JPanel();
    JButton changeButton = new JButton("修改个人信息");
    JButton backButton = new JButton("返回");
	private void init () {
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		takeoff_timeField.setText(this.flight.getTakeoff_time());
		start_placeField.setText(this.flight.getStart_place());
		flying_timeField.setText(this.flight.getFlying_time());
		end_placeField.setText(this.flight.getEnd_place());
		ticketField.setText(String.valueOf(this.flight.getTicket()));
		priceField.setText(String.valueOf(this.flight.getPrice()));
	}
	
	private void setComponent () {
		setLayout(new GridLayout(10, 1));
		flightIdField.setText(String.valueOf(this.flight.getId()));
		
	}
	
	private void addComponent () {
		flightID.add(flightIdLabel);
		flightID.add(flightIdField);
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
		add(flightID);
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
				newflight.setId(ChangeFlightFrame.this.flight.getId());
				newflight.setTakeoff_time(takeoff_time);
				newflight.setFlying_time(flying_time);
				newflight.setStart_place(start_place);
				newflight.setEnd_place(end_place);
				newflight.setTicket(ticket_int);
				newflight.setPrice(price_dou);
				FlightManage flightManage = new FlightManageImpl();
				try {
					boolean f = flightManage.updateFlight(newflight);
					if (f) {
						JOptionPane.showMessageDialog(null, "修改成功");
						return ;
					} else {
						JOptionPane.showMessageDialog(null, "修改失败");
						return ;
					}
				} catch (FlightException e) {
					JOptionPane.showMessageDialog(null,e.getMessage());
				}
			};
    		
    	});
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ChangeFlightFrame.this.setVisible(false);
			}
			
		});
	}
	
	public ChangeFlightFrame (int id) {
		super("修改航班信息");
		FlightManage flightMange = new FlightManageImpl ();
		try {
			this.flight = flightMange.getFlight(id);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		init ();
		setComponent ();
		addComponent ();
		addListenter ();
		setVisible(true);
	}
	public static void main(String[] args) {
		new ChangeFlightFrame (1);
	}
}
