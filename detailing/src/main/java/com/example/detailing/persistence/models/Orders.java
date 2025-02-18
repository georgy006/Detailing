package com.example.detailing.persistence.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "CarID", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "ServiceID", nullable = false)
    private Services services;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private Users user;
}

