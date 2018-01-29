package com.example.hercilio.appwithfirebase.Objetos;

import android.widget.ArrayAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hercilio on 27/12/2017.
 */

public class HabitosLeituraEscritaObject implements Serializable {


    private List<Perguntas> perguntas;
//    private int[] vetorTotalLeituraAtual = new int[4];
//    private int[] vetorTotalLeituraPassado = new int[4];
//    private int[] vetorTotalLeitura = new int[2]; // Esse vetor possui 2 posições: [0] total atual e [1] total passado
//    private int[] vetorTotalEscritaAtual = new int[4];
//    private int[] vetorTotalEscritaPassado = new int[4];
//    private int[] vetorTotalEscrita = new int[2];

    public HabitosLeituraEscritaObject() {
        this.perguntas = new ArrayList<>();
    }

    public void criaPergunta(String descricao, boolean opcaoUmSelecionada, boolean opcaoDoisSelecionada, boolean opcaoTresSelecionada, boolean opcaoQuatroSelecionada, boolean opcaoCincoSelecionada) {
        Perguntas pergunta = new Perguntas(descricao, opcaoUmSelecionada, opcaoDoisSelecionada, opcaoTresSelecionada, opcaoQuatroSelecionada, opcaoCincoSelecionada);
        perguntas.add(pergunta);
    }

    public List<Perguntas> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(int i, String descricao, boolean opcaoUmSelecionada, boolean opcaoDoisSelecionada, boolean opcaoTresSelecionada, boolean opcaoQuatroSelecionada, boolean opcaoCincoSelecionada) {
        Perguntas pergunta = new Perguntas(descricao, opcaoUmSelecionada, opcaoDoisSelecionada, opcaoTresSelecionada, opcaoQuatroSelecionada, opcaoCincoSelecionada);
        perguntas.set(i, pergunta);
        return;
    }

//    public int[] getVetorTotalLeituraAtual() {
//        return vetorTotalLeituraAtual;
//    }
//
//    public int[] getVetorTotalLeituraPassado() {
//        return vetorTotalLeituraPassado;
//    }
//
//    public int[] getVetorTotalLeitura() {
//        return vetorTotalLeitura;
//    }
//
//    public int[] getVetorTotalEscritaAtual() {
//        return vetorTotalEscritaAtual;
//    }
//
//    public int[] getVetorTotalEscritaPassado() {
//        return vetorTotalEscritaPassado;
//    }
//
//    public int[] getVetorTotalEscrita() {
//        return vetorTotalEscrita;
//    }
//
//    public void setPerguntas(List<Perguntas> perguntas) {
//        this.perguntas = perguntas;
//    }
//
//    public void setVetorTotalLeituraAtual(int[] vetorTotalLeituraAtual) {
//        this.vetorTotalLeituraAtual = vetorTotalLeituraAtual;
//    }
//
//    public void setVetorTotalLeituraPassado(int[] vetorTotalLeituraPassado) {
//        this.vetorTotalLeituraPassado = vetorTotalLeituraPassado;
//    }
//
//    public void setVetorTotalLeitura(int[] vetorTotalLeitura) {
//        this.vetorTotalLeitura = vetorTotalLeitura;
//    }
//
//    public void setVetorTotalEscritaAtual(int[] vetorTotalEscritaAtual) {
//        this.vetorTotalEscritaAtual = vetorTotalEscritaAtual;
//    }
//
//    public void setVetorTotalEscritaPassado(int[] vetorTotalEscritaPassado) {
//        this.vetorTotalEscritaPassado = vetorTotalEscritaPassado;
//    }
//
//    public void setVetorTotalEscrita(int[] vetorTotalEscrita) {
//        this.vetorTotalEscrita = vetorTotalEscrita;
//    }
}
