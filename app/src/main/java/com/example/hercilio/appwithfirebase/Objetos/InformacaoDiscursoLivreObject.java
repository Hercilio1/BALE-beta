package com.example.hercilio.appwithfirebase.Objetos;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Hercilio on 15/02/2018.
 */

public class InformacaoDiscursoLivreObject implements Serializable {
    //Informacao:
    private Map<String, Integer> informacao;
    private int valorTotalInformacaoEstrutura, valorTotalInformacaoDesempenho;
    //Discurso Livre:
    private Map<String, Integer> discursoLivre;
    private int valorTotalDiscursoLivreEstrutura, valorTotalDiscursoLivreDesempenho;
    //Observações:
    private String observacoes;
    //Porcentagem:
    private int porcentagem;

    public InformacaoDiscursoLivreObject(){}

    //Informacao:
    public void atualizaInformacao(Map buttonsList) {
        informacao = buttonsList;
    }
    public Map getInformacao() {
        return informacao;
    }
    public int getValorTotalInformacaoEstrutura() {
        return valorTotalInformacaoEstrutura;
    }
    public int getValorTotalInformacaoDesempenho() {
        return valorTotalInformacaoDesempenho;
    }
    public void setValorTotalInformacaoEstrutura(int valorTotalInformacaoEstrutura) {
        this.valorTotalInformacaoEstrutura = valorTotalInformacaoEstrutura;
    }
    public void setValorTotalInformacaoDesempenho(int valorTotalInformacaoDesempenho) {
        this.valorTotalInformacaoDesempenho = valorTotalInformacaoDesempenho;
    }

    //Discurso Livre:
    public void atualizaDiscursoLivre(Map buttonsList) {
        discursoLivre = buttonsList;
    }
    public Map getDiscursoLivre() {
        return discursoLivre;
    }
    public int getValorTotalDiscursoLivreEstrutura() {
        return valorTotalDiscursoLivreEstrutura;
    }
    public int getValorTotalDiscursoLivreDesempenho() {
        return valorTotalDiscursoLivreDesempenho;
    }
    public void setValorTotalDiscursoLivreEstrutura(int valorTotalDiscursoLivreEstrutura) {
        this.valorTotalDiscursoLivreEstrutura = valorTotalDiscursoLivreEstrutura;
    }
    public void setValorTotalDiscursoLivreDesempenho(int valorTotalDiscursoLivreDesempenho) {
        this.valorTotalDiscursoLivreDesempenho = valorTotalDiscursoLivreDesempenho;
    }

    //Observacoes:
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public int getPorcentagem() {
        return porcentagem;
    }
    public void setPorcentagem(int porcentagem) {
        this.porcentagem = porcentagem;
    }
}
