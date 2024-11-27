package ar.edu.utn.frba.dds.grupo05.repositories.tarjetas.personasvulnerables;

import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.personasvulnerables.TarjetaPersonaVulnerable;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;

public class RepositorioTarjetaPersonaVulnerable extends Repositorio {



    public void guardarTarjetaPersonaVulnerable(TarjetaPersonaVulnerable tarjetaPersonaVulnerable) {
        this.guardar(tarjetaPersonaVulnerable);
    }


    public List<TarjetaPersonaVulnerable> buscarTodasLasTarjetaPersonaVulnerable() {
        return (List<TarjetaPersonaVulnerable>) (List<?>) this.buscarTodos("tarjeta_persona_vulnerable");
    }

    public void actualizarTarjetaPersonaVulnerable(TarjetaPersonaVulnerable tarjetaPersonaVulnerable) {
        this.actualizar(tarjetaPersonaVulnerable);
    }

    public void eliminarTarjetaPersonaVulnerable(TarjetaPersonaVulnerable tarjetaPersonaVulnerable) {
        this.eliminar(tarjetaPersonaVulnerable);
    }

}

