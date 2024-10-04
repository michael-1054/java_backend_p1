package com.alquilercoches.coches.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.alquilercoches.coches.models.dao.CocheDAO;
import com.alquilercoches.coches.models.entities.Coche;


@Service
public class CocheServiceImpl implements CocheService {

	private final CocheDAO cocheDAO;
	
	
	
	public CocheServiceImpl(
			
			CocheDAO cocheDAO
			) {
		
		this.cocheDAO = cocheDAO;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Coche> findAll() {
		return cocheDAO.findAll(); // Ahora este método está disponible en JpaRepository
	}


	@Transactional(readOnly=true)
	@Override
	public Page<Coche> findAll(Pageable pageable) {
		return cocheDAO.findAll(pageable);
	}

	@Transactional(readOnly=true)
	@Override
	public Coche findOne(Long id) {
		return cocheDAO.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void save(Coche coche) {
		cocheDAO.save(coche);
	}

	@Transactional
	@Override
	public void remove(Long id) {
		cocheDAO.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Long count() {
		return cocheDAO.count();
	}
	
	
	
	
}
