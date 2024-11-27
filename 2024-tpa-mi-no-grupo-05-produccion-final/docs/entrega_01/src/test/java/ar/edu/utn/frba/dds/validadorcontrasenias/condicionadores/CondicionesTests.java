package ar.edu.utn.frba.dds.validadorcontrasenias.condicionadores;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CondicionesTests {

    @Test
    @DisplayName("Tiene mas de N caracteres.")
    void masDeNCaracteres() {
        Condicionador masDe8Caracteres = new MasDeNCaracteres(8);
        assertTrue(masDe8Caracteres.cumpleCondicion("1234567890"));
        assertFalse(masDe8Caracteres.cumpleCondicion("123"));
    }

    @Test
    @DisplayName("Contiene al menos una mayúscula.")
    void alMenosUnaMayuscula() {
        Condicionador alMenosUnaMayuscula = new AlmenosUnaMayuscula();
        assertTrue(alMenosUnaMayuscula.cumpleCondicion("Password"));
        assertFalse(alMenosUnaMayuscula.cumpleCondicion("password"));
    }

    @Test
    @DisplayName("Contiene al menos un carácter especial.")
    void alMenosUnCaracterEspecial() {
        Condicionador alMenosUnCaracterEspecial = new AlmenosUnCaracterEspecial();
        assertTrue(alMenosUnCaracterEspecial.cumpleCondicion("Pa$$word"));
        assertFalse(alMenosUnCaracterEspecial.cumpleCondicion("Password"));
    }

    @Test
    @DisplayName("Contiene al menos un número.")
    void alMenosUnNumero() {
        Condicionador alMenosUnNumero = new AlmenosUnNumero();
        assertTrue(alMenosUnNumero.cumpleCondicion("Password1"));
        assertFalse(alMenosUnNumero.cumpleCondicion("Password"));
    }

    @Test
    @DisplayName("Tiene menos de N caracteres.")
    void menosDeNCaracteres() {
        Condicionador menosDe10Caracteres = new MenosDeNCaracteres(10);
        assertTrue(menosDe10Caracteres.cumpleCondicion("12345678"));
        assertFalse(menosDe10Caracteres.cumpleCondicion("12345678900"));
    }

    @Test
    @DisplayName("No es una contraseña común.")
    void noEsComun() {
        Condicionador noEsComun = new NoEsComun();
        assertTrue(noEsComun.cumpleCondicion("MohzT9xyDsgW!n"));
        assertFalse(noEsComun.cumpleCondicion("12345678"));
    }


}
