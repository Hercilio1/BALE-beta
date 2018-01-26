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
    private long total;

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
}
