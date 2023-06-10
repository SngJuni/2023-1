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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;

public class SignupPage extends JFrame {

	private JPanel contentPane;
	private JLabel wallpaperLabel;
	private JLabel titleLabel;
	private JPanel panel;
	private JLabel IDLabel;
	private JTextField IDtextField;
	private JLabel PWLabel;
	private JPasswordField PWField;
	private JLabel confirmPWLabel;
	private JPasswordField confirmPWField;
	private JLabel phoneNumLabel;
	private JTextField phoneNumtextField;
	private JPanel buttonPanel;
	private JButton enterButton;
	private JButton cancelButton;
	private List<String> errorList; // the list for error to deal with error message

	public SignupPage() {           // java swing components for page layout
		setTitle("Sign Up");
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
		wallpaperLabel.setIcon(new ImageIcon(SignupPage.class.getResource("img/wallpaper.png")));
		wallpaperLabel.setBounds(-7, -10, 300, 470);
		layeredPane.add(wallpaperLabel, Integer.valueOf(Integer.MIN_VALUE));
		
		titleLabel = new JLabel("Signup Page");
		titleLabel.setBackground(new Color(255, 255, 255));
		titleLabel.setForeground(new Color(50, 83, 45));
		titleLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 30));
		titleLabel.setVerticalAlignment(SwingConstants.TOP);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(0, 10, 300, 50);
		layeredPane.add(titleLabel);
		
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 60, 275, 260);
		layeredPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{276, 0};
		gbl_panel.rowHeights = new int[] {30, 30, 30, 30, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		IDLabel = new JLabel("ID :");
		IDLabel.setForeground(new Color(50, 83, 45));
		IDLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 20));
		GridBagConstraints gbc_IDLabel = new GridBagConstraints();
		gbc_IDLabel.fill = GridBagConstraints.BOTH;
		gbc_IDLabel.insets = new Insets(0, 10, 5, 0);
		gbc_IDLabel.gridx = 0;
		gbc_IDLabel.gridy = 0;
		panel.add(IDLabel, gbc_IDLabel);
		
		IDtextField = new JTextField();
		GridBagConstraints gbc_IDtextField = new GridBagConstraints();
		gbc_IDtextField.fill = GridBagConstraints.BOTH;
		gbc_IDtextField.insets = new Insets(0, 10, 5, 10);
		gbc_IDtextField.gridx = 0;
		gbc_IDtextField.gridy = 1;
		panel.add(IDtextField, gbc_IDtextField);
		IDtextField.setColumns(10);
		
		PWLabel = new JLabel("PW : ");
		PWLabel.setForeground(new Color(50, 83, 45));
		PWLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 20));
		GridBagConstraints gbc_PWLabel = new GridBagConstraints();
		gbc_PWLabel.fill = GridBagConstraints.BOTH;
		gbc_PWLabel.insets = new Insets(0, 10, 5, 0);
		gbc_PWLabel.gridx = 0;
		gbc_PWLabel.gridy = 2;
		panel.add(PWLabel, gbc_PWLabel);
		
		PWField = new JPasswordField();
		GridBagConstraints gbc_PWField = new GridBagConstraints();
		gbc_PWField.ipady = 6;
		gbc_PWField.insets = new Insets(0, 10, 5, 10);
		gbc_PWField.fill = GridBagConstraints.HORIZONTAL;
		gbc_PWField.gridx = 0;
		gbc_PWField.gridy = 3;
		panel.add(PWField, gbc_PWField);
		
		confirmPWLabel = new JLabel("Confirm PW :");
		confirmPWLabel.setForeground(new Color(50, 83, 45));
		confirmPWLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 20));
		GridBagConstraints gbc_confirmPWLabel = new GridBagConstraints();
		gbc_confirmPWLabel.fill = GridBagConstraints.BOTH;
		gbc_confirmPWLabel.insets = new Insets(0, 10, 5, 0);
		gbc_confirmPWLabel.gridx = 0;
		gbc_confirmPWLabel.gridy = 4;
		panel.add(confirmPWLabel, gbc_confirmPWLabel);
		
		confirmPWField = new JPasswordField();
		GridBagConstraints gbc_confirmPWField = new GridBagConstraints();
		gbc_confirmPWField.ipady = 6;
		gbc_confirmPWField.insets = new Insets(0, 10, 5, 10);
		gbc_confirmPWField.fill = GridBagConstraints.HORIZONTAL;
		gbc_confirmPWField.gridx = 0;
		gbc_confirmPWField.gridy = 5;
		panel.add(confirmPWField, gbc_confirmPWField);
		
		phoneNumLabel = new JLabel("Phone Number :");
		phoneNumLabel.setForeground(new Color(50, 83, 45));
		phoneNumLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 20));
		GridBagConstraints gbc_phoneNumLabel = new GridBagConstraints();
		gbc_phoneNumLabel.fill = GridBagConstraints.BOTH;
		gbc_phoneNumLabel.insets = new Insets(0, 10, 5, 0);
		gbc_phoneNumLabel.gridx = 0;
		gbc_phoneNumLabel.gridy = 6;
		panel.add(phoneNumLabel, gbc_phoneNumLabel);
		
		phoneNumtextField = new JTextField();
		GridBagConstraints gbc_phoneNumtextField = new GridBagConstraints();
		gbc_phoneNumtextField.ipady = 6;
		gbc_phoneNumtextField.insets = new Insets(0, 10, 5, 10);
		gbc_phoneNumtextField.fill = GridBagConstraints.BOTH;
		gbc_phoneNumtextField.gridx = 0;
		gbc_phoneNumtextField.gridy = 7;
		panel.add(phoneNumtextField, gbc_phoneNumtextField);
		phoneNumtextField.setColumns(10);
		
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
					checkIDField();
				} catch (ImproperFieldException err) {
					errorList.add(err.getMessage());
				}
				try {                               
					checkPWField();
				} catch (ImproperFieldException err) {
					errorList.add(err.getMessage());
				}
				try {                               
					checkConfirmPWField();
				} catch (ImproperFieldException err) {
					errorList.add(err.getMessage());
				}
				try {                               
					checkPhoneNumField();
				} catch (ImproperFieldException err) {
					errorList.add(err.getMessage());
				}
				
				// if there isn't error, write these data to userDB text file
				if (errorList.size() == 0) {
					String id = IDtextField.getText();
					char[] pw = PWField.getPassword();
					String pwStr = new String(pw);
					String phoneNum = phoneNumtextField.getText();
					
					try {
						FileOutputStream fileWrite = new FileOutputStream("userDB.txt", false);
						PrintWriter buffer = new PrintWriter(fileWrite);
						buffer.println(id);
						buffer.println(pwStr);
						buffer.print(phoneNum);
						
						buffer.close();
						fileWrite.close();
						
						JOptionPane.showMessageDialog(SignupPage.this, "You are signed up, thank you!");
						LoginPage LoginPage = new LoginPage();
						LoginPage.setVisible(true);
						dispose();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else { // if there are some error, display the error message dialog
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
		cancelButton.addActionListener(new ActionListener() { // close the signup page
			public void actionPerformed(ActionEvent e) {
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
	public void checkIDField() throws ImproperFieldException {
		String id = IDtextField.getText();
		
		if (id.length() == 0) {
			throw new ImproperFieldException("You forgot to fill id field, Please fill it.");
		}
	}
	
	public void checkPWField() throws ImproperFieldException {
		char[] pw = PWField.getPassword();
		String pwStr = new String(pw);
		
		if (pwStr.length() == 0) {
			throw new ImproperFieldException("You forgot to fill pw field, Please fill it.");
		}
	}
	
	public void checkConfirmPWField() throws ImproperFieldException {
		char[] pw = PWField.getPassword();
		String pwStr = new String(pw);
		char[] confirmpw = confirmPWField.getPassword();
		String confirmpwStr = new String(confirmpw);
		
		if (confirmpwStr.length() == 0) {
			throw new ImproperFieldException("You forgot to fill confirm pw field, Please fill it.");
		} else if (!pwStr.equals(confirmpwStr)) { // verify the passwords match
			throw new ImproperFieldException("Your password does not match, Please enter it again.");
		}
	}
	
	public void checkPhoneNumField() throws ImproperFieldException { // check the phone number format with using regex
		String phoneNum = phoneNumtextField.getText();
		String phoneNumFormat = "^\\d{3}-\\d{4}-\\d{4}$";
		Pattern pattern = Pattern.compile(phoneNumFormat);
		Matcher matcher = pattern.matcher(phoneNum);
		
		if (phoneNum.length() == 0) {
			throw new ImproperFieldException("You fotgot to fill the phone number field, Please fill it.");
		} else if (!matcher.matches()) {
			throw new ImproperFieldException("Proper format for a phone number is '010-1234-5678'.");
		}
}

}
