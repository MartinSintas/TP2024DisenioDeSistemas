package ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes;

import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.MedioDeContacto;
import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.TipoContacto;

import java.util.List;
import java.util.Optional;

public class NotificadorColaboradores {
    private EnviadorEmail enviadorEmail = new EnviadorEmail();

    public void notificarColaboradoresNoRegistrados(List<PersonaHumana> colaboradores) {
        for (PersonaHumana colaborador : colaboradores) {
            Optional<MedioDeContacto> medioDeContacto = colaborador.getMediosDeContacto().stream()
                    .filter(medio -> medio.getTipo() == TipoContacto.EMAIL)
                    .findFirst();

            if (medioDeContacto.isPresent()) {
                String direccion = medioDeContacto.get().getContacto();
                String asunto = "¡Gracias por tu colaboración!";
                String mensaje = "Estimado/a " + colaborador.getNombre() + ",\n\n" +
                        "Gracias por tu aporte. Te dejamos tu usuario para acceder al sistema:\n" +
                        colaborador.getNombre() + colaborador.getApellido() + "\n\n" +
                        "Por favor, verifica que tus datos sean correctos y completa los datos faltantes " +
                        "una vez que ingreses. \n\n" +
                        "Saludos cordiales.";

                enviadorEmail.enviarNotificacion(direccion, asunto, mensaje);
            } else {
                System.out.println("No se encontró un medio de contacto tipo EMAIL para " + colaborador.getNombre() + " " + colaborador.getApellido());
            }
        }
    }
}
