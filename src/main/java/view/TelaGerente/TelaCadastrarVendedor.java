package view.TelaGerente;

import Exceptions.CPFJaCadastradoException;
import controller.gerente.VendedorController;
import model.Franquia;
import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;
import view.TelaCadastro;

import javax.swing.*;

public class TelaCadastrarVendedor extends TelaCadastro {
    VendedorController vendedorController;
    public TelaCadastrarVendedor(JFrame parent, Franquia franquia) {
        super(parent,"Cadastro de Vendedor");
        getTitulo().setText("Cadastro de Vendedor");
        vendedorController = new VendedorController(franquia);
    }

    @Override
    public void cadastrar() {
        String nome = getNomeTxt().getText();
        String cpf = getCpfTxt().getText();
        String email = getEmailTxt().getText();
        String senha = getSenhaTxt().getText();
        if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            return;
        }
        try {
            Vendedor vendedorNovo = new Vendedor(nome, cpf, email, senha);
            vendedorController.adicionarVendedor(vendedorNovo);
            JOptionPane.showMessageDialog(null, "Vendedor cadastrado com sucesso!");
            dispose();
        }
        catch (IllegalArgumentException | CPFJaCadastradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
