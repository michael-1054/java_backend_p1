package com.alquilercoches.coches.models.services;

import com.alquilercoches.coches.models.entities.Corporative_users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Corporative_usersService {
	
	public List<Corporative_users> findAll();
	public Page<Corporative_users> findAll(Pageable pageable);
	public Corporative_users findOne(Long id);
	public void save(Corporative_users corporativeUsers);
	public void remove(Long id);
	public Long count();

	public void save(String user, String password);
	
	
	
}
