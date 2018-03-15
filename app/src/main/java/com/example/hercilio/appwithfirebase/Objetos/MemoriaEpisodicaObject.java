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

    //Segunda Fase:
    private Map<String, Boolean> segundaFaseSemDica, segundaFaseComDica;
    private int pontuacaoSegundaFaseSemDica = 0, pontuacaoSegundaFaseComDica = 0;
    private long timeStampSegundaFase = 0;

    //Terceira Fase:
    private Map<String, Boolean> terceiraFaseSemDica, terceiraFaseComDica;
    private int pontuacaoTerceiraFaseSemDica = 0, pontuacaoTerceiraFaseComDica = 0;
    private long timeStampTerceiraFase = 0;

    //Quarta Fase:
    private Map<String, Boolean> quartaFaseSemDica, quartaFaseComDica;
    private int pontuacaoQuartaFaseSemDica = 0, pontuacaoQuartaFaseComDica = 0;
    private long timeStampQuartaFase = 0;

    //Quinta Fase:
    private Map<String, Boolean> quintaFaseSemDica, quintaFaseComDica;
    private int pontuacaoQuintaFaseSemDica = 0, pontuacaoQuintaFaseComDica = 0;
    private long timeStampQuintaFase = 0;

    //Porcentagem:
    private int porcentagem;

    public MemoriaEpisodicaObject() {
    }

    /*****************
     * PRIMEIRA FASE *
     *****************/
    //Getters dos MAPs:
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
    //Setters dos MAPs:
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

    //Getters dos totais:
    public int getPontuacaoIndicacao() {
        return pontuacaoIndicacao;
    }
    public int getPontuacaoNomeacao() {
        return pontuacaoNomeacao;
    }
    //Setters dos totais:
    public void setPontuacaoIndicacao(int pontuacaoIndicacao) {
        this.pontuacaoIndicacao = pontuacaoIndicacao;
    }
    public void setPontuacaoNomeacao(int pontuacaoNomeacao) {
        this.pontuacaoNomeacao = pontuacaoNomeacao;
    }


    /*****************
     * SEGUNDA FASE *
     *****************/
    //Getters dos MAPs:
    public Map<String, Boolean> getSegundaFaseSemDica() {
        return segundaFaseSemDica;
    }
    public Map<String, Boolean> getSegundaFaseComDica() {
        return segundaFaseComDica;
    }
    //Setters dos MAPs:
    public void setSegundaFaseSemDica(Map<String, Boolean> segundaFaseSemDica) {
        this.segundaFaseSemDica = segundaFaseSemDica;
    }
    public void setSegundaFaseComDica(Map<String, Boolean> segundaFaseComDica) {
        this.segundaFaseComDica = segundaFaseComDica;
    }

    //Getters dos totais:
    public int getPontuacaoSegundaFaseSemDica() {
        return pontuacaoSegundaFaseSemDica;
    }
    public int getPontuacaoSegundaFaseComDica() {
        return pontuacaoSegundaFaseComDica;
    }
    //Setters dos totais:
    public void setPontuacaoSegundaFaseSemDica(int pontuacaoSegundaFaseSemDica) {
        this.pontuacaoSegundaFaseSemDica = pontuacaoSegundaFaseSemDica;
    }
    public void setPontuacaoSegundaFaseComDica(int pontuacaoSegundaFaseComDica) {
        this.pontuacaoSegundaFaseComDica = pontuacaoSegundaFaseComDica;
    }

    //Getter e Setter timestamp
    public long getTimeStampSegundaFase() {
        return timeStampSegundaFase;
    }
    public void setTimeStampSegundaFase(long timeStampSegundaFase) {
        this.timeStampSegundaFase = timeStampSegundaFase;
    }


    /*****************
     * TERCEIRA FASE *
     *****************/
    //Getters dos MAPs:
    public Map<String, Boolean> getTerceiraFaseSemDica() {
        return terceiraFaseSemDica;
    }
    public Map<String, Boolean> getTerceiraFaseComDica() {
        return terceiraFaseComDica;
    }
    //Setters dos MAPs:
    public void setTerceiraFaseSemDica(Map<String, Boolean> terceiraFaseSemDica) {
        this.terceiraFaseSemDica = terceiraFaseSemDica;
    }
    public void setTerceiraFaseComDica(Map<String, Boolean> terceiraFaseComDica) {
        this.terceiraFaseComDica = terceiraFaseComDica;
    }

    //Getters dos totais:
    public int getPontuacaoTerceiraFaseSemDica() {
        return pontuacaoTerceiraFaseSemDica;
    }
    public int getPontuacaoTerceiraFaseComDica() {
        return pontuacaoTerceiraFaseComDica;
    }
    //Setters dos totais:
    public void setPontuacaoTerceiraFaseSemDica(int pontuacaoTerceiraFaseSemDica) {
        this.pontuacaoTerceiraFaseSemDica = pontuacaoTerceiraFaseSemDica;
    }
    public void setPontuacaoTerceiraFaseComDica(int pontuacaoTerceiraFaseComDica) {
        this.pontuacaoTerceiraFaseComDica = pontuacaoTerceiraFaseComDica;
    }

    //Getter e Setter timestamp
    public long getTimeStampTerceiraFase() {
        return timeStampTerceiraFase;
    }
    public void setTimeStampTerceiraFase(long timeStampTerceiraFase) {
        this.timeStampTerceiraFase = timeStampTerceiraFase;
    }


    /*****************
     * QUARTA FASE *
     *****************/
    //Getters dos MAPs:
    public Map<String, Boolean> getQuartaFaseSemDica() {
        return quartaFaseSemDica;
    }
    public Map<String, Boolean> getQuartaFaseComDica() {
        return quartaFaseComDica;
    }
    //Setters dos MAPs:
    public void setQuartaFaseSemDica(Map<String, Boolean> quartaFaseSemDica) {
        this.quartaFaseSemDica = quartaFaseSemDica;
    }
    public void setQuartaFaseComDica(Map<String, Boolean> quartaFaseComDica) {
        this.quartaFaseComDica = quartaFaseComDica;
    }

    //Getters dos totais:
    public int getPontuacaoQuartaFaseSemDica() {
        return pontuacaoQuartaFaseSemDica;
    }
    public int getPontuacaoQuartaFaseComDica() {
        return pontuacaoQuartaFaseComDica;
    }
    //Setters dos totais:
    public void setPontuacaoQuartaFaseSemDica(int pontuacaoQuartaFaseSemDica) {
        this.pontuacaoQuartaFaseSemDica = pontuacaoQuartaFaseSemDica;
    }
    public void setPontuacaoQuartaFaseComDica(int pontuacaoQuartaFaseComDica) {
        this.pontuacaoQuartaFaseComDica = pontuacaoQuartaFaseComDica;
    }

    //Getter e Setter timestamp
    public long getTimeStampQuartaFase() {
        return timeStampQuartaFase;
    }
    public void setTimeStampQuartaFase(long timeStampQuartaFase) {
        this.timeStampQuartaFase = timeStampQuartaFase;
    }


    /*****************
     * QUINTA FASE *
     *****************/
    //Getters dos MAPs:
    public Map<String, Boolean> getQuintaFaseSemDica() {
        return quintaFaseSemDica;
    }
    public Map<String, Boolean> getQuintaFaseComDica() {
        return quintaFaseComDica;
    }
    //Setters dos MAPs:
    public void setQuintaFaseSemDica(Map<String, Boolean> quintaFaseSemDica) {
        this.quintaFaseSemDica = quintaFaseSemDica;
    }
    public void setQuintaFaseComDica(Map<String, Boolean> quintaFaseComDica) {
        this.quintaFaseComDica = quintaFaseComDica;
    }

    //Getters dos totais:
    public int getPontuacaoQuintaFaseSemDica() {
        return pontuacaoQuintaFaseSemDica;
    }
    public int getPontuacaoQuintaFaseComDica() {
        return pontuacaoQuintaFaseComDica;
    }
    //Setters dos totais:
    public void setPontuacaoQuintaFaseSemDica(int pontuacaoQuintaFaseSemDica) {
        this.pontuacaoQuintaFaseSemDica = pontuacaoQuintaFaseSemDica;
    }
    public void setPontuacaoQuintaFaseComDica(int pontuacaoQuintaFaseComDica) {
        this.pontuacaoQuintaFaseComDica = pontuacaoQuintaFaseComDica;
    }

    //Getter e Setter timestamp
    public long getTimeStampQuintaFase() {
        return timeStampQuintaFase;
    }
    public void setTimeStampQuintaFase(long timeStampQuintaFase) {
        this.timeStampQuintaFase = timeStampQuintaFase;
    }

    /***************
     * PORCENTAGEM *
     ***************/
    public int getPorcentagem() {
        return porcentagem;
    }
    public void setPorcentagem(int porcentagem) {
        this.porcentagem = porcentagem;
    }
}