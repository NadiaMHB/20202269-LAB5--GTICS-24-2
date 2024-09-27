package org.example.lab520202269.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "mascotas")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMascotas")
    private Integer id;

    @Column(name = "nombre_mascota")
    private String nombreMascota;

    @Column(name = "genero")
    private String genero;

    @Column(name = "edad")
    private String edad;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "Vacunado")
    private String vacunado;

    @Column(name = "Desparasitado")
    private String desparasitado;

    @ManyToOne
    @JoinColumn(name = "Persona_idPersona", nullable = false)
    private Persona persona;
}
