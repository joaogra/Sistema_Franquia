package view.TelaGerente;

import com.google.gson.Gson;
import model.Pessoas.Gerente;
import model.Produto;

import javax.swing.*;

public class TelaCadastrarProduto extends TelaProduto {
    public TelaCadastrarProduto(JDialog parent,  Gerente gerente) {
        super(parent,gerente);
        getTitulo().setText("Cadastrar Produto");

    }
    @Override
    public void confirmar() {
            String nome = getNomeTxt().getText();
            String codigo = getCodigoTxt().getText();
            float preco = Float.parseFloat(getPrecoTxt().getText());
            int quantidade = Integer.parseInt(getQuantidadeTxt().getText());

            Produto produto = new Produto(preco,quantidade,codigo,nome);
            getGerente().getFranquia().getEstoque().adicionaProduto(produto);
            /*Gson gson = new Gson();
            String json = gson.toJson(produto);
            try{
                    FileWriter writer = new FileWriter("data/produtos" , true);
                    writer.write(json + System.lineSeparator());
                    writer.close();
            }
            catch (IOException ex) {
                    throw new RuntimeException(ex);
            }*/
            JOptionPane.showMessageDialog(this,"Cadastro realizado com sucesso");
            clean();
    }
}
