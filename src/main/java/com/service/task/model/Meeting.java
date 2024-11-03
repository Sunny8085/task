package com.service.task.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Meeting {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long meetingId;
	
	private String employeeId;
	
	private String name;
	
	private String meeting;
	
	private String participant;
	
	private LocalDateTime startTime;
	
	private LocalDateTime endTime;
	
}
