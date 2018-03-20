package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Nomeacao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Hercilio on 05/03/2018.
 */

public class NomeacaoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Button mBtnContinuar;

    //Variável que auxilia na verificacao se o teste já está finalizado
    private boolean isFinalizado;

    private String[] strings = {"a1_chave_de_fenda", "a2_esquilo", "a3_regador", "a4_rinocerounte"
            , "b1_chaved_e_fenda", "b2_pinguim", "b3_escova_de_dentes", "b4_aguia"
            , "c1_serrote", "c2_avestruz", "c3_alicate", "c4_canguru"
            , "d1_lixeira", "d2_urso", "d3_pente", "d4_pavao"
            , "e1_pincel", "e2_cisne", "e3_sofa", "e4_jacare"
            , "f1_escova", "f2_pera", "f3_tesoura", "f4_milho"
            , "g1_machado", "g2_sapo", "g3_motocicleta", "g4_abacaxi"
            , "h1_carta", "h2_tartaruga", "h3_helicoptero", "h4_coruja"
            , "i1_mala", "i2_elefante", "i3_barril", "i4_tigre"
            , "j1_gravata", "j2_maca", "j3_vela", "j4_zebra"
            , "k1_guarda_chuva", "k2_galinha", "k3_copo", "k4_tomate"
            , "l1_piano", "l2_pato", "l3_caminhao", "l4_cachorro"
            , "m1_sino", "m2_banana", "m3_galo", "m4_trem"
            , "n1_cesta", "n2_cavalo", "n3_aviao", "n4_vaca"
            , "o1_onibus", "o2_rato", "o3_chave", "o4_peixe"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nomeacao);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //Rotina da recyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_nomeacao);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        //Rotina do adapter
        NomeacaoAdapter nomeacaoAdapter = new NomeacaoAdapter(this);
        nomeacaoAdapter.setListener(selecionarItemView);
        mRecyclerView.setAdapter(nomeacaoAdapter);

        mBtnContinuar = (Button) findViewById(R.id.button_nomeacao);


        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);
            isFinalizado = participante.isFinalizado();

            mBtnContinuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!isFinalizado)
                        registrar(participante);
                    Intent intent = new Intent(getBaseContext(),  BaleLobbyActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    startActivity(intent);
                }
            });
        }

    }

    final OnListFragmentInteractionListener selecionarItemView = new OnListFragmentInteractionListener() {
        @Override
        public void onListFragmentInteraction(ImageButton item, TextView key) {
            Intent intentFromList = getIntent();
            if (intentFromList != null) {
                final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);
                Integer[] ref = new Integer[1];
                String[] refKey = new String[1];
                ref[0] = (Integer) item.getTag();
                refKey[0] = key.getTag().toString();

                Intent intent = new Intent(getBaseContext(), ImagemExpandidaNomeacaoActivity.class);
                intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                intent.putExtra(ImagemExpandidaNomeacaoActivity.IMAGEM_EXPANDIDA, ref);
                intent.putExtra(ImagemExpandidaNomeacaoActivity.KEY_IMAGEM_EXPANDIDA, refKey);
                startActivity(intent);
            }

        }

    };

    public void registrar(Participante participante) {
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
        int numeroDeVerf = 60;
        int numeroDeVerfConcluidos = 0;

        for(String x : strings) {
            if(participante.getNomeacaoObject().getVerificadores().containsKey(x))
                numeroDeVerfConcluidos += 1;
        }

        int porcentagem = (100*numeroDeVerfConcluidos)/numeroDeVerf;

        participante.getNomeacaoObject().setPorcentagem(porcentagem);
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
