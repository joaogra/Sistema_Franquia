package model;

import model.Pessoas.Cliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Pedido {

    private String cod;
    private Map<Produto,Integer> produtos;//map
    private Double valVenda;
    private Cliente cliente;
    private Date horario;
    private  String formaPagamento;
    private float taxa;
    private String motivoSolicitacao;
    public Pedido(String cod, Cliente cliente, Date horario, String formaPagamento, float taxa, Map<Produto,Integer> produtos, Double valVenda) {
        this.cod = cod;
        this.cliente = cliente;
        this.horario = horario;
        this.formaPagamento = formaPagamento;
        this.taxa = taxa;
        this.valVenda = valVenda;
        this.motivoSolicitacao = "";
        this.produtos = produtos;
    }
    public List<Produto> getProdutos(){return new ArrayList<>(produtos.keySet());}
    public String getCod(){return cod;}

    public Cliente getCliente(){return cliente;}

    public Date getHorario(){return horario;}

    public String getFormaPagamento(){return formaPagamento;}

    public Double getValVenda(){return valVenda;}
    public  String getMotivoSolicitacao(){return motivoSolicitacao;}

    public float getTaxa(){return taxa;}

    public void setMotivoSolicitacao(String motivoSolicitacao){this.motivoSolicitacao = motivoSolicitacao;}
    public void setCod(String cod){this.cod = cod;}

    public void setCliente(Cliente cliente){this.cliente = cliente;}

    public void setHorario(Date horario){this.horario = horario;}

    public void setTaxa(float taxa){this.taxa = taxa;}

}
