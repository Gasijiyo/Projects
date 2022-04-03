package pjt.java.schedule.ojdbc;

public interface OracleJdbc {
	//DB접속 계정
	String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	String USER = "scott";
	String PASSWORD = "tiger";
	//테이블, 컬럼 이름
	String TABLE_SCHEDULE = "schedule";
	String COL_TITLE = "title";
	String COL_SDATE = "sdate";
	String COL_MEMO	= "memo";
	String COL_NO = "no";
	
	//명령어 
//	String COM_SEL_ALL = "select title, to_char(sdate, 'YYYY/MM/DD') as SDATE, memo, no from "
//			+ TABLE_SCHEDULE + " where to_char(" + COL_SDATE + ", 'YYYY/MM/DD') = " + "'"; //+ yyyy + "/" + mm + "/" + dd + "'";
	String COM_SEL_ALL = "select title, to_char(sdate, 'YYYY/MM/DD') as SDATE, memo, no from " + TABLE_SCHEDULE ;			
		
	String COM_SEL = "select title, to_char(sdate, 'YYYY/MM/DD') as SDATE, memo from " + TABLE_SCHEDULE + " where no = "  ;
	String COM_ADD = "insert into " + TABLE_SCHEDULE 
			+ " (" + COL_TITLE + ", "+ COL_SDATE + ", " + COL_MEMO +")"+" values (?, ?, ?)";
	String COM_UPDATE = "update " + TABLE_SCHEDULE	+ " set " 
			 + COL_TITLE + " = ?, "+ COL_SDATE + " = ?, "+ COL_MEMO + " = ? where no = ";
//	String COM_UPDATE = "update " + TABLE_SCHEDULE	+ " set " 
//			 + COL_NO + " = ?" + COL_TITLE + " = ?, "+ COL_SDATE + " = ?, "+ COL_MEMO + " = ? , ";
		
	String COM_DEL = "delete from " + TABLE_SCHEDULE + " where no = " ;
//	String COM_DEL = "delete from " + TABLE_SCHEDULE + " where to_char(" + COL_SDATE + ", 'yyyy/mm/dd') = '";
			//+ sdate + "' and " + COL_TITLE + " = '" + title + "' and " + COL_MEMO + " = '" + memo + "'";
	
	
}
