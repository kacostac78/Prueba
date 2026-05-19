package com.futbol.app.repository;

import com.futbol.app.entity.Competicion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompeticionRepository extends JpaRepository<Competicion, Long> {}