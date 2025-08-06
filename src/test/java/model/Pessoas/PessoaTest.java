package model.Pessoas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class PessoaTest {

    private Pessoa pessoa1;
    private Pessoa pessoa2;

    @Test

    protected void verificaCPFSucessful(){
        assertDoesNotThrow(()->{
            pessoa1 = new Pessoa("João Victor", "12345678909");
        });
    }

    @Test
    protected void verificaCPFFail(){
        assertThrows(IllegalArgumentException.class, ()->{
            pessoa2 = new Pessoa("Mariazinha","555564654");
        });
    }




}