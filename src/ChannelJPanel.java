import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.JScrollPane;


public class ChannelJPanel extends JPanel {
	
	private ArrayList<Room> rooms = null;
	
	public ChannelJPanel() {
		
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
		roomButton.setBounds(93, 5, 61, 23);
		roomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainJFrame.contentPane.removeAll();
				MainJFrame.contentPane.validate();
				MainJFrame.contentPane.repaint();
				
				MainJFrame.contentPane.add(new RoomJPanel());
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
		submitRoom.setBounds(10, 39, 101, 23);
		add(submitRoom);
		
	

	}
}
