package ar.edu.utn.frba.dds.grupo05.repositories.heladeras;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.RegistroTemperatura;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;

public class RepositorioRegistroTemperatura extends Repositorio {

    public void guardarRegistroTemperatura(RegistroTemperatura registroTemperatura) {
        this.guardar(registroTemperatura);
    }

    public List<RegistroTemperatura> buscarRegistroTemperatura() {
        return (List<RegistroTemperatura>) (List<?>) this.buscarTodos("movimiento_heladera");
    }

    public void actualizarRegistroTemperatura(RegistroTemperatura registroTemperatura) {
        this.actualizar(registroTemperatura);
    }

    public void eliminarRegistroTemperatura(RegistroTemperatura registroTemperatura) {
        this.eliminar(registroTemperatura);
    }
}
