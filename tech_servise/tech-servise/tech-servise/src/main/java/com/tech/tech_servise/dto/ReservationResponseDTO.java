package com.tech.tech_servise.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.tech.tech_servise.constants.GenderClient;
import com.tech.tech_servise.constants.TypeClient;

import java.time.LocalDateTime;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ReservationResponseDTO (

        Long id,
        LocalDateTime dateReservation,
        LocalDateTime dateCreateReservation,
        List<Long> idServices,
        String manger,
        String client,
        Long userId,
       // TypeClient typeClient,
        GenderClient genderClient
)

{
}
