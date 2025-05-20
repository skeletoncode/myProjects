package com.tech.tech_servise.mapper;

import com.tech.tech_servise.dto.ReservationRequestDTO;
import com.tech.tech_servise.dto.ReservationResponseDTO;
import com.tech.tech_servise.entity.Reservation;
import org.springframework.stereotype.Service;

@Service
public class ReservationMapper {
    public Reservation mapToEntity(ReservationRequestDTO reservationRequestDTO) {

        return Reservation.builder()
                .dateReservation(reservationRequestDTO.dateReservation())
                .idServices(reservationRequestDTO.idServices())
                .manger(reservationRequestDTO.manger())
                .client(reservationRequestDTO.client())
                .userId(reservationRequestDTO.UserId())
                .gender(reservationRequestDTO.genderClient())
                .isActive(reservationRequestDTO.isActive())
                .build();
    }

    public ReservationResponseDTO mapToDTO(Reservation reservation) {
        return new ReservationResponseDTO(
                reservation.getId(),
                reservation.getDateReservation(),
                reservation.getCreatedAt(),
                reservation.getIdServices(),
                reservation.getManger(),
                reservation.getClient(),
                reservation.getUserId(),
                reservation.getGender()

        );

    }




}
