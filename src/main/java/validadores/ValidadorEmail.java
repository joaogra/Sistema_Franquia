package validadores;

public class ValidadorEmail implements ValidadorEntradas {
    @Override
    public boolean validar(String email) {
            String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
            return email != null && email.matches(regex);
    }

    @Override
    public String getMensagemErro() {
        return "Email Inválido!";
    }
}
