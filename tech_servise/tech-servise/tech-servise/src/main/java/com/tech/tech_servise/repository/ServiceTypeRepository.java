package com.tech.tech_servise.repository;

import com.tech.tech_servise.constants.TypeService;
import com.tech.tech_servise.entity.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long> {
    // получить все виды сервиса по категории
    Optional<ServiceType> findAllByNameLat(TypeService nameLat);

}
