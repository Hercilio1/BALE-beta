package com.example.hercilio.appwithfirebase.Objetos;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Hercilio on 06/03/2018.
 */

public class NomeacaoObject implements Serializable {
    //Primeira avalicao:
    private Map<String, String> verificadores;
    private int porcentagem;

    public NomeacaoObject(){}

    public Map<String, String> getVerificadores() {
        return verificadores;
    }

    public void setVerificadores(Map<String, String> verificadores) {
        this.verificadores = verificadores;
    }

    public int getPorcentagem() {
        return porcentagem;
    }
    public void setPorcentagem(int porcentagem) {
        this.porcentagem = porcentagem;
    }
}
