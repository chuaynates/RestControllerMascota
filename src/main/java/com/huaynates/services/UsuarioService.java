package com.huaynates.services;

import java.util.List;

import com.huaynates.entities.Usuario;

public interface UsuarioService {
	public List<Usuario> findAll();
	public Usuario findById(Long id);
	public void save (Usuario usuario);
	public void deleteById(Long id);

}
