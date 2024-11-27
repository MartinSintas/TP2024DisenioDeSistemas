package ar.edu.utn.frba.dds.grupo05.utils.validadorContrasenias.normalizadores;

import java.text.Normalizer;

public class NormalizadorUnicode implements Normalizador {
    @Override
    public String normalizar(String contrasenia) {
        return Normalizer.normalize(contrasenia, Normalizer.Form.NFKC);
    }
}
