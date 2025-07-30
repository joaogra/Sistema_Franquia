package view.telasDono;

import model.Endereco;
import model.Pessoas.Dono;
import model.ViaCepService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;


public class TelaCadastroFranquia extends JDialog {
    private JPanel panel1;
    private JButton cancelarButton;
    private JButton adicionarButton;
    private JButton voltarButton;
    private JTextField nomeFTxt;
    private JTextField ruaTxt;
    private JTextField bairroTxt;
    private JTextField numeroTxt;
    private JTextField complementoTxt;
    private JTextField cidadeTxt;
    private JTextField estadoTxt;
    private JTextField cepTxt;
    private JButton buscarBtn;


    public TelaCadastroFranquia(JFrame parent, Dono dono) {
        super(parent, "Cadastro de Nova Franquia", true);
        setContentPane(panel1);
        setSize(800,600);
        setLocationRelativeTo(null);

        adicionarButton.addActionListener(e -> {
           ///
        });
        buscarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cep = cepTxt.getText();
                ViaCepService service = new ViaCepService();

                try{
                    Endereco endereco = service.buscar(cep);
                    ruaTxt.setText(endereco.getLogradouro());
                    bairroTxt.setText(endereco.getBairro());
                    cidadeTxt.setText(endereco.getLocalidade());
                    estadoTxt.setText(endereco.getEstado());

                }
                catch (IOException | InterruptedException  | URISyntaxException a){
                    System.out.println("ERRO" + a.getMessage());
                }
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clean();
            }
        });
        voltarButton.addActionListener(e -> {
           this.dispose();
        });
    }
    public void clean(){
        bairroTxt.setText("");
        numeroTxt.setText("");
        complementoTxt.setText("");
        cidadeTxt.setText("");
        estadoTxt.setText("");
        cepTxt.setText("");
        ruaTxt.setText("");
        nomeFTxt.setText("");
    }
}
