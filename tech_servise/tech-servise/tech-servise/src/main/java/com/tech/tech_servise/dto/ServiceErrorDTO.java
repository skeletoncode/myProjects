package com.tech.tech_servise.dto;

import java.time.LocalDateTime;

public record ServiceErrorDTO(String message, LocalDateTime timestamp) {
}
