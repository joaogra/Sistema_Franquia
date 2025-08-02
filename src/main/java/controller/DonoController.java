package controller;

import Exceptions.FranquiaNomeIgualException;
import model.Endereco;
import model.Franquia;
import model.Pessoas.Dono;
import model.Pessoas.Gerente;
import model.ViaCepService;

import java.util.ArrayList;
import java.util.List;

public class DonoController {
    Dono dono;
    public DonoController(Dono dono) {
        this.dono = dono;
    }
    public List<Object[]> listaFranquias() {
        List<Object[]> lista = new ArrayList<Object[]>();
        for(Franquia franquia : dono.getListaFranquias()){
            Object[] linha = {
                    franquia.getNome(),
                    franquia.getEndereco().getCep(),
                    franquia.getGerente().getNome(),
                    "R$" + String.format("%.2f",0f),
                    franquia
            };
            lista.add(linha);
        }
        return lista;
    }
    public void cadastrarFranquia(Franquia franquia,Gerente gerente) throws FranquiaNomeIgualException {
        if(nomeFranquiaJaExiste(franquia)) {
            throw new FranquiaNomeIgualException("Não pode cadastrar franquias com nomes iguais!");
        }
            dono.getListaFranquias().add(franquia);
            gerente.associaGerenteFranquia(franquia);
    }
    private boolean nomeFranquiaJaExiste(Franquia franquia) {
        for(Franquia f : dono.getListaFranquias()){
            if(f.getNome().trim().equalsIgnoreCase(franquia.getNome())){
                return true;
            }
        }
        return false;
    }
    public Endereco cadastraCEP(String[] informacoes) {
        Endereco CEP = new Endereco();
        CEP.setCep(informacoes[0]);
        CEP.setLocalidade(informacoes[1]);
        CEP.setEstado(informacoes[2]);
        CEP.setLogradouro(informacoes[3]);
        CEP.setBairro(informacoes[4]);
        CEP.setComplemento(informacoes[5]);
        CEP.setNumero(informacoes[6]);
        return CEP;
    }
}
