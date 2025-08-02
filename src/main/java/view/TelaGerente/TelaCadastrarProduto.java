package view.TelaGerente;

import controller.gerente.ProdutoController;
import model.Pessoas.Gerente;
import model.Produto;

import javax.swing.*;

public class TelaCadastrarProduto extends TelaProduto {
    private ProdutoController produtoController;
    public TelaCadastrarProduto(JDialog parent, Gerente gerente) {
        super(parent,"Cadastrar Produto",gerente);
        getTitulo().setText("Cadastrar Produto");
        this.produtoController = new ProdutoController(gerente);
    }
    @Override
    public void confirmar() {
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

            Produto produto = new Produto(preco,quantidade,codigo,nome);
            if(produtoController.adicionarProduto(produto)) {
                JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
                clean();
            }
            else {
                JOptionPane.showMessageDialog(this, "Não é possivel cadastrar o produtos com códigos repetidos!");
            }
    }
}
//tratar excessao quando adicionar produto