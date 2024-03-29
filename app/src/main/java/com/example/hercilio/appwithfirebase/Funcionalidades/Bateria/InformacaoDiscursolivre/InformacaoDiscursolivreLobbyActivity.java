package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.InformacaoDiscursolivre;

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
 * Created by Hercilio on 15/02/2018.
 */

public class InformacaoDiscursolivreLobbyActivity extends AppCompatActivity {

    private Button mBtnInformacao;
    private Button mBtnDiscursoLivre;
    private Button mBtnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacao_discursolivre_lobby);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mBtnInformacao = (Button) findViewById(R.id.btn_informacao);
        mBtnDiscursoLivre = (Button) findViewById(R.id.btn_discurso_livre);
        mBtnContinuar = (Button) findViewById(R.id.btn_continuar_informacao_discursolivre_narrativa_lobby);

        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);

            if(participante.getInformacaoDiscLivreObject() != null) {
                if (participante.getInformacaoDiscLivreObject().getInformacao() != null)
                    mBtnInformacao.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                if (participante.getInformacaoDiscLivreObject().getDiscursoLivre() != null)
                    mBtnDiscursoLivre.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            }

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

    public void atualizaPorcentagem(Participante participante) {
        int numeroDeVerf = 4;
        int numeroDeVerfConcluidos = 0;

        if(participante.getInformacaoDiscLivreObject().getInformacao() != null) {
            numeroDeVerfConcluidos += 1;
        }
        if(participante.getInformacaoDiscLivreObject().getDiscursoLivre() != null) {
            numeroDeVerfConcluidos += 1;
        }
        if(participante.getInformacaoDiscLivreObject().getObservacoes() != null) {
            numeroDeVerfConcluidos += 2;
        }

        int porcentagem = (100*numeroDeVerfConcluidos)/numeroDeVerf;

        participante.getInformacaoDiscLivreObject().setPorcentagem(porcentagem);
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
        Intent intentFromList = getIntent();
        //Faz as transações
        switch (id) {
            case android.R.id.home:
                if (intentFromList != null) {
                    final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);
                    Intent intent = new Intent(this, BaleLobbyActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    startActivity(intent);

                }
                return true;
            case R.id.bale_observacoes_button:
                if (intentFromList != null) {
                    final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);
                    String[] strings = new String[1];
                    strings[0] = "InformacaoDiscursolivreLobbyActivity";

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
}
