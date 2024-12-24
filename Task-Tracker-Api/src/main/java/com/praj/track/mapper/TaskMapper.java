package com.praj.track.mapper;

import com.praj.track.dto.TaskDto;
import com.praj.track.model.Task;

public interface TaskMapper {

	Task fromDto(TaskDto taskDto);
	TaskDto toDto(Task task);
}
