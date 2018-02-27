package com.example.hercilio.appwithfirebase.Objetos;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Hercilio on 27/02/2018.
 */

public class MemoriaEpisodicaObject implements Serializable {
    //Primeira Fase:
    private Map<String, Boolean> primeiraFaseAnalise1, primeiraFaseAnalise2, primeiraFaseAnalise3, primeiraFaseAnalise4;
    private int pontuacaoIndicacao = 0, pontuacaoNomeacao = 0;


    public MemoriaEpisodicaObject() {
    }

    public Map<String, Boolean> getPrimeiraFaseAnalise1() {
        return primeiraFaseAnalise1;
    }

    public Map<String, Boolean> getPrimeiraFaseAnalise2() {
        return primeiraFaseAnalise2;
    }

    public Map<String, Boolean> getPrimeiraFaseAnalise3() {
        return primeiraFaseAnalise3;
    }

    public Map<String, Boolean> getPrimeiraFaseAnalise4() {
        return primeiraFaseAnalise4;
    }

    public void setPrimeiraFaseAnalise1(Map<String, Boolean> primeiraFaseAnalise1) {
        this.primeiraFaseAnalise1 = primeiraFaseAnalise1;
    }

    public void setPrimeiraFaseAnalise2(Map<String, Boolean> primeiraFaseAnalise2) {
        this.primeiraFaseAnalise2 = primeiraFaseAnalise2;
    }

    public void setPrimeiraFaseAnalise3(Map<String, Boolean> primeiraFaseAnalise3) {
        this.primeiraFaseAnalise3 = primeiraFaseAnalise3;
    }

    public void setPrimeiraFaseAnalise4(Map<String, Boolean> primeiraFaseAnalise4) {
        this.primeiraFaseAnalise4 = primeiraFaseAnalise4;
    }

    public int getPontuacaoIndicacao() {
        return pontuacaoIndicacao;
    }

    public int getPontuacaoNomeacao() {
        return pontuacaoNomeacao;
    }

    public void setPontuacaoIndicacao(int pontuacaoIndicacao) {
        this.pontuacaoIndicacao = pontuacaoIndicacao;
    }

    public void setPontuacaoNomeacao(int pontuacaoNomeacao) {
        this.pontuacaoNomeacao = pontuacaoNomeacao;
    }
}