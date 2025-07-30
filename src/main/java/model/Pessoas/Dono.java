package model.Pessoas;

import model.Franquia;

import java.util.List;

public class Dono extends Funcionario{

    List<Franquia> listaFranquias;

    public Dono(String nome, String CPF, String email,String senha, List<Franquia>listaFranquias) {
        super(nome,CPF,email,senha);
        this.listaFranquias = listaFranquias;
    }

    public List<Franquia> getListaFranquias() {
        return listaFranquias;
    }
}
