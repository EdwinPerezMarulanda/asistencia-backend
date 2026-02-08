package com.teccah.asistenciaapi.service;

import com.teccah.asistenciaapi.dto.AsistenciaDTO;
import com.teccah.asistenciaapi.dto.CrearAsistenciaRequest;
import com.teccah.asistenciaapi.entity.Asistencia;
import com.teccah.asistenciaapi.entity.Centro;
import com.teccah.asistenciaapi.entity.Estudiante;
import com.teccah.asistenciaapi.repository.AsistenciaRepository;
import com.teccah.asistenciaapi.repository.CentroRepository;
import com.teccah.asistenciaapi.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CentroRepository centroRepository;

    public List<AsistenciaDTO> consultarAsistencia(String jornada, String curso, String documento) {
        List<Asistencia> asistencias = asistenciaRepository.findByJornadaCursoDocumento(jornada, curso, documento);
        return convertirADTO(asistencias);
    }

    public List<AsistenciaDTO> listarPorFecha(LocalDate fecha) {
        List<Asistencia> asistencias = asistenciaRepository.findByFecha(fecha);
        return convertirADTO(asistencias);
    }

    public AsistenciaDTO crear(CrearAsistenciaRequest request) {
        Estudiante estudiante = estudianteRepository.findById(request.getEstudianteId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Centro centro = centroRepository.findById(request.getCentroId())
                .orElseThrow(() -> new RuntimeException("Centro no encontrado"));

        Asistencia asistencia = new Asistencia();
        asistencia.setEstudiante(estudiante);
        asistencia.setCentro(centro);
        asistencia.setFecha(request.getFecha());
        asistencia.setActivo(request.getAsistencia());
        asistencia.setObservacion(request.getObservacion());

        Asistencia guardada = asistenciaRepository.save(asistencia);
        return convertirADTO(guardada);
    }

    public AsistenciaDTO actualizar(Long id, CrearAsistenciaRequest request) {
        Asistencia asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asistencia no encontrada"));

        if (request.getEstudianteId() != null) {
            Estudiante estudiante = estudianteRepository.findById(request.getEstudianteId())
                    .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
            asistencia.setEstudiante(estudiante);
        }

        if (request.getCentroId() != null) {
            Centro centro = centroRepository.findById(request.getCentroId())
                    .orElseThrow(() -> new RuntimeException("Centro no encontrado"));
            asistencia.setCentro(centro);
        }

        if (request.getFecha() != null) asistencia.setFecha(request.getFecha());
        if (request.getAsistencia() != null) asistencia.setActivo(request.getAsistencia());
        if (request.getObservacion() != null) asistencia.setObservacion(request.getObservacion());

        Asistencia actualizada = asistenciaRepository.save(asistencia);
        return convertirADTO(actualizada);
    }

    public void eliminar(Long id) {
        asistenciaRepository.deleteById(id);
    }

    private List<AsistenciaDTO> convertirADTO(List<Asistencia> asistencias) {
        return asistencias.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private AsistenciaDTO convertirADTO(Asistencia a) {
        AsistenciaDTO dto = new AsistenciaDTO();
        dto.setId(a.getId());
        dto.setFecha(a.getFecha());
        dto.setAsistencia(a.getActivo());
        dto.setObservacion(a.getObservacion());
        dto.setCentroInteres(a.getCentro().getNombre());
        dto.setCentroId(a.getCentro().getId());
        dto.setEstudianteId(a.getEstudiante().getId());
        dto.setEstudianteNombre(a.getEstudiante().getNombres() + " " + a.getEstudiante().getApellidos());
        dto.setEstudianteDocumento(a.getEstudiante().getDocumento());
        dto.setEstudianteCurso(a.getEstudiante().getCurso());
        dto.setEstudianteJornada(a.getEstudiante().getJornada());
        return dto;
    }
}
