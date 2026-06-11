package com.luisdbb.tarea3AD2024base.config;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Utilidad encargada de la carga de archivos FXML integrados con Spring
 * Framework.
 * 
 * @author Lucia Garcia
 * @version 1.0
 */
@Component
public class SpringFXMLLoader {
	private final ResourceBundle resourceBundle;
	private final ApplicationContext context;

	@Autowired
	public SpringFXMLLoader(ApplicationContext context, ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
		this.context = context;
	}

	public Parent load(String fxmlPath) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setControllerFactory(context::getBean);
		loader.setResources(resourceBundle);

		var url = context.getResource(fxmlPath).getURL();

		System.out.println("FXML URL: " + url);

		loader.setLocation(url);

		try {
			return loader.load();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public ApplicationContext getContext() {
		return context;
	}

}
