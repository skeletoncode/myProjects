package com.tech.tech_servise.repository;

import com.tech.tech_servise.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByClient(String clientName);
    List<Reservation> findAllByManger(String managerName);

    // какая-то ошибка здесь
  //  List<Reservation> findAllByReservationTypeClientTypeClientLatAndIsActiveTrue(String nameLat);

    @Query(nativeQuery = true,
    value = "SELECT * FROM reservation" +
    "WHERE (:fromDataReservation == NULL OR dateReservation > :fromDateReservation) AND " +
            "(:toDateReservation == NULL OR dateReservation < :toDateReservation ) AND" +
            "isActive == TRUE")
    List<Reservation> findByFilter(LocalDateTime fromDateReservation,
                                   LocalDateTime toDateReservation);

}
