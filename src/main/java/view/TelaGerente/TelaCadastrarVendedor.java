package view.TelaGerente;

import controller.gerente.VendedorController;
import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;
import view.TelaCadastro;

import javax.swing.*;

public class TelaCadastrarVendedor extends TelaCadastro {
    VendedorController vendedorController;
    public TelaCadastrarVendedor(JDialog parent, Gerente gerente) {
        super(parent,"Cadastro de Vendedor");
        getTitulo().setText("Cadastro de Vendedor");
        vendedorController = new VendedorController(gerente);
    }

    @Override
    public void cadastrar() {
        String nome = getNomeTxt().getText();
        String cpf = getCpfTxt().getText();
        String email = getEmailTxt().getText();
        if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            return;
        }
        try {
            //senha vazia para o usuario mudar ela no primeiro acesso
            Vendedor vendedorNovo = new Vendedor(nome, cpf, email, "");
            vendedorController.adicionarVendedor(vendedorNovo);
            JOptionPane.showMessageDialog(null, "Vendedor cadastrado com sucesso!");
            dispose();
        }
        catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
