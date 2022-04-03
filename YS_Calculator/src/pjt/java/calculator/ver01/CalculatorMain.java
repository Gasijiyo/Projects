package pjt.java.calculator.ver01;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class CalculatorMain {

	private JFrame frame;
	private JTextField display;
	
	double firstNum;
	double secondNum;
	double result;
	String operations;
	String answer;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculatorMain window = new CalculatorMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CalculatorMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 279, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		display = new JTextField();
		display.setHorizontalAlignment(SwingConstants.RIGHT);
		display.setFont(new Font("D2Coding", Font.BOLD, 18));
		display.setBounds(12, 10, 236, 60);
		frame.getContentPane().add(display);
		display.setColumns(10);
		
		JButton btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterNumber = display.getText() + btn7.getText(); 
				display.setText(enterNumber);
			}
		});
		btn7.setFont(new Font("D2Coding", Font.PLAIN, 18));
		btn7.setBounds(12, 146, 50, 50);
		frame.getContentPane().add(btn7);
		
		JButton btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterNumber = display.getText() + btn8.getText(); 
				display.setText(enterNumber);
			}
		});
		btn8.setFont(new Font("D2Coding", Font.PLAIN, 18));
		btn8.setBounds(74, 146, 50, 50);
		frame.getContentPane().add(btn8);
		
		JButton btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterNumber = display.getText() + btn9.getText(); 
				display.setText(enterNumber);
			}
		});
		btn9.setFont(new Font("D2Coding", Font.PLAIN, 18));
		btn9.setBounds(136, 146, 50, 50);
		frame.getContentPane().add(btn9);
		
		JButton btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterNumber = display.getText() + btn4.getText(); 
				display.setText(enterNumber);
			}
		});
		btn4.setFont(new Font("D2Coding", Font.PLAIN, 18));
		btn4.setBounds(12, 206, 50, 50);
		frame.getContentPane().add(btn4);
		
		JButton btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterNumber = display.getText() + btn5.getText(); 
				display.setText(enterNumber);
			}
		});
		btn5.setFont(new Font("D2Coding", Font.PLAIN, 18));
		btn5.setBounds(74, 206, 50, 50);
		frame.getContentPane().add(btn5);
		
		JButton btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterNumber = display.getText() + btn6.getText(); 
				display.setText(enterNumber);
			}
		});
		btn6.setFont(new Font("D2Coding", Font.PLAIN, 18));
		btn6.setBounds(136, 206, 50, 50);
		frame.getContentPane().add(btn6);
		
		JButton btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterNumber = display.getText() + btn1.getText(); 
				display.setText(enterNumber);
			}
		});
		btn1.setFont(new Font("D2Coding", Font.PLAIN, 18));
		btn1.setBounds(12, 266, 50, 50);
		frame.getContentPane().add(btn1);
		
		JButton btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterNumber = display.getText() + btn2.getText(); 
				display.setText(enterNumber);
			}
		});
		btn2.setFont(new Font("D2Coding", Font.PLAIN, 18));
		btn2.setBounds(74, 266, 50, 50);
		frame.getContentPane().add(btn2);
		
		JButton btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterNumber = display.getText() + btn3.getText(); 
				display.setText(enterNumber);
			}
		});
		btn3.setFont(new Font("D2Coding", Font.PLAIN, 18));
		btn3.setBounds(136, 266, 50, 50);
		frame.getContentPane().add(btn3);
		
		JButton btnPM = new JButton("±");
		btnPM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double ops = Double.parseDouble(String.valueOf(display.getText()));
				ops = ops * (-1);
				display.setText(String.valueOf(ops));
			}
		});
		btnPM.setFont(new Font("D2Coding", Font.PLAIN, 18));
		btnPM.setBounds(12, 326, 50, 50);
		frame.getContentPane().add(btnPM);
		
		JButton btn0 = new JButton("0");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterNumber = display.getText() + btn0.getText(); 
				display.setText(enterNumber);
			}
		});
		btn0.setFont(new Font("D2Coding", Font.PLAIN, 18));
		btn0.setBounds(74, 326, 50, 50);
		frame.getContentPane().add(btn0);
		
		JButton btnPoint = new JButton(".");
		btnPoint.setFont(new Font("D2Coding", Font.PLAIN, 18));
		btnPoint.setBounds(136, 326, 50, 50);
		frame.getContentPane().add(btnPoint);
		
		JButton btnBackSpace = new JButton("←");
		btnBackSpace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				String backSpace = null;
				if(display.getText().length() > 0) {
					StringBuilder sb = new StringBuilder(display.getText());
					sb.deleteCharAt(display.getText().length() - 1);
					backSpace = sb.toString();
					display.setText(backSpace);
				}
			}
		});
		btnBackSpace.setFont(new Font("D2Coding", Font.PLAIN, 18));
		btnBackSpace.setBounds(12, 86, 50, 50);
		frame.getContentPane().add(btnBackSpace);
		
		JButton btnClear = new JButton("C");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.setText(null);
			}
		});
		btnClear.setFont(new Font("D2Coding", Font.PLAIN, 18));
		btnClear.setBounds(74, 86, 50, 50);
		frame.getContentPane().add(btnClear);
		
		JButton btnPercent = new JButton("%");
		btnPercent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				firstNum = Double.parseDouble(display.getText());
				display.setText("");
				operations = "%";
			}
		});
		btnPercent.setFont(new Font("D2Coding", Font.PLAIN, 18));
		btnPercent.setBounds(136, 86, 50, 50);
		frame.getContentPane().add(btnPercent);
		
		JButton btnDivide = new JButton("/");
		btnDivide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				firstNum = Double.parseDouble(display.getText());
				display.setText("");
				operations = "/";
			}
		});
		btnDivide.setFont(new Font("D2Coding", Font.PLAIN, 18));
		btnDivide.setBounds(198, 86, 50, 50);
		frame.getContentPane().add(btnDivide);
		
		JButton btntimes = new JButton("*");
		btntimes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				firstNum = Double.parseDouble(display.getText());
				display.setText("");
				operations = "*";
			}
		});
		btntimes.setFont(new Font("D2Coding", Font.PLAIN, 18));
		btntimes.setBounds(198, 146, 50, 50);
		frame.getContentPane().add(btntimes);
		
		JButton btnMinus = new JButton("-");
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				firstNum = Double.parseDouble(display.getText());
				display.setText("");
				operations = "-";
			}
		});
		btnMinus.setFont(new Font("D2Coding", Font.PLAIN, 18));
		btnMinus.setBounds(198, 206, 50, 50);
		frame.getContentPane().add(btnMinus);
		
		JButton btnPlus = new JButton("+");
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				firstNum = Double.parseDouble(display.getText());			
				operations = "+";
				display.setText("");
			}
		});
		btnPlus.setFont(new Font("D2Coding", Font.PLAIN, 18));
		btnPlus.setBounds(198, 266, 50, 50);
		frame.getContentPane().add(btnPlus);
		
		JButton btnEqual = new JButton("=");
		btnEqual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String answer;
				
				secondNum = Double.parseDouble(display.getText());
				if (operations.equals("+")) {					
					result = firstNum + secondNum;
					answer = String.format("%.2f", result);
					display.setText(answer);
					
				} else if (operations.equals("-")) {					
					result = firstNum - secondNum;
					answer = String.format("%.2f", result);
					display.setText(answer);
					
				} else if (operations.equals("*")) {					
					result = firstNum * secondNum;
					answer = String.format("%.2f", result);
					display.setText(answer);
					
				} else if (operations.equals("/")) {					
					result = firstNum / secondNum;
					answer = String.format("%.2f", result);
					display.setText(answer);
					
				} else if (operations.equals("%")) {					
					result = firstNum % secondNum;
					answer = String.format("%.2f", result);
					display.setText(answer);
					
				}
				
			}
		});
		btnEqual.setFont(new Font("D2Coding", Font.PLAIN, 18));
		btnEqual.setBounds(198, 326, 50, 50);
		frame.getContentPane().add(btnEqual);
	}
}
