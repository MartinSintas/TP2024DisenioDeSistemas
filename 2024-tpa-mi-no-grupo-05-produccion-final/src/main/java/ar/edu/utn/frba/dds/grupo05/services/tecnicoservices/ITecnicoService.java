package ar.edu.utn.frba.dds.grupo05.services.tecnicoservices;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.TecnicoInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.outputs.TecnicoOutputDTO;

public interface ITecnicoService {
  TecnicoOutputDTO darDeAlta(TecnicoInputDTO tecnicoInputDTO, Usuario usuario);
  void darDeAlta(TecnicoInputDTO tecnicoInputDTO);
  TecnicoOutputDTO modificar(Long id, TecnicoInputDTO tecnicoInputDTO, Usuario usuario);
  void darDeBaja(Long id, Usuario usuario);
}
