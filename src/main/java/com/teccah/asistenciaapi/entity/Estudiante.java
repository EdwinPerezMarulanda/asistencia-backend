package com.teccah.asistenciaapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "estudiantes")
@Getter
@Setter
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String documento;

    @Column(nullable = false, length = 80)
    private String apellidos;

    @Column(nullable = false, length = 80)
    private String nombres;

    @Column(nullable = false, length = 20)
    private String jornada;

    @Column(nullable = false, length = 10)
    private String curso;

    public Estudiante() {
    }

    public Estudiante(String nombres, String apellidos, String documento) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.documento = documento;
    }
}
