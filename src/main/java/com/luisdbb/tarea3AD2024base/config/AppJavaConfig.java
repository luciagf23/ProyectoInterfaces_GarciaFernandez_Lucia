package com.luisdbb.tarea3AD2024base.config;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;;

/**
 * Clase de configuración principal de Spring.
 * 
 * Define los componentes necesarios para la inicialización de la aplicación.
 * 
 * @author Lucia Garcia
 * @version 1.0
 */
@Configuration
public class AppJavaConfig {

	@Autowired
	SpringFXMLLoader springFXMLLoader;

//    /**
//     * Useful when dumping stack trace to a string for logging.
//     * @return ExceptionWriter contains logging utility methods
//     */
//    @Bean
//    @Scope("prototype")
//    public ExceptionWriter exceptionWriter() {
//        return new ExceptionWriter(new StringWriter());
//    }

	@Bean
	public ResourceBundle resourceBundle() {
		return ResourceBundle.getBundle("Bundle");
	}

}
