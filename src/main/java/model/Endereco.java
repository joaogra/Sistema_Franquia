package model;

public class Endereco {

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String localidade;
    private String estado;

    public Endereco() {

    }

    public String getCep() {return cep;}
    public String getLogradouro() {return logradouro;}
    public String getNumero() {return numero;}
    public String getComplemento() {return complemento;}
    public String getBairro() {return bairro;}
    public String getLocalidade() {return localidade;}
    public String getEstado() {return estado;}

    public void setCep(String cep) {this.cep = cep;}
    public void setBairro(String bairro) {this.bairro = bairro;}
    public void setNumero(String numero) {this.numero = numero;}
    public void setComplemento(String complemento) {this.complemento = complemento;}
    public void setLocalidade(String localidade) {this.localidade = localidade;}
    public void setEstado(String estado) {this.estado = estado;}
    public void setLogradouro(String logradouro){this.logradouro = logradouro;}
}
