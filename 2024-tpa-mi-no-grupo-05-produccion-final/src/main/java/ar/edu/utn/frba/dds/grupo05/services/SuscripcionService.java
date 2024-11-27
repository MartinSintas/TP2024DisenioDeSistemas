package ar.edu.utn.frba.dds.grupo05.services;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.suscripciones.FaltanViandas;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.suscripciones.HayViandas;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.suscripciones.SuscripcionHeladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.dtos.SuscripcionInputDTO;
import ar.edu.utn.frba.dds.grupo05.repositories.heladeras.RepositorioSuscripcionHeladera;
import ar.edu.utn.frba.dds.grupo05.services.heladera.IHeladeraService;
import ar.edu.utn.frba.dds.grupo05.services.personashumanasservices.IPersonaHumanaService;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.RollbackException;
import java.util.List;
import java.util.Set;

public class SuscripcionService implements ISuscripcionService {

  RepositorioSuscripcionHeladera repositorioSuscripcionHeladera;
  IPersonaHumanaService personaHumanaService;
  IHeladeraService heladeraService;

  public SuscripcionService(RepositorioSuscripcionHeladera repositorioSuscripcionHeladera,
                            IPersonaHumanaService personaHumanaService,
                            IHeladeraService heladeraService) {
    this.repositorioSuscripcionHeladera = repositorioSuscripcionHeladera;
    this.personaHumanaService = personaHumanaService;
    this.heladeraService = heladeraService;
  }

  private void crearSuscripcionCantidades(PersonaHumana colaborador, Heladera heladera, SuscripcionInputDTO input) {
    SuscripcionHeladera suscripcionHeladera = obtenerSuscripcion(colaborador, heladera, input);
    try {;
      repositorioSuscripcionHeladera.guardarSuscripcion(suscripcionHeladera);
    } catch (RollbackException e) {
      throw new AlreadySubscribedException(e);
    }
  }

  private void crearSuscripcionIncidente(PersonaHumana colaborador, Heladera heladera) {
    try {
      if (heladera.yaSuscripto(colaborador)) {
        throw new AlreadySubscribedException();
      }
      heladera.agregarSuscriptorDeIncidentes(colaborador);
      this.heladeraService.actualizar(heladera);

    } catch (RollbackException e) {
      throw new AlreadySubscribedException(e);
    }
  }

  @Override
  public void crearSuscripcion(SuscripcionInputDTO input) {
    PersonaHumana colaborador = this.personaHumanaService.obtenerPorUsuario(input.getUsuario());
    Heladera heladera = this.heladeraService.obtenerPorId(input.getIdHeladera());

    if (input.getTipoSuscripcion().equals("INCIDENTE")) {
      crearSuscripcionIncidente(colaborador, heladera);
    } else if (
            input.getTipoSuscripcion().equals("FALTAN_VIANDAS") ||
            input.getTipoSuscripcion().equals("HAY_VIANDAS")
    ) {
      crearSuscripcionCantidades(colaborador, heladera, input);
    } else {
      throw new IllegalArgumentException("Tipo de suscripcion no valido");
    }
  }

  private SuscripcionHeladera obtenerSuscripcion(PersonaHumana personaHumana, Heladera heladera, SuscripcionInputDTO input) {
    SuscripcionHeladera suscripcionHeladera = new SuscripcionHeladera();
    suscripcionHeladera.setPersonaHumana(personaHumana);
    suscripcionHeladera.setHeladera(heladera);
    switch (input.getTipoSuscripcion()) {
      case "FALTAN_VIANDAS":
        suscripcionHeladera.setTipoSuscripcion(new FaltanViandas(input.getNumeroCritico()));
        break;
      case "HAY_VIANDAS":
        suscripcionHeladera.setTipoSuscripcion(new HayViandas(input.getNumeroCritico()));
        break;
      default:
        throw new IllegalArgumentException("Tipo de suscripcion no valido");
    }
    return suscripcionHeladera;
  }
}
