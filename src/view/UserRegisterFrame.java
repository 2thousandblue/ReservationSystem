package view;

import javax.swing.*;
import java.awt.*;

/**
 * @author: 我的袜子都是洞
 * @description:
 * @path: ReservationSystem-view-UserRegisterFrame
 * @date: 2019-01-20 00:13
 */
public class UserRegisterFrame extends JFrame {
    JPanel panel = new JPanel();
    JLabel loginnameLabel = new JLabel("请输入用户名:");
    JLabel password1Label = new JLabel("请输入密码:");
    JLabel password2Label = new JLabel("请再次输入密码:");
    JLabel usernameLabel = new JLabel("请输入姓名:");
    JLabel identityLabel = new JLabel("请输入身份证号:");
    JLabel sexLabel = new JLabel("请选择性别:");
    JLabel phoneLabel = new JLabel("请输入手机号码:");
    JLabel emailLabel = new JLabel("请输入电子邮箱:");
    JLabel addressLabel = new JLabel("请输入地址:");
    JTextField loginnameField = new JTextField(16);
    JPasswordField password1Field = new JPasswordField(16);
    JPasswordField password2Field = new JPasswordField(16);
    JTextField usernameField = new JTextField(16);
    JTextField identityField = new JTextField(16);
    ButtonGroup sexButton = new ButtonGroup();
    JRadioButton maleRadio = new JRadioButton("男");
    JRadioButton femaleRadio = new JRadioButton("女");
    JTextField phoneField = new JTextField(16);
    JTextField emailField = new JTextField(16);
    JTextField addressField = new JTextField(16);

    private void init () {
        setSize(800, 600);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void addComponent () {
        setLayout(new FlowLayout());
        panel.setLocation(350,0);
        panel.setLayout(new GridLayout(20,1));
        panel.setSize(100,600);
        panel.add(loginnameLabel);
        panel.add(loginnameField);
        panel.add(password1Label);
        panel.add(password1Field);
        panel.add(password2Label);
        panel.add(password2Field);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(identityLabel);
        panel.add(identityField);
        panel.add(sexLabel);
        sexButton.add(maleRadio);
        sexButton.add(femaleRadio);
        panel.add(maleRadio);
        panel.add(femaleRadio);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(addressLabel);
        panel.add(addressField);
        add(panel);
    }

    private void addListener() {

    }

    public UserRegisterFrame(String title) {
        super(title);
        init();
        addComponent();
        addListener();
        setVisible(true);
    }

    public static void main(String[] args) {
        new UserRegisterFrame("航空订票系统");
    }
}
