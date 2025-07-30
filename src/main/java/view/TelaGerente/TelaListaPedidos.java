package view.TelaGerente;

import controller.gerente.PedidoController;
import controller.gerente.VendedorController;
import model.Pedido;
import model.Pessoas.Gerente;
import model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TelaListaPedidos extends JDialog {
    private JPanel painelListaPedidos;
    private JTable tabelaPedidos;
    private JButton solicitaAlteracaoBtn;
    private JButton fecharBtn;
    private PedidoController pedidoController;
    public TelaListaPedidos(JFrame parent, Gerente gerente) {
        super(parent, "Lista de pedidos", true);
        setContentPane(painelListaPedidos);
        setSize(1280,720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pedidoController = new PedidoController(gerente);

        atualizarTabelaPedidos();
        tabelaPedidos.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent event) {
               if(event.getClickCount() == 2){
                   int linha = tabelaPedidos.getSelectedRow();
                   Pedido pedido = (Pedido) tabelaPedidos.getValueAt(linha, 5);
                   new TelaProdutoDoPedido(TelaListaPedidos.this,gerente,pedido).setVisible(true);
               }
           }
        });
        solicitaAlteracaoBtn.addActionListener(e -> {
            //new TelaSolicitaAlteracao
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

        //deixa a coluna "Produtos" invisivel
        tabelaPedidos.getColumnModel().getColumn(5).setMaxWidth(0);
        tabelaPedidos.getColumnModel().getColumn(5).setMinWidth(0);
        tabelaPedidos.getColumnModel().getColumn(5).setWidth(0);
    }
}
