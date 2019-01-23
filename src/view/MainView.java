package view;

import view.user.UserLogin;
import view.user.UserRegister;
import javax.swing.*;
import view.admin.AdminLogin;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author: 我的袜子都是洞
 * @description: 程序界面入口
 * @path: ReservationSystem-view-MainView
 * @date: 2019-01-20 17:25
 */
public class MainView  extends JFrame{
    JLabel imgLabel = new JLabel(new ImageIcon("src/view/img.jpg"));
    JPanel butonPanel = new JPanel();
    JButton userLoginBtn = new JButton("用户登陆");
    JButton userRegisterBtn = new JButton("注册用户");
    JButton adminLoginBtn = new JButton("管理员登陆");
    Dimension preferredSize = new Dimension(210,60);
    Font font = new Font(null,Font.BOLD,18);
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
        // 背景图片靠上
        add(imgLabel, BorderLayout.NORTH);
        // 设置按钮大小
        userLoginBtn.setPreferredSize(preferredSize);
        userRegisterBtn.setPreferredSize(preferredSize);
        adminLoginBtn.setPreferredSize(preferredSize);
        // 设置按钮文字大小
        userLoginBtn.setFont(font);
        userRegisterBtn.setFont(font);
        adminLoginBtn.setFont(font);
        // 添加组件
        butonPanel.add(userLoginBtn);
        butonPanel.add(userRegisterBtn);
        butonPanel.add(adminLoginBtn);
        add(butonPanel, BorderLayout.SOUTH);
    }

    /**
     * 监听器
     */
    private void addListener() {
        // 用户登陆事件
        userLoginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 清空当前所有组件
                MainView.this.remove(imgLabel);
                MainView.this.remove(butonPanel);

                UserLogin userLogin = new UserLogin();
                MainView.this.add(userLogin, BorderLayout.SOUTH);
                MainView.this.setVisible(true);
            }
        });

        // 注册用户事件
        userRegisterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 清空当前所有组件
                MainView.this.remove(imgLabel);
                MainView.this.remove(butonPanel);

                UserRegister userRegister = new UserRegister();
                MainView.this.add(userRegister);
                MainView.this.setVisible(true);
            }
        });

        // 管理员登陆事件
        adminLoginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 清空当前所有组件
                MainView.this.remove(imgLabel);
                MainView.this.remove(butonPanel);

                AdminLogin adminLogin = new AdminLogin();
                MainView.this.add(adminLogin, BorderLayout.SOUTH);
                MainView.this.setVisible(true);
            }
        });
    }

    public MainView(String title) {
        super(title);
        initFrame();
        addComponent();
        addListener();
        setVisible(true);
    }

    public static void main(String[] args) {
        MainView mf = new MainView("航空订票系统");
    }
}
