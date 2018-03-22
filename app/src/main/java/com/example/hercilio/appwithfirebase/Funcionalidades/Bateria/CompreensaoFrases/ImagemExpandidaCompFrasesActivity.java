package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoFrases;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;

/**
 * Created by Hercilio on 27/02/2018.
 */

public class ImagemExpandidaCompFrasesActivity extends AppCompatActivity {

    public static final String IMAGEM_EXPANDIDA = "Integer[]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagem_expandida);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ImageView imagem = (ImageView) this.findViewById(R.id.imagem_expandida_mem_ep);
        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);
            Glide.with(imagem.getContext()).load(participante.getCompFrasesObject().getFotoRelogio()).into(imagem);
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

