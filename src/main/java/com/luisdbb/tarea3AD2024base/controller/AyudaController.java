package com.luisdbb.tarea3AD2024base.controller;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class AyudaController {

	@FXML
	private WebView webAyuda;

	private String pageToLoad = "inicio.html"; 

	@FXML
	private void initialize() {
		loadPage(pageToLoad);
	}

	public void loadPage(String page) {
		this.pageToLoad = page; 
		String url = getClass().getResource("/help/" + page).toExternalForm();
		webAyuda.getEngine().load(url);
	}

	@FXML
	private void cerrarVentana() {
		Stage stage = (Stage) webAyuda.getScene().getWindow();
		stage.close();
	}
}
