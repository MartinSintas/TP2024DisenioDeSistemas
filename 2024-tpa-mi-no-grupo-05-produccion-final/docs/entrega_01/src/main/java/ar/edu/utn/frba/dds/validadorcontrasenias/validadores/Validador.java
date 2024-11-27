package ar.edu.utn.frba.dds.validadorcontrasenias.validadores;

import ar.edu.utn.frba.dds.validadorcontrasenias.condicionadores.Condicionador;
import ar.edu.utn.frba.dds.validadorcontrasenias.condicionadores.MasDeNCaracteres;
import ar.edu.utn.frba.dds.validadorcontrasenias.condicionadores.MenosDeNCaracteres;
import ar.edu.utn.frba.dds.validadorcontrasenias.condicionadores.NoEsComun;
import ar.edu.utn.frba.dds.validadorcontrasenias.normalizadores.Normalizador;
import ar.edu.utn.frba.dds.validadorcontrasenias.normalizadores.NormalizadorDeEspacios;
import ar.edu.utn.frba.dds.validadorcontrasenias.normalizadores.NormalizadorUnicode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Validador {
    private List<Condicionador> condicionesACumplir; //posible ser Set
    private List<Normalizador> normalizaciones;

    public Validador() {
        this.condicionesACumplir = new ArrayList<>();
        this.normalizaciones = new ArrayList<>();
    }

    public static Validador nist80063() {
        Normalizador normalizadorDeEspacios = new NormalizadorDeEspacios();
        Normalizador normalizadorUnicode = new NormalizadorUnicode();
        Condicionador masDeNCaracteres = new MasDeNCaracteres(10);
        Condicionador menosDeNCaracteres = new MenosDeNCaracteres(64);
        Condicionador noEsComun = new NoEsComun();
        Validador validador = new Validador();
        validador.agregarCondiciones(masDeNCaracteres, noEsComun, menosDeNCaracteres);
        validador.agregarNormalizaciones(normalizadorDeEspacios, normalizadorUnicode);
        return validador;
    }

    public void agregarCondiciones(Condicionador... condicionador) {
        Collections.addAll(condicionesACumplir, condicionador);
    }

    public void agregarNormalizaciones(Normalizador... normalizador) {
        Collections.addAll(normalizaciones, normalizador);
    }

    private Boolean cumpleTodasLasCondiciones(String contrasenia) {
        return condicionesACumplir.stream().allMatch(condicion ->
                condicion.cumpleCondicion(contrasenia));
    }
    public Boolean validarContrasenia(String contrasenia) {
        contrasenia = this.normalizar(contrasenia);
        return cumpleTodasLasCondiciones(contrasenia);
    }

    private String normalizar(String contrasenia) {
        return this.normalizaciones.stream().reduce(contrasenia,
                (contraseniaANormalizar, normalizacion) ->
                        normalizacion.normalizar(contraseniaANormalizar),
                (contra1, contra2) -> contra2);
    }

    public List<Condicionador> condicionesQueNoCumple(String contrasenia) {
        final String contraseniaNormalizada = this.normalizar(contrasenia);
        return this.condicionesACumplir
                .stream()
                .filter(condicion -> !condicion.cumpleCondicion(contraseniaNormalizada))
                .toList();
    }
}
