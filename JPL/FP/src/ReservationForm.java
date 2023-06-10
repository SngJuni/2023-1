import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

public class ReservationForm extends JFrame {

	private JPanel contentPane;
	private JLabel wallpaperLabel;
	private JLabel titleLabel;
	private JPanel panel;
	private JLabel eventLabel;
	private JComboBox eventComboBox;
	private JLabel dateLabel;
	private JComboBox dateComboBox;
	private JLabel matchLabel;
	private JComboBox matchComboBox;
	private JLabel etcLabel;
	private JTextField etctextField;
	private JPanel buttonPanel;
	private JButton enterButton;
	private JButton cancelButton;
	private JButton mkMatchButton;
	private List<String> errorList; // the list for error to deal with error message

	public ReservationForm(User user) { // java swing components for page layout
		setTitle("Reservation Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane);
		layeredPane.setBounds(0, 0, 300, 470);
		layeredPane.setLayout(null);
		
		wallpaperLabel = new JLabel("");
		wallpaperLabel.setVerticalAlignment(SwingConstants.TOP);
		wallpaperLabel.setIcon(new ImageIcon(ReservationForm.class.getResource("img/wallpaper.png")));
		wallpaperLabel.setBounds(-7, -10, 300, 470);
		layeredPane.add(wallpaperLabel, Integer.valueOf(Integer.MIN_VALUE));
		
		titleLabel = new JLabel("Reservation");
		titleLabel.setBackground(new Color(255, 255, 255));
		titleLabel.setForeground(new Color(50, 83, 45));
		titleLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 30));
		titleLabel.setVerticalAlignment(SwingConstants.TOP);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(0, 10, 300, 50);
		layeredPane.add(titleLabel);
		
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 50, 275, 270);
		layeredPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{276, 0};
		gbl_panel.rowHeights = new int[] {30, 30, 30, 30, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		eventLabel = new JLabel("Event :");
		eventLabel.setForeground(new Color(50, 83, 45));
		eventLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 20));
		GridBagConstraints gbc_eventLabel = new GridBagConstraints();
		gbc_eventLabel.fill = GridBagConstraints.BOTH;
		gbc_eventLabel.insets = new Insets(0, 10, 5, 0);
		gbc_eventLabel.gridx = 0;
		gbc_eventLabel.gridy = 0;
		panel.add(eventLabel, gbc_eventLabel);
		
		String event[] = {"", "Football", "Futsal"}; // list for combo box
		eventComboBox = new JComboBox(event);
		eventComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // if event is seleceted, load date data into date combo box
				selectedEvent();
			}
		});
		eventComboBox.setForeground(new Color(0, 0, 0));
		eventComboBox.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_eventComboBox = new GridBagConstraints();
		gbc_eventComboBox.insets = new Insets(0, 10, 5, 10);
		gbc_eventComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_eventComboBox.gridx = 0;
		gbc_eventComboBox.gridy = 1;
		panel.add(eventComboBox, gbc_eventComboBox);
		
		dateLabel = new JLabel("Date :");
		dateLabel.setForeground(new Color(50, 83, 45));
		dateLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 20));
		GridBagConstraints gbc_dateLabel = new GridBagConstraints();
		gbc_dateLabel.fill = GridBagConstraints.BOTH;
		gbc_dateLabel.insets = new Insets(0, 10, 5, 0);
		gbc_dateLabel.gridx = 0;
		gbc_dateLabel.gridy = 2;
		panel.add(dateLabel, gbc_dateLabel);
		
		String date[] = {""}; // initialize the combo box
		dateComboBox = new JComboBox(date);
		dateComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // if date is seleceted, load match data into match combo box
				selectedDate();
			}
		});
		dateComboBox.setForeground(new Color(0, 0, 0));
		dateComboBox.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_dateComboBox = new GridBagConstraints();
		gbc_dateComboBox.insets = new Insets(0, 10, 5, 10);
		gbc_dateComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateComboBox.gridx = 0;
		gbc_dateComboBox.gridy = 3;
		panel.add(dateComboBox, gbc_dateComboBox);
		
		matchLabel = new JLabel("Match :");
		matchLabel.setForeground(new Color(50, 83, 45));
		matchLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 20));
		GridBagConstraints gbc_matchLabel = new GridBagConstraints();
		gbc_matchLabel.fill = GridBagConstraints.BOTH;
		gbc_matchLabel.insets = new Insets(0, 10, 5, 0);
		gbc_matchLabel.gridx = 0;
		gbc_matchLabel.gridy = 4;
		panel.add(matchLabel, gbc_matchLabel);
		
		String match[] = {""}; // initialize the combo box
		matchComboBox = new JComboBox(match);
		matchComboBox.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_matchComboBox = new GridBagConstraints();
		gbc_matchComboBox.insets = new Insets(0, 10, 5, 10);
		gbc_matchComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_matchComboBox.gridx = 0;
		gbc_matchComboBox.gridy = 5;
		panel.add(matchComboBox, gbc_matchComboBox);
		
		etcLabel = new JLabel("Etc (positon, ...) :");
		etcLabel.setForeground(new Color(50, 83, 45));
		etcLabel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));
		GridBagConstraints gbc_etcLabel = new GridBagConstraints();
		gbc_etcLabel.fill = GridBagConstraints.BOTH;
		gbc_etcLabel.insets = new Insets(0, 10, 5, 0);
		gbc_etcLabel.gridx = 0;
		gbc_etcLabel.gridy = 6;
		panel.add(etcLabel, gbc_etcLabel);
		
		etctextField = new JTextField();
		GridBagConstraints gbc_etctextField = new GridBagConstraints();
		gbc_etctextField.ipady = 6;
		gbc_etctextField.insets = new Insets(0, 10, 5, 10);
		gbc_etctextField.fill = GridBagConstraints.BOTH;
		gbc_etctextField.gridx = 0;
		gbc_etctextField.gridy = 7;
		panel.add(etctextField, gbc_etctextField);
		etctextField.setColumns(10);
		
		mkMatchButton = new JButton("Make Match");
		mkMatchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MakeMatch makeMatchForm = new MakeMatch(user); // open the make match window
				makeMatchForm.setVisible(true);
				dispose();
			}
		});
		mkMatchButton.setForeground(new Color(255, 255, 255));
		mkMatchButton.setBackground(new Color(255, 128, 0));
		GridBagConstraints gbc_mkMatchButton = new GridBagConstraints();
		gbc_mkMatchButton.insets = new Insets(0, 0, 0, 10);
		gbc_mkMatchButton.anchor = GridBagConstraints.EAST;
		gbc_mkMatchButton.gridx = 0;
		gbc_mkMatchButton.gridy = 8;
		panel.add(mkMatchButton, gbc_mkMatchButton);
		
		buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setBounds(0, 320, 300, 100);
		layeredPane.add(buttonPanel);
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[]{276, 0};
		gbl_buttonPanel.rowHeights = new int[] {30, 30, 0};
		gbl_buttonPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_buttonPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		buttonPanel.setLayout(gbl_buttonPanel);
		
		enterButton = new JButton("Enter");
		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errorList = new ArrayList<>();

				String event = eventComboBox.getSelectedItem().toString();
				String date = dateComboBox.getSelectedItem().toString();
				String match = matchComboBox.getSelectedItem().toString();
				String etc = etctextField.getText();
				
				// check each field and if there are some error, throw exception and add error message
				try {                               
					checkEventField();
				} catch (ImproperFieldException err) {
					errorList.add(err.getMessage());
				}
				try {                               
					checkDateField();
				} catch (ImproperFieldException err) {
					errorList.add(err.getMessage());
				}
				try {                               
					checkMatchField();
				} catch (ImproperFieldException err) {
					errorList.add(err.getMessage());
				}
				
				// if there isn't error, make reservation and open check reservation form while passing user, reservation
				if (errorList.size() == 0) {
					Reservation reservation = new Reservation(event, date, match, etc);
					
					CheckReservation checkReservation = new CheckReservation(user, reservation);
					checkReservation.setVisible(true);
					dispose();
				} else {
					String error = new String("");
					
					for (int i = 0; i < errorList.size(); i++) {
						 error = error + (i + 1) + ". " + errorList.get(i) + "\n";
					}
					
					JOptionPane.showMessageDialog(null, error, "You have following problems", JOptionPane.ERROR_MESSAGE);
					
					error = "";
					errorList.clear();
					return;
				}
			}
		});
		enterButton.setForeground(new Color(255, 255, 255));
		enterButton.setBackground(new Color(50, 83, 45));
		enterButton.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 20));
		GridBagConstraints gbc_enterButton = new GridBagConstraints();
		gbc_enterButton.insets = new Insets(10, 10, 5, 10);
		gbc_enterButton.fill = GridBagConstraints.BOTH;
		gbc_enterButton.gridx = 0;
		gbc_enterButton.gridy = 0;
		buttonPanel.add(enterButton, gbc_enterButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // close the window
				dispose();
			}
		});
		cancelButton.setForeground(new Color(255, 255, 255));
		cancelButton.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 20));
		cancelButton.setBackground(new Color(50, 83, 45));
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.insets = new Insets(5, 10, 10, 10);
		gbc_cancelButton.fill = GridBagConstraints.BOTH;
		gbc_cancelButton.gridx = 0;
		gbc_cancelButton.gridy = 1;
		buttonPanel.add(cancelButton, gbc_cancelButton);
	}
	
	private void selectedEvent() {
		SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				String event = eventComboBox.getSelectedItem().toString(); // load event data
				dateComboBox.removeAllItems();  // initialize all combo box
				dateComboBox.addItem("");
				matchComboBox.removeAllItems();
				matchComboBox.addItem("");
				
				if (event.equals("")) {
					publish(0);
				} else if (event.equals("Football")) {
					publish(1);
				} else if (event.equals("Futsal")) {
					publish(2);
				}
				
				return null;
			}
			
			@Override
			protected void process(List<Integer> chunks) {
				int eventFlag = chunks.get(chunks.size()-1);
				
				if (eventFlag == 1) { // the case of "Football"
					try {
						FileInputStream fileReader = new FileInputStream("reservationDB.txt");
						Scanner buffer = new Scanner(fileReader);
						
						while (buffer.hasNext()) {
							String line = buffer.nextLine();
							if (line.equals("Football")) {
								line = buffer.nextLine();
								dateComboBox.addItem(line);
							}
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				} else if (eventFlag == 2) { // the case of "Futsal"
					try {
						FileInputStream fileReader = new FileInputStream("reservationDB.txt");
						Scanner buffer = new Scanner(fileReader);
						
						while (buffer.hasNext()) {
							String line = buffer.nextLine();
							if (line.equals("Futsal")) {
								line = buffer.nextLine();
								dateComboBox.addItem(line);
							}
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		};
		worker.execute();
	}
	
	private void selectedDate() {
		SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				matchComboBox.removeAllItems(); // initialize the match combo box
				matchComboBox.addItem("");
				
				publish(0);
				
				return null;
			}
			
			@Override
			protected void process(List<Integer> chunks) {
					String date = dateComboBox.getSelectedItem().toString(); // load date data
					
					try {
						FileInputStream fileReader = new FileInputStream("reservationDB.txt");
						Scanner buffer = new Scanner(fileReader);
						
						while (buffer.hasNext()) {
							String line = buffer.nextLine();
							
							if (line.equals("Football")) { // if db's event is "Football", and db's date equals to seleceted date
								line = buffer.nextLine();
								if (line.equals(date)) {
									line = buffer.nextLine();
									matchComboBox.addItem(line);
								}
							}
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
		};
		worker.execute();
	}
	
	
	// if each fields are empty, throw error message.
	public void checkEventField() throws ImproperFieldException {
		String event = eventComboBox.getSelectedItem().toString();
		
		if (event.length() == 0) {
			throw new ImproperFieldException("You forgot to select the event, Please select it.");
		}
	}
	
	public void checkDateField() throws ImproperFieldException {
		String date = dateComboBox.getSelectedItem().toString();
		
		if (date.length() == 0) {
			throw new ImproperFieldException("You forgot to select the date, Please select it.");
		}
	}
	
	public void checkMatchField() throws ImproperFieldException {
		String match = matchComboBox.getSelectedItem().toString();
		
		if (match.length() == 0) {
			throw new ImproperFieldException("You forgot to select the match, Please select it.");
		}
	}
}
