package ar.edu.utn.frba.dds.grupo05.repositories.heladeras;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.repositories.IHeladeraRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HeladerasListRepository implements IHeladeraRepository {

    private List<Heladera> heladeras = new ArrayList<>();

    @Override
    public List<Heladera> buscarTodas() {
        return this.heladeras;
    }

    @Override
    public Optional<Heladera> buscarPorId(Long id) {
        return heladeras
                .stream()
                .filter(h -> h.getId().equals(id))
                .findFirst();
    }

    @Override
    public void guardar(Heladera heladera) {
        // If heladera is already in list, dont add (can check by id)
        if (heladeras.stream().noneMatch(h -> h.getId().equals(heladera.getId()))) {
            heladeras.add(heladera);
        }
    }
}
