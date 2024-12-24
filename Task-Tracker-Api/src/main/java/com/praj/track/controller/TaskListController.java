package com.praj.track.controller;

import java.util.List;
import java.util.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.praj.track.dto.TaskListDto;
import com.praj.track.mapper.TaskListMapper;
import com.praj.track.model.TaskList;
import com.praj.track.service.TaskListService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/task-lists")
public class TaskListController {
	
	private final TaskListService taskListService;
	private final TaskListMapper taskListMapper;
	
	@GetMapping
	public List<TaskListDto> listTaskLists() {
		return taskListService.listTaskLists()
				.stream().map(taskListMapper::toDto)
				.toList();
	}
	
	@PostMapping
	public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
		TaskList createdTaskList = taskListService.createTaskList(taskListMapper.fromDto(taskListDto));
		
		return taskListMapper.toDto(createdTaskList); 
	}
	
	@GetMapping(path = "/{task_list_id}")
	public Optional<TaskListDto> getTaskList(@PathVariable("task_list_id") UUID taskListId) {
		return taskListService.getTaskList(taskListId).map(taskListMapper::toDto);
	}
	
	@PutMapping(path = "/{task_list_id}")
	public TaskListDto updateTaskList(@PathVariable("task_list_id") UUID taskListId, 
			@RequestBody TaskListDto taskListDto) {
		TaskList updatedTaskList = taskListService.updateTaskList(taskListId, taskListMapper.fromDto(taskListDto));
		
		return taskListMapper.toDto(updatedTaskList);
	}
	
	@DeleteMapping(path = "/{task_list_id}")
	public void deleteTaskList(@PathVariable("task_list_id") UUID taskListId) {
		taskListService.deleteTaskList(taskListId);
	}

}
