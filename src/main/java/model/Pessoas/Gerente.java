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
    List<Vendedor> vendedoresPedidosAlterados;
    List<Pedido> pedidosParaAlterar;
    public Gerente (String nome, String CPF, String email,String senha) {
        super(nome, CPF, email,senha);
        this.vendedoresPedidosAlterados = new ArrayList<>();
        this.pedidosParaAlterar = new ArrayList<>();
    }
    public Franquia getFranquia() {
        return franquia;
    }
    public void associaGerenteFranquia(Franquia franquia) {
        this.franquia = franquia;
    }


    public void removerVendedor(Vendedor vendedor) {
        if(!franquia.getVendedores().isEmpty()) {
            franquia.getVendedores().remove(vendedor);
        }
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
        this.pedidosParaAlterar.add(pedido);
        this.vendedoresPedidosAlterados.add(vendedor);
    }
    public List<Pedido>  getPedidosParaAlterar() {
        return pedidosParaAlterar;
    }
    public List<Vendedor> getVendedoresPedidosAlterados() {
        return vendedoresPedidosAlterados;
    }
}
