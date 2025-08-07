package controller.gerente;

import model.Franquia;
import model.Pedido;
import model.Pessoas.Cliente;
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
            for(Pedido pedido : franquia.getTodosPedidos()){
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
        vendedor.setValorTotalVendas(-vendedor.getHistoricoPedidos().get(pedidoAlterado.getCod()).getValVenda());
       vendedor.setValorTotalVendas(pedidoAlterado.getValVenda());
       //pega lista de codigos dos produtos do pedido anterior
        List<Produto> produtosPedido = new ArrayList<>();

        //pega lista de codigos dos produtos do pedido anterior
        Set<String> listaCodigos = vendedor.getHistoricoPedidos().get(pedidoAlterado.getCod()).getMapProdutos().keySet();
        for(String codigo : listaCodigos){
            produtosPedido.add(franquia.getEstoque().buscaProduto(codigo));
        }
        for(Produto p : produtosPedido){
            int qtd1 = vendedor.getHistoricoPedidos().get(pedidoAlterado.getCod()).getMapProdutos().get(p.getCod());
            p.setQuantidadeEstoque(-qtd1);
        }
        produtosPedido.clear();
        listaCodigos = pedidoAlterado.getMapProdutos().keySet();
        for(String codigo : listaCodigos){
            produtosPedido.add(franquia.getEstoque().buscaProduto(codigo));
        }
        for(Produto p : produtosPedido) {
            int qtd2 = pedidoAlterado.getMapProdutos().get(p.getCod());
            p.setQuantidadeEstoque(qtd2);
        }

        for(Cliente c : franquia.getClientes()){
            if(c.equals(pedidoAlterado.getCliente())){
                c.setGastoTotal(-vendedor.getHistoricoPedidos().get(pedidoAlterado.getCod()).getValVenda() + pedidoAlterado.getValVenda());
            }
        }
        vendedor.getHistoricoPedidos().put(pedidoAlterado.getCod(), pedidoAlterado);
        for(Pedido p : franquia.getTodosPedidos()){
            if(p.getCod().equals(pedidoAlterado.getCod())){
                franquia.getTodosPedidos().remove(p);
                franquia.getTodosPedidos().add(pedidoAlterado);
                break;
            }
        }

        franquia.getGerente().removeSolicitacaoPedido(vendedor, pedidoAlterado);
    }
    public void rejeitaAlteracao(Vendedor vendedor, Pedido pedido) {
        franquia.getGerente().removeSolicitacaoPedido(vendedor, pedido);
    }
}
