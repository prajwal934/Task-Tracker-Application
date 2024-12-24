package com.praj.track.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.praj.track.model.TaskList;
import com.praj.track.repository.TaskListRepository;
import com.praj.track.service.TaskListService;

import jakarta.transaction.Transactional;

@Service
public class TaskListServiceImpl implements TaskListService {
	
	private final TaskListRepository taskListRepository;

	public TaskListServiceImpl(TaskListRepository taskListRepository) {
		super();
		this.taskListRepository = taskListRepository;
	}

	@Override
	public List<TaskList> listTaskLists() {
		// TODO Auto-generated method stub
		return taskListRepository.findAll();
	}

	@Override
	public TaskList createTaskList(TaskList taskList) {
		// TODO Auto-generated method stub
		if(null != taskList.getId()) {
			throw new IllegalArgumentException("Task list already has an ID");
		}
		
		if(null == taskList.getTitle() || taskList.getTitle().isBlank()) {
			throw new IllegalArgumentException("Task list title must be present!"); 
		}
		
		LocalDateTime now = LocalDateTime.now();
		return  taskListRepository.save(new TaskList(
				null , 
				taskList.getTitle(),
				taskList.getDescription(),
				null,
				now,
				now
				));
	}

	@Override
	public Optional<TaskList> getTaskList(UUID id) {
		// TODO Auto-generated method stub
		return taskListRepository.findById(id);
	}

	@Transactional
	@Override
	public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
		// TODO Auto-generated method stub
		if(null == taskList.getId()) {
			throw new IllegalArgumentException("Task list must have an ID!");
		}
		
		if(!Objects.equals(taskList.getId(), taskListId)) {
			throw new IllegalArgumentException("Attempting to change Task List ID, this is not permitted!");
		}
		
		TaskList existingTaskList = taskListRepository.findById(taskListId).orElseThrow(() -> 
		
		new IllegalArgumentException("Oops, Task list not found!"));
		
		existingTaskList.setTitle(taskList.getTitle());
		existingTaskList.setDescription(taskList.getDescription());
		existingTaskList.setUpdated(LocalDateTime.now());
		
		return taskListRepository.save(existingTaskList);
		
		
	}

	@Override
	public void deleteTaskList(UUID taskListId) {
		// TODO Auto-generated method stub
		taskListRepository.deleteById(taskListId);
		
	}
	
	


}
