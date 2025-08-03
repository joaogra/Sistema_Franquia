package controller.gerente;

import model.Pedido;
import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;
import model.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PedidoController {
    private Gerente gerente;
    private Vendedor vendedor;
    private Pedido pedido;
    public PedidoController(Gerente gerente) {
        this.gerente = gerente;
    }
    public PedidoController() {}
    public PedidoController(Pedido pedido){this.pedido = pedido;}
    public PedidoController(Vendedor vendedor){this.vendedor = vendedor;}
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
            for(Pedido pedido : gerente.getFranquia().getPedidosParaAlterar()){
                Object[] linha = {
                        pedido.getCod(),
                        pedido.getCliente().getNome(),
                        pedido.getHorario(),
                        pedido.getFormaPagamento(),
                        pedido.getValVenda(),
                        gerente.getFranquia().getVendedoresPedidosAlterados().get(i),
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

    public String nomeCliente(){
        return pedido.getCliente().getNome();
    }

    public String cpfCliente(){
        return pedido.getCliente().getCPF();
    }

    public String getCodigo(){
        return pedido.getCod();
    }

    public Map<String, Pedido> getListaPedidos() {
        return vendedor.getHistoricoPedidos();
    }
    public void editaPedido(Vendedor vendedor,Pedido pedidoAlterado) {
        vendedor.getHistoricoPedidos().get(pedidoAlterado.getCod()).setPedido(pedidoAlterado);
        gerente.removeSolicitacaoPedido(vendedor, pedidoAlterado);
    }
    public void rejeitaAlteracao(Vendedor vendedor, Pedido pedido) {
        gerente.removeSolicitacaoPedido(vendedor, pedido);
    }
}
