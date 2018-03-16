package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.ConhecimentoSemantico;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoVerbal.CompreensaoVerbalLobbyActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Objetos.CompreensaoVerbalObject;
import com.example.hercilio.appwithfirebase.Objetos.ConhecimentoSemanticoObject;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hercilio on 12/03/2018.
 */

public class ConhecimentoSemanticoActivity extends AppCompatActivity {

    //Proverbio RadioGroup:
    private RadioGroup mRadioProverbioQuest1, mRadioProverbioQuest2, mRadioProverbioQuest3;
    //Metafora RadioGroup:
    private RadioGroup mRadioMetaforaQuest1, mRadioMetaforaQuest2, mRadioMetaforaQuest3;

    //Proverbio EditTexts:
    private EditText mEditTextProverbio1, mEditTextProverbio2, mEditTextProverbio3;
    private EditText mEditTextProverbioExplicacao1, mEditTextProverbioExplicacao2, mEditTextProverbioExplicacao3;
    //Metafora EditTexts:
    private EditText mEditTextMetafora1, mEditTextMetafora2, mEditTextMetafora3;

    //Check Proverbio:
    private int checkedRadioProverbioQuest1, checkedRadioProverbioQuest2, checkedRadioProverbioQuest3;
    //Check Metafora:
    private int checkedRadioMetaforaQuest1, checkedRadioMetaforaQuest2, checkedRadioMetaforaQuest3;

    //Parciais Proverbio:
    private int parcialProverbioQuest1, parcialProverbioQuest2, parcialProverbioQuest3;
    //Parciais Metafora:
    private int parcialMetaforaQuest1, parcialMetaforaQuest2, parcialMetaforaQuest3;

    //Variaveis dos valores totais:
    private int valorTotalProverbio, valorTotalMetafora;

    //Totais Text Views:
    private TextView mTotalNumero1Proverbio, mTotalNumero1Metafora;

    //Dicionario que armazena os radiobuttons selecionados:
    private Map<String, Integer> verificadores = new HashMap<>();

    //Discionario de EditText
    private Map<String, String> verificadoresEditText = new HashMap<>();

    //Botão validador caso não haja o pressionamento de algum radio button
    private boolean validaRadioButtons;

    //Botao continuar:
    private Button btnContinuar;

    //Variável que auxilia na verificacao se o teste já está finalizado
    private boolean isFinalizado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conhecimento_semantico);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //Proverbio:
        mRadioProverbioQuest1 = (RadioGroup) findViewById(R.id.rg_conhecimento_semantico_proverbio_1);
        mRadioProverbioQuest2 = (RadioGroup) findViewById(R.id.rg_conhecimento_semantico_proverbio_2);
        mRadioProverbioQuest3 = (RadioGroup) findViewById(R.id.rg_conhecimento_semantico_proverbio_3);

        //Metafora:
        mRadioMetaforaQuest1 = (RadioGroup) findViewById(R.id.rg_conhecimento_semantico_metafora_1);
        mRadioMetaforaQuest2 = (RadioGroup) findViewById(R.id.rg_conhecimento_semantico_metafora_2);
        mRadioMetaforaQuest3 = (RadioGroup) findViewById(R.id.rg_conhecimento_semantico_metafora_3);

        //Proverbio EditText:
        mEditTextProverbio1 = (EditText) findViewById(R.id.et_conhecimento_semantico_proverbio_1);
        mEditTextProverbio2 = (EditText) findViewById(R.id.et_conhecimento_semantico_proverbio_2);
        mEditTextProverbio3 = (EditText) findViewById(R.id.et_conhecimento_semantico_proverbio_3);
        mEditTextProverbioExplicacao1 = (EditText) findViewById(R.id.et_conhecimento_semantico_proverbio_1_explicacao);
        mEditTextProverbioExplicacao2 = (EditText) findViewById(R.id.et_conhecimento_semantico_proverbio_2_explicacao);
        mEditTextProverbioExplicacao3 = (EditText) findViewById(R.id.et_conhecimento_semantico_proverbio_3_explicacao);

        //Metafora EditText:
        mEditTextMetafora1 = (EditText) findViewById(R.id.et_conhecimento_semantico_metafora_1);
        mEditTextMetafora2 = (EditText) findViewById(R.id.et_conhecimento_semantico_metafora_2);
        mEditTextMetafora3 = (EditText) findViewById(R.id.et_conhecimento_semantico_metafora_3);

        //Totais:
        mTotalNumero1Proverbio = (TextView) findViewById(R.id.total_number1_proverbio);
        mTotalNumero1Metafora = (TextView) findViewById(R.id.total_number1_metafora);

        //Botao continuar:
        btnContinuar = (Button) findViewById(R.id.btn_continuar_conhecimento_semantico);

        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);
            isFinalizado = participante.isFinalizado();

            if (participante.getConhecimentoSemanticoObject() != null) {
                verificadores = participante.getConhecimentoSemanticoObject().getVerificadores();
                verificadoresEditText = participante.getConhecimentoSemanticoObject().getVerificadoresEditText();
                autoComplete(participante);
                autoCompleteEditText();
            }

            btnContinuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!isFinalizado) {
                        armazenaStringsEditText();
                        registrar(participante);
                    }
                    if(!validaRadioButtons) {
                        Intent intent = new Intent(getBaseContext(), BaleLobbyActivity.class);
                        intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                        startActivity(intent);
                    } else {
                        if (validaRadioButtons) {

                            AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());

                            alert.setTitle("Atenção");
                            alert.setMessage("Você não pressionou algum botão necessário para pesquisa. Favor pressiona-lo(s) para presseguir");

                            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    validaRadioButtons = true;
                                }
                            });

                            AlertDialog dialog = alert.create();
                            alert.show();
                        }
                    }
                }
            });
        }
    }

    public void registrar(Participante participante) {
        ArrayList<Integer> verificaValidade = new ArrayList<>();

        //Proverbio:
        checkedRadioProverbioQuest1 = (int) onFrequenciaRadioButtonClicked(mRadioProverbioQuest1, false);
        if(checkedRadioProverbioQuest1 == -1)
            verificaValidade.add(11);
        armazenaDadoNoDicionario(11, checkedRadioProverbioQuest1);

        checkedRadioProverbioQuest2 = (int) onFrequenciaRadioButtonClicked(mRadioProverbioQuest2, false);
        if(checkedRadioProverbioQuest2 == -1)
            verificaValidade.add(12);
        armazenaDadoNoDicionario(12, checkedRadioProverbioQuest2);

        checkedRadioProverbioQuest3 = (int) onFrequenciaRadioButtonClicked(mRadioProverbioQuest3, false);
        if(checkedRadioProverbioQuest3 == -1)
            verificaValidade.add(13);
        armazenaDadoNoDicionario(13, checkedRadioProverbioQuest3);

        //Metafora:
        checkedRadioMetaforaQuest1 = (int) onFrequenciaRadioButtonClicked(mRadioMetaforaQuest1, true);
        if(checkedRadioMetaforaQuest1 == -1)
            verificaValidade.add(21);
        armazenaDadoNoDicionario(21, checkedRadioMetaforaQuest1);

        checkedRadioMetaforaQuest2 = (int) onFrequenciaRadioButtonClicked(mRadioMetaforaQuest2, true);
        if(checkedRadioMetaforaQuest2 == -1)
            verificaValidade.add(22);
        armazenaDadoNoDicionario(22, checkedRadioMetaforaQuest2);

        checkedRadioMetaforaQuest3 = (int) onFrequenciaRadioButtonClicked(mRadioMetaforaQuest3, true);
        if(checkedRadioMetaforaQuest3 == -1)
            verificaValidade.add(23);
        armazenaDadoNoDicionario(23, checkedRadioMetaforaQuest3);

        if(!verificaValidade.isEmpty()) {
            validaRadioButtons = true;
        } else if (participante != null) {
            validaRadioButtons = false;
            alteraDadosFirebase(participante);
        }

    }

    public long onFrequenciaRadioButtonClicked(RadioGroup mRadio, boolean ref) {

        long avaliacao = -1;
        String checkedFrequencia;
        int selectedRadioId = mRadio.getCheckedRadioButtonId();
        if (selectedRadioId != -1) {
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioId);
            checkedFrequencia = selectedRadioButton.getText().toString();
            if(ref) {
                if (checkedFrequencia.equals(getResources().getString(R.string.comp_verbal_segunda_avaliacao_errou))) {
                    avaliacao = 0;
                } else if (checkedFrequencia.equals(getResources().getString(R.string.comp_verbal_segunda_avaliacao_acertou))) {
                    avaliacao = 1;
                }
            } else {
                if (checkedFrequencia.equals(getResources().getString(R.string.comp_verbal_segunda_avaliacao_errou))) {
                    avaliacao = 0;
                } else if (checkedFrequencia.equals(getResources().getString(R.string.associacao_semantica_parcialmente))) {
                    avaliacao = 1;
                } else if (checkedFrequencia.equals(getResources().getString(R.string.comp_verbal_segunda_avaliacao_acertou))) {
                    avaliacao = 2;
                }
            }
        }
        return avaliacao;
    }

    public void armazenaDadoNoDicionario(int index, Integer selecao) {
        verificadores.put(""+index, selecao);
        somaValoresTotais(index, selecao);
    }

    public void somaValoresTotais(int ref, int selecao) {
        if (selecao != -1) {
            switch (ref) {
                case 11:
                    valorTotalProverbio -= parcialProverbioQuest1;
                    parcialProverbioQuest1 = selecao;
                    valorTotalProverbio += parcialProverbioQuest1;
                    break;
                case 12:
                    valorTotalProverbio -= parcialProverbioQuest2;
                    parcialProverbioQuest2 = selecao;
                    valorTotalProverbio += parcialProverbioQuest2;
                    break;
                case 13:
                    valorTotalProverbio -= parcialProverbioQuest3;
                    parcialProverbioQuest3 = selecao;
                    valorTotalProverbio += parcialProverbioQuest3;
                    break;
                case 21:
                    valorTotalMetafora -= parcialMetaforaQuest1;
                    parcialMetaforaQuest1 = selecao;
                    valorTotalMetafora += parcialMetaforaQuest1;
                    break;
                case 22:
                    valorTotalMetafora -= parcialMetaforaQuest2;
                    parcialMetaforaQuest2 = selecao;
                    valorTotalMetafora += parcialMetaforaQuest2;
                    break;
                case 23:
                    valorTotalMetafora -= parcialMetaforaQuest3;
                    parcialMetaforaQuest3 = selecao;
                    valorTotalMetafora += parcialMetaforaQuest3;
                    break;
                default:
                    break;
            }
            mTotalNumero1Proverbio.setText("" + valorTotalProverbio);
            mTotalNumero1Metafora.setText("" + valorTotalMetafora);
        }
    }

    public void onClickRadio(View v) {
        if(!isFinalizado) {
            if (v.getId() == mRadioProverbioQuest1.getChildAt(0).getId()
                    || v.getId() == mRadioProverbioQuest1.getChildAt(1).getId()
                    || v.getId() == mRadioProverbioQuest1.getChildAt(2).getId())
                somaValoresTotais(11, (int) onFrequenciaRadioButtonClicked(mRadioProverbioQuest1, false));

            else if (v.getId() == mRadioProverbioQuest2.getChildAt(0).getId()
                    || v.getId() == mRadioProverbioQuest2.getChildAt(1).getId()
                    || v.getId() == mRadioProverbioQuest2.getChildAt(2).getId())
                somaValoresTotais(12, (int) onFrequenciaRadioButtonClicked(mRadioProverbioQuest2, false));

            else if (v.getId() == mRadioProverbioQuest3.getChildAt(0).getId()
                    || v.getId() == mRadioProverbioQuest3.getChildAt(1).getId()
                    || v.getId() == mRadioProverbioQuest3.getChildAt(2).getId())
                somaValoresTotais(13, (int) onFrequenciaRadioButtonClicked(mRadioProverbioQuest3, false));

            else if (v.getId() == mRadioMetaforaQuest1.getChildAt(0).getId()
                    || v.getId() == mRadioMetaforaQuest1.getChildAt(1).getId())
                somaValoresTotais(21, (int) onFrequenciaRadioButtonClicked(mRadioMetaforaQuest1, true));

            else if (v.getId() == mRadioMetaforaQuest2.getChildAt(0).getId()
                    || v.getId() == mRadioMetaforaQuest2.getChildAt(1).getId())
                somaValoresTotais(22, (int) onFrequenciaRadioButtonClicked(mRadioMetaforaQuest2, true));

            else if (v.getId() == mRadioMetaforaQuest3.getChildAt(0).getId()
                    || v.getId() == mRadioMetaforaQuest3.getChildAt(1).getId())
                somaValoresTotais(23, (int) onFrequenciaRadioButtonClicked(mRadioMetaforaQuest3, true));
        }
    }

    public void autoComplete(Participante participante) {
        int[] aux = {11,12,13,21,22,23};
        for(int i=0; i<aux.length; i++) {
            switch (aux[i]) {
                case 11:
                    mRadioProverbioQuest1.check(mRadioProverbioQuest1.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioProverbioQuest1, false));
                    break;
                case 12:
                    mRadioProverbioQuest2.check(mRadioProverbioQuest2.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioProverbioQuest2, false));
                    break;
                case 13:
                    mRadioProverbioQuest3.check(mRadioProverbioQuest3.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioProverbioQuest3, false));
                    break;
                case 21:
                    mRadioMetaforaQuest1.check(mRadioMetaforaQuest1.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioMetaforaQuest1, true));
                    break;
                case 22:
                    mRadioMetaforaQuest2.check(mRadioMetaforaQuest2.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioMetaforaQuest2, true));
                    break;
                case 23:
                    mRadioMetaforaQuest3.check(mRadioMetaforaQuest3.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioMetaforaQuest3, true));
                    break;
                default: break;
            }
        }
    }

    public void armazenaStringsEditText(){
        //Proverbio
        verificadoresEditText.put("mEditTextProverbio1", mEditTextProverbio1.getText().toString());
        verificadoresEditText.put("mEditTextProverbioExplicacao1", mEditTextProverbioExplicacao1.getText().toString());

        verificadoresEditText.put("mEditTextProverbio2", mEditTextProverbio2.getText().toString());
        verificadoresEditText.put("mEditTextProverbioExplicacao2", mEditTextProverbioExplicacao2.getText().toString());

        verificadoresEditText.put("mEditTextProverbio3", mEditTextProverbio3.getText().toString());
        verificadoresEditText.put("mEditTextProverbioExplicacao3", mEditTextProverbioExplicacao3.getText().toString());

        //Metafora
        verificadoresEditText.put("mEditTextMetafora1", mEditTextMetafora1.getText().toString());
        verificadoresEditText.put("mEditTextMetafora2", mEditTextMetafora2.getText().toString());
        verificadoresEditText.put("mEditTextMetafora3", mEditTextMetafora3.getText().toString());
    }

    public void autoCompleteEditText(){
        //Proverbio
        mEditTextProverbio1.setText(verificadoresEditText.get("mEditTextProverbio1"));
        mEditTextProverbioExplicacao1.setText(verificadoresEditText.get("mEditTextProverbioExplicacao1"));

        mEditTextProverbio2.setText(verificadoresEditText.get("mEditTextProverbio2"));
        mEditTextProverbioExplicacao2.setText(verificadoresEditText.get("mEditTextProverbioExplicacao2"));

        mEditTextProverbio3.setText(verificadoresEditText.get("mEditTextProverbio3"));
        mEditTextProverbioExplicacao3.setText(verificadoresEditText.get("mEditTextProverbioExplicacao3"));

        //Metafora
        mEditTextMetafora1.setText(verificadoresEditText.get("mEditTextMetafora1"));
        mEditTextMetafora2.setText(verificadoresEditText.get("mEditTextMetafora2"));
        mEditTextMetafora3.setText(verificadoresEditText.get("mEditTextMetafora3"));
    }

    public void alteraDadosFirebase(Participante participante) {
        ConhecimentoSemanticoObject conhecimentoSemanticoObject;
        if(participante.getConhecimentoSemanticoObject() == null)
            conhecimentoSemanticoObject= new ConhecimentoSemanticoObject();
        else
            conhecimentoSemanticoObject = participante.getConhecimentoSemanticoObject();

        conhecimentoSemanticoObject.setVerificadores(verificadores);
        conhecimentoSemanticoObject.setVerificadoresEditText(verificadoresEditText);
        conhecimentoSemanticoObject.setValorTotalProverbio(valorTotalProverbio);
        conhecimentoSemanticoObject.setValorTotalMetafora(valorTotalMetafora);
        participante.setConhecimentoSemanticoObject(conhecimentoSemanticoObject);

        atualizaPorcentagem(participante);

        FirebaseDatabase mFirebaseDatabase;
        final DatabaseReference mParticipanteDatabaseReference;

        //Cria o caminho que garantirá o acesso somente aos participantes do usuário logado
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        mParticipanteDatabaseReference = mFirebaseDatabase.getReference().child("users").child(auth.getCurrentUser().getUid()).child("participantes");

        //Criar uma váriavel final estava criando um loop no onDataChange
        //final Participante partAux = participante;
        mParticipanteDatabaseReference.child(participante.getCpf()).setValue(participante);
    }

    public void atualizaPorcentagem(Participante participante) {
        int numeroDeVerf = 1 + participante.getConhecimentoSemanticoObject().getVerificadoresEditText().size();
        int numeroDeVerfConcluidos = 0;

        if(participante.getConhecimentoSemanticoObject().getVerificadores() != null) {
            numeroDeVerfConcluidos += 1;
        }
        if(participante.getConhecimentoSemanticoObject().getVerificadoresEditText() != null) {
            if(!participante.getConhecimentoSemanticoObject().getVerificadoresEditText().get("mEditTextProverbio1").equals(""))
                numeroDeVerfConcluidos += 1;
            if(!participante.getConhecimentoSemanticoObject().getVerificadoresEditText().get("mEditTextProverbioExplicacao1").equals(""))
                numeroDeVerfConcluidos += 1;
            if(!participante.getConhecimentoSemanticoObject().getVerificadoresEditText().get("mEditTextProverbio2").equals(""))
                numeroDeVerfConcluidos += 1;
            if(!participante.getConhecimentoSemanticoObject().getVerificadoresEditText().get("mEditTextProverbioExplicacao2").equals(""))
                numeroDeVerfConcluidos += 1;
            if(!participante.getConhecimentoSemanticoObject().getVerificadoresEditText().get("mEditTextProverbio2").equals(""))
                numeroDeVerfConcluidos += 1;
            if(!participante.getConhecimentoSemanticoObject().getVerificadoresEditText().get("mEditTextProverbioExplicacao2").equals(""))
                numeroDeVerfConcluidos += 1;

            if(!participante.getConhecimentoSemanticoObject().getVerificadoresEditText().get("mEditTextMetafora1").equals(""))
                numeroDeVerfConcluidos += 1;
            if(!participante.getConhecimentoSemanticoObject().getVerificadoresEditText().get("mEditTextMetafora2").equals(""))
                numeroDeVerfConcluidos += 1;
            if(!participante.getConhecimentoSemanticoObject().getVerificadoresEditText().get("mEditTextMetafora3").equals(""))
                numeroDeVerfConcluidos += 1;
        }

        int porcentagem = (100*numeroDeVerfConcluidos)/numeroDeVerf;

        participante.getConhecimentoSemanticoObject().setPorcentagem(porcentagem);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            Intent intentFromList = getIntent();
            if (intentFromList != null) {
                final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);
                Intent intent = new Intent(this, BaleLobbyActivity.class);
                intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                startActivity(intent);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
