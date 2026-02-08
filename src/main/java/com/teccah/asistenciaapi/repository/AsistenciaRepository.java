package com.teccah.asistenciaapi.repository;

import com.teccah.asistenciaapi.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {

    @Query(value = "SELECT a.* FROM asistencias a " +
            "JOIN estudiantes e ON e.id = a.estudiante_id " +
            "WHERE e.jornada = :jornada " +
            "AND e.curso = :curso " +
            "AND e.documento = :documento " +
            "ORDER BY a.fecha DESC",
            nativeQuery = true)
    List<Asistencia> findByJornadaCursoDocumento(
            @Param("jornada") String jornada,
            @Param("curso") String curso,
            @Param("documento") String documento
    );

    List<Asistencia> findByFecha(LocalDate fecha);
}