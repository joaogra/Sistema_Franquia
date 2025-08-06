package model.Pessoas;

import Exceptions.CodigoPedidoJaCadastradoException;
import Exceptions.QuantidadeProdutoInsuficienteException;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VendedorTest {

    private Vendedor vendedor;
    private Pedido pedido1;
    private Pedido pedido2;
    private Pedido pedido3;
    private Pedido pedido4;


    @BeforeEach
    void inicializa(){
        Dono dono = new Dono("adrian","14518498690","a@emial.com","admin",new ArrayList<>());
        Franquia f1 = new Franquia("Franquia 1", new Endereco(), List.of(), new Estoque(), dono);
        vendedor = new Vendedor("João", "14945740712", "a@e.com", "1234", f1);
        dono.getListaFranquias().add(f1);
        f1.getVendedores().add(vendedor);
        Produto p1 = new Produto( 10.0f, 51,"001", "Produto A");
        Produto p2 = new Produto( 20.5f, 33,"002","Produto B");
        f1.getEstoque().getEstoque().put(p1.getCod(),p1);
        f1.getEstoque().getEstoque().put(p2.getCod(),p2);
        pedido1 = new Pedido("1234",new Cliente("João","12345678909"),new Date(),"PIX", 0, Map.of(), 500.0);
        pedido2 = new Pedido("1234",new Cliente("Cleiton","08840106006"),new Date(),"Dinheiro", 0, Map.of(), 500.0);
        pedido3 = new Pedido("5432",new Cliente("Cleiton","08840106006"),new Date(),"Dinheiro", 0, Map.of(), 700.0);
        pedido4 = new Pedido("5555", new Cliente("Jorge", "93653438012"),new Date(),"Débito",0,Map.of(p2.getCod(), 35), 150.00);
        vendedor.adicionaPedido(pedido1);

    }
    @Test
    //Adiciona um pedido com um código único e novo

    protected void adicionaNovoPedido(){
        assertTrue(vendedor.adicionaPedido(pedido3));
    }


    @Test

    //Lança execption se ja existe pedido com aquele codigo
    protected void lancaExceptionMesmoCodigo() {
        assertThrows(CodigoPedidoJaCadastradoException.class, ()->{
            vendedor.adicionaPedido(pedido2);
        });
    }

    //Tenta adicionar um pedido com 35 unidadades do prod2, sendo que só tem 33 no estoque
    @Test
    protected void lancaExceptionQtdProdInsuficiente(){
        assertThrows(QuantidadeProdutoInsuficienteException.class, ()->{
            vendedor.adicionaPedido(pedido4);
        });
    }

}