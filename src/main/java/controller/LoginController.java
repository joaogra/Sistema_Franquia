package controller;

import Exceptions.SenhaInvalidaException;
import Exceptions.UsuarioNaoCadastradoException;
import controller.gerente.VendedorController;
import controller.gerente.VendedorTelaController;
import model.Franquia;
import model.Pessoas.Dono;
import model.Pessoas.Funcionario;
import model.Pessoas.Vendedor;

import java.util.LinkedHashMap;
import java.util.Map;

public class LoginController {

    private Map<String, FuncionarioController> funcionariosControllers;
    private Map<String, Funcionario> funcionarios;
    public LoginController(Dono dono){
        this.funcionariosControllers = new LinkedHashMap<>();
        this.funcionarios = new LinkedHashMap<>();
        criaListaFuncionarios(dono);
    }

    public void criaListaFuncionarios(Dono dono){

        funcionariosControllers.put(dono.getCPF(), new DonoController(dono));
        funcionarios.put(dono.getCPF(), dono);
        for(Franquia franquia: dono.getListaFranquias()){
            franquia.setDono(dono);
            if(franquia.getGerente() != null) {
                franquia.getGerente().associaGerenteFranquia(franquia);
                funcionariosControllers.put(franquia.getGerente().getCPF(), new GerenteController(dono,franquia.getGerente()));
                funcionarios.put(franquia.getGerente().getCPF(), franquia.getGerente());

            }
            for(Vendedor vendedor: franquia.getVendedores()){
                vendedor.associaFranquia(franquia);
                funcionariosControllers.put(vendedor.getCPF(), new VendedorTelaController(dono,vendedor));
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
    public void logar(String CPF){
        funcionariosControllers.get(CPF).abrirTela();
    }
}
