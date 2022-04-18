package pjt.java.schedule.ver01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleDriver;
import pjt.java.schedule.model.Schedule;
import static pjt.java.schedule.ojdbc.OracleJdbc.*;

public class ScheduleDaoImpl implements ScheduleDao {

	//field (멤버 변수)
	
	//Singleton
	private static ScheduleDaoImpl instance = null;
	private ScheduleDaoImpl() {		
		
		try {
			DriverManager.registerDriver(new OracleDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	
	}	
	public static ScheduleDaoImpl getInstance() {
		if(instance == null) {
			instance = new ScheduleDaoImpl();
		}
		return instance;
	}
	
	
	@Override
	public List<Schedule> select() {
		List<Schedule> schedules = new ArrayList<Schedule>();
		
		String sql = COM_SEL_ALL;
		System.out.println(sql);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int no = rs.getInt(COL_NO);
				String title = rs.getNString(COL_TITLE);
				String date = rs.getNString(COL_SDATE);
				String memo = rs.getNString(COL_MEMO);				
				Schedule sc = new Schedule(no, title, date, memo);
				
				schedules.add(sc);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return schedules;
	}
		

	@Override
	public Schedule select(int no) {
		Schedule result = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();			
			String sql = COM_SEL + no;
			System.out.println(sql);
			
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String title = rs.getString(COL_TITLE);
				String sdate = rs.getString(COL_SDATE);
				String memo = rs.getString(COL_MEMO);
				
				result = new Schedule(no, title, sdate, memo);
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
		
		return result;
	}
	
	
	
	@Override
	public int add(Schedule s) {		
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = COM_ADD;
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s.getTitle());
			pstmt.setString(2, s.getSdate());
			pstmt.setString(3, s.getMemo());			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
//		schedules.add(s);		
		return result;
	}

	@Override
	public int update(int no, Schedule s) {			
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = COM_UPDATE + no;
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, s.getTitle());
			pstmt.setString(2, s.getSdate());
			pstmt.setString(3, s.getMemo());	
						
//			pstmt.setInt(1, s.getNo());
//			pstmt.setString(2, s.getTitle());
//			pstmt.setString(3, s.getSdate());
//			pstmt.setString(4, s.getMemo());			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int delete(int no) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = COM_DEL + no;
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}

	

}
