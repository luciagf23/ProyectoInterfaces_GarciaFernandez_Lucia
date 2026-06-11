package com.luisdbb.tarea3AD2024base.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luisdbb.tarea3AD2024base.modelo.Coordinacion;

/**
 * Repositorio encargado de acceso a datos para coordinadores.
 * 
 * @author Lucia Garcia
 * @version 1.0
 */
@Repository
public interface CoordinacionRepository extends JpaRepository<Coordinacion, Long> {

}