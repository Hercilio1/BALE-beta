package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Nomeacao;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.MemoriaEpisodica.GridAdapter;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.MemoriaEpisodica.MemoriaEpisodicaLobbyActivity;
import com.example.hercilio.appwithfirebase.Objetos.MemoriaEpisodicaObject;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hercilio on 05/03/2018.
 */

public class NomeacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nomeacao);

        NomeacaoAdapter nomeacaoAdapter = new NomeacaoAdapter(this);

        GridView gridview = (GridView) findViewById(R.id.gridview_nomeacao);
        nomeacaoAdapter.setListener(selecionarItemView);
        gridview.setAdapter(nomeacaoAdapter);


    }

    final OnListFragmentInteractionListener selecionarItemView = new OnListFragmentInteractionListener() {
        @Override
        public void onListFragmentInteraction(ImageButton item, boolean ref) {
//            if(ref)
//                verificaTipo(item);
//            else {
//                autoComplete(item);
//            }
        }
    };

}
