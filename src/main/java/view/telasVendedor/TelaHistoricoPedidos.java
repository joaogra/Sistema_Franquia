package view.telasVendedor;

import model.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class TelaHistoricoPedidos extends JDialog {
    private JLabel titulo;
    private JButton voltarBtn = new JButton("Voltar");

    public TelaHistoricoPedidos(JDialog telaAnterior, Map <String, Pedido> historicoPedidos){
        setTitle("Histórico de Pedidos");
        setModal(true);
        setLocationRelativeTo(telaAnterior);
        setSize(600,500);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        String [] colunas = {"ID","Cliente","Horario","FormaPagamento", "Valor: "};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        add(voltarBtn, BorderLayout.SOUTH);
        voltarBtn.setMargin(new Insets(10,10,10,10));
        voltarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        for(Pedido pedido : historicoPedidos.values()){
            Object [] linha = {
                    pedido.getCod(),
                    pedido.getCliente().getNome(),
                    pedido.getHorario().toString(),
                    pedido.getFormaPagamento(),
                    String.format("R$ " + pedido.getValVenda())
            };
            modelo.addRow(linha);
        }
        JTable table = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

    }

    public static void main(String [] args){

    }
}
