package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.InformacaoDiscursolivreNarrativa;

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
 * Created by Hercilio on 15/02/2018.
 */

public class InformacaoDiscursolivreNarrativaLobbyActivity extends AppCompatActivity {

    private Button mBtnInformacao;
    private Button mBtnDiscursoLivre;
    private Button mBtnNarrativa;
    private Button mBtnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacao_discursolivre_narrativa_lobby);

        mBtnInformacao = (Button) findViewById(R.id.btn_informacao);
        mBtnDiscursoLivre = (Button) findViewById(R.id.btn_discurso_livre);
        mBtnNarrativa = (Button) findViewById(R.id.btn_narrativa);
        mBtnContinuar = (Button) findViewById(R.id.btn_continuar_informacao_discursolivre_narrativa_lobby);

        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);

            //TODO: fazer intents

            mBtnInformacao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), InformacaoActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    startActivity(intent);
                }
            });

            mBtnDiscursoLivre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), DiscursoLivreActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    startActivity(intent);
                }
            });
//
//            mBtnTerceiraAvaliacao.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(getBaseContext(), CompVerbalTerceiraAvaliacaoActivity.class);
//                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
//                    startActivity(intent);
//                }
//            });

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
