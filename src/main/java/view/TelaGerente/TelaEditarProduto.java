package view.TelaGerente;

import controller.gerente.ProdutoController;
import model.Pessoas.Gerente;
import model.Produto;

import javax.swing.*;

public class TelaEditarProduto extends TelaProduto {
    private ProdutoController produtoController;
    public TelaEditarProduto(JDialog parent, Gerente gerente, Produto produto) {
        super(parent,"Editar Produto",gerente);
        getTitulo().setText("Editar Produto");
        getNomeTxt().setText(produto.getNome());
        getCodigoTxt().setText(produto.getCod());
        getCodigoTxt().setEditable(false);
        getPrecoTxt().setText(Float.toString(produto.getPreco()));
        getQuantidadeTxt().setText(Integer.toString(produto.getQuantidade()));
        this.produtoController = new ProdutoController(gerente);
    }


    @Override
    public void confirmar() {
        try {
            String nome = getNomeTxt().getText().trim();
            String codigo = getCodigoTxt().getText().trim();
            String precoStr = getPrecoTxt().getText().trim();
            String qtdStr = getQuantidadeTxt().getText().trim();

            if (nome.isEmpty() || codigo.isEmpty() || precoStr.isEmpty() || qtdStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                return;
            }

            float preco = Float.parseFloat(precoStr);
            int quantidade = Integer.parseInt(qtdStr);
            if(preco <= 0 || quantidade < 0) {
                JOptionPane.showMessageDialog(null,"Preço e Quantidade devem ser números positivos!");
                return;
            }
            Produto novoProduto = new Produto(preco, quantidade, codigo, nome);
            produtoController.editarProduto(codigo,novoProduto);
            getGerente().getFranquia().getEstoque().editarProduto(codigo, novoProduto);
            JOptionPane.showMessageDialog(null, "Produto editado com sucesso!");
            dispose();
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Preço e Quantidade devem ser números válidos!");
        }
    }
}
