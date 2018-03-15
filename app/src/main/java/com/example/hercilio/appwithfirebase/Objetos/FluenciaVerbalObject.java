package com.example.hercilio.appwithfirebase.Objetos;

import java.io.Serializable;

/**
 * Created by Hercilio on 04/03/2018.
 */

public class FluenciaVerbalObject implements Serializable {

    //Animais
    private String animais00s15s, animais16s30s, animais31s45s, animais46s60s;
    //Palavras com P
    private String palavras00s15s, palavras16s30s, palavras31s45s, palavras46s60s;
    //Porcentagem
    private int porcentagem;

    public FluenciaVerbalObject(){}

    public String getAnimais00s15s() {
        return animais00s15s;
    }

    public String getAnimais16s30s() {
        return animais16s30s;
    }

    public String getAnimais31s45s() {
        return animais31s45s;
    }

    public String getAnimais46s60s() {
        return animais46s60s;
    }

    public String getPalavras00s15s() {
        return palavras00s15s;
    }

    public String getPalavras16s30s() {
        return palavras16s30s;
    }

    public String getPalavras31s45s() {
        return palavras31s45s;
    }

    public String getPalavras46s60s() {
        return palavras46s60s;
    }

    public void setAnimais00s15s(String animais00s15s) {
        this.animais00s15s = animais00s15s;
    }

    public void setAnimais16s30s(String animais16s30s) {
        this.animais16s30s = animais16s30s;
    }

    public void setAnimais31s45s(String animais31s45s) {
        this.animais31s45s = animais31s45s;
    }

    public void setAnimais46s60s(String animais46s60s) {
        this.animais46s60s = animais46s60s;
    }

    public void setPalavras00s15s(String palavras00s15s) {
        this.palavras00s15s = palavras00s15s;
    }

    public void setPalavras16s30s(String palavras16s30s) {
        this.palavras16s30s = palavras16s30s;
    }

    public void setPalavras31s45s(String palavras31s45s) {
        this.palavras31s45s = palavras31s45s;
    }

    public void setPalavras46s60s(String palavras46s60s) {
        this.palavras46s60s = palavras46s60s;
    }

    //Porcentagem:
    public int getPorcentagem() {
        return porcentagem;
    }
    public void setPorcentagem(int porcentagem) {
        this.porcentagem = porcentagem;
    }
}
