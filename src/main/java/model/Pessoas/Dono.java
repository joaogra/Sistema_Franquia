package model.Pessoas;

import model.Franquia;

import java.util.ArrayList;
import java.util.List;

public class Dono extends Funcionario{

    private List<Franquia> listaFranquias;

    public Dono(String nome, String CPF, String email,String senha, List<Franquia>listaFranquias) {
        super(nome,CPF,email,senha);
        this.listaFranquias = new ArrayList<>(listaFranquias);
    }
    public List<Gerente> getListaGerentes() {
        List<Gerente> listaGerentes = new ArrayList<>();
        for(Franquia f : listaFranquias){
            listaGerentes.add(f.getGerente());
        }
        return listaGerentes;
    }
    public List<Franquia> getListaFranquias() {
        return listaFranquias;
    }
}
