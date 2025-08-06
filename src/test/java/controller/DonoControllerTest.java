package controller;

import Exceptions.CPFJaCadastradoException;
import Exceptions.FranquiaJaPossuiGerenteException;
import Exceptions.FranquiaNaoPossuiGerenteException;
import Exceptions.FranquiaNomeIgualException;
import model.Endereco;
import model.Estoque;
import model.Franquia;
import model.Pessoas.Dono;
import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DonoControllerTest {
    private Dono dono;
    private Franquia f1;
    private Franquia f2;
    private Franquia f3;
    private Franquia f4;
    private Gerente gerente1;
    private Gerente gerente2;
    private Gerente gerente3;
    private Gerente gerente4;


    @BeforeEach
    void setUp() {
        dono = new Dono("adrian", "14518498690", "a@emial.com", "admin", new ArrayList<>());
        f1 = new Franquia("Franquia 1", new Endereco(), List.of(), new Estoque(), dono);
        f2 = new Franquia("Franquia 1", new Endereco(), List.of(), new Estoque(), dono);
        f3 = new Franquia("Franquia 3", new Endereco(), List.of(), new Estoque(), dono);
        f4 = new Franquia("Franquia 4", new Endereco(), List.of(), new Estoque(), dono);


        Vendedor vendedor = new Vendedor("João", "14945740712", "a@e.com", "helloworld", f1);
        f1.getVendedores().add(vendedor);
        gerente1 = new Gerente("Jota", "53773905041", "a@e.com", "1234");
        gerente2 = new Gerente("Gilso", "68434007010", "b@e.com", "4321");
        gerente3 = new Gerente("Jairo", "42432644069", "c@e.com", "4321");
        gerente4 = new Gerente("Tommy", "53773905041", "c@e.com", "4321");
        f1.associarGerente(gerente1);
        dono.getListaFranquias().add(f1);
        f4.associarGerente(gerente2);
    }

    @Test
    void CadastroFranquiaSucessful(){
        assertDoesNotThrow(()->{
            DonoController donoController = new DonoController(dono);
            donoController.cadastrarFranquia(f3,gerente3);
        });
    }

    @Test
    // F2 tem mesmo nome de F1
    void CadastroFranquiaFailMesmoNome(){
        assertThrows(FranquiaNomeIgualException.class, ()->{
           DonoController donoController = new DonoController(dono);
           donoController.cadastrarFranquia(f2,gerente3);
        });
    }

    @Test
    //Tenta associar um gerente em f3 com o msm CPF que o gerente de f1
    void CadastroFranquiaFailCPFJaCadastrado(){
        assertThrows(CPFJaCadastradoException.class, ()->{
            DonoController donoController = new DonoController(dono);
            donoController.cadastrarFranquia(f3,gerente4);
        });
    }

    @Test

    void CadastraGerenteSucessful(){
        assertDoesNotThrow(()->{
            DonoController donoController = new DonoController(dono);
            donoController.cadastrarGerente(f3,gerente2);

        });
    }

    @Test

    //Tenta cadastrar o gerente3, em f4, que ja possui gerente(gerente2)
    void CadastraGerenteFailJaPossuiGerente(){
        assertThrows(FranquiaJaPossuiGerenteException.class, ()->{
            DonoController donoController = new DonoController(dono);
            donoController.cadastrarGerente(f4,gerente3);
        });
    }

    @Test

    void PossuiGerenteCadastrado(){
        assertDoesNotThrow(()->{
            DonoController donoController = new DonoController(dono);
            donoController.removeGerente(f4);
        });
    }

    @Test
    void TentaRemoverGerenteQueNaoExiste(){
        assertThrows(FranquiaNaoPossuiGerenteException.class, ()->{
            DonoController donoController = new DonoController(dono);
            donoController.removeGerente(f2);
        });
    }



}