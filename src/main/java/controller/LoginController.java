package controller;

import Exceptions.SenhaInvalidaException;
import Exceptions.UsuarioNaoCadastradoException;
import controller.gerente.VendedorController;
import model.Franquia;
import model.Pessoas.Dono;
import model.Pessoas.Funcionario;
import model.Pessoas.Vendedor;

import java.util.LinkedHashMap;
import java.util.Map;

public class LoginController {

    Map<String, FuncionarioController> funcionariosControllers;
    Map<String, Funcionario> funcionarios;
    public LoginController(Dono dono){
        funcionariosControllers = new LinkedHashMap<>();
        criaListaFuncionarios(dono);
    }

    public void criaListaFuncionarios(Dono dono){

        funcionariosControllers.put(dono.getCPF(), new DonoController(dono));
        funcionarios.put(dono.getCPF(), dono);
        for(Franquia franquia: dono.getListaFranquias()){
            if(franquia.getGerente() != null) {
                funcionariosControllers.put(franquia.getGerente().getCPF(), new GerenteController(dono,franquia.getGerente()));
                funcionarios.put(franquia.getGerente().getCPF(), franquia.getGerente());
            }
            for(Vendedor vendedor: franquia.getVendedores()){
                funcionariosControllers.put(vendedor.getCPF(), new VendedorController(vendedor));
                funcionarios.put(vendedor.getCPF(), vendedor);
            }
        }
    }

    public boolean verificaNoSitema(String cpf, String senha) throws SenhaInvalidaException, UsuarioNaoCadastradoException {
        if(funcionarios.containsKey(cpf)) {
            if(funcionarios.get(cpf).getSenha().equals(senha)) {
                return true;
            }
            else{
                throw new SenhaInvalidaException("Senha invalida!");
            }
        }
        throw new UsuarioNaoCadastradoException("Usuario nao cadastrado!");
    }

}
