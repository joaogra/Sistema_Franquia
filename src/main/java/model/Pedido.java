package model;

import model.Pessoas.Cliente;

import java.util.Date;
import java.util.List;

public class Pedido {

    private String cod;
    private List<Produto> produtos;
    private Double valVenda;
    private Cliente cliente;
    private Date horario;
    private  String formaPagamento;
    private float taxa;
    private String motivoSolicitacao;

    public Pedido(String cod, Cliente cliente, Date horario, String formaPagamento, float taxa, List<Produto> produtos, Double valVenda) {
        this.cod = cod;
        this.cliente = cliente;
        this.horario = horario;
        this.formaPagamento = formaPagamento;
        this.taxa = taxa;
        this.produtos = produtos;
        this.valVenda = valVenda;
        this.motivoSolicitacao = "";
    }
    //GETTERS

    public String getCod(){return cod;}
    public Cliente getCliente(){return cliente;}
    public Date getHorario(){return horario;}
    public String getFormaPagamento(){return formaPagamento;}
    public Double getValVenda(){return valVenda;}
    public float getTaxa(){return taxa;}
    public  List<Produto> getProdutos(){return produtos;}
    public String getMotivoSolicitacao(){return motivoSolicitacao;}
    //SETTER
    public void setCod(String cod){this.cod = cod;}
    public void setCliente(Cliente cliente){this.cliente = cliente;}
    public void setHorario(Date horario){this.horario = horario;}
    public void setTaxa(float taxa){this.taxa = taxa;}
    public float calculaValor(){
        for(Produto produto: produtos){
            //valVenda+=
        }
    return 1;
    }
    public void setMotivoSolicitacao(String motivoSolicitacao){
        this.motivoSolicitacao = motivoSolicitacao;
    }
}
