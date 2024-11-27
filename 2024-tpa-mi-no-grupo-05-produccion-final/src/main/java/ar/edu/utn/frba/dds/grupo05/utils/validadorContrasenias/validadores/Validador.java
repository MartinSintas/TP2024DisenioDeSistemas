package ar.edu.utn.frba.dds.grupo05.utils.validadorContrasenias.validadores;

import ar.edu.utn.frba.dds.grupo05.exceptions.ContraseniaDebilException;
import ar.edu.utn.frba.dds.grupo05.utils.validadorContrasenias.normalizadores.NormalizadorDeEspacios;
import ar.edu.utn.frba.dds.grupo05.utils.validadorContrasenias.normalizadores.NormalizadorUnicode;
import ar.edu.utn.frba.dds.grupo05.utils.validadorContrasenias.condicionadores.Condicionador;
import ar.edu.utn.frba.dds.grupo05.utils.validadorContrasenias.condicionadores.MasDeNCaracteres;
import ar.edu.utn.frba.dds.grupo05.utils.validadorContrasenias.condicionadores.MenosDeNCaracteres;
import ar.edu.utn.frba.dds.grupo05.utils.validadorContrasenias.condicionadores.NoEsComun;
import ar.edu.utn.frba.dds.grupo05.utils.validadorContrasenias.normalizadores.Normalizador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Validador {
    private List<Condicionador> condicionesACumplir;
    private List<Normalizador> normalizadores;

    public Validador() {
        this.condicionesACumplir = new ArrayList<>();
        this.normalizadores = new ArrayList<>();
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
        Collections.addAll(normalizadores, normalizador);
    }

    private Boolean cumpleTodasLasCondiciones(String contrasenia) {
        if (!condicionesACumplir.stream().allMatch(condicion ->
                condicion.cumpleCondicion(contrasenia))) {
            throw new ContraseniaDebilException();
        }
        else
            return true;
    }
    public Boolean validarContrasenia(String contrasenia) {
        contrasenia = this.normalizar(contrasenia);
        return cumpleTodasLasCondiciones(contrasenia);
    }

    private String normalizar(String contrasenia) {
        return this.normalizadores.stream().reduce(contrasenia,
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
