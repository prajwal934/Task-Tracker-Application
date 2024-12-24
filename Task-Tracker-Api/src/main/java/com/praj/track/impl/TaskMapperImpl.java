package com.praj.track.impl;

import org.springframework.stereotype.Component;

import com.praj.track.dto.TaskDto;
import com.praj.track.mapper.TaskMapper;
import com.praj.track.model.Task;

@Component
public class TaskMapperImpl  implements TaskMapper{

	@Override
	public Task fromDto(TaskDto taskDto) {
		// TODO Auto-generated method stub
		return new Task(
				taskDto.id(),
				taskDto.title(),
				taskDto.description(),
				taskDto.dueDate(),
				taskDto.status(),
				taskDto.priority(),
				null,
				null,
				null
				);
	}

	@Override
	public TaskDto toDto(Task task) {
		// TODO Auto-generated method stub
		return new TaskDto(task.getId(), 
				task.getTitle(),
				task.getDescription(), 
				task.getDueDate(), 
				task.getPriority(), 
				task.getStatus());
	}

}
