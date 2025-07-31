package view.telasVendedor;

import model.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class TelaHistoricoPedidos extends JDialog {
    private JLabel titulo;
    private JPanel painel;
    private JButton fecharBtn;
    private JTable tabela;


    public TelaHistoricoPedidos(JDialog telaAnterior, Map <String, Pedido> historicoPedidos){
        super(telaAnterior);
        setTitle("Histórico de Pedidos");
        setModal(true);
        setLocationRelativeTo(null);
        setSize(600,500);
        setContentPane(painel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        String [] colunas = {"ID","Cliente","Horario","FormaPagamento", "Valor: "};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);


        fecharBtn.addActionListener(e -> {
           dispose();
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
        tabela.setModel(modelo);
        //add(scrollPane, BorderLayout.CENTER);

    }

    public static void main(String [] args){

    }
}
