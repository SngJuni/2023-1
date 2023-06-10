import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MakeMatch extends JFrame {

	private JPanel contentPane;
	private JLabel wallpaperLabel;
	private JLabel titleLabel;
	private JPanel panel;
	private JLabel eventLabel;
	private JComboBox eventComboBox;
	private JLabel dateLabel;
	private JLabel matchLabel;
	private JPanel buttonPanel;
	private JButton enterButton;
	private JButton cancelButton;
	private JTextField datetextField;
	private JTextField matchtextField;
	private List<String> errorList; // the list for error to deal with error message

	public MakeMatch(User user) {   // java swing components for page layout
		setTitle("Make Match");
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
		
		titleLabel = new JLabel("Match");
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
		gbl_panel.rowHeights = new int[] {30, 30, 30, 30, 30, 30};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
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
		
		String event[] = {"", "Football", "Futsal"};
		eventComboBox = new JComboBox(event);
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
		
		matchLabel = new JLabel("Match :");
		matchLabel.setForeground(new Color(50, 83, 45));
		matchLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 20));
		GridBagConstraints gbc_matchLabel = new GridBagConstraints();
		gbc_matchLabel.fill = GridBagConstraints.BOTH;
		gbc_matchLabel.insets = new Insets(0, 10, 5, 0);
		gbc_matchLabel.gridx = 0;
		gbc_matchLabel.gridy = 4;
		panel.add(matchLabel, gbc_matchLabel);
		
		datetextField = new JTextField();
		GridBagConstraints gbc_datetextField = new GridBagConstraints();
		gbc_datetextField.ipady = 5;
		gbc_datetextField.insets = new Insets(0, 10, 5, 10);
		gbc_datetextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_datetextField.gridx = 0;
		gbc_datetextField.gridy = 3;
		panel.add(datetextField, gbc_datetextField);
		datetextField.setColumns(10);
		
		matchtextField = new JTextField();
		GridBagConstraints gbc_matchtextField = new GridBagConstraints();
		gbc_matchtextField.ipady = 5;
		gbc_matchtextField.insets = new Insets(0, 10, 5, 10);
		gbc_matchtextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_matchtextField.gridx = 0;
		gbc_matchtextField.gridy = 5;
		panel.add(matchtextField, gbc_matchtextField);
		matchtextField.setColumns(10);
		
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
				
				// if there isn't error, write these data to reservationDB text file
				if (errorList.size() == 0) {
					String event = eventComboBox.getSelectedItem().toString();
					String date = datetextField.getText();
					String match = matchtextField.getText();
					
					try {
						FileOutputStream fileWrite = new FileOutputStream("reservationDB.txt", true);
						PrintWriter buffer = new PrintWriter(fileWrite);
						buffer.println(event);
						buffer.println(date);
						buffer.println(match);
						
						buffer.close();
						fileWrite.close();
						
						JOptionPane.showMessageDialog(MakeMatch.this, "You make the match!");
						
						ReservationForm ReservationForm = new ReservationForm(user); // open reservation form while passing user
						ReservationForm.setVisible(true);
						dispose();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				} else {
					String error = new String("");
					
					for (int i = 0; i < errorList.size(); i++) {
						 error = error + (i + 1) + ". " + errorList.get(i) + "\n";
					}
					
					JOptionPane.showMessageDialog(null, error, "You have following problems", JOptionPane.ERROR_MESSAGE);
					
					error = "";
					errorList.clear();
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
	
	// if each fields are empty or don't satisfy the format, throw error message.
	public void checkEventField() throws ImproperFieldException {
		String event = eventComboBox.getSelectedItem().toString();
		
		if (event.length() == 0) {
			throw new ImproperFieldException("You fotgot to select event, Please select it.");
		}
	}
	
	public void checkDateField() throws ImproperFieldException {
		String date = datetextField.getText();
		String dateFormat = "^\\d{4}/\\d{2}/\\d{2}$";
		Pattern pattern = Pattern.compile(dateFormat);
		Matcher matcher = pattern.matcher(date);
		
		if (date.length() == 0) {
			throw new ImproperFieldException("You fotgot to fill the date field, Please fill it.");
		} else if (!matcher.matches()) {
			throw new ImproperFieldException("Proper format for date is '2023/06/09'.");
		}
	}
	
	public void checkMatchField() throws ImproperFieldException {
		String match = matchtextField.getText();
		String matchFormat = "\\d{2}:\\d{2}-\\d{2}:\\d{2}";
		Pattern pattern = Pattern.compile(matchFormat);
		Matcher matcher = pattern.matcher(match);
		
		if (match.length() == 0) {
			throw new ImproperFieldException("You fotgot to fill the match field, Please fill it.");
		} else if (!matcher.matches()) {
			throw new ImproperFieldException("Proper format for date is '09:00-11:00'.");
		}
	}
	
}
