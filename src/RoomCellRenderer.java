import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


public class RoomCellRenderer extends JLabel implements ListCellRenderer {
	
	
	
	private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);
	public RoomCellRenderer() {
		setOpaque(true);
		setIconTextGap(12);
	}
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		Room room = (Room) value;
		setText(room.getAddress());
		
		ImageIcon icon = ChannelJPanel.cacheLogo.get(index);

		setIcon(icon);
		
		if (isSelected) {
			setBackground(HIGHLIGHT_COLOR);
			setForeground(Color.white);
		}
		else {
			setBackground(Color.white);
			setForeground(Color.black);
		}
		
		return this;
	}
	
}
