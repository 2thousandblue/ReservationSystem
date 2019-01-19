package view;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView extends JFrame {
	JPanel jPanel = new JPanel();
	private JButton yellowButton = new JButton("Yellow");
	private JButton blueButton = new JButton("Blue");
	private JButton redButton = new JButton("Red");
	
	public void initFrame() {
		setSize(400, 300);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void addComponent() {
		jPanel.add(yellowButton);
		jPanel.add(blueButton);
		jPanel.add(redButton);
		add(jPanel);
	}
	
	
	public void addListener() {
		class ColorAction implements ActionListener {
			private Color backgroundColor;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				jPanel.setBackground(backgroundColor);
			}
			public ColorAction (Color c) {
				backgroundColor = c;
			}
			
		}
		
		yellowButton.addActionListener(new ColorAction(Color.YELLOW));
		blueButton.addActionListener(new ColorAction(Color.BLUE));
		redButton.addActionListener(new ColorAction(Color.RED));	
	}
	
	public MainView(String title) {
		super(title);
		initFrame();
		addComponent();
		addListener();
		setVisible(true);
	}

	public static void main(String[] args) {
		MainView mf = new MainView("窗口");
	}
}




