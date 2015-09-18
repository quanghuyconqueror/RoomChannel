import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SpinnerListModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JSpinner;


public class MainJFrame extends JFrame {

	public static JPanel contentPane;
	public static JPanel loginPanel;
	public static JPanel channelPanel;
	public static JPanel registerPanel;
	public static JPanel googleMapPanel;
	public static User userLogin = null;
	
	private static MySqlFunctions mySqlFunctions;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		mySqlFunctions = new MySqlFunctions();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainJFrame frame = new MainJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainJFrame() {
		setTitle("Room Channel");
		loginPanel = new LoginJPanel();
		channelPanel = new ChannelJPanel();
		registerPanel = new RegisterJPanel();
		googleMapPanel = new GoogleMapJPanel();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		loginPanel = new LoginJPanel();
		contentPane.add(loginPanel);
		
		
		
	}
	
	
}
