package model.Pessoas;

import model.Franquia;
import model.Pedido;
import model.Produto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gerente extends Funcionario{
    private transient Franquia franquia;

    public Gerente (String nome, String CPF, String email,String senha) {
        super(nome, CPF, email,senha);
        this.franquia = null;
    }
    public Franquia getFranquia() {
        return franquia;
    }
    public void associaGerenteFranquia(Franquia franquia) {
        this.franquia = franquia;
    }


    public void removerVendedor(Vendedor vendedor) {
            franquia.getVendedores().remove(vendedor);
    }
    public void adicionarVendedor(Vendedor vendedor) {
        franquia.getVendedores().add(vendedor);
    }
    public void removeSolicitacaoPedido(Vendedor vendedor,Pedido pedido) {
        franquia.getVendedoresPedidosAlterados().remove(vendedor);
        franquia.getPedidosParaAlterar().remove(pedido);
    }
    public void desassociaGerenteFranquia(){
        this.franquia = null;
    }
}
