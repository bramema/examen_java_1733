package com.carmotors.servicio;

import com.carmotors.dao.ReporteServiciosDAO;
import com.carmotors.excepcion.DaoException;
import com.carmotors.modelo.ServicioEntregadoReporteDato;
import com.carmotors.modelo.TiempoPromedioReparacion;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ReporteServicio {
    private final ReporteServiciosDAO dao;
    private final Supplier<Connection> abrirConexion;

    public ReporteServicio(ReporteServiciosDAO dao, Supplier<Connection> abrirConexion) {
        this.dao = Objects.requireNonNull(dao);
        this.abrirConexion = Objects.requireNonNull(abrirConexion);
    }

    public List<TiempoPromedioReparacion> obtenerTiemposPromedioReparacion() {
        try (Connection conexion = abrirConexion.get()) {
            var serviciosPorTipo = dao.listarServiciosEntregados(conexion).stream()
                    .collect(Collectors.groupingBy(ServicioEntregadoReporteDato::tipoMantenimiento));
            return Stream.of("Preventivo", "Correctivo")
                    .map(tipo -> new TiempoPromedioReparacion(
                            tipo, calcularPromedio(serviciosPorTipo.getOrDefault(tipo, List.of()))))
                    .toList();
        } catch (SQLException e) {
            throw new DaoException("No se pudo cerrar la conexión del reporte.", e);
        }
    }

    private Optional<Duration> calcularPromedio(List<ServicioEntregadoReporteDato> servicios) {
        if (servicios.isEmpty()) {
            return Optional.empty();
        }

        Duration total = Duration.ZERO;
        for (ServicioEntregadoReporteDato servicio : servicios) {
            total = total.plus(Duration.between(servicio.fechaRecepcion(), servicio.fechaEntrega()));
        }
        return Optional.of(total.dividedBy(servicios.size()));
    }
}
