package controller.gerente;
import model.Franquia;
import model.Pessoas.Gerente;
import model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoController {
    private Franquia franquia;

    public ProdutoController(Franquia franquia) {
        this.franquia = franquia;
    }

    public List<Object[]> listarProdutosParaTabela() {
        List<Object[]> dados = new ArrayList<>();
        for (Produto p : franquia.getEstoque().getProdutos()) {
            String status = p.getQuantidadeEstoque() <= 50 ? "Em Falta" : "OK";//numero fixado
            Object[] linha = {
                    p.getCod(),
                    p.getNome(),
                    String.format("R$ %.2f", p.getPreco()),
                    p.getQuantidadeEstoque(),
                    status
            };
            dados.add(linha);
        }
        return dados;
    }

    public Produto buscarProduto(String codigo) {
        return franquia.getEstoque().buscaProduto(codigo);
    }
    public void editarProduto(String codigo, Produto produto) {
        franquia.getEstoque().editarProduto(codigo, produto);
    }
    //boolean para saber se deu certo
    public boolean removerProduto(String codigo) {
        return franquia.getEstoque().removerProduto(codigo);
    }
    public boolean adicionarProduto(Produto produto) {
        return franquia.getEstoque().adicionaProduto(produto);
    }

}