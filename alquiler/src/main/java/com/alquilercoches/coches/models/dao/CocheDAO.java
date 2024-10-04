package com.alquilercoches.coches.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alquilercoches.coches.models.entities.Coche;

public interface CocheDAO extends JpaRepository<Coche, Long> {
    // JpaRepository ya proporciona findAll(), findById(), save(), deleteById(), etc.
}
