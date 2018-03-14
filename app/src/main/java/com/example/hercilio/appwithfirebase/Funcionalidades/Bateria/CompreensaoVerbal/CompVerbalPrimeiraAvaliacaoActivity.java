package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoVerbal;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Objetos.CompreensaoVerbalObject;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hercilio on 08/02/2018.
 */

public class CompVerbalPrimeiraAvaliacaoActivity extends AppCompatActivity {

    //Botoes principais:
    private Button mBtnPrincLucia, mBtnPrincMora, mBtnPrincNoInterior, mBtnPrincElaSaiu, mBtnPrincEntrevistaTrabalho
            , mBtnPrincNaCapital, mBtnPrincElaFoi, mBtnPrincRodoviaria, mBtnPrincDeCarona, mBtnPrincSeuAmigo
            , mBtnPrincPedro, mBtnPrincPneuFurou, mBtnPrincPegouTaxi, mBtnPrincChegarATempo;

    //Botoes secundarios:
    private Button mBtnSecunDoParana, mBtnSecunNumaManha, mBtnSecunDeSegundaFeira, mBtnSecunEstavaChovendo
            , mBtnSecunOCarro, mBtnSecunBuraco, mBtnSecunOnibus, mBtnSecunAteRodoviaria;

    //Totais views:
    private TextView mTotalPrincipal, mTotalSecundario;
    private int totalPrincipal, totalSecundario;

    //Botao continuar:
    private Button btnContinuar;

    //Dicionario que armazena os botoes selecionados:
    private Map<String, Boolean> verificadores = new HashMap<>();
    private Map<String, Integer> dicionario = new HashMap<>();

    //Lista que auxilia no autoComplete
    private ArrayList<String> auxAutoComplete = new ArrayList<>();
    private ArrayList<String> auxAutoCompleteSecundario = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_verbal_primeira_avaliacao);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //Botoes principais:
        mBtnPrincLucia = (Button) findViewById(R.id.btn_p_lucia);
        mBtnPrincLucia.setTag("mBtnPrincLucia");
        dicionario.put(mBtnPrincLucia.getTag().toString(), mBtnPrincLucia.getId());
        verificadores.put(mBtnPrincLucia.getTag().toString(), false);
        auxAutoComplete.add(mBtnPrincLucia.getTag().toString());

        mBtnPrincMora = (Button) findViewById(R.id.btn_p_mora);
        mBtnPrincMora.setTag("mBtnPrincMora");
        dicionario.put(mBtnPrincMora.getTag().toString(), mBtnPrincMora.getId());
        verificadores.put(mBtnPrincMora.getTag().toString(), false);
        auxAutoComplete.add(mBtnPrincMora.getTag().toString());

        mBtnPrincNoInterior = (Button) findViewById(R.id.btn_p_no_interior);
        mBtnPrincNoInterior.setTag("mBtnPrincNoInterior");
        dicionario.put(mBtnPrincNoInterior.getTag().toString(), mBtnPrincNoInterior.getId());
        verificadores.put(mBtnPrincNoInterior.getTag().toString(), false);
        auxAutoComplete.add(mBtnPrincNoInterior.getTag().toString());

        mBtnPrincElaSaiu = (Button) findViewById(R.id.btn_p_ela_saiu);
        mBtnPrincElaSaiu.setTag("mBtnPrincElaSaiu");
        dicionario.put(mBtnPrincElaSaiu.getTag().toString(), mBtnPrincElaSaiu.getId());
        verificadores.put(mBtnPrincElaSaiu.getTag().toString(), false);
        auxAutoComplete.add(mBtnPrincElaSaiu.getTag().toString());

        mBtnPrincEntrevistaTrabalho = (Button) findViewById(R.id.btn_p_entrevista_trabalho);
        mBtnPrincEntrevistaTrabalho.setTag("mBtnPrincEntrevistaTrabalho");
        dicionario.put(mBtnPrincEntrevistaTrabalho.getTag().toString(), mBtnPrincEntrevistaTrabalho.getId());
        verificadores.put(mBtnPrincEntrevistaTrabalho.getTag().toString(), false);
        auxAutoComplete.add(mBtnPrincEntrevistaTrabalho.getTag().toString());

        mBtnPrincNaCapital = (Button) findViewById(R.id.btn_p_na_capital);
        mBtnPrincNaCapital.setTag("mBtnPrincNaCapital");
        dicionario.put(mBtnPrincNaCapital.getTag().toString(), mBtnPrincNaCapital.getId());
        verificadores.put(mBtnPrincNaCapital.getTag().toString(), false);
        auxAutoComplete.add(mBtnPrincNaCapital.getTag().toString());

        mBtnPrincElaFoi = (Button) findViewById(R.id.btn_p_ela_foi);
        mBtnPrincElaFoi.setTag("mBtnPrincElaFoi");
        dicionario.put(mBtnPrincElaFoi.getTag().toString(), mBtnPrincElaFoi.getId());
        verificadores.put(mBtnPrincElaFoi.getTag().toString(), false);
        auxAutoComplete.add(mBtnPrincElaFoi.getTag().toString());

        mBtnPrincRodoviaria = (Button) findViewById(R.id.btn_p_rodoviaria);
        mBtnPrincRodoviaria.setTag("mBtnPrincRodoviaria");
        dicionario.put(mBtnPrincRodoviaria.getTag().toString(), mBtnPrincRodoviaria.getId());
        verificadores.put(mBtnPrincRodoviaria.getTag().toString(), false);
        auxAutoComplete.add(mBtnPrincRodoviaria.getTag().toString());

        mBtnPrincDeCarona = (Button) findViewById(R.id.btn_p_de_carona);
        mBtnPrincDeCarona.setTag("mBtnPrincDeCarona");
        dicionario.put(mBtnPrincDeCarona.getTag().toString(), mBtnPrincDeCarona.getId());
        verificadores.put(mBtnPrincDeCarona.getTag().toString(), false);
        auxAutoComplete.add(mBtnPrincDeCarona.getTag().toString());

        mBtnPrincSeuAmigo = (Button) findViewById(R.id.btn_p_com_seu_amigo);
        mBtnPrincSeuAmigo.setTag("mBtnPrincSeuAmigo");
        dicionario.put(mBtnPrincSeuAmigo.getTag().toString(), mBtnPrincSeuAmigo.getId());
        verificadores.put(mBtnPrincSeuAmigo.getTag().toString(), false);
        auxAutoComplete.add(mBtnPrincSeuAmigo.getTag().toString());

        mBtnPrincPedro = (Button) findViewById(R.id.btn_p_pedro);
        mBtnPrincPedro.setTag("mBtnPrincPedro");
        dicionario.put(mBtnPrincPedro.getTag().toString(), mBtnPrincPedro.getId());
        verificadores.put(mBtnPrincPedro.getTag().toString(), false);
        auxAutoComplete.add(mBtnPrincPedro.getTag().toString());

        mBtnPrincPneuFurou = (Button) findViewById(R.id.btn_p_pneu_furou);
        mBtnPrincPneuFurou.setTag("mBtnPrincPneuFurou");
        dicionario.put(mBtnPrincPneuFurou.getTag().toString(), mBtnPrincPneuFurou.getId());
        verificadores.put(mBtnPrincPneuFurou.getTag().toString(), false);
        auxAutoComplete.add(mBtnPrincPneuFurou.getTag().toString());

        mBtnPrincPegouTaxi = (Button) findViewById(R.id.btn_p_pegou_taxi);
        mBtnPrincPegouTaxi.setTag("mBtnPrincPegouTaxi");
        dicionario.put(mBtnPrincPegouTaxi.getTag().toString(), mBtnPrincPegouTaxi.getId());
        verificadores.put(mBtnPrincPegouTaxi.getTag().toString(), false);
        auxAutoComplete.add(mBtnPrincPegouTaxi.getTag().toString());

        mBtnPrincChegarATempo = (Button) findViewById(R.id.btn_p_chegar_a_tempo);
        mBtnPrincChegarATempo.setTag("mBtnPrincChegarATempo");
        dicionario.put(mBtnPrincChegarATempo.getTag().toString(), mBtnPrincChegarATempo.getId());
        verificadores.put(mBtnPrincChegarATempo.getTag().toString(), false);
        auxAutoComplete.add(mBtnPrincChegarATempo.getTag().toString());

        //Botoes secundarios:
        mBtnSecunDoParana = (Button) findViewById(R.id.btn_s_do_parana);
        mBtnSecunDoParana.setTag("mBtnSecunDoParana");
        dicionario.put(mBtnSecunDoParana.getTag().toString(), mBtnSecunDoParana.getId());
        verificadores.put(mBtnSecunDoParana.getTag().toString(), false);
        auxAutoCompleteSecundario.add(mBtnSecunDoParana.getTag().toString());

        mBtnSecunNumaManha = (Button) findViewById(R.id.btn_s_numa_manha);
        mBtnSecunNumaManha.setTag("mBtnSecunNumaManha");
        dicionario.put(mBtnSecunNumaManha.getTag().toString(), mBtnSecunNumaManha.getId());
        verificadores.put(mBtnSecunNumaManha.getTag().toString(), false);
        auxAutoCompleteSecundario.add(mBtnSecunNumaManha.getTag().toString());

        mBtnSecunDeSegundaFeira = (Button) findViewById(R.id.btn_s_de_segundafeira);
        mBtnSecunDeSegundaFeira.setTag("mBtnSecunDeSegundaFeira");
        dicionario.put(mBtnSecunDeSegundaFeira.getTag().toString(), mBtnSecunDeSegundaFeira.getId());
        verificadores.put(mBtnSecunDeSegundaFeira.getTag().toString(), false);
        auxAutoCompleteSecundario.add(mBtnSecunDeSegundaFeira.getTag().toString());

        mBtnSecunEstavaChovendo = (Button) findViewById(R.id.btn_s_estava_chovendo);
        mBtnSecunEstavaChovendo.setTag("mBtnSecunEstavaChovendo");
        dicionario.put(mBtnSecunEstavaChovendo.getTag().toString(), mBtnSecunEstavaChovendo.getId());
        verificadores.put(mBtnSecunEstavaChovendo.getTag().toString(), false);
        auxAutoCompleteSecundario.add(mBtnSecunEstavaChovendo.getTag().toString());

        mBtnSecunOCarro = (Button) findViewById(R.id.btn_s_o_carro);
        mBtnSecunOCarro.setTag("mBtnSecunOCarro");
        dicionario.put(mBtnSecunOCarro.getTag().toString(), mBtnSecunOCarro.getId());
        verificadores.put(mBtnSecunOCarro.getTag().toString(), false);
        auxAutoCompleteSecundario.add(mBtnSecunOCarro.getTag().toString());

        mBtnSecunBuraco = (Button) findViewById(R.id.btn_s_buraco);
        mBtnSecunBuraco.setTag("mBtnSecunBuraco");
        dicionario.put(mBtnSecunBuraco.getTag().toString(), mBtnSecunBuraco.getId());
        verificadores.put(mBtnSecunBuraco.getTag().toString(), false);
        auxAutoCompleteSecundario.add(mBtnSecunBuraco.getTag().toString());

        mBtnSecunOnibus = (Button) findViewById(R.id.btn_s_onibus);
        mBtnSecunOnibus.setTag("mBtnSecunOnibus");
        dicionario.put(mBtnSecunOnibus.getTag().toString(), mBtnSecunOnibus.getId());
        verificadores.put(mBtnSecunOnibus.getTag().toString(), false);
        auxAutoCompleteSecundario.add(mBtnSecunOnibus.getTag().toString());

        mBtnSecunAteRodoviaria = (Button) findViewById(R.id.btn_s_rodoviaria);
        mBtnSecunAteRodoviaria.setTag("mBtnSecunAteRodoviaria");
        dicionario.put(mBtnSecunAteRodoviaria.getTag().toString(), mBtnSecunAteRodoviaria.getId());
        verificadores.put(mBtnSecunAteRodoviaria.getTag().toString(), false);
        auxAutoCompleteSecundario.add(mBtnSecunAteRodoviaria.getTag().toString());
        //Totais:
        mTotalPrincipal = (TextView) findViewById(R.id.first_value_p_avaliacao1_comp_verbal);
        mTotalSecundario = (TextView) findViewById(R.id.first_value_s_avaliacao1_comp_verbal);

        totalPrincipal = 0;
        totalSecundario = 0;

        //Botao continuar
        btnContinuar = (Button) findViewById(R.id.btn_continuar_comp_verbal_primeira_avaliacao_btn);

        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);

            if(participante.getCompVerbalObject() != null && participante.getCompVerbalObject().getPrimeiraAvaliacao() != null) {
                verificadores = participante.getCompVerbalObject().getPrimeiraAvaliacao();
                autoComplete();
            }

            btnContinuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    registrar(participante);
                    Intent intent = new Intent(getBaseContext(), CompreensaoVerbalLobbyActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    startActivity(intent);
                }
            });
        }
    }

    public void onClickPrincipal(View v) {
        Button button = (Button) v;

        if(!verificadores.get(v.getTag().toString())) {
            v.getBackground().setColorFilter(getResources().getColor(R.color.clickedPrinc_compVerval), PorterDuff.Mode.SRC_ATOP);
//            button.setTextColor(Color.WHITE);
            verificadores.put(v.getTag().toString(), true);
            atualizaTotalPrincipal(true);
        } else {
            v.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
//            button.setTextColor(Color.BLACK);
            verificadores.put(v.getTag().toString(), false);
            atualizaTotalPrincipal(false);
        }
    }

    public void onClickSecundario(View v) {
        Button button = (Button) v;
        if(!verificadores.get(v.getTag().toString())) {
            v.getBackground().setColorFilter(getResources().getColor(R.color.clickedSecun_compVerval), PorterDuff.Mode.SRC_ATOP);
//            button.setTextColor(Color.WHITE);
            verificadores.put(v.getTag().toString(), true);
            atualizaTotalSecundario(true);
        } else {
            v.getBackground().setColorFilter(getResources().getColor(R.color.colorSecundaria_compVerbal), PorterDuff.Mode.SRC_ATOP);
//            button.setTextColor(Color.BLACK);
            verificadores.put(v.getTag().toString(), false);
            atualizaTotalSecundario(false);
        }
    }

    public void atualizaTotalPrincipal(boolean ref) {
        if(ref)
            totalPrincipal++;
        else
            totalPrincipal--;
        mTotalPrincipal.setText(""+totalPrincipal);
    }

    public void atualizaTotalSecundario(boolean ref) {
        if(ref)
            totalSecundario++;
        else
            totalSecundario--;
        mTotalSecundario.setText(""+totalSecundario);
    }

    public void autoComplete() {
        Button button;
        for(String x : auxAutoComplete) {
            button = (Button) findViewById(dicionario.get(x));
            autoCompleteButtonsPriciapais(button);
        }
        for(String x : auxAutoCompleteSecundario) {
            button = (Button) findViewById(dicionario.get(x));
            autoCompleteButtonsSecundarios(button);
        }
    }

    public void autoCompleteButtonsPriciapais(View v) {
        Button button = (Button) v;
        if(verificadores.get(v.getTag().toString())) {
            v.getBackground().setColorFilter(getResources().getColor(R.color.clickedPrinc_compVerval), PorterDuff.Mode.SRC_ATOP);
            atualizaTotalPrincipal(true);
        }
    }

    public void autoCompleteButtonsSecundarios(View v) {
        Button button = (Button) v;
        if(verificadores.get(v.getTag().toString())) {
            v.getBackground().setColorFilter(getResources().getColor(R.color.clickedSecun_compVerval), PorterDuff.Mode.SRC_ATOP);
            atualizaTotalSecundario(true);
        }
    }

    public void registrar(Participante participante) {
        if(participante.getCompVerbalObject() == null) {
            CompreensaoVerbalObject compVerbalObj = new CompreensaoVerbalObject();
            compVerbalObj.atualizaPrimeiraAvaliacao(verificadores);
            compVerbalObj.setAv1ValorTotalPrincipal(totalPrincipal);
            compVerbalObj.setAv1ValorTotalSecundario(totalSecundario);
            participante.setCompVerbalObject(compVerbalObj);
        } else {
            CompreensaoVerbalObject aux = participante.getCompVerbalObject();
            aux.setAv1ValorTotalPrincipal(totalPrincipal);
            aux.setAv1ValorTotalSecundario(totalSecundario);
            aux.atualizaPrimeiraAvaliacao(verificadores);
            participante.setCompVerbalObject(aux);
        }

        new CompreensaoVerbalLobbyActivity().atualizaPorcentagem(participante);

        FirebaseDatabase mFirebaseDatabase;
        final DatabaseReference mParticipanteDatabaseReference;

        //Cria o caminho que garantirá o acesso somente aos participantes do usuário logado
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        mParticipanteDatabaseReference = mFirebaseDatabase.getReference().child("users").child(auth.getCurrentUser().getUid()).child("participantes");

       //Criar uma váriavel final estava criando um loop no onDataChange
       //final Participante partAux = participante;
        mParticipanteDatabaseReference.child(participante.getCpf()).setValue(participante);
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
