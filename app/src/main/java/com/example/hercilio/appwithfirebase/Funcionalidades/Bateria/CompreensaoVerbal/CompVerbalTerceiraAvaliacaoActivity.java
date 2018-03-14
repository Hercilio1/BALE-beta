package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoVerbal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Objetos.CompreensaoVerbalObject;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hercilio on 09/02/2018.
 */

public class CompVerbalTerceiraAvaliacaoActivity extends AppCompatActivity {

    //Label 2:
    private RadioGroup mRadioPergunta1Label2, mRadioPergunta2Label2, mRadioPergunta3Label2;
    //Laber 3:
    private RadioGroup mRadioPergunta1Label3, mRadioPergunta2Label3, mRadioPergunta3Label3;

    //Check label 2:
    private int checkedRadioPergunta1Label2, checkedRadioPergunta2Label2, checkedRadioPergunta3Label2;
    //Check label 3:
    private int checkedRadioPergunta1Label3, checkedRadioPergunta2Label3, checkedRadioPergunta3Label3;

    //Variaveis dos valores totais:
    private int valorTotalLabel2, valorTotalLabel3;

    //Parciais Label 2:
    private int pergunta1Label2, pergunta2Label2, pergunta3Label2;
    //Parciais Label 3:
    private int pergunta1Label3, pergunta2Label3, pergunta3Label3;

    //Dicionario que armazena os radiobuttons selecionados:
    private Map<String, Integer> verificadores = new HashMap<>();

    //Totais:
    private TextView mTotalNumero1Label2, mTotalNumero1Label3;

    //Botão validador caso não haja o pressionamento de algum radio button
    private boolean validaRadioButtons;

    //Botao continuar:
    private Button btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_verbal_terceira_avaliacao);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //Label 2:
        mRadioPergunta1Label2 = (RadioGroup) findViewById(R.id.comp_verb3_radio_pergunta1_label2);
        mRadioPergunta2Label2 = (RadioGroup) findViewById(R.id.comp_verb3_radio_pergunta2_label2);
        mRadioPergunta3Label2 = (RadioGroup) findViewById(R.id.comp_verb3_radio_pergunta3_label2);

        //Label 3:
        mRadioPergunta1Label3 = (RadioGroup) findViewById(R.id.comp_verb3_radio_pergunta1_label3);
        mRadioPergunta2Label3 = (RadioGroup) findViewById(R.id.comp_verb3_radio_pergunta2_label3);
        mRadioPergunta3Label3 = (RadioGroup) findViewById(R.id.comp_verb3_radio_pergunta3_label3);

        //Totais:
        mTotalNumero1Label2 = (TextView) findViewById(R.id.comp_verb3_total_number1_label_2);
        mTotalNumero1Label3 = (TextView) findViewById(R.id.comp_verb3_total_number1_label_3);


        //Botao continuar:
        btnContinuar = (Button) findViewById(R.id.btn_continuar_comp_verbal_terceira_avaliacao_btn);


        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);

            if (participante.getCompVerbalObject() != null && participante.getCompVerbalObject().getTerceiraAvaliacao() != null) {
                verificadores = participante.getCompVerbalObject().getTerceiraAvaliacao();
                autoComplete();
            }

            btnContinuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                registrar(participante);
                if(!validaRadioButtons) {
                    Intent intent = new Intent(getBaseContext(), CompreensaoVerbalLobbyActivity.class);
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

    public void autoComplete() {
        int[] aux = {12,22,32,13,23,33};
        for(int i=0; i<aux.length; i++) {
            switch (aux[i]) {
                case 12:
                    mRadioPergunta1Label2.check(mRadioPergunta1Label2.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioPergunta1Label2));
                    break;
                case 22:
                    mRadioPergunta2Label2.check(mRadioPergunta2Label2.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioPergunta2Label2));
                    break;
                case 32:
                    mRadioPergunta3Label2.check(mRadioPergunta3Label2.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioPergunta3Label2));
                    break;
                case 13:
                    mRadioPergunta1Label3.check(mRadioPergunta1Label3.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioPergunta1Label3));
                    break;
                case 23:
                    mRadioPergunta2Label3.check(mRadioPergunta2Label3.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioPergunta2Label3));
                    break;
                case 33:
                    mRadioPergunta3Label3.check(mRadioPergunta3Label3.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioPergunta3Label3));
                    break;
                default: break;
            }
        }
    }

    public void registrar(Participante participante) {
        ArrayList<Integer> verificaValidade = new ArrayList<>();

        //Label 2:
        checkedRadioPergunta1Label2 = (int) onFrequenciaRadioButtonClicked(mRadioPergunta1Label2);
        if(checkedRadioPergunta1Label2 == -1)
            verificaValidade.add(12);
        armazenaDadoNoDicionario(12, checkedRadioPergunta1Label2);

        checkedRadioPergunta2Label2 = (int) onFrequenciaRadioButtonClicked(mRadioPergunta2Label2);
        if(checkedRadioPergunta2Label2 == -1)
            verificaValidade.add(22);
        armazenaDadoNoDicionario(22, checkedRadioPergunta2Label2);

        checkedRadioPergunta3Label2 = (int) onFrequenciaRadioButtonClicked(mRadioPergunta3Label2);
        if(checkedRadioPergunta3Label2 == -1)
            verificaValidade.add(32);
        armazenaDadoNoDicionario(32, checkedRadioPergunta3Label2);

        //Label 3:
        checkedRadioPergunta1Label3 = (int) onFrequenciaRadioButtonClicked(mRadioPergunta1Label3);
        if(checkedRadioPergunta1Label3 == -1)
            verificaValidade.add(13);
        armazenaDadoNoDicionario(13, checkedRadioPergunta1Label3);

        checkedRadioPergunta2Label3 = (int) onFrequenciaRadioButtonClicked(mRadioPergunta2Label3);
        if(checkedRadioPergunta2Label3 == -1)
            verificaValidade.add(23);
        armazenaDadoNoDicionario(23, checkedRadioPergunta2Label3);

        checkedRadioPergunta3Label3 = (int) onFrequenciaRadioButtonClicked(mRadioPergunta3Label3);
        if(checkedRadioPergunta3Label3 == -1)
            verificaValidade.add(33);
        armazenaDadoNoDicionario(33, checkedRadioPergunta3Label3);

        if(!verificaValidade.isEmpty()) {
            validaRadioButtons = true;
        } else if (participante != null) {
            validaRadioButtons = false;
            alteraDadosFirebase(participante);
        }

    }

    public long onFrequenciaRadioButtonClicked(RadioGroup mRadio) {

        long avaliacao = -1;
        String checkedFrequencia;
        int selectedRadioId = mRadio.getCheckedRadioButtonId();
        if (selectedRadioId != -1) {
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioId);
            checkedFrequencia = selectedRadioButton.getText().toString();
            if (checkedFrequencia.equals(getResources().getString(R.string.comp_verbal_terceira_avaliacao_sim))) {
                avaliacao = 0;
            } else if (checkedFrequencia.equals(getResources().getString(R.string.comp_verbal_terceira_avaliacao_intermediario))) {
                avaliacao = 1;
            } else if (checkedFrequencia.equals(getResources().getString(R.string.comp_verbal_terceira_avaliacao_não))) {
                avaliacao = 2;
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
                case 12:
                    valorTotalLabel2 -= pergunta1Label2;
                    pergunta1Label2 = selecao;
                    valorTotalLabel2 += pergunta1Label2;
                    break;
                case 22:
                    valorTotalLabel2 -= pergunta2Label2;
                    pergunta2Label2 = selecao;
                    valorTotalLabel2 += pergunta2Label2;
                    break;
                case 32:
                    valorTotalLabel2 -= pergunta3Label2;
                    pergunta3Label2 = selecao;
                    valorTotalLabel2 += pergunta3Label2;
                case 13:
                    valorTotalLabel3 -= pergunta1Label3;
                    pergunta1Label3 = selecao;
                    valorTotalLabel3 += pergunta1Label3;
                    break;
                case 23:
                    valorTotalLabel3 -= pergunta2Label3;
                    pergunta2Label3 = selecao;
                    valorTotalLabel3 += pergunta2Label3;
                    break;
                case 33:
                    valorTotalLabel3 -= pergunta3Label3;
                    pergunta3Label3 = selecao;
                    valorTotalLabel3 += pergunta3Label3;
                    break;
                default:
                    break;
            }
            mTotalNumero1Label2.setText("" + valorTotalLabel2);
            mTotalNumero1Label3.setText("" + valorTotalLabel3);
        }
    }

    public void onClickRadio(View v) {
        if(v.getId() == mRadioPergunta1Label2.getChildAt(0).getId()
                || v.getId() == mRadioPergunta1Label2.getChildAt(1).getId()
                || v.getId() == mRadioPergunta1Label2.getChildAt(2).getId())
            somaValoresTotais(12, (int) onFrequenciaRadioButtonClicked(mRadioPergunta1Label2));

        else if(v.getId() == mRadioPergunta2Label2.getChildAt(0).getId()
                || v.getId() == mRadioPergunta2Label2.getChildAt(1).getId()
                || v.getId() == mRadioPergunta2Label2.getChildAt(2).getId())
            somaValoresTotais(22, (int) onFrequenciaRadioButtonClicked(mRadioPergunta2Label2));

        else if(v.getId() == mRadioPergunta3Label2.getChildAt(0).getId()
                || v.getId() == mRadioPergunta3Label2.getChildAt(1).getId()
                || v.getId() == mRadioPergunta3Label2.getChildAt(2).getId())
            somaValoresTotais(32, (int) onFrequenciaRadioButtonClicked(mRadioPergunta3Label2));

        else if(v.getId() == mRadioPergunta1Label3.getChildAt(0).getId()
                || v.getId() == mRadioPergunta1Label3.getChildAt(1).getId()
                || v.getId() == mRadioPergunta1Label3.getChildAt(2).getId())
            somaValoresTotais(13, (int) onFrequenciaRadioButtonClicked(mRadioPergunta1Label3));

        else if(v.getId() == mRadioPergunta2Label3.getChildAt(0).getId()
                || v.getId() == mRadioPergunta2Label3.getChildAt(1).getId()
                || v.getId() == mRadioPergunta2Label3.getChildAt(2).getId())
            somaValoresTotais(23, (int) onFrequenciaRadioButtonClicked(mRadioPergunta2Label3));

        else if(v.getId() == mRadioPergunta3Label3.getChildAt(0).getId()
                || v.getId() == mRadioPergunta3Label3.getChildAt(1).getId()
                || v.getId() == mRadioPergunta3Label3.getChildAt(2).getId())
            somaValoresTotais(33, (int) onFrequenciaRadioButtonClicked(mRadioPergunta3Label3));
    }

    public void alteraDadosFirebase(Participante participante) {
        if(participante.getCompVerbalObject() == null) {
            CompreensaoVerbalObject compVerbalObj = new CompreensaoVerbalObject();
            compVerbalObj.atualizaTerceiraAvaliacao(verificadores);
            compVerbalObj.setAv3ValorTotalLabel2(valorTotalLabel2);
            compVerbalObj.setAv3ValorTotalLabel3(valorTotalLabel3);
            participante.setCompVerbalObject(compVerbalObj);
        } else {
            CompreensaoVerbalObject aux = participante.getCompVerbalObject();
            aux.setAv3ValorTotalLabel2(valorTotalLabel2);
            aux.setAv3ValorTotalLabel3(valorTotalLabel3);
            aux.atualizaTerceiraAvaliacao(verificadores);
            participante.setCompVerbalObject(aux);
        }

        new CompreensaoVerbalLobbyActivity().atualizaPorcentagem(participante);

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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            this.onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
