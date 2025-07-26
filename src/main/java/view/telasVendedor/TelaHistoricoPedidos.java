package view.telasVendedor;

import model.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

public class TelaHistoricoPedidos extends JDialog {
    private JLabel titulo;
    private JTable table1;

    public TelaHistoricoPedidos(Map<String, Pedido> historicoPedidos){
        setTitle("Histórico de Pedidos");
        setSize(600,300);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        String [] colunas = {"ID","Cliente","Horario","FormaPagamento", "Valor: "};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

        for(Pedido pedido : historicoPedidos.values()){
            Object [] linha = {
                    pedido.getCod(),
                    pedido.getCliente(),
                    pedido.getHorario(),
                    pedido.getFormaPagamento(),
                    String.format("R$ %2f",pedido.getValVenda())
            };
            modelo.addRow(linha);
        }
        JTable table = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(table1);
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String [] args){

        SwingUtilities.invokeLater(()->{
            new TelaHistoricoPedidos(null);
        });
    }
}
