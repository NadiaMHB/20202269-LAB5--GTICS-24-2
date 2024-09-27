package org.example.lab520202269.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "viajes")
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idViajes")
    private Integer id;

    @Column(name = "punto_recojo")
    private String puntoRecojo;

    @Column(name = "cant_personas")
    private String cantPersonas;

    @Column(name = "cant_perros")
    private String cantPerros;

    @ManyToOne
    @JoinColumn(name = "Persona_idPersona", nullable = false)
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "Lugares_idLugares", nullable = false)
    private Lugares lugares;
}
