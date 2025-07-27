package view.TelaGerente;

import model.Pessoas.Gerente;
import model.Produto;

import javax.swing.*;

public class TelaEditarProduto extends TelaProduto {
    public TelaEditarProduto(JDialog parent, Gerente gerente, Produto produto) {
        super(parent,gerente);
        getTitulo().setText("Editar Produto");
        getNomeTxt().setText(produto.getNome());
        getCodigoTxt().setText(produto.getCod());
        getCodigoTxt().setEditable(false);
        getPrecoTxt().setText(Float.toString(produto.getPreco()));
        getQuantidadeTxt().setText(Integer.toString(produto.getQuantidade()));

    }


    @Override
    public void confirmar() {
        String nome = getNomeTxt().getText();
        String codigo = getCodigoTxt().getText();
        float preco = Float.parseFloat(getPrecoTxt().getText());
        int quantidade = Integer.parseInt(getQuantidadeTxt().getText());
        Produto produto = new Produto(preco,quantidade,codigo,nome);
        getGerente().getFranquia().getEstoque().editarProduto(codigo, produto);
        JOptionPane.showMessageDialog(null, "Produto editado com sucesso!");
        dispose();
    }
}
