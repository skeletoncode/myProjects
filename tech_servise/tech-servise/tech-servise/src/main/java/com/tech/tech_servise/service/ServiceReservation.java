package com.tech.tech_servise.service;

import com.tech.tech_servise.dto.ReservationRequestDTO;
import com.tech.tech_servise.dto.ReservationResponseDTO;
import com.tech.tech_servise.dto.ServiceResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceReservation {
    public Long createReservation(ReservationRequestDTO reservationRequestDTO) {
        // TODO :: create Reservation in database
        return null;
    }
    public void cancelReservation(int reservationId) {
        // TODO :: cancel(delete) Reservation in database
    }
    public LocalDateTime changeReservationTime(int reservationId, LocalDateTime reservationTime) {
        // TODO :: change Reservation time in database
        return null;
    }
    public List<ReservationResponseDTO> getReservations() {
        // TODO :: create get all reservations from database
        return null;
    }
    public List<ServiceResponseDTO> getServicesByReservationId(int reservationId) {
        // TODO :: create get Services by Reservation Id from database
        return null;
    }
}
