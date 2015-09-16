import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LoginJPanel extends JPanel {
	private JTextField usernameTextField;
	private JTextField passwordTextField;
	private User userLogin;

	public LoginJPanel() {
		setLayout(null);
		JLabel lblNewLabel = new JLabel("Tên đăng nhập:");
		lblNewLabel.setBounds(69, 77, 113, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mật khẩu:");
		lblNewLabel_1.setBounds(69, 139, 113, 14);
		add(lblNewLabel_1);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(233, 74, 143, 20);
		add(usernameTextField);
		usernameTextField.setColumns(10);
		
		passwordTextField = new JTextField();
		passwordTextField.setBounds(233, 136, 143, 20);
		add(passwordTextField);
		passwordTextField.setColumns(10);
		
		JButton loginButton = new JButton("Đăng nhập");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameTextField.getText();
				String password = passwordTextField.getText();
				MySqlFunctions mySqlFunctions = new MySqlFunctions();
				userLogin = mySqlFunctions.login(username, password);
				if (userLogin == null) {
					JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không đúng!", "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
					usernameTextField.setText("");
					passwordTextField.setText("");
				}
				else {
					MainJFrame.contentPane.removeAll();
					MainJFrame.contentPane.validate();
					MainJFrame.contentPane.repaint();
					
					MainJFrame.contentPane.add(MainJFrame.channelPanel);
					MainJFrame.contentPane.validate();
					MainJFrame.contentPane.repaint();
					
					JOptionPane.showMessageDialog(null, "Chúc mừng! Bạn đã đăng nhập thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					
				}
			}
		});
		loginButton.setBounds(81, 220, 124, 23);
		add(loginButton);
		
		JButton registerButton = new JButton("Tạo tài khoản");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainJFrame.contentPane.removeAll();
				MainJFrame.contentPane.validate();
				MainJFrame.contentPane.repaint();
				
				MainJFrame.contentPane.add(MainJFrame.registerPanel);
				MainJFrame.contentPane.validate();
				MainJFrame.contentPane.repaint();
			}
		});
		registerButton.setBounds(249, 220, 124, 23);
		add(registerButton);

	}
}
