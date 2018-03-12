package com.example.hercilio.appwithfirebase.Objetos;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Hercilio on 11/03/2018.
 */

public class AssociacaoSemanticaObject implements Serializable {
    //Primeira avalicao:
    private Map<String, Integer> verificadores;
    private int valorTotal;

    public AssociacaoSemanticaObject(){}

    public Map<String, Integer> getVerificadores() {
        return verificadores;
    }

    public void setVerificadores(Map<String, Integer> verificadores) {
        this.verificadores = verificadores;
    }

    public int getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }
}
