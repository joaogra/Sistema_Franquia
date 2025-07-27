package view.TelaGerente;

import model.Pessoas.Gerente;
import model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TelaVisualizaEstoque extends JDialog{
    private JPanel painelEstoque;
    private JTable tabelaEstoque;
    private JButton fecharBtn;
    private JLabel titulo;
    private JButton cadastrarProdutoBtn;
    private JButton removeBtn;
    private JButton editaProdutoBtn;

    public TelaVisualizaEstoque(JFrame parent, Gerente gerente) {
        super(parent,"Estoque da Franquia", true);
        setContentPane(painelEstoque);
        setSize(500,400);
        setLocationRelativeTo(parent);
        DefaultTableModel tabela = new DefaultTableModel();
        atualizarTabela(gerente,tabela);

        cadastrarProdutoBtn.addActionListener(event ->{
            new TelaCadastrarProduto(this,gerente).setVisible(true);
            atualizarTabela(gerente,tabela);
        });

        removeBtn.addActionListener(event ->{
            int linhaEscolhida = tabelaEstoque.getSelectedRow();
            if (linhaEscolhida != -1) {
                String codigo = (String) tabelaEstoque.getValueAt(linhaEscolhida, 0);
                String nome = (String) tabelaEstoque.getValueAt(linhaEscolhida, 1);
                gerente.getFranquia().getEstoque().removerProduto(codigo);//remove do estoque
                atualizarTabela(gerente,tabela);
                JOptionPane.showMessageDialog(null,"O produto " + nome + " foi removido com sucesso!");
            }
            else{
                JOptionPane.showMessageDialog(null,"Selecione um produto para remover!");
            }
        });
        editaProdutoBtn.addActionListener(event ->{
            int linhaEscolhida = tabelaEstoque.getSelectedRow();
            String cod = tabelaEstoque.getValueAt(linhaEscolhida,0).toString();
            Produto produto = gerente.getFranquia().getEstoque().buscaProduto(cod);
            new TelaEditarProduto(this,gerente,produto).setVisible(true);
            atualizarTabela(gerente,tabela);
        });
        fecharBtn.addActionListener(event -> {
            dispose();
        });
    }
    private void atualizarTabela(Gerente gerente,DefaultTableModel tabela) {
        String[] colunas = {"Código", "Nome", "Preço", "Quantidade","Status"};
        tabela = new DefaultTableModel(colunas,0){
            @Override public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<Produto> produtos = gerente.getFranquia().getEstoque().getProdutos();
        for(Produto produto : produtos){
            String status = produto.getQuantidade() <= 5 ? "Em Falta" : "OK";
            Object[] linha = {
                    produto.getCod(),
                    produto.getNome(),
                    String.format("R$ %.2f",produto.getPreco()),
                    produto.getQuantidade(),
                    status
            };
            tabela.addRow(linha);
        }
        tabelaEstoque.setModel(tabela);//aplica os dados a tabela
        tabelaEstoque.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
}
//ver quais produtos estao com qtd baixa (nessa tela msm) OK
//CadastrarProdutos OK
//editar produtos OK
//remover produtos OK
