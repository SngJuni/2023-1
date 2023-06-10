import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;

public class Main extends JFrame implements ActionListener {

	private JPanel contentPane;                 // all the variable for components
	private JPanel left;
	private JPanel right;
	private JLabel wallPaper;
	private JLabel titleImg;
	private JLabel ApplicantNameLabel;
	private JTextField ApplicantNameTextField;
	private JLabel BirthDateLabel;
	private JTextField BirthDateTextField;
	private JLabel EmailLabel;
	private JTextField EmailTextField;
	private JLabel DegreeLabel;
	private JTextField DegreeTextField;
	private JLabel AttendedUnivLabel;
	private JTextField AttendedUnivTextField;
	private JLabel GpaLabel;
	private JTextField GpaTextField;
	private JLabel PhoneNumLabel;
	private JTextField PhoneNumTextField;
	private JLabel KakaoIDLabel;
	private JTextField KakaoIDTextField;
	private JLabel PersonalStmtLabel;
	private JTextArea PersonalStmtTextArea;
	private JLabel HomeAddrLabel;
	private JTextField HomeAddrTextField;
	private JButton SubmitButton;
	
	private List<String> errorList; // the list to store error messages

	
	public Main() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {0};
		gbl_contentPane.rowHeights = new int[] {0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		left = new JPanel();                                       // left panel for wall paper
		left.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_left = new GridBagConstraints();
		gbc_left.gridheight = 2;
		gbc_left.fill = GridBagConstraints.BOTH;
		gbc_left.gridx = 0;
		gbc_left.gridy = 0;
		contentPane.add(left, gbc_left);
		
		wallPaper = new JLabel();
		wallPaper.setIcon(new ImageIcon(Main.class.getResource("images/skku_wallpaper.png")));
		left.add(wallPaper);
		
		right = new JPanel();                                     // right panel for remaining components
		right.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_right = new GridBagConstraints();
		gbc_right.gridheight = 2;
		gbc_right.gridwidth = 2;
		gbc_right.fill = GridBagConstraints.BOTH;
		gbc_right.gridx = 1;
		gbc_right.gridy = 0;
		contentPane.add(right, gbc_right);
		GridBagLayout gbl_right = new GridBagLayout();
		gbl_right.columnWidths = new int[] {0};
		gbl_right.rowHeights = new int[] {0};
		gbl_right.columnWeights = new double[]{1.0, 1.0, 0.0};
		gbl_right.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0};
		right.setLayout(gbl_right);
		
		
		                                                                             // all the components with gridbag layout
		titleImg = new JLabel();                                                     // 1 label and 1 textfield
		titleImg.setIcon(new ImageIcon(Main.class.getResource("images/title_label.png")));
		GridBagConstraints gbc_titleImg = new GridBagConstraints();
		gbc_titleImg.insets = new Insets(0, 0, 20, 0);
		gbc_titleImg.gridwidth = 3;
		gbc_titleImg.fill = GridBagConstraints.BOTH;
		gbc_titleImg.gridx = 0;
		gbc_titleImg.gridy = 0;
		right.add(titleImg, gbc_titleImg);
		
		ApplicantNameLabel = new JLabel("Applicant Name: ");
		ApplicantNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_ApplicantNameLabel = new GridBagConstraints();
		gbc_ApplicantNameLabel.insets = new Insets(0, 0, 15, 5);
		gbc_ApplicantNameLabel.fill = GridBagConstraints.BOTH;
		gbc_ApplicantNameLabel.gridx = 0;
		gbc_ApplicantNameLabel.gridy = 1;
		right.add(ApplicantNameLabel, gbc_ApplicantNameLabel);
		
		ApplicantNameTextField = new JTextField();
		GridBagConstraints gbc_ApplicantNameTextField = new GridBagConstraints();
		gbc_ApplicantNameTextField.insets = new Insets(0, 0, 15, 10);
		gbc_ApplicantNameTextField.gridwidth = 2;
		gbc_ApplicantNameTextField.fill = GridBagConstraints.BOTH;
		gbc_ApplicantNameTextField.gridx = 1;
		gbc_ApplicantNameTextField.gridy = 1;
		right.add(ApplicantNameTextField, gbc_ApplicantNameTextField);
		ApplicantNameTextField.setColumns(10);
		
		BirthDateLabel = new JLabel("Birth Date: ");
		BirthDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_BirthDateLabel = new GridBagConstraints();
		gbc_BirthDateLabel.fill = GridBagConstraints.BOTH;
		gbc_BirthDateLabel.insets = new Insets(0, 0, 15, 5);
		gbc_BirthDateLabel.gridx = 0;
		gbc_BirthDateLabel.gridy = 2;
		right.add(BirthDateLabel, gbc_BirthDateLabel);
		
		BirthDateTextField = new JTextField();
		GridBagConstraints gbc_BirthDateTextField = new GridBagConstraints();
		gbc_BirthDateTextField.gridwidth = 2;
		gbc_BirthDateTextField.fill = GridBagConstraints.BOTH;
		gbc_BirthDateTextField.insets = new Insets(0, 0, 15, 10);
		gbc_BirthDateTextField.gridx = 1;
		gbc_BirthDateTextField.gridy = 2;
		right.add(BirthDateTextField, gbc_BirthDateTextField);
		BirthDateTextField.setColumns(10);
		
		EmailLabel = new JLabel("Email: ");
		EmailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_EmailLabel = new GridBagConstraints();
		gbc_EmailLabel.fill = GridBagConstraints.BOTH;
		gbc_EmailLabel.insets = new Insets(0, 0, 15, 5);
		gbc_EmailLabel.gridx = 0;
		gbc_EmailLabel.gridy = 3;
		right.add(EmailLabel, gbc_EmailLabel);
		
		EmailTextField = new JTextField();
		GridBagConstraints gbc_EmailTextField = new GridBagConstraints();
		gbc_EmailTextField.gridwidth = 2;
		gbc_EmailTextField.fill = GridBagConstraints.BOTH;
		gbc_EmailTextField.insets = new Insets(0, 0, 15, 10);
		gbc_EmailTextField.gridx = 1;
		gbc_EmailTextField.gridy = 3;
		right.add(EmailTextField, gbc_EmailTextField);
		EmailTextField.setColumns(10);
		
		DegreeLabel = new JLabel("Degree: ");
		DegreeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_DegreeLabel = new GridBagConstraints();
		gbc_DegreeLabel.fill = GridBagConstraints.BOTH;
		gbc_DegreeLabel.insets = new Insets(0, 0, 15, 5);
		gbc_DegreeLabel.gridx = 0;
		gbc_DegreeLabel.gridy = 4;
		right.add(DegreeLabel, gbc_DegreeLabel);
		
		DegreeTextField = new JTextField();
		GridBagConstraints gbc_DegreeTextField = new GridBagConstraints();
		gbc_DegreeTextField.insets = new Insets(0, 0, 15, 10);
		gbc_DegreeTextField.gridwidth = 2;
		gbc_DegreeTextField.fill = GridBagConstraints.BOTH;
		gbc_DegreeTextField.gridx = 1;
		gbc_DegreeTextField.gridy = 4;
		right.add(DegreeTextField, gbc_DegreeTextField);
		DegreeTextField.setColumns(10);
		
		AttendedUnivLabel = new JLabel("Attended university (for Graduates): ");
		GridBagConstraints gbc_AttendedUnivLabel = new GridBagConstraints();
		gbc_AttendedUnivLabel.anchor = GridBagConstraints.EAST;
		gbc_AttendedUnivLabel.insets = new Insets(0, 0, 15, 5);
		gbc_AttendedUnivLabel.gridx = 0;
		gbc_AttendedUnivLabel.gridy = 5;
		right.add(AttendedUnivLabel, gbc_AttendedUnivLabel);
		
		AttendedUnivTextField = new JTextField();
		GridBagConstraints gbc_AttendedUnivTextField = new GridBagConstraints();
		gbc_AttendedUnivTextField.insets = new Insets(0, 0, 15, 10);
		gbc_AttendedUnivTextField.gridwidth = 2;
		gbc_AttendedUnivTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_AttendedUnivTextField.gridx = 1;
		gbc_AttendedUnivTextField.gridy = 5;
		right.add(AttendedUnivTextField, gbc_AttendedUnivTextField);
		AttendedUnivTextField.setColumns(10);
		
		GpaLabel = new JLabel("GPA (for Graduates): ");
		GridBagConstraints gbc_GpaLabel = new GridBagConstraints();
		gbc_GpaLabel.anchor = GridBagConstraints.EAST;
		gbc_GpaLabel.insets = new Insets(0, 0, 15, 5);
		gbc_GpaLabel.gridx = 0;
		gbc_GpaLabel.gridy = 6;
		right.add(GpaLabel, gbc_GpaLabel);
		
		GpaTextField = new JTextField();
		GridBagConstraints gbc_GpaTextField = new GridBagConstraints();
		gbc_GpaTextField.gridwidth = 2;
		gbc_GpaTextField.insets = new Insets(0, 0, 15, 10);
		gbc_GpaTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_GpaTextField.gridx = 1;
		gbc_GpaTextField.gridy = 6;
		right.add(GpaTextField, gbc_GpaTextField);
		GpaTextField.setColumns(10);
		
		PhoneNumLabel = new JLabel("Phone Number: ");
		GridBagConstraints gbc_PhoneNumLabel = new GridBagConstraints();
		gbc_PhoneNumLabel.anchor = GridBagConstraints.EAST;
		gbc_PhoneNumLabel.insets = new Insets(0, 0, 15, 5);
		gbc_PhoneNumLabel.gridx = 0;
		gbc_PhoneNumLabel.gridy = 7;
		right.add(PhoneNumLabel, gbc_PhoneNumLabel);
		
		PhoneNumTextField = new JTextField();
		GridBagConstraints gbc_PhoneNumTextField = new GridBagConstraints();
		gbc_PhoneNumTextField.gridwidth = 2;
		gbc_PhoneNumTextField.insets = new Insets(0, 0, 15, 10);
		gbc_PhoneNumTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_PhoneNumTextField.gridx = 1;
		gbc_PhoneNumTextField.gridy = 7;
		right.add(PhoneNumTextField, gbc_PhoneNumTextField);
		PhoneNumTextField.setColumns(10);
		
		KakaoIDLabel = new JLabel("KakaoTalk ID: ");
		GridBagConstraints gbc_KakaoIDLabel = new GridBagConstraints();
		gbc_KakaoIDLabel.anchor = GridBagConstraints.EAST;
		gbc_KakaoIDLabel.insets = new Insets(0, 0, 15, 5);
		gbc_KakaoIDLabel.gridx = 0;
		gbc_KakaoIDLabel.gridy = 8;
		right.add(KakaoIDLabel, gbc_KakaoIDLabel);
		
		KakaoIDTextField = new JTextField();
		GridBagConstraints gbc_KakaoIDTextField = new GridBagConstraints();
		gbc_KakaoIDTextField.gridwidth = 2;
		gbc_KakaoIDTextField.insets = new Insets(0, 0, 15, 10);
		gbc_KakaoIDTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_KakaoIDTextField.gridx = 1;
		gbc_KakaoIDTextField.gridy = 8;
		right.add(KakaoIDTextField, gbc_KakaoIDTextField);
		KakaoIDTextField.setColumns(10);
		
		PersonalStmtLabel = new JLabel("Personal Statement");
		GridBagConstraints gbc_PersonalStmtLabel = new GridBagConstraints();
		gbc_PersonalStmtLabel.insets = new Insets(0, 0, 5, 0);
		gbc_PersonalStmtLabel.gridwidth = 3;
		gbc_PersonalStmtLabel.gridx = 0;
		gbc_PersonalStmtLabel.gridy = 9;
		right.add(PersonalStmtLabel, gbc_PersonalStmtLabel);
		
		PersonalStmtTextArea = new JTextArea();                                // use textarea for statement 
		PersonalStmtTextArea.setText("At least 100 words...");
		PersonalStmtTextArea.setToolTipText("");
		PersonalStmtTextArea.setBackground(new Color(222, 223, 239));
		GridBagConstraints gbc_PersonalStmtTextArea = new GridBagConstraints();
		gbc_PersonalStmtTextArea.gridwidth = 3;
		gbc_PersonalStmtTextArea.insets = new Insets(0, 50, 15, 50);
		gbc_PersonalStmtTextArea.fill = GridBagConstraints.BOTH;
		gbc_PersonalStmtTextArea.gridx = 0;
		gbc_PersonalStmtTextArea.gridy = 10;
		right.add(PersonalStmtTextArea, gbc_PersonalStmtTextArea);
		
		HomeAddrLabel = new JLabel("Home Address: ");
		HomeAddrLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_HomeAddrLabel = new GridBagConstraints();
		gbc_HomeAddrLabel.anchor = GridBagConstraints.EAST;
		gbc_HomeAddrLabel.insets = new Insets(0, 0, 15, 5);
		gbc_HomeAddrLabel.gridx = 0;
		gbc_HomeAddrLabel.gridy = 11;
		right.add(HomeAddrLabel, gbc_HomeAddrLabel);
		
		HomeAddrTextField = new JTextField();
		GridBagConstraints gbc_HomeAddrTextField = new GridBagConstraints();
		gbc_HomeAddrTextField.gridwidth = 2;
		gbc_HomeAddrTextField.insets = new Insets(0, 0, 15, 10);
		gbc_HomeAddrTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_HomeAddrTextField.gridx = 1;
		gbc_HomeAddrTextField.gridy = 11;
		right.add(HomeAddrTextField, gbc_HomeAddrTextField);
		HomeAddrTextField.setColumns(10);
		
		SubmitButton = new JButton("Submit Application");                // button to submit
		SubmitButton.setFont(new Font("굴림", Font.BOLD, 12));
		SubmitButton.setForeground(new Color(255, 255, 255));
		SubmitButton.setBackground(new Color(0, 0, 160));
		SubmitButton.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_SubmitButton = new GridBagConstraints();
		gbc_SubmitButton.gridwidth = 2;
		gbc_SubmitButton.insets = new Insets(0, 0, 15, 5);
		gbc_SubmitButton.gridx = 0;
		gbc_SubmitButton.gridy = 12;
		right.add(SubmitButton, gbc_SubmitButton);
		
		SubmitButton.addActionListener(new ActionListener () {          // action listener for submit button
			public void actionPerformed(ActionEvent e) {
				errorList = new ArrayList<>();
				
				                                        // try-catch to deal with exceptions
				try {                               
					checkNameField();
				} catch (ImproperFieldException err) {
					errorList.add(err.getMessage());
				}
				try {
					checkBirthDateField();
				} catch (ImproperFieldException err) {
					errorList.add(err.getMessage());
				}
				try {
					checkEmailField();
				} catch (ImproperFieldException err) {
					errorList.add(err.getMessage());
				}
				try {
					checkDegreeField();
				} catch (ImproperFieldException err) {
					errorList.add(err.getMessage());
				}
				try {
					checkAttendedUnivField();
				} catch (ImproperFieldException err) {
					errorList.add(err.getMessage());
				}
				try {
					checkPhoneNumField();
				} catch (ImproperFieldException err) {
					errorList.add(err.getMessage());
				}
				try {
					checkKakaoIDField();
				} catch (ImproperFieldException err) {
					errorList.add(err.getMessage());
				}
				try {
					checkPersonalStmtField();
				} catch (ImproperFieldException err) {
					errorList.add(err.getMessage());
				}
				try {
					checkHomeAddrField();
				} catch (ImproperFieldException err) {
					errorList.add(err.getMessage());
				}
				
				
				if (errorList.size() == 0) {             // if all problems fixed, clear form and show a success popup
					
					ApplicantNameTextField.setText("");
					BirthDateTextField.setText("");
					EmailTextField.setText("");
					DegreeTextField.setText("");
					AttendedUnivTextField.setText("");
					GpaTextField.setText("");
					PhoneNumTextField.setText("");
					KakaoIDTextField.setText("");
					PersonalStmtTextArea.setText("");
					HomeAddrTextField.setText("");
					
					JOptionPane.showMessageDialog(null, "Successfully Submitted", "Success", JOptionPane.INFORMATION_MESSAGE);
				} else {                                // if problems remain, show popup with problems
					String error = new String("");
					
					for (int i = 0; i < errorList.size(); i++) {
						 error = error + (i + 1) + ". " + errorList.get(i) + "\n";
					}
					
					JOptionPane.showMessageDialog(null, error, "You have following problems", JOptionPane.ERROR_MESSAGE);
					
					error = "";                        // after show popup, clearing the error message and error list
					errorList.clear();
				}
			}        	
	    });
	}
	                                                  // check the condition of each parts, if error detected, throw error message 
	public void checkNameField() throws ImproperFieldException {
		
		String name = ApplicantNameTextField.getText();
		
		if (name.length() == 0) {
			throw new ImproperFieldException("You forgot to fill the name text field, Please fill it.");
		} else if (!name.contains(" ")) {
			throw new ImproperFieldException("You forgot to write your name or surname.");
		}
	}
	public void checkBirthDateField() throws ImproperFieldException {
		                                                          // check the birth date format with using regex
		String birthDate = BirthDateTextField.getText();
		String birthDateFormat = "^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/\\d{4}$"; 
		Pattern pattern = Pattern.compile(birthDateFormat);
		Matcher matcher = pattern.matcher(birthDate);
		
		if (birthDate.length() == 0) {
			throw new ImproperFieldException("You fotgot to fill the birth date text field, Please fill it.");
		} else if (!matcher.matches()) {
			throw new ImproperFieldException("Birthdate must be in '06/06/1995' format.");
		}
	}
	public void checkEmailField() throws ImproperFieldException {
                                                                  // check the email format with using regex
		String email = EmailTextField.getText();
		String emailFormat = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(emailFormat);
		Matcher matcher = pattern.matcher(email);
		
		if (email.length() == 0) {
			throw new ImproperFieldException("You fotgot to fill the email text field, Please fill it.");
		} else if (!matcher.matches()) {
			throw new ImproperFieldException("Email must be in example@some.some.");
		}
	}
	public void checkDegreeField() throws ImproperFieldException {
		
		String degree = DegreeTextField.getText();
		
		if (degree.length() == 0) {
			throw new ImproperFieldException("You fotgot to fill the degree text field, Please fill it.");
		}
	}
	public void checkAttendedUnivField() throws ImproperFieldException {
		
		String degree = DegreeTextField.getText();
		String attendedUniv = AttendedUnivTextField.getText();
		String gpa = GpaTextField.getText();
		
		if (degree.equals("PhD")) {     // if degree is PhD, attended university and gpa are required
			if (attendedUniv.length() == 0 || gpa.length() == 0) {
				throw new ImproperFieldException("For graduate, you have to enter previous university and GPA");
			} else if (Double.parseDouble(gpa) < 0 || Double.parseDouble(gpa) > 4.5) {
				throw new ImproperFieldException("GPA must be between 0 and 4.5");
			}
		}
	}
	public void checkPhoneNumField() throws ImproperFieldException {
                                                                // check the phone number format with using regex
		String phoneNum = PhoneNumTextField.getText();
		String phoneNumFormat = "^\\d{2}\\s\\d{4}-\\d{4}$";
		Pattern pattern = Pattern.compile(phoneNumFormat);
		Matcher matcher = pattern.matcher(phoneNum);
		
		if (phoneNum.length() == 0) {
			throw new ImproperFieldException("You fotgot to fill the phone number text field, Please fill it.");
		} else if (!matcher.matches()) {
			throw new ImproperFieldException("Proper format for a phone number is '10 2158-0222'.");
		}
	}
	public void checkKakaoIDField() throws ImproperFieldException {
                                                               // check the kakao talk ID format with using regex
		String kakaoID = KakaoIDTextField.getText();
		String kakaoIDFormat = "^[a-z][a-z0-9_-]{1,18}[a-z0-9]$";
		Pattern pattern = Pattern.compile(kakaoIDFormat);
		Matcher matcher = pattern.matcher(kakaoID);
		
		if (kakaoID.length() == 0) {
			throw new ImproperFieldException("You fotgot to fill the kakao talk ID text field, Please fill it.");
		} else if (!matcher.matches()) {
			throw new ImproperFieldException("Proper format for a kakao ID is 'example123'.");
		}
	}
	public void checkPersonalStmtField() throws ImproperFieldException {
																// remove whitespaces and check the length of statement
		String personalStmt = PersonalStmtTextArea.getText();
		String words = personalStmt.trim().replaceAll("\\s+", "");
		
		if (personalStmt.length() == 0) {
			throw new ImproperFieldException("You fotgot to fill the personal statement text area, Please fill it.");
		} else if (words.length() < 100 ) {
			throw new ImproperFieldException("Your Personal Statement must be at least 100 words.");
		}
	}
	public void checkHomeAddrField() throws ImproperFieldException {
                                                                // check the home address format with using regex
		String homeAddr = HomeAddrTextField.getText();
		String homeAddrFormat = "^[^,]*,[^,]*,[^,]*,[^,]*$";
		Pattern pattern = Pattern.compile(homeAddrFormat);
		Matcher matcher = pattern.matcher(homeAddr);
		
		if (homeAddr.length() == 0) {
			throw new ImproperFieldException("You fotgot to fill the home address text area, Please fill it.");
		}  else if (!matcher.matches()) {
			throw new ImproperFieldException("Your address must be in 'number, street, district, city' format.");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
			
class ImproperFieldException extends RuntimeException {
												// user-defined error class
												// store the error message to passed as a parameter
												// and return it through getMessage() function
	private String message;
	
	public ImproperFieldException (String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
}


