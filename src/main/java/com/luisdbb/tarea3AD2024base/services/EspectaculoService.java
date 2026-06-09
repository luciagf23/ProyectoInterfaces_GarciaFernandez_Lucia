package com.luisdbb.tarea3AD2024base.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisdbb.tarea3AD2024base.modelo.Espectaculo;
import com.luisdbb.tarea3AD2024base.repositorios.EspectaculoRepository;
import com.luisdbb.tarea3AD2024base.repositorios.NumeroRepository;

@Service
public class EspectaculoService {

	@Autowired
	private EspectaculoRepository espectaculoRepository;

	@Autowired
	private NumeroRepository numeroRepository;

	public Espectaculo guardar(Espectaculo espectaculo) {

		validarNombreYFechaUnicos(espectaculo);
		validarEspectaculo(espectaculo);
		validarFechas(espectaculo);
		validarDuracionMaxima(espectaculo);
		validarCoordinador(espectaculo);

		return espectaculoRepository.save(espectaculo);
	}

	public void eliminar(Long id) {
		espectaculoRepository.deleteById(id);
	}

	// Validaciones
	private void validarNombreYFechaUnicos(Espectaculo e) {

		Long id = (e.getId() == null) ? -1 : e.getId();

		boolean existeOtro = espectaculoRepository.existsByNombreAndFechaInicioAndIdNot(e.getNombre(),
				e.getFechaInicio(), id);

		if (existeOtro) {
			throw new RuntimeException("Ya existe un espectáculo con ese nombre en esa fecha");
		}
	}

	private void validarEspectaculo(Espectaculo e) {

		if (e.getNombre() == null || e.getNombre().isBlank()) {

			throw new RuntimeException("Debe indicar un nombre para el espectáculo");
		}
		if (e.getNombre().length() > 25) {
			throw new RuntimeException("El nombre no puede superar los 25 caracteres");
		}

	}

	private void validarFechas(Espectaculo e) {
		if (e.getFechaInicio() == null || e.getFechaFin() == null) {
			throw new RuntimeException("Debe indicar fecha de inicio y fin");
		}

		LocalDate inicio = e.getFechaInicio();
		LocalDate fin = e.getFechaFin();

		// No permitir fechas pasadas
		if (inicio.isBefore(LocalDate.now())) {
			throw new RuntimeException("La fecha de inicio no puede ser anterior a hoy");
		}

		// No permitir periodo mayor a 1 año
		if (fin.isAfter(inicio.plusYears(1))) {
			throw new RuntimeException("El periodo del espectáculo no puede superar un año");
		}

		// No permitir fin antes que inicio
		if (fin.isBefore(inicio)) {
			throw new RuntimeException("La fecha de fin no puede ser anterior a la fecha de inicio");
		}
	}

	private void validarDuracionMaxima(Espectaculo e) {
		if (e.getFechaInicio().plusYears(1).isBefore(e.getFechaFin())) {
			throw new RuntimeException("El espectáculo no puede durar más de 1 año");
		}
	}

	private void validarCoordinador(Espectaculo e) {
		if (e.getCoordinador() == null) {
			throw new RuntimeException("Debe asignarse un coordinador");
		}
	}

	public void validarMinimoNumeros(Espectaculo e) {
		long count = numeroRepository.countByEspectaculoId(e.getId());
		if (count < 3) {
			throw new RuntimeException("El espectáculo debe tener al menos 3 números");
		}
	}

	public Espectaculo cargarEspectaculoCompleto(Long id) {
		return espectaculoRepository.findByIdWithNumerosAndArtistas(id);
	}

	public List<Espectaculo> listarTodos() {
		return espectaculoRepository.findAll();
	}

	public Espectaculo buscarPorId(Long id) {
		return espectaculoRepository.findById(id).orElse(null);
	}

	public boolean existsByNombre(String nombre) {
		return espectaculoRepository.existsByNombre(nombre);
	}
}
