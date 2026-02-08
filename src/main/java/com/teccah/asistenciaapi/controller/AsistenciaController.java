package com.teccah.asistenciaapi.controller;

import com.teccah.asistenciaapi.dto.AsistenciaDTO;
import com.teccah.asistenciaapi.dto.CrearAsistenciaRequest;
import com.teccah.asistenciaapi.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/asistencia")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    // Consulta para padres (ya existente)
    @GetMapping(value = "/consultar", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public ResponseEntity<List<AsistenciaDTO>> consultarAsistencia(
            @RequestParam String jornada,
            @RequestParam String curso,
            @RequestParam String documento) {

        List<AsistenciaDTO> asistencias = asistenciaService.consultarAsistencia(jornada, curso, documento);
        return ResponseEntity.ok(asistencias);
    }

    // Listar asistencias por fecha (para profesores)
    @GetMapping(value = "/lista", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public ResponseEntity<List<AsistenciaDTO>> listarPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {

        List<AsistenciaDTO> asistencias = asistenciaService.listarPorFecha(fecha);
        return ResponseEntity.ok(asistencias);
    }

    // Crear nueva asistencia
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public ResponseEntity<AsistenciaDTO> crear(@RequestBody CrearAsistenciaRequest request) {
        AsistenciaDTO asistencia = asistenciaService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(asistencia);
    }

    // Actualizar asistencia
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public ResponseEntity<AsistenciaDTO> actualizar(
            @PathVariable Long id,
            @RequestBody CrearAsistenciaRequest request) {

        AsistenciaDTO asistencia = asistenciaService.actualizar(id, request);
        return ResponseEntity.ok(asistencia);
    }

    // Eliminar asistencia
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        asistenciaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}