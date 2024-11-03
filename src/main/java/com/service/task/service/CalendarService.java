package com.service.task.service;

import java.time.LocalDateTime;
import java.util.List;

import com.service.task.model.Meeting;

public interface CalendarService {

	List<LocalDateTime> findFreeSlots(String employeeId1, String employeeId2, int durationMinutes);

	Meeting bookMeeting(Meeting meeting);

	String findConflictingParticipants(Meeting meeting);

}
