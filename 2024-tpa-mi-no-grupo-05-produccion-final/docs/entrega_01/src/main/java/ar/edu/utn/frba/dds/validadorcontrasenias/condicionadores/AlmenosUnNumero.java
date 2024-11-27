package ar.edu.utn.frba.dds.validadorcontrasenias.condicionadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlmenosUnNumero implements Condicionador {
    public boolean cumpleCondicion(String contrasenia){
        Pattern patternNumero = Pattern.compile("[0-9]");
        Matcher matcherNumero = patternNumero.matcher(contrasenia);
        return matcherNumero.find();
    }
}