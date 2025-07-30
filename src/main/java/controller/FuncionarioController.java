package controller;

import model.Franquia;
import model.Pessoas.Dono;
import model.Pessoas.Funcionario;

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
            for(Funcionario funcionario: franquia.getFuncionarios()){
                funcionarios.put(funcionario.getCPF(), funcionario);
            }
        }
    }

    public void verificaNoSitema(String cpf, String senha){
        if(funcionarios.containsKey(cpf)){
            if(funcionarios.get(cpf).getSenha().equals(senha)){
                JOptionPane.showMessageDialog(null, "Acesso Permitido");
            }else{
                JOptionPane.showMessageDialog(null, "Senha Incorreta");
            }
        }else{
            JOptionPane.showMessageDialog(null,"Você não está no Sistema");
        }
    }

}
