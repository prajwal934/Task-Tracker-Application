package com.praj.track.impl;

import java.util.*;

import org.springframework.stereotype.Component;

import com.praj.track.dto.TaskListDto;
import com.praj.track.enums.TaskStatus;
import com.praj.track.mapper.TaskListMapper;
import com.praj.track.mapper.TaskMapper;
import com.praj.track.model.Task;
import com.praj.track.model.TaskList;

@Component
public class TaskListMapperImpl  implements TaskListMapper{
	
	private final TaskMapper taskMapper;
	
	

	public TaskListMapperImpl(TaskMapper taskMapper) {
		super();
		this.taskMapper = taskMapper;
	}

	@Override
	public TaskList fromDto(TaskListDto taskListDto) {
		// TODO Auto-generated method stub
		return new TaskList(
				taskListDto.id(),
				taskListDto.title(),
				taskListDto.description(),
				Optional.ofNullable(taskListDto.tasks())
				.map(tasks -> tasks.stream().map(taskMapper::fromDto)
						.toList()).orElse(null),
				null,
				null
				);
	}

	@Override
	public TaskListDto toDto(TaskList taskList) {
		// TODO Auto-generated method stub
		return new TaskListDto(
				taskList.getId(), 
				taskList.getTitle(), 
				taskList.getDescription(),
				Optional.ofNullable(taskList.getTasks()).map(List::size).orElse(0),
				calculateTaskListProgress(taskList.getTasks()),
				Optional.ofNullable(taskList.getTasks()).map(tasks -> tasks.stream().map(taskMapper::toDto)
						.toList()).orElse(null)
				);
	}

	
	private Double calculateTaskListProgress(List<Task> tasks) {
		if(null == tasks) {
			return null;
		}
		
		long closedTaskCount = tasks.stream().filter(task -> TaskStatus.CLOSED == task.getStatus()).count();
		
		return (double) closedTaskCount / tasks.size();
	}
}
