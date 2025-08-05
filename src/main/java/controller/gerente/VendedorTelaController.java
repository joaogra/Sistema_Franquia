package controller.gerente;

import controller.FuncionarioController;
import model.Pessoas.Dono;
import model.Pessoas.Vendedor;
import view.telasVendedor.TelaVendedor;

public class VendedorTelaController extends FuncionarioController {
    private Dono dono;
    private Vendedor vendedor;
    public VendedorTelaController(Dono dono, Vendedor vendedor){
        this.vendedor = vendedor;
        this.dono = dono;
    }

    @Override
    protected void abrirTela() {
        new TelaVendedor(dono,vendedor).setVisible(true);
    }
}
