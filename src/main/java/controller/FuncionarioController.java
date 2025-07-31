package controller;

import model.Franquia;
import model.Pessoas.Dono;
import model.Pessoas.Funcionario;
import model.Pessoas.Vendedor;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FuncionarioController {

    Map<String, Funcionario> funcionarios;

    public FuncionarioController(Dono dono){
        funcionarios = new LinkedHashMap<>();
        criaListaFuncionarios(dono);
    }

    public void criaListaFuncionarios(Dono dono){

        funcionarios.put(dono.getCPF(), dono);
        for(Franquia franquia: dono.getListaFranquias()){
            funcionarios.put(franquia.getGerente().getCPF(),franquia.getGerente());
            for(Vendedor vendedor: franquia.getVendedores()){
                funcionarios.put(vendedor.getCPF(), vendedor);
            }
        }
    }

    public void verificaNoSitema(String cpf, String senha){
        if(funcionarios.containsKey(cpf)){
            if(funcionarios.get(cpf).getSenha().equals(senha)){
                //JOptionPane.showMessageDialog(null, "Acesso Permitido");
            }else{
               // JOptionPane.showMessageDialog(null, "Senha Incorreta");
            }
        }else{
            //JOptionPane.showMessageDialog(null,"Você não está no Sistema");
        }
    }

}
