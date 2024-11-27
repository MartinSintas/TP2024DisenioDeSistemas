package ar.edu.utn.frba.dds.grupo05;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas.Vianda;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.suscripciones.FaltanViandas;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.suscripciones.HayViandas;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.suscripciones.SuscripcionHeladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.MedioDeContacto;
import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.TipoContacto;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.Localidad;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;
import ar.edu.utn.frba.dds.grupo05.repositories.ISuscripcionHeladeraRepository;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.EnviadorEmail;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.EnviadorMensaje;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.enviadortelegram.EnviadorTelegram;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.EnviadorWhatsapp;
import ar.edu.utn.frba.dds.grupo05.services.notificadorsuscripcionesheladeras.NotificadorSuscripcionesHeladeras;

import java.io.IOException;
import java.util.List;

public class NotificadorSuscripcionesHeladerasProcedureMock {
    public static void main(String[] args) throws IOException {

        EnviadorMensaje enviadorEmail = new EnviadorEmail();
        EnviadorTelegram enviadorTelegram = new EnviadorTelegram();
        EnviadorWhatsapp enviadorWhatsapp = new EnviadorWhatsapp();

        MedioDeContacto unMedioDeContacto1 = new MedioDeContacto();
        unMedioDeContacto1.setEnviador(enviadorEmail);
        unMedioDeContacto1.setDetalle("juanpablocastiglione01@gmail.com");
        unMedioDeContacto1.setTipo(TipoContacto.EMAIL);

        MedioDeContacto unMedioDeContacto2 = new MedioDeContacto();
        unMedioDeContacto2.setEnviador(enviadorTelegram);
        unMedioDeContacto2.setDetalle("964610424");
        unMedioDeContacto2.setTipo(TipoContacto.TELEGRAM);

        MedioDeContacto unMedioDeContacto3 = new MedioDeContacto();
        unMedioDeContacto3.setEnviador(enviadorWhatsapp);
        unMedioDeContacto3.setDetalle("+5491153889062");
        unMedioDeContacto3.setTipo(TipoContacto.WHATSAPP);

        Heladera heladera = new Heladera();
        heladera.setNombreSignificativo("Heladera de la esquina");
        heladera.setCapacidadEnViandas(8);
        heladera.agregarVianda(new Vianda());
        heladera.agregarVianda(new Vianda());
        heladera.agregarVianda(new Vianda());

        Localidad localidad = new Localidad("CABA");

        PuntoGeografico puntoGeografico = PuntoGeografico.builder()
                .latitud("-34.6037")
                .longitud("-58.3816")
                .calle("Av. Rivadavia")
                .altura("1478")
                .localidad(localidad)
                .build();



        PersonaHumana colaborador1 =  new PersonaHumana();
        colaborador1.agregarMedioDeContacto(unMedioDeContacto1);
        colaborador1.agregarMedioDeContacto(unMedioDeContacto3);

        PersonaHumana colaborador2 =  new PersonaHumana();
        colaborador2.agregarMedioDeContacto(unMedioDeContacto2);

        SuscripcionHeladera suscripcionHeladera1 = new SuscripcionHeladera(
                colaborador1,
                heladera,
                new HayViandas(5)
        );

        SuscripcionHeladera suscripcionHeladera2 = new SuscripcionHeladera(
                colaborador2,
                heladera,
                new FaltanViandas(5)
        );

        SuscripcionHeladera suscripcionHeladera3 = new SuscripcionHeladera(
                colaborador1,
                heladera,
                new HayViandas(3)
        );

        class SuscripcionHeladeraRepository implements ISuscripcionHeladeraRepository{
            public List<SuscripcionHeladera> findAll() {
                return List.of(suscripcionHeladera1, suscripcionHeladera2, suscripcionHeladera3);
            }
        }

        // MOCK END

        ISuscripcionHeladeraRepository suscripcionHeladeraRepository = new SuscripcionHeladeraRepository();

        NotificadorSuscripcionesHeladeras notificadorSuscripcionesHeladeras =
                new NotificadorSuscripcionesHeladeras(suscripcionHeladeraRepository);

        notificadorSuscripcionesHeladeras.notificarSuscripciones();
    }
}
