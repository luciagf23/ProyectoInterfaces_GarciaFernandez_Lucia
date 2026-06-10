package com.luisdbb.tarea3AD2024base.controller;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.luisdbb.tarea3AD2024base.modelo.Espectaculo;
import com.luisdbb.tarea3AD2024base.services.EspectaculoService;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

@Controller
public class ExportarEspectaculoController {

	@Autowired
	private EspectaculoService espectaculoService;

	@FXML
	private TextField txtRuta;

	public ExportarEspectaculoController() {
	}

	public ExportarEspectaculoController(EspectaculoService espectaculoService) {
		this.espectaculoService = espectaculoService;
	}

	@FXML
	private void onExaminar() {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Guardar archivo");
		chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));

		File file = chooser.showSaveDialog(null);
		if (file != null) {
			txtRuta.setText(file.getAbsolutePath());
		}
	}

	@FXML
	private void onExportar() {
		try {

			// 1. Validar que hay ruta
			if (txtRuta.getText().isBlank()) {
				mostrarError("Debes seleccionar una ruta para guardar el archivo.");
				return;
			}

			// 2. Obtener todos los espectáculos
			List<Espectaculo> lista = espectaculoService.findAll();

			// 3. Construir el contenido del TXT
			StringBuilder sb = new StringBuilder();
			for (Espectaculo e : lista) {
				sb.append("Espectáculo: ").append(e.getNombre()).append("\n").append("Fecha inicio: ")
						.append(e.getFechaInicio()).append("\n").append("Fecha fin: ").append(e.getFechaFin())
						.append("\n\n");

			}

			// 4. Guardar el archivo
			Files.writeString(new File(txtRuta.getText()).toPath(), sb.toString());

			// 5. Mensaje opcional
			mostrarInfo("Espectáculos exportados correctamente.");

			// 6. Cerrar ventana
			cerrar();

		} catch (Exception e) {
			e.printStackTrace();
			mostrarError("Error al exportar los espectáculos.");
		}
	}

	@FXML
	private void onCancelar() {
		cerrar();
	}

	private void cerrar() {
		Stage stage = (Stage) txtRuta.getScene().getWindow();
		stage.close();
	}

	private void mostrarError(String msg) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
	}

	private void mostrarInfo(String msg) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
	}

}
