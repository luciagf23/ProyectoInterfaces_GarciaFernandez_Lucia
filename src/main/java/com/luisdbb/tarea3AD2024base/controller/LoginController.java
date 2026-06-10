package com.luisdbb.tarea3AD2024base.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.luisdbb.tarea3AD2024base.config.StageManager;
import com.luisdbb.tarea3AD2024base.modelo.Credencial;
import com.luisdbb.tarea3AD2024base.services.CredencialService;
import com.luisdbb.tarea3AD2024base.services.SesionService;
import com.luisdbb.tarea3AD2024base.services.UserService;
import com.luisdbb.tarea3AD2024base.view.FxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * @author Ram Alapure
 * @since 05-04-2017
 */

@Controller
public class LoginController implements Initializable {

	@FXML
	private Button btnLogin;

	@FXML
	private Button btnInvitado;

	@FXML
	private PasswordField password;

	@FXML
	private TextField username;

	@FXML
	private Label lblLogin;

	@Autowired
	private UserService userService;

	private StageManager stageManager;

	@Autowired
	private CredencialService credencialService;

	@Autowired
	private SesionService sesionService;

	@Value("${admin.user}")
	private String adminUser;

	@Value("${admin.password}")
	private String adminPassword;

	public void setStageManager(StageManager stageManager) {
		this.stageManager = stageManager;
	}

	@FXML
	private void login(ActionEvent event) {

		try {

			// Login administrador
			if (getUsername().equals(adminUser) && getPassword().equals(adminPassword)) {

				stageManager.switchScene(FxmlView.USER);
				return;
			}

			// Login usuarios normales
			if (userService.authenticate(getUsername(), getPassword())) {

				Credencial credencial = credencialService.findByUsername(getUsername());

				sesionService.setUsuarioActual(credencial);

				switch (credencial.getRol()) {

				case ARTISTA:
					stageManager.switchScene(FxmlView.FICHAARTISTA);
					break;

				case COORDINACION:
					stageManager.switchScene(FxmlView.ESPECTACULOS);
					break;

				default:
					lblLogin.setText("Rol no válido.");
					break;
				}

			} else {
				lblLogin.setText("Usuario o contraseña incorrectos.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			lblLogin.setText("Error: " + e.getMessage());
		}
	}

	public String getPassword() {
		return password.getText();
	}

	public String getUsername() {
		return username.getText();
	}

	@FXML
	private void verEspectaculos(ActionEvent event) {
		stageManager.switchScene(FxmlView.INVITADO);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
