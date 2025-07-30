package view.telasVendedor;

import model.Pedido;
import model.Pessoas.Vendedor;
import view.TelaProdutoDoPedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class TelaHistoricoPedidos extends JDialog {
    private JLabel titulo;
    private JButton voltarBtn = new JButton("Voltar");

    public TelaHistoricoPedidos(JDialog telaAnterior, Map <String, Pedido> historicoPedidos, Vendedor vendedor){
        super(telaAnterior);
        setTitle("Histórico de Pedidos");
        setModal(true);
        setLocationRelativeTo(null);
        setSize(600,500);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        String [] colunas = {"ID","Cliente","Horario","FormaPagamento", "Valor: ", ""};
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
                    String.format("R$ " + pedido.getValVenda()),
                    pedido
            };
            modelo.addRow(linha);
        }
        JTable table = new JTable(modelo){
        @Override
        public boolean isCellEditable(int row, int column) {return false;}

    };
        TableColumn colunaInv = table.getColumnModel().getColumn(5);
        colunaInv.setMinWidth(0);
        colunaInv.setMaxWidth(0);
        colunaInv.setWidth(0);
        colunaInv.setPreferredWidth(0);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        table.addMouseListener(new  MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount()==2){
                    int linhaSelecionada = table.rowAtPoint(e.getPoint());
                    if(linhaSelecionada!=-1){
                        new TelaNovaVenda(telaAnterior,vendedor,(Pedido)table.getValueAt(linhaSelecionada,5)).setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null, "Selecione uma linha");
                    }
                }
            }
        });

    }

    public static void main(String [] args){

    }
}
