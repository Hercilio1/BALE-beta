package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.MemoriaEpisodica;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoVerbal.CompreensaoVerbalLobbyActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Objetos.CompreensaoVerbalObject;
import com.example.hercilio.appwithfirebase.Objetos.HabitosLeituraEscritaObject;
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
 * Created by Hercilio on 27/02/2018.
 */

public class MemoriaEpisodicaPrimeiraFaseActivity extends AppCompatActivity {

    public static final String IDENTIFICADOR_DA_SEQUENCIA_MEM_EP = "Integer[]";

    //Imagem principal
    private ImageView mImagemPrincipal;

    //Small images
    private ImageView mSmallImage1, mSmallImage2, mSmallImage3, mSmallImage4;


    //Botoes de indicacao
    private TextView mBtnIndicacao1, mBtnIndicacao2, mBtnIndicacao3, mBtnIndicacao4;

    //Botoes de nomeacao
    private TextView mBtnNomeacao1, mBtnNomeacao2, mBtnNomeacao3, mBtnNomeacao4;

    //Tipo do objeto
    private TextView mTipoObjeto1, mTipoObjeto2, mTipoObjeto3, mTipoObjeto4;

    //Nome do objeto
    private TextView mNomeObjeto1, mNomeObjeto2, mNomeObjeto3, mNomeObjeto4;

    //Pontuaçao de indicacao
    private TextView mPontuacaoIndicacao;

    //Pontuacao de nomeacao
    private TextView mPontuacaoNomeacao;

    //Total final:
    private int totalIndicacao, totalNomeacao;

    //Botao continuar:
    private Button btnProsseguir;

    //Strings das textViews
    String[] tipoObjetos = {"Fruta", "Lugar/Construção", "Utensílio de cozinha", "Instrumento musical"
            , "Jogo", "Material escolar", "Inseto", "Móvel"
            , "Ferramenta", "Peixe", "Parte do corpo", "Utensílio de limpeza"
            , "Legume", "Vestimenta", "Meio de transporte", "Mamífero"};

    String[] nomeObjetos = {"Morango", "Igreja", "Garfo", "Violão"
            , "Dominó", "Lápis", "Formiga", "Cama"
            , "Martelo", "Tubarão", "Orelha", "Vassoura"
            , "Cenoura", "Calça", "Bicicleta", "Macaco"};

    //Dicionario que armazena os botoes selecionados:
    private Map<String, Boolean> verificadores = new HashMap<>();

    //Auxiliano auto complete
    private ArrayList<String> auxAutoCompleteIndicacao = new ArrayList<>();
    private ArrayList<String> auxAutoCompleteNomeacao = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoria_eposodica_primeira_fase);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //Imagens:
        mImagemPrincipal = (ImageView) findViewById(R.id.imagem_mem_ep_principal);
        mSmallImage1 = (ImageView) findViewById(R.id.img1_image_view);
        mSmallImage2 = (ImageView) findViewById(R.id.img2_image_view);
        mSmallImage3 = (ImageView) findViewById(R.id.img3_image_view);
        mSmallImage4 = (ImageView) findViewById(R.id.img4_image_view);

        //Botoes:
        mBtnIndicacao1 = (Button) findViewById(R.id.img1_button_indicou);
        mBtnIndicacao2 = (Button) findViewById(R.id.img2_button_indicou);
        mBtnIndicacao3 = (Button) findViewById(R.id.img3_button_indicou);
        mBtnIndicacao4 = (Button) findViewById(R.id.img4_button_indicou);

        mBtnNomeacao1 = (Button) findViewById(R.id.img1_button_nomeou);
        mBtnNomeacao2 = (Button) findViewById(R.id.img2_button_nomeou);
        mBtnNomeacao3 = (Button) findViewById(R.id.img3_button_nomeou);
        mBtnNomeacao4 = (Button) findViewById(R.id.img4_button_nomeou);

        //TextViews das imagens
        mTipoObjeto1 = (TextView) findViewById(R.id.small_image_1_tipo);
        mTipoObjeto2 = (TextView) findViewById(R.id.small_image_2_tipo);
        mTipoObjeto3 = (TextView) findViewById(R.id.small_image_3_tipo);
        mTipoObjeto4 = (TextView) findViewById(R.id.small_image_4_tipo);

        mNomeObjeto1 = (TextView) findViewById(R.id.small_image_1_nome);
        mNomeObjeto2 = (TextView) findViewById(R.id.small_image_2_nome);
        mNomeObjeto3 = (TextView) findViewById(R.id.small_image_3_nome);
        mNomeObjeto4 = (TextView) findViewById(R.id.small_image_4_nome);

        //TextView de pontuação
        mPontuacaoIndicacao = (TextView) findViewById(R.id.first_value_indicacao_mem_ep_fase1);
        mPontuacaoNomeacao = (TextView) findViewById(R.id.first_value_nomeacao_mem_ep_fase1);

        //Botao prosseguir
        btnProsseguir = (Button) findViewById(R.id.btn_continuar_mem_ep_primeira_fase);

        //Preparação das estruturas de dados:
        //Botoes de indicacao
        verificadores.put("" + R.id.img1_button_indicou, false);
        auxAutoCompleteIndicacao.add("" + R.id.img1_button_indicou);

        verificadores.put("" + R.id.img2_button_indicou, false);
        auxAutoCompleteIndicacao.add("" + R.id.img2_button_indicou);

        verificadores.put("" + R.id.img3_button_indicou, false);
        auxAutoCompleteIndicacao.add("" + R.id.img3_button_indicou);

        verificadores.put("" + R.id.img4_button_indicou, false);
        auxAutoCompleteIndicacao.add("" + R.id.img4_button_indicou);

        //Botoes de nomeação
        verificadores.put("" + R.id.img1_button_nomeou, false);
        auxAutoCompleteNomeacao.add("" + R.id.img1_button_nomeou);

        verificadores.put("" + R.id.img2_button_nomeou, false);
        auxAutoCompleteNomeacao.add("" + R.id.img2_button_nomeou);

        verificadores.put("" + R.id.img3_button_nomeou, false);
        auxAutoCompleteNomeacao.add("" + R.id.img3_button_nomeou);

        verificadores.put("" + R.id.img4_button_nomeou, false);
        auxAutoCompleteNomeacao.add("" + R.id.img4_button_nomeou);

        //Indica qual sequencia de imagens está
        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Integer[] indicador = (Integer[]) intentFromList.getSerializableExtra(IDENTIFICADOR_DA_SEQUENCIA_MEM_EP);
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);

            switch (indicador[0]) {
                case 1:
                    mImagemPrincipal.setImageResource(R.drawable.mem_ep_fig_a);
                    mSmallImage1.setImageResource(R.drawable.a_morango);
                    mSmallImage2.setImageResource(R.drawable.a_igreja);
                    mSmallImage3.setImageResource(R.drawable.a_garfo);
                    mSmallImage4.setImageResource(R.drawable.a_violao);

                    mTipoObjeto1.setText(tipoObjetos[0]);
                    mTipoObjeto2.setText(tipoObjetos[1]);
                    mTipoObjeto3.setText(tipoObjetos[2]);
                    mTipoObjeto4.setText(tipoObjetos[3]);

                    mNomeObjeto1.setText(nomeObjetos[0]);
                    mNomeObjeto2.setText(nomeObjetos[1]);
                    mNomeObjeto3.setText(nomeObjetos[2]);
                    mNomeObjeto4.setText(nomeObjetos[3]);

                    if (participante.getMemEpObject() != null && participante.getMemEpObject().getPrimeiraFaseAnalise1() != null) {
                        totalIndicacao = participante.getMemEpObject().getPontuacaoIndicacao();
                        totalNomeacao = participante.getMemEpObject().getPontuacaoNomeacao();
                        mPontuacaoIndicacao.setText(""+totalIndicacao);
                        mPontuacaoNomeacao.setText(""+totalNomeacao);
                        verificadores = participante.getMemEpObject().getPrimeiraFaseAnalise1();
                        autoComplete();
                    } else if(participante.getMemEpObject() != null &&
                            (participante.getMemEpObject().getPontuacaoIndicacao() != 0 || participante.getMemEpObject().getPontuacaoNomeacao() != 0)) {
                        totalIndicacao = participante.getMemEpObject().getPontuacaoIndicacao();
                        totalNomeacao = participante.getMemEpObject().getPontuacaoNomeacao();
                        mPontuacaoIndicacao.setText(""+totalIndicacao);
                        mPontuacaoNomeacao.setText(""+totalNomeacao);
                    }

                    btnProsseguir.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Integer[] proxIndicador = new Integer[1];
                            proxIndicador[0] = 2;
                            Intent intent = new Intent(getBaseContext(), MemoriaEpisodicaPrimeiraFaseActivity.class);
                            intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, registrar(participante, 1));
                            intent.putExtra(IDENTIFICADOR_DA_SEQUENCIA_MEM_EP, proxIndicador);
                            startActivity(intent);
                        }
                    });

                    mImagemPrincipal.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Integer[] nroAnalise = new Integer[1];
                            nroAnalise[0] = 1;
                            Intent intent = new Intent(getBaseContext(), ImagemExpandidaActivity.class);
                            intent.putExtra(ImagemExpandidaActivity.IMAGEM_EXPANDIDA, nroAnalise);
                            startActivity(intent);
                        }
                    });
                    break;

                case 2:
                    mImagemPrincipal.setImageResource(R.drawable.mem_ep_fig_b);
                    mSmallImage1.setImageResource(R.drawable.b_domino);
                    mSmallImage2.setImageResource(R.drawable.b_lapis);
                    mSmallImage3.setImageResource(R.drawable.b_formiga);
                    mSmallImage4.setImageResource(R.drawable.b_cama);

                    mTipoObjeto1.setText(tipoObjetos[4]);
                    mTipoObjeto2.setText(tipoObjetos[5]);
                    mTipoObjeto3.setText(tipoObjetos[6]);
                    mTipoObjeto4.setText(tipoObjetos[7]);

                    mNomeObjeto1.setText(nomeObjetos[4]);
                    mNomeObjeto2.setText(nomeObjetos[5]);
                    mNomeObjeto3.setText(nomeObjetos[6]);
                    mNomeObjeto4.setText(nomeObjetos[7]);

                    if (participante.getMemEpObject() != null && participante.getMemEpObject().getPrimeiraFaseAnalise2()!= null) {
                        totalIndicacao = participante.getMemEpObject().getPontuacaoIndicacao();
                        totalNomeacao = participante.getMemEpObject().getPontuacaoNomeacao();
                        mPontuacaoIndicacao.setText(""+totalIndicacao);
                        mPontuacaoNomeacao.setText(""+totalNomeacao);
                        verificadores = participante.getMemEpObject().getPrimeiraFaseAnalise2();
                        autoComplete();
                    } else if(participante.getMemEpObject().getPontuacaoIndicacao() != 0 || participante.getMemEpObject().getPontuacaoNomeacao() != 0) {
                        totalIndicacao = participante.getMemEpObject().getPontuacaoIndicacao();
                        totalNomeacao = participante.getMemEpObject().getPontuacaoNomeacao();
                        mPontuacaoIndicacao.setText(""+totalIndicacao);
                        mPontuacaoNomeacao.setText(""+totalNomeacao);
                    }

                    btnProsseguir.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Integer[] proxIndicador = new Integer[1];
                            proxIndicador[0] = 3;
                            Intent intent = new Intent(getBaseContext(), MemoriaEpisodicaPrimeiraFaseActivity.class);
                            intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, registrar(participante, 2));
                            intent.putExtra(IDENTIFICADOR_DA_SEQUENCIA_MEM_EP, proxIndicador);
                            startActivity(intent);
                        }
                    });

                    mImagemPrincipal.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Integer[] nroAnalise = new Integer[1];
                            nroAnalise[0] = 2;
                            Intent intent = new Intent(getBaseContext(), ImagemExpandidaActivity.class);
                            intent.putExtra(ImagemExpandidaActivity.IMAGEM_EXPANDIDA, nroAnalise);
                            startActivity(intent);
                        }
                    });
                    break;

                case 3:
                    mImagemPrincipal.setImageResource(R.drawable.mem_ep_fig_c);
                    mSmallImage1.setImageResource(R.drawable.c_martelo);
                    mSmallImage2.setImageResource(R.drawable.c_tubarao);
                    mSmallImage3.setImageResource(R.drawable.c_orelha);
                    mSmallImage4.setImageResource(R.drawable.c_vassoura);

                    mTipoObjeto1.setText(tipoObjetos[8]);
                    mTipoObjeto2.setText(tipoObjetos[9]);
                    mTipoObjeto3.setText(tipoObjetos[10]);
                    mTipoObjeto4.setText(tipoObjetos[11]);

                    mNomeObjeto1.setText(nomeObjetos[8]);
                    mNomeObjeto2.setText(nomeObjetos[9]);
                    mNomeObjeto3.setText(nomeObjetos[10]);
                    mNomeObjeto4.setText(nomeObjetos[11]);

                    if (participante.getMemEpObject() != null && participante.getMemEpObject().getPrimeiraFaseAnalise3() != null) {
                        totalIndicacao = participante.getMemEpObject().getPontuacaoIndicacao();
                        totalNomeacao = participante.getMemEpObject().getPontuacaoNomeacao();
                        mPontuacaoIndicacao.setText(""+totalIndicacao);
                        mPontuacaoNomeacao.setText(""+totalNomeacao);
                        verificadores = participante.getMemEpObject().getPrimeiraFaseAnalise3();
                        autoComplete();
                    } else if(participante.getMemEpObject().getPontuacaoIndicacao() != 0 || participante.getMemEpObject().getPontuacaoNomeacao() != 0) {
                        totalIndicacao = participante.getMemEpObject().getPontuacaoIndicacao();
                        totalNomeacao = participante.getMemEpObject().getPontuacaoNomeacao();
                        mPontuacaoIndicacao.setText(""+totalIndicacao);
                        mPontuacaoNomeacao.setText(""+totalNomeacao);
                    }

                    btnProsseguir.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Integer[] proxIndicador = new Integer[1];
                            proxIndicador[0] = 4;
                            Intent intent = new Intent(getBaseContext(), MemoriaEpisodicaPrimeiraFaseActivity.class);
                            intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, registrar(participante, 3));
                            intent.putExtra(IDENTIFICADOR_DA_SEQUENCIA_MEM_EP, proxIndicador);
                            startActivity(intent);
                        }
                    });

                    mImagemPrincipal.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Integer[] nroAnalise = new Integer[1];
                            nroAnalise[0] = 3;
                            Intent intent = new Intent(getBaseContext(), ImagemExpandidaActivity.class);
                            intent.putExtra(ImagemExpandidaActivity.IMAGEM_EXPANDIDA, nroAnalise);
                            startActivity(intent);
                        }
                    });
                    break;
                case 4:
                    mImagemPrincipal.setImageResource(R.drawable.mem_ep_fig_d);
                    mSmallImage1.setImageResource(R.drawable.d_cenoura);
                    mSmallImage2.setImageResource(R.drawable.d_calca);
                    mSmallImage3.setImageResource(R.drawable.d_bicicleta);
                    mSmallImage4.setImageResource(R.drawable.d_macaco);

                    mTipoObjeto1.setText(tipoObjetos[12]);
                    mTipoObjeto2.setText(tipoObjetos[13]);
                    mTipoObjeto3.setText(tipoObjetos[14]);
                    mTipoObjeto4.setText(tipoObjetos[15]);

                    mNomeObjeto1.setText(nomeObjetos[12]);
                    mNomeObjeto2.setText(nomeObjetos[13]);
                    mNomeObjeto3.setText(nomeObjetos[14]);
                    mNomeObjeto4.setText(nomeObjetos[15]);

                    if (participante.getMemEpObject() != null && participante.getMemEpObject().getPrimeiraFaseAnalise4() != null) {
                        totalIndicacao = participante.getMemEpObject().getPontuacaoIndicacao();
                        totalNomeacao = participante.getMemEpObject().getPontuacaoNomeacao();
                        mPontuacaoIndicacao.setText(""+totalIndicacao);
                        mPontuacaoNomeacao.setText(""+totalNomeacao);
                        verificadores = participante.getMemEpObject().getPrimeiraFaseAnalise4();
                        autoComplete();
                    } else if(participante.getMemEpObject().getPontuacaoIndicacao() != 0 || participante.getMemEpObject().getPontuacaoNomeacao() != 0) {
                        totalIndicacao = participante.getMemEpObject().getPontuacaoIndicacao();
                        totalNomeacao = participante.getMemEpObject().getPontuacaoNomeacao();
                        mPontuacaoIndicacao.setText(""+totalIndicacao);
                        mPontuacaoNomeacao.setText(""+totalNomeacao);
                    }

                    btnProsseguir.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            boolean[] bool = new boolean[4];
                            bool[0] = true;
                            Intent intent = new Intent(getBaseContext(), MemoriaEpisodicaLobbyActivity.class);
                            intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, registrar(participante, 4));
                            intent.putExtra(MemoriaEpisodicaLobbyActivity.IDENTIFICA_INTERVALO, bool);
                            startActivity(intent);

                        }
                    });

                    mImagemPrincipal.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Integer[] nroAnalise = new Integer[1];
                            nroAnalise[0] = 4;
                            Intent intent = new Intent(getBaseContext(), ImagemExpandidaActivity.class);
                            intent.putExtra(ImagemExpandidaActivity.IMAGEM_EXPANDIDA, nroAnalise);
                            startActivity(intent);
                        }
                    });
                    break;
                default:
                    break;
            }
        }

    }

    public void onClickIndicou(View v) {
        Button button = (Button) v;

        if(!verificadores.get(""+v.getId())) {
            v.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            verificadores.put("" + v.getId(), true);
            atualizaTotalIndicacao(true);
        } else {
            v.getBackground().clearColorFilter();
            verificadores.put("" + v.getId(), false);
            atualizaTotalIndicacao(false);
        }
    }

    public void onClickNomeou(View v) {
        Button button = (Button) v;
        if(!verificadores.get(""+
                v.getId())) {
            v.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            verificadores.put("" + v.getId(), true);
            atualizaTotalNomeacao(true);
        } else {
            v.getBackground().clearColorFilter();
            verificadores.put("" + v.getId(), false);
            atualizaTotalNomeacao(false);
        }
    }

    public void atualizaTotalIndicacao(boolean ref) {
        if(ref)
            totalIndicacao++;
        else
            totalIndicacao--;
        mPontuacaoIndicacao.setText(""+totalIndicacao);
    }

    public void atualizaTotalNomeacao(boolean ref) {
        if(ref)
            totalNomeacao++;
        else
            totalNomeacao--;
        mPontuacaoNomeacao.setText(""+totalNomeacao);
    }

    public void autoComplete() {
        Button button;
        for(String x : auxAutoCompleteIndicacao) {
            button = (Button) findViewById(Integer.parseInt(x));
            autoCompleteButtonsIndicacao(button);
        }
        for(String x : auxAutoCompleteNomeacao) {
            button = (Button) findViewById(Integer.parseInt(x));
            autoCompleteButtonsNomeacao(button);
        }
    }

    public void autoCompleteButtonsIndicacao(View v) {
        Button button = (Button) v;
        if(verificadores.get(""+ v.getId())) {
            v.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
//            atualizaTotalIndicacao(true);
        }
    }

    public void autoCompleteButtonsNomeacao(View v) {
        Button button = (Button) v;
        if(verificadores.get(""+ v.getId())) {
            v.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
//            atualizaTotalNomeacao(true);
        }
    }

    public Participante registrar(Participante participante, int nroAnalise) {
        MemoriaEpisodicaObject memEpObj;
        switch (nroAnalise) {

            case 1:
                if(participante.getMemEpObject() == null) {
                    memEpObj = new MemoriaEpisodicaObject();
                    memEpObj.setPrimeiraFaseAnalise1(verificadores);
                    memEpObj.setPontuacaoIndicacao(totalIndicacao);
                    memEpObj.setPontuacaoNomeacao(totalNomeacao);
                    participante.setMemEpObject(memEpObj);
                } else {
                    memEpObj = participante.getMemEpObject();
                    memEpObj.setPrimeiraFaseAnalise1(verificadores);
                    memEpObj.setPontuacaoIndicacao(totalIndicacao);
                    memEpObj.setPontuacaoNomeacao(totalNomeacao);
                    participante.setMemEpObject(memEpObj);
                }
                break;
            case 2:
                memEpObj = participante.getMemEpObject();
                memEpObj.setPrimeiraFaseAnalise2(verificadores);
                memEpObj.setPontuacaoIndicacao(totalIndicacao);
                memEpObj.setPontuacaoNomeacao(totalNomeacao);
                participante.setMemEpObject(memEpObj);
                break;
            case 3:
                memEpObj = participante.getMemEpObject();
                memEpObj.setPrimeiraFaseAnalise3(verificadores);
                memEpObj.setPontuacaoIndicacao(totalIndicacao);
                memEpObj.setPontuacaoNomeacao(totalNomeacao);
                participante.setMemEpObject(memEpObj);
                break;
            case 4:
                memEpObj = participante.getMemEpObject();
                memEpObj.setPrimeiraFaseAnalise4(verificadores);
                memEpObj.setPontuacaoIndicacao(totalIndicacao);
                memEpObj.setPontuacaoNomeacao(totalNomeacao);
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
