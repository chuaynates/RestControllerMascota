package com.huaynates.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huaynates.entities.Mascotas;
import com.huaynates.entities.Usuario;
import com.huaynates.repositories.MascotasRepository;
import com.huaynates.repositories.UsuarioRepository;

@Service
@Transactional
public class MascotasServiceImpl implements MascotasService {

	@Autowired
	private MascotasRepository mascotasRepository;
	
	@Override
	public List<Mascotas> findAll() {
		return mascotasRepository.findAll();
	}

	@Override
	public Mascotas findById(Long id) {
		return mascotasRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}

	@Override
	public void save(Mascotas mascotas) {
		mascotasRepository.save(mascotas);
	}

	@Override
	public void deleteById(Long id) {
		mascotasRepository.deleteById(id);
	}
}
