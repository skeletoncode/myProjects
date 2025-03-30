package com.tech.tech_servise.dto;

import com.tech.tech_servise.constants.Manager;
import com.tech.tech_servise.constants.ServiceName;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class ServiceRequestDTO {
    @NotNull
    @NotBlank
    @NotEmpty
    private String client;

    @NotNull
    private ServiceName serviceName;

    @NotNull
    private Manager manager;

    @Positive
    private int serviceId;

    @Positive
    private int price;

    @NotNull
    @Past
    private LocalDateTime acceptTo;


}
