package ar.edu.utn.frba.dds.grupo05.services.tecnicoservices.impl;

import ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.Localidad;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;
import ar.edu.utn.frba.dds.grupo05.repositories.ITecnicoRepository;
import ar.edu.utn.frba.dds.grupo05.services.tecnicoservices.IBuscadorTecnico;
import ar.edu.utn.frba.dds.grupo05.exceptions.TecnicoNoEncontrado;
import ar.edu.utn.frba.dds.grupo05.utils.calcularDistanciaEntrePuntos.CalculoDistanciaEntrePuntos;

import java.util.List;

public class BuscadorTecnico implements IBuscadorTecnico {

    public Tecnico buscarTecnicoMasCercano(PuntoGeografico puntoGeografico) {
        //lista de tecnicos cercanos a la heladera de la falla
        Localidad localidad = puntoGeografico.getLocalidad();
        List<Tecnico> tecnicosEnZona = localidad.getTecnicos();

        if (tecnicosEnZona.isEmpty()) {
            throw new TecnicoNoEncontrado("No se encontraron técnicos en la zona");
        }

        CalculoDistanciaEntrePuntos calculoDistanciaEntrePuntos = new CalculoDistanciaEntrePuntos();
        double distanciaMinima = Double.MAX_VALUE;
        Tecnico tecnicoMasCercano = null;

        for (Tecnico tecnico : tecnicosEnZona) {

            double tecnicoLatitud = Double.valueOf(tecnico.getLatitud());
            double tecnicoLongitud = Double.valueOf(tecnico.getLongitud());
            double heladeraLatitud = Double.valueOf(puntoGeografico.getLatitud());
            double heladeraLongitud = Double.valueOf(puntoGeografico.getLongitud());

            double distancia = calculoDistanciaEntrePuntos.calculoDistancia(
                    tecnicoLatitud,
                    tecnicoLongitud,
                    heladeraLatitud,
                    heladeraLongitud
            );
            // Actualizar la distancia mínima si encontramos una distancia menor
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                tecnicoMasCercano = tecnico;
            }
        }
        // Dejamos q devuelva null, porq si tira excepcion mata al broker

        return tecnicoMasCercano;
    }
}
