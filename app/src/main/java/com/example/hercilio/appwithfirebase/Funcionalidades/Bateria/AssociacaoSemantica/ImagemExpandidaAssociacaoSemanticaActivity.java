package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.AssociacaoSemantica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Nomeacao.NomeacaoActivity;
import com.example.hercilio.appwithfirebase.Objetos.NomeacaoObject;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hercilio on 27/02/2018.
 */

public class ImagemExpandidaAssociacaoSemanticaActivity extends AppCompatActivity {

    public static final String IMAGEM_EXPANDIDA = "Integer[]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagem_expandida);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Integer[] ref = (Integer[]) intentFromList.getSerializableExtra(IMAGEM_EXPANDIDA);

            ImageView imagem = (ImageView) findViewById(R.id.imagem_expandida_mem_ep);
            imagem.setImageResource(ref[0]);

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

