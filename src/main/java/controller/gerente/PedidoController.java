package controller.gerente;

import model.Franquia;
import model.Pedido;
import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;
import model.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PedidoController {
    private Franquia franquia;
    private Vendedor vendedor;
    private Pedido pedido;
    public PedidoController(Franquia franquia) {
        this.franquia = franquia;
    }
    public List<Object[]> listaPedidosParaTabela() {
        List<Object[]> listaPedidos = new ArrayList<>();
        for(Vendedor vendedor : franquia.getVendedores()) {
            for(Pedido pedido : vendedor.getHistoricoPedidos().values()){
                Object[] linha = {
                        pedido.getCod(),
                        pedido.getCliente().getNome(),
                        pedido.getHorario(),
                        pedido.getFormaPagamento(),
                        "R$" + String.format("%.2f",pedido.getValVenda()),
                        pedido
                };
                listaPedidos.add(linha);
            }
        }
        return listaPedidos;
    }
    public List<Object[]> listaSolicitacaoPedidosParaTabela() {
        List<Object[]> listaPedidos = new ArrayList<>();
            int i = 0;
            for(Pedido pedido : franquia.getPedidosParaAlterar()){
                Object[] linha = {
                        pedido.getCod(),
                        pedido.getCliente().getNome(),
                        pedido.getHorario(),
                        pedido.getFormaPagamento(),
                        "R$" + String.format("%.2f",pedido.getValVenda()),
                        franquia.getVendedoresPedidosAlterados().get(i),
                        pedido
                };
                listaPedidos.add(linha);
                i++;
            }

        return listaPedidos;
    }
    public List<Object[]> listaProdutosUnicoPedido(Pedido pedido) {
        List<Object[]> listaProdutos = new ArrayList<>();
        List<Produto> produtosPedido = new ArrayList<>();

        Set<String> listaCodigos = pedido.getMapProdutos().keySet();
        for(String codigo : listaCodigos){
            produtosPedido.add(franquia.getEstoque().buscaProduto(codigo));
        }
        for(Produto produto : produtosPedido) {
            Object[] linha = {
                    produto.getCod(),
                    produto.getNome(),
                    pedido.getQuantidade(produto),
                    "R$" + String.format("%.2f",produto.getPreco()),
                    produto
            };
            listaProdutos.add(linha);
        }
        return listaProdutos;
    }
    public void editaPedido(Vendedor vendedor,Pedido pedidoAlterado) {
        pedidoAlterado.setMotivoSolicitacao("");
        vendedor.getHistoricoPedidos().get(pedidoAlterado.getCod()).setPedido(pedidoAlterado);
        franquia.getGerente().removeSolicitacaoPedido(vendedor, pedidoAlterado);
    }
    public void rejeitaAlteracao(Vendedor vendedor, Pedido pedido) {
        franquia.getGerente().removeSolicitacaoPedido(vendedor, pedido);
    }
}
