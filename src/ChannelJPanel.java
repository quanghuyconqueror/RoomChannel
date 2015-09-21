import javax.imageio.ImageIO;
import javax.management.modelmbean.ModelMBean;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public class ChannelJPanel extends JPanel {
	//sss
	private ArrayList<Room> rooms = null;
	public static ArrayList<ImageIcon> cacheLogo = null;
	public static String cityDialogChose = "";
	private RoomListModel model;
	private JList list;
	private JScrollPane scrollPane;
	
	public ChannelJPanel() {
		setSize(800, 600);
		JButton googleMapButton = new JButton("Google Map");
		googleMapButton.setBounds(10, 215, 91, 23);
		googleMapButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainJFrame.contentPane.removeAll();
				MainJFrame.contentPane.validate();
				MainJFrame.contentPane.repaint();

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

		scrollPane = new JScrollPane();
		scrollPane.setBounds(153, 74, 647, 515);
		add(scrollPane);
		Date currentDate = new Date();
		model = new RoomListModel();
		if (rooms != null) {
			for (int i = 0; i < rooms.size(); i++) {
				model.addElement(rooms.get(i));
				
				TimeFunctions timeFunctions = new TimeFunctions();
					
				Date postTime = timeFunctions.formatDate(rooms.get(i).getTimePosted());
				String difTime = timeFunctions.calculteDateDif(currentDate, postTime);
				System.out.println("Dif time: " + difTime);
					
					
				
				BufferedImage img = null;
				try {
				    img = ImageIO.read(new File("icon/background_login.png"));
				} catch (IOException e) {
				    e.printStackTrace();
				}
				Image dimg = img.getScaledInstance(60, 60,
				            Image.SCALE_SMOOTH);
				ImageIcon logoIcon = new ImageIcon(dimg);
				cacheLogo.add(logoIcon);
			}
		}
		
		list = new JList(model);
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
		
		JButton submitRoom = new JButton("Đăng phòng");
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
		
		JButton searchAround = new JButton("Tìm gần đây");
		searchAround.setBounds(10, 84, 118, 23);
		add(searchAround);
		
		JButton searchByCity = new JButton("Tìm theo thành phố");
		searchByCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChooseCityDialog dialog = new ChooseCityDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				dialog.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						if (!cityDialogChose.equals("")) {
							refreshRooms();
							cityDialogChose = "";
						}
						
					}
				});
			}
		});
		searchByCity.setBounds(10, 264, 133, 23);
		add(searchByCity);
		
		JButton btnNewButton_1 = new JButton("Kênh chia sẻ");
		btnNewButton_1.setBounds(10, 314, 101, 23);
		add(btnNewButton_1);
		
		
		
	

	}
	private void refreshRooms() {
		model.clear();
		cacheLogo.clear();
		rooms.clear();
		System.out.println("Tỉnh / thành phố đã chọn: " + cityDialogChose);
		
		MySqlFunctions mySqlFunctions = new MySqlFunctions();
		
		rooms = mySqlFunctions.loadRoomByCity(cityDialogChose);
		
		for (int i = 0; i < rooms.size(); i++) {
			model.addElement(rooms.get(i));
			
			TimeFunctions timeFunctions = new TimeFunctions();
				
			Date postTime = timeFunctions.formatDate(rooms.get(i).getTimePosted());
			Date currentDate = new Date();
			String difTime = timeFunctions.calculteDateDif(currentDate, postTime);
			System.out.println("Dif time: " + difTime);
				
				
			
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File("icon/background_login.png"));
			} catch (IOException e) {
			    e.printStackTrace();
			}
			Image dimg = img.getScaledInstance(60, 60,
			            Image.SCALE_SMOOTH);
			ImageIcon logoIcon = new ImageIcon(dimg);
			cacheLogo.add(logoIcon);
			model.fireContentsChanged(model, 0, model.size());
			
			
		}
		
	}
}
