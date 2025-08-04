package validadores;

public interface ValidadorEntradas {
    boolean validar(String entrada);
    String getMensagemErro();
}
