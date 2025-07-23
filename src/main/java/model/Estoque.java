package model;

import java.util.HashMap;
import java.util.Map;

public class Estoque {
    Map<String, Produto> estoque;

    public Estoque(){
        estoque = new HashMap<String, Produto>();
    }
}
