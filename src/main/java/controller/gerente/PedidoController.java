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
    public List<Object[]> listaPedidosParaTabela() {
        List<Object[]> listaPedidos = new ArrayList<>();
        for(Vendedor vendedor : gerente.getVendedores()) {
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
    public List<Object[]> listaProdutosUnicoPedido(Pedido pedido) {
        List<Object[]> listaProdutos = new ArrayList<>();
        for(Produto produto : pedido.getProdutos()) {
            Object[] linha = {
                    produto.getCod(),
                    produto.getNome(),
                    produto.getQuantidadePedido(),
                    produto.getPreco()
            };
            listaProdutos.add(linha);
        }
        return listaProdutos;
    }
}
