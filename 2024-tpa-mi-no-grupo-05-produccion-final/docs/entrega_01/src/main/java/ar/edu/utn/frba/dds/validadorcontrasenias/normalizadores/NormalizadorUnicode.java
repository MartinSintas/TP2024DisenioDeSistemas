package ar.edu.utn.frba.dds.validadorcontrasenias.normalizadores;

import java.text.Normalizer;

public class NormalizadorUnicode implements Normalizador {
    @Override
    public String normalizar(String contrasenia) {
        return Normalizer.normalize(contrasenia, Normalizer.Form.NFKC);
        //java.text.Normalizer.normalize(contrasenia, java.text.Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}
