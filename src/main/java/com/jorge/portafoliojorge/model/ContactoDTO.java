package com.jorge.portafoliojorge.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "M_Contacto")

public class ContactoDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String correo;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String mensaje;
    private LocalDateTime fechaEnvio;

    @PrePersist

    protected void onCreat(){
        this.fechaEnvio= LocalDateTime.now();
    }

}
