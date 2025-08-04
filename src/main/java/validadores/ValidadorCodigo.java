package validadores;

public class ValidadorCodigo implements ValidadorEntradas{
    @Override
    public boolean validar(String codigo) {
        StringBuilder codigoAux = new StringBuilder();
        boolean primeiroNaoZero = false;
        for (char c : codigo.toCharArray()) {
            if (c != '0' || primeiroNaoZero) {
                primeiroNaoZero = true;
                codigoAux.append(c);
            }
        }
        if (codigoAux.isEmpty()) {
            codigoAux.append("0");
        }
        return codigoAux.toString().matches("\\d+");
    }

    @Override
    public String getMensagemErro() {
        return "O codigo tem que ter 5 digitos \nEx: 00001,10102,99999";
    }
}
