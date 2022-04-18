package pjt.java.schedule.ver01;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;

import java.util.Calendar;
import java.awt.event.ActionEvent;

import pjt.java.schedule.model.Schedule;

public class ScheduleAddFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitle;
	private JTextField txtDate;
	private JTextField txtMemo;
	
	Calendar cal;
	int year, month, ddate;
	int minYear, maxMonth, maxDdate;	//날짜 입력 오류 제한 값
	private Component parentComponent;
	private ScheduleMain mainApp;

	/**
	 * Launch the application.
	 */
	public static void showAddFrame(Component parentComponent, ScheduleMain mainApp) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduleAddFrame frame = new ScheduleAddFrame(parentComponent, mainApp);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ScheduleAddFrame(Component parentComponent, ScheduleMain mainApp) {
		this.parentComponent = parentComponent;
		this.mainApp = mainApp;
		initialize();
		loadDate();
		
	}
	
	

	public void initialize() {
		setTitle("일정 추가");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int x = parentComponent.getX()+75;
		int y = parentComponent.getY()+100;		
		setBounds(x, y, 300, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("제목");
		lblTitle.setFont(new Font("D2Coding", Font.BOLD, 20));
		lblTitle.setBounds(12, 10, 65, 50);
		contentPane.add(lblTitle);
		
		txtTitle = new JTextField();
		txtTitle.setFont(new Font("D2Coding", Font.BOLD, 16));
		txtTitle.setBounds(89, 10, 183, 50);
		contentPane.add(txtTitle);
		txtTitle.setColumns(10);
		
		JLabel lblDate = new JLabel("날짜");
		lblDate.setFont(new Font("D2Coding", Font.BOLD, 20));
		lblDate.setBounds(12, 70, 65, 50);
		contentPane.add(lblDate);
		
		txtDate = new JTextField();
		txtDate.setFont(new Font("D2Coding", Font.BOLD, 16));
		txtDate.setColumns(10);
		txtDate.setBounds(89, 70, 183, 50);
		contentPane.add(txtDate);
		
		JLabel lblMemo = new JLabel("메모");
		lblMemo.setFont(new Font("D2Coding", Font.BOLD, 20));
		lblMemo.setBounds(12, 130, 65, 50);
		contentPane.add(lblMemo);
		
		JButton btnCancel = new JButton("취소");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("D2Coding", Font.BOLD, 20));
		btnCancel.setBounds(12, 232, 97, 50);
		contentPane.add(btnCancel);
		
		JButton btnSave = new JButton("저장");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addSchedule();
			}
		});
		btnSave.setFont(new Font("D2Coding", Font.BOLD, 20));
		btnSave.setBounds(175, 232, 97, 50);
		contentPane.add(btnSave);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(89, 130, 183, 69);
		contentPane.add(scrollPane);
		
		txtMemo = new JTextField();
		txtMemo.setFont(new Font("D2Coding", Font.BOLD, 16));
		scrollPane.setViewportView(txtMemo);
		txtMemo.setColumns(10);
	}
	
	//추가 클릭 시 보고 있는 년/월 출력으로 입력 간편화 
	private void loadDate() {

		//날짜 입력 용도 		
		cal = Calendar.getInstance();
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;
		ddate = cal.get(Calendar.DATE);		
		
		//메인화면에서 보고 있는 년/월 값을 바로출력		
		txtDate.setText(String.valueOf(mainApp.comboYear.getSelectedItem())+"/"
				+String.valueOf(mainApp.comboMonth.getSelectedItem())+"/" 
				+ mainApp.selectedDay);	
		
		if(mainApp.selectedDay == 0) {	//프로그램 실행 후 바로 추가를 눌렀을 때
			txtDate.setText(String.valueOf(mainApp.comboYear.getSelectedItem())+"/"
					+String.valueOf(mainApp.comboMonth.getSelectedItem())+"/" 
					+ ddate);
		}
		
	}
	
	private void addSchedule() {
			
		//날짜 입력 값 제한 용도
		minYear = year;	//일정은 일반적으로 지금부터 있을 미래를 나타내기에 최소값으로 현재 년도 설정 
		maxMonth = 13;
		maxDdate = 32;
				
		String title = txtTitle.getText();
		String date = txtDate.getText();
		String memo = txtMemo.getText();

//		---- ---- 입력 값들 조건식 ---- ----
		// 1. 날짜 입력 조건
		//	a.일 (day) 미 입력 시
		if(txtDate.getText().equals(String.valueOf(mainApp.comboYear.getSelectedItem())
				+"/"+String.valueOf(mainApp.comboMonth.getSelectedItem())+"/")) {
				JOptionPane.showMessageDialog(parentComponent, "날짜를 입력해 주십시오!");
				return;
		}
		
		//	b.날짜 전체 미 입력 시
		if( txtDate.getText().equals("") ) {
			date = year + "/" + month + "/" + ddate;
			
			JOptionPane.showMessageDialog(parentComponent, "입력된 날짜가 없으므로 오늘 날짜로 저장됩니다.");
		} 		
		//	c.년/월/일 각각 입력값 초과 또는 미달 입력 시
		if (year < minYear || month > maxMonth || ddate > maxDdate) {
			JOptionPane.showMessageDialog(parentComponent, "날짜를 정확히 입력해 주십시오!");
			return;
		}
		

		// 2.일정 제목 기본값
		if(txtTitle.getText().equals("")) {
			title = "내 일정";
		} 	
		// 3.일정 메모 기본값 
		if (txtMemo.getText().equals("")) {
			memo = "."; 
		}						
			
		Schedule s = new Schedule(title, date, memo);	//저장위한 데이터 선언 
		mainApp.addSchedule(s);	//저장위한 데이터 전달				
		dispose();			
		
	}
	
}
