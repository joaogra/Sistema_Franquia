package model;

import model.Pessoas.Cliente;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Pedido {

    private String cod;
    private Map<Produto, Integer> produtos;
    private Double valVenda;
    private Cliente cliente;
    private Date horario;
    private  String formaPagamento;
    private float taxa;

    public Pedido(String cod, Cliente cliente, Date horario, String formaPagamento, float taxa, Map<Produto, Integer> produtos, Double valVenda) {
        this.cod = cod;
        this.cliente = cliente;
        this.horario = horario;
        this.formaPagamento = formaPagamento;
        this.taxa = taxa;
        this.valVenda = valVenda;
        this.produtos = produtos;
    }

    public String getCod(){return cod;}

    public Cliente getCliente(){return cliente;}

    public Date getHorario(){return horario;}

    public String getFormaPagamento(){return formaPagamento;}

    public Double getValVenda(){return valVenda;}

    public float getTaxa(){return taxa;}

    public void setCod(String cod){this.cod = cod;}

    public void setCliente(Cliente cliente){this.cliente = cliente;}

    public void setHorario(Date horario){this.horario = horario;}

    public void setTaxa(float taxa){this.taxa = taxa;}

    public float calculaValor(){
        //
    return 1;
    }
}
