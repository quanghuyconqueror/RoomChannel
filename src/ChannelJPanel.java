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
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ChannelJPanel extends JPanel {
	
	private ArrayList<Room> rooms = null;
	
	public ChannelJPanel() {
		setSize(800, 600);
		JButton googleMapButton = new JButton("Google Map");
		googleMapButton.setBounds(265, 5, 91, 23);
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
		
		JButton roomButton = new JButton("Room");
		roomButton.setBounds(185, 39, 61, 23);
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
		
		JButton loadAllRoomButton = new JButton("Load All Room");
		loadAllRoomButton.setBounds(159, 5, 101, 23);
		
		
		add(loadAllRoomButton);
		add(googleMapButton);
		
		MySqlFunctions mySqlFunctions = new MySqlFunctions();
		rooms = mySqlFunctions.loadAllRoom();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(93, 74, 261, 157);
		add(scrollPane);
		DefaultListModel model = new DefaultListModel();
		if (rooms != null) {
			for (int i = 0; i < rooms.size(); i++) {
				Room room = rooms.get(i);
				model.addElement(room.getAddress());
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
		submitRoom.setBounds(366, 5, 101, 23);
		add(submitRoom);
		
		
		
	

	}
}
