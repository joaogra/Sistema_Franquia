package model;

import model.Pessoas.Cliente;
import model.Pessoas.Dono;
import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Franquia {

    private String nome;
    private Endereco endereco;
    private Gerente gerente;
    private List<Vendedor> vendedores;
    private Set<Cliente> clientes;
    private Estoque estoque;
    private List<Vendedor> vendedoresPedidosAlterados;
    private List<Pedido> pedidosParaAlterar;
    private transient Dono dono;

    //tirar lista de vendedores do construtor so esta servindo para fins de testes e o estoque tbm
    public Franquia(String nome, Endereco endereco, List<Vendedor> vendedores, Estoque estoque, Dono dono) {
        this.nome = nome.toUpperCase();
        this.endereco = endereco;
        this.vendedores = new ArrayList<>(vendedores);
        this.clientes = new HashSet<>();
        this.estoque = estoque;
        this.vendedoresPedidosAlterados = new ArrayList<>();
        this.pedidosParaAlterar = new ArrayList<>();
        this.dono = dono;
    }

    public Set<Cliente> getClientes() {
        return clientes;
    }

    public void adicionarCliente(Cliente cliente) {
        if (!clientes.contains(cliente)) {
            this.clientes.add(cliente);
        }
    }

    //GETTERS
    public Dono getDono() {
        return dono;
    }
    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public List<Pedido> getPedidosParaAlterar() {
        return pedidosParaAlterar;
    }

    public List<Vendedor> getVendedoresPedidosAlterados() {
        return vendedoresPedidosAlterados;
    }

    //public float getLucro() {return lucro;}
    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public Gerente getGerente() {
        return gerente;
    }

    //SETTERS
    public void setDono(Dono dono) {
        this.dono = dono;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    //public void setLucro(float lucro) {this.lucro = lucro;}
    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public void associarGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public void desassociaGerente() {
        this.gerente.desassociaGerenteFranquia();
        this.gerente = null;
    }

    public int getTotalPedidos() {
        int totalVendas = 0;
        for (Vendedor vendedor : vendedores) {
            totalVendas += vendedor.getNumVendas();
        }
        return totalVendas;
    }

    public float getFaturamentoBruto() {
        float somaVendas = 0;
        for (Vendedor vendedor : vendedores) {
            somaVendas += vendedor.getValorTotalVendas();
        }
        return somaVendas;
    }
    public float getTicketMedio(){
        int numPedidos = getTotalPedidos();
        return numPedidos == 0 ? 0 : getFaturamentoBruto() / numPedidos;
    }
    public float getTicketMedioCliente(){
        return clientes.isEmpty() ? 0 : getFaturamentoBruto() / clientes.size();
    }
}
