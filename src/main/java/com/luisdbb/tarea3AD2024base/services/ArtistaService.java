package com.luisdbb.tarea3AD2024base.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisdbb.tarea3AD2024base.modelo.Artista;
import com.luisdbb.tarea3AD2024base.repositorios.ArtistaRepository;

/**
 * 
 * Servicio encargado de la gestión de artistas.
 * 
 * Proporciona operaciones de consulta y mantenimiento de artistas registrados
 * en el sistema.
 * 
 * @author Lucia Garcia
 * @version 1.0
 */
@Service
public class ArtistaService {

	@Autowired
	private ArtistaRepository artistaRepository;

	public List<Artista> findAll() {
		return artistaRepository.findAll();
	}

	public Artista guardar(Artista artista) {
		validarEspecialidades(artista);
		return artistaRepository.save(artista);
	}

	public void delete(Long id) {
		artistaRepository.deleteById(id);
	}

	public List<Artista> listarTodos() {
		return artistaRepository.findAll();
	}

	private void validarEspecialidades(Artista artista) {
		if (artista.getEspecialidades() == null || artista.getEspecialidades().isEmpty()) {
			throw new RuntimeException("Debe seleccionar al menos una especialidad");
		}
	}

}
