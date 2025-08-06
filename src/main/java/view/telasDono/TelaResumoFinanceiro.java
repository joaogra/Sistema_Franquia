package view.telasDono;

import model.Franquia;

import javax.swing.*;

public class TelaResumoFinanceiro extends JDialog{
    private JPanel painelResumo;
    private JLabel gastoMedioClienteLAbel;
    private JLabel totalPedidosLabel;
    private JLabel ticketMedioLabel;
    private JTextField totalPedidosTxt;
    private JTextField ticketMedioTxt;
    private JTextField gastoMedioClienteTxt;
    private JButton fechatBtn;
    private JLabel franquiaLabel;
    private JTextField numTotalCleinteTxt;
    private JLabel numClientesLabel;


    public TelaResumoFinanceiro(JFrame parent, Franquia franquia) {
        super(parent,"Resumo financeiro",false);
        setContentPane(painelResumo);
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        franquiaLabel.setText(franquia.getNome());


        totalPedidosTxt.setEditable(false);
        totalPedidosTxt.setText(String.valueOf(franquia.getTotalPedidos()));

        ticketMedioTxt.setEditable(false);
        ticketMedioTxt.setText("R$" + String.format("%.2f",franquia.getTicketMedio()));

        gastoMedioClienteTxt.setEditable(false);
        gastoMedioClienteTxt.setText("R$" + String.format("%.2f",franquia.getTicketMedioCliente()));

        numTotalCleinteTxt.setEditable(false);
        numTotalCleinteTxt.setText(String.valueOf(franquia.getNumTotalClientes()));
        fechatBtn.addActionListener(e -> {
           dispose();
        });
    }
}
