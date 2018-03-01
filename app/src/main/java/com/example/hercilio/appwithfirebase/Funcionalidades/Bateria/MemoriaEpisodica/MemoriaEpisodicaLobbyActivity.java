package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.MemoriaEpisodica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoVerbal.CompVerbalPrimeiraAvaliacaoActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoVerbal.CompVerbalSegundaAvaliacaoActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoVerbal.CompVerbalTerceiraAvaliacaoActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;

/**
 * Created by Hercilio on 27/02/2018.
 */

public class MemoriaEpisodicaLobbyActivity extends AppCompatActivity {

    public static final String IDENTIFICA_FASE = "Integer[]";

    private Button mBtnPrimeiraFase, mBtnSegundaFase, mBtnTerceiraFase, mBtnQuartaFase, mBtnQuintaFase, mBtnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoria_episodica_lobby);

        mBtnPrimeiraFase = (Button) findViewById(R.id.button_for_mem_ep_fase_1);
        mBtnSegundaFase = (Button) findViewById(R.id.button_for_mem_ep_fase_2);
        mBtnTerceiraFase = (Button) findViewById(R.id.button_for_mem_ep_fase_3);
        mBtnQuartaFase = (Button) findViewById(R.id.button_for_mem_ep_fase_4);
        mBtnQuintaFase = (Button) findViewById(R.id.button_for_mem_ep_fase_5);

        mBtnContinuar = (Button) findViewById(R.id.btn_continuar_mem_ep_lobby);


        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);

            //TODO: fazer intents

            mBtnPrimeiraFase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer identificador[] = new Integer[1];
                    identificador[0] = 1;

                    Intent intent = new Intent(getBaseContext(), MemoriaEpisodicaPrimeiraFaseActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    intent.putExtra(MemoriaEpisodicaPrimeiraFaseActivity.IDENTIFICADOR_DA_SEQUENCIA_MEM_EP, identificador);
                    startActivity(intent);
                }
            });

            mBtnSegundaFase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer identificador[] = new Integer[1];
                    identificador[0] = 1;

                    Intent intent = new Intent(getBaseContext(), GridActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    intent.putExtra(IDENTIFICA_FASE, identificador);
                    startActivity(intent);
                }
            });

            mBtnTerceiraFase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer identificador[] = new Integer[1];
                    identificador[0] = 2;

                    Intent intent = new Intent(getBaseContext(), GridActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    intent.putExtra(IDENTIFICA_FASE, identificador);
                    startActivity(intent);
                }
            });

            mBtnQuartaFase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer identificador[] = new Integer[1];
                    identificador[0] = 3;

                    Intent intent = new Intent(getBaseContext(), GridActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    intent.putExtra(IDENTIFICA_FASE, identificador);
                    startActivity(intent);
                }
            });

            mBtnQuintaFase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer identificador[] = new Integer[1];
                    identificador[0] = 4;

                    Intent intent = new Intent(getBaseContext(), GridActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    intent.putExtra(IDENTIFICA_FASE, identificador);
                    startActivity(intent);
                }
            });

            mBtnContinuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), BaleLobbyActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    startActivity(intent);
                }
            });
        }

    }
}
