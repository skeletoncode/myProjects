package com.tech.tech_servise.entity;

import com.tech.tech_servise.constants.TypeClient;
import com.tech.tech_servise.constants.TypeService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "reservationTypeClient")
public class ReservationTypeClient {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "typeClientLat", nullable = false)
    private TypeClient typeClientLat;

    @Column(name = "name_rus", nullable = false)
    private String typeClientNameRus;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Column(name = "is_active", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isActive;

}
