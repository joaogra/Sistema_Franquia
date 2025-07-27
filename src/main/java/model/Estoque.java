package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estoque {
    Map<String, Produto> estoque;

    public Estoque(){
        estoque = new HashMap<String, Produto>();
    }

    public Map<String, Produto> getEstoque() {
        return estoque;
    }
    public List<Produto> getProdutos(){
        return new ArrayList<Produto>(estoque.values());
    }

    public void adicionaProduto(Produto produto){
        estoque.put(produto.getCod(), produto);
    }
    public void removerProduto(String codigo){
        estoque.remove(codigo);
    }
    public Produto buscaProduto(String codigo){
        Produto produto = estoque.get(codigo);
        return produto;
    }
    public void editarProduto(String codigo,Produto produtoNovo){
        Produto produtoAtual = estoque.get(codigo);
        produtoAtual.setNome(produtoNovo.getNome());
        produtoAtual.setPreco(produtoNovo.getPreco());
        produtoAtual.setQuantidade(produtoNovo.getQuantidade());
    }
}
