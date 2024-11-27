package ar.edu.utn.frba.dds.validadorcontrasenias.normalizadores;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NormalizadoresTests {

    @Test
    @DisplayName("Se eliminan espacios sucesivos.")
    void espaciosSucesivos() {
        Normalizador espacios = new NormalizadorDeEspacios();
        String contra = "asdfsd     a a  adfasdf   a";
        String contraNormalizada = espacios.normalizar(contra);
        assertEquals(contraNormalizada, "asdfsd a a adfasdf a");
    }

    @Test
    @DisplayName("Se normaliza el texto con NFKC.")
    void normalizacionNFKC() {
        Normalizador unicode = new NormalizadorUnicode();
        String texto = "株式会社ＫＡＤＯＫＡＷＡ Ｆｕｔｕｒｅ Ｐｕｂｌｉｓｈｉｎｇ";
        String textoNormalizado = unicode.normalizar(texto);
        assertEquals("株式会社KADOKAWA Future Publishing", textoNormalizado);
    }
}
