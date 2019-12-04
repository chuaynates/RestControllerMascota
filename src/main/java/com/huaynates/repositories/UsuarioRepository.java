package com.huaynates.repositories;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huaynates.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario,Long> {
	@Override
	List<Usuario> findAll();
	

}
