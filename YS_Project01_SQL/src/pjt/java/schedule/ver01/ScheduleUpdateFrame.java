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

public class ScheduleUpdateFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitle;
	private JTextField txtDate;
	private JTextField txtMemo;
	
	Calendar cal;
	int year, month, ddate;	
	int minYear, maxMonth, maxDdate;	//날짜 입력 오류 제한 값
	private Component parentComponent;
	
	private ScheduleDao dao;
	private ScheduleMain mainApp;
	
	private int no;
	
	
	/**
	 * Launch the application.
	 */
	public static void showUpdateFrame(int no, Component parentComponent, ScheduleMain mainApp) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduleUpdateFrame frame = new ScheduleUpdateFrame(no, parentComponent, mainApp);
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
	public ScheduleUpdateFrame(int no, Component parentComponent, ScheduleMain mainApp) {
		this.no = no;
		this.parentComponent = parentComponent;
		
		this.dao = ScheduleDaoImpl.getInstance();
		this.mainApp = mainApp;
		initialize();
		loadScheduleData();
	}
	
	public void initialize() {
		setTitle("일정 수정");
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
		txtTitle.setColumns(10);
		txtTitle.setBounds(89, 10, 183, 50);
		contentPane.add(txtTitle);
		
		JLabel lblDate = new JLabel("시간");
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
				updateSchedule();
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
		txtMemo.setColumns(10);
		scrollPane.setViewportView(txtMemo);
	}

	
	public void loadScheduleData() {		
		// 선택된 행의 no를 DB에서 검색, 검색된 내용을 텍스트 필드에 채워줌.
		Schedule schedule = dao.select(no);
		
		txtTitle.setText(schedule.getTitle());
		txtDate.setText(schedule.getSdate());
		txtMemo.setText(schedule.getMemo());						
	}		
		
	private void updateSchedule() {
				
		String title = txtTitle.getText();
		String date = txtDate.getText();
		String memo = txtMemo.getText();		
		
		// ---- 입력 값들 조건식 ----

		//날짜 입력 용도 
		cal = Calendar.getInstance();
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;
		ddate = cal.get(Calendar.DATE);
		//날짜 입력 값 제한 용도
		minYear = year;	//일정은 일반적으로 지금부터 있을 미래를 나타내기에 최소값으로 현재 년도 설정 
		maxMonth = 13;
		maxDdate = 32;
		
		// 1. 날짜 입력 조건		
		//	a.일 (day) 미 입력 시
		if(txtDate.getText().equals(String.valueOf(mainApp.comboYear.getSelectedItem())
				+"/"+String.valueOf(mainApp.comboMonth.getSelectedItem())+"/")) {
				JOptionPane.showMessageDialog(parentComponent, "날짜를 정확히 입력해 주십시오!");
				return;
		}
		//	b.날짜 전체 미 입력 시
		if( txtDate.getText().equals("") ) {
			date = year + "/" + month + "/" + ddate;
		} 		
		//	c.일 (day) 31일 초과 시
		if (year < minYear || month > maxMonth || ddate > maxDdate) {
			JOptionPane.showMessageDialog(parentComponent, "날짜를 정확히 입력해 주십시오!");
			return;
		}		
		
		//		if(txtDate.getText().charAt(txtDate.getText().length()-2) > 3 
//				&&txtDate.getText().charAt(txtDate.getText().length()-1) > 1) {
//				
//					date = year + "/" + month + "/" + 31;
//		}
		// 2.일정 제목 기본값
		if(txtTitle.getText().equals("")) {
			title = "내 일정";
		} 	
		// 3.일정 메모 기본값 
		if (txtMemo.getText().equals("")) {
			memo = "."; 
		}	
//		---- 조건식 끝 ----
		
		
		//수정 후 result 값  
		Schedule s = new Schedule(title, date, memo);
		int result = dao.update(no, s);
		if(result == 1) {
			dispose();
			JOptionPane.showMessageDialog(parentComponent, "일정을 수정했습니다.");
			mainApp.scheduleUpdateNotify(no, s);			
			
		}
		
		
	}

}
