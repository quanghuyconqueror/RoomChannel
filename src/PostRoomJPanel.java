import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class PostRoomJPanel extends JPanel {
	private JTextField addressTF;
	private JTextField descriptionTF;
	private JTextField costTF;
	private JTextField imageTF;

	/**
	 * Create the panel.
	 */
	public PostRoomJPanel() {
		setLayout(null);
		setSize(800, 600);
		
		addressTF = new JTextField();
		addressTF.setBounds(258, 24, 167, 20);
		add(addressTF);
		addressTF.setColumns(10);
		
		descriptionTF = new JTextField();
		descriptionTF.setBounds(258, 71, 167, 55);
		add(descriptionTF);
		descriptionTF.setColumns(10);
		
		JSpinner citySpinner = new JSpinner();
		citySpinner.setBounds(261, 137, 164, 18);
		add(citySpinner);
		
		costTF = new JTextField();
		costTF.setBounds(258, 234, 167, 20);
		add(costTF);
		costTF.setColumns(10);
		
		imageTF = new JTextField();
		imageTF.setBounds(258, 184, 167, 20);
		add(imageTF);
		imageTF.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u0110\u1ECBa ch\u1EC9");
		lblNewLabel.setBounds(76, 27, 86, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("M\u00F4 t\u1EA3");
		lblNewLabel_1.setBounds(76, 93, 86, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Th\u00E0nh ph\u1ED1");
		lblNewLabel_2.setBounds(76, 139, 86, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("H\u00ECnh \u1EA3nh");
		lblNewLabel_3.setBounds(76, 187, 86, 14);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Gi\u00E1");
		lblNewLabel_4.setBounds(76, 237, 86, 14);
		add(lblNewLabel_4);
		
		JButton submitButton = new JButton("Đăng");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Room roomPosted = null;
				String address= addressTF.getText();
				String description = descriptionTF.getText();
				String latitude = "Test";
				String longitude = "Test";
				String city = "Test";
				String images = "Test";
				String cost = costTF.getText();
				String userPostID = "1";
				
				MySqlFunctions mySqlFunctions = new MySqlFunctions();
				roomPosted = mySqlFunctions.postRoom(address, description, latitude, longitude, city, images, cost, userPostID);
				if (roomPosted == null) {
					JOptionPane.showMessageDialog(null, "Đăng phòng thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, roomPosted.toString(), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		submitButton.setBounds(118, 265, 91, 23);
		add(submitButton);

		final JLabel backLabel = new JLabel();
		backLabel.setBounds(10, 10, 32, 32);
		backLabel.setIcon(new ImageIcon("icon/back_nofill.png"));
		backLabel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		backLabel.setIcon(new ImageIcon("icon/back_fill.png"));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		backLabel.setIcon(new ImageIcon("icon/back_nofill.png"));
        	}
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		MainJFrame.contentPane.removeAll();
				MainJFrame.contentPane.validate();
				MainJFrame.contentPane.repaint();
				
				MainJFrame.contentPane.add(MainJFrame.channelPanel);
				MainJFrame.contentPane.validate();
				MainJFrame.contentPane.repaint();
        	}
        });
		add(backLabel);
		
		JLabel uploadLabel1 = new JLabel();
		uploadLabel1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openFileChooser();
			}
		});
		uploadLabel1.setBounds(487, 118, 45, 45);
		uploadLabel1.setIcon(new ImageIcon("icon/upload_icon.png"));
		add(uploadLabel1);
		
		JLabel uploadLabel2 = new JLabel();
		uploadLabel2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openFileChooser();
			}
		});
		uploadLabel2.setBounds(487, 206, 45, 45);
		uploadLabel2.setIcon(new ImageIcon("icon/upload_icon.png"));
		add(uploadLabel2);
		
		JLabel uploadLabel3 = new JLabel();
		uploadLabel3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openFileChooser();
			}
		});
		uploadLabel3.setBounds(487, 297, 45, 45);
		uploadLabel3.setIcon(new ImageIcon("icon/upload_icon.png"));
		add(uploadLabel3);

	}
	
	// cho phép chọn file image để upload lên server
	private void openFileChooser() {
		
		JFileChooser fc = new JFileChooser();
		
		// thiết đặt bộ lọc file
		FileFilter imageFilter = new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp");
		fc.setFileFilter(imageFilter);
		fc.setAcceptAllFileFilterUsed(false);
		
		// hiển thị dialog chọn file và trả về kết quả
        int res = fc.showOpenDialog(this);

        try {
            if (res == JFileChooser.APPROVE_OPTION) {
            	System.out.println(fc.getSelectedFile().getAbsolutePath());
            }
            else {
                JOptionPane.showMessageDialog(null,
                        "You must select one image to be the reference.", "Aborting...",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception iOException) {
        }
	}
}
