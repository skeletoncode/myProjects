package com.tech.tech_servise.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.tech.tech_servise.constants.GenderClient;
import com.tech.tech_servise.constants.TypeClient;
import com.tech.tech_servise.constants.TypeService;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ServiceResponseDTO(
        String serviceName,
        String master,
        //@JsonProperty("service_price")
        int price,
        String description,
        TypeClient typeClient,
        TypeService typeService,
        LocalDateTime dateCreated,
        GenderClient genderClient
)
{
}
