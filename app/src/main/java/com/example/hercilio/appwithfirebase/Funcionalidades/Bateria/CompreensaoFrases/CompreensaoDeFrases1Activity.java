package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoFrases;

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

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Objetos.CompreensaoFrasesObject;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hercilio on 31/01/2018.
 */

public class CompreensaoDeFrases1Activity extends AppCompatActivity {

    private RadioGroup mRadioGroupCompreensaoFrases;
    private int checkedRadioGroupCompreensaoFrases;
    //Botão validador caso não haja o pressionamento de algum radio button
    private boolean validaRadioButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compreensao_frases_1);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Button btnContinuar = (Button) findViewById(R.id.btn_continuarFrase);
        mRadioGroupCompreensaoFrases = (RadioGroup) findViewById(R.id.radioGroupFrase);


        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);

            if(participante.getCompFrasesObject() != null && participante.getCompFrasesObject().getValorFinal1() >= 0) {
                autoComplete(participante.getCompFrasesObject());
            }

            btnContinuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!participante.isFinalizado())
                        registrar(participante);
                    if(!validaRadioButtons) {
                        Intent intent = new Intent(getBaseContext(), CompreensaoDeFrases2Activity.class);
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

    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), FraseExpandidaActivity.class);
        startActivity(intent);
    }

    public void registrar(Participante participante){
        boolean verificaValidade = false;
        checkedRadioGroupCompreensaoFrases = (int) onFrequenciaRadioButtonClicked(mRadioGroupCompreensaoFrases);
        if(checkedRadioGroupCompreensaoFrases == -1)
            verificaValidade = true;
        traduzRadioButtonSelecionado(checkedRadioGroupCompreensaoFrases, participante);

        if(verificaValidade) {
            validaRadioButtons = true;
        } else if (participante != null) {
            validaRadioButtons = false;
            alteraDadosFirebase(participante);
        }
    }

    public void autoComplete(CompreensaoFrasesObject compFrasesObj) {
        mRadioGroupCompreensaoFrases.check(mRadioGroupCompreensaoFrases.getChildAt(compFrasesObj.getValorFinal1()).getId());
    }

    public long onFrequenciaRadioButtonClicked(RadioGroup mRadio) {

        long avaliacao = -1;
        String checkedFrequencia;
        int selectedRadioId = mRadio.getCheckedRadioButtonId();
        if (selectedRadioId != -1) {
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioId);
            checkedFrequencia = selectedRadioButton.getText().toString();
            if (checkedFrequencia.equals(getResources().getString(R.string.comp_frases_nao_leu))) {
                avaliacao = 0;
            } else {
                if (checkedFrequencia.equals(getResources().getString(R.string.comp_frases_leu))) {
                    avaliacao = 1;
                } else {
                    if (checkedFrequencia.equals(getResources().getString(R.string.comp_frases_leu_executou))) {
                        avaliacao = 2;
                    }
                }
            }
        }
        return avaliacao;
    }

    public void traduzRadioButtonSelecionado(Integer selecao, Participante participante) {
        if (selecao != -1) {
            if (participante.getCompFrasesObject() != null) {
                switch (selecao) {
                    case 0:
                        participante.getCompFrasesObject().setValorFinal1(selecao);
                        break;
                    case 1:
                        participante.getCompFrasesObject().setValorFinal1(selecao);
                        break;
                    case 2:
                        participante.getCompFrasesObject().setValorFinal1(selecao);
                        break;
                    default:
                        break;
                }
            } else {
                CompreensaoFrasesObject compreensaoFrasesObject = new CompreensaoFrasesObject();
                switch (selecao) {
                    case 0:
                        compreensaoFrasesObject.setValorFinal1(selecao);
                        break;
                    case 1:
                        compreensaoFrasesObject.setValorFinal1(selecao);
                        break;
                    case 2:
                        compreensaoFrasesObject.setValorFinal1(selecao);
                        break;
                    default:
                        break;
                }
                participante.setCompFrasesObject(compreensaoFrasesObject);
            }
        }

    }

    public void alteraDadosFirebase(Participante participante) {

        atualizaPorcentagem(participante);

        FirebaseDatabase mFirebaseDatabase;
        final DatabaseReference mParticipanteDatabaseReference;
        ChildEventListener mChildEventListener;
        //Cria o caminho que garantirá o acesso somente aos participantes do usuário logado
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        mParticipanteDatabaseReference = mFirebaseDatabase.getReference().child("users").child(auth.getCurrentUser().getUid()).child("participantes");
        mParticipanteDatabaseReference.child(participante.getCpf()).setValue(participante);
    }

    public void atualizaPorcentagem(Participante participante) {
        int numeroDeVerf = 3;
        int numeroDeVerfConcluidos = 0;

        if(participante.getCompFrasesObject().getValorFinal1() >=0) {
            numeroDeVerfConcluidos += 1;
        }
        if(participante.getCompFrasesObject().getValorFinal2() >=0) {
            numeroDeVerfConcluidos += 1;
        }
        if(participante.getCompFrasesObject().getFotoRelogio() != null) {
            numeroDeVerfConcluidos += 1;
        }

        int porcentagem = (100*numeroDeVerfConcluidos)/numeroDeVerf;

        participante.getCompFrasesObject().setPorcentagem(porcentagem);
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
