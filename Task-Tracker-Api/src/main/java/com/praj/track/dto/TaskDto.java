package com.praj.track.dto;

import java.time.LocalDateTime;
import java.util.*;

import com.praj.track.enums.TaskPriority;
import com.praj.track.enums.TaskStatus;

public record TaskDto(
		
	UUID id , 
	String title,
	String description,
	LocalDateTime dueDate,
	TaskPriority priority,
	TaskStatus status
	
		
) {

}
