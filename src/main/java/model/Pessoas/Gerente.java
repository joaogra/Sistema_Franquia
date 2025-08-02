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
    public void editarVendedor(Vendedor vendedorEditado) {
        for (Vendedor vendedor : franquia.getVendedores()) {
                if (vendedor.getCPF().equals(vendedorEditado.getCPF())) {

                    vendedor.setNome(vendedorEditado.getNome());
                    vendedor.setEmail(vendedorEditado.getEmail());
                    return;
                }
        }
        throw new IllegalArgumentException("Vendedor com CPF " + vendedorEditado.getCPF() + " não encontrado.");
    }
    public void adicionarSolicitaoPedido(Vendedor vendedor,Pedido pedido) {
        this.franquia.getPedidosParaAlterar().add(pedido);
        this.franquia.getVendedoresPedidosAlterados().add(vendedor);
    }
    public void removeSolicitacaoPedido(Vendedor vendedor,Pedido pedido) {
        this.franquia.getVendedoresPedidosAlterados().remove(vendedor);
        this.franquia.getPedidosParaAlterar().remove(pedido);
    }
}
