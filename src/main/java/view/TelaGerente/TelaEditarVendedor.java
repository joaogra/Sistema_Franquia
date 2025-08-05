package view.TelaGerente;

import controller.gerente.VendedorController;
import model.Franquia;

import model.Pessoas.Vendedor;
import validadores.ValidadorCampoVazio;
import validadores.ValidadorEmail;
import validadores.ValidadorEntradas;
import view.TelaCadastro;

import javax.swing.*;

public class TelaEditarVendedor extends TelaCadastro {
    private VendedorController vendedorController;
    private Vendedor vendedor;
    private Franquia franquia;
    public TelaEditarVendedor(JFrame parent, Franquia franquia, Vendedor vendedor) {
        super(parent,"Editar Vendedor");
        getTitulo().setText("Editar Vendedor");
        getCadastrarBtn().setText("Confirmar");
        this.vendedor = vendedor;
        getCpfTxt().setEditable(false);
        getNomeTxt().setText(vendedor.getNome());
        getCpfTxt().setText(vendedor.getCPF());
        getEmailTxt().setText(vendedor.getEmail());
        getSenhaTxt().setText(vendedor.getSenha());
        this.vendedorController = new VendedorController(franquia);
        this.franquia = franquia;
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
            Vendedor vendedorNovo = new Vendedor(nome, cpf, email, senha,franquia);
            vendedorController.editarVendedor(vendedor,vendedorNovo);
            JOptionPane.showMessageDialog(null, "Vendedor cadastrado com sucesso!");
            dispose();
    }
}