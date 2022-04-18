package pjt.java.schedule.ver01;

import java.util.List;

import pjt.java.schedule.model.Schedule;

//MVC - Controller
public interface ScheduleDao {

	/**
	 * 리스트에 저장된 일정 리턴
	 * @return - 일정 정보 저장된 리스트
	 */
	List<Schedule> select();
	
	
	/**
	 * no에 해당하는 Schedule 객체를 리턴.
	 * @param no
	 * @return no에 있는 Schedule 객체.  
	 */	
	Schedule select(int no);

	
	/**
	 * Schedule 객체 전달받아 리스트 저장
	 * @param schedule - 리스트에 저장할 스케쥴타입 객체
	 * @return - 일정 추가 성공 시 1을, 실패 시 0을 리턴
	 */
	int add(Schedule s);
	
	/**
	 * 리스트에서 업데이트할 일정 정보를 전달받아 리스트의 해당 정보를 업데이트  
	 * 	  
	 * @param schedule - 스케쥴타입 객체, 업데이트할 정보(제목, 날짜, 메모)저장
	 * @return 수정 성공 시 1을, 실패 시 0을 리턴
	 */
	int update(int no, Schedule s);
	
	/**
	 * 리스트에서 일정 정보 삭제
	 * @param schedule - 리스트에서 삭제할 일정
	 * @return - 삭제 성공 시 1을, 실패시 0을 리턴
	 */
	int delete(int no);
}
