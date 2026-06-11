package com.luisdbb.tarea3AD2024base.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luisdbb.tarea3AD2024base.modelo.Credencial;

/**
 * Repositorio de acceso a datos para credenciales.
 * 
 * @author Lucia Garcia
 * @version 1.0
 */
@Repository
public interface CredencialRepository extends JpaRepository<Credencial, Long> {

	Optional<Credencial> findByUsername(String username);

	boolean existsByUsername(String username);

	Optional<Credencial> findByPersonaId(Long id);
}
