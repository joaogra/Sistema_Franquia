package view.telasVendedor;

import controller.gerente.FranquiaController;
import controller.vendedor.VendedorOperaController;
import model.Pedido;
import model.Pessoas.Vendedor;

import javax.swing.*;
import java.awt.*;

public class TelaMotivo extends  JDialog {
    private JLabel titulo;
    private JTextArea textArea;
    private JButton confirmarBtn;
    private JButton voltarBtn;
    private JButton limparBtn;

    private VendedorOperaController vendedorC;
    private Pedido pedido;

    public TelaMotivo(VendedorOperaController vendedor, Pedido pedido){

        setTitle("Motivo para alteração");
        setContentPane(TelaMotivo.this);
        setMinimumSize(new Dimension(650,500));
        setModal(true);
        setLocationRelativeTo(null);
        setVisible(true);
        this.pedido=pedido;
        this.vendedorC=vendedor;


        voltarBtn.addActionListener(e -> {
            dispose();
        } );

        limparBtn.addActionListener(e -> {
            textArea.setText("");
        });

        confirmarBtn.addActionListener(e -> {
            setMotivo();
            FranquiaController f = new FranquiaController(this.vendedorC);
            f.adicionaSolitacaoPedido(pedido);
        });
    }

    public void setMotivo(){
        String motivo = textArea.toString();
        if(motivo.isBlank()){
            JOptionPane.showMessageDialog(null, "Informe um motivo");
        }else {
            pedido.setMotivo(motivo);
        }
    }

}
