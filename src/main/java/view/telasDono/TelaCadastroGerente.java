package view.telasDono;

import model.Franquia;
import model.Pessoas.Gerente;
import validadores.ValidadorCampoVazio;
import validadores.ValidadorEmail;
import validadores.ValidadorEntradas;
import view.TelaCadastro;

import javax.swing.*;

public class TelaCadastroGerente extends TelaCadastro {
    private Gerente gerenteCadastrado;
    private Franquia franquia;
    public TelaCadastroGerente(JFrame parent) {
        super(parent,"Cadastro de Gerente");
        getTitulo().setText("Cadastro de Gerente");
        this.gerenteCadastrado = null;
        this.franquia = franquia;
    }

    @Override
    public void cadastrar() {
        ValidadorEntradas validadorCampoVazio = new ValidadorCampoVazio();
        String nome = getNomeTxt().getText().trim();
        String cpf = getCpfTxt().getText().trim().replaceAll("\\D","");
        String email = getEmailTxt().getText().trim();
        String senha = getSenhaTxt().getText().trim();
        if (validadorCampoVazio.validar(nome) || validadorCampoVazio.validar(cpf)
                || validadorCampoVazio.validar(email) || validadorCampoVazio.validar(senha)) {
            JOptionPane.showMessageDialog(null, validadorCampoVazio.getMensagemErro());
            return;
        }
        ValidadorEntradas validadorEmail =  new ValidadorEmail();
        if(!validadorEmail.validar(email)){
           JOptionPane.showMessageDialog(null, validadorEmail.getMensagemErro());
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
