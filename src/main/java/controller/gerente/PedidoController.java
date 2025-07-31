package controller.gerente;

import model.Pedido;
import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;
import model.Produto;

import java.util.ArrayList;
import java.util.List;

public class PedidoController {
    private Gerente gerente;
    public PedidoController(Gerente gerente) {
        this.gerente = gerente;
    }
    public PedidoController() {}
    public List<Object[]> listaPedidosParaTabela() {
        List<Object[]> listaPedidos = new ArrayList<>();
        for(Vendedor vendedor : gerente.getFranquia().getVendedores()) {
            for(Pedido pedido : vendedor.getHistoricoPedidos().values()){
                Object[] linha = {
                        pedido.getCod(),
                        pedido.getCliente().getNome(),
                        pedido.getHorario(),
                        pedido.getFormaPagamento(),
                        pedido.getValVenda(),
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
            for(Pedido pedido : gerente.getPedidosParaAlterar()){
                Object[] linha = {
                        pedido.getCod(),
                        pedido.getCliente().getNome(),
                        pedido.getHorario(),
                        pedido.getFormaPagamento(),
                        pedido.getValVenda(),
                        gerente.getVendedoresPedidosAlterados().get(i),
                        pedido
                };
                listaPedidos.add(linha);
                i++;
            }

        return listaPedidos;
    }
    public List<Object[]> listaProdutosUnicoPedido(Pedido pedido) {
        List<Object[]> listaProdutos = new ArrayList<>();
        for(Produto produto : pedido.getProdutos()) {
            Object[] linha = {
                    produto.getCod(),
                    produto.getNome(),
                    pedido.getQuantidade(produto),
                    produto.getPreco()
            };
            listaProdutos.add(linha);
        }
        return listaProdutos;
    }
    public void editaPedido(Vendedor vendedor,Pedido pedidoAlterado) {
        vendedor.getHistoricoPedidos().get(pedidoAlterado.getCod()).setPedido(pedidoAlterado);
        System.out.println("pedido alterado");
        gerente.getVendedoresPedidosAlterados().remove(vendedor);
        gerente.getPedidosParaAlterar().remove(pedidoAlterado);
    }
    public void rejeitaAlteracao(Vendedor vendedor, Pedido pedido) {
        gerente.getVendedoresPedidosAlterados().remove(vendedor);
        gerente.getPedidosParaAlterar().remove(pedido);
    }
}
