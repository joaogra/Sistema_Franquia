package model.Pessoas;

import model.Pedido;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vendedor extends Funcionario {
    int numVendas;
    Map<String, Pedido> historicoPedidos;

    public Vendedor(String nome, String CPF, String email, String senha){
        super(nome,CPF, email, senha);
        this.numVendas = 0;
        historicoPedidos = new HashMap<>();

    }


}
