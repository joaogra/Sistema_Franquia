package model.Pessoas;

import model.Franquia;

import java.util.ArrayList;
import java.util.List;

public class Dono extends Funcionario{
    private List<Franquia> franquias;
    public Dono(String nome, String CPF, String email,String senha) {
        super(nome,CPF,email,senha);
        this.franquias = new ArrayList<>();
    }
    public void cadastrarFranquia(Franquia f) {
        franquias.add(f);
    }

    public void removerFranquia(Franquia f) {
        franquias.remove(f);
    }

    public List<Franquia> getFranquias() {
        return franquias;
    }
}
