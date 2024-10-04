package com.alquilercoches.coches.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alquilercoches.coches.models.entities.Alquiler;

public interface AlquilerDAO extends JpaRepository<Alquiler, Long> {
    // JpaRepository ya proporciona los métodos básicos como findById, findAll, save, deleteById, etc.
}
