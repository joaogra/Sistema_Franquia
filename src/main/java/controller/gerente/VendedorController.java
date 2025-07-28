package controller.gerente;

import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;
import model.Produto;

import java.util.ArrayList;
import java.util.List;

public class VendedorController {
    private Gerente gerente;
    public VendedorController(Gerente gerente) {
        this.gerente = gerente;
    }
    public List<Object[]> listarVendedorParaTabela() {
        List<Vendedor> vendedores = new ArrayList<>(gerente.getVendedores());

        vendedores.sort((v1, v2) -> Integer.compare(v2.getNumVendas(), v1.getNumVendas()));

        List<Object[]> dados = new ArrayList<>();
        for (Vendedor v : vendedores) {
            Object[] linha = {
                    v.getNome(),
                    v.getNumVendas(),
                    v
            };
            dados.add(linha);
        }
        return dados;
    }
    public void removerVendedor(Vendedor vendedor) {
        gerente.removerVendedor(vendedor);
    }
    public void adicionarVendedor(Vendedor vendedor) {
        gerente.adicionarVendedor(vendedor);
    }
    public void editarVendedor(Vendedor vendedor) {
        gerente.editarVendedor(vendedor);
    }
}
