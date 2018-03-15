package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoVerbal;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Observacoes.ObservacoesActivity;
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

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mBtnPrimeiraAvaliacao = (Button) findViewById(R.id.btn_primeira_avaliacao);
        mBtnSegundaAvaliacao = (Button) findViewById(R.id.btn_segunda_avaliacao);
        mBtnTerceiraAvaliacao = (Button) findViewById(R.id.btn_terceira_avaliacao);
        mBtnContinuar = (Button) findViewById(R.id.btn_continuar_comp_verbal_lobby);

        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);

            if(participante.getCompVerbalObject() != null) {
                if (participante.getCompVerbalObject().getPrimeiraAvaliacao() != null)
                    mBtnPrimeiraAvaliacao.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                if (participante.getCompVerbalObject().getSegundaAvaliacao() != null)
                    mBtnSegundaAvaliacao.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                if (participante.getCompVerbalObject().getTerceiraAvaliacao() != null)
                    mBtnTerceiraAvaliacao.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            }

            mBtnPrimeiraAvaliacao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), CompVerbalPrimeiraAvaliacaoActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    startActivity(intent);
                }
            });

            mBtnSegundaAvaliacao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), CompVerbalSegundaAvaliacaoActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    startActivity(intent);
                }
            });

            mBtnTerceiraAvaliacao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), CompVerbalTerceiraAvaliacaoActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.obeservacoes_button, menu);
        //Instanciação das referências.
        MenuItem baleLobbyHomeBtn = menu.findItem(R.id.bale_observacoes_button);
        baleLobbyHomeBtn.setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //Faz as transações
        switch (id) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
            case R.id.bale_observacoes_button:
                Intent intentFromList = getIntent();
                if (intentFromList != null) {
                    final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);
                    String[] strings = new String[1];
                    strings[0] = "CompreensaoVerbalLobbyActivity";

                    Intent intent = new Intent(this, ObservacoesActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    intent.putExtra(ObservacoesActivity.ACTIVITY_LISTENER, strings);
                    startActivity(intent);
                }
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void atualizaPorcentagem(Participante participante) {
        int numeroDeVerf = 6;
        int numeroDeVerfConcluidos = 0;

        if(participante.getCompVerbalObject().getPrimeiraAvaliacao() != null) {
            numeroDeVerfConcluidos += 1;
        }
        if(participante.getCompVerbalObject().getSegundaAvaliacao() != null) {
            numeroDeVerfConcluidos += 1;
        }
        if(participante.getCompVerbalObject().getTerceiraAvaliacao() != null) {
            numeroDeVerfConcluidos += 1;
        }
        if(participante.getCompVerbalObject().getObservacoes() != null) {
            numeroDeVerfConcluidos += 3;
        }

        int porcentagem = (100*numeroDeVerfConcluidos)/numeroDeVerf;

        participante.getCompVerbalObject().setPorcentagem(porcentagem);
    }
}
