package ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes;

public interface EnviadorMensaje {
    void enviarNotificacion(String direccion, String mensaje);
    void enviarNotificacion (String direccion, String asunto, String mensaje);
}
