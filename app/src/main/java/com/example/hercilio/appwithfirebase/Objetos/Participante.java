package com.example.hercilio.appwithfirebase.Objetos;

/**
 * Created by Hercilio on 19/12/2017.
 */

public class Participante {
    private String nomeCompleto;
    private String dataNasc;
    private String sexo; //M ou F
    private String celular;
    private String escolaridade; //Opções listadas depois
    private String dinamicaManual;
    private String profissao;
    private String linguaMaterna;
    private String outrasLinguas;
    private String observacaoPosAvaliacao;

    private String dataDaAvaliacao;
    private String exercidaPor;

    private Bateria bateria;


    public Participante(String nomeCompleto, String dataNasc,
                        String sexo, String celular,
                        String escolaridade, String dinamicaManual,
                        String profissao, String linguaMaterna,
                        String outrasLinguas, String observacaoPosAvaliacao,
                        String dataDaAvaliacao, String exercidaPor) {
        this.nomeCompleto = nomeCompleto;
        this.dataNasc = dataNasc;
        this.sexo = sexo;
        this.celular = celular;
        this.escolaridade = escolaridade;
        this.dinamicaManual = dinamicaManual;
        this.profissao = profissao;
        this.linguaMaterna = linguaMaterna;
        this.outrasLinguas = outrasLinguas;
        this.observacaoPosAvaliacao = observacaoPosAvaliacao;
        this.dataDaAvaliacao = dataDaAvaliacao;
        this.exercidaPor = exercidaPor;
        this.bateria = new Bateria(null);
    }

    public Bateria getBateria() {
        return bateria;
    }

    public void setBateria(Bateria bat) {
        bateria = bat;
    }
}
