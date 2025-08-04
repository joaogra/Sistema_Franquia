package view.telasDono;

import controller.gerente.VendedorController;
import model.Franquia;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaRankingVendedores extends JFrame {
    private JLabel titulo;
    private JLabel nomeFranquia;
    private JTable tabelaVendedores;
    private JLabel infoTabela;
    private JPanel painelRanking;
    private JButton ordenaBtn;
    private JButton voltarBtn;
    private boolean modoOrdenar;
    private VendedorController  vendedorController;
    public TelaRankingVendedores(JFrame parent, Franquia franquia) {
        super("Ranking Vendedores");
        setContentPane(painelRanking);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        modoOrdenar = true;
        vendedorController = new VendedorController(franquia);
        nomeFranquia.setText("Franquia: " + franquia.getNome());
        infoTabela.setText("Vendedores ordenados por NUMERO DE VENDAS");
        atualizarTabela();

        ordenaBtn.addActionListener(e -> {
            String info = modoOrdenar ? "Vendedores ordenados por NUMERO DE VENDAS" : "Vendedores ordenados por VALOR TOTAL VENDIDO";
            infoTabela.setText(info);
            atualizarTabela();
        });
        voltarBtn.addActionListener(e -> {
            dispose();
        });
    }
    public void atualizarTabela() {
        String[] colunas = {"Vendedor", "Quantidade de Vendas", "Valor Total Vendido", "Ticket Médio"};
        DefaultTableModel tabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int linha, int coluna) {
                return false;
            }
        };

        List<Object[]> produtos = vendedorController.listarVendedorParaRanking(modoOrdenar);
        for (Object[] linha : produtos) {
            tabela.addRow(linha);
        }
        modoOrdenar = !modoOrdenar;
        tabelaVendedores.setModel(tabela);
        tabelaVendedores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaVendedores.getTableHeader().setReorderingAllowed(false);

        //centraliza os itens
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        centralizado.setVerticalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabelaVendedores.getColumnCount(); i++) {
            tabelaVendedores.getColumnModel().getColumn(i).setCellRenderer(centralizado);
        }

        //coloca fontes no cabecalho e nos itens
        tabelaVendedores.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tabelaVendedores.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
    }
}
