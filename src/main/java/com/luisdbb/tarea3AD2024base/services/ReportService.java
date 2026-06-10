package com.luisdbb.tarea3AD2024base.services;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class ReportService {

	private final DataSource dataSource;

	public ReportService(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// CARNET DEL ARTISTA
	public void generarCarnetArtista(Long idArtista, Date fecha) {
		try {
			InputStream jrxml = getClass().getResourceAsStream("/reports/carnet_artistas.jrxml");

			if (jrxml == null) {
				System.out.println("NO SE ENCUENTRA EL JRXML: carnet_artistas.jrxml");
				return;
			}

			JasperReport report = JasperCompileManager.compileReport(jrxml);

			Map<String, Object> params = new HashMap<>();
			params.put("id_artista", idArtista);
			params.put("fecha_emision", fecha);

			JasperPrint print = JasperFillManager.fillReport(report, params, dataSource.getConnection());

			JasperExportManager.exportReportToPdfFile(print, "carnet_artista.pdf");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// INFORME ESTADÍSTICO
	public void generarInformeEstadistico() {
		try {
			InputStream jrxml = getClass().getResourceAsStream("/reports/artistas_especialidades.jrxml");

			if (jrxml == null) {
				System.out.println("NO SE ENCUENTRA EL JRXML: artistas_especialidades.jrxml");
				return;
			}

			JasperReport report = JasperCompileManager.compileReport(jrxml);

			JasperPrint print = JasperFillManager.fillReport(report, null, dataSource.getConnection());

			JasperExportManager.exportReportToPdfFile(print, "artistas_especialidades.pdf");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
