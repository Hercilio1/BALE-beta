package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Nomeacao;

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
import com.example.hercilio.appwithfirebase.Objetos.NomeacaoObject;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hercilio on 27/02/2018.
 */

public class ImagemExpandidaNomeacaoActivity extends AppCompatActivity {

    public static final String IMAGEM_EXPANDIDA = "Integer[]";

    private Button mBtnRegistrar;
    private EditText mEditText;
    private Map<String, String> verificadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nomeacao_zoom);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mBtnRegistrar = (Button) findViewById(R.id.imagem_expandida_nomeacao_btn);
        mEditText = (EditText) findViewById(R.id.imagem_expandida_nomeacao_et);
        verificadores = new HashMap<>();

        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Integer[] ref = (Integer[]) intentFromList.getSerializableExtra(IMAGEM_EXPANDIDA);
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);

            ImageView imagem = (ImageView) findViewById(R.id.imagem_expandida_nomeacao_img);
            imagem.setImageResource(ref[0]);

            mBtnRegistrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(participante.getNomeacaoObject() == null) {
                        verificadores.put(""+ref[0], mEditText.getText().toString());
                        NomeacaoObject nomeacaoObject = new NomeacaoObject();
                        nomeacaoObject.setVerificadores(verificadores);
                        participante.setNomeacaoObject(nomeacaoObject);
                    } else {
                        NomeacaoObject nomeacaoObject = participante.getNomeacaoObject();
                        verificadores = nomeacaoObject.getVerificadores();
                        verificadores.put(""+ref[0], mEditText.getText().toString());
                        nomeacaoObject.setVerificadores(verificadores);
                        participante.setNomeacaoObject(nomeacaoObject);
                    }

                    Intent intent = new Intent(getBaseContext(),  NomeacaoActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    startActivity(intent);
                }
            });

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

