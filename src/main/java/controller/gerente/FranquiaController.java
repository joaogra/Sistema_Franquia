package controller.gerente;

import controller.vendedor.VendedorOperaController;
import model.Pedido;
import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;

public class FranquiaController {
    private Gerente gerente;
    private VendedorOperaController vendedor;
    public FranquiaController(Gerente gerente) {
        this.gerente = gerente;
    }
    public FranquiaController(VendedorOperaController vendedor){this.vendedor=vendedor;}
    public int numTotalVendas() {
        int totalVendas = 0;
        for (Vendedor vendedor : gerente.getFranquia().getVendedores()) {
            totalVendas += vendedor.getNumVendas();
        }
        return totalVendas;
    }

    public void adicionaSolitacaoPedido(Pedido pedido){
        vendedor.retornaFranquia().getPedidosParaAlterar().add(pedido);
        vendedor.retornaFranquia().getVendedoresPedidosAlterados().add(vendedor.getVendedor());
    }
}
