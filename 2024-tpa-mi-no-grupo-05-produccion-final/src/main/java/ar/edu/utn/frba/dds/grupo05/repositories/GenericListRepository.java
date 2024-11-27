package ar.edu.utn.frba.dds.grupo05.repositories;

import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;

import java.util.List;
import java.util.Optional;


public class GenericListRepository<T extends Persistente> {
    private List<T> items;

    public List<T> findAll() {
        return this.items;
    }

    public void save(T item) {
        // If not present, save
        if (!this.items.contains(item)) {
            this.items.add(item);
        }
    }

    public Optional<T> findById(Long id) {
        // Find by id
        for (T item : this.items) {
            if (item.getId().equals(id)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }
}
