package view.user;

import javax.swing.*;
import java.awt.*;

/**
 * @author: 我的袜子都是洞
 * @description: 用户注册界面
 * @path: ReservationSystem-view-UserRegister
 * @date: 2019-01-20 00:13
 */
public class UserRegister extends JPanel {
    JPanel panel = new JPanel();
    Box box = Box.createVerticalBox();
    // Label文字
    JLabel loginnameLabel = new JLabel("请输入用户名:");
    JLabel password1Label = new JLabel("请输入密码:");
    JLabel password2Label = new JLabel("请再次输入密码:");
    JLabel usernameLabel = new JLabel("请输入姓名:");
    JLabel identityLabel = new JLabel("请输入身份证号:");
    JLabel sexLabel = new JLabel("请选择性别:");
    JLabel phoneLabel = new JLabel("请输入手机号码:");
    JLabel emailLabel = new JLabel("请输入电子邮箱:");
    JLabel addressLabel = new JLabel("请输入地址:");

    // 输入框
    JTextField loginnameField = new JTextField(20);
    JPasswordField password1Field = new JPasswordField(20);
    JPasswordField password2Field = new JPasswordField(20);
    JTextField usernameField = new JTextField(16);
    JTextField identityField = new JTextField(16);
    ButtonGroup sexButton = new ButtonGroup();
    JRadioButton maleRadio = new JRadioButton("男");
    JRadioButton femaleRadio = new JRadioButton("女");
    JTextField phoneField = new JTextField(16);
    JTextField emailField = new JTextField(16);
    JTextArea addressField = new JTextArea();

    // 按钮
    JButton registerButton = new JButton("立即注册");

    private void setComponent () {
        BoxLayout layout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(layout);
    }

    private void addComponent () {
        panel.add(box, BorderLayout.NORTH);
        panel.add(loginnameLabel);
        panel.add(loginnameField);
        panel.add(password1Label);
        panel.add(password1Field);
        add(panel);
    }

    private void addListener() {

    }

    public UserRegister() {
        setComponent();
        addComponent();
        addListener();
        setVisible(true);
    }
}
