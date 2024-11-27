package ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.ofertas;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.ofertas.Canje;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.registro.RegistroPersonaVulnerable;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;

public class RepositorioCanje extends Repositorio {

    public void guardarCanje(Canje canje) {
        this.guardar(canje);
    }

    public List<Canje> buscarCanje() {
        return (List<Canje>) (List<?>) this.buscarTodos("canje");
    }

    public void actualizarCanje(Canje canje) {
        this.actualizar(canje);
    }

    public void eliminarCanje(Canje canje) {
        this.eliminar(canje);
    }
}
