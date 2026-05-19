package com.futbol.app.repository;

import com.futbol.app.entity.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {}