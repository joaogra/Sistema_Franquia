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
    public List<Vendedor> getVendedores() {
        List<Vendedor> vendedores = new ArrayList<>();
        for (Funcionario f : franquia.getFuncionarios()) {
            if (f instanceof Vendedor) {
                vendedores.add((Vendedor) f);
            }
        }
        return vendedores;
    }
    public void removerVendedor(Vendedor vendedor) {
        if(!getVendedores().isEmpty()) {
            franquia.getFuncionarios().remove(vendedor);
        }
    }
    public void adicionarVendedor(Vendedor vendedor) {
        franquia.getFuncionarios().add(vendedor);
    }
    public void editarVendedor(Vendedor vendedorEditado) {
        for (Funcionario funcionario : franquia.getFuncionarios()) {
            if (funcionario instanceof Vendedor) {
                Vendedor v = (Vendedor) funcionario;
                if (v.getCPF().equals(vendedorEditado.getCPF())) {
                    v.setNome(vendedorEditado.getNome());
                    v.setEmail(vendedorEditado.getEmail());
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Vendedor com CPF " + vendedorEditado.getCPF() + " não encontrado.");
    }
}
