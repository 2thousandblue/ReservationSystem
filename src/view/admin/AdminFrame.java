package view.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AdminFrame extends JFrame {

	JPanel buttonPanel = new JPanel();
	
	private JButton allFlightBtn = new JButton("查询所有航班");;
    private JButton canModfyTicketBtn = new JButton("修改航班");
    
    private JPanel p = null;
    
    private List<JPanel> list = new ArrayList<JPanel>();
    
	private void setComponent () {
		this.setLayout(null);
		buttonPanel.setSize (800, 50);
		buttonPanel.setLocation(0, 0);
	}
	
	private void changePanel (JPanel panel) {
		for (JPanel p : list) {
			AdminFrame.this.getContentPane().remove(p);
		}
		list.clear();
		AdminFrame.this.add(panel);
		list.add(panel);
		//刷新窗口
		AdminFrame.this.setVisible(true);
	}
	
	private void addComponent () {
		p = new AllFlightJPanel ();
		// 添加到list统一管理
		list.add(p);
		buttonPanel.add (allFlightBtn);
		buttonPanel.add (canModfyTicketBtn);
		add (buttonPanel);
		add (p);
		
	}
	
	private void addListenter () {
		// 查询所有未起飞航班
		allFlightBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AllFlightJPanel panel = new AllFlightJPanel();
				changePanel(panel);
			}
			
		});
		// 可修改航班（没有人订票未起飞的航班）
		canModfyTicketBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ModifyFlightJPanel panel = new ModifyFlightJPanel();
				changePanel(panel);
			}
			
		});
		
	}

	private void init () {
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
	}
	
	
	public AdminFrame () {
		super("航空订票系统");
		init ();
		setComponent ();
		addComponent ();
		addListenter ();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		AdminFrame adminFrame = new AdminFrame ();
	}
	
}
