package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.MemoriaEpisodica;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Objetos.MemoriaEpisodicaObject;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hercilio on 26/02/2018.
 */

public class GridActivity extends AppCompatActivity {
    //Dicionario que armazena os botoes de controle selecionados:
    Map<String, Boolean> verificadorBotoesDeControle = new HashMap<>();

    //Dicionario que armazena os botoes selecionados:
    private Map<String, Boolean> verificadoresSemDica = new HashMap<>();
    private Map<String, Boolean> verificadoresComDica = new HashMap<>();

    //Auxiliano auto complete
    private ArrayList<String> auxAutoCompleteSemDica = new ArrayList<>();
    private ArrayList<String> auxAutoCompleteComDica = new ArrayList<>();

    //totais textViews
    private TextView mTotalSemDica, mTotalComDica;

    //totais inteiros
    private int pontuacaoSemDica, pontuacaoComDica;

    //btn concluir
    private Button mBtnConcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoria_eposodica_segunda_fase);

        GridAdapter gridAdapter = new GridAdapter(this);

        GridView gridview = (GridView) findViewById(R.id.gridview_mem_ep);
        gridAdapter.setListener(selecionarItemView);
        gridview.setAdapter(gridAdapter);

        //Totais TextViews
        mTotalSemDica = (TextView) findViewById(R.id.first_value_pontuacao_sem_pista_mem_ep);
        mTotalComDica = (TextView) findViewById(R.id.first_value_pontuacao_com_pista_mem_ep);

        //Totais inteiros
        pontuacaoSemDica = 0;
        pontuacaoComDica = 0;

        //Btn concluir
        mBtnConcluir = (Button) findViewById(R.id.button_grid_mem_ep);

        //Inserindo no discionário a chave para os botoes de controle:
        verificadorBotoesDeControle.put(""+R.id.btn_mem_ep_com_pista, false);
        verificadorBotoesDeControle.put(""+R.id.btn_mem_ep_sem_pista, false);

        //Inserindo no discionário a chave para os botoes:
        verificadoresSemDica.put(""+R.drawable.a_morango, false);
        verificadoresSemDica.put(""+R.drawable.a_igreja, false);
        verificadoresSemDica.put(""+R.drawable.a_garfo, false);
        verificadoresSemDica.put(""+R.drawable.a_violao, false);
        verificadoresSemDica.put(""+R.drawable.b_domino, false);
        verificadoresSemDica.put(""+R.drawable.b_lapis, false);
        verificadoresSemDica.put(""+R.drawable.b_formiga, false);
        verificadoresSemDica.put(""+R.drawable.b_cama, false);
        verificadoresSemDica.put(""+R.drawable.c_martelo, false);
        verificadoresSemDica.put(""+R.drawable.c_tubarao, false);
        verificadoresSemDica.put(""+R.drawable.c_orelha, false);
        verificadoresSemDica.put(""+R.drawable.c_vassoura, false);
        verificadoresSemDica.put(""+R.drawable.d_cenoura, false);
        verificadoresSemDica.put(""+R.drawable.d_calca, false);
        verificadoresSemDica.put(""+R.drawable.d_bicicleta, false);
        verificadoresSemDica.put(""+R.drawable.d_macaco, false);

        verificadoresComDica.put(""+R.drawable.a_morango, false);
        verificadoresComDica.put(""+R.drawable.a_igreja, false);
        verificadoresComDica.put(""+R.drawable.a_garfo, false);
        verificadoresComDica.put(""+R.drawable.a_violao, false);
        verificadoresComDica.put(""+R.drawable.b_domino, false);
        verificadoresComDica.put(""+R.drawable.b_lapis, false);
        verificadoresComDica.put(""+R.drawable.b_formiga, false);
        verificadoresComDica.put(""+R.drawable.b_cama, false);
        verificadoresComDica.put(""+R.drawable.c_martelo, false);
        verificadoresComDica.put(""+R.drawable.c_tubarao, false);
        verificadoresComDica.put(""+R.drawable.c_orelha, false);
        verificadoresComDica.put(""+R.drawable.c_vassoura, false);
        verificadoresComDica.put(""+R.drawable.d_cenoura, false);
        verificadoresComDica.put(""+R.drawable.d_calca, false);
        verificadoresComDica.put(""+R.drawable.d_bicicleta, false);
        verificadoresComDica.put(""+R.drawable.d_macaco, false);

        //Inserir todos os botoes nas bibliotecas
        auxAutoCompleteSemDica.add(""+R.drawable.a_morango);
        auxAutoCompleteSemDica.add(""+R.drawable.a_igreja);
        auxAutoCompleteSemDica.add(""+R.drawable.a_garfo);
        auxAutoCompleteSemDica.add(""+R.drawable.a_violao);
        auxAutoCompleteSemDica.add(""+R.drawable.b_domino);
        auxAutoCompleteSemDica.add(""+R.drawable.b_lapis);
        auxAutoCompleteSemDica.add(""+R.drawable.b_formiga);
        auxAutoCompleteSemDica.add(""+R.drawable.b_cama);
        auxAutoCompleteSemDica.add(""+R.drawable.c_martelo);
        auxAutoCompleteSemDica.add(""+R.drawable.c_tubarao);
        auxAutoCompleteSemDica.add(""+R.drawable.c_orelha);
        auxAutoCompleteSemDica.add(""+R.drawable.c_vassoura);
        auxAutoCompleteSemDica.add(""+R.drawable.d_cenoura);
        auxAutoCompleteSemDica.add(""+R.drawable.d_calca);
        auxAutoCompleteSemDica.add(""+R.drawable.d_bicicleta);
        auxAutoCompleteSemDica.add(""+R.drawable.d_macaco);

        auxAutoCompleteComDica.add(""+R.drawable.a_morango);
        auxAutoCompleteComDica.add(""+R.drawable.a_igreja);
        auxAutoCompleteComDica.add(""+R.drawable.a_garfo);
        auxAutoCompleteComDica.add(""+R.drawable.a_violao);
        auxAutoCompleteComDica.add(""+R.drawable.b_domino);
        auxAutoCompleteComDica.add(""+R.drawable.b_lapis);
        auxAutoCompleteComDica.add(""+R.drawable.b_formiga);
        auxAutoCompleteComDica.add(""+R.drawable.b_cama);
        auxAutoCompleteComDica.add(""+R.drawable.c_martelo);
        auxAutoCompleteComDica.add(""+R.drawable.c_tubarao);
        auxAutoCompleteComDica.add(""+R.drawable.c_orelha);
        auxAutoCompleteComDica.add(""+R.drawable.c_vassoura);
        auxAutoCompleteComDica.add(""+R.drawable.d_cenoura);
        auxAutoCompleteComDica.add(""+R.drawable.d_calca);
        auxAutoCompleteComDica.add(""+R.drawable.d_bicicleta);
        auxAutoCompleteComDica.add(""+R.drawable.d_macaco);

        //Inicia a comunicacao com o banco de dados
        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);
            final Integer[] identificador = (Integer[]) intentFromList.getSerializableExtra(MemoriaEpisodicaLobbyActivity.IDENTIFICA_FASE);

            switch (identificador[0]) {
                case 1:
                    if (participante.getMemEpObject() != null &&
                            (participante.getMemEpObject().getSegundaFaseComDica() != null || participante.getMemEpObject().getSegundaFaseSemDica() != null)) {
                        pontuacaoSemDica = participante.getMemEpObject().getPontuacaoSegundaFaseSemDica();
                        pontuacaoComDica = participante.getMemEpObject().getPontuacaoSegundaFaseComDica();
                        mTotalSemDica.setText("" + pontuacaoSemDica);
                        mTotalComDica.setText("" + pontuacaoComDica);
                        verificadoresSemDica = participante.getMemEpObject().getSegundaFaseSemDica();
                        verificadoresComDica = participante.getMemEpObject().getSegundaFaseComDica();
                    }

                    mBtnConcluir.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getBaseContext(), MemoriaEpisodicaLobbyActivity.class);
                            intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, registrar(participante, 1));
                            startActivity(intent);
                        }
                    });
                    break;
                case 2:
                    if (participante.getMemEpObject() != null &&
                            (participante.getMemEpObject().getTerceiraFaseComDica() != null || participante.getMemEpObject().getTerceiraFaseSemDica() != null)) {
                        pontuacaoSemDica = participante.getMemEpObject().getPontuacaoTerceiraFaseSemDica();
                        pontuacaoComDica = participante.getMemEpObject().getPontuacaoTerceiraFaseComDica();
                        mTotalSemDica.setText("" + pontuacaoSemDica);
                        mTotalComDica.setText("" + pontuacaoComDica);
                        verificadoresSemDica = participante.getMemEpObject().getTerceiraFaseSemDica();
                        verificadoresComDica = participante.getMemEpObject().getTerceiraFaseComDica();
                    }

                    mBtnConcluir.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getBaseContext(), MemoriaEpisodicaLobbyActivity.class);
                            intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, registrar(participante, 2));
                            startActivity(intent);
                        }
                    });
                    break;
                case 3:
                    if (participante.getMemEpObject() != null &&
                            (participante.getMemEpObject().getQuartaFaseComDica() != null || participante.getMemEpObject().getQuartaFaseSemDica() != null)) {
                        pontuacaoSemDica = participante.getMemEpObject().getPontuacaoQuartaFaseSemDica();
                        pontuacaoComDica = participante.getMemEpObject().getPontuacaoQuartaFaseComDica();
                        mTotalSemDica.setText("" + pontuacaoSemDica);
                        mTotalComDica.setText("" + pontuacaoComDica);
                        verificadoresSemDica = participante.getMemEpObject().getQuartaFaseSemDica();
                        verificadoresComDica = participante.getMemEpObject().getQuartaFaseComDica();
                    }

                    mBtnConcluir.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getBaseContext(), MemoriaEpisodicaLobbyActivity.class);
                            intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, registrar(participante, 3));
                            startActivity(intent);
                        }
                    });
                    break;
                case 4:
                    if (participante.getMemEpObject() != null &&
                            (participante.getMemEpObject().getQuintaFaseComDica() != null || participante.getMemEpObject().getQuintaFaseSemDica() != null)) {
                        pontuacaoSemDica = participante.getMemEpObject().getPontuacaoQuintaFaseSemDica();
                        pontuacaoComDica = participante.getMemEpObject().getPontuacaoQuintaFaseComDica();
                        mTotalSemDica.setText("" + pontuacaoSemDica);
                        mTotalComDica.setText("" + pontuacaoComDica);
                        verificadoresSemDica = participante.getMemEpObject().getQuintaFaseSemDica();
                        verificadoresComDica = participante.getMemEpObject().getQuintaFaseComDica();
                    }

                    mBtnConcluir.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getBaseContext(), MemoriaEpisodicaLobbyActivity.class);
                            intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, registrar(participante, 4));
                            startActivity(intent);
                        }
                    });
                    break;
                default:
                    break;
            }


        }

    }

    final OnListFragmentInteractionListener selecionarItemView = new OnListFragmentInteractionListener() {
        @Override
        public void onListFragmentInteraction(ImageButton item, boolean ref) {
            if(ref)
                verificaTipo(item);
            else {
                autoComplete(item);
            }
        }
    };

    /*
     * ColorPrimaty 			=> r:129 g:175 b:145
     * ColorAssent  			=> r:230 g:172 b:39
     *
     * clickedPrinc_compVerval => r:108 g:76 b:0
     * clickedSecun_compVerval => r:42 g:84 b:33
     */

    public void verificaTipo(ImageButton imageButton) {
        //caso a acao seja feita SEM DICA
        if(verificadorBotoesDeControle.get(""+R.id.btn_mem_ep_sem_pista)) {
            //Caso o botao já esteja clicado
            if(verificadoresSemDica.get(""+imageButton.getTag())) {
                atualizaTotalSemDica(false);
                imageButton.setBackgroundColor(Color.LTGRAY);
                verificadoresSemDica.put(""+imageButton.getTag(), false);
            }
            else {
                //caso o botao nao esteja clicado
                atualizaTotalSemDica(true);
                imageButton.setBackgroundColor(Color.rgb(129, 175, 145));
                verificadoresSemDica.put(""+imageButton.getTag(), true);
                if(verificadoresComDica.get(""+imageButton.getTag())) {
                    atualizaTotalComDica(false);
                    verificadoresComDica.put("" + imageButton.getTag(), false);
                }
            }
        }//caso a acao seja feita COM DICA
        else if(verificadorBotoesDeControle.get(""+R.id.btn_mem_ep_com_pista)) {
            //Caso o botao já esteja clicado
            if(verificadoresComDica.get(""+imageButton.getTag())) {
                atualizaTotalComDica(false);
                imageButton.setBackgroundColor(Color.LTGRAY);
                verificadoresComDica.put(""+imageButton.getTag(), false);
            } else {
                //Caso o botao nao esteja clicado
                atualizaTotalComDica(true);
                imageButton.setBackgroundColor(Color.rgb(230, 172, 39));
                verificadoresComDica.put(""+imageButton.getTag(), true);
                if(verificadoresSemDica.get(""+imageButton.getTag())) {
                    atualizaTotalSemDica(false);
                    verificadoresSemDica.put("" + imageButton.getTag(), false);
                }
            }
        }
    }

    public void OnclickSemPista(View v) {
        Button mBtnComPista = (Button) findViewById(R.id.btn_mem_ep_com_pista);
        verificadorBotoesDeControle.put(""+R.id.btn_mem_ep_com_pista, false);
        verificadorBotoesDeControle.put("" + v.getId(), true);
        //colorPrimary
        mBtnComPista.getBackground().setColorFilter(getResources().getColor(R.color.clickedPrinc_compVerval), PorterDuff.Mode.SRC_ATOP);
        //clickedSecun_compVerval
        v.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
    }

    public void OnclickComPista(View v) {
        Button mBtnSemPista = (Button) findViewById(R.id.btn_mem_ep_sem_pista);
        verificadorBotoesDeControle.put(""+R.id.btn_mem_ep_sem_pista, false);
        verificadorBotoesDeControle.put("" + v.getId(), true);
        //ColorPrimary
        mBtnSemPista.getBackground().setColorFilter(getResources().getColor(R.color.clickedSecun_compVerval), PorterDuff.Mode.SRC_ATOP);
        //clickedPrinc_compVerval
        v.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
    }

    public void atualizaTotalSemDica(boolean ref) {
        if(ref)
            pontuacaoSemDica++;
        else
            pontuacaoSemDica--;
        mTotalSemDica.setText(""+ pontuacaoSemDica);
    }

    public void atualizaTotalComDica(boolean ref) {
        if(ref)
            pontuacaoComDica++;
        else
            pontuacaoComDica--;
        mTotalComDica.setText(""+ pontuacaoComDica);
    }

    public void autoComplete(ImageButton img) {
        autoCompleteButtonsSemDica(img);
        autoCompleteButtonsComDica(img);
    }

    public void autoCompleteButtonsSemDica(ImageButton v) {
        if(verificadoresSemDica.get(""+ v.getTag())) {
            v.setBackgroundColor(Color.rgb(129, 175, 145));
        }
    }

    public void autoCompleteButtonsComDica(ImageButton v) {
        if(verificadoresComDica.get(""+ v.getTag())) {
            v.setBackgroundColor(Color.rgb(230, 172, 39));
        }
    }

    public Participante registrar(Participante participante, int nroIndicador) {
        MemoriaEpisodicaObject memEpObj;

        switch (nroIndicador) {
            case 1:
                memEpObj = participante.getMemEpObject();
                memEpObj.setSegundaFaseSemDica(verificadoresSemDica);
                memEpObj.setSegundaFaseComDica(verificadoresComDica);
                memEpObj.setPontuacaoSegundaFaseComDica(pontuacaoSemDica);
                memEpObj.setPontuacaoSegundaFaseSemDica(pontuacaoComDica);
                participante.setMemEpObject(memEpObj);
                break;
            case 2:
                memEpObj = participante.getMemEpObject();
                memEpObj.setTerceiraFaseSemDica(verificadoresSemDica);
                memEpObj.setTerceiraFaseComDica(verificadoresComDica);
                memEpObj.setPontuacaoTerceiraFaseComDica(pontuacaoSemDica);
                memEpObj.setPontuacaoTerceiraFaseSemDica(pontuacaoComDica);
                participante.setMemEpObject(memEpObj);
                break;
            case 3:
                memEpObj = participante.getMemEpObject();
                memEpObj.setQuartaFaseSemDica(verificadoresSemDica);
                memEpObj.setQuartaFaseComDica(verificadoresComDica);
                memEpObj.setPontuacaoQuartaFaseComDica(pontuacaoSemDica);
                memEpObj.setPontuacaoQuartaFaseSemDica(pontuacaoComDica);
                participante.setMemEpObject(memEpObj);
                break;
            case 4:
                memEpObj = participante.getMemEpObject();
                memEpObj.setQuintaFaseSemDica(verificadoresSemDica);
                memEpObj.setQuintaFaseComDica(verificadoresComDica);
                memEpObj.setPontuacaoQuintaFaseComDica(pontuacaoSemDica);
                memEpObj.setPontuacaoQuintaFaseSemDica(pontuacaoComDica);
                participante.setMemEpObject(memEpObj);
                break;
            default:
                break;
        }

        FirebaseDatabase mFirebaseDatabase;
        final DatabaseReference mParticipanteDatabaseReference;

        //Cria o caminho que garantirá o acesso somente aos participantes do usuário logado
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        mParticipanteDatabaseReference = mFirebaseDatabase.getReference().child("users").child(auth.getCurrentUser().getUid()).child("participantes");

        //Criar uma váriavel final estava criando um loop no onDataChange
        //final Participante partAux = participante;
        mParticipanteDatabaseReference.child(participante.getCpf()).setValue(participante);

        return participante;
    }

}
