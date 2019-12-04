package com.huaynates.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huaynates.entities.Mascotas;


public interface MascotasRepository extends CrudRepository<Mascotas, Long> {
	
	@Override
	List<Mascotas> findAll();

}
