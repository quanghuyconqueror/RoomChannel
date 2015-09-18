import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


public class ChannelJPanel extends JPanel {
	
	private ArrayList<Room> rooms = null;
	public static ArrayList<ImageIcon> cacheLogo = null;
	
	public ChannelJPanel() {
		setSize(800, 600);
		JButton googleMapButton = new JButton("Google Map");
		googleMapButton.setBounds(10, 215, 91, 23);
		googleMapButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainJFrame.contentPane.removeAll();
				MainJFrame.contentPane.validate();
				MainJFrame.contentPane.repaint();
				//ss
				MainJFrame.contentPane.add(MainJFrame.googleMapPanel);
				MainJFrame.contentPane.validate();
				MainJFrame.contentPane.repaint();
			}
		});
		cacheLogo = new ArrayList<ImageIcon>();
		JButton roomButton = new JButton("Room");
		roomButton.setBounds(10, 171, 61, 23);
		roomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainJFrame.contentPane.removeAll();
				MainJFrame.contentPane.validate();
				MainJFrame.contentPane.repaint();
				
				MainJFrame.contentPane.add(new RoomJPanel(rooms.get(0)));
				MainJFrame.contentPane.validate();
				MainJFrame.contentPane.repaint();
			}
		});
		setLayout(null);
		add(roomButton);
		add(googleMapButton);
		
		MySqlFunctions mySqlFunctions = new MySqlFunctions();
		rooms = mySqlFunctions.loadAllRoom();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(153, 74, 647, 515);
		add(scrollPane);
		Date currentDate = new Date();
		DefaultListModel model = new DefaultListModel();
		if (rooms != null) {
			for (int i = 0; i < rooms.size(); i++) {
				model.addElement(rooms.get(i));
				
				TimeFunctions timeFunctions = new TimeFunctions();
					
				Date postTime = timeFunctions.formatDate(rooms.get(i).getTimePosted());
				String difTime = timeFunctions.calculteDateDif(currentDate, postTime);
				System.out.println("Dif time: " + difTime);
					
					
				
				BufferedImage img = null;
				try {
				    img = ImageIO.read(new URL("http://enddev.site50.net/RoomChannelPHPServer/RoomImages/1.jpg"));
				} catch (IOException e) {
				    e.printStackTrace();
				}
				Image dimg = img.getScaledInstance(60, 60,
				            Image.SCALE_SMOOTH);
				ImageIcon logoIcon = new ImageIcon(dimg);
				cacheLogo.add(logoIcon);
			}
		}
		
		JList list = new JList(model);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JList listClicked = (JList) e.getSource();
				if (e.getClickCount() == 2) {
					// double click detected
					int index = listClicked.locationToIndex(e.getPoint());
					MainJFrame.contentPane.removeAll();
					MainJFrame.contentPane.validate();
					MainJFrame.contentPane.repaint();
					
					MainJFrame.contentPane.add(new RoomJPanel(rooms.get(index)));
					MainJFrame.contentPane.validate();
					MainJFrame.contentPane.repaint();
		
				}
			}
		});
		
		list.setCellRenderer(new RoomCellRenderer());
		
		scrollPane.setViewportView(list);
		
		JButton submitRoom = new JButton("\u0110\u0103ng ph\u00F2ng");
		submitRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainJFrame.contentPane.removeAll();
				MainJFrame.contentPane.validate();
				MainJFrame.contentPane.repaint();
				
				MainJFrame.contentPane.add(new PostRoomJPanel());
				MainJFrame.contentPane.validate();
				MainJFrame.contentPane.repaint();
			}
		});
		submitRoom.setBounds(10, 126, 101, 23);
		add(submitRoom);
		
		JButton btnNewButton = new JButton("T\u00ECm g\u1EA7n \u0111\u00E2y");
		btnNewButton.setBounds(10, 84, 118, 23);
		add(btnNewButton);
		
		JButton btnTmTheoThnh = new JButton("T\u00ECm theo th\u00E0nh ph\u1ED1");
		btnTmTheoThnh.setBounds(10, 264, 133, 23);
		add(btnTmTheoThnh);
		
		JButton btnNewButton_1 = new JButton("K\u00EAnh chia s\u1EBB");
		btnNewButton_1.setBounds(10, 314, 101, 23);
		add(btnNewButton_1);
		
		
		
	

	}
}
