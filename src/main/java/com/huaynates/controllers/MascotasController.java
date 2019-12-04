package com.huaynates.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.huaynates.entities.Mascotas;

import com.huaynates.services.MascotasService;

@RestController
@RequestMapping("/api")
public class MascotasController {
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	@Value("${app.storage.path}")
	private String STORAGEPATH;

	@Autowired
	private MascotasService mascotasService;
	
	@GetMapping("/mascotas")
	public List<Mascotas> mascotas() {
		logger.info("call mascotas");
		
		List<Mascotas> mascotas = mascotasService.findAll();
		logger.info("mascotas: " + mascotas);
		
		return mascotas;
	}
	
	@GetMapping("/mascotas/images/{filename:.+}")
	public ResponseEntity<Resource> files(@PathVariable String filename) throws Exception{
		logger.info("call images: " + filename);
		
		Path path = Paths.get(STORAGEPATH).resolve(filename);
		logger.info("Path: " + path);
		
		if(!Files.exists(path)) {
			return ResponseEntity.notFound().build();
		}
		
		Resource resource = new UrlResource(path.toUri());
		logger.info("Resource: " + resource);
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\""+resource.getFilename()+"\"")
				.header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(Paths.get(STORAGEPATH).resolve(filename)))
				.header(HttpHeaders.CONTENT_LENGTH, String.valueOf(resource.contentLength()))
				.body(resource);
	}
	
	@PostMapping("/mascotas")
	public Mascotas crear(@RequestParam(name="foto", required=false) MultipartFile foto, @RequestParam("nombre") String nombre, @RequestParam("raza") String raza, @RequestParam("edad") Integer edad, @RequestParam("usuario_id") Long usuario_id) throws Exception {
		logger.info("call crear(" + nombre + ", " + raza + ", " + edad + ", " + foto + ", "+usuario_id+ ")");
		
		Mascotas mascotas = new Mascotas();
		mascotas.setNombre(nombre);
		mascotas.setRaza(raza);
		mascotas.setEdad(edad);
		mascotas.setUsuario_id(usuario_id);
		
		
		if (foto != null && !foto.isEmpty()) {
			String filename = System.currentTimeMillis() + foto.getOriginalFilename().substring(foto.getOriginalFilename().lastIndexOf("."));
			mascotas.setFoto(filename);
			if(Files.notExists(Paths.get(STORAGEPATH))){
		        Files.createDirectories(Paths.get(STORAGEPATH));
		    }
			Files.copy(foto.getInputStream(), Paths.get(STORAGEPATH).resolve(filename));
		}
		
		mascotasService.save(mascotas);
		
		return mascotas;
	}
	
	
}
