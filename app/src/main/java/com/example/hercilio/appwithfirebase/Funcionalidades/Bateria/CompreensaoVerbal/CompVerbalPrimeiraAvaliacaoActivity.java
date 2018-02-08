package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoVerbal;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;

import java.util.HashMap;

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

    private HashMap<Integer, Boolean> verificadores = new HashMap<>();

    private int totalPrincipal, totalSecundario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_verbal_primeira_avaliacao);

        //Botoes principais:
        mBtnPrincLucia = (Button) findViewById(R.id.btn_p_lucia);
        verificadores.put(R.id.btn_p_lucia, false);
        mBtnPrincMora = (Button) findViewById(R.id.btn_p_mora);
        verificadores.put(R.id.btn_p_mora, false);
        mBtnPrincNoInterior = (Button) findViewById(R.id.btn_p_no_interior);
        verificadores.put(R.id.btn_p_no_interior, false);
        mBtnPrincElaSaiu = (Button) findViewById(R.id.btn_p_ela_saiu);
        verificadores.put(R.id.btn_p_ela_saiu, false);
        mBtnPrincEntrevistaTrabalho = (Button) findViewById(R.id.btn_p_entrevista_trabalho);
        verificadores.put(R.id.btn_p_entrevista_trabalho, false);
        mBtnPrincNaCapital = (Button) findViewById(R.id.btn_p_na_capital);
        verificadores.put(R.id.btn_p_na_capital, false);
        mBtnPrincElaFoi = (Button) findViewById(R.id.btn_p_ela_foi);
        verificadores.put(R.id.btn_p_ela_foi, false);
        mBtnPrincRodoviaria = (Button) findViewById(R.id.btn_p_rodoviaria);
        verificadores.put(R.id.btn_p_rodoviaria, false);
        mBtnPrincDeCarona = (Button) findViewById(R.id.btn_p_de_carona);
        verificadores.put(R.id.btn_p_de_carona, false);
        mBtnPrincSeuAmigo = (Button) findViewById(R.id.btn_p_com_seu_amigo);
        verificadores.put(R.id.btn_p_com_seu_amigo, false);
        mBtnPrincPedro = (Button) findViewById(R.id.btn_p_pedro);
        verificadores.put(R.id.btn_p_pedro, false);
        mBtnPrincPneuFurou = (Button) findViewById(R.id.btn_p_pneu_furou);
        verificadores.put(R.id.btn_p_pneu_furou, false);
        mBtnPrincPegouTaxi = (Button) findViewById(R.id.btn_p_pegou_taxi);
        verificadores.put(R.id.btn_p_pegou_taxi, false);
        mBtnPrincChegarATempo = (Button) findViewById(R.id.btn_p_chegar_a_tempo);
        verificadores.put(R.id.btn_p_chegar_a_tempo, false);

        //Botoes secundarios:
        mBtnSecunDoParana = (Button) findViewById(R.id.btn_s_do_parana);
        verificadores.put(R.id.btn_s_do_parana, false);
        mBtnSecunNumaManha = (Button) findViewById(R.id.btn_s_numa_manha);
        verificadores.put(R.id.btn_s_numa_manha, false);
        mBtnSecunDeSegundaFeira = (Button) findViewById(R.id.btn_s_de_segundafeira);
        verificadores.put(R.id.btn_s_de_segundafeira, false);
        mBtnSecunEstavaChovendo = (Button) findViewById(R.id.btn_s_estava_chovendo);
        verificadores.put(R.id.btn_s_estava_chovendo, false);
        mBtnSecunOCarro = (Button) findViewById(R.id.btn_s_o_carro);
        verificadores.put(R.id.btn_s_o_carro, false);
        mBtnSecunBuraco = (Button) findViewById(R.id.btn_s_buraco);
        verificadores.put(R.id.btn_s_buraco, false);
        mBtnSecunOnibus = (Button) findViewById(R.id.btn_s_onibus);
        verificadores.put(R.id.btn_s_onibus, false);
        mBtnSecunAteRodoviaria = (Button) findViewById(R.id.btn_s_rodoviaria);
        verificadores.put(R.id.btn_s_rodoviaria, false);

        mTotalPrincipal = (TextView) findViewById(R.id.first_value_p_avaliacao1_comp_verbal);
        mTotalSecundario = (TextView) findViewById(R.id.first_value_s_avaliacao1_comp_verbal);

        totalPrincipal = 0;
        totalSecundario = 0;

        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);

            //TODO: fazer intents

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

    public void onClickPrincipal(View v) {
        Button button = (Button) v;

        if(!verificadores.get(v.getId())) {
            v.getBackground().setColorFilter(getResources().getColor(R.color.clickedPrinc_compVerval), PorterDuff.Mode.SRC_ATOP);
//            button.setTextColor(Color.WHITE);
            verificadores.put(v.getId(), true);
            atualizaTotalPrincipal(true);
        } else {
            v.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
//            button.setTextColor(Color.BLACK);
            verificadores.put(v.getId(), false);
            atualizaTotalPrincipal(false);
        }
    }

    public void onClickSecundario(View v) {
        Button button = (Button) v;
        if(!verificadores.get(v.getId())) {
            v.getBackground().setColorFilter(getResources().getColor(R.color.clickedSecun_compVerval), PorterDuff.Mode.SRC_ATOP);
//            button.setTextColor(Color.WHITE);
            verificadores.put(v.getId(), true);
            atualizaTotalSecundario(true);
        } else {
            v.getBackground().setColorFilter(getResources().getColor(R.color.colorSecundaria_compVerbal), PorterDuff.Mode.SRC_ATOP);
//            button.setTextColor(Color.BLACK);
            verificadores.put(v.getId(), false);
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
}
