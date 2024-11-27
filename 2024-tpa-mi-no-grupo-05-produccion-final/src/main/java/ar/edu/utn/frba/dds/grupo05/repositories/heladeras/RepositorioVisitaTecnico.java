package ar.edu.utn.frba.dds.grupo05.repositories.heladeras;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes.VisitaTecnico;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;

public class RepositorioVisitaTecnico extends Repositorio {

    public void guardarVisitaTecnico(VisitaTecnico visitaTecnico) {
        this.guardar(visitaTecnico);
    }

    public List<VisitaTecnico> buscarVisitaTecnico() {
        return (List<VisitaTecnico>) (List<?>) this.buscarTodos("visita_tecnico");
    }

    public void actualizarVisitaTecnico(VisitaTecnico visitaTecnico) {
        this.actualizar(visitaTecnico);
    }

    public void eliminarVisitaTecnico(VisitaTecnico visitaTecnico) {
        this.eliminar(visitaTecnico);
    }
}
