package view.telasDono;

import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;
import view.TelaCadastro;

import javax.swing.*;

public class TelaCadastroGerente extends TelaCadastro {
    Gerente gerenteCadastrado;
    public TelaCadastroGerente(JFrame parent) {
        super(parent,"Cadastro de Gerente");
        getTitulo().setText("Cadastro de Gerente");
        this.gerenteCadastrado = null;
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
            gerenteCadastrado = new Gerente(nome, cpf, email, senha);
            dispose();
        }
        catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    protected Gerente getGerenteCadastrado() {
        return gerenteCadastrado;
    }
}
