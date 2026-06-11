package com.luisdbb.tarea3AD2024base.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luisdbb.tarea3AD2024base.modelo.User;

/**
 * Repositorio de acceso a datos para usuarios.
 * 
 * @author Lucia Garcia
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
