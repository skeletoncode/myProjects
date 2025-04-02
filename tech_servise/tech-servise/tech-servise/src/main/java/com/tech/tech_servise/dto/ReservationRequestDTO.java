package com.tech.tech_servise.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.tech.tech_servise.constants.GenderClient;
import com.tech.tech_servise.constants.TypeClient;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ReservationRequestDTO (

        @Positive
        int id,

        @NotBlank
        @Future
        LocalDateTime dateReservation,

        @NotBlank
        @Past
        LocalDateTime dateCreateReservation,

        @NotNull @NotBlank
        List<ServiceResponseDTO> services,

        @NotNull @NotBlank
        @NotEmpty
        String manger,

        @NotNull @NotBlank @NotEmpty
        String client,

        @NotNull @NotBlank
        TypeClient typeClient,

        @NotNull @NotBlank @NotEmpty
        GenderClient genderClient
)

{
}
