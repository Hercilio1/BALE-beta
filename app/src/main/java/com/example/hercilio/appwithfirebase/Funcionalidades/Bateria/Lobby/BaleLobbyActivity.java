package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hercilio.appwithfirebase.AdminActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.HabitosDeLeituraEscritra.HabitosLeituraEscritaActivity;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hercilio on 26/12/2017.
 */

public class BaleLobbyActivity extends AppCompatActivity {

    public static final String EXTRA_PARTICIPANTE = "participante";

    private MenuItem baleLobbyHomeBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bale_lobby);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(EXTRA_PARTICIPANTE);

            atualizaPorcentagem(participante);

            if (savedInstanceState == null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container_bale_lobby, BaleLobbyFragment.newInstance(participante), "BaleLobby")
                        .commit();
            }
        }
    }

    public void atualizaPorcentagem(Participante participante) {
        int numeroDeVerf = 11 * 100;
        int numeroDeVerfConcluidos = 0;

        if(participante.getHleObject() != null)
            numeroDeVerfConcluidos += participante.getHleObject().getPorcentagem();

        if(participante.getCompFrasesObject() != null)
            numeroDeVerfConcluidos += participante.getCompFrasesObject().getPorcentagem();

        if(participante.getMemEpObject() != null)
            numeroDeVerfConcluidos += participante.getMemEpObject().getPorcentagem();

        if(participante.getCompVerbalObject() != null)
            numeroDeVerfConcluidos += participante.getCompVerbalObject().getPorcentagem();

        if(participante.getInformacaoDiscLivreObject() != null)
            numeroDeVerfConcluidos += participante.getInformacaoDiscLivreObject().getPorcentagem();

        if(participante.getNarrativaObject() != null)
            numeroDeVerfConcluidos += participante.getNarrativaObject().getPorcentagem();

        if(participante.getFluenciaVerbalObject() != null)
            numeroDeVerfConcluidos += participante.getFluenciaVerbalObject().getPorcentagem();

        if(participante.getNomeacaoObject() != null)
            numeroDeVerfConcluidos += participante.getNomeacaoObject().getPorcentagem();

        if(participante.getDigitSpanObject() != null)
            numeroDeVerfConcluidos += participante.getDigitSpanObject().getPorcentagem();

        if(participante.getAssociacaoSemanticaObject() != null)
            numeroDeVerfConcluidos += participante.getAssociacaoSemanticaObject().getPorcentagem();

        if(participante.getConhecimentoSemanticoObject() != null)
            numeroDeVerfConcluidos += participante.getConhecimentoSemanticoObject().getPorcentagem();

        int porcentagem = (100*numeroDeVerfConcluidos)/numeroDeVerf;

        participante.setPorcentagem(porcentagem);

        FirebaseDatabase mFirebaseDatabase;
        final DatabaseReference mParticipanteDatabaseReference;

        //Cria o caminho que garantirá o acesso somente aos participantes do usuário logado
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        mParticipanteDatabaseReference = mFirebaseDatabase.getReference().child("users").child(auth.getCurrentUser().getUid()).child("participantes");

        mParticipanteDatabaseReference.child(participante.getCpf()).setValue(participante);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            Intent intent = new Intent(this, AdminActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
