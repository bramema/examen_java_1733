package com.carmotors.modelo;

import java.time.Duration;
import java.util.Optional;

public record TiempoPromedioReparacion(
        String tipoMantenimiento,
        Optional<Duration> duracionPromedio
) {
}
