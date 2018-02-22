package com.example.hercilio.appwithfirebase.Objetos;

import org.w3c.dom.ProcessingInstruction;

import java.io.Serializable;

/**
 * Created by Hercilio on 19/02/2018.
 */

public class UserDados implements Serializable {
    private String nome;
    private int nroDeParticipantesEntrevistados;
    private boolean isAdmin;

    public UserDados () {}

    public UserDados(String nome, int nroDeParticipantesEntrevistados, boolean isAdmin) {
        this.nome = nome;
        this.nroDeParticipantesEntrevistados = nroDeParticipantesEntrevistados;
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNroDeParticipantesEntrevistados() {
        return nroDeParticipantesEntrevistados;
    }
    public void setNroDeParticipantesEntrevistados(int nroDeParticipantesEntrevistados) {
        this.nroDeParticipantesEntrevistados = nroDeParticipantesEntrevistados;
    }
}
