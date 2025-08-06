package validadores;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorCampoVazioTest {

    ValidadorCampoVazio validadorCampoVazio;

    @BeforeEach

    protected void setUp() {
        validadorCampoVazio =  new ValidadorCampoVazio();
    }

    @Test
    //Retorna true se a entrada for vazia
    protected void validarSucessful() {
        assertTrue(validadorCampoVazio.validar(""));

    }

    @Test
    //Retorna false caso o contrário
    protected void validarFalse() {
        assertFalse(validadorCampoVazio.validar("Ola mundo!"));
    }
}