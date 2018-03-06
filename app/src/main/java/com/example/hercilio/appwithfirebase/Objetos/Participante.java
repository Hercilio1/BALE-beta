package com.example.hercilio.appwithfirebase.Objetos;

import java.io.Serializable;

/**
 * Created by Hercilio on 19/12/2017.
 */

public class Participante implements Serializable {
    private String nomeCompleto;
    private String cpf;
    private String dataNasc;
    private String sexo; //M ou F
    private String celular;
    private String escolaridade; //Opções listadas depois
    private String dinamicaManual;
    private String profissao;
    private boolean ehAposentado;
    private String linguaMaterna;
    private String outrasLinguas;
    private String observacaoPosAvaliacao;

    private HabitosLeituraEscritaObject hleObject;
    private CompreensaoFrasesObject compFrasesObject;
    private CompreensaoFrasesRadioObject compFrasesRadioObject;
    private String fotoRelogio;
    private CompreensaoVerbalObject compVerbalObject;
    private InformacaoDiscursoLivreObject infDiscNarrObject;
    private NarrativaObject narrativaObject;
    private MemoriaEpisodicaObject memEpObject;
    private FluenciaVerbalObject fluenciaVerbalObject;
    private NomeacaoObject nomeacaoObject;

//    private String dataDaAvaliacao;
//    private String exercidaPor;

//    private Bateria bateria;

    public Participante(){}

    public Participante(String nomeCompleto, String cpf, String dataNasc
                        , String sexo, String celular
                        , String escolaridade, String dinamicaManual
                        , String profissao, boolean ehAposentado
                        , String linguaMaterna, String outrasLinguas) {

        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.sexo = sexo;
        this.celular = celular;
        this.escolaridade = escolaridade;
        this.dinamicaManual = dinamicaManual;
        this.profissao = profissao;
        this.ehAposentado = ehAposentado;
        this.linguaMaterna = linguaMaterna;
        this.outrasLinguas = outrasLinguas;
        this.observacaoPosAvaliacao = observacaoPosAvaliacao;
        this.hleObject = null;

        this.compFrasesObject = null;
        this.compFrasesRadioObject = null;
        this.fotoRelogio = null;
        this.compVerbalObject = null;
        this.infDiscNarrObject = null;
        this.memEpObject = null;
        this.narrativaObject = null;
        this.fluenciaVerbalObject = null;
        this.nomeacaoObject = null;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public String getSexo() {
        return sexo;
    }

    public String getCelular() {
        return celular;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public String getDinamicaManual() {
        return dinamicaManual;
    }

    public String getProfissao() {
        return profissao;
    }

    public boolean isEhAposentado() {
        return ehAposentado;
    }

    public String getLinguaMaterna() {
        return linguaMaterna;
    }

    public String getOutrasLinguas() {
        return outrasLinguas;
    }

    public String getObservacaoPosAvaliacao() {
        return observacaoPosAvaliacao;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public void setDinamicaManual(String dinamicaManual) {
        this.dinamicaManual = dinamicaManual;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public void setEhAposentado(boolean ehAposentado) {
        this.ehAposentado = ehAposentado;
    }

    public void setLinguaMaterna(String linguaMaterna) {
        this.linguaMaterna = linguaMaterna;
    }

    public void setOutrasLinguas(String outrasLinguas) {
        this.outrasLinguas = outrasLinguas;
    }

    public void setObservacaoPosAvaliacao(String observacaoPosAvaliacao) {
        this.observacaoPosAvaliacao = observacaoPosAvaliacao;
    }


    /**********************
     * OBJETOS DA BATERIA *
     **********************/

    public HabitosLeituraEscritaObject getHleObject() {
        return hleObject;
    }
    public void setHleObject() {
        hleObject = new HabitosLeituraEscritaObject();
    }

    public CompreensaoFrasesObject getCompFrasesObject() { return compFrasesObject; }
    public void setCompFrasesObject() { compFrasesObject = new CompreensaoFrasesObject(); }

    public CompreensaoFrasesRadioObject getCompFrasesRadioObject() {
        return compFrasesRadioObject;
    }
    public void setCompFrasesRadioObject() {
        compFrasesRadioObject = new CompreensaoFrasesRadioObject();
    }

    public String getFotoRelogio() {
        return fotoRelogio;
    }
    public void setFotoRelogio(String fotoRelogio) {
        this.fotoRelogio = fotoRelogio;
    }

    public CompreensaoVerbalObject getCompVerbalObject() {
        return compVerbalObject;
    }
    public void setCompVerbalObject(CompreensaoVerbalObject compVerbalObject) {
        this.compVerbalObject = compVerbalObject;
    }

    public InformacaoDiscursoLivreObject getInfDiscNarrObject() {
        return infDiscNarrObject;
    }
    public void setInfDiscNarrObject(InformacaoDiscursoLivreObject infDiscNarrObject) {
        this.infDiscNarrObject = infDiscNarrObject;
    }

    public MemoriaEpisodicaObject getMemEpObject() {
        return memEpObject;
    }
    public void setMemEpObject(MemoriaEpisodicaObject memEpObject) {
        this.memEpObject = memEpObject;
    }

    public NarrativaObject getNarrativaObject() {
        return narrativaObject;
    }
    public void setNarrativaObject(NarrativaObject narrativaObject) {
        this.narrativaObject = narrativaObject;
    }

    public FluenciaVerbalObject getFluenciaVerbalObject() {
        return fluenciaVerbalObject;
    }
    public void setFluenciaVerbalObject(FluenciaVerbalObject fluenciaVerbalObject) {
        this.fluenciaVerbalObject = fluenciaVerbalObject;
    }

    public NomeacaoObject getNomeacaoObject() {
        return nomeacaoObject;
    }
    public void setNomeacaoObject(NomeacaoObject nomeacaoObject) {
        this.nomeacaoObject = nomeacaoObject;
    }
}
