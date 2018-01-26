package com.example.hercilio.appwithfirebase.Objetos;

import java.io.Serializable;

/**
 * Created by Hercilio on 26/01/2018.
 */

public class Perguntas implements Serializable {
    private String descricao;
    private boolean opcaoUmSelecionada;
    private boolean opcaoDoisSelecionada;
    private boolean opcaoTresSelecionada;
    private boolean opcaoQuatroSelecionada;
    private boolean opcaoCincoSelecionada;

    public Perguntas(){}

    public Perguntas(String descricao, boolean opcaoUmSelecionada, boolean opcaoDoisSelecionada, boolean opcaoTresSelecionada, boolean opcaoQuatroSelecionada, boolean opcaoCincoSelecionada) {
        this.descricao = descricao;
        this.opcaoUmSelecionada = opcaoUmSelecionada;
        this.opcaoDoisSelecionada = opcaoDoisSelecionada;
        this.opcaoTresSelecionada = opcaoTresSelecionada;
        this.opcaoQuatroSelecionada = opcaoQuatroSelecionada;
        this.opcaoCincoSelecionada = opcaoCincoSelecionada;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isOpcaoUmSelecionada() {
        return opcaoUmSelecionada;
    }

    public boolean isOpcaoDoisSelecionada() {
        return opcaoDoisSelecionada;
    }

    public boolean isOpcaoTresSelecionada() {
        return opcaoTresSelecionada;
    }

    public boolean isOpcaoQuatroSelecionada() {
        return opcaoQuatroSelecionada;
    }

    public boolean isOpcaoCincoSelecionada() {
        return opcaoCincoSelecionada;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setOpcaoUmSelecionada(boolean opcaoUmSelecionada) {
        this.opcaoUmSelecionada = opcaoUmSelecionada;
    }

    public void setOpcaoDoisSelecionada(boolean opcaoDoisSelecionada) {
        this.opcaoDoisSelecionada = opcaoDoisSelecionada;
    }

    public void setOpcaoTresSelecionada(boolean opcaoTresSelecionada) {
        this.opcaoTresSelecionada = opcaoTresSelecionada;
    }

    public void setOpcaoQuatroSelecionada(boolean opcaoQuatroSelecionada) {
        this.opcaoQuatroSelecionada = opcaoQuatroSelecionada;
    }

    public void setOpcaoCincoSelecionada(boolean opcaoCincoSelecionada) {
        this.opcaoCincoSelecionada = opcaoCincoSelecionada;
    }
}