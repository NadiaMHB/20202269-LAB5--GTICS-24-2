package org.example.lab520202269.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name="lugares")
public class Lugares {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLugares")
    private Integer id;

    @Column(name = "nombre_lugar")
    private String nombreLugar;

    @Column(name = "descrip_lugar")
    private String descripLugar;

    @Column(name ="fecha_lugar")
    private LocalDate fechaLugar;

}
