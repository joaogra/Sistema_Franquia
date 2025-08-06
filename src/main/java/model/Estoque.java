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

    public List<Produto> getProdutos(){
        return new ArrayList<Produto>(estoque.values());
    }

    public boolean adicionaProduto(Produto produto){
        if(!estoque.containsKey(produto.getCod())) {
            estoque.put(produto.getCod(), produto);
            return true;
        }
        return false;
    }
    public boolean removerProduto(String codigo){
        if(estoque.containsKey(codigo) ){
            estoque.remove(codigo);
            return true;
        }
        return false;
    }
    public Produto buscaProduto(String codigo){
        Produto produto = estoque.get(codigo);
        return produto;
    }
    public void editarProduto(String codigo,Produto produtoNovo){
        Produto produtoAtual = estoque.get(codigo);
        produtoAtual.setNome(produtoNovo.getNome());
        produtoAtual.setPreco(produtoNovo.getPreco());
        produtoAtual.setQuandidadeEditada(produtoNovo.getQuantidadeEstoque());
    }
}
