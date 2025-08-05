package view.telasVendedor;

import model.Pedido;
import model.Pessoas.Vendedor;
import model.Produto;
import view.TelaProdutoDoPedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Map;

public class TelaHistoricoPedidos extends JDialog {
    private JPanel painel;
    private JButton voltarBtn;
    private JButton consultarBtn;
    private JButton editarBtn;
    private JTable tabela;
    private DefaultTableModel modelo;
    private JScrollPane scroll;

    public TelaHistoricoPedidos(Vendedor vendedor, Map<String, Pedido> historicoPedidos ) {
        setContentPane(painel);
        setTitle("Historico de pedidos");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(650,500));
        setModal(true);
        setLocationRelativeTo(null);
        String [] colunas = {"ID", "CLIENTE", "DATE", "FORMA PAGAMENTO" , "TOTAL", " "};
        modelo = new DefaultTableModel(colunas, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabela = new JTable(modelo);
        //scroll = new JScrollPane(tabela);
        scroll.setViewportView(tabela);
        TableColumn colunaInv = tabela.getColumnModel().getColumn(5);
        colunaInv.setMinWidth(0);
        colunaInv.setMaxWidth(0);
        colunaInv.setWidth(0);
        colunaInv.setPreferredWidth(0);
        for(Pedido pedido : historicoPedidos.values()){
            Object[] linha = {pedido.getCod(),
                    pedido.getCliente().getNome(),
                    pedido.getHorario(),
                    pedido.getFormaPagamento(),
                    pedido.getTotal(),
                    pedido
            };
            modelo.addRow(linha);
        }
        tabela.setModel(modelo);
        tabela.setVisible(true);

        voltarBtn.addActionListener(e -> {
            this.dispose();
        });

        consultarBtn.addActionListener(e -> {
            int linhaSelecionada =  tabela.getSelectedRow();
            if(linhaSelecionada != -1){
                Pedido pedido = (Pedido) modelo.getValueAt(linhaSelecionada, 5);
                new TelaProdutoDoPedido(TelaHistoricoPedidos.this, pedido).setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada");
            }
        });

        editarBtn.addActionListener(e -> {
            int linhaSelecionada =  tabela.getSelectedRow();
            if(linhaSelecionada != -1){
                Pedido pedido = (Pedido) modelo.getValueAt(linhaSelecionada, 5);
                new TelaNovaVenda(TelaHistoricoPedidos.this, vendedor, pedido).setVisible(true);
            }
        });

    }
}
