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

    //
    public boolean adicionaPedido(Pedido pedido) {
        if(!historicoPedidos.containsKey(pedido.getCod())) {
            historicoPedidos.put(pedido.getCod(), pedido);
            return true;
        }
        return false;
    }
    public float valorTotalVendas() {
        float somaVendas = 0;
        for(Pedido pedido : historicoPedidos.values()) {
            somaVendas += pedido.getValVenda();
        }
        return somaVendas;
    }
}
