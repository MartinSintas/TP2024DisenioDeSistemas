package ar.edu.utn.frba.dds.grupo05.services.heladera;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.EstadoHeladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.sensores.SensorMovimiento;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.sensores.SensorTemperatura;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.Localidad;
import ar.edu.utn.frba.dds.grupo05.factories.HeladeraFactory;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.HeladeraOutputDTO;
import ar.edu.utn.frba.dds.grupo05.exceptions.HeladeraNotFoundException;
import ar.edu.utn.frba.dds.grupo05.repositories.RepositorioModeloHeladera;
import ar.edu.utn.frba.dds.grupo05.repositories.heladeras.RepositorioHeladera;
import ar.edu.utn.frba.dds.grupo05.repositories.personas.RepositorioPersonaJuridica;
import ar.edu.utn.frba.dds.grupo05.repositories.ubicacion.RepositorioLocalidad;
import ar.edu.utn.frba.dds.grupo05.services.verificadorPermisos.VerificadorDePermisos;

import java.util.List;

public class HeladeraService implements IHeladeraService {

  RepositorioHeladera heladeraRepository;
  VerificadorDePermisos verificadorDePermisos;
  RepositorioPersonaJuridica repositorioPersonaJuridica;
  RepositorioLocalidad repositorioLocalidad = new RepositorioLocalidad();
  RepositorioModeloHeladera repositorioModeloHeladera = new RepositorioModeloHeladera();

  public HeladeraService(RepositorioHeladera heladeraRepository, RepositorioPersonaJuridica repositorioPersonaJuridica) {
    this.heladeraRepository = heladeraRepository;
    this.repositorioPersonaJuridica = repositorioPersonaJuridica;
  }

  @Override
  public List<HeladeraOutputDTO> obtenerTodas() {
    List<Heladera> heladeras = heladeraRepository.buscarHeladera();
    return HeladeraFactory.fromEntity(heladeras);
  }

  @Override
  public List<HeladeraOutputDTO> obtenerTodasPorEstado(EstadoHeladera estado) {
    List<Heladera> heladeras = heladeraRepository.heladerasPorEstado(estado);
    return HeladeraFactory.fromEntity(heladeras);
  }

  @Override
  public List<HeladeraOutputDTO> obtenerRecomendaciones() {
    return null;
  }

  @Override
  public Heladera obtenerPorId(Long id) {
    return this.heladeraRepository.buscarPorId(id).orElseThrow(
            () -> new HeladeraNotFoundException(
                    "No se encontró la heladera con el id " + id)
    );
  }

  @Override
  public void actualizar(Heladera heladera) {
    this.heladeraRepository.actualizar(heladera);
  }

  @Override
  public void create(Usuario usuario, HeladeraOutputDTO heladeraOutputDTO) {
    try {
      Heladera nuevaHeladera = HeladeraFactory.fromDTO(heladeraOutputDTO);
      nuevaHeladera.setPersonaACargo(repositorioPersonaJuridica.buscarPorUsuario(usuario).get());

      SensorTemperatura sensorTemperatura = new SensorTemperatura();
      sensorTemperatura.setHeladera(nuevaHeladera);

      SensorMovimiento sensorMovimiento = new SensorMovimiento();
      sensorMovimiento.setHeladera(nuevaHeladera);

      try {
        nuevaHeladera.setLocalidad(repositorioLocalidad.buscarPorNombre(heladeraOutputDTO.getLocalidad()).get(0));
      } catch (Exception e) {
        nuevaHeladera.setLocalidad(new Localidad(heladeraOutputDTO.getLocalidad()));
      }
      nuevaHeladera.setSensorMovimiento(sensorMovimiento);
      nuevaHeladera.setSensorTemperatura(sensorTemperatura);
      nuevaHeladera.setModeloHeladera(repositorioModeloHeladera.buscarPorId(heladeraOutputDTO.getModel()).get());

      this.heladeraRepository.guardar(nuevaHeladera);
    } catch (Exception e) {
      System.err.println("Error al crear la heladera: " + e.getMessage());
      throw new RuntimeException("Error al crear heladera: " + heladeraOutputDTO.getName(), e);
    }
  }

  @Override
  public void darDeAlta(HeladeraOutputDTO heladeraOutputDTO, Usuario usuario) {

    verificadorDePermisos.verificarSiUsuarioPuede("CREAR_HELADERA", usuario);

    Heladera nuevaHeladera = HeladeraFactory.fromDTO(heladeraOutputDTO);
    this.heladeraRepository.guardar(nuevaHeladera);
  }

}
