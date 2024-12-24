package com.praj.track.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "task_lists")
public class TaskList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id" , updatable = false, nullable = false)
	private UUID id;
	
	@Column(name = "title" ,nullable = false)
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@OneToMany(mappedBy = "taskList" , cascade = {CascadeType.REMOVE , CascadeType.PERSIST})
	private List<Task> tasks;
	
	@Column(name = "created" , nullable = false)
	private LocalDateTime created;
	
	@Column(name = "updated" , nullable = false)
	private LocalDateTime updated;
	

}
