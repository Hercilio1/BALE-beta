package com.example.hercilio.appwithfirebase.Objetos;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Hercilio on 02/03/2018.
 */

public class NarrativaObject implements Serializable {
    //Primeira avalicao:
    private Map<String, Integer> verificadores;
    private int valorTotalEstrutura, valorTotalDesempLing1, valorTotalDesempLing2;
    private String observacoes;

    public NarrativaObject(){}

    public Map<String, Integer> getVerificadores() {
        return verificadores;
    }

    public int getValorTotalEstrutura() {
        return valorTotalEstrutura;
    }

    public int getValorTotalDesempLing1() {
        return valorTotalDesempLing1;
    }

    public int getValorTotalDesempLing2() {
        return valorTotalDesempLing2;
    }

    public void setVerificadores(Map<String, Integer> verificadores) {
        this.verificadores = verificadores;
    }

    public void setValorTotalEstrutura(int valorTotalEstrutura) {
        this.valorTotalEstrutura = valorTotalEstrutura;
    }

    public void setValorTotalDesempLing1(int valorTotalDesempLing1) {
        this.valorTotalDesempLing1 = valorTotalDesempLing1;
    }

    public void setValorTotalDesempLing2(int valorTotalDesempLing2) {
        this.valorTotalDesempLing2 = valorTotalDesempLing2;
    }

    //Observacoes:
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}
