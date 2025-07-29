package controller.gerente;
import model.Pessoas.Gerente;
import model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoController {
    private Gerente gerente;

    public ProdutoController(Gerente gerente) {
        this.gerente = gerente;
    }

    public List<Object[]> listarProdutosParaTabela() {
        List<Object[]> dados = new ArrayList<>();
        for (Produto p : gerente.getFranquia().getEstoque().getProdutos()) {
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
        return gerente.getFranquia().getEstoque().buscaProduto(codigo);
    }
    public void editarProduto(String codigo, Produto produto) {
        gerente.getFranquia().getEstoque().editarProduto(codigo, produto);
    }
    //boolean para saber se deu certo
    public boolean removerProduto(String codigo) {
        return gerente.getFranquia().getEstoque().removerProduto(codigo);
    }
    public boolean adicionarProduto(Produto produto) {
        return gerente.getFranquia().getEstoque().adicionaProduto(produto);
    }

}