import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CheckReservation extends JFrame {

	private JPanel contentPane;
	private JLabel wallpaperLabel;
	private JLabel titleLabel;
	private JPanel panel;
	private JPanel buttonPanel;
	private JButton okButton;
	private JLabel eventLabel;
	private JLabel checkEventLabel;
	private JLabel dateLabel;
	private JLabel checkDateLabel;
	private JLabel matchLabel;
	private JLabel checkMatchLabel;
	private JLabel etcLabel;
	private JLabel checkEtcLabel;

	public CheckReservation(User user, Reservation reservation) { // java swing components for page layout
		setTitle("Check Reservation");
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
		wallpaperLabel.setIcon(new ImageIcon(CheckReservation.class.getResource("img/wallpaper.png")));
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
		panel.setBackground(new Color(50, 83, 45));
		panel.setBounds(15, 100, 250, 200);
		layeredPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {138, 138};
		gbl_panel.rowHeights = new int[] {30, 30, 30, 30};
		gbl_panel.columnWeights = new double[]{1.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		eventLabel = new JLabel("Event :");
		eventLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		eventLabel.setForeground(new Color(255, 255, 255));
		eventLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 20));
		eventLabel.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_eventLabel = new GridBagConstraints();
		gbc_eventLabel.fill = GridBagConstraints.BOTH;
		gbc_eventLabel.insets = new Insets(0, 0, 5, 5);
		gbc_eventLabel.gridx = 0;
		gbc_eventLabel.gridy = 0;
		panel.add(eventLabel, gbc_eventLabel);
		
		checkEventLabel = new JLabel(reservation.getEvent()); // load the information of reservation with using getter method
		checkEventLabel.setForeground(new Color(255, 255, 255));
		checkEventLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 20));
		GridBagConstraints gbc_checkEventLabel = new GridBagConstraints();
		gbc_checkEventLabel.insets = new Insets(0, 0, 5, 0);
		gbc_checkEventLabel.gridx = 1;
		gbc_checkEventLabel.gridy = 0;
		panel.add(checkEventLabel, gbc_checkEventLabel);
		
		dateLabel = new JLabel("Date :");
		dateLabel.setForeground(new Color(255, 255, 255));
		dateLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 20));
		dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_dateLabel = new GridBagConstraints();
		gbc_dateLabel.fill = GridBagConstraints.BOTH;
		gbc_dateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_dateLabel.gridx = 0;
		gbc_dateLabel.gridy = 1;
		panel.add(dateLabel, gbc_dateLabel);
		
		checkDateLabel = new JLabel(reservation.getDate()); // load the information of reservation with using getter method
		checkDateLabel.setForeground(new Color(255, 255, 255));
		checkDateLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 18));
		GridBagConstraints gbc_checkDateLabel = new GridBagConstraints();
		gbc_checkDateLabel.insets = new Insets(0, 0, 5, 0);
		gbc_checkDateLabel.gridx = 1;
		gbc_checkDateLabel.gridy = 1;
		panel.add(checkDateLabel, gbc_checkDateLabel);
		
		matchLabel = new JLabel("Match :");
		matchLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		matchLabel.setForeground(new Color(255, 255, 255));
		matchLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 20));
		GridBagConstraints gbc_matchLabel = new GridBagConstraints();
		gbc_matchLabel.fill = GridBagConstraints.BOTH;
		gbc_matchLabel.insets = new Insets(0, 0, 5, 5);
		gbc_matchLabel.gridx = 0;
		gbc_matchLabel.gridy = 2;
		panel.add(matchLabel, gbc_matchLabel);
		
		checkMatchLabel = new JLabel(reservation.getMatch()); // load the information of reservation with using getter method
		checkMatchLabel.setForeground(new Color(255, 255, 255));
		checkMatchLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 18));
		GridBagConstraints gbc_checkMatchLabel = new GridBagConstraints();
		gbc_checkMatchLabel.insets = new Insets(0, 0, 5, 0);
		gbc_checkMatchLabel.gridx = 1;
		gbc_checkMatchLabel.gridy = 2;
		panel.add(checkMatchLabel, gbc_checkMatchLabel);
		
		etcLabel = new JLabel("Etc :");
		etcLabel.setForeground(new Color(255, 255, 255));
		etcLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 20));
		etcLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_etcLabel = new GridBagConstraints();
		gbc_etcLabel.fill = GridBagConstraints.BOTH;
		gbc_etcLabel.insets = new Insets(0, 0, 5, 5);
		gbc_etcLabel.gridx = 0;
		gbc_etcLabel.gridy = 3;
		panel.add(etcLabel, gbc_etcLabel);
		
		checkEtcLabel = new JLabel(reservation.getEtc()); // load the information of reservation with using getter method
		checkEtcLabel.setForeground(new Color(255, 255, 255));
		checkEtcLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 20));
		GridBagConstraints gbc_checkEtcLabel = new GridBagConstraints();
		gbc_checkEtcLabel.insets = new Insets(0, 0, 5, 0);
		gbc_checkEtcLabel.gridx = 1;
		gbc_checkEtcLabel.gridy = 3;
		panel.add(checkEtcLabel, gbc_checkEtcLabel);
		
		buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setBounds(0, 360, 300, 100);
		layeredPane.add(buttonPanel);
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[]{276, 0};
		gbl_buttonPanel.rowHeights = new int[] {30, 30, 0};
		gbl_buttonPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_buttonPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		buttonPanel.setLayout(gbl_buttonPanel);
		
		okButton = new JButton("OK");
		okButton.setVerticalAlignment(SwingConstants.BOTTOM);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // close the window
				dispose();
			}
		});
		okButton.setForeground(new Color(255, 255, 255));
		okButton.setBackground(new Color(50, 83, 45));
		okButton.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 20));
		GridBagConstraints gbc_okButton = new GridBagConstraints();
		gbc_okButton.insets = new Insets(10, 10, 5, 10);
		gbc_okButton.fill = GridBagConstraints.BOTH;
		gbc_okButton.gridx = 0;
		gbc_okButton.gridy = 0;
		buttonPanel.add(okButton, gbc_okButton);
	}
}
