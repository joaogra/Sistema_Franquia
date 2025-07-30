package view.TelaGerente;

import controller.gerente.ProdutoController;
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
    private JLabel infoTabela;
    private ProdutoController produtoController;

    public TelaVisualizaEstoque(JFrame parent, Gerente gerente) {
        super(parent,"Estoque da Franquia", true);
        setContentPane(painelEstoque);
        setSize(800,600);
        setLocationRelativeTo(parent);
        this.produtoController = new ProdutoController(gerente);

        atualizarTabela();

        cadastrarProdutoBtn.addActionListener(event ->{
            new TelaCadastrarProduto(TelaVisualizaEstoque.this,gerente).setVisible(true);
            atualizarTabela();
        });

        removeBtn.addActionListener(event ->{
            int linhaEscolhida = tabelaEstoque.getSelectedRow();
            if (linhaEscolhida != -1) {
                String codigo = (String) tabelaEstoque.getValueAt(linhaEscolhida, 0);
                String nome = (String) tabelaEstoque.getValueAt(linhaEscolhida, 1);
                if(produtoController.removerProduto(codigo)){//remove do estoque
                    atualizarTabela();
                    JOptionPane.showMessageDialog(null,"O produto " + nome + " foi removido com sucesso!");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Não foi possível remover o produto!");
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Selecione um produto para remover!");
            }
        });
        editaProdutoBtn.addActionListener(event ->{
            int linhaEscolhida = tabelaEstoque.getSelectedRow();
            if(linhaEscolhida != -1) {
                String cod = tabelaEstoque.getValueAt(linhaEscolhida, 0).toString();
                Produto produto = produtoController.buscarProduto(cod);
                new TelaEditarProduto(TelaVisualizaEstoque.this, gerente, produto).setVisible(true);
                atualizarTabela();
            }
            else{
                JOptionPane.showMessageDialog(null,"Selecione um produto para editar!");
            }
        });
        fecharBtn.addActionListener(event -> {
            dispose();
        });
    }
    private void atualizarTabela() {
        String[] colunas = {"Código", "Nome", "Preço", "Quantidade","Status"};
        DefaultTableModel tabela = new DefaultTableModel(colunas,0){
            @Override public boolean isCellEditable(int linha, int coluna) {
                return false;
            }
        };

        List<Object[]> produtos = produtoController.listarProdutosParaTabela();
        for(Object[] linha : produtos){
            tabela.addRow(linha);
        }
        tabelaEstoque.setModel(tabela);//aplica os dados a tabela
        tabelaEstoque.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
}