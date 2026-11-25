package com.carmotors.modelo;

import com.carmotors.modelo.DatosOrdenParaFactura.Repuesto;
import java.util.List;

public record DetalleOrdenServicio(
        OrdenServicio orden,
        String cliente,
        String documento,
        String marca,
        String modelo,
        List<Repuesto> repuestos
) {
    public DetalleOrdenServicio {
        repuestos = List.copyOf(repuestos);
    }
}
