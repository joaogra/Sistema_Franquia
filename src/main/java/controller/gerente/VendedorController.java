package controller.gerente;

import Exceptions.CPFJaCadastradoException;
import controller.FuncionarioController;
import model.Franquia;
import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;
import model.Produto;
import view.telasVendedor.TelaVendedor;

import java.util.ArrayList;
import java.util.List;

public class VendedorController extends FuncionarioController {
    private Franquia franquia;
    private Vendedor vendedor;
    public VendedorController(Franquia franquia) {
        this.franquia = franquia;
    }
    public VendedorController(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    public List<Object[]> listarVendedorParaTabela() {
        List<Vendedor> vendedores = new ArrayList<>(franquia.getVendedores());

        vendedores.sort((v1, v2) -> Integer.compare(v2.getNumVendas(), v1.getNumVendas()));

        List<Object[]> dados = new ArrayList<>();
        for (Vendedor v : vendedores) {
            Object[] linha = {
                    v.getNome(),
                    v.getNumVendas(),
                    v
            };
            dados.add(linha);
        }
        return dados;
    }
    public List<Object[]> listarVendedorParaRanking(boolean modoOrdenar) {
        List<Vendedor> vendedores = new ArrayList<>(franquia.getVendedores());
        if(modoOrdenar) {
            vendedores.sort((v1, v2) -> Integer.compare(v2.getNumVendas(), v1.getNumVendas()));
        }
        else{
            vendedores.sort((v1, v2) -> Float.compare(v2.getValorTotalVendas(), v1.getValorTotalVendas()));
        }
        List<Object[]> dados = new ArrayList<>();
        for (Vendedor v : vendedores) {
            int numVendas = v.getNumVendas();
            Object[] linha = {
                    v.getNome(),
                    numVendas,
                    "R$" + String.format("%.2f",v.getValorTotalVendas()),
                    numVendas == 0 ? "R%0,00" : "R$" + String.format("%.2f",v.getValorTotalVendas()/numVendas),
                    v
            };
            dados.add(linha);
        }
        return dados;
    }
    public void removerVendedor(Vendedor vendedor) {

        franquia.getGerente().removerVendedor(vendedor);
    }
    public void adicionarVendedor(Vendedor vendedor) throws CPFJaCadastradoException {
        if(CPFJaCadastrado(vendedor)) {
            throw new CPFJaCadastradoException("CPF já cadastrado no sistema!" +
                    "\nCadastro de vendedor cancelado!");
        }
        franquia.getGerente().adicionarVendedor(vendedor);
    }
    private boolean CPFJaCadastrado(Vendedor vendedor) {
        for(Vendedor v : franquia.getVendedores())  {
            if(v.getCPF().equals(vendedor.getCPF())) {
                return true;
            }
        }
        return false;
    }
    public void editarVendedor(Vendedor vendedor,Vendedor vendedorEditado){
        vendedor.setNome(vendedorEditado.getNome());
        vendedor.setEmail(vendedorEditado.getEmail());
        vendedor.setSenha(vendedorEditado.getSenha());
    }
    @Override
    protected void abrirTela() {
        new TelaVendedor(vendedor).setVisible(true);
    }
}
