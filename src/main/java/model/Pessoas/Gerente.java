package model.Pessoas;

import model.Estoque;
import model.Produto;

public class Gerente extends Funcionario{
    private Estoque estoque;
    public Gerente (String nome, String CPF, String email, String senha){
        super(nome, CPF, email, senha);
        this.estoque = new Estoque();
    }
    public Estoque getEstoque() {
        return estoque;
    }
    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }
    public void adicionaProduto(Produto produto){
        estoque.getEstoque().put(produto.getCod(),produto);
    }
}
