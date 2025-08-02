package view;

import controller.gerente.PedidoController;
import model.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaProdutoDoPedido extends JDialog {
    private JPanel painelProdutoPedido;
    private JTable tabelaProdutos;
    private JLabel titulo;
    private JButton fecharBtn;
    private PedidoController pedidoController;
    public TelaProdutoDoPedido(JDialog parent, Pedido pedido){
        super(parent,"Produtos",true);
        setContentPane(painelProdutoPedido);
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        pedidoController = new PedidoController();

        String[] colunas = {"Codigo","Nome","Quantidade","Preço"};
        DefaultTableModel tabela = new DefaultTableModel(colunas,0){
            @Override public boolean isCellEditable(int linha, int coluna) {
                return false;
            }
        };
        List<Object[]> produtos = pedidoController.listaProdutosUnicoPedido(pedido);
        for(Object[] produto : produtos){
            tabela.addRow(produto);
        }
        tabelaProdutos.setModel(tabela);
        tabelaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaProdutos.getTableHeader().setReorderingAllowed(false);
        //centraliza os itens
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        centralizado.setVerticalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabelaProdutos.getColumnCount(); i++) {
            tabelaProdutos.getColumnModel().getColumn(i).setCellRenderer(centralizado);
        }

        //coloca fontes no cabecalho e nos itens
        tabelaProdutos.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tabelaProdutos.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

        fecharBtn.addActionListener(e -> {
           this.dispose();
        });
    }
}
