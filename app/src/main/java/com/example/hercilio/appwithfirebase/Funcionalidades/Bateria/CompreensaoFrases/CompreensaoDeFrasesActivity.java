package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoFrases;

import android.animation.Animator;
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

public class CompreensaoDeFrasesActivity extends AppCompatActivity {

    private RadioGroup mRadioGroupCompreensaoFrases;
    private int checkedRadioGroupCompreensaoFrases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compreensao_frases);

        Button btnContinuar = (Button) findViewById(R.id.btn_continuarFrase);
        mRadioGroupCompreensaoFrases = (RadioGroup) findViewById(R.id.radioGroupFrase);


        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);

            if(participante.getCompFrasesObject() != null) {
                autoComplete(participante.getCompFrasesObject());
            }

            btnContinuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    registrar(participante);
                    Intent intent = new Intent(getBaseContext(), CompreensaoFrasesRadioActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    startActivity(intent);
                }
            });
        }

    }

    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), FraseExpandidaActivity.class);
        startActivity(intent);
    }

    public void registrar(Participante participante){
        checkedRadioGroupCompreensaoFrases = (int) onFrequenciaRadioButtonClicked(mRadioGroupCompreensaoFrases);
        traduzRadioButtonSelecionado(checkedRadioGroupCompreensaoFrases, participante);
        alteraDadosFirebase(participante);
    }

    public void autoComplete(CompreensaoFrasesObject compFrasesObj) {
        View y = mRadioGroupCompreensaoFrases.getChildAt(compFrasesObj.getValorFinal());
        int x = mRadioGroupCompreensaoFrases.getChildAt(compFrasesObj.getValorFinal()).getId();
        mRadioGroupCompreensaoFrases.check(mRadioGroupCompreensaoFrases.getChildAt(compFrasesObj.getValorFinal()).getId());
    }

    public long onFrequenciaRadioButtonClicked(RadioGroup mRadio) {

        long avaliacao = 2;
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
        if(participante.getCompFrasesObject() != null) {
            switch (selecao) {
                case 0:
                    participante.getCompFrasesObject().setValorFinal(selecao);
                    break;
                case 1:
                    participante.getCompFrasesObject().setValorFinal(selecao);
                    break;
                case 2:
                    participante.getCompFrasesObject().setValorFinal(selecao);
                    break;
                default:
                    break;
            }
        } else {
            switch (selecao) {
                case 0:
                    participante.setCompFrasesObject();
                    participante.getCompFrasesObject().setValorFinal(selecao);
                    break;
                case 1:
                    participante.setCompFrasesObject();
                    participante.getCompFrasesObject().setValorFinal(selecao);
                    break;
                case 2:
                    participante.setCompFrasesObject();
                    participante.getCompFrasesObject().setValorFinal(selecao);
                    break;
                default:
                    break;
            }
        }
    }

    public void alteraDadosFirebase(final Participante participante) {


        FirebaseDatabase mFirebaseDatabase;
        final DatabaseReference mParticipanteDatabaseReference;
        ChildEventListener mChildEventListener;
        //Cria o caminho que garantirá o acesso somente aos participantes do usuário logado
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        mParticipanteDatabaseReference = mFirebaseDatabase.getReference().child("users").child(auth.getCurrentUser().getUid()).child("participantes");

//        Criar uma váriavel final estava criando um loop no onDataChange
//        final Participante partAux = participante;

        mParticipanteDatabaseReference.child(participante.getCpf()).setValue(participante);
    }
}
