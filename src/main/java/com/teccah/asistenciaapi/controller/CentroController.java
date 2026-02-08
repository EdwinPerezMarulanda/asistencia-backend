package com.teccah.asistenciaapi.controller;

import com.teccah.asistenciaapi.entity.Centro;
import com.teccah.asistenciaapi.repository.CentroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/centros")

public class CentroController {

    @Autowired
    private CentroRepository centroRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public ResponseEntity<List<Centro>> listarTodos() {
        return ResponseEntity.ok(centroRepository.findAll());
    }
}
