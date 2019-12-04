package com.huaynates.controllers;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huaynates.entities.Usuario;
import com.huaynates.services.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {
	
private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

	@Value("${app.storage.path}")
	private String STORAGEPATH;

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public List<Usuario> usuarios() {
		logger.info("call usuarios");
		
		List<Usuario> usuarios = usuarioService.findAll();
		logger.info("usuarios: " + usuarios);
		
		return usuarios;
	}
	
	
	@PostMapping("/usuarios")
	public Usuario crear(@RequestParam("nombre") String nombre, @RequestParam("correo") String correo, @RequestParam("contraseña") String contraseña) throws Exception {
		logger.info("call crear(" + nombre + ", " + correo + ", " + contraseña + ")");
		
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setCorreo(correo);
		usuario.setContraseña(contraseña);
		
		
		usuarioService.save(usuario);
		
		return usuario;
	}

}
