package model;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViaCepService {
    public Endereco buscar(String cep) throws URISyntaxException, IOException, InterruptedException {
        String url = "https://viacep.com.br/ws/"+cep+"/json/";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200){
            return new Gson().fromJson(response.body(), Endereco.class);
        }else{
        throw new IOException("Erro ao consultar o CEP!");
        }
    }
}
