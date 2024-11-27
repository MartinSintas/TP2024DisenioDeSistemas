package ar.edu.utn.frba.dds.grupo05.services.heladera;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.EstadoHeladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.HeladeraOutputDTO;

import java.util.List;

public interface IHeladeraService {
  List<HeladeraOutputDTO> obtenerTodas();
  List<HeladeraOutputDTO> obtenerTodasPorEstado(EstadoHeladera estado);
  List<HeladeraOutputDTO> obtenerRecomendaciones();

  Heladera obtenerPorId(Long id);
  void actualizar(Heladera heladera);

  void create(Usuario usuario, HeladeraOutputDTO heladeraOutputDTO);
  void darDeAlta(HeladeraOutputDTO heladeraOutputDTO, Usuario usuario);
}
