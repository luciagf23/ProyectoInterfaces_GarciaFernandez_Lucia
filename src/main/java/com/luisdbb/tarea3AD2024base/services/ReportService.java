package com.luisdbb.tarea3AD2024base.services;

import javax.sql.DataSource;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Servicio encargado de la generación de informes y exportación de datos.
 * 
 * @author Lucia Garcia
 * @version 1.0
 */

public class ReportService {

	private final DataSource dataSource;

	public ReportService(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// CARNET DEL ARTISTA
	public void generarCarnetArtista(Long idArtista) {
		try {
			InputStream jrxml = getClass().getResourceAsStream("/reports/carnet_artistas.jrxml");

			if (jrxml == null) {
				System.out.println("NO SE ENCUENTRA EL JRXML: carnet_artistas.jrxml");
				return;
			}

			JasperReport report = JasperCompileManager.compileReport(jrxml);

			Map<String, Object> params = new HashMap<>();
			params.put("id_artista", idArtista);
			

			JasperPrint print = JasperFillManager.fillReport(report, params, dataSource.getConnection());

			String proyectoDirString = System.getProperty("user.dir");

			File carpetaInformes = new File(proyectoDirString + File.separator + "informes");

			String rutaPdf = carpetaInformes.getAbsolutePath() + File.separator + "carnet_artista.pdf";

			JasperExportManager.exportReportToPdfFile(print, rutaPdf);

		} catch (Exception e) {
			e.printStackTrace();
		}

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("informe");
		alert.setHeaderText(null);

		alert.setContentText("Informe generado correctamente");

		alert.showAndWait();
	}

	// INFORME ESTADÍSTICO
	public void generarInformeEstadistico() {
		try {
			InputStream jrxml = getClass().getResourceAsStream("/reports/artistas_especialidad.jrxml");

			if (jrxml == null) {
				System.out.println("NO SE ENCUENTRA EL JRXML: artistas_especialidad.jrxml");
				return;
			}

			JasperReport report = JasperCompileManager.compileReport(jrxml);

			JasperPrint print = JasperFillManager.fillReport(report, null, dataSource.getConnection());

			String proyectoDirString = System.getProperty("user.dir");

			File carpetaInformes = new File(proyectoDirString + File.separator + "informes");

			String rutaPdf = carpetaInformes.getAbsolutePath() + File.separator + "artistas_especialidad.pdf";

			JasperExportManager.exportReportToPdfFile(print, rutaPdf);

		} catch (Exception e) {
			e.printStackTrace();
		}

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("informe");
		alert.setHeaderText(null);

		alert.setContentText("Informe generado correctamente");

		alert.showAndWait();
	}

}
