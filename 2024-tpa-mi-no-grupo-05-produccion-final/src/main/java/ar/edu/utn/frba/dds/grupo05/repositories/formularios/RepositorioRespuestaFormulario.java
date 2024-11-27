package ar.edu.utn.frba.dds.grupo05.repositories.formularios;

import ar.edu.utn.frba.dds.grupo05.domain.entities.formularios.Formulario;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;
import ar.edu.utn.frba.dds.grupo05.domain.entities.formularios.RespuestasFormulario;

import java.util.List;

public class RepositorioRespuestaFormulario extends Repositorio {

    public void guardarRespuestaFormulario(RespuestasFormulario respuestaFormulario) {
        this.guardar(respuestaFormulario);
    }

    public List<RespuestasFormulario> buscarRespuestasFormulario() {
        return (List<RespuestasFormulario>) (List<?>) this.buscarTodos("respuestas_formulario");
    }

    public void actualizarRespuestasFormulario(RespuestasFormulario respuestasFormulario) {
        this.actualizar(respuestasFormulario);
    }

    public void eliminarRespuestasFormulario(RespuestasFormulario respuestasFormulario) {
        this.eliminar(respuestasFormulario);
    }
}
