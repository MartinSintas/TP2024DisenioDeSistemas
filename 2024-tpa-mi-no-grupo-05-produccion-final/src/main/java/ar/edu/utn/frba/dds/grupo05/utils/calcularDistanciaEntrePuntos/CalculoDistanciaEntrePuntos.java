package ar.edu.utn.frba.dds.grupo05.utils.calcularDistanciaEntrePuntos;

import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;

public class CalculoDistanciaEntrePuntos {

    private static final double radioTierra = 6371; // Radio de la Tierra en kilómetros
    public Double calculoDistancia(
            Double latitudA,
            Double longitudA,
            Double latitudB,
            Double longitudB){

        // Convertir las latitudes y longitudes de grados a radianes
        double dLat = Math.toRadians(latitudB - latitudA);
        double dLon = Math.toRadians(longitudB - longitudA);

        double lat1Rad = Math.toRadians(latitudA);
        double lat2Rad = Math.toRadians(latitudB);

        // Aplicar la fórmula de Haversine
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        // Calcular la distancia

        return radioTierra * c;
    }


}
