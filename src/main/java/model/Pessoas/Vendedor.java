package model.Pessoas;

import model.Franquia;
import model.Pedido;
import model.Produto;

import javax.swing.*;
import java.util.*;

public class Vendedor extends Funcionario {
    private transient Franquia franquia;
    private int numVendas;
    private Map<String, Pedido> historicoPedidos;
    private float valorTotalVendas;

    public Vendedor(String nome, String CPF, String email,String senha,Franquia franquia) {
        super(nome,CPF, email,senha);
        this.numVendas = 0;
        this.historicoPedidos = new HashMap<>();
        this.franquia = franquia;
        this.valorTotalVendas = 0;
    }
    //GETTERS
    public int getNumVendas() { return numVendas;}
    public Map <String, Pedido> getHistoricoPedidos() {return historicoPedidos;}
    public Franquia getFranquia(){ return franquia;}

    public float getValorTotalVendas(){return valorTotalVendas;}
    //SETTERS
    public void associaFranquia(Franquia franquia) {
        this.franquia = franquia;
    }
    public void setNumVendas(int numVendas) { this.numVendas = numVendas;}
    public void setValorTotalVendas(float valPedidoAtual) { this.valorTotalVendas += valPedidoAtual;}
    //
    public boolean adicionaPedido(Pedido pedido) {
        if(!this.historicoPedidos.containsKey(pedido.getCod())) {
            List<Produto> produtosPedido = new ArrayList<>();
            Set<String> listaCodigos = pedido.getMapProdutos().keySet();
            for(String codigo : listaCodigos){
                produtosPedido.add(franquia.getEstoque().buscaProduto(codigo));
            }

            for(Produto produto : produtosPedido){
                if(produto.getQuantidadeEstoque() < pedido.getMapProdutos().get(produto.getCod())){
                    return false;
                }
            }
            historicoPedidos.put(pedido.getCod(), pedido);
            if(!franquia.getClientes().contains(pedido.getCliente())) {
                franquia.adicionarCliente(pedido.getCliente());
            }
            for(String codigo : listaCodigos){
                produtosPedido.add(franquia.getEstoque().buscaProduto(codigo));
            }
            for(Produto produto : produtosPedido){
                produto.setQuantidadeEstoque(pedido.getMapProdutos().get(produto.getCod()));
            }
            pedido.getCliente().setQuantidadeCompras();
            pedido.getCliente().setGastoTotal( pedido.getValVenda());
            JOptionPane.showMessageDialog(null,"aaaa");
            this.numVendas++;
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
