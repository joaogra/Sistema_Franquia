package view.TelaGerente;

import controller.gerente.PedidoController;
import model.Franquia;
import model.Pedido;
import model.Pessoas.Vendedor;
import view.TelaProdutoDoPedido;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaSolicitaAlteracao extends JDialog{
    private JPanel painelPrincipal;
    private JButton verificarBtn;
    private JButton voltarBtn;
    private JLabel Pedidos;
    private JLabel info;
    private JTable tabelaPedidos;
    private JButton aceitarBtn;
    private JButton rejeitarBtn;
    private JButton viewProdutosBtn;
    private PedidoController pedidoController;

    public TelaSolicitaAlteracao(JFrame parent, Franquia franquia) {
        super(parent,"Alteração de pedidos",true);
        setContentPane(painelPrincipal);
        setSize(1280,720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pedidoController = new PedidoController(franquia);

        atualizarTabelaPedidos();

        verificarBtn.addActionListener(e -> {
            int pedidoEscolhido = tabelaPedidos.getSelectedRow();
            if (pedidoEscolhido != -1) {
                Pedido pedido =  (Pedido) tabelaPedidos.getValueAt(pedidoEscolhido, 6);
                JOptionPane.showMessageDialog(null, "Motivo: \n"
                        + pedido.getMotivoSolicitacao());
            }
            else{
                JOptionPane.showMessageDialog(null,"Selecione um pedido");
            }
        });
        viewProdutosBtn.addActionListener(event -> {
            int linha = tabelaPedidos.getSelectedRow();
            if(linha != -1) {
                Pedido pedido = (Pedido) tabelaPedidos.getValueAt(linha, 6);
                new TelaProdutoDoPedido(this, pedido).setVisible(true);
            }
            else {
                JOptionPane.showMessageDialog(null, "Selecione um Pedido!");
            }
        });
        aceitarBtn.addActionListener(event -> {
            int linhaEscolhido = tabelaPedidos.getSelectedRow();
            if (linhaEscolhido != -1) {
                Pedido pedido = (Pedido) tabelaPedidos.getValueAt(linhaEscolhido, 6);
                Vendedor vendedor = (Vendedor) tabelaPedidos.getValueAt(linhaEscolhido, 5);
                pedidoController.editaPedido(vendedor,pedido);
                JOptionPane.showMessageDialog(null, "Pedido alterado com sucesso");
                atualizarTabelaPedidos();
            }
            else{
                JOptionPane.showMessageDialog(null,"Selecione um pedido");
            }
        });
        rejeitarBtn.addActionListener(event -> {
           int linhaEscolhido = tabelaPedidos.getSelectedRow();
           if (linhaEscolhido != -1) {
               Pedido pedido = (Pedido) tabelaPedidos.getValueAt(linhaEscolhido, 6);
               Vendedor vendedor = (Vendedor) tabelaPedidos.getValueAt(linhaEscolhido, 5);
               pedidoController.rejeitaAlteracao( vendedor,pedido);
               JOptionPane.showMessageDialog(null, "Pedido rejeitado com sucesso");
               atualizarTabelaPedidos();
           }
           else{
               JOptionPane.showMessageDialog(null,"Selecione um pedido");
           }
        });
        voltarBtn.addActionListener(e -> {
           dispose();
        });
    }
    private void atualizarTabelaPedidos() {
        String[] colunas = {"Codigo","Cliente","Horário","Forma de Pagamento","Valor","Vendedor","Pedidos"};
        DefaultTableModel tabela = new DefaultTableModel(colunas, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<Object[]> pedidos = pedidoController.listaSolicitacaoPedidosParaTabela();
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

        tabelaPedidos.getColumnModel().getColumn(6).setMaxWidth(0);
        tabelaPedidos.getColumnModel().getColumn(6).setMinWidth(0);
        tabelaPedidos.getColumnModel().getColumn(6).setWidth(0);

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
