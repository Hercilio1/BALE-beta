package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoVerbal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoFrases.CompreensaoFrasesRadioActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;

/**
 * Created by Hercilio on 08/02/2018.
 */

public class CompreensaoVerbalLobbyActivity extends AppCompatActivity {

    private Button mBtnPrimeiraAvaliacao;
    private Button mBtnSegundaAvaliacao;
    private Button mBtnTerceiraAvaliacao;
    private Button mBtnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compreensao_verbal);

        mBtnPrimeiraAvaliacao = (Button) findViewById(R.id.btn_primeira_avaliacao);
        mBtnSegundaAvaliacao = (Button) findViewById(R.id.btn_segunda_avaliacao);
        mBtnTerceiraAvaliacao = (Button) findViewById(R.id.btn_terceira_avaliacao);
        mBtnContinuar = (Button) findViewById(R.id.btn_continuar_comp_verbal_lobby);

        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);

            //TODO: fazer intents

            mBtnPrimeiraAvaliacao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), CompVerbalPrimeiraAvaliacaoActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    startActivity(intent);
                }
            });
//            btnContinuar.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    registrar(participante);
//                    Intent intent = new Intent(getBaseContext(), CompreensaoFrasesRadioActivity.class);
//                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
//                    startActivity(intent);
//                }
//            });
        }

    }
}
