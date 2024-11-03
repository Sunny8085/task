package com.service.task.repository;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.service.task.model.Meeting;

public interface CalendarRepository extends JpaRepository<Meeting, Long>{

	
	@Query(value = "SELECT * FROM meeting WHERE DATE(start_time) = DATE(:localDate)", nativeQuery = true)
	List<Meeting> getMeetingsByEmployeeId(LocalDate localDate);

	@Query(value = "SELECT * FROM meeting WHERE employee_id IN (:employeeIds) and DATE(start_time) = CURRENT_DATE ORDER BY TO_CHAR(start_time, 'HH24:MI:SS')", nativeQuery = true)
	LinkedList<Meeting> findMeetingsByEmployeeId(List<String> employeeIds);

}
