package controller.vendedor;

import Exceptions.CodigoPedidoJaCadastradoException;
import Exceptions.QuantidadeProdutoInsuficienteException;
import controller.gerente.VendedorController;
import model.Franquia;
import model.Pedido;
import model.Pessoas.Vendedor;

public class VendedorOperaController {
    private Vendedor vendedor;
    private Pedido pedido;

    public VendedorOperaController(Vendedor v1){
        this.vendedor = v1;
    }

    public boolean adicionarVenda(Pedido venda) throws CodigoPedidoJaCadastradoException, QuantidadeProdutoInsuficienteException {
        return vendedor.adicionaPedido(venda);
    }

    public Franquia retornaFranquia(){
        return vendedor.getFranquia();
    }
    public Vendedor getVendedor() {
        return vendedor;
    }
    public boolean jaExistePedido(String cod) {
        return vendedor.jaExistePedido(cod);
    }
}
