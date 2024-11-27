package ar.edu.utn.frba.dds.grupo05;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.repositories.heladeras.RepositorioHeladera;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

public class MainExample implements WithSimplePersistenceUnit {

    private RepositorioHeladera repositorioHeladera;
    public static void main(String[] args){
        MainExample instance = new MainExample();
        instance.repositorioHeladera = new RepositorioHeladera();
    }

    private void inicializar(){
        withTransaction(() -> {

        });
    }

}
