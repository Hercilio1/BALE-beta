package com.example.hercilio.appwithfirebase.Objetos;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Hercilio on 07/03/2018.
 */

public class DigitSpanObject implements Serializable {
    //Primeira avalicao:
    private Map<String, Boolean> verificadores;
    private int valorTotalForward, valorTotalBackward;

    public DigitSpanObject(){}

    public Map<String, Boolean> getVerificadores() {
        return verificadores;
    }

    public void setVerificadores(Map<String, Boolean> verificadores) {
        this.verificadores = verificadores;
    }

    public int getValorTotalForward() {
        return valorTotalForward;
    }

    public int getValorTotalBackward() {
        return valorTotalBackward;
    }

    public void setValorTotalForward(int valorTotalForward) {
        this.valorTotalForward = valorTotalForward;
    }

    public void setValorTotalBackward(int valorTotalBackward) {
        this.valorTotalBackward = valorTotalBackward;
    }
}
