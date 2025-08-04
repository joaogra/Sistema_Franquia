package validadores;



public class ValidadorCampoVazio implements ValidadorEntradas {
    @Override
    public boolean validar(String entrada) {
        return entrada.isEmpty();
    }

    @Override
    public String getMensagemErro() {
        return "Preencha todos os campos!";
    }
}
