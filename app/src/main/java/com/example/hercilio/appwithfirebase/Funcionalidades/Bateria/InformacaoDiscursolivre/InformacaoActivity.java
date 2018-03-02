package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.InformacaoDiscursolivre;

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
import com.example.hercilio.appwithfirebase.Objetos.InformacaoDiscursoLivreObject;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hercilio on 15/02/2018.
 */

public class InformacaoActivity extends AppCompatActivity {

    //Label 1:
    private RadioGroup mRadioLabel1Quest1, mRadioLabel1Quest2, mRadioLabel1Quest3, mRadioLabel1Quest4;
    //Laber 2:
    private RadioGroup mRadioLabel2Quest1, mRadioLabel2Quest2, mRadioLabel2Quest3, mRadioLabel2Quest4, mRadioLabel2Quest5, mRadioLabel2Quest6;

    //Check label 1:
    private int checkedRadioLabel1Quest1, checkedRadioLabel1Quest2, checkedRadioLabel1Quest3, checkedRadioLabel1Quest4;
    //Check label 2:
    private int checkedRadioLabel2Quest1, checkedRadioLabel2Quest2, checkedRadioLabel2Quest3, checkedRadioLabel2Quest4, checkedRadioLabel2Quest5, checkedRadioLabel2Quest6;

    //Variaveis dos valores totais:
    private int valorTotal;
    private int valorLabel1Quest1, valorLabel1Quest2, valorLabel1Quest3, valorLabel1Quest4;

    //Dicionario que armazena os radiobuttons selecionados:
    private Map<String, Integer> verificadores = new HashMap<>();

    //Total text view:
    private TextView mTotal;

    //Botão validador caso não haja o pressionamento de algum radio button
    private boolean validaRadioButtons;

    //Botao continuar:
    private Button btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacao);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //Label 1:
        mRadioLabel1Quest1 = (RadioGroup) findViewById(R.id.radio_informacao_label1_quest1);
        mRadioLabel1Quest2 = (RadioGroup) findViewById(R.id.radio_informacao_label1_quest2);
        mRadioLabel1Quest3 = (RadioGroup) findViewById(R.id.radio_informacao_label1_quest3);
        mRadioLabel1Quest4 = (RadioGroup) findViewById(R.id.radio_informacao_label1_quest4);

        //Label 2:
        mRadioLabel2Quest1 = (RadioGroup) findViewById(R.id.radio_informacao_label2_quest1);
        mRadioLabel2Quest2 = (RadioGroup) findViewById(R.id.radio_informacao_label2_quest2);
        mRadioLabel2Quest3 = (RadioGroup) findViewById(R.id.radio_informacao_label2_quest3);
        mRadioLabel2Quest4 = (RadioGroup) findViewById(R.id.radio_informacao_label2_quest4);
        mRadioLabel2Quest5 = (RadioGroup) findViewById(R.id.radio_informacao_label2_quest5);
        mRadioLabel2Quest6 = (RadioGroup) findViewById(R.id.radio_informacao_label2_quest6);

        //Totais:
        mTotal = (TextView) findViewById(R.id.informacao_total_number1);

        //Botao continuar:
        btnContinuar = (Button) findViewById(R.id.btn_continuar_informacao_btn);


        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);

            if (participante.getInfDiscNarrObject() != null && participante.getInfDiscNarrObject().getInformacao() != null) {
                verificadores = participante.getInfDiscNarrObject().getInformacao();
                autoComplete();
            }

            btnContinuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    registrar(participante);
                    if(!validaRadioButtons) {
                        Intent intent = new Intent(getBaseContext(), InformacaoDiscursolivreLobbyActivity.class);
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

        //Label 1:
        checkedRadioLabel1Quest1 = (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest1, false);
        if(checkedRadioLabel1Quest1 == -1)
            verificaValidade.add(11);
        armazenaDadoNoDicionario(11, checkedRadioLabel1Quest1);

        checkedRadioLabel1Quest2 = (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest2, false);
        if(checkedRadioLabel1Quest2 == -1)
            verificaValidade.add(12);
        armazenaDadoNoDicionario(12, checkedRadioLabel1Quest2);

        checkedRadioLabel1Quest3 = (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest3, false);
        if(checkedRadioLabel1Quest3 == -1)
            verificaValidade.add(13);
        armazenaDadoNoDicionario(13, checkedRadioLabel1Quest3);

        checkedRadioLabel1Quest4 = (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest4, false);
        if(checkedRadioLabel1Quest4 == -1)
            verificaValidade.add(14);
        armazenaDadoNoDicionario(14, checkedRadioLabel1Quest4);

        //Label2:
        checkedRadioLabel2Quest1 = (int) onFrequenciaRadioButtonClicked(mRadioLabel2Quest1, true);
        if(checkedRadioLabel2Quest1 == -1)
            verificaValidade.add(21);
        armazenaDadoNoDicionario(21, checkedRadioLabel2Quest1);

        checkedRadioLabel2Quest2 = (int) onFrequenciaRadioButtonClicked(mRadioLabel2Quest2, true);
        if(checkedRadioLabel2Quest2 == -1)
            verificaValidade.add(22);
        armazenaDadoNoDicionario(22, checkedRadioLabel2Quest2);

        checkedRadioLabel2Quest3 = (int) onFrequenciaRadioButtonClicked(mRadioLabel2Quest3, true);
        if(checkedRadioLabel2Quest3 == -1)
            verificaValidade.add(23);
        armazenaDadoNoDicionario(23, checkedRadioLabel2Quest3);

        checkedRadioLabel2Quest4 = (int) onFrequenciaRadioButtonClicked(mRadioLabel2Quest4, true);
        if(checkedRadioLabel2Quest4 == -1)
            verificaValidade.add(24);
        armazenaDadoNoDicionario(24, checkedRadioLabel2Quest4);

        checkedRadioLabel2Quest5 = (int) onFrequenciaRadioButtonClicked(mRadioLabel2Quest5, true);
        if(checkedRadioLabel2Quest5 == -1)
            verificaValidade.add(25);
        armazenaDadoNoDicionario(25, checkedRadioLabel2Quest5);

        checkedRadioLabel2Quest6 = (int) onFrequenciaRadioButtonClicked(mRadioLabel2Quest6, true);
        if(checkedRadioLabel2Quest6 == -1)
            verificaValidade.add(26);
        armazenaDadoNoDicionario(26, checkedRadioLabel2Quest6);

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
                if (checkedFrequencia.equals(getResources().getString(R.string.informacao_sim))) {
                    avaliacao = 0;
                } else if (checkedFrequencia.equals(getResources().getString(R.string.informacao_nao))) {
                    avaliacao = 1;
                }
            } else {
                if (checkedFrequencia.equals(getResources().getString(R.string.informacao_sim))) {
                    avaliacao = 0;
                } else if (checkedFrequencia.equals(getResources().getString(R.string.informacao_semelhante))) {
                    avaliacao = 1;
                } else if (checkedFrequencia.equals(getResources().getString(R.string.informacao_nao))) {
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
                    valorTotal -= valorLabel1Quest1;
                    valorLabel1Quest1 = selecao;
                    valorTotal += valorLabel1Quest1;
                    break;
                case 12:
                    valorTotal -= valorLabel1Quest2;
                    valorLabel1Quest2 = selecao;
                    valorTotal += valorLabel1Quest2;
                    break;
                case 13:
                    valorTotal -= valorLabel1Quest3;
                    valorLabel1Quest3 = selecao;
                    valorTotal += valorLabel1Quest3;
                    break;
                case 14:
                    valorTotal -= valorLabel1Quest4;
                    valorLabel1Quest4 = selecao;
                    valorTotal += valorLabel1Quest4;
                    break;
                default:
                    break;
            }
            mTotal.setText("" + valorTotal);
        }
    }

    public void onClickRadio(View v) {
        //Label 1:
        if(v.getId() == mRadioLabel1Quest1.getChildAt(0).getId()
                || v.getId() == mRadioLabel1Quest1.getChildAt(1).getId()
                || v.getId() == mRadioLabel1Quest1.getChildAt(2).getId())
            somaValoresTotais(11, (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest1,false));
        else if(v.getId() == mRadioLabel1Quest2.getChildAt(0).getId()
                || v.getId() == mRadioLabel1Quest2.getChildAt(1).getId()
                || v.getId() == mRadioLabel1Quest2.getChildAt(2).getId())
            somaValoresTotais(12, (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest2,false));
        else if(v.getId() == mRadioLabel1Quest3.getChildAt(0).getId()
                || v.getId() == mRadioLabel1Quest3.getChildAt(1).getId()
                || v.getId() == mRadioLabel1Quest3.getChildAt(2).getId())
            somaValoresTotais(13, (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest3,false));
        else if(v.getId() == mRadioLabel1Quest4.getChildAt(0).getId()
                || v.getId() == mRadioLabel1Quest4.getChildAt(1).getId()
                || v.getId() == mRadioLabel1Quest4.getChildAt(2).getId())
            somaValoresTotais(14, (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest4,false));
    }

    public void alteraDadosFirebase(Participante participante) {
        if(participante.getInfDiscNarrObject() == null) {
            InformacaoDiscursoLivreObject infDiscNarrObj = new InformacaoDiscursoLivreObject();
            infDiscNarrObj.atualizaInformacao(verificadores);
            infDiscNarrObj.setValorTotalInformacao(valorTotal);
            participante.setInfDiscNarrObject(infDiscNarrObj);
        } else {
            InformacaoDiscursoLivreObject aux = participante.getInfDiscNarrObject();
            aux.setValorTotalInformacao(valorTotal);
            aux.atualizaInformacao(verificadores);
            participante.setInfDiscNarrObject(aux);
        }

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

    public void autoComplete() {
        int[] aux = {11,12,13,14,21,22,23,24,25,26};
        for(int i=0; i<aux.length; i++) {
            switch (aux[i]) {
                case 11:
                    mRadioLabel1Quest1.check(mRadioLabel1Quest1.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest1, false));
                    break;
                case 12:
                    mRadioLabel1Quest2.check(mRadioLabel1Quest2.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest2, false));
                    break;
                case 13:
                    mRadioLabel1Quest3.check(mRadioLabel1Quest3.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest3, false));
                    break;
                case 14:
                    mRadioLabel1Quest4.check(mRadioLabel1Quest4.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest4, false));
                    break;
                case 21:
                    mRadioLabel2Quest1.check(mRadioLabel2Quest1.getChildAt(verificadores.get(""+aux[i])).getId());
                    break;
                case 22:
                    mRadioLabel2Quest2.check(mRadioLabel2Quest2.getChildAt(verificadores.get(""+aux[i])).getId());
                    break;
                case 23:
                    mRadioLabel2Quest3.check(mRadioLabel2Quest3.getChildAt(verificadores.get(""+aux[i])).getId());
                    break;
                case 24:
                    mRadioLabel2Quest4.check(mRadioLabel2Quest4.getChildAt(verificadores.get(""+aux[i])).getId());
                    break;
                case 25:
                    mRadioLabel2Quest5.check(mRadioLabel2Quest5.getChildAt(verificadores.get(""+aux[i])).getId());
                    break;
                case 26:
                    mRadioLabel2Quest6.check(mRadioLabel2Quest6.getChildAt(verificadores.get(""+aux[i])).getId());
                    break;
                default: break;
            }
        }
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
