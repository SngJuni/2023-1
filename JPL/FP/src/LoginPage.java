import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JLabel wallpaperLabel;
	private JLabel titleLabel;
	private JPanel panel;
	private JLabel IDLabel;
	private JTextField IDtextField;
	private JLabel PWLabel;
	private JPanel buttonPanel;
	private JButton loginButton;
	private JButton signupButton;
	private JPasswordField PWField;
	private List<String> errorList; // the list for error to deal with error message
	private int flag = 0;           // the flag to deal with authenticating

	public LoginPage() {            // java swing components for page layout
		setTitle("Login");
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
		wallpaperLabel.setIcon(new ImageIcon(LoginPage.class.getResource("img/wallpaper.png")));
		wallpaperLabel.setBounds(-7, -10, 300, 470);
		layeredPane.add(wallpaperLabel, Integer.valueOf(Integer.MIN_VALUE));
		
		titleLabel = new JLabel("Login Page");
		titleLabel.setBackground(new Color(255, 255, 255));
		titleLabel.setForeground(new Color(50, 83, 45));
		titleLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 30));
		titleLabel.setVerticalAlignment(SwingConstants.TOP);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(0, 10, 300, 50);
		layeredPane.add(titleLabel);
		
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 75, 275, 200);
		layeredPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{276, 0};
		gbl_panel.rowHeights = new int[] {30, 30, 30, 30, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
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
		gbc_IDtextField.ipady = 10;
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
		gbc_PWField.ipady = 10;
		gbc_PWField.insets = new Insets(0, 10, 0, 10);
		gbc_PWField.fill = GridBagConstraints.HORIZONTAL;
		gbc_PWField.gridx = 0;
		gbc_PWField.gridy = 3;
		panel.add(PWField, gbc_PWField);
		
		buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setBounds(0, 300, 300, 100);
		layeredPane.add(buttonPanel);
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[]{276, 0};
		gbl_buttonPanel.rowHeights = new int[] {30, 30, 0};
		gbl_buttonPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_buttonPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		buttonPanel.setLayout(gbl_buttonPanel);
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errorList = new ArrayList<>();

				String id = IDtextField.getText();
				char[] pw = PWField.getPassword();
				String pwStr = new String(pw);
				String phoneNum = "";
				
				// check id, password field and if there are some error, throw exception and add error message
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
				
				// if there isn't error, read userDB text file and check id, password with using flag
				if (errorList.size() == 0) {
					try {
						FileInputStream fileReader = new FileInputStream("userDB.txt");
						Scanner buffer = new Scanner(fileReader);

						String line = buffer.nextLine();
						if (line.equals(id)) {
							flag++;
						}
						line = buffer.nextLine();
						if (line.equals(pwStr)) {
							flag++;
						}
						line = buffer.nextLine();
						phoneNum = line;
						
						buffer.close();
						fileReader.close();

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
					return;
				}
				
				// if id and password match db's id and password, make user and open reservation form while passing user
				if (flag == 2) {
					User user = new User(id, pwStr, phoneNum, null);
					
					ReservationForm reservationForm = new ReservationForm(user);
					reservationForm.setVisible(true);
					dispose();
				} else { // if id and password do not match db's id and password, display error message window
					flag = 0;
					
					JOptionPane.showMessageDialog(LoginPage.this, "Please check your ID or password", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBackground(new Color(50, 83, 45));
		loginButton.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 20));
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.insets = new Insets(10, 10, 5, 10);
		gbc_loginButton.fill = GridBagConstraints.BOTH;
		gbc_loginButton.gridx = 0;
		gbc_loginButton.gridy = 0;
		buttonPanel.add(loginButton, gbc_loginButton);
		
		signupButton = new JButton("Sign Up");
		signupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignupPage signupPage = new SignupPage(); // open signup page
				signupPage.setVisible(true);
				dispose();
			}
		});
		signupButton.setForeground(new Color(255, 255, 255));
		signupButton.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 20));
		signupButton.setBackground(new Color(50, 83, 45));
		GridBagConstraints gbc_signupButton = new GridBagConstraints();
		gbc_signupButton.insets = new Insets(5, 10, 10, 10);
		gbc_signupButton.fill = GridBagConstraints.BOTH;
		gbc_signupButton.gridx = 0;
		gbc_signupButton.gridy = 1;
		buttonPanel.add(signupButton, gbc_signupButton);
	}

	// if id and password field are empty, throw error message.
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
}
