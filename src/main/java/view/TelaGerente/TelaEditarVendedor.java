package view.TelaGerente;

import controller.gerente.ProdutoController;
import controller.gerente.VendedorController;
import model.Franquia;
import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;
import model.Produto;
import view.TelaCadastro;

import javax.swing.*;

public class TelaEditarVendedor extends TelaCadastro {
    private VendedorController vendedorController;
    private String senha;
    public TelaEditarVendedor(JFrame parent, Franquia franquia, Vendedor vendedor) {
        super(parent,"Editar Vendedor");
        getTitulo().setText("Editar Vendedor");
        getCadastrarBtn().setText("Confirmar");
        getNomeTxt().setText(vendedor.getNome());
        getCpfTxt().setText(vendedor.getCPF());
        getEmailTxt().setText(vendedor.getEmail());
        getSenhaTxt().setText(vendedor.getSenha());
        this.vendedorController = new VendedorController(franquia);
    }

    @Override
    public void cadastrar() {
        String nome = getNomeTxt().getText();
        String cpf = getCpfTxt().getText();
        String email = getEmailTxt().getText();
        String senha = getSenhaTxt().getText();
        if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            return;
        }
        try {
            Vendedor vendedorNovo = new Vendedor(nome, cpf, email, senha);
            vendedorController.editarVendedor(vendedorNovo);
            JOptionPane.showMessageDialog(null, "Vendedor cadastrado com sucesso!");
            dispose();
        }
        catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
//alterar para poder editar a senha