package model;

import model.Pessoas.Cliente;
import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;

import java.util.ArrayList;
import java.util.List;

public class Franquia {

    private String nome;
    private String endereco;
    private float lucro;
    private Gerente gerente;
    private List <Vendedor> vendedores;
    private List <Cliente> clientes;
    private Estoque estoque;

    public Franquia(String nome, String endereco, float lucro, Gerente gerente,List<Vendedor> vendedores, Estoque estoque) {
        this.nome = nome;
        this.endereco = endereco;
        this.lucro = 0;
        this.gerente = gerente;
        this.vendedores= new ArrayList<>(vendedores);
        this.clientes = new ArrayList<>();
        this.estoque = estoque;
    }
    public void adicionarCliente(Cliente cliente) {
        if(!clientes.contains(cliente)) {
            this.clientes.add(cliente);
        }
    }

    //GETTERS
    public String getNome() {return nome;}
    public String getEndereco() {return endereco;}
    public float getLucro() {return lucro;}
    public List <Vendedor> getVendedores() {return vendedores;}
    public Estoque getEstoque() {return estoque;}

    //SETTERS
    public void setNome(String nome) {this.nome = nome;}
    public void setEndereco(String endereco) {this.endereco = endereco;}
    public void setLucro(float lucro) {this.lucro = lucro;}
    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }
}
