package ar.edu.utn.frba.dds.validadorcontrasenias.normalizadores;

public class NormalizadorDeEspacios implements Normalizador {
    @Override
    public String normalizar(String contrasenia) {
        return contrasenia = contrasenia.replaceAll("\\s{2,}", " ");
    }
}
