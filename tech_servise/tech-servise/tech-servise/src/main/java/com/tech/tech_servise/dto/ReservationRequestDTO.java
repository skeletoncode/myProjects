package com.tech.tech_servise.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

//        @NotBlank  дата не может быть пустой строкой
        @Future
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime dateReservation,

//        @NotBlank дата не может быть пустой строкой
        @Past
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime dateCreateReservation,

//        @NotNull @NotBlank достаточно передать список идентификаторов услуг, а не целые обьекты
//        List<ServiceResponseDTO> services,
        List<Integer> services,

        @NotNull @NotBlank
        @NotEmpty
        String manger,

        @NotNull @NotBlank @NotEmpty
        String client,

        @NotNull
//        @NotBlank enum не могут быть пустой строкой
        TypeClient typeClient,

        @NotNull
//        @NotBlank @NotEmpty enum не могут быть пустой строкой
        GenderClient genderClient
)

{
}
