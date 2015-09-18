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
	private static final int MAX = 20;
    private static final Font sans = new Font("SansSerif", Font.PLAIN, 16);
    private static final Border border =
        BorderFactory.createMatteBorder(4, 16, 4, 16, Color.lightGray);
    private ArrayList<String> list = new ArrayList<String>(MAX);
    private ArrayList<ImageIcon> cache = new ArrayList<ImageIcon>(MAX);
    private JLabel imageLabel = new JLabel();
    private JButton prevButton = new JButton();
    private JButton nextButton = new JButton();
    private JComboBox favorites;
    private Room room;
    private final JPanel titlePanel = new JPanel();
    private final JButton backButton = new JButton();

    public RoomJPanel(Room room) {
    	setSize(800, 600);
        this.room = room;
        list.add(urlImages + "1.jpg");
        list.add(urlImages + "2.jpg");
        list.add(urlImages + "3.jpg");
        list.add(urlImages + "4.jpg");
        list.add(urlImages + "5.jpg");
        list.add(urlImages + "6.jpg");
        

        for (int i = 0; i < list.size(); i++) cache.add(i, null);
        setLayout(null);
        titlePanel.setBounds(0, 0, 800, 49);
        
        add(titlePanel);
        titlePanel.setLayout(null);
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
        
        titlePanel.add(backButton);
        imageLabel.setBounds(70, 52, 660, 378);

        imageLabel.setIcon(getImage(0));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setBorder(border);
        this.add(imageLabel);

        favorites = new JComboBox(
            list.toArray(new String[list.size()]));
        favorites.setBounds(339, 9, 53, 40);
        favorites.setActionCommand("favs");
        favorites.addActionListener(this);
        
        prevButton.setLocation(290, 9);

        prevButton.setText("");
        prevButton.setSize(40, 40);
        ImageIcon prevIcon = new ImageIcon("icon/prev_icon.png");
		Image imgPrev = prevIcon.getImage();
		Image newimgPrev = imgPrev.getScaledInstance(prevButton.getWidth(), prevButton.getHeight(), Image.SCALE_SMOOTH);
		prevIcon = new ImageIcon(newimgPrev);
		prevButton.setIcon(prevIcon);
        prevButton.setActionCommand("prev");
        
        prevButton.addActionListener(this);
        nextButton.setBounds(397, 9, 79, 31);
        

        nextButton.setText("");
        nextButton.setSize(40, 40);
        ImageIcon nextIcon = new ImageIcon("icon/next_icon.png");
		Image imgNext = nextIcon.getImage();
		final Image newimgNext = imgNext.getScaledInstance(nextButton.getWidth(), nextButton.getHeight(), Image.SCALE_SMOOTH);
		nextIcon = new ImageIcon(newimgNext);
		nextButton.setIcon(nextIcon);
        nextButton.setActionCommand("next");
        nextButton.addActionListener(this);
        
        prevButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		prevButton.setIcon(new ImageIcon(newimgNext));
        	}
        });
        

        JPanel controlPanel = new JPanel();
        controlPanel.setBounds(0, 540, 800, 60);
        controlPanel.setLayout(null);
        controlPanel.add(prevButton);
        controlPanel.add(favorites);
        controlPanel.add(nextButton);
        controlPanel.setBorder(border);
        this.add(controlPanel);
        
        
        
    }

    public void actionPerformed(ActionEvent ae) {
        String cmd = ae.getActionCommand();
        if ("favs".equals(cmd)) {
            int index = favorites.getSelectedIndex();
            ImageIcon image = getImage(index);
            imageLabel.setIcon(image);
            if (image != null) imageLabel.setText("");
            else imageLabel.setText("Image not available.");
        }
        if ("prev".equals(cmd)) {
            int index = favorites.getSelectedIndex() - 1;
            if (index < 0) index = list.size() - 1;
            favorites.setSelectedIndex(index);
        }
        if ("next".equals(cmd)) {
            int index = favorites.getSelectedIndex() + 1;
            if (index > list.size() - 1) index = 0;
            favorites.setSelectedIndex(index);
        }
    }

    public JButton getDefault() { return nextButton; }

    // Return the (possibly cached) image having the given index.
    private ImageIcon getImage(int index) {
        ImageIcon image = cache.get(index);
        if (image != null) return image;
        String name = "images/" + list.get(index);
        try {
        	URL url = new URL(list.get(index));
        	if (url != null) {
                image = new ImageIcon(url);
            }
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        
        cache.set(index, image);
        return image;
    }
}
