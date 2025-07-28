package controller.gerente;

import model.Pedido;
import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;

import java.util.ArrayList;
import java.util.List;

public class PedidoController {
    private Gerente gerente;
    public PedidoController(Gerente gerente) {
        this.gerente = gerente;
    }
    public List<Object[]> listaPedidosParaTabela() {
        List<Object[]> listaPedidos = new ArrayList<Object[]>();
        for(Vendedor vendedor : gerente.getVendedores()) {
            for(Pedido pedido : vendedor.getHistoricoPedidos().values()){
                Object[] linha = {
                        pedido.getCod(),
                        pedido.getCliente().getNome(),
                        pedido.getHorario(),
                        pedido.getFormaPagamento(),
                        pedido.getValVenda()
                };
                listaPedidos.add(linha);
            }
        }
        return listaPedidos;
    }
}
