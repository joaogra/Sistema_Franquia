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
    private List<Pedido> todosPedidos;
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
        this.todosPedidos = new ArrayList<>();
    }

    public Set<Cliente> getClientes() {
        return clientes;
    }
    public void adicionarPedido(Pedido pedido) {
        todosPedidos.add(pedido);
    }

    public List<Pedido> getTodosPedidos() {
        return todosPedidos;
    }

    public void adicionarCliente(Cliente cliente) {
        if (!clientes.contains(cliente)) {
            this.clientes.add(cliente);
        }
    }
    public Cliente getClienteDoSet(Cliente cliente ){
        for (Cliente c : clientes) {
            if (c.equals(cliente)) {
                return c;
            }
        }
        return null;
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
    public List<Vendedor> getVendedores() { return vendedores;}
    public Estoque getEstoque() { return estoque;}
    public Gerente getGerente() { return gerente;}

    //SETTERS
    public void setDono(Dono dono) {
        this.dono = dono;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void associarGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public void desassociaGerente() {
        this.gerente.desassociaGerenteFranquia();
        this.gerente = null;
    }

    public int getTotalPedidos() {
        return todosPedidos.size();
    }

    public float getFaturamentoBruto() {
        float somaVendas = 0;
        for(Pedido pedido : todosPedidos) {
            somaVendas += pedido.getValVenda();
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
    public int getNumTotalClientes(){
        return clientes.size();
    }
}
