package controller.vendedor;

import controller.gerente.VendedorController;
import model.Pedido;
import model.Pessoas.Vendedor;

public class VendedorOperaController {
    private Vendedor vendedor;
    private Pedido pedido;

    public VendedorOperaController(Vendedor v1){
        this.vendedor = v1;
    }

    public void adicionarVenda(Pedido venda){
        vendedor.adicionaPedido(venda);
    }
}
