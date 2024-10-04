package com.alquilercoches.coches.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alquilercoches.coches.models.entities.Cliente;

public interface ClienteDAO extends JpaRepository<Cliente, Long> {
    // JpaRepository already provides findAll(), findById(), save(), deleteById(), etc.
}
