package com.tech.tech_servise.repository;

import com.tech.tech_servise.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
