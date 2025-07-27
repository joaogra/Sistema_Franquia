package model;

import model.Pessoas.Funcionario;

import java.util.List;

public class Franquia {

    private String nome;
    private String endereco;
    private float lucro;
    private List <Funcionario> funcionarios;
    private Estoque estoque;

    public Franquia(String nome, String endereco, float lucro, List<Funcionario> funcionarios, Estoque estoque) {
        this.nome = nome;
        this.endereco = endereco;
        this.lucro = 0;
        this.funcionarios = funcionarios;
        this.estoque = estoque;
    }

    public String getNome() {return nome;}

    public String getEndereco() {return endereco;}

    public float getLucro() {return lucro;}

    public List <Funcionario> getFuncionarios() {return funcionarios;}

    public Estoque getEstoque() {return estoque;}

    public void setNome(String nome) {this.nome = nome;}

    public void setEndereco(String endereco) {this.endereco = endereco;}

    public void setLucro(float lucro) {this.lucro = lucro;}

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }
}
