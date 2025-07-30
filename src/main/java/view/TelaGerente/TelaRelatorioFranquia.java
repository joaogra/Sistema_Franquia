package view.TelaGerente;

import model.Franquia;
import model.Pessoas.Gerente;

import javax.swing.*;

public class TelaRelatorioFranquia extends JDialog {
    private JPanel painelRelatorio;
    private JLabel titulo;
    private JLabel totalVendasTxt;
    private JLabel lucroTxt;
    private JLabel mediaLucro;
    private JTable table1;
    private JButton fecharBtn;
    private JLabel infoTabela;

    public TelaRelatorioFranquia(JFrame parent, Gerente gerente) {
        super(parent, "Relatório da Franquia", true);
        setContentPane(painelRelatorio);
        setSize(1280,720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        totalVendasTxt.setText("Total de vendas realizados: ");
        lucroTxt.setText("Soma total das vendas: ");
        mediaLucro.setText("Media de valor por venda: ");

        infoTabela.setText("Clientes mais recorrentes(Ordenados pela quantidade de compras efetuadas)");
    }
}
