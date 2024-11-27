package ar.edu.utn.frba.dds.grupo05.utils.validadorContrasenias.normalizadores;

public class NormalizadorDeEspacios implements Normalizador {
    @Override
    public String normalizar(String contrasenia) {
        return contrasenia = contrasenia.replaceAll("\\s{2,}", " ");
    }
}
