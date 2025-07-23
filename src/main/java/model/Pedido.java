package model;

import model.Pessoas.Cliente;

import java.util.Date;
import java.util.List;

public class Pedido {

    private String cod;
    private List<Produto> produtos;
    private float valVenda;
    private Cliente cliente;
    private Date horario;
    private  String formaPagamento;
    private float taxa;

    public Pedido(String cod, Cliente cliente, Date horario, String formaPagamento, float taxa){
        this.cod = cod;
        this.cliente = cliente;
        this.horario = horario;
        this.formaPagamento = formaPagamento;
        this.taxa = 0;
    }

    public String getCod(){return cod;}

    public Cliente getCliente(){return cliente;}

    public Date getHorario(){return horario;}

    public String getFormaPagamento(){return formaPagamento;}

    public float getTaxa(){return taxa;}

    public void setCod(String cod){this.cod = cod;}

    public void setCliente(Cliente cliente){this.cliente = cliente;}

    public void setHorario(Date horario){this.horario = horario;}

    public void setTaxa(float taxa){this.taxa = taxa;}
}
