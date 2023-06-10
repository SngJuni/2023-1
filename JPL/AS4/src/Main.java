import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.SwingWorker;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JPanel pnInput;
	private JPanel pnStatus;
	private JLabel TitleLabel;
	private JTextField InputTextField;
	private JButton StartButton;
	private JButton CancelButton;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JProgressBar progressBar;
	private JLabel StatusLabel;
	
	int [] numList = new int[40]; // list for fibonacci numbers
	int sum = 0;                  // variable for sum of fibonacci numbers
	int progress = 0;             // variable to store the progress percentage

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


	public Main() {                                        // all the components for the program
		setTitle("Finding Fibonacci series sum");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		pnInput = new JPanel();
		contentPane.add(pnInput, BorderLayout.NORTH);
		pnInput.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		TitleLabel = new JLabel("Number of row in Fibonacci Series: ");
		pnInput.add(TitleLabel);
		
		InputTextField = new JTextField();
		pnInput.add(InputTextField);
		InputTextField.setColumns(10);
		
		StartButton = new JButton("Get Sum of Fibonacci series");
		StartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				progressBar.setValue(0);
				textArea.setText("");
				StatusLabel.setText("");
				printFibo();
				printSum();
			}
		});
		pnInput.add(StartButton);
		
		CancelButton = new JButton("Cancel");
		pnInput.add(CancelButton);
		
		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		pnStatus = new JPanel();
		contentPane.add(pnStatus, BorderLayout.SOUTH);
		pnStatus.setLayout(new GridLayout(0, 2, 0, 0));
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		pnStatus.add(progressBar);
		
		StatusLabel = new JLabel("");
		pnStatus.add(StatusLabel);
	}
	
	public int fibonacci(int num) { // to calculate fibonacci number and return the results
		if (num == 1 || num == 2) {
			return 1;
		}
		
		return fibonacci(num - 1) + fibonacci(num - 2);
	}
	
	private void printFibo() {  // print fibo num to textarea and write the result to txt file
		
		try {                   // to handle the wrong input exception
			int InputNum = Integer.parseInt(InputTextField.getText());
		} catch (Exception err) {
			JOptionPane.showMessageDialog(null, "Enter valid input!", "Warning", JOptionPane.INFORMATION_MESSAGE);
		}
		
		int InputNum = Integer.parseInt(InputTextField.getText());
		
		
		// Thread 1
		SwingWorker<Boolean, Integer> worker1 = new SwingWorker<Boolean, Integer>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				CancelButton.setEnabled(true);
				StartButton.setEnabled(false);
				
				
				numList[0] = 0;
				textArea.append(numList[0] + "\n");
				
				for (int i = 1; i < InputNum; i++) { // calculate the fibonacci num, save to list, and publish i
					Thread.sleep(100);
					numList[i] = fibonacci(i);
					
					publish(i);
				}
				
				return false;
			}
			
			@Override
			protected void process(List<Integer> chunks) { // update GUI in the middle of the background job
				Integer numIdx = chunks.get(chunks.size() - 1);
				
				textArea.append(numList[numIdx] + "\n");
				progress = (numIdx) * 100 / InputNum;
				progressBar.setValue(progress);
				
			}
			
			@Override
			protected void done() { // update GUI and write the results to txt file when the program is done
				CancelButton.setEnabled(false);
				StartButton.setEnabled(true);
				
				try {
					FileOutputStream fileObject = new FileOutputStream("data.txt", false);
					PrintWriter x = new PrintWriter(fileObject);
					
					x.print("[" + numList[0] + ", ");
					
					for (int i = 1; i < InputNum - 1; i++) {
						x.print(numList[i] + ", ");
					}
					x.print(numList[InputNum - 1] + "]");
					x.close();
					
					progressBar.setValue(100);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		};
		worker1.execute();
		
		CancelButton.addActionListener(new ActionListener() { // if cancel button clicked, cancel the thread and set progress
			public void actionPerformed(ActionEvent e) {
				worker1.cancel(true);
				progressBar.setValue(progress);
			}
		});
		
	}
	
	public void printSum() {
		
		int InputNum = Integer.parseInt(InputTextField.getText());
		
		// Thread 2
		SwingWorker<Boolean, Integer> worker2 = new SwingWorker<Boolean, Integer>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				StatusLabel.setText("");
				sum = 0;
				
				for (int i = 1; i < InputNum; i++) { // calculate the sum of fibonacci numbers
					Thread.sleep(100);
					sum += fibonacci(i); 
					
					publish(sum);
				}
				
				return false;
			}
			
			@Override
			protected void done() {
				StatusLabel.setText("Sum = " + sum);
			}

		};
		worker2.execute();
		
		CancelButton.addActionListener(new ActionListener() { // if cancel button clicked, cancel the thread
			public void actionPerformed(ActionEvent e) {
				worker2.cancel(true);
			}
		});
	}
}
