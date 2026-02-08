package com.teccah.asistenciaapi.service;

import com.teccah.asistenciaapi.entity.Estudiante;
import com.teccah.asistenciaapi.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    //Listar todos
    public List<Estudiante> listarTodos() {
        return estudianteRepository.findAll();
    }

    //Buscar por ID
    public Optional<Estudiante> buscarPorId(Long id) {
        return estudianteRepository.findById(id);
    }

    //Buscar por documento
    public Optional<Estudiante> buscarPorDocumento(String documento) {
        return estudianteRepository.findByDocumento(documento);
    }

    //Guardar estudiante
    public Estudiante guardar(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

}