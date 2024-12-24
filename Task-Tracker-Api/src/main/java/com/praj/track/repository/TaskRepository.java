package com.praj.track.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.praj.track.model.Task;

public interface TaskRepository extends JpaRepository<Task, UUID>{
	
	List<Task> findByTaskListId(UUID taskListId);
	
	Optional<Task> findByTaskListIdAndId(UUID tasklistId, UUID id);
	
	void deleteByTaskListIdAndId(UUID taskListId, UUID id);

}
