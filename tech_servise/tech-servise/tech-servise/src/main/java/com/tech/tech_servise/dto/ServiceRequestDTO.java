package com.tech.tech_servise.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ServiceRequestDTO {

    @NotNull
    @NotBlank
    @NotEmpty
    private String serviceName;


    @NotBlank
    @NotEmpty
    @NotBlank
    private String master;
}
