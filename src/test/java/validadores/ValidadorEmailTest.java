package validadores;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorEmailTest {

    ValidadorEmail validador;

    @BeforeEach
    protected void setUp(){
        validador = new ValidadorEmail();
    }

    @Test
    protected void validadaSucessful() {
        assertTrue(validador.validar("a@e.com"));
    }

    @Test
    protected void validaFailsemArroba() {
        assertFalse(validador.validar("abc.com"));
    }

    @Test
    protected void validaFailsemPonto() {
        assertFalse(validador.validar("abc@com"));
    }

    @Test
    protected void validaFailsemUsuario() {
        assertFalse(validador.validar("@gmail.com"));
    }
}