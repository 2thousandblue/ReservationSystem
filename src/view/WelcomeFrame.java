package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Description: 航空订票系统图形入口
 * @author: 我的袜子都是洞
 * @date: 2019-01-19 23:08
 */
public class WelcomeFrame extends JFrame {
	JLabel imgLabel = new JLabel(new ImageIcon("src/view/img.jpg"));
	JPanel jPanel = new JPanel();
	JLabel loginText = new JLabel("用户名");
	JLabel passText = new JLabel("密码");
	JTextField loginField = new JTextField(20);
	JPasswordField passField = new JPasswordField(20);
	JButton loginBtn = new JButton("登陆");
	JButton registerBtn = new JButton("注册");
	/**
	 * 图形框架初始化
	 */
	private void initFrame() {
		setSize(800, 600);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * 添加组件
	 */
	private void addComponent() {
		add(imgLabel, BorderLayout.NORTH);
		jPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,20));
		jPanel.setSize(800, 100);
		jPanel.add(loginText);
		jPanel.add(loginField);
		jPanel.add(passText);
		jPanel.add(passField);
		jPanel.add(loginBtn);
		jPanel.add(registerBtn);
		add(jPanel, BorderLayout.SOUTH);
	}

	/**
	 * 监听器
	 */
	private void addListener() {
		// 登陆按钮的事件监听
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String loginname = loginField.getText();
				String password = passField.getText();
				System.out.println("用户名："+loginname+",密码："+password);
				loginField.setText("");
				passField.setText("");
			}
		});
		// 注册按钮的事件监听
		registerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WelcomeFrame.this.remove(imgLabel);
				WelcomeFrame.this.remove(jPanel);
				WelcomeFrame.this.add(imgLabel);
				WelcomeFrame.this.setVisible(true);
			}
		});
	}
	
	public WelcomeFrame(String title) {
		super(title);
		initFrame();
		addComponent();
		addListener();
		setVisible(true);
	}

	public static void main(String[] args) {
		WelcomeFrame mf = new WelcomeFrame("航空订票系统");
	}
}




