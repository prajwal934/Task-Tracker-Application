package com.praj.track.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.praj.track.enums.TaskPriority;
import com.praj.track.enums.TaskStatus;
import com.praj.track.model.Task;
import com.praj.track.model.TaskList;
import com.praj.track.repository.TaskListRepository;
import com.praj.track.repository.TaskRepository;
import com.praj.track.service.TaskService;

import jakarta.transaction.Transactional;

@Service
public class TaskServiceImpl implements TaskService {
	
	private final TaskRepository taskRepository;
	private final TaskListRepository taskListRepository;
	
	

	public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
		super();
		this.taskRepository = taskRepository;
		this.taskListRepository = taskListRepository;
	}



	@Override
	public List<Task> listTasks(UUID taskListId) {
		// TODO Auto-generated method stub
		return taskRepository.findByTaskListId(taskListId);
	}



	@Transactional
	@Override
	public Task createTask(UUID taskListId, Task task) {
		// TODO Auto-generated method stub
		if(null != task.getId()) {
			throw new IllegalArgumentException("Task already has an ID");
		}
		if(null == task.getTitle() || task.getTitle().isBlank()) {
			throw new IllegalArgumentException("Task must have a title!");
		}
		
		TaskPriority taskPriority = Optional.ofNullable(task.getPriority())
				.orElse(TaskPriority.MEDIUM);
		
		TaskStatus taskStatus = TaskStatus.OPEN;
		
		TaskList taskList = taskListRepository.findById(taskListId)
				.orElseThrow(() ->  new IllegalArgumentException("Invalid Task List Id provide!"));
		
		LocalDateTime now = LocalDateTime.now();
		
		Task taskToSave = new Task(
				null,
				task.getTitle(),
				task.getDescription(),
				task.getDueDate(),
				taskStatus,
				taskPriority,
				taskList,
				now,
				now
				);
		
		return taskRepository.save(taskToSave);
	}



	@Override
	public Optional<Task> getTask(UUID taskListId, UUID taskId) {
		// TODO Auto-generated method stub
		return taskRepository.findByTaskListIdAndId(taskListId, taskId);
	}



	@Transactional
	@Override
	public Task updateTask(UUID taskListId, UUID taskId, Task task) {
		// TODO Auto-generated method stub
		if(null == task.getId()) {
			throw new IllegalArgumentException("Task must have an Id");
		}
		if(!Objects.equals(taskId, task.getId())) {
			throw new IllegalArgumentException("Task Ids do not match!");
		}
		if(null == task.getPriority()) {
			throw new IllegalArgumentException("Task must have a valid priority!");
		}
		if(null == task.getStatus()) {
			throw new IllegalArgumentException("Task must have valid status");
		}
		
		Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId)
				.orElseThrow(() -> new IllegalArgumentException("Task not found!"));
		
		existingTask.setTitle(task.getTitle());
		existingTask.setDescription(task.getDescription());
		existingTask.setDueDate(task.getDueDate());
		existingTask.setPriority(task.getPriority());
		existingTask.setStatus(task.getStatus());
		existingTask.setUpdated(LocalDateTime.now());
		
		return taskRepository.save(existingTask);
	}



	@Transactional
	@Override
	public void deleteTask(UUID taskListId, UUID taskId) {
		// TODO Auto-generated method stub
		taskRepository.deleteByTaskListIdAndId(taskListId, taskId);;
		
	}

}
