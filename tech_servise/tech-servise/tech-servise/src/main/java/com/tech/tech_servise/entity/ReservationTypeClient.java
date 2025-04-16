package com.tech.tech_servise.entity;

import com.tech.tech_servise.constants.TypeService;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservationTypeClient")
public class ReservationTypeClient {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "typeServiceLat", nullable = false)
    private TypeService typeServiveLat;

    @Column(name = "typeServiceRus", nullable = false)
    private String typeServiceRus;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

}
