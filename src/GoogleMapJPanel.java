import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GoogleMapJPanel extends JPanel {

	public GoogleMapJPanel() {
		setSize(800, 600);
		setLayout(null);
		
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
	}

}
