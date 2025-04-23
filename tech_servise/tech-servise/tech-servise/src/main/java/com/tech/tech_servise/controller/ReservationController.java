package com.tech.tech_servise.controller;


import com.tech.tech_servise.dto.ReservationRequestDTO;
import com.tech.tech_servise.dto.ReservationResponseDTO;
import com.tech.tech_servise.dto.ServiceResponseDTO;
import com.tech.tech_servise.service.ServiceReservation;
import com.tech.tech_servise.service.ServiceServ;
import jakarta.servlet.http.HttpServlet;
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
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Validated
@Slf4j
@RequestMapping("/api/v1/reservation")
@RestController
public class ReservationController {

    private final ServiceReservation reservation;

    // как оттестировать данный метод, не составить запрос в POSTMAN
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createReservation(@Valid @RequestBody ReservationRequestDTO reservationRequestDTO, HttpServletRequest httpServletRequest) {
        log.debug("POST request. Reservation {}", reservationRequestDTO);
        URI uri = URI.create("/api/v1/reservation" + reservation.createReservation(reservationRequestDTO));
        return ResponseEntity.created(uri).build();
    }

    // пример LocalDateTime : 2025-05-02T11:02:53.236+00:00
    @PostMapping(value ="/cancel", produces = "application/json")
    public ResponseEntity<Void> cancelReservation(@Positive @RequestParam int idReservation, HttpServletRequest httpServletRequest) {
        log.debug("POST request. cancel Reservation {}", idReservation);
        reservation.cancelReservation(idReservation);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/changeReservation", produces = "application/json")
    public ResponseEntity<LocalDateTime> changeReservation(@Positive @RequestParam int id, @NotNull @Future LocalDateTime dateTimeNew) {
        log.debug("POST request. change Reservation, new dateTime {}", dateTimeNew);
        return new ResponseEntity<>(reservation.changeReservationTime(id, dateTimeNew),HttpStatus.OK);
    }

    // получение брони по дате и времени
    @GetMapping(path = "/allReservations", produces = "application/json")
    public List<ReservationResponseDTO> getAllReservations(LocalDateTime fromDateReservation,
                                                           LocalDateTime toDateReservation) {
        log.debug("GET allReservations");
        return reservation.getReservations(fromDateReservation, toDateReservation);
    }

    @GetMapping(path = "/{idReservation}", produces = "application/json")
    public List<ServiceResponseDTO> getServicesByReservation(@Positive @PathVariable int idReservation) {
        log.debug("GET servicesByReservation id = {}", idReservation);
        return reservation.getServicesByReservationId(idReservation);
    }
}
