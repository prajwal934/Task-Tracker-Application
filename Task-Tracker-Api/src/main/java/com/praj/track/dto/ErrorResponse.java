package com.praj.track.dto;

public record ErrorResponse(
		
		int status,
		String message,
		String details
		
		) {
}
