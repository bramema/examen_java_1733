package com.carmotors.modelo;

import java.time.LocalDateTime;

public record ServicioEntregadoReporteDato(
        String tipoMantenimiento,
        LocalDateTime fechaRecepcion,
        LocalDateTime fechaEntrega
) {
}
