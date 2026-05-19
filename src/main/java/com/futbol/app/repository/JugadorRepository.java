package com.futbol.app.repository;

import com.futbol.app.entity.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {}