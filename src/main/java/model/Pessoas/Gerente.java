package model.Pessoas;

import model.Estoque;
import model.Franquia;
import model.Produto;

public class Gerente extends Funcionario{
    private Franquia franquia;
    public Gerente (String nome, String CPF, String email, String senha){
        super(nome, CPF, email, senha);
    }
    public Franquia getFranquia() {
        return franquia;
    }
    public void associaGerenteFranquia(Franquia franquia) {
        this.franquia = franquia;
    }
}
