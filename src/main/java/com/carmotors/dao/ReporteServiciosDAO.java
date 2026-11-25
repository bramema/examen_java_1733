package com.carmotors.dao;

import com.carmotors.modelo.FiltroReporte;
import com.carmotors.modelo.ServicioEntregadoReporteDato;
import com.carmotors.modelo.SolicitudServicioReporteDato;
import java.sql.Connection;
import java.util.List;

/** Lectura para reportes, independiente del almacenamiento concreto. */
public interface ReporteServiciosDAO {
    List<SolicitudServicioReporteDato> listarSolicitudes(Connection conexion, FiltroReporte filtro);

    List<ServicioEntregadoReporteDato> listarServiciosEntregados(Connection conexion);
}
