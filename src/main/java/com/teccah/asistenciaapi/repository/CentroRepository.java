package com.teccah.asistenciaapi.repository;

import com.teccah.asistenciaapi.entity.Centro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroRepository extends JpaRepository<Centro, Long> {
}
