package com.example.hercilio.appwithfirebase.Objetos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hercilio on 08/02/2018.
 */

public class CompreensaoVerbalObject implements Serializable {
    //Primeira avalicao:
    private Map<String, Boolean> primeiraAvaliacao;
    private int valorTotalPrincipal, valorTotalSecundario;

    //Segunda avaliacao:
    private Map<String, Integer> segundaAvaliacao;
    private int valorTotalLabel3, valorTotalLabel4;

    public CompreensaoVerbalObject(){}

    //Primeira avaliacao:
    public void atualizaPrimeiraAvaliacao(Map buttonsList) {
        primeiraAvaliacao = buttonsList;
    }
    public Map getPrimeiraAvaliacao() {
        return primeiraAvaliacao;
    }
    public int getValorTotalPrincipal() { return valorTotalPrincipal; }
    public int getValorTotalSecundario() { return valorTotalSecundario; }
    public void setValorTotalPrincipal(int valorTotalPrincipal) { this.valorTotalPrincipal = valorTotalPrincipal; }
    public void setValorTotalSecundario(int valorTotalSecundario) { this.valorTotalSecundario = valorTotalSecundario; }

    //Segunda avaliacao:
    public void atualizaSegundaAvaliacao(Map buttonsList) {
        segundaAvaliacao = buttonsList;
    }
    public Map getSegundaAvaliacao() {
        return segundaAvaliacao;
    }
    public int getValorTotalLabel3() { return valorTotalLabel3; }
    public int getValorTotalLabel4() { return valorTotalLabel4; }
    public void setValorTotalLabel3(int valorTotalLabel3) { this.valorTotalLabel3 = valorTotalLabel3; }
    public void setValorTotalLabel4(int valorTotalLabel4) { this.valorTotalLabel4 = valorTotalLabel4; }
}
