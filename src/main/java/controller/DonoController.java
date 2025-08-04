package controller;

import Exceptions.CPFJaCadastradoException;
import Exceptions.FranquiaJaPossuiGerenteException;
import Exceptions.FranquiaNaoPossuiGerenteException;
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
        List<Object[]> lista = new ArrayList<>();
        for(Franquia franquia : dono.getListaFranquias()){
            String nomeGerente = franquia.getGerente() != null ? franquia.getGerente().getNome() : "";
            Object[] linha = {
                    franquia.getNome(),
                    franquia.getEndereco().getCep(),
                    nomeGerente,
                    "R$" + String.format("%.2f",0f),
                    franquia
            };
            lista.add(linha);
        }
        return lista;
    }
    public void cadastrarFranquia(Franquia franquia,Gerente gerente) throws FranquiaNomeIgualException,CPFJaCadastradoException {
        if(nomeFranquiaJaExiste(franquia)) {
            throw new FranquiaNomeIgualException("Não é possível cadastrar franquias com nomes iguais!");
        }
        if(CPFJaCadastrado(gerente)){
            throw new CPFJaCadastradoException("Esse gerente já está cadastrado no sistema!");
        }
            dono.getListaFranquias().add(franquia);
            gerente.associaGerenteFranquia(franquia);
    }
    private boolean CPFJaCadastrado(Gerente gerente) {
        for(Gerente g : dono.getListaGerentes()){
            if(g.getCPF().equals(gerente.getCPF())){
                return true;
            }
        }
        return false;
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
    public void cadastrarGerente(Franquia franquia, Gerente gerente) throws FranquiaJaPossuiGerenteException {
        if (franquia.getGerente() != null) {
            throw new FranquiaJaPossuiGerenteException("Esta franquia já possui um gerente.");
        }
        franquia.associarGerente(gerente);
        gerente.associaGerenteFranquia(franquia);
    }
    public void removeGerente(Franquia franquia) throws FranquiaNaoPossuiGerenteException {
        if(franquia.getGerente() == null) {
            throw new FranquiaNaoPossuiGerenteException("Esta franquia não possui um gerente cadastrado.");
        }
        franquia.desassociaGerente();
    }
}
