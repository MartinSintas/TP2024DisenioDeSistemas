package ar.edu.utn.frba.dds.grupo05.utils.validadorContrasenias.condicionadores;

public class MasDeNCaracteres implements Condicionador {
    private Integer cantidadCaracteres;

    public MasDeNCaracteres(Integer cantidadCaracteres) {
        this.cantidadCaracteres = cantidadCaracteres;
    }

    @Override
    public boolean cumpleCondicion(String contrasenia) {
        return contrasenia.length() >= cantidadCaracteres;
    }
}
