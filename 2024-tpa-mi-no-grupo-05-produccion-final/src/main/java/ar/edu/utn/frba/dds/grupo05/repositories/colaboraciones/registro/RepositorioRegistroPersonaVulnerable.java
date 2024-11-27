package ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.registro;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.solicitudesApertura.SolicitudAperturaHeladera;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.registro.RegistroPersonaVulnerable;

import java.util.List;

public class RepositorioRegistroPersonaVulnerable extends Repositorio {

    public void guardarRegistroPersonaVulnerable(RegistroPersonaVulnerable registroPersonaVulnerable) {
        this.guardar(registroPersonaVulnerable);
    }

    public List<RegistroPersonaVulnerable> buscarRegistroPersonaVulnerable() {
        return (List<RegistroPersonaVulnerable>) (List<?>) this.buscarTodos("registro_persona_vulnerable");
    }

    public void actualizarRegistroPersonaVulnerable(RegistroPersonaVulnerable registroPersonaVulnerable) {
        this.actualizar(registroPersonaVulnerable);
    }

    public void eliminarRegistroPersonaVulnerable(RegistroPersonaVulnerable registroPersonaVulnerable) {
        this.eliminar(registroPersonaVulnerable);
    }
}
