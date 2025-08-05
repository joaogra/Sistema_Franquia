package Exceptions;

public class UsuarioNaoCadastradoException extends RuntimeException {
    public UsuarioNaoCadastradoException(String message) {
        super(message);
    }
}
