package ar.edu.utn.frba.dds.grupo05.repositories.alertas;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes.Alerta;
import ar.edu.utn.frba.dds.grupo05.repositories.IAlertaRepository;

import java.util.ArrayList;
import java.util.List;

public class AlertaListRepository implements IAlertaRepository {
    private ArrayList<Alerta> alertas = new ArrayList<>();

    @Override
    public void guardar(Alerta... alertas) {
        this.alertas.addAll(List.of(alertas));
    }

    @Override
    public List<Alerta> buscarTodas() {
        return this.alertas;
    }
}
