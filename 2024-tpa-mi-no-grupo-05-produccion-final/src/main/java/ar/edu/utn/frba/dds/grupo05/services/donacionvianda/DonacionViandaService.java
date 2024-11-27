package ar.edu.utn.frba.dds.grupo05.services.donacionvianda;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas.DonacionVianda;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas.Vianda;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.EstadoHeladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.colaboradores.TarjetaColaborador;
import ar.edu.utn.frba.dds.grupo05.factories.HeladeraFactory;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.colaboradores.MotivoApertura;
import ar.edu.utn.frba.dds.grupo05.dtos.DonacionViandaDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.HeladeraOutputDTO;
import ar.edu.utn.frba.dds.grupo05.exceptions.HeladeraNotFoundException;
import ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.viandas.RepositorioDonacionVianda;
import ar.edu.utn.frba.dds.grupo05.repositories.heladeras.RepositorioHeladera;
import ar.edu.utn.frba.dds.grupo05.exceptions.ColaboradorSinTarjetaException;
import ar.edu.utn.frba.dds.grupo05.exceptions.HeladeraNotFoundException;
import ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.viandas.RepositorioDonacionVianda;
import ar.edu.utn.frba.dds.grupo05.repositories.heladeras.RepositorioHeladera;
import ar.edu.utn.frba.dds.grupo05.repositories.tarjetas.colaboradores.RepositorioTarjetaColaborador;
import ar.edu.utn.frba.dds.grupo05.services.IDonacionViandaService;
import ar.edu.utn.frba.dds.grupo05.services.aperturaheladera.AperturaHeladeraService;
import ar.edu.utn.frba.dds.grupo05.services.personashumanasservices.IPersonaHumanaService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class DonacionViandaService implements IDonacionViandaService {
  private final RepositorioHeladera repositorioHeladera;
  private final RepositorioDonacionVianda repositorioDonacionVianda;
  private final IPersonaHumanaService personaHumanaService;
  private final RepositorioTarjetaColaborador repositorioTarjetaColaborador;
  private final AperturaHeladeraService aperturaHeladeraService;

  public DonacionViandaService(RepositorioHeladera repositorioHeladera,
                               RepositorioDonacionVianda repositorioDonacionVianda,
                               IPersonaHumanaService personaHumanaService,
                               AperturaHeladeraService aperturaHeladeraService,
                               RepositorioTarjetaColaborador repositorioTarjetaColaborador) {
    this.repositorioHeladera = repositorioHeladera;
    this.repositorioDonacionVianda = repositorioDonacionVianda;
    this.personaHumanaService = personaHumanaService;
    this.aperturaHeladeraService = aperturaHeladeraService;
    this.repositorioTarjetaColaborador = repositorioTarjetaColaborador;
  }

  @Override
  public List<HeladeraOutputDTO> obtenerPosiblesHeladeras() {
    List<Heladera> heladeras = this.repositorioHeladera.heladerasPorEstado(EstadoHeladera.ACTIVA);
    return HeladeraFactory.fromEntity(heladeras);
  }

  @Override
  public void donarVianda(DonacionViandaDTO donacionViandaDTO) {

    PersonaHumana colaborador = this.personaHumanaService.obtenerPorUsuario(donacionViandaDTO.getUsuario());

    TarjetaColaborador tarjeta;
    try {
      tarjeta = this.repositorioTarjetaColaborador.buscarPorColaborador(colaborador).get(0);
    } catch (Exception e) {
      throw new ColaboradorSinTarjetaException();
    }
    
    Heladera heladera = this.repositorioHeladera.buscarPorId(donacionViandaDTO.getHeladeraId()).orElseThrow(
        () -> new HeladeraNotFoundException("Heladera no encontrada: " + donacionViandaDTO.getHeladeraId())
    );

    DonacionVianda donacionVianda = new DonacionVianda();
    Vianda vianda = new Vianda();
    vianda.setComidaDetalle(donacionViandaDTO.getDescripcion());
    vianda.setFechaCaducidad(donacionViandaDTO.getFechaDeCaducidad());
    vianda.setPesoVianda(donacionViandaDTO.getPesoEnGramos());
    vianda.setCalorias(donacionViandaDTO.getKiloCalorias());

    heladera.agregarVianda(vianda);
    this.repositorioHeladera.guardar(heladera);

    donacionVianda.setVianda(vianda);
    donacionVianda.setFechaAlta(LocalDate.now());
    donacionVianda.setDonante(colaborador);

    this.enviarApertura(donacionVianda.getDonante(), heladera, tarjeta);
    this.repositorioDonacionVianda.guardarDonacionVianda(donacionVianda);
  }

  @Override
  public Integer obtenerTiempoParaApertura(Long heladeraId) {
    Heladera heladera = this.repositorioHeladera.buscarPorId(heladeraId).orElseThrow(
            () -> new HeladeraNotFoundException("Heladera no encontrada: " + heladeraId)
    );
    return heladera.getHorasParaApertura();
  }


  private void enviarApertura(PersonaHumana personaHumana, Heladera heladera, TarjetaColaborador tarjetaColaborador) {
    MotivoApertura motivoApertura = MotivoApertura.INGRESO_DONACION_VIANDAS;
    LocalDateTime horaSolicitud = LocalDateTime.now();
    this.aperturaHeladeraService.guardarApertura(personaHumana, tarjetaColaborador, heladera, horaSolicitud, motivoApertura);
  }

}
