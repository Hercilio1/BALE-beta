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
    private int av1ValorTotalPrincipal, av1ValorTotalSecundario;

    //Segunda avaliacao:
    private Map<String, Integer> segundaAvaliacao;
    private int av2ValorTotalLabel3, av2ValorTotalLabel4;

    //Terceira avaliacao:
    private Map<String, Integer> terceiraAvaliacao;
    private int av3ValorTotalLabel2, av3ValorTotalLabel3;

    //Observavoes:
    private String observacoes;

    public CompreensaoVerbalObject(){}

    //Primeira avaliacao:
    public void atualizaPrimeiraAvaliacao(Map buttonsList) {
        primeiraAvaliacao = buttonsList;
    }
    public Map getPrimeiraAvaliacao() {
        return primeiraAvaliacao;
    }
    public int getAv1ValorTotalPrincipal() { return av1ValorTotalPrincipal; }
    public int getAv1ValorTotalSecundario() { return av1ValorTotalSecundario; }
    public void setAv1ValorTotalPrincipal(int valorTotalPrincipal) { this.av1ValorTotalPrincipal = valorTotalPrincipal; }
    public void setAv1ValorTotalSecundario(int valorTotalSecundario) { this.av1ValorTotalSecundario = valorTotalSecundario; }

    //Segunda avaliacao:
    public void atualizaSegundaAvaliacao(Map buttonsList) {
        segundaAvaliacao = buttonsList;
    }
    public Map getSegundaAvaliacao() {
        return segundaAvaliacao;
    }
    public int getAv2ValorTotalLabel3() { return av2ValorTotalLabel3; }
    public int getAv2ValorTotalLabel4() { return av2ValorTotalLabel4; }
    public void setAv2ValorTotalLabel3(int av2ValorTotalLabel3) { this.av2ValorTotalLabel3 = av2ValorTotalLabel3; }
    public void setAv2ValorTotalLabel4(int av2ValorTotalLabel4) { this.av2ValorTotalLabel4 = av2ValorTotalLabel4; }

    //Terceira avaliacao:
    public void atualizaTerceiraAvaliacao(Map buttonsList) {
        terceiraAvaliacao = buttonsList;
    }
    public Map getTerceiraAvaliacao() {
        return terceiraAvaliacao;
    }
    public int getAv3ValorTotalLabel2() { return av3ValorTotalLabel2; }
    public int getAv3ValorTotalLabel3() { return av3ValorTotalLabel3; }
    public void setAv3ValorTotalLabel2(int av3ValorTotalLabel2) { this.av3ValorTotalLabel2 = av3ValorTotalLabel2; }
    public void setAv3ValorTotalLabel3(int av3ValorTotalLabel3) { this.av3ValorTotalLabel3 = av3ValorTotalLabel3; }

    //Observavoes:
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}
