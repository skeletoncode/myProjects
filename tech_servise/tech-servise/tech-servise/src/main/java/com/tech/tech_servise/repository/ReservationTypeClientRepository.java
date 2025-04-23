package com.tech.tech_servise.repository;

import com.tech.tech_servise.constants.TypeClient;
import com.tech.tech_servise.entity.ReservationTypeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationTypeClientRepository extends JpaRepository <ReservationTypeClient, Long> {

    Optional<ReservationTypeClient> findAllByTypeClientLat(TypeClient type);


}
