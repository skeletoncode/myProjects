package com.tech.tech_servise.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tech.tech_servise.constants.GenderClient;
import com.tech.tech_servise.constants.TypeClient;
import com.tech.tech_servise.constants.TypeService;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reservation")
public class Reservation {

    @Column(name ="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // стратегия генерации Id
    private Long id;

    @Column(name = "dateReservation", nullable = false)
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateReservation;

    @ElementCollection
    @CollectionTable(name = "id_services")
    private List<Long> idServices;

    @Column(name = "manager")
    private String manger;

    @Column(name = "client")
    private String client;

    @Enumerated(EnumType.STRING) // преобразование Entity в сроку
    @Column(name = "gender", nullable = false)
    private GenderClient gender;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "is_active", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    private ReservationTypeClient reservationType;

}
