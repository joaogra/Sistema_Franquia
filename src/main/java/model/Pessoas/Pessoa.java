package model.Pessoas;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

import javax.swing.*;

public class Pessoa {

    private String nome;
    private String CPF;

    public Pessoa(String nome, String CPF){
        if(verificaCPF(CPF)){
        this.nome = nome.toUpperCase().trim();
        this.CPF = CPF;
        }
        else{
        throw new IllegalArgumentException("CPF invalido!");
        }
    }

    public String getNome() {return nome;}

    public String getCPF() {return CPF;}

    public void setNome(String nome) { this.nome = nome;}

    public void setCPF(String CPF) {this.CPF = CPF;}

    private boolean verificaCPF(String CPF){
        CPFValidator validador = new CPFValidator();
        try{
            validador.assertValid(CPF);
            return true;
        } catch (InvalidStateException e ){
            return false;

        }
    }
}