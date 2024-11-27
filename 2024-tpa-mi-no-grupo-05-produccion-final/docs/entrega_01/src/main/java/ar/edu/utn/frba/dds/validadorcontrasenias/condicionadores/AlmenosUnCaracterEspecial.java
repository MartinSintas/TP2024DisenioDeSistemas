package ar.edu.utn.frba.dds.validadorcontrasenias.condicionadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlmenosUnCaracterEspecial implements Condicionador {

    public boolean cumpleCondicion(String contrasenia) {
        Pattern patternNumero = Pattern.compile("[^a-zA-Z0-9\\s]");
        Matcher matcherNumero = patternNumero.matcher(contrasenia);
        return matcherNumero.find();
    }

}
