package view.TelaGerente;

import controller.gerente.ProdutoController;
import controller.gerente.VendedorController;
import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;
import model.Produto;
import view.TelaCadastro;

import javax.swing.*;

public class TelaEditarVendedor extends TelaCadastro {
    private VendedorController vendedorController;
    private String senha;
    public TelaEditarVendedor(JDialog parent, Gerente gerente, Vendedor vendedor) {
        super(parent,"Editar Vendedor");
        getTitulo().setText("Editar Vendedor");
        getCadastrarBtn().setText("Confirmar");
        getNomeTxt().setText(vendedor.getNome());
        getCpfTxt().setText(vendedor.getCPF());
        getEmailTxt().setText(vendedor.getEmail());
        senha = getSenhaTxt().getText();
        getSenhaTxt().setVisible(false);
        getSenha().setVisible(false);
        this.vendedorController = new VendedorController(gerente);
    }

    @Override
    public void cadastrar() {
        String nome = getNomeTxt().getText();
        String cpf = getCpfTxt().getText();
        String email = getEmailTxt().getText();

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
