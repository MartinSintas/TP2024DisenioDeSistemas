package ar.edu.utn.frba.dds.grupo05.services.personashumanasservices;

import ar.edu.utn.frba.dds.grupo05.domain.entities.formularios.Enunciado;
import ar.edu.utn.frba.dds.grupo05.domain.entities.formularios.Formulario;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.CargaArchivoCsvInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.PersonaHumanaInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.outputs.PersonaHumanaOutputDTO;

import java.util.List;

public interface IPersonaHumanaService {
    void darDeAlta(PersonaHumanaInputDTO personaHumanaInputDTO);
    void darDeAlta(CargaArchivoCsvInputDTO cargaArchivoCsvInputDTO);
    PersonaHumana obtenerPorUsuario(Usuario usuario);
    PersonaHumanaOutputDTO modificar(Long id, PersonaHumanaInputDTO personaHumanaInputDTO, Usuario usuario);
    void darDeBaja(Long id, Usuario usuario);
    List<Enunciado> obtenerEnunciadosDinamicos();
    public void aumentarPuntos(Double puntos, Usuario usuario);
}
