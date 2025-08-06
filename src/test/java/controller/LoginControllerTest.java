package controller;

import Exceptions.SenhaInvalidaException;
import Exceptions.UsuarioNaoCadastradoException;
import controller.gerente.VendedorTelaController;
import model.Endereco;
import model.Estoque;
import model.Franquia;
import model.Pessoas.Dono;
import model.Pessoas.Funcionario;
import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    private Dono dono;

    @BeforeEach
    void setUp() {
        dono = new Dono("adrian", "14518498690", "a@emial.com", "admin", new ArrayList<>());
        Franquia f1 = new Franquia("Franquia 1", new Endereco(), List.of(), new Estoque(), dono);
        Vendedor vendedor = new Vendedor("João", "14945740712", "a@e.com", "helloworld", f1);
        f1.getVendedores().add(vendedor);
        Gerente gerente = new Gerente("Jota", "53773905041", "a@e.com", "1234");
        f1.associarGerente(gerente);
        dono.getListaFranquias().add(f1);
    }

    @Test
    protected void loginSucessful() {
        LoginController loginController = new LoginController(dono);
        loginController.criaListaFuncionarios(dono);
        //Verifica o Dono
        assertTrue(loginController.verificaNoSitema("14518498690", "admin"));
        //Verifica o Gerente
        assertTrue(loginController.verificaNoSitema("53773905041", "1234"));
        //Verifica o Vendedor
        assertTrue(loginController.verificaNoSitema("14945740712", "helloworld"));
    }

    @Test
    protected void loginFailSenhaInvalida() {
        // Lança a exception de senha inválida
        LoginController loginController = new LoginController(dono);
        loginController.criaListaFuncionarios(dono);
        assertThrows(SenhaInvalidaException.class, ()->{
            loginController.verificaNoSitema("14945740712", "1234");
        });
    }

    @Test
    protected void loginFailUsuarioNaoCadastrado() {
        LoginController loginController = new LoginController(dono);
        loginController.criaListaFuncionarios(dono);
        assertThrows(UsuarioNaoCadastradoException.class, ()->{
            loginController.verificaNoSitema("12345678909","1234");
        });
    }

}


