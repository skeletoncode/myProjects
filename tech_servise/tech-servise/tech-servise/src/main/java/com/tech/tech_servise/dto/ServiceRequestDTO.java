package com.tech.tech_servise.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.tech.tech_servise.constants.GenderClient;
import com.tech.tech_servise.constants.TypeClient;
import com.tech.tech_servise.constants.TypeService;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ServiceRequestDTO (

    @NotNull
    @NotBlank
    @NotEmpty
    String serviceName,

    @NotBlank
    @NotEmpty
    @NotBlank
    String master,

    @Positive
    double price,

    @NotBlank
    @NotEmpty
    @NotBlank
    String description,

    @NotNull
    TypeService typeService,

    @NotNull
    @Past
    LocalDateTime dateCreated,

    @NotNull
    Boolean isActive
)
{
}
