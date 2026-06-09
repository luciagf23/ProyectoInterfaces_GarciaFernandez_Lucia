package com.luisdbb.tarea3AD2024base.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisdbb.tarea3AD2024base.modelo.*;
import com.luisdbb.tarea3AD2024base.repositorios.*;

@Service
public class RegistroService {

	@Autowired
	private PersonaRepository personaRepository;

	@Autowired
	private CredencialRepository credencialRepository;

	// MÉTODO PRINCIPAL DE REGISTRO

	public Persona registrarPersona(Persona persona, Credencial credencial) {

		persona.setEmail(persona.getEmail().toLowerCase());

		validarDatosPersona(persona);
		validarEmail(persona);

		
		if (persona instanceof Coordinacion c) {
			validarCoordinacion(c);
		}

		// Validar credenciales
		validarCredenciales(credencial);

		// Normalizar username antes validar
		credencial.setUsername(credencial.getUsername().toLowerCase());

		// Validar username unico
		if (credencial.getId() == null) {
			if (credencialRepository.existsByUsername(credencial.getUsername())) {
				throw new RuntimeException("Username ya registrado");
			}
		}

		// Guardar persona
		Persona guardada = personaRepository.save(persona);

		// Guardar credencial
		credencial.setPersona(guardada);
		guardada.setCredencial(credencial);

		credencialRepository.save(credencial);

		return guardada;
	}

	// VALIDACIONES
	private void validarDatosPersona(Persona persona) {
		if (persona.getNombre() == null || persona.getNombre().isBlank() || persona.getEmail() == null
				|| persona.getEmail().isBlank() || persona.getNacionalidad() == null
				|| persona.getNacionalidad().isBlank()) {

			throw new RuntimeException("Faltan datos personales obligatorios");
		}
		if (persona.getNombre() == null || persona.getNombre().isBlank()) {
			throw new RuntimeException("El nombre es obligatorio");
		}
		if (persona.getEmail() == null || persona.getEmail().isBlank()) {
			throw new RuntimeException("El email es obligatorio");
		}
		if (!persona.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
			throw new RuntimeException("El formato del email no es válido");
		}
		if (persona.getNacionalidad() == null || persona.getNacionalidad().isBlank()) {
			throw new RuntimeException("La nacionalidad es obligatoria");
		}

	}

	private void validarEmail(Persona persona) {

		String email = persona.getEmail().toLowerCase();

		Persona existente = personaRepository.findByEmail(email);

		// Si existe otro usuario con ese email
		if (existente != null && !existente.getId().equals(persona.getId())) {
			throw new RuntimeException("Email ya registrado");
		}
	}

	private void validarCredenciales(Credencial cred) {

		String username = cred.getUsername();
		String password = cred.getPassword();

		if (username == null || password == null) {
			throw new RuntimeException("Usuario y contraseña obligatorios");
		}

		if (username.contains(" ") || password.contains(" ")) {
			throw new RuntimeException("Usuario y contraseña no pueden contener espacios");
		}

		if (username.length() <= 2 || password.length() <= 2) {
			throw new RuntimeException("Usuario y contraseña deben tener más de 2 caracteres");
		}

		if (!username.matches("[a-zA-Z]+")) {
			throw new RuntimeException("El usuario solo puede contener letras sin tildes");
		}

		cred.setUsername(username.toLowerCase());
	}

	private void validarCoordinacion(Coordinacion c) {
		if (c.isSenior() && c.getFechaSenior() == null) {
			throw new RuntimeException("Debe indicar la fecha desde que es senior");
		}
	}
}
