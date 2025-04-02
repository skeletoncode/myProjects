package com.tech.tech_servise.dto;

import com.tech.tech_servise.constants.GenderClient;
import com.tech.tech_servise.constants.TypeClient;

import java.time.LocalDateTime;
import java.util.List;

public record ReservationResponseDTO (

        int id,
        LocalDateTime dateReservation,
        LocalDateTime dateCreateReservation,
        List<ServiceResponseDTO> services,
        String manger,
        String client,
        TypeClient typeClient,
        GenderClient genderClient
)

{
}
