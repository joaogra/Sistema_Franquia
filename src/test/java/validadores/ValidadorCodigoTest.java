package validadores;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorCodigoTest {

    ValidadorCodigo validador;

    @BeforeEach
    protected void setUp() {
        validador = new ValidadorCodigo();
    }

    @Test

    void validarCodSucessful() {
        assertTrue(validador.validar("123"));
    }

    @Test
    void validarCodErro() {
        assertFalse(validador.validar("a50"));
    }
}