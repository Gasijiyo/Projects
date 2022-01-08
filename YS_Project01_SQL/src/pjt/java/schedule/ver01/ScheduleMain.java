package pjt.java.schedule.ver01;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.table.DefaultTableModel;

import oracle.jdbc.OracleDriver;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import pjt.java.schedule.model.Schedule;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;

import static pjt.java.schedule.ojdbc.OracleJdbc.*;

public class ScheduleMain implements ActionListener {

//	본 프로젝트에 존재하는 System.out.println 구문은 
//	프로그램 시연 시 exe파일과 같이 실행되는 cmd창에 어떤 명령어가 입력되는지 확인하는 용도입니다. 	
	
	private List<Schedule> scheduleOfDay;	//리스트 필드 선언
	private ScheduleDao dao;

	private JFrame frame;
	private DefaultTableModel model;
	
	private static final String[] COL_NAMES = {"제목", "날짜", "메모"};
	
	private JPanel panelDate;

	Calendar cal;
	int year, month, date;
	int selectedDay;
	private JTable table;
	private JButton btnPrev;
	private JButton btnNext;

	JComboBox<Integer> comboYear = new JComboBox<Integer>();
	DefaultComboBoxModel<Integer> modelYear = new DefaultComboBoxModel<Integer>();

	JComboBox<Integer> comboMonth = new JComboBox<Integer>();
	DefaultComboBoxModel<Integer> modelMonth = new DefaultComboBoxModel<Integer>();
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnRemove;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduleMain window = new ScheduleMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		try {
			DriverManager.registerDriver(new OracleDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Create the application.
	 */
	public ScheduleMain() {
		dao = ScheduleDaoImpl.getInstance();
		initialize();	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setTitle("일정 관리 프로그램");
		frame.setBounds(500, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	

		// 상단 패널 (날짜 조정)
		JPanel panelSelectDate = new JPanel();
		panelSelectDate.setBounds(12, 10, 310, 39);
		frame.getContentPane().add(panelSelectDate);

		// 이전 버튼
		btnPrev = new JButton("◀");
		panelSelectDate.add(btnPrev);

		// 연도 콤보박스
//		comboYear = new JComboBox();
		comboYear.setFont(new Font("D2Coding", Font.PLAIN, 14));
		panelSelectDate.add(comboYear);
		cal = Calendar.getInstance(); // 현재날짜
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;
		date = cal.get(Calendar.DATE);

		for (int i = year - 50; i <= year + 50; i++) {
			modelYear.addElement(i);
		}

		comboYear.setModel(modelYear);
		comboYear.setSelectedItem(year);

		// 연도 레이블
		JLabel lblYear = new JLabel("년");
		lblYear.setFont(new Font("D2Coding", Font.PLAIN, 14));
		panelSelectDate.add(lblYear);

		// 월 콤보박스
		comboMonth.setFont(new Font("D2Coding", Font.PLAIN, 14));
		panelSelectDate.add(comboMonth);
		for (int i = 1; i <= 12; i++) {
			modelMonth.addElement(i);
		}
		comboMonth.setModel(modelMonth);
		comboMonth.setSelectedItem(month);

		// 월 레이블
		JLabel lblMonth = new JLabel("월");
		lblMonth.setFont(new Font("D2Coding", Font.PLAIN, 14));
		panelSelectDate.add(lblMonth);
		day(year, month);

		// 다음 버튼
		btnNext = new JButton("▶");
		panelSelectDate.add(btnNext);

		// 버튼, 콤보박스 기능 정리
		btnPrev.addActionListener(this);
		btnNext.addActionListener(this);
		comboYear.addActionListener(this);
		comboMonth.addActionListener(this);

		// 오늘날짜로 돌아오는 버튼
		JButton btnToday = new JButton("Today");
		btnToday.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				backToToday();
			}
		});
		btnToday.setBounds(334, 12, 88, 29);
		frame.getContentPane().add(btnToday);
		btnToday.setFont(new Font("D2Coding", Font.BOLD, 17));

		// 일정 표시 스크롤패널
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 396, 410, 94);
		frame.getContentPane().add(scrollPane);

		// 일정 표시 테이블
		table = new JTable();		
		table.setFont(new Font("D2Coding", Font.BOLD, 14));
		model = new DefaultTableModel(null, COL_NAMES);
		table.setModel(model);
		scrollPane.setViewportView(table);

		// 일정 추가 버튼
		btnAdd = new JButton("추가");
		btnAdd.setBounds(12, 500, 88, 50);
		frame.getContentPane().add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				ScheduleAddFrame.showAddFrame(frame, ScheduleMain.this);
			}
		});
		btnAdd.setFont(new Font("D2Coding", Font.BOLD, 22));

		// 일정 수정 버튼
		btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				showUpdateSchedule();

			}
		});
		btnUpdate.setFont(new Font("D2Coding", Font.BOLD, 22));
		btnUpdate.setBounds(112, 500, 88, 50);
		frame.getContentPane().add(btnUpdate);

		// 일정 삭제 버튼
		btnRemove = new JButton("삭제");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteSchedule();
			}
		});
		btnRemove.setFont(new Font("D2Coding", Font.BOLD, 22));
		btnRemove.setBounds(212, 500, 88, 50);
		frame.getContentPane().add(btnRemove);

		// 프로그램 종료 버튼
		JButton btnClose = new JButton("QUIT");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		btnClose.setFont(new Font("D2Coding", Font.BOLD, 18));
		btnClose.setBounds(334, 500, 88, 50);
		frame.getContentPane().add(btnClose);

	}

//	------- ------- 각 기능 구현 메소드 ------- -------
	
	// 1. 프로그램 상단 패널
	// 버튼, 콤보박스 기능 구현
	public void actionPerformed(ActionEvent e) {
		Object eventObj = e.getSource();

		if (eventObj instanceof JComboBox) {

			panelDate.setVisible(false); // 보여지는 패널을 숨김.
			panelDate.removeAll(); // 레이블 지우기
			day((Integer) comboYear.getSelectedItem(), (Integer) comboMonth.getSelectedItem()); // 날짜 출력
			panelDate.setVisible(true); // 패널 재출력

		} else if (eventObj instanceof JButton) {

			JButton eventBtn = (JButton) eventObj;
			int yy = (Integer) comboYear.getSelectedItem();
			int mm = (Integer) comboMonth.getSelectedItem();

			if (eventBtn.equals(btnPrev)) { // 이전 버튼
				if (mm == 1) {
					yy--;
					mm = 12;
				} else {
					mm--;
				}

			} else if (eventBtn.equals(btnNext)) { // 다음 버튼
				if (mm == 12) {
					yy++;
					mm = 1;
				} else {
					mm++;
				}
			}
			comboYear.setSelectedItem(yy);
			comboMonth.setSelectedItem(mm);
		}
	}
	
	// Today 버튼 클릭
	private void backToToday() {
		
		System.out.println("back to today");
		if(date < 10 && date > 0) {	//일(day)이 한 자리수 일 경우 앞에 "0" 붙여주기
			date = Integer.parseInt("0")+date;
		}		
		comboYear.setSelectedItem(year);
		comboMonth.setSelectedItem(month);
		showSchedule(date);
		
	}
	
	
	// 2. 프로그램 중단 달력 패널 (2개)
	// 달력 설계
	private void day(int year, int month) {

		// 요일 레이블 in 패널
		JPanel panelDay = new JPanel(new GridLayout(1, 7));
		panelDay.setBounds(12, 70, 410, 48);

		String titleStr[] = { "일", "월", "화", "수", "목", "금", "토" };
		for (int i = 0; i < titleStr.length; i++) {
			JLabel lbl = new JLabel(titleStr[i], JLabel.CENTER);
			if (i == 0) {
				lbl.setForeground(Color.red);
			} else if (i == 6) {
				lbl.setForeground(Color.blue);
			}
			panelDay.add(lbl);
		}

		frame.getContentPane().add(panelDay);

		// 일 (달력) 레이블
		panelDate = new JPanel(new GridLayout(0, 7));
		Calendar date = Calendar.getInstance(); // 오늘날짜
		date.set(year, month - 1, 1);

		int week = date.get(Calendar.DAY_OF_WEEK);
		int lastDay = date.getActualMaximum(Calendar.DAY_OF_MONTH);

		// 줄 정렬/줄바꿈용 공간 레이블 생성
		for (int space = 1; space < week; space++) {
			panelDate.add(new JLabel("\t"));
		}
		// 날짜 출력 - for반복문으로 레이블 생성
		for (int day = 1; day <= lastDay; day++) {
			JLabel lbl1 = new JLabel(String.valueOf(day), JLabel.CENTER);
			cal.set(year, month - 1, day);
			int Week = cal.get(Calendar.DAY_OF_WEEK);
			if (Week == 1) {
				lbl1.setForeground(Color.red);
			} else if (Week == 7) {
				lbl1.setForeground(Color.BLUE);
			}
			panelDate.add(lbl1);
			// 클릭한 날짜 인식
			lbl1.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseExited(MouseEvent e) {
				}

				@Override
				public void mouseEntered(MouseEvent e) {
				}

				@Override
				public void mouseClicked(MouseEvent e) { // 날짜 클릭 시 발생이벤트
					System.out.println("Clicked Date is " + lbl1.getText());
					// 날짜 -> 일 변환
					Object dd = Integer.parseInt(lbl1.getText());	
					selectedDay = (Integer) dd;
					if ((Integer) dd < 10) {
						dd = "0" + dd; // 한 자리수 일(day) 앞에 '0' 붙이기
					} 					
					
					System.out.println(dd);
					showSchedule(dd);
				}
			});

		}

		panelDate.setBounds(12, 119, 410, 267);
		frame.getContentPane().add(panelDate);
	}
	
	
	// 3. 프로그램 하단	
	
	// 	a. 일정 표시 위한 마우스클릭 이벤트 - 테이블에 해당 날짜 일정 표시	
	private void showSchedule(Object dd) {
		scheduleOfDay = new ArrayList<Schedule>();	//클릭한 날짜의 일정을 저장할 리스트 설정
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		// 날짜 변수 선언 (년/월)
		Object yyyy = (Integer) comboYear.getSelectedItem();
		Object mm = (Integer) comboMonth.getSelectedItem();
		if ((Integer) mm < 10) {
			mm = "0" + mm; // 한 자리수 월(month) 앞에 '0' 붙이기
		}
		
		try {
			model = new DefaultTableModel(null, COL_NAMES);
			table.setModel(model);
			
			// DB접속
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();

			String sqlSelect = COM_SEL_ALL + " where to_char(" + COL_SDATE + ", 'YYYY/MM/DD') = " 
			+ "'" + yyyy + "/" + mm + "/" + dd + "'";	//날짜에 해당하는 데이터 불러오기																													
						
			System.out.println(sqlSelect);
			rs = stmt.executeQuery(sqlSelect);
			
			while (rs.next()) {
				int no = rs.getInt(COL_NO);
				String title = rs.getString(COL_TITLE);
				String sdate = rs.getString(COL_SDATE);
				String memo = rs.getString(COL_MEMO);

				String[] rowData = { title, sdate, memo };
				model.addRow(rowData);
				
				Schedule s = new Schedule(no, title, sdate, memo);
				System.out.println(s);
				scheduleOfDay.add(s);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}	
	
	// b. 일정 추가 후 데이터 저장 및 전달
	public void addSchedule(Schedule s) {
		//일정 추가
		int result = dao.add(s);
		if(result == 1) {			
			JOptionPane.showMessageDialog(frame, "일정이 저장됐습니다.");	
			//입력한 데이터 
			String sdate = s.getSdate();
			String day = sdate.substring(sdate.length() - 2);
			char c = sdate.charAt(sdate.length()-2);
			if(c == '/' ) {
				c = '0' ;
				day = c + sdate.substring(sdate.length() - 1);
			} 										
			System.out.println(day);
			showSchedule(day);
		}	
		
	}	

	// c. 일정 업데이트(수정) 작업
	
	private void showUpdateSchedule() {
		// 테이블에서 수정하기 위해 선택된 행이 있는 지 검사
		int row = table.getSelectedRow(); 		
		if (row == -1) {
			JOptionPane.showMessageDialog(frame, "수정할 행을 먼저 선택하세요.", "확인", JOptionPane.WARNING_MESSAGE);
			return; 
		}	
				
		int no = scheduleOfDay.get(row).getNo(); // Schedule의 PK
		System.out.println(scheduleOfDay.get(row));
		ScheduleUpdateFrame.showUpdateFrame(no , frame, this);

	}	
	
	//수정된 일정 테이블 반영	
	public void scheduleUpdateNotify(int no, Schedule s) {
		//테이블 모델을 초기화 전체 데이터를 새로 로딩
		String sdate = s.getSdate();
		String day = sdate.substring(sdate.length() - 2);
		char c = sdate.charAt(sdate.length()-2);
		if(c == '/' ) {
			c = '0' ;
			day = c + sdate.substring(sdate.length() - 1);
		} 
		System.out.println(day);
		showSchedule(day);
		
	}	


	// d. 일정 삭제 버튼
	private void deleteSchedule() {
		
		int row = table.getSelectedRow();	//삭제할 행을 잘 못 선택한 경우
		if (row == -1) {
			JOptionPane.showMessageDialog(frame, "삭제할 행을 먼저 선택하세요...", "확인", JOptionPane.WARNING_MESSAGE);
			return; 
		} 
		
		int confirm = JOptionPane.showConfirmDialog(frame, "정말 삭제하시겠습니까?", "삭제 확인",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

		if (confirm == JOptionPane.YES_OPTION) {
			int no = scheduleOfDay.get(row).getNo();
			dao.delete(no);
			JOptionPane.showMessageDialog(frame, "선택한 행이 삭제 되었습니다.");
			scheduleOfDay.remove(row);
			model.removeRow(row);	
		}	
		
	}


}
