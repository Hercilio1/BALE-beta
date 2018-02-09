package com.example.hercilio.appwithfirebase.Objetos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hercilio on 08/02/2018.
 */

public class CompreensaoVerbalObject implements Serializable {
    private Map<String, Boolean> primeiraAvaliacao;

    public CompreensaoVerbalObject(){}

    public void atualizaPrimeiraAvaliacao(Map buttonsList) {
        primeiraAvaliacao = buttonsList;
    }

    public Map getPrimeiraAvaliacao() {
        return primeiraAvaliacao;
    }

}
