package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Narrativa;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.hercilio.appwithfirebase.R;

/**
 * Created by Hercilio on 27/02/2018.
 */

public class ImagemExpandidaNarrativaActivity extends AppCompatActivity {

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
        imagem.setImageResource(R.drawable.narrativa_historia_cachorro);
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

