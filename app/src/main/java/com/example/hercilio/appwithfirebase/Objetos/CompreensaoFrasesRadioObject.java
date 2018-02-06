package com.example.hercilio.appwithfirebase.Objetos;

import java.io.Serializable;

/**
 * Created by Hercilio on 05/02/2018.
 */

public class CompreensaoFrasesRadioObject implements Serializable{
    private int valorFinal;

    public CompreensaoFrasesRadioObject(){}

    public int getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(int valorFinal) {
        this.valorFinal = valorFinal;
    }
}
