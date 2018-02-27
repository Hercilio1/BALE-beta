package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.MemoriaEpisodica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;

/**
 * Created by Hercilio on 27/02/2018.
 */

public class ImagemExpandidaActivity extends AppCompatActivity {

    public static final String IMAGEM_EXPANDIDA = "Integer[]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagem_expandida);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ImageView imagem = (ImageView) findViewById(R.id.imagem_expandida_mem_ep);

        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Integer[] indicador = (Integer[]) intentFromList.getSerializableExtra(IMAGEM_EXPANDIDA);
            switch (indicador[0]) {
                case 1:
                    imagem.setImageResource(R.drawable.mem_ep_fig_a);
                    break;
                case 2:
                    imagem.setImageResource(R.drawable.mem_ep_fig_b);
                    break;
                case 3:
                    imagem.setImageResource(R.drawable.mem_ep_fig_c);
                    break;
                case 4:
                    imagem.setImageResource(R.drawable.mem_ep_fig_d);
                    break;
                default:
                    break;
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

