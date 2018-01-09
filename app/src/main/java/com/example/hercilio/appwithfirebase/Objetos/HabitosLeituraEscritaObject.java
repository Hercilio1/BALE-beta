package com.example.hercilio.appwithfirebase.Objetos;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Hercilio on 27/12/2017.
 */

public class HabitosLeituraEscritaObject {
    public class Perguntas {
        private String descricao;
        private boolean opcaoUmSelecionada;
        private boolean opcaoDoisSelecionada;
        private boolean opcaoTresSelecionada;
        private boolean opcaoQuatroSelecionada;
        private boolean opcaoCincoSelecionada;

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

    private ArrayList<Perguntas> perguntas;
    private int total;

    public HabitosLeituraEscritaObject() {
        perguntas = new ArrayList<>();
    }

    public void criaPergunta(String descricao, boolean opcaoUmSelecionada, boolean opcaoDoisSelecionada, boolean opcaoTresSelecionada, boolean opcaoQuatroSelecionada, boolean opcaoCincoSelecionada) {
        Perguntas pergunta = new Perguntas(descricao, opcaoUmSelecionada, opcaoDoisSelecionada, opcaoTresSelecionada, opcaoQuatroSelecionada, opcaoCincoSelecionada);
        perguntas.add(pergunta);
    }

    public ArrayList<Perguntas> getPerguntas() {
        return perguntas;
    }
}
