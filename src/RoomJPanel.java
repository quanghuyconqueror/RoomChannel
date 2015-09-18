import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoomJPanel extends JPanel implements ActionListener {
	private static final String urlImages = "http://enddev.site50.net/RoomChannelPHPServer/RoomImages/";
	private static final Border border = BorderFactory.createMatteBorder(4, 16,
			4, 16, Color.lightGray);
	private ArrayList<String> listImage = new ArrayList<String>();
	private ArrayList<ImageIcon> cache = new ArrayList<ImageIcon>();
	private JLabel imageLabel = new JLabel();
	private JButton prevButton = new JButton();
	private JButton nextButton = new JButton();
	private JComboBox favorites;
	private Room room;
	
	private JLabel backLabel;
	private JLabel prevLabel;
	private JLabel nextLabel;

	private void createPrevLabel() {
		prevLabel = new JLabel();
		prevLabel.setBounds(245, 474, 46, 14);
		prevLabel.setIcon(new ImageIcon("icon/prev_black.png"));
		prevLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				prevLabel.setIcon(new ImageIcon("icon/prev_blue.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				prevLabel.setIcon(new ImageIcon("icon/prev_black.png"));
			}
		});

	}
	
	private void createNextLabel() {
		
		nextLabel = new JLabel();
		nextLabel.setBounds(394, 474, 46, 14);
		nextLabel.setIcon(new ImageIcon("icon/next_black.png"));
		nextLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				nextLabel.setIcon(new ImageIcon("icon/next_blue.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				nextLabel.setIcon(new ImageIcon("icon/next_black.png"));
			}
		});
	}

	public RoomJPanel(Room room) {

		setSize(800, 600);
		setLayout(null);
		this.room = room;

		listImage.add(urlImages + "1.jpg");
		listImage.add(urlImages + "2.jpg");
		listImage.add(urlImages + "3.jpg");
		listImage.add(urlImages + "4.jpg");
		listImage.add(urlImages + "5.jpg");
		listImage.add(urlImages + "6.jpg");

		for (int i = 0; i < listImage.size(); i++)
			cache.add(i, null);

		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(0, 0, 800, 50);

		add(titlePanel);
		titlePanel.setLayout(null);

		createBackLabel();

		titlePanel.add(backLabel);
		imageLabel.setBounds(70, 52, 660, 378);

		imageLabel.setIcon(getImage(0));
		imageLabel.setHorizontalAlignment(JLabel.CENTER);
		imageLabel.setBorder(border);
		this.add(imageLabel);
		ImageIcon prevIcon = new ImageIcon("icon/prev_icon.png");
		Image imgPrev = prevIcon.getImage();
		prevIcon = new ImageIcon(imgPrev);
		ImageIcon nextIcon = new ImageIcon("icon/next_icon.png");
		Image imgNext = nextIcon.getImage();
		nextIcon = new ImageIcon(imgNext);
		prevButton.setBounds(269, 527, 40, 40);
		add(prevButton);

		prevButton.setText("");
		Image newimgPrev = imgPrev.getScaledInstance(prevButton.getWidth(),
				prevButton.getHeight(), Image.SCALE_SMOOTH);
		prevButton.setIcon(prevIcon);
		prevButton.setActionCommand("prev");

		prevButton.addActionListener(this);

		favorites = new JComboBox(
				listImage.toArray(new String[listImage.size()]));
		favorites.setBounds(335, 527, 53, 40);
		add(favorites);
		favorites.setActionCommand("favs");
		favorites.addActionListener(this);
		nextButton.setBounds(418, 527, 40, 40);
		add(nextButton);

		nextButton.setText("");
		final Image newimgNext = imgNext.getScaledInstance(
				nextButton.getWidth(), nextButton.getHeight(),
				Image.SCALE_SMOOTH);
		nextButton.setIcon(nextIcon);
		nextButton.setActionCommand("next");
		nextButton.addActionListener(this);
		
		
		createPrevLabel();
		add(prevLabel);
		
		createNextLabel();
		add(nextLabel);

		prevButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				prevButton.setIcon(new ImageIcon(newimgNext));
			}
		});

	}

	public void actionPerformed(ActionEvent ae) {
		String cmd = ae.getActionCommand();
		if ("favs".equals(cmd)) {
			int index = favorites.getSelectedIndex();
			ImageIcon image = getImage(index);
			imageLabel.setIcon(image);
			if (image != null)
				imageLabel.setText("");
			else
				imageLabel.setText("Image not available.");
		}
		if ("prev".equals(cmd)) {
			int index = favorites.getSelectedIndex() - 1;
			if (index < 0)
				index = listImage.size() - 1;
			favorites.setSelectedIndex(index);
		}
		if ("next".equals(cmd)) {
			int index = favorites.getSelectedIndex() + 1;
			if (index > listImage.size() - 1)
				index = 0;
			favorites.setSelectedIndex(index);
		}
	}

	public JButton getDefault() {
		return nextButton;
	}

	// Return the (possibly cached) image having the given index.
	private ImageIcon getImage(int index) {
		ImageIcon image = cache.get(index);
		if (image != null)
			return image;
		String name = "images/" + listImage.get(index);
		try {
			URL url = new URL(listImage.get(index));
			if (url != null) {
				image = new ImageIcon(url);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		cache.set(index, image);
		return image;
	}

	private void createBackLabel() {
		backLabel = new JLabel();
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
		backLabel.setBounds(10, 10, 32, 32);
		backLabel.setIcon(new ImageIcon("icon/back_nofill.png"));
	}
}
