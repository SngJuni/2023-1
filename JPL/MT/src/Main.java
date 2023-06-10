import javax.swing.*;
import javax.swing.border.EmptyBorder;


import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.EventQueue;
import java.util.ArrayList;

public class Main extends JFrame implements ActionListener {
	
	private static int state = 0;     // variable for storing the state
	private static int userIdx = 0;   // variable for storing user index
	private static int targetIdx = 0; // variable for storing target user index of transfer
	
	// initialize the elements of application
	private JPanel contentPane = new JPanel();
	private JLabel woori = new JLabel();
    private JButton btn_op1 = new JButton();
    private JButton btn_op2 = new JButton();
    private JButton btn_op3 = new JButton();
    private JButton btn_op4 = new JButton();
    private JLabel SKKU_ATM = new JLabel();
    private JTextArea textarea = new JTextArea();
    private JButton btn_eng = new JButton();
    private JButton btn_kor = new JButton();
    private JButton btn_1 = new JButton();
    private JButton btn_2 = new JButton();
    private JButton btn_3 = new JButton();
    private JButton btn_4 = new JButton();
    private JButton btn_5 = new JButton();
    private JButton btn_6 = new JButton();
    private JButton btn_7 = new JButton();
    private JButton btn_8 = new JButton();
    private JButton btn_9 = new JButton();
    private JButton btn_0 = new JButton();
    private JButton btn_cancel = new JButton();
    private JButton btn_clear = new JButton();
    private JButton btn_enter = new JButton();


	public Main() {
		
	    ArrayList<BankAccount> bankAccounts = new ArrayList<>(); // use arralist and initialize users' information
	    User user1 = new User("Firuz");
	    User user2 = new User("John");
	    User user3 = new User("Eldor");

	    bankAccounts.add(new BankAccount("200100237898", 1234, 500000.0, user1));
	    bankAccounts.add(new BankAccount("110000022033", 4321, 700000.0, user2));
	    bankAccounts.add(new BankAccount("111111111111", 2222, 900000.0, user3));

        setSize(600, 600); // == super.serSize, set the frame size
        setTitle("ATM");   // set the title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set default close option
        
        setContentPane(contentPane);
        
        GridBagLayout grid = new GridBagLayout();          // use gridbaglayout to allocate elements
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);
        contentPane.setLayout(grid);                      // set the layout to grid
		
        
        // set the icons and button and locate the correct position to use grid option 
        woori.setIcon(new ImageIcon(Main.class.getResource("icons/woori.png")));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPane.add(woori, gbc);
        
        btn_op1.setIcon(new ImageIcon(Main.class.getResource("icons/arr.png")));
        btn_op1.setText("OPTION 1");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        contentPane.add(btn_op1, gbc);
        
        btn_op2.setIcon(new ImageIcon(Main.class.getResource("icons/arr.png")));
        btn_op2.setText("OPTION 2");
        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPane.add(btn_op2, gbc);
        
        btn_op3.setIcon(new ImageIcon(Main.class.getResource("icons/arr.png")));
        btn_op3.setText("OPTION 3");
        gbc.gridx = 0;
        gbc.gridy = 4;
        contentPane.add(btn_op3, gbc);
        
        btn_op4.setIcon(new ImageIcon(Main.class.getResource("icons/arr.png")));
        btn_op4.setText("OPTION 4");
        gbc.gridx = 0;
        gbc.gridy = 5;
        contentPane.add(btn_op4, gbc);
   
        SKKU_ATM.setText("SKKU ATM");
        gbc.gridx = 2;
        gbc.gridy = 1;
        contentPane.add(SKKU_ATM, gbc);
        
        textarea.setColumns(30); // textarea to input the value
        textarea.setRows(5);
        textarea.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.gridheight = 4;
        contentPane.add(textarea, gbc);
        
        btn_eng.setIcon(new ImageIcon(Main.class.getResource("icons/eng.png")));
        btn_eng.setText("ENGLISH");
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        contentPane.add(btn_eng, gbc);
        
        btn_kor.setIcon(new ImageIcon(Main.class.getResource("icons/kor.png")));
        btn_kor.setText("KOREAN");
        gbc.gridx = 4;
        gbc.gridy = 3;
        contentPane.add(btn_kor, gbc);
        
        btn_1.setIcon(new ImageIcon(Main.class.getResource("icons/1.png")));
        gbc.gridx = 1;
        gbc.gridy = 6;
        contentPane.add(btn_1, gbc);
        
        btn_2.setIcon(new ImageIcon(Main.class.getResource("icons/2.png")));
        gbc.gridx = 2;
        gbc.gridy = 6;
        contentPane.add(btn_2, gbc);
        
        btn_3.setIcon(new ImageIcon(Main.class.getResource("icons/3.png")));
        gbc.gridx = 3;
        gbc.gridy = 6;
        contentPane.add(btn_3, gbc);
        
        btn_4.setIcon(new ImageIcon(Main.class.getResource("icons/4.png")));
        gbc.gridx = 1;
        gbc.gridy = 7;
        contentPane.add(btn_4, gbc);
        
        btn_5.setIcon(new ImageIcon(Main.class.getResource("icons/5.png")));
        gbc.gridx = 2;
        gbc.gridy = 7;
        contentPane.add(btn_5, gbc);
        
        btn_6.setIcon(new ImageIcon(Main.class.getResource("icons/6.png")));
        gbc.gridx = 3;
        gbc.gridy = 7;
        contentPane.add(btn_6, gbc);
        
        btn_7.setIcon(new ImageIcon(Main.class.getResource("icons/7.png")));
        gbc.gridx = 1;
        gbc.gridy = 8;
        contentPane.add(btn_7, gbc);
        
        btn_8.setIcon(new ImageIcon(Main.class.getResource("icons/8.png")));
        gbc.gridx = 2;
        gbc.gridy = 8;
        contentPane.add(btn_8, gbc);
        
        btn_9.setIcon(new ImageIcon(Main.class.getResource("icons/9.png")));
        gbc.gridx = 3;
        gbc.gridy = 8;
        contentPane.add(btn_9, gbc);
        
        btn_0.setIcon(new ImageIcon(Main.class.getResource("icons/0.png")));
        gbc.gridx = 2;
        gbc.gridy = 9;
        contentPane.add(btn_0, gbc);

        btn_cancel.setIcon(new ImageIcon(Main.class.getResource("icons/cancel.png")));
        btn_cancel.setText("CANCEL");
        gbc.gridx = 4;
        gbc.gridy = 6;
        contentPane.add(btn_cancel, gbc);
        
        btn_clear.setIcon(new ImageIcon(Main.class.getResource("icons/clear.png")));
        btn_clear.setText("CLEAR");
        gbc.gridx = 4;
        gbc.gridy = 7;
        contentPane.add(btn_clear, gbc);
        
        btn_enter.setIcon(new ImageIcon(Main.class.getResource("icons/enter.png")));
        btn_enter.setText("ENTER");
        gbc.gridx = 4;
        gbc.gridy = 8;
        contentPane.add(btn_enter, gbc);
        

        
        // at first, initialize the textarea
        if (state == 0) {
        	textarea.setText("Please, insert your card and press ENTER...");
        }
        
        
        
        // add action listener to button
        
        // state 0 -> at first, before insert card(press enter)
        // state 1 -> input PIN
        // state 2 -> login success, select option
        // state 3 -> login failure
        // state 4 -> check the current balance
        // state 5 -> input withdraw
        // state 6 -> input diposit
        // state 7 -> input the target user's accouunt num for transfer
        // state 8 -> input transfer       
        
        btn_op1.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if (state == 2) {
	    			textarea.setText("User: " + bankAccounts.get(userIdx).getBankUser().getName() + "\n" +
	    							"Balance: " + String.format("%.2f", bankAccounts.get(userIdx).getBalance()) + "\n" +
	    							"Press Enter...");
	    			state = 4;
				}
			}        	
        });
        btn_op2.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if (state == 2) {
					textarea.setText("Enter Withdrawal Amount: ");
					state = 5;
				}
			}        	
        });
        btn_op3.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if (state == 2) {
	    			textarea.setText("Enter Deposit Amount: ");
	    			state = 6;
				}
			}        	
        });
        btn_op4.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if (state == 2) {
	    			textarea.setText("Enter Account Number (Receiver): ");
	    			state = 7;
				}
			}        	
        });
        btn_1.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if (state == 1 | state == 3 | state == 5 | state == 6 | state == 7 | state == 8) {
	    			String temp = textarea.getText() + "1";
	    			textarea.setText(temp);
				}
			}        	
        });
        btn_2.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if (state == 1 | state == 3 | state == 5 | state == 6 | state == 7 | state == 8) {
	    			String temp = textarea.getText() + "2";
	    			textarea.setText(temp);
				}
			}        	
        });
        btn_3.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if (state == 1 | state == 3 | state == 5 | state == 6 | state == 7 | state == 8) {
	    			String temp = textarea.getText() + "3";
	    			textarea.setText(temp);
				}
			}        	
        });
        btn_4.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if (state == 1 | state == 3 | state == 5 | state == 6 | state == 7 | state == 8) {
	    			String temp = textarea.getText() + "4";
	    			textarea.setText(temp);
				}
			}        	
        });
        btn_5.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if (state == 1 | state == 3 | state == 5 | state == 6 | state == 7 | state == 8) {
	    			String temp = textarea.getText() + "5";
	    			textarea.setText(temp);
				}
			}        	
        });
        btn_6.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if (state == 1 | state == 3 | state == 5 | state == 6 | state == 7 | state == 8) {
	    			String temp = textarea.getText() + "6";
	    			textarea.setText(temp);
				}
			}        	
        });
        btn_7.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if (state == 1 | state == 3 | state == 5 | state == 6 | state == 7 | state == 8) {
	    			String temp = textarea.getText() + "7";
	    			textarea.setText(temp);
				}
			}        	
        });
        btn_8.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if (state == 1 | state == 3 | state == 5 | state == 6 | state == 7 | state == 8) {
	    			String temp = textarea.getText() + "8";
	    			textarea.setText(temp);
				}
			}        	
        });
        btn_9.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if (state == 1 | state == 3 | state == 5 | state == 6 | state == 7 | state == 8) {
	    			String temp = textarea.getText() + "9";
	    			textarea.setText(temp);
				}
			}        	
        });
        btn_0.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if (state == 1 | state == 3 | state == 5 | state == 6 | state == 7 | state == 8) {
	    			String temp = textarea.getText() + "0";
	    			textarea.setText(temp);
				}
			}        	
        });
        btn_cancel.addActionListener(new ActionListener () { // at any time except for state 0, can cancel
        	public void actionPerformed(ActionEvent e) {
        		if (state != 0) {
        			textarea.setText("Process is canceled bu user! Please press ENTER...");
        		}
        		state = 0;
        	}
        });
        btn_clear.addActionListener(new ActionListener () { // if we can input the value, can clear just input value
        	public void actionPerformed(ActionEvent e) {
        		if (state == 1) {
        			textarea.setText("PIN: ");
        		} else if (state == 3) {
        			textarea.setText("Wrong pin! Try Again:\nPIN:");
        		} else if (state == 5) {
        			textarea.setText("Enter Withdrawal Amount: ");
        		} else if (state == 6) {
        			textarea.setText("Enter Deposit Amount: ");
        		} else if (state == 7) {
        			textarea.setText("Enter Account Number (Receiver): ");
        		} else if (state == 8) {
        			textarea.setText("Transfer Account: " + bankAccounts.get(targetIdx).getBankUser().getName() + "\n" +
							"Enter Transfer Amount: ");
        		}
        	}
        }); 
        btn_enter.addActionListener(new ActionListener () {
        	public void actionPerformed(ActionEvent e) {
    			if (state == 0) {
    				textarea.setText("PIN: ");
    				state = 1;
    			} else if (state == 1 | state == 3) {
    				String temp = textarea.getText();
    				int pin = Integer.parseInt(temp.replaceAll("[^0-9]", "")); // except for digit, replace all the string to whitespace
    				
    				for (int i = 0; i < 3; i++) { // get bank account pin while iterate the arraylist 
    					if (pin == bankAccounts.get(i).getPinCode()) {
    						textarea.setText("Welcome " + bankAccounts.get(i).getBankUser().getName() + "\n" +
    								"Please choose options:\n" +
    								"OPTION 1: Balance Checking\n" +
    								"OPTION 2: Withdrawing money\n" +
    								"OPTION 3: Deposit\n" +
    								"OPTION 4: Transfer");
    						userIdx = i;
        					state = 2;
        					break;
    					} else if (i == 2) {  // when terminated the iteration and i == 2, there isn't user having the pin 
    						textarea.setText("Wrong pin! Try Again:\nPIN:");
    						state = 3;
    					}
    				}
    			} else if (state == 4) {
    				textarea.setText("Thank you for banking with us!\npress ENTER...");
    				state = 0;
    			} else if (state == 5) {
    				String temp = textarea.getText();
    				double withdraw = Double.parseDouble(temp.replaceAll("[^0-9]", ""));
    				
    				if (withdraw <= bankAccounts.get(userIdx).getBalance()) { // if balance is more than withdraw, decrease the balance
    					bankAccounts.get(userIdx).setBalance(bankAccounts.get(userIdx).getBalance() - withdraw);
    					textarea.setText("Success:)\nUser: " + bankAccounts.get(userIdx).getBankUser().getName() + "\n" +
    								"Withdrawal Amount: " + String.format("%.2f", withdraw) + "\n" +
    								"Current Balance: " + String.format("%.2f", bankAccounts.get(userIdx).getBalance()) + "\n" +
    								"Press ENTER...");
    					state = 4;
    				} else {
    					textarea.setText("Not enough money!\nPress ENTER...");
    					state = 0;
    				}
    			} else if (state == 6) {
    				String temp = textarea.getText();
    				double deposit = Double.parseDouble(temp.replaceAll("[^0-9]", ""));  // increase the balance
    				bankAccounts.get(userIdx).setBalance(bankAccounts.get(userIdx).getBalance() + deposit);
    				
    				textarea.setText("Success:)\nUser: " + bankAccounts.get(userIdx).getBankUser().getName() + "\n" +
    								"Deposit Amount: " + String.format("%.2f", deposit) + "\n" +
    								"Current Balance: " + String.format("%.2f", bankAccounts.get(userIdx).getBalance()) + "\n" +
    								"Press ENTER...");
    				state = 4;
    			} else if (state == 7) {
    				String temp = textarea.getText();
    				String accNum = temp.substring(temp.length() - 12, temp.length());
    				
    				for (int i = 0; i < 3; i++) { // get the account num of bank user and transfer amount
    					if (accNum.equals(bankAccounts.get(i).getBankNumber())) {
    						textarea.setText("Transfer Account: " + bankAccounts.get(i).getBankUser().getName() + "\n" +
    										"Enter Transfer Amount: ");
    						state = 8;
    						targetIdx = i;
    					} else if (i == 2) { // when terminated the iteration and i == 2, there isn't user having the account num
    						textarea.setText("You entered the wrong account number!\npress ENTER...");
    						state = 0;
    					}
    				}
    			} else if (state == 8) {
    				String temp = textarea.getText();
    				double transfer= Double.parseDouble(temp.replaceAll("[^0-9]", ""));
    				
    				if (transfer <= bankAccounts.get(userIdx).getBalance()) { // if balance is more than transfer, decrease the balance
    					bankAccounts.get(userIdx).setBalance(bankAccounts.get(userIdx).getBalance() - transfer);
    					bankAccounts.get(targetIdx).setBalance(bankAccounts.get(targetIdx).getBalance() + transfer);
    					
    					textarea.setText("Transfer Amount: " + String.format("%.2f", transfer) + "\n" +
    									"Current Balance: " + String.format("%.2f", bankAccounts.get(userIdx).getBalance()) + "\n" +
    									"Press ENTER...");
    					state = 0;
    				} else {
    					textarea.setText("Not enough money!\nPress ENTER...");
    					state = 0;
    				}
    			}
        	}
        });
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