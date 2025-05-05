package com.tech.tech_servise.controller;


import com.tech.tech_servise.dto.ReservationRequestDTO;
import com.tech.tech_servise.dto.ReservationResponseDTO;
import com.tech.tech_servise.service.ServiceReservation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Validated
@Slf4j
@RequestMapping("/api/v1/reservation")
@RestController
public class ReservationController {

    private final ServiceReservation reservation;
    private final ServiceReservation serviceReservation;


    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createReservation(@Valid @RequestBody ReservationRequestDTO reservationRequestDTO, HttpServletRequest httpServletRequest) {
        log.debug("POST request. Reservation {}", reservationRequestDTO);
        URI uri = URI.create("/api/v1/reservation" + reservation.createReservation(reservationRequestDTO));
        return ResponseEntity.created(uri).build();
    }

    // пример LocalDateTime : 2025-05-02T11:02:53.236+00:00
    @PostMapping(value ="/cancel", produces = "application/json")
    public ResponseEntity<Void> cancelReservation(@Positive @RequestParam Long idReservation, HttpServletRequest httpServletRequest) {
        log.debug("POST request. cancel Reservation {}", idReservation);
        reservation.cancelReservation(idReservation);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/changeReservation", produces = "application/json")
    public ResponseEntity<LocalDateTime> changeReservation(@Positive @RequestParam Long id, @NotNull @Future LocalDateTime dateTimeNew) {
        log.debug("POST request. change Reservation, new dateTime {}", dateTimeNew);
        return new ResponseEntity<>(reservation.changeReservationTime(id, dateTimeNew),HttpStatus.OK);
    }


    @GetMapping(path = "/allReservations", produces = "application/json")
    public List<ReservationResponseDTO> getAllReservations(){
        log.debug("GET allReservations");
        return reservation.getReservations();
    }
    // получение брони по дате и времени
    @GetMapping(path = "/localDateTimeReservations", produces = "application/json")
    public List<ReservationResponseDTO> getLocalDatetimeReservations(LocalDateTime fromDateReservation,
                                                                    LocalDateTime toDateReservation) {
        log.debug("GET localDatetimeReservations");
        return reservation.getReservationLocalDatetime(fromDateReservation, toDateReservation);
    }



    @GetMapping(path = "/{idReservation}", produces = "application/json")
    public ReservationResponseDTO getServicesByReservation(@Positive @PathVariable Long idReservation) {
        log.debug("GET servicesByReservation id = {}", idReservation);
        return reservation.getReservationById(idReservation);
    }

    @GetMapping(path = "/dayPrice", produces = "application/json")
    public Map<LocalDate, Double> getDayPrice(LocalDateTime fromDateReservation, LocalDateTime toDateReservation) {
        log.debug("Get dayPrice day : price");
        return serviceReservation.getDayPrice(fromDateReservation,toDateReservation);
    }

}
