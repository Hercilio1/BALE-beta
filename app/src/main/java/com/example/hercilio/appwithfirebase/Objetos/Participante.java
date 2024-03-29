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
    private int porcentagem;
    private boolean finalizado;

    private HabitosLeituraEscritaObject hleObject;
    private CompreensaoFrasesObject compFrasesObject;
    private CompreensaoVerbalObject compVerbalObject;
    private InformacaoDiscursoLivreObject informacaoDiscLivreObject;
    private NarrativaObject narrativaObject;
    private MemoriaEpisodicaObject memEpObject;
    private FluenciaVerbalObject fluenciaVerbalObject;
    private NomeacaoObject nomeacaoObject;
    private DigitSpanObject digitSpanObject;
    private AssociacaoSemanticaObject associacaoSemanticaObject;
    private ConhecimentoSemanticoObject conhecimentoSemanticoObject;

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
        this.porcentagem = 0;
        this.finalizado = false;

        this.hleObject = null;
        this.compFrasesObject = null;
        this.compVerbalObject = null;
        this.informacaoDiscLivreObject = null;
        this.memEpObject = null;
        this.narrativaObject = null;
        this.fluenciaVerbalObject = null;
        this.nomeacaoObject = null;
        this.digitSpanObject = null;
        this.associacaoSemanticaObject = null;
        this.conhecimentoSemanticoObject = null;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public int getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(int porcentagem) {
        this.porcentagem = porcentagem;
    }

    public boolean isFinalizado() {
        return finalizado;
    }
    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
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
    public void setCompFrasesObject(CompreensaoFrasesObject compFrasesObject) {
        this.compFrasesObject = compFrasesObject;
    }

    public CompreensaoVerbalObject getCompVerbalObject() {
        return compVerbalObject;
    }
    public void setCompVerbalObject(CompreensaoVerbalObject compVerbalObject) {
        this.compVerbalObject = compVerbalObject;
    }

    public InformacaoDiscursoLivreObject getInformacaoDiscLivreObject() {
        return informacaoDiscLivreObject;
    }
    public void setInformacaoDiscLivreObject(InformacaoDiscursoLivreObject informacaoDiscLivreObject) {
        this.informacaoDiscLivreObject = informacaoDiscLivreObject;
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

    public DigitSpanObject getDigitSpanObject() {
        return digitSpanObject;
    }
    public void setDigitSpanObject(DigitSpanObject digitSpanObject) {
        this.digitSpanObject = digitSpanObject;
    }

    public AssociacaoSemanticaObject getAssociacaoSemanticaObject() {
        return associacaoSemanticaObject;
    }
    public void setAssociacaoSemanticaObject(AssociacaoSemanticaObject associacaoSemanticaObject) {
        this.associacaoSemanticaObject = associacaoSemanticaObject;
    }

    public ConhecimentoSemanticoObject getConhecimentoSemanticoObject() {
        return conhecimentoSemanticoObject;
    }
    public void setConhecimentoSemanticoObject(ConhecimentoSemanticoObject conhecimentoSemanticoObject) {
        this.conhecimentoSemanticoObject = conhecimentoSemanticoObject;
    }

    /*****************************
     * GETS AND SETS Secundarios *
     *****************************/
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
}
