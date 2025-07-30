package model.Pessoas;

import model.Franquia;

import java.util.ArrayList;
import java.util.List;

public class Gerente extends Funcionario{
    private Franquia franquia;
    public Gerente (String nome, String CPF, String email,String senha) {
        super(nome, CPF, email,senha);
    }
    public Franquia getFranquia() {
        return franquia;
    }
    public void associaGerenteFranquia(Franquia franquia) {
        this.franquia = franquia;
    }

    //retorna copia da lista!!!
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
}
