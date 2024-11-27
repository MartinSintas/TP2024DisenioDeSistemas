package ar.edu.utn.frba.dds.grupo05.utils.validadorContrasenias.condicionadores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class NoEsComun implements Condicionador {
    private static List<String> contraseniasComunes;

    public NoEsComun(){
        if (contraseniasComunes == null) {
            leerContraseniasComunes();
        }
    }
    private static void leerContraseniasComunes() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/passwords.txt"));
            contraseniasComunes = new ArrayList<>();
            contraseniasComunes.addAll(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean cumpleCondicion(String contrasenia) {
        return !contraseniasComunes.contains(contrasenia);
    }
}
