package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoVerbal;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoFrases.CompreensaoFrasesRadioActivity;
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

    //Lista que auxilia no autoComplete
    private ArrayList<String> auxAutoComplete = new ArrayList<>();
    private ArrayList<String> auxAutoCompleteSecundario = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_verbal_primeira_avaliacao);

        //Botoes principais:
        mBtnPrincLucia = (Button) findViewById(R.id.btn_p_lucia);
        verificadores.put("" + R.id.btn_p_lucia, false);
        auxAutoComplete.add("" + R.id.btn_p_lucia);
        mBtnPrincMora = (Button) findViewById(R.id.btn_p_mora);
        verificadores.put("" + R.id.btn_p_mora, false);
        auxAutoComplete.add("" + R.id.btn_p_mora);
        mBtnPrincNoInterior = (Button) findViewById(R.id.btn_p_no_interior);
        verificadores.put("" + R.id.btn_p_no_interior, false);
        auxAutoComplete.add("" + R.id.btn_p_no_interior);
        mBtnPrincElaSaiu = (Button) findViewById(R.id.btn_p_ela_saiu);
        verificadores.put("" + R.id.btn_p_ela_saiu, false);
        auxAutoComplete.add("" + R.id.btn_p_ela_saiu);
        mBtnPrincEntrevistaTrabalho = (Button) findViewById(R.id.btn_p_entrevista_trabalho);
        verificadores.put("" + R.id.btn_p_entrevista_trabalho, false);
        auxAutoComplete.add("" + R.id.btn_p_entrevista_trabalho);
        mBtnPrincNaCapital = (Button) findViewById(R.id.btn_p_na_capital);
        verificadores.put("" + R.id.btn_p_na_capital, false);
        auxAutoComplete.add("" + R.id.btn_p_na_capital);
        mBtnPrincElaFoi = (Button) findViewById(R.id.btn_p_ela_foi);
        verificadores.put("" + R.id.btn_p_ela_foi, false);
        auxAutoComplete.add("" + R.id.btn_p_ela_foi);
        mBtnPrincRodoviaria = (Button) findViewById(R.id.btn_p_rodoviaria);
        verificadores.put("" + R.id.btn_p_rodoviaria, false);
        auxAutoComplete.add("" + R.id.btn_p_rodoviaria);
        mBtnPrincDeCarona = (Button) findViewById(R.id.btn_p_de_carona);
        verificadores.put("" + R.id.btn_p_de_carona, false);
        auxAutoComplete.add("" + R.id.btn_p_de_carona);
        mBtnPrincSeuAmigo = (Button) findViewById(R.id.btn_p_com_seu_amigo);
        verificadores.put("" + R.id.btn_p_com_seu_amigo, false);
        auxAutoComplete.add("" + R.id.btn_p_com_seu_amigo);
        mBtnPrincPedro = (Button) findViewById(R.id.btn_p_pedro);
        verificadores.put("" + R.id.btn_p_pedro, false);
        auxAutoComplete.add("" + R.id.btn_p_pedro);
        mBtnPrincPneuFurou = (Button) findViewById(R.id.btn_p_pneu_furou);
        verificadores.put("" + R.id.btn_p_pneu_furou, false);
        auxAutoComplete.add("" + R.id.btn_p_pneu_furou);
        mBtnPrincPegouTaxi = (Button) findViewById(R.id.btn_p_pegou_taxi);
        verificadores.put("" + R.id.btn_p_pegou_taxi, false);
        auxAutoComplete.add("" + R.id.btn_p_pegou_taxi);
        mBtnPrincChegarATempo = (Button) findViewById(R.id.btn_p_chegar_a_tempo);
        verificadores.put("" + R.id.btn_p_chegar_a_tempo, false);
        auxAutoComplete.add("" + R.id.btn_p_chegar_a_tempo);

        //Botoes secundarios:
        mBtnSecunDoParana = (Button) findViewById(R.id.btn_s_do_parana);
        verificadores.put("" + R.id.btn_s_do_parana, false);
        auxAutoCompleteSecundario.add("" + R.id.btn_s_do_parana);
        mBtnSecunNumaManha = (Button) findViewById(R.id.btn_s_numa_manha);
        verificadores.put("" + R.id.btn_s_numa_manha, false);
        auxAutoCompleteSecundario.add("" + R.id.btn_s_numa_manha);
        mBtnSecunDeSegundaFeira = (Button) findViewById(R.id.btn_s_de_segundafeira);
        verificadores.put("" + R.id.btn_s_de_segundafeira, false);
        auxAutoCompleteSecundario.add("" + R.id.btn_s_de_segundafeira);
        mBtnSecunEstavaChovendo = (Button) findViewById(R.id.btn_s_estava_chovendo);
        verificadores.put("" + R.id.btn_s_estava_chovendo, false);
        auxAutoCompleteSecundario.add("" + R.id.btn_s_estava_chovendo);
        mBtnSecunOCarro = (Button) findViewById(R.id.btn_s_o_carro);
        verificadores.put("" + R.id.btn_s_o_carro, false);
        auxAutoCompleteSecundario.add("" + R.id.btn_s_o_carro);
        mBtnSecunBuraco = (Button) findViewById(R.id.btn_s_buraco);
        verificadores.put("" + R.id.btn_s_buraco, false);
        auxAutoCompleteSecundario.add("" + R.id.btn_s_buraco);
        mBtnSecunOnibus = (Button) findViewById(R.id.btn_s_onibus);
        verificadores.put("" + R.id.btn_s_onibus, false);
        auxAutoCompleteSecundario.add("" + R.id.btn_s_onibus);
        mBtnSecunAteRodoviaria = (Button) findViewById(R.id.btn_s_rodoviaria);
        verificadores.put("" + R.id.btn_s_rodoviaria, false);
        auxAutoCompleteSecundario.add("" + R.id.btn_s_rodoviaria);

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

            if(participante.getCompVerbalObject() != null) {
                verificadores = participante.getCompVerbalObject().getPrimeiraAvaliacao();
                autoComplete(participante);
            }

            btnContinuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    registrar(participante);
                    Intent intent = new Intent(getBaseContext(), BaleLobbyActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    startActivity(intent);
                }
            });
        }
    }

    public void onClickPrincipal(View v) {
        Button button = (Button) v;

        if(!verificadores.get(""+v.getId())) {
            v.getBackground().setColorFilter(getResources().getColor(R.color.clickedPrinc_compVerval), PorterDuff.Mode.SRC_ATOP);
//            button.setTextColor(Color.WHITE);
            verificadores.put("" + v.getId(), true);
            atualizaTotalPrincipal(true);
        } else {
            v.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
//            button.setTextColor(Color.BLACK);
            verificadores.put("" + v.getId(), false);
            atualizaTotalPrincipal(false);
        }
    }

    public void onClickSecundario(View v) {
        Button button = (Button) v;
        if(!verificadores.get(""+
                v.getId())) {
            v.getBackground().setColorFilter(getResources().getColor(R.color.clickedSecun_compVerval), PorterDuff.Mode.SRC_ATOP);
//            button.setTextColor(Color.WHITE);
            verificadores.put("" + v.getId(), true);
            atualizaTotalSecundario(true);
        } else {
            v.getBackground().setColorFilter(getResources().getColor(R.color.colorSecundaria_compVerbal), PorterDuff.Mode.SRC_ATOP);
//            button.setTextColor(Color.BLACK);
            verificadores.put("" + v.getId(), false);
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

    public void autoComplete(Participante participante) {
        Button button;
        for(String x : auxAutoComplete) {
            button = (Button) findViewById(Integer.parseInt(x));
            autoCompleteButtonsPriciapais(button);
        }
        for(String x : auxAutoCompleteSecundario) {
            button = (Button) findViewById(Integer.parseInt(x));
            autoCompleteButtonsSecundarios(button);
        }
    }

    public void autoCompleteButtonsPriciapais(View v) {
        Button button = (Button) v;
        if(verificadores.get(""+ v.getId())) {
            v.getBackground().setColorFilter(getResources().getColor(R.color.clickedPrinc_compVerval), PorterDuff.Mode.SRC_ATOP);
            atualizaTotalPrincipal(true);
        }
    }

    public void autoCompleteButtonsSecundarios(View v) {
        Button button = (Button) v;
        if(verificadores.get(""+ v.getId())) {
            v.getBackground().setColorFilter(getResources().getColor(R.color.clickedSecun_compVerval), PorterDuff.Mode.SRC_ATOP);
            atualizaTotalSecundario(true);
        }
    }

    public void registrar(Participante participante) {
        CompreensaoVerbalObject compVerbalObj = new CompreensaoVerbalObject();
        compVerbalObj.atualizaPrimeiraAvaliacao(verificadores);

        participante.setCompVerbalObject(compVerbalObj);

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
}
