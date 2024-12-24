package com.praj.track.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.praj.track.model.TaskList;

public interface TaskListRepository extends JpaRepository<TaskList, UUID> {

}
