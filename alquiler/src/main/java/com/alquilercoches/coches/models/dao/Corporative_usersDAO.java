package com.alquilercoches.coches.models.dao;

import com.alquilercoches.coches.models.entities.Corporative_users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Corporative_usersDAO extends JpaRepository<Corporative_users, Long> {
    // JpaRepository already provides findAll(), findById(), save(), deleteById(), etc.
}
