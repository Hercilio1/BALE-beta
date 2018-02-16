package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoFrases;

import android.animation.Animator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Objetos.CompreensaoFrasesObject;
import com.example.hercilio.appwithfirebase.Objetos.CompreensaoFrasesRadioObject;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.Objetos.Perguntas;
import com.example.hercilio.appwithfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Hercilio on 31/01/2018.
 */

public class CompreensaoFrasesRadioActivity extends AppCompatActivity {

    private RadioGroup mRadioGroupCompreensaoFrases;
    private int checkedRadioGroupCompreensaoFrases;
    //Botão validador caso não haja o pressionamento de algum radio button
    private boolean validaRadioButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compreensao_frase_radio);

        Button btnContinuar = (Button) findViewById(R.id.btn_continuarRadio);
        mRadioGroupCompreensaoFrases = (RadioGroup) findViewById(R.id.radioGroupRadio);


        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);

            if(participante.getCompFrasesRadioObject() != null) {
                autoComplete(participante);
            }

            btnContinuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                registrar(participante);
                    if(!validaRadioButtons) {
                        Intent intent = new Intent(getBaseContext(), CompreensaoFraseRelogioActivity.class);
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

    public void autoComplete(Participante participante) {
        if(participante.getCompFrasesRadioObject().getValorFinal() == 1)
            mRadioGroupCompreensaoFrases.check(mRadioGroupCompreensaoFrases.getChildAt(0).getId());
        else
            mRadioGroupCompreensaoFrases.check(mRadioGroupCompreensaoFrases.getChildAt(1).getId());
    }

    public long onFrequenciaRadioButtonClicked(RadioGroup mRadio) {

        long avaliacao = -1;
        String checkedFrequencia;
        int selectedRadioId = mRadio.getCheckedRadioButtonId();
        if (selectedRadioId != -1) {
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioId);
            checkedFrequencia = selectedRadioButton.getText().toString();
            if (checkedFrequencia.equals(getResources().getString(R.string.comp_frases_radio_nao))) {
                avaliacao = 0;
            } else {
                if (checkedFrequencia.equals(getResources().getString(R.string.comp_frases_radio_sim))) {
                    avaliacao = 1;
                }
            }
        }
        return avaliacao;
    }

    public void traduzRadioButtonSelecionado(Integer selecao, Participante participante) {
        if (selecao != -1) {
            if (participante.getCompFrasesRadioObject() != null) {
                switch (selecao) {
                    case 0:
                        participante.getCompFrasesRadioObject().setValorFinal(0);
                        break;
                    case 1:
                        participante.getCompFrasesRadioObject().setValorFinal(1);
                        break;
                    default:
                        break;
                }
            } else {
                switch (selecao) {
                    case 0:
                        participante.setCompFrasesRadioObject();
                        participante.getCompFrasesRadioObject().setValorFinal(0);
                        break;
                    case 1:
                        participante.setCompFrasesRadioObject();
                        participante.getCompFrasesRadioObject().setValorFinal(1);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void alteraDadosFirebase(final Participante participante) {

        FirebaseDatabase mFirebaseDatabase;
        final DatabaseReference mParticipanteDatabaseReference;

        //Cria o caminho que garantirá o acesso somente aos participantes do usuário logado
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        mParticipanteDatabaseReference = mFirebaseDatabase.getReference().child("users").child(auth.getCurrentUser().getUid()).child("participantes");

//        Criar uma váriavel final estava criando um loop no onDataChange
//        final Participante partAux = participante;

        mParticipanteDatabaseReference.child(participante.getCpf()).setValue(participante);

    }
}
