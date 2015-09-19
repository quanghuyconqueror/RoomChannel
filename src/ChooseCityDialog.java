import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;


public class ChooseCityDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String[] cityItems = {"Đà Nẵng", "Quảng Nam", "Huế", "Hà Nội"};
	public ChooseCityDialog() {
		setBounds(100, 100, 450, 300);
		setTitle("Chọn tỉnh / thành phố");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(121, 121, 216, 22);
		for (int i = 0; i < cityItems.length; i++) {
			comboBox.addItem(cityItems[i]);
		}
		contentPanel.add(comboBox);
		
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
        		dispose();
        	}
        });
		contentPanel.add(backLabel);
		
		JLabel searchLabel = new JLabel();
		
		searchLabel.setBounds(400, 230, 32, 32);
		searchLabel.setIcon(new ImageIcon("icon/search_icon.png"));
		searchLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ChannelJPanel.cityDialogChose = (String) comboBox.getSelectedItem();
				dispose();
			}
		});
			
		contentPanel.add(searchLabel);
		
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setBounds(0, 0, 450, 273);
		backgroundLabel.setIcon(new ImageIcon("icon/background_login.png"));
		contentPanel.add(backgroundLabel);
		
	}
}
