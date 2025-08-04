package view.TelaGerente;

import Exceptions.CPFJaCadastradoException;
import controller.gerente.VendedorController;
import model.Franquia;
import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;
import validadores.ValidadorCampoVazio;
import validadores.ValidadorEmail;
import validadores.ValidadorEntradas;
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
        ValidadorEntradas validadorCampoVazio = new ValidadorCampoVazio();
        String nome = getNomeTxt().getText().trim();
        String cpf = getCpfTxt().getText().trim().replaceAll("\\D", "");
        String email = getEmailTxt().getText().trim();
        String senha = getSenhaTxt().getText().trim();

        if (validadorCampoVazio.validar(nome) || validadorCampoVazio.validar(cpf)
                || validadorCampoVazio.validar(email) || validadorCampoVazio.validar(senha)) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            return;
        }
        ValidadorEntradas validadorEmail = new ValidadorEmail();
        if(!validadorEmail.validar(email)) {
            JOptionPane.showMessageDialog(null,validadorEmail.getMensagemErro());
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
