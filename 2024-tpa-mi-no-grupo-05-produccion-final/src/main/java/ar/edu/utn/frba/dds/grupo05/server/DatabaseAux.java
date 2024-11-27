package ar.edu.utn.frba.dds.grupo05.server;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

public class DatabaseAux implements WithSimplePersistenceUnit {
    public void init() {
      withTransaction(() -> {
      });
    }
}
