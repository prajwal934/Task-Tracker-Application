package com.praj.track.mapper;

import com.praj.track.dto.TaskListDto;
import com.praj.track.model.TaskList;

public interface TaskListMapper {
	
	TaskList fromDto(TaskListDto taskListDto);
	
	TaskListDto toDto(TaskList taskList);

}
