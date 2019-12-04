package com.huaynates.services;

import java.util.List;

import com.huaynates.entities.Mascotas;


public interface MascotasService {

	public List<Mascotas> findAll();
	public Mascotas findById(Long id);
	public void save (Mascotas mascotas);
	public void deleteById(Long id);
}
