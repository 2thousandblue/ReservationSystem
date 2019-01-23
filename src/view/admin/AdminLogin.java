package view.admin;

import javax.swing.*;
import manage.AdminManage;
import manage.impl.AdminManageImpl;
import exception.AdminException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

/**
 * @author: 我的袜子都是洞
 * @description: 管理员登陆界面
 * @path: ReservationSystem-view.admin-AdminLogin
 * @date: 2019-01-20 18:18
 */
public class AdminLogin extends JPanel {
    JLabel imgLabel = new JLabel(new ImageIcon("src/view/img.jpg"));
    JPanel panel = new JPanel();
    JLabel loginText = new JLabel("管理员用户名:");
    JLabel passText = new JLabel("管理员密码:");
    JTextField loginField = new JTextField(20);
    JPasswordField passField = new JPasswordField(20);
    JButton loginBtn = new JButton("登陆");
    Dimension preferredSize = new Dimension(120,100);
    Font font = new Font(null,Font.BOLD,20);

    /**
     * 添加组件
     */
    private void addComponent() {
        setLayout(new BorderLayout());
        add(imgLabel, BorderLayout.NORTH);
        setSize(800, 100);

        // 设置用户名、密码输入框样式
        loginField.setPreferredSize(new Dimension(80,40));
        passField.setPreferredSize(new Dimension(80,40));
        loginBtn.setPreferredSize(preferredSize);
        loginBtn.setFont(font);

        panel.add(loginText);
        panel.add(loginField);
        panel.add(passText);
        panel.add(passField);
        panel.add(loginBtn);
        panel.setSize(800,100);
        add(panel, BorderLayout.CENTER);
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
				if (loginname.length() == 0) {
					JOptionPane.showMessageDialog(null,"管理员用户名不能为空");
					// 获取用户名焦点
					loginField.grabFocus();
					loginField.setText("");
					passField.setText("");
					return;
				}
				if (password.length() == 0) {
					JOptionPane.showMessageDialog(null,"密码不能为空");
					// 获取用户名焦点
					loginField.grabFocus();
					loginField.setText("");
					passField.setText("");
					return;
				}
				// 以字母开头，长度在6~18之间，只能包含字符、数字和下划线
				String pattern = "^[a-zA-Z]\\w{3,17}$";
				boolean isMatch = Pattern.matches(pattern, loginname);
				if (!isMatch) {
					JOptionPane.showMessageDialog(null,"用户名：以字母开头，长度在4~18之间，只能包含字符、数字和下划线");
					// 获取用户名焦点
					loginField.grabFocus();
					loginField.setText("");
					passField.setText("");
					return;
				}
				// 导入管理层，准备对帐号和密码进行验证
				AdminManage adminManage = new AdminManageImpl();
				try {
					boolean isSuccess = adminManage.loginAdmin(loginname, password);
					if (!isSuccess) {
						JOptionPane.showMessageDialog(null,"用户密码错误");
						// 获取用户名焦点
						loginField.grabFocus();
						loginField.setText("");
						passField.setText("");
						return;
					} else {
						new AdminFrame();
					}
				} catch (AdminException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage());
					// 获取用户名焦点
					loginField.grabFocus();
					loginField.setText("");
					passField.setText("");
					return;
				}
            }
        });
    }

    public AdminLogin() {
        addComponent();
        addListener();
    }

}
