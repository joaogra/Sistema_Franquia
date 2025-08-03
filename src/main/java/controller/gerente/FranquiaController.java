package controller.gerente;

import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;

public class FranquiaController {
    private Gerente gerente;
    public FranquiaController(Gerente gerente) {
        this.gerente = gerente;
    }
    public int numTotalVendas(){
        int totalVendas = 0;
        for(Vendedor vendedor : gerente.getFranquia().getVendedores()) {
            totalVendas += vendedor.getNumVendas();
        }
        return totalVendas;
    }
    public float somaVendas(){
        float somaVendas = 0;
        for(Vendedor vendedor : gerente.getFranquia().getVendedores()) {
            somaVendas += vendedor.valorTotalVendas();
        }
        return somaVendas;
    }
}
