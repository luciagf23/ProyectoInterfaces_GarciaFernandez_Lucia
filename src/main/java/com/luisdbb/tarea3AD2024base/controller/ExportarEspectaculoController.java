package com.luisdbb.tarea3AD2024base.controller;

import java.io.File;
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

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Controlador encargado de la exportación de información de espectáculos.
 * 
 * Permite generar documentos e informes con los datos almacenados en el
 * sistema.
 * 
 * @author Lucia Garcia
 * @version 1.0
 */
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
		chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF", "*.pdf"));

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

			// 3. Crear PDF con iText
			com.itextpdf.text.Document document = new com.itextpdf.text.Document();
			com.itextpdf.text.pdf.PdfWriter.getInstance(document, new java.io.FileOutputStream(txtRuta.getText()));

			document.open();
			document.add(new com.itextpdf.text.Paragraph("LISTADO DE ESPECTÁCULOS\n\n"));

			for (Espectaculo e : lista) {
				document.add(new com.itextpdf.text.Paragraph("Espectáculo: " + e.getNombre()));
				document.add(new com.itextpdf.text.Paragraph("Fecha inicio: " + e.getFechaInicio()));
				document.add(new com.itextpdf.text.Paragraph("Fecha fin: " + e.getFechaFin()));
				document.add(new com.itextpdf.text.Paragraph("\n-----------------------------\n"));
			}

			document.close();

			// 4. Mensaje opcional
			mostrarInfo("PDF generado correctamente.");

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
