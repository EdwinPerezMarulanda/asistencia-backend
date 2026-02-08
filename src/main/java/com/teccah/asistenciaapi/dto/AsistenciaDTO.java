package com.teccah.asistenciaapi.dto;

import com.teccah.asistenciaapi.entity.EstadoAsistencia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsistenciaDTO {
    private Long id;
    private LocalDate fecha;
    private EstadoAsistencia asistencia;
    private String observacion;
    private String centroInteres;
    private Long centroId;
    private Long estudianteId;
    private String estudianteNombre;
    private String estudianteDocumento;
    private String estudianteCurso;
    private String estudianteJornada;
}
