package com.example.hercilio.appwithfirebase.Objetos;

import java.io.Serializable;

/**
 * Created by Hercilio on 02/02/2018.
 */

public class CompreensaoFrasesObject implements Serializable {

    private int valorFinal1 = -1;
    private int valorFinal2 = -1;
    private String fotoRelogio;
    private int porcentagem;

    public CompreensaoFrasesObject() {}

    public int getValorFinal1() {
        return valorFinal1;
    }
    public void setValorFinal1(int valorFinal1) {
        this.valorFinal1 = valorFinal1;
    }

    public int getValorFinal2() {
        return valorFinal2;
    }
    public void setValorFinal2(int valorFinal2) {
        this.valorFinal2 = valorFinal2;
    }

    public String getFotoRelogio() {
        return fotoRelogio;
    }
    public void setFotoRelogio(String fotoRelogio) {
        this.fotoRelogio = fotoRelogio;
    }

    public int getPorcentagem() {
        return porcentagem;
    }
    public void setPorcentagem(int porcentagem) {
        this.porcentagem = porcentagem;
    }
}
