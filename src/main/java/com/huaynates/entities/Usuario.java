package com.huaynates.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String correo;
	private String contraseña;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="usuario_id",referencedColumnName="id")
	private List<Mascotas> mascota=new ArrayList<>();
	
	
	public List<Mascotas> getMascota() {
		return mascota;
	}
	public void setMascota(List<Mascotas> mascota) {
		this.mascota = mascota;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	@Override
	public String toString() {
		return "Usuario [id=" + id+ ", nombre=" + nombre + ", correo=" + correo + ", contraseña="
				+ contraseña + "]";
	}
	
	
	
	
}
