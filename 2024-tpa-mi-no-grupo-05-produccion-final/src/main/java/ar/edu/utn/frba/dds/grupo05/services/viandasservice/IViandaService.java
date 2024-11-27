package ar.edu.utn.frba.dds.grupo05.services.viandasservice;

import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.ViandaInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.outputs.ViandaOutputDTO;

public interface IViandaService {
    ViandaOutputDTO darDeAlta(ViandaInputDTO viandaInputDTO, Usuario usuario);
    ViandaOutputDTO modificar(Long id, ViandaInputDTO viandaInputDTO, Usuario usuario);
    void darDeBaja(Long id, Usuario usuario);
}
