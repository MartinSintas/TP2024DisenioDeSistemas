package ar.edu.utn.frba.dds.grupo05.domain.entities.personas;

public interface AcumuladorPuntos {
    public void setPuntosAcumulados(Double puntos);
    public void descontarPuntos(Double puntos);
    public Double getPuntosConsumidos();
}
