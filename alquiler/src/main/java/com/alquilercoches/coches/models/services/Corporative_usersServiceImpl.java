package com.alquilercoches.coches.models.services;

import com.alquilercoches.coches.models.dao.Corporative_usersDAO;
import com.alquilercoches.coches.models.entities.Corporative_users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class Corporative_usersServiceImpl implements Corporative_usersService {

	private final Corporative_usersDAO corporative_usersDAO;



	public Corporative_usersServiceImpl(

			Corporative_usersDAO corporative_usersDAO
			) {
		
		this.corporative_usersDAO = corporative_usersDAO;
	}

	@Transactional(readOnly=true)
	@Override
	public List<Corporative_users> findAll() {
		return (List<Corporative_users>) corporative_usersDAO.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Page<Corporative_users> findAll(Pageable pageable) {
		return corporative_usersDAO.findAll(pageable);
	}

	@Transactional(readOnly=true)
	@Override
	public Corporative_users findOne(Long id) {
		return corporative_usersDAO.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void save(Corporative_users corporativeUsers) {
		corporative_usersDAO.save(corporativeUsers);
	}

	@Transactional
	@Override
	public void remove(Long id) {
		corporative_usersDAO.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Long count() {
		return corporative_usersDAO.count();
	}
	
	
	
	
}
