package com.service.task.serviceImpl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.task.model.Meeting;
import com.service.task.repository.CalendarRepository;
import com.service.task.service.CalendarService;

@Service
public class CalendarServiceImpl implements CalendarService {
	
	@Autowired private CalendarRepository calendarRepository;
	
	@Override
	public List<LocalDateTime> findFreeSlots(String employeeId1, String employeeId2, int durationMinutes) {
		List<String> employeeIds = Arrays.asList(employeeId1, employeeId2);
		LinkedList<Meeting> allMeetings = calendarRepository.findMeetingsByEmployeeId(employeeIds);
        
        List<LocalDateTime> gapDuration = new ArrayList<>();
        gapDuration.add(allMeetings.get(0).getStartTime().minusMinutes(durationMinutes));
        gapDuration.add(allMeetings.get(allMeetings.size() - 1).getEndTime().plusMinutes(durationMinutes));
        
		for(int i = 1; (i < allMeetings.size() && allMeetings.size() >= 1); i++) {
			long minutesDifference = Duration.between(allMeetings.get(i - 1).getEndTime().atZone(ZoneId.systemDefault()).toInstant(),
					allMeetings.get(i).getStartTime().atZone(ZoneId.systemDefault()).toInstant()).toMinutes();
			if(minutesDifference >= durationMinutes) {
				gapDuration.add(allMeetings.get(i - 1).getEndTime().plusMinutes(durationMinutes));
			}
		}
        return gapDuration;
	}

	@Override
	public Meeting bookMeeting(Meeting meeting) {
		return calendarRepository.save(meeting);
	}

	@Override
	public String findConflictingParticipants(Meeting meeting) {
		LocalDateTime requestedStart = meeting.getStartTime();
        LocalDateTime requestedEnd = meeting.getEndTime();
        List<Meeting> participantMeetings = calendarRepository.getMeetingsByEmployeeId(requestedStart.toLocalDate());
        boolean isConflict = participantMeetings.stream().anyMatch(e -> {
        	if(!((requestedStart.isAfter(e.getStartTime()) && requestedEnd.isAfter(e.getEndTime())) || 
        			(requestedStart.isBefore(e.getStartTime()) && requestedEnd.isBefore(e.getEndTime()))))
        		return true;
        	else return false;
        });
        if (isConflict)
            return "Meeting has a conflict with existing schedules";
        else
            return "Meeting has no conflicts and can be scheduled";
	}

}











