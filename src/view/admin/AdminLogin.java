package view.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    JButton loginBtn = new JButton("管理员登陆");
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
                char[] password = passField.getPassword();
                if (loginname.length() == 0 || password.length == 0) {
                    JOptionPane.showMessageDialog(null,"用户名、密码不能为空");
                }
                // 获取用户名焦点
                loginField.grabFocus();
                loginField.setText("");
                passField.setText("");
            }
        });
    }

    public AdminLogin() {
        addComponent();
        addListener();
    }

}
