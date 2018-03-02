package com.example.hercilio.appwithfirebase.Objetos;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Hercilio on 15/02/2018.
 */

public class InformacaoDiscursoLivreObject implements Serializable {
    //Informacao:
    private Map<String, Integer> informacao;
    private int valorTotalInformacao;

    //Discurso Livre:
    private Map<String, Integer> discursoLivre;
    private int valorTotalDiscursoLivre;

    public InformacaoDiscursoLivreObject(){}

    //Informacao:
    public void atualizaInformacao(Map buttonsList) {
        informacao = buttonsList;
    }
    public Map getInformacao() {
        return informacao;
    }
    public int getValorTotalInformacao() { return valorTotalInformacao; }
    public void setValorTotalInformacao(int valorTotalInformacao) { this.valorTotalInformacao = valorTotalInformacao; }

    //Discurso Livre:
    public void atualizaDiscursoLivre(Map buttonsList) {
        discursoLivre = buttonsList;
    }
    public Map getDiscursoLivre() {
        return discursoLivre;
    }
    public int getValorTotalDiscursoLivre() { return valorTotalDiscursoLivre; }
    public void setValorTotalDiscursoLivre(int valorTotalDiscursoLivre) { this.valorTotalDiscursoLivre = valorTotalDiscursoLivre; }
}
