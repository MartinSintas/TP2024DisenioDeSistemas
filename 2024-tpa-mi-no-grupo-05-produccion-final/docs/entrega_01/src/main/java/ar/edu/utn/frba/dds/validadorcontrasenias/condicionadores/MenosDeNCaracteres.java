package ar.edu.utn.frba.dds.validadorcontrasenias.condicionadores;

public class MenosDeNCaracteres implements Condicionador {
    private Integer maximoCaracteres;

    public MenosDeNCaracteres(Integer maximo) {
        this.maximoCaracteres = maximo;
    }

    public boolean cumpleCondicion(String contrasenia){
        return contrasenia.length() <= this.maximoCaracteres;
    }
}
