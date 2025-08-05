package model;

import model.Pessoas.Cliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Pedido {

    private String cod;
    private Map<String,Integer> produtos;
    private Double valVenda;
    private Cliente cliente;
    private Date horario;
    private  String formaPagamento;
    private float taxa;
    private String motivoSolicitacao;
    public Pedido(String cod, Cliente cliente, Date horario, String formaPagamento, float taxa, Map<String,Integer> produtos, Double valVenda) {
        this.cod = cod;
        this.cliente = cliente;
        this.horario = horario;
        this.formaPagamento = formaPagamento;
        this.taxa = taxa;
        this.valVenda = valVenda;
        this.motivoSolicitacao = "";
        this.produtos = produtos;
    }

    public String getCod(){return this.cod;}

    public Cliente getCliente(){return cliente;}

    public Date getHorario(){return horario;}

    public String getFormaPagamento(){return formaPagamento;}

    public Double getValVenda(){return valVenda;}
    public  String getMotivoSolicitacao(){return motivoSolicitacao;}

    public float getTaxa(){return taxa;}
    public int getQuantidade(Produto produto){return produtos.get(produto.getCod());}
    public Map<String,Integer> getMapProdutos(){return produtos;}
    public void setMotivoSolicitacao(String motivoSolicitacao){this.motivoSolicitacao = motivoSolicitacao;}
    public void setCliente(Cliente cliente){this.cliente = cliente;}


    public void setPedido(Pedido pedido){
        this.valVenda = pedido.getValVenda();
        this.formaPagamento = pedido.getFormaPagamento();
        this.produtos = pedido.getMapProdutos();
        this.taxa = pedido.getTaxa();
        this.motivoSolicitacao = "";
    }
    public void setMotivo(String motivo){this.motivoSolicitacao=motivo;}
}
