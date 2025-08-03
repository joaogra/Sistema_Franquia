package model.Pessoas;

import model.Franquia;
import model.Pedido;

import java.util.HashMap;
import java.util.Map;

public class Vendedor extends Funcionario {
    private transient Franquia franquia;
    private int numVendas;
    private Map<String, Pedido> historicoPedidos;

    public Vendedor(String nome, String CPF, String email,String senha, Franquia franquia) {
        super(nome,CPF, email,senha);
        this.numVendas = 0;
        historicoPedidos = new HashMap<>();
        this.franquia = franquia;

    }
    //GETTERS
    public int getNumVendas() { return numVendas;}
    public Map <String, Pedido> getHistoricoPedidos() {return historicoPedidos;}
    public Franquia getFranquia(){ return franquia;}

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
