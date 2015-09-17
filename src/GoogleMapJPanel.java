import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GoogleMapJPanel extends JPanel {

	public GoogleMapJPanel() {
		setSize(800, 600);
		setLayout(null);
		
		JButton backButton = new JButton("");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainJFrame.contentPane.removeAll();
				MainJFrame.contentPane.validate();
				MainJFrame.contentPane.repaint();
				
				MainJFrame.contentPane.add(MainJFrame.channelPanel);
				MainJFrame.contentPane.validate();
				MainJFrame.contentPane.repaint();
			}
		});
		
		backButton.setBounds(10, 10, 30, 30);
		ImageIcon backIcon = new ImageIcon("icon/back_icon.png");
		Image img = backIcon.getImage();
		Image newimg = img.getScaledInstance(backButton.getWidth(), backButton.getHeight(), Image.SCALE_SMOOTH);
		backIcon = new ImageIcon(newimg);
		backButton.setIcon(backIcon);
		
		add(backButton);
	}

}
