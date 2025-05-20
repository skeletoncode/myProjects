package com.tech.tech_servise.service;

import com.tech.tech_servise.dto.ReservationRequestDTO;
import com.tech.tech_servise.dto.ReservationResponseDTO;
import com.tech.tech_servise.entity.Reservation;
import com.tech.tech_servise.entity.ReservationTypeClient;
import com.tech.tech_servise.entity.Servise;
import com.tech.tech_servise.exceptions.ReservationException;
import com.tech.tech_servise.mapper.ReservationMapper;
import com.tech.tech_servise.repository.ReservationRepository;
import com.tech.tech_servise.repository.ReservationTypeClientRepository;
import com.tech.tech_servise.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class ServiceReservation {
    private final ReservationRepository reservationRepository;
    private final ReservationTypeClientRepository reservationTypeClientRepository;
    private final ReservationMapper reservationMapper;
    private final ServiceRepository serviceRepository;


    public Long createReservation(ReservationRequestDTO reservationRequestDTO) {
        ReservationTypeClient reservationTypeClient = reservationTypeClientRepository
                .findAllByTypeClientLat(reservationRequestDTO.typeClient())
                .orElseThrow(() -> new ReservationException(HttpStatus.BAD_REQUEST, "Указанный тип клиента не существует"));
        Reservation reservation = reservationMapper.mapToEntity(reservationRequestDTO);
        reservation.setReservationType(reservationTypeClient);
        reservationRepository.save(reservation);
        // TODO :: create Reservation in database
        return reservation.getId();
    }
    public void cancelReservation(Long reservationId) {
       Reservation reservationCancel = reservationRepository.findById(reservationId)
               .orElseThrow(()-> new ReservationException(HttpStatus.NOT_FOUND, "Запись не найдена"));
        reservationCancel.setIsActive(false);
        reservationRepository.save(reservationCancel);
       // TODO :: cancel(delete) Reservation in database
    }
    public LocalDateTime changeReservationTime(Long reservationId, LocalDateTime reservationTimeChange) {
       Reservation reservationChangeTime = reservationRepository.findById(reservationId)
               .orElseThrow(() -> new ReservationException(HttpStatus.NOT_FOUND, "Запись не найдена"));
       reservationChangeTime.setDateReservation(reservationTimeChange);
       reservationRepository.save(reservationChangeTime);
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
                .orElseThrow(()-> new ReservationException(HttpStatus.BAD_REQUEST, "Запись не найдена"));
        // TODO :: create get Services by Reservation Id from database
        return reservationMapper.mapToDTO(reservation);
    }
    public List<ReservationResponseDTO> getReservationByManager(String managerName) {
        List<Reservation> reservations = reservationRepository
                .findAllByManger(managerName);
        return reservations.stream().map(reservationMapper::mapToDTO).toList();
    }
    public List<ReservationResponseDTO> getUserReservation (Long userId) {
        List<Reservation> reservations = reservationRepository
                .findByUserId(userId);
        return reservations.stream().map(reservationMapper::mapToDTO).toList();
    }

    public List<ReservationResponseDTO> getReservationByClient(String clientName) {
        List<Reservation> reservations = reservationRepository
                .findAllByClient(clientName);
        return reservations.stream().map(reservationMapper::mapToDTO).toList();
    }


    public Map<LocalDate, Double> getDayPrice(LocalDateTime fromDate, LocalDateTime toDate) {
        List<Reservation> reservations = reservationRepository
                .findByFilter(fromDate, toDate);
         Map<LocalDate, Double> mapDayPrice = new HashMap<>();
         while (fromDate.isBefore(toDate)) {
             for (Reservation reservation : reservations) {
               LocalDateTime dateReservation = reservation.getDateReservation();
               if(dateReservation.getDayOfMonth()==fromDate.getDayOfMonth() && dateReservation.getMonth()==fromDate.getMonth()){
                  List<Long> idReservations = reservation.getIdServices();
                  for (Long idReservation : idReservations)
                  {
                      if (serviceRepository.findById(idReservation).isPresent()) {
                    Servise service =   serviceRepository.findById(idReservation).get();
                      Double price2 = service.getPrice();
                      double sum = 0.0;
                       sum = sum + price2;
                    LocalDate dateMap = dateReservation.toLocalDate();

                      mapDayPrice.put(dateMap, sum);
                      }
                }
               }
             }
             fromDate = fromDate.plusDays(1);
         }
        return mapDayPrice;
    }

    // с методом репозитория чтото не то ?
//    public List<ReservationResponseDTO> getReservationByType( TypeClient typeClient) {
//        List<Reservation> reservations = reservationRepository
//                .findAllByReservationTypeClientTypeClientLatAndIsActiveTrue(typeClient.toString());
//        return reservations.stream().map(reservationMapper::mapToDTO).toList();
//    }

}
