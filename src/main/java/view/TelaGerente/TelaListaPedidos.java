package view.TelaGerente;

import controller.gerente.PedidoController;
import model.Franquia;
import model.Pedido;
import model.Pessoas.Gerente;
import view.TelaProdutoDoPedido;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaListaPedidos extends JDialog {
    private JPanel painelListaPedidos;
    private JTable tabelaPedidos;
    private JButton solicitaAlteracaoBtn;
    private JButton fecharBtn;
    private JButton viewProdutos;
    private PedidoController pedidoController;
    public TelaListaPedidos(JFrame parent, Franquia franquia) {
        super(parent, "Lista de pedidos", true);
        setContentPane(painelListaPedidos);
        setSize(1280,720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pedidoController = new PedidoController(franquia);

        atualizarTabelaPedidos();
        viewProdutos.addActionListener(event -> {
            int linha = tabelaPedidos.getSelectedRow();
            if(linha != -1) {
                Pedido pedido = (Pedido) tabelaPedidos.getValueAt(linha, 5);
                new TelaProdutoDoPedido(TelaListaPedidos.this, pedido).setVisible(true);
            }
            else {
                JOptionPane.showMessageDialog(null, "Selecione um Pedido!");
            }
        });

        solicitaAlteracaoBtn.addActionListener(e -> {
            new TelaSolicitaAlteracao(parent,franquia).setVisible(true);
        });
        fecharBtn.addActionListener(e -> {
            this.dispose();
        });
    }

    private void atualizarTabelaPedidos() {
        String[] colunas = {"Codigo","Cliente","Horário","Forma de Pagamento","Valor","Pedidos"};
        DefaultTableModel tabela = new DefaultTableModel(colunas, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<Object[]> pedidos = pedidoController.listaPedidosParaTabela();
        for(Object[] linha : pedidos){
            tabela.addRow(linha);
        }

        tabelaPedidos.setModel(tabela);
        tabelaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaPedidos.getTableHeader().setReorderingAllowed(false);
        //deixa a coluna "Produtos" invisivel
        tabelaPedidos.getColumnModel().getColumn(5).setMaxWidth(0);
        tabelaPedidos.getColumnModel().getColumn(5).setMinWidth(0);
        tabelaPedidos.getColumnModel().getColumn(5).setWidth(0);

        //centraliza os itens
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        centralizado.setVerticalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabelaPedidos.getColumnCount(); i++) {
            tabelaPedidos.getColumnModel().getColumn(i).setCellRenderer(centralizado);
        }

        //coloca fontes no cabecalho e nos itens
        tabelaPedidos.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tabelaPedidos.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
    }
}
