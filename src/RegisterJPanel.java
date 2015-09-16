import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.SpinnerListModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;


public class RegisterJPanel extends JPanel {
	private JTextField usernameTextField;
	private JTextField passwordTextField;
	private JTextField retypePasswordTextField;
	private JTextField emailTextField;
	private JTextField fullNameTextField;
	private JTextField cityTextField;

	public RegisterJPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		add(panelTop, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Đăng ký tài khoản Room Channel");
		panelTop.add(lblNewLabel);
		
		JPanel panelCenter = new JPanel();
		add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Tên đăng nhập");
		lblNewLabel_1.setBounds(64, 11, 99, 14);
		panelCenter.add(lblNewLabel_1);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(265, 8, 123, 20);
		panelCenter.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Mật khẩu");
		lblNewLabel_2.setBounds(64, 36, 99, 14);
		panelCenter.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nhập lại mật khẩu");
		lblNewLabel_3.setBounds(64, 61, 99, 14);
		panelCenter.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setBounds(64, 86, 99, 14);
		panelCenter.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Tên đầy đủ");
		lblNewLabel_5.setBounds(64, 111, 99, 14);
		panelCenter.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Tỉnh / Thành phố");
		lblNewLabel_6.setBounds(64, 136, 99, 14);
		panelCenter.add(lblNewLabel_6);
		
		passwordTextField = new JTextField();
		passwordTextField.setBounds(265, 33, 123, 20);
		panelCenter.add(passwordTextField);
		passwordTextField.setColumns(10);
		
		retypePasswordTextField = new JTextField();
		retypePasswordTextField.setBounds(265, 58, 123, 20);
		panelCenter.add(retypePasswordTextField);
		retypePasswordTextField.setColumns(10);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(265, 83, 123, 20);
		panelCenter.add(emailTextField);
		emailTextField.setColumns(10);
		
		fullNameTextField = new JTextField();
		fullNameTextField.setBounds(265, 108, 123, 20);
		panelCenter.add(fullNameTextField);
		fullNameTextField.setColumns(10);
		
		cityTextField = new JTextField();
		cityTextField.setBounds(265, 133, 123, 20);
		panelCenter.add(cityTextField);
		cityTextField.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Loại tài khoản");
		lblNewLabel_7.setBounds(64, 161, 99, 14);
		panelCenter.add(lblNewLabel_7);
		
		final JLabel errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setBounds(95, 183, 252, 14);
		panelCenter.add(errorLabel);
		
		String[] typeUserItems = {"Chủ trọ", "Người thuê trọ"};
		SpinnerListModel typeModel = new SpinnerListModel(typeUserItems);
		final JSpinner typeSpinner = new JSpinner(typeModel);
		((DefaultEditor) typeSpinner.getEditor()).getTextField().setEditable(false);
		typeSpinner.setBounds(265, 159, 123, 18);
		panelCenter.add(typeSpinner);
		
		JButton submitButton = new JButton("Đăng ký");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameTextField.getText().trim();
				String password = passwordTextField.getText().trim();
				String retypePassword = retypePasswordTextField.getText().trim();
				String email = emailTextField.getText().trim();
				String city = cityTextField.getText().trim();
				String name = fullNameTextField.getText().trim();
				String type = ((String)typeSpinner.getValue()).trim();
				if (username.equals("") || password.equals("") || retypePassword.equals("") || email.equals("") || city.equals("") || name.equals("") || type.equals("")) {
					errorLabel.setText("Bạn hãy điền đầy đủ thông tin!");
					return;
				}
				if (!password.equals(retypePassword)) {
					errorLabel.setText("Mật khẩu và mật khẩu xác nhận không trùng khớp!");
					return;
				}
				
				MySqlFunctions mySqlFunctions = new MySqlFunctions();
				User userRegister = mySqlFunctions.register(username, password, email, name, city, type);
				if (userRegister != null) {
					
					MainJFrame.contentPane.removeAll();
					MainJFrame.contentPane.validate();
					MainJFrame.contentPane.repaint();
					
					MainJFrame.contentPane.add(MainJFrame.loginPanel);
					MainJFrame.contentPane.validate();
					MainJFrame.contentPane.repaint();
					
					JOptionPane.showMessageDialog(null, "Bạn đã tạo tài khoản thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "Tài khoản đã tồn tại", "Lỗi đăng ký", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		submitButton.setBounds(95, 208, 91, 23);
		panelCenter.add(submitButton);
		
		JButton backButton = new JButton("Trở về");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainJFrame.contentPane.removeAll();
				MainJFrame.contentPane.validate();
				MainJFrame.contentPane.repaint();
				
				MainJFrame.contentPane.add(MainJFrame.loginPanel);
				MainJFrame.contentPane.validate();
				MainJFrame.contentPane.repaint();
			
			}
		});
		backButton.setBounds(242, 208, 91, 23);
		panelCenter.add(backButton);
		
		

	}
}
