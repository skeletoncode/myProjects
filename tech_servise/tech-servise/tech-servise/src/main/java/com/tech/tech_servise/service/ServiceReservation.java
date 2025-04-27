package com.tech.tech_servise.service;

import com.tech.tech_servise.constants.TypeClient;
import com.tech.tech_servise.dto.ReservationRequestDTO;
import com.tech.tech_servise.dto.ReservationResponseDTO;
import com.tech.tech_servise.dto.ServiceResponseDTO;
import com.tech.tech_servise.entity.Reservation;
import com.tech.tech_servise.entity.ReservationTypeClient;
import com.tech.tech_servise.mapper.ReservationMapper;
import com.tech.tech_servise.repository.ReservationRepository;
import com.tech.tech_servise.repository.ReservationTypeClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
@Service
public class ServiceReservation {
    private final ReservationRepository reservationRepository;
    private final ReservationTypeClientRepository reservationTypeClientRepository;
    private final ReservationMapper reservationMapper;


    public Long createReservation(ReservationRequestDTO reservationRequestDTO) {
        ReservationTypeClient reservationTypeClient = reservationTypeClientRepository
                .findAllByTypeClientLat(reservationRequestDTO.typeClient())
                .get();
        Reservation reservation = reservationMapper.mapToEntity(reservationRequestDTO);
        reservation.setReservationType(reservationTypeClient);
        reservationRepository.save(reservation);
        // TODO :: create Reservation in database
        return reservation.getId();
    }
    public void cancelReservation(int reservationId) {
        // TODO :: cancel(delete) Reservation in database
    }
    public LocalDateTime changeReservationTime(int reservationId, LocalDateTime reservationTime) {
        // TODO :: change Reservation time in database
        return null;
    }
    public List<ReservationResponseDTO> getReservations() {
       List<Reservation> reservations = reservationRepository
               .findAll();

        // TODO :: create get all reservations from database
        return reservations.stream().map(reservationMapper::mapToDTO).toList();
    }

    public List<ReservationResponseDTO> getReservationLocalDatetime(LocalDateTime fromDateReservation,
                                                                    LocalDateTime toDateReservation ) {
        List<Reservation> reservations = reservationRepository
                .findByFilter(fromDateReservation, toDateReservation);

        return reservations.stream().map(reservationMapper::mapToDTO).toList();
    }


    public ReservationResponseDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .get();
        // TODO :: create get Services by Reservation Id from database
        return reservationMapper.mapToDTO(reservation);
    }
    public List<ReservationResponseDTO> getReservationByManager(String managerName) {
        List<Reservation> reservations = reservationRepository
                .findAllByManger(managerName);
        return reservations.stream().map(reservationMapper::mapToDTO).toList();
    }

    public List<ReservationResponseDTO> getReservationByClient(String clientName) {
        List<Reservation> reservations = reservationRepository
                .findAllByClient(clientName);
        return reservations.stream().map(reservationMapper::mapToDTO).toList();
    }

    public List<ReservationResponseDTO> getReservationByType( TypeClient typeClient) {
        List<Reservation> reservations = reservationRepository
                .findAllByReservationTypeClientTypeClientLatAndIsActiveTrue(typeClient.toString());
        return reservations.stream().map(reservationMapper::mapToDTO).toList();
    }

}
