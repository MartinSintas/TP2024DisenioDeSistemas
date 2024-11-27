package ar.edu.utn.frba.dds.grupo05.repositories.formularios;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.FormaDeColaborar;
import ar.edu.utn.frba.dds.grupo05.domain.entities.formularios.Respuesta;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;

public class RepositorioRespuesta extends Repositorio {

    public void guardarRespuesta(Respuesta respuesta) {
        this.guardar(respuesta);
    }

    public List<Respuesta> buscarRespuesta() {
        return (List<Respuesta>) (List<?>) this.buscarTodos("respuesta");
    }

    public void actualizarRespuesta(Respuesta respuesta) {
        this.actualizar(respuesta);
    }

    public void eliminarRespuesta(Respuesta respuesta) {
        this.eliminar(respuesta);
    }
}
