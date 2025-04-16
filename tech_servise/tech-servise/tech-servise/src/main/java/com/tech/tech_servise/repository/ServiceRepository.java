package com.tech.tech_servise.repository;

import com.tech.tech_servise.entity.ServiceType;
import com.tech.tech_servise.entity.Servise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Servise,Long> {
   // AndIsActiveTrue -- можно не писать true --  AndIsActive
    // в чем тут ошибка
    List<Servise> findAllByServiceTypeNameLatAndIsActiveTrue(String nameLat);

    @Query(nativeQuery = true,
    value = "SELECT * FROM service " +
            "WHERE (:serviceType == NULL OR type == :serviceType)  AND " +
            "(:serviceStatus == NULL OR serviceStatus == :serviceStatus) AND " +
            "(:fromDateService == NULL OR createSrv > == :fromDateService) AND " +
            "(:to == NULL OR createSrv < == :to)" +
            "is_active == TRUE")
    List<Servise> findByFilter(ServiceType serviceType,
                               LocalDateTime fromDateService, LocalDateTime to,
                               Boolean serviceStatus);
}
