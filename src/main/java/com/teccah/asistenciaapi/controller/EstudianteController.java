package com.teccah.asistenciaapi.controller;

import com.teccah.asistenciaapi.entity.Estudiante;
import com.teccah.asistenciaapi.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public List<Estudiante> listar() {

        List<Estudiante> lista = estudianteService.listarTodos();

        System.out.println("ESTUDIANTES EN BD: " + lista.size());

        lista.forEach(e -> {
            System.out.println(e.getId() + " - " + e.getNombres());
        });

        return lista;
    }
}