package view.telasDono;

import Exceptions.FranquiaNomeIgualException;
import controller.DonoController;
import model.Endereco;
import model.Estoque;
import model.Franquia;
import model.Pessoas.Dono;
import model.Pessoas.Gerente;
import model.ViaCepService;
import view.TelaCadastro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;


public class TelaCadastroFranquia extends JDialog {
    private JPanel panelCadastroFranquias;
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
    private DonoController donoController;

    public TelaCadastroFranquia(JFrame parent, Dono dono) {
        super(parent, "Cadastro de Nova Franquia", true);
        setContentPane(panelCadastroFranquias);
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.donoController = new DonoController(dono);

        adicionarButton.addActionListener(e -> {
            String nome =  nomeFTxt.getText();
            String cep = cepTxt.getText();
            String cidade =  cidadeTxt.getText();
            String estado = estadoTxt.getText();
            String rua = ruaTxt.getText();
            String complemento = complementoTxt.getText();
            String bairro = bairroTxt.getText();
            String numero = numeroTxt.getText();
            if(nome.isBlank() || cep.isBlank() || cidade.isBlank()
                    || estado.isBlank() || rua.isBlank() || complemento.isBlank() || bairro.isBlank() ||numero.isBlank()){
                JOptionPane.showMessageDialog(null, "Preencha todos os campos");
                return;
            }
            if (!buscarEnderecoPorCep(cep)) {
                return;
            }
            TelaCadastroGerente telaCadastroGerente = new TelaCadastroGerente(TelaCadastroFranquia.this);
            telaCadastroGerente.setVisible(true);
            Gerente gerenteCadastrado = telaCadastroGerente.getGerenteCadastrado();
            if(gerenteCadastrado == null){
                JOptionPane.showMessageDialog(null, "Obrigátorio associar um gerente a franquia!");
                return;
            }

            String[] informacoes = {cep,cidade,estado,rua,bairro,complemento,numero};

            Endereco CEP = donoController.cadastraCEP(informacoes);
            Franquia franquia = new Franquia(nome,CEP,gerenteCadastrado, List.of(), new Estoque());
            try {
                donoController.cadastrarFranquia(franquia,gerenteCadastrado);
                clean();
                JOptionPane.showMessageDialog(null, "Franquia cadastrada com sucesso!");
            }
            catch(FranquiaNomeIgualException exception){
                JOptionPane.showMessageDialog(null,exception.getMessage());
            }
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
                    JOptionPane.showMessageDialog(null,a.getMessage());
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
    private boolean buscarEnderecoPorCep(String cep) {
        ViaCepService service = new ViaCepService();
        try {
            Endereco endereco = service.buscar(cep);
            if(endereco == null){
                JOptionPane.showMessageDialog(null, "CEP inválido ou não encontrado.");
                return false;
            }
            if (!endereco.getEstado().equals(estadoTxt.getText()) || !endereco.getLocalidade().equals(cidadeTxt.getText())) {
                JOptionPane.showMessageDialog(null,"Cidade e/ou estado não correspondem ao CEP informado.");
                return false;
            }
            return true;
        } catch (IOException | InterruptedException | URISyntaxException e) {
            return false;
        }
    }
}
