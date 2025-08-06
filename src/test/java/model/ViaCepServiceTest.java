package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ViaCepServiceTest {

    ViaCepService service;

    @BeforeEach
    protected void setUp(){
        service = new ViaCepService();
    }

    @Test
    protected void buscarCEPSucessful() {
        assertDoesNotThrow(()->{
            service.buscar("25710285");
        });
    }

    @Test
    protected void buscaCEPFail(){
        assertThrows(IOException.class, ()->{
            service.buscar("123456789");
        });
    }
}