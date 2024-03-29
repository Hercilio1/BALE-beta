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
    public static final String KEY_IMAGEM_EXPANDIDA = "String[]";

    private Button mBtnRegistrar;
    private EditText mEditText;
    private Map<String, String> verificadores;

    //Variável que auxilia na verificacao se o teste já está finalizado
    private boolean isFinalizado;

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


        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Integer[] ref = (Integer[]) intentFromList.getSerializableExtra(IMAGEM_EXPANDIDA);
            final String[] refKey = (String[]) intentFromList.getSerializableExtra(KEY_IMAGEM_EXPANDIDA);
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);
            isFinalizado = participante.isFinalizado();

            if(participante.getNomeacaoObject() != null
                    && participante.getNomeacaoObject().getVerificadores() != null)
                verificadores = participante.getNomeacaoObject().getVerificadores();
            else
                verificadores = new HashMap<>();

            ImageView imagem = (ImageView) findViewById(R.id.imagem_expandida_nomeacao_img);
            imagem.setImageResource(ref[0]);
            if(verificadores.containsKey(refKey[0]))
                mEditText.setText(verificadores.get(refKey[0]));

            mBtnRegistrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!isFinalizado) {
                        NomeacaoObject nomeacaoObject;
                        if (participante.getNomeacaoObject() == null)
                            nomeacaoObject = new NomeacaoObject();
                        else
                            nomeacaoObject = participante.getNomeacaoObject();

                        verificadores.put(refKey[0], mEditText.getText().toString());
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

