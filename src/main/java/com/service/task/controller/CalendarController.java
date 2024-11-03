package com.service.task.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.task.model.Meeting;
import com.service.task.service.CalendarService;

@RestController
@RequestMapping("api/v1")
public class CalendarController {
	
	@Autowired private CalendarService calendarService;
	
	@PostMapping("/meetings")
    public ResponseEntity<Meeting> bookMeeting(@RequestBody Meeting meeting) {
		Meeting createdMeeting = calendarService.bookMeeting(meeting);
	    return new ResponseEntity<>(createdMeeting, HttpStatus.CREATED);
    }

    @GetMapping("/free-slots")
    public ResponseEntity<List<LocalDateTime>> getFreeSlots(@RequestParam String employeeId1, @RequestParam String employeeId2,
            @RequestParam int durationMinutes) {
        List<LocalDateTime> freeSlots = calendarService.findFreeSlots(employeeId1, employeeId2, durationMinutes);
        return ResponseEntity.ok(freeSlots);
    }

    @PostMapping("/conflicts")
    public ResponseEntity<String> getConflicts(@RequestBody Meeting meeting) {
        String conflicts = calendarService.findConflictingParticipants(meeting);
        return ResponseEntity.ok(conflicts);
    }
	
}
