package model.Pessoas;

import model.Pedido;

import java.util.HashMap;
import java.util.Map;

public class Vendedor extends Funcionario {
    private int numVendas;
    private Map<String, Pedido> historicoPedidos;

    public Vendedor(String nome, String CPF, String email,String senha) {
        super(nome,CPF, email,senha);
        this.numVendas = 0;
        historicoPedidos = new HashMap<>();

    }
    //GETTERS
    public int getNumVendas() { return numVendas;}
    public Map <String, Pedido> getHistoricoPedidos() {return historicoPedidos;}

    //SETTERS
    public void setNumVendas(int numVendas) { this.numVendas = numVendas;}
}
