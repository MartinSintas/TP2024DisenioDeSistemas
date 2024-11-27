package ar.edu.utn.frba.dds.validadorcontrasenias.validadores;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ValidadoresTests {

    @Test
    @DisplayName("Ejemplo de uso, validacion de contrasenia segun NIST 800-63 3")
    void validacionSegunNist80063() {
        Validador validador = Validador.nist80063();
        String contra1 = "password";
        String contra2 = "904jtnmi";
        String contra3 = "93q4ijn    a";
        String contra4 = "()@)(slaasi1";
        assertFalse(validador.validarContrasenia(contra1));
        assertFalse(validador.validarContrasenia(contra2));
        assertFalse(validador.validarContrasenia(contra3));
        assertTrue(validador.validarContrasenia(contra4));
    }
    @Test
    @DisplayName("Ejemplo de como configurar un validador con condiciones y normalizaciones")
    void validacionPersonalizada() {
        Normalizador normalizadorDeEspacios = new NormalizadorDeEspacios();
        Normalizador normalizadorUnicode = new NormalizadorUnicode();
        Condicionador masDeNCaracteres = new MasDeNCaracteres(8);
        Condicionador alMenosUnNumero = new AlmenosUnNumero();
        Condicionador alMenosUnaMayuscula = new AlmenosUnaMayuscula();

        Validador validador = new Validador();
        validador.agregarCondiciones(masDeNCaracteres, alMenosUnNumero, alMenosUnaMayuscula);
        String password1 = "password";
        String password2 = "Hola18";
        String password3 = "AhoraSi18";
        assertFalse(validador.validarContrasenia(password1));
        assertFalse(validador.validarContrasenia(password2));
        assertTrue(validador.validarContrasenia(password3));
    }

    @Test
    @DisplayName("Verificar que condiciones no cumplieron en NIST 800-63 3")
    void queCondicionesNoCumplen() {
        Validador validador = Validador.nist80063();
        String contra1 = "password";

        assertEquals(2, validador.condicionesQueNoCumple(contra1).size()); // que es comun y que no tiene mas de 10 caracteres
        //Alcanza con el size¿
    }
}
