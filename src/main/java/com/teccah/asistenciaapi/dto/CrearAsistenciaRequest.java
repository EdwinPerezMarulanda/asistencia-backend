package com.teccah.asistenciaapi.dto;

import com.teccah.asistenciaapi.entity.EstadoAsistencia;
import java.time.LocalDate;

public class CrearAsistenciaRequest {
    private Long estudianteId;
    private Long centroId;
    private LocalDate fecha;
    private EstadoAsistencia asistencia;
    private String observacion;

    public CrearAsistenciaRequest() {}

    // Getters
    public Long getEstudianteId() { return estudianteId; }
    public Long getCentroId() { return centroId; }
    public LocalDate getFecha() { return fecha; }
    public EstadoAsistencia getAsistencia() { return asistencia; }
    public String getObservacion() { return observacion; }

    // Setters
    public void setEstudianteId(Long estudianteId) { this.estudianteId = estudianteId; }
    public void setCentroId(Long centroId) { this.centroId = centroId; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setAsistencia(EstadoAsistencia asistencia) { this.asistencia = asistencia; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
}
