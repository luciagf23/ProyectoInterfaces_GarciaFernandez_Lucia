package com.luisdbb.tarea3AD2024base.help;

import com.luisdbb.tarea3AD2024base.controller.AyudaController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Utilidad para la gestión y visualización de la ayuda contextual.
 * 
 * @author Lucia Garcia
 * @version 1.0
 */
public class HelpUtil {

	public static void mostrarAyuda() {
		mostrarAyuda("index.html");
	}

	public static void mostrarAyuda(String pagina) {
		try {
			FXMLLoader loader = new FXMLLoader(HelpUtil.class.getResource("/fxml/AyudaView.fxml"));

			Parent root = loader.load();

			AyudaController controller = loader.getController();
			controller.loadPage(pagina);

			Stage stage = new Stage();
			stage.setTitle("Ayuda");
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
