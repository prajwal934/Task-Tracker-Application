package com.praj.track.dto;

import java.util.*;

public record TaskListDto(
		
		UUID id,
		String title,
		String description,
		Integer count,
		Double progress,
		List<TaskDto> tasks
		
		) {

}
