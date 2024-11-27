package ar.edu.utn.frba.dds.grupo05.utils.validadorContrasenias.condicionadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlmenosUnaMayuscula implements Condicionador {
    public boolean cumpleCondicion(String contrasenia) {
        Pattern patternNumero = Pattern.compile("[A-Z]");
        Matcher matcherNumero = patternNumero.matcher(contrasenia);
        return matcherNumero.find();
    }
}
