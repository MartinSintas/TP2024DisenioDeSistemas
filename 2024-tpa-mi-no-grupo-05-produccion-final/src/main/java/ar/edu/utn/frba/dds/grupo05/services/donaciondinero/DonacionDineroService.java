package ar.edu.utn.frba.dds.grupo05.services.donaciondinero;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.dinero.DonacionDinero;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.dinero.DonacionDineroProgramada;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaJuridica;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.TipoRol;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.DonacionDineroDTO;
import ar.edu.utn.frba.dds.grupo05.exceptions.UsuarioNoVinculadoAPersonaException;
import ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.dinero.RepositorioDonacionDinero;
import ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.dinero.RepositorioDonacionDineroProgramada;
import ar.edu.utn.frba.dds.grupo05.repositories.personas.RepositorioPersonaHumana;
import ar.edu.utn.frba.dds.grupo05.repositories.personas.RepositorioPersonaJuridica;

import java.time.LocalDateTime;

public class DonacionDineroService implements IDonacionDineroService {
  private RepositorioPersonaHumana personaHumanaRepository;
  private RepositorioPersonaJuridica personaJuridicaRepository;
  private RepositorioDonacionDineroProgramada donacionDineroProgramadaRepository;
  private RepositorioDonacionDinero donacionDineroRepository;

  public DonacionDineroService(RepositorioPersonaHumana personaHumanaRepository,
                               RepositorioPersonaJuridica personaJuridicaRepository,
                               RepositorioDonacionDineroProgramada donacionDineroProgramadaRepository,
                               RepositorioDonacionDinero donacionDineroRepository) {
    this.personaHumanaRepository = personaHumanaRepository;
    this.personaJuridicaRepository = personaJuridicaRepository;
    this.donacionDineroProgramadaRepository = donacionDineroProgramadaRepository;
    this.donacionDineroRepository = donacionDineroRepository;
  }

  @Override
  public DonacionDineroDTO donarDinero(DonacionDineroDTO donacionDineroDTO, Usuario usuario) {
    DonacionDinero donacionDinero = new DonacionDinero(LocalDateTime.now(), donacionDineroDTO.getMonto());
    this.donacionDineroRepository.guardarDonacionDinero(donacionDinero);


    if (usuario.getRol().equals(TipoRol.PERSONA_HUMANA)) {
      donarDineroPersonaHumana(donacionDinero, usuario, donacionDineroDTO.getRecurrente());
    } else {
      donarDineroPersonaJuridica(donacionDinero, usuario, donacionDineroDTO.getRecurrente());
    }

    return donacionDineroDTO;
  }

  private DonacionDineroProgramada crearDonacionProgramada(DonacionDinero donacionDinero, Integer diasParaRepetir) {
    DonacionDineroProgramada donacionDineroProgramada = new DonacionDineroProgramada();
    donacionDineroProgramada.setUltimaDonacion(donacionDinero);
    donacionDineroProgramada.setDiasParaRepetir(diasParaRepetir);
    donacionDineroProgramada.setMonto(donacionDinero.getMonto());
    return donacionDineroProgramada;
  }

  private void donarDineroPersonaJuridica(DonacionDinero donacionDinero, Usuario usuario, Boolean recurrente) {
    PersonaJuridica personaJuridica = personaJuridicaRepository.buscarPorUsuario(usuario).orElseThrow(
            UsuarioNoVinculadoAPersonaException::new
    );

    if (recurrente) {
      DonacionDineroProgramada donacionDineroProgramada = crearDonacionProgramada(donacionDinero, 30);
      donacionDineroProgramadaRepository.guardarDonacionDineroProgramada(donacionDineroProgramada);
      personaJuridica.agregarDonacionDineroProgramada(donacionDineroProgramada);
    }

    personaJuridica.agregarDonacionDinero(donacionDinero);

    personaJuridicaRepository.actualizarPersonaJuridica(personaJuridica);
  }

  private void donarDineroPersonaHumana(DonacionDinero donacionDinero, Usuario usuario, Boolean recurrente) {
    PersonaHumana personaHumana = personaHumanaRepository.buscarPorUsuario(usuario).orElseThrow(
            UsuarioNoVinculadoAPersonaException::new
    );

    if (recurrente) {
      DonacionDineroProgramada donacionDineroProgramada = crearDonacionProgramada(donacionDinero, 30);
      donacionDineroProgramadaRepository.guardarDonacionDineroProgramada(donacionDineroProgramada);
      personaHumana.agregarDonacionDineroProgramada(donacionDineroProgramada);
    }

    personaHumana.agregarDonacionDeDinero(donacionDinero);

    personaHumanaRepository.actualizarPersonaHumana(personaHumana);
  }
}
