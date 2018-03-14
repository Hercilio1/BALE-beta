package com.example.hercilio.appwithfirebase.Objetos;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Hercilio on 13/03/2018.
 */

public class ConhecimentoSemanticoObject implements Serializable{

    //Segunda avaliacao:
    private Map<String, Integer> verificadores;
    private Map<String, String> verificadoresEditText;
    private int valorTotalProverbio, valorTotalMetafora;
    private int porcentagem;

    public ConhecimentoSemanticoObject(){}

    public Map<String, Integer> getVerificadores() {
        return verificadores;
    }
    public void setVerificadores(Map<String, Integer> verificadores) {
        this.verificadores = verificadores;
    }

    public Map<String, String> getVerificadoresEditText() {
        return verificadoresEditText;
    }
    public void setVerificadoresEditText(Map<String, String> verificadoresEditText) {
        this.verificadoresEditText = verificadoresEditText;
    }

    public int getValorTotalProverbio() {
        return valorTotalProverbio;
    }
    public void setValorTotalProverbio(int valorTotalProverbio) {
        this.valorTotalProverbio = valorTotalProverbio;
    }

    public int getValorTotalMetafora() {
        return valorTotalMetafora;
    }
    public void setValorTotalMetafora(int valorTotalMetafora) {
        this.valorTotalMetafora = valorTotalMetafora;
    }

    public int getPorcentagem() {
        return porcentagem;
    }
    public void setPorcentagem(int porcentagem) {
        this.porcentagem = porcentagem;
    }
}
