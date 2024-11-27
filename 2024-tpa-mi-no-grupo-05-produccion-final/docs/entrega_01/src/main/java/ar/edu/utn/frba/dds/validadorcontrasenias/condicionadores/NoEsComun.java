package ar.edu.utn.frba.dds.validadorcontrasenias.condicionadores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class NoEsComun implements Condicionador {
    private static List<String> contrasenias = new ArrayList<>();

    public NoEsComun() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/passwords.txt"));
            lines.forEach(s -> this.contrasenias.add(s));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cumpleCondicion(String contrasenia) {
        return !contrasenias.contains(contrasenia);
    }
}
