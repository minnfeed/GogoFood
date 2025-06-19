package com.example.springbootservices.model.entites;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;

    private String note;

}