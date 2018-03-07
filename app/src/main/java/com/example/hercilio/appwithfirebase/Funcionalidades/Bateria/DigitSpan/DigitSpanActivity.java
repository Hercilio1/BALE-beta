package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.DigitSpan;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.MemoriaEpisodica.ImagemExpandidaActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.MemoriaEpisodica.MemoriaEpisodicaLobbyActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.MemoriaEpisodica.MemoriaEpisodicaPrimeiraFaseActivity;
import com.example.hercilio.appwithfirebase.Objetos.DigitSpanObject;
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
 * Created by Hercilio on 07/03/2018.
 */

public class DigitSpanActivity extends AppCompatActivity {

    //Buttons Forward:
    private Button mForwardButton1Option1, mForwardButton2Option1
            , mForwardButton1Option2, mForwardButton2Option2
            , mForwardButton1Option3, mForwardButton2Option3
            , mForwardButton1Option4, mForwardButton2Option4
            , mForwardButton1Option5, mForwardButton2Option5
            , mForwardButton1Option6, mForwardButton2Option6
            , mForwardButton1Option7, mForwardButton2Option7
            , mForwardButton1Option8, mForwardButton2Option8;

    //Buttons Backward:
    private Button mBackwardButton1Option1, mBackwardButton2Option1
            , mBackwardButton1Option2, mBackwardButton2Option2
            , mBackwardButton1Option3, mBackwardButton2Option3
            , mBackwardButton1Option4, mBackwardButton2Option4
            , mBackwardButton1Option5, mBackwardButton2Option5
            , mBackwardButton1Option6, mBackwardButton2Option6
            , mBackwardButton1Option7, mBackwardButton2Option7;

    //Pontuaçao forward, backward and total:
    private TextView mPontuacaoForward, mPontuacaoBackward, mPontuacaoTotal;

    //Totais forward, backward and total:
    private int totalForward, totalBackward, totalTotal;

    //Botao continuar:
    private Button mBtnProsseguir;

    //Dicionario que armazena os botoes selecionados:
    private Map<String, Boolean> verificadores = new HashMap<>();

    //Auxiliano auto complete
    private ArrayList<String> auxAutoCompleteForward = new ArrayList<>();
    private ArrayList<String> auxAutoCompleteBackward = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digit_span);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //Buttons Forward:
        mForwardButton1Option1 = (Button) findViewById(R.id.digit_span_layout1_button1_nvl_1);
        mForwardButton2Option1 = (Button) findViewById(R.id.digit_span_layout1_button2_nvl_1);
        mForwardButton1Option2 = (Button) findViewById(R.id.digit_span_layout1_button1_nvl_2);
        mForwardButton2Option2 = (Button) findViewById(R.id.digit_span_layout1_button2_nvl_2);
        mForwardButton1Option3 = (Button) findViewById(R.id.digit_span_layout1_button1_nvl_3);
        mForwardButton2Option3 = (Button) findViewById(R.id.digit_span_layout1_button2_nvl_3);
        mForwardButton1Option4 = (Button) findViewById(R.id.digit_span_layout1_button1_nvl_4);
        mForwardButton2Option4 = (Button) findViewById(R.id.digit_span_layout1_button2_nvl_4);
        mForwardButton1Option5 = (Button) findViewById(R.id.digit_span_layout1_button1_nvl_5);
        mForwardButton2Option5 = (Button) findViewById(R.id.digit_span_layout1_button2_nvl_5);
        mForwardButton1Option6 = (Button) findViewById(R.id.digit_span_layout1_button1_nvl_6);
        mForwardButton2Option6 = (Button) findViewById(R.id.digit_span_layout1_button2_nvl_6);
        mForwardButton1Option7 = (Button) findViewById(R.id.digit_span_layout1_button1_nvl_7);
        mForwardButton2Option7 = (Button) findViewById(R.id.digit_span_layout1_button2_nvl_7);
        mForwardButton1Option8 = (Button) findViewById(R.id.digit_span_layout1_button1_nvl_8);
        mForwardButton2Option8 = (Button) findViewById(R.id.digit_span_layout1_button2_nvl_8);

        //Buttons Backward:
        mBackwardButton1Option1 = (Button) findViewById(R.id.digit_span_layout2_button1_nvl_1);
        mBackwardButton2Option1 = (Button) findViewById(R.id.digit_span_layout2_button2_nvl_1);
        mBackwardButton1Option2 = (Button) findViewById(R.id.digit_span_layout2_button1_nvl_2);
        mBackwardButton2Option2 = (Button) findViewById(R.id.digit_span_layout2_button2_nvl_2);
        mBackwardButton1Option3 = (Button) findViewById(R.id.digit_span_layout2_button1_nvl_3);
        mBackwardButton2Option3 = (Button) findViewById(R.id.digit_span_layout2_button2_nvl_3);
        mBackwardButton1Option4 = (Button) findViewById(R.id.digit_span_layout2_button1_nvl_4);
        mBackwardButton2Option4 = (Button) findViewById(R.id.digit_span_layout2_button2_nvl_4);
        mBackwardButton1Option5 = (Button) findViewById(R.id.digit_span_layout2_button1_nvl_5);
        mBackwardButton2Option5 = (Button) findViewById(R.id.digit_span_layout2_button2_nvl_5);
        mBackwardButton1Option6 = (Button) findViewById(R.id.digit_span_layout2_button1_nvl_6);
        mBackwardButton2Option6 = (Button) findViewById(R.id.digit_span_layout2_button2_nvl_6);
        mBackwardButton1Option7 = (Button) findViewById(R.id.digit_span_layout2_button1_nvl_7);
        mBackwardButton2Option7 = (Button) findViewById(R.id.digit_span_layout2_button2_nvl_7);

        //TextView de pontuação
        mPontuacaoForward = (TextView) findViewById(R.id.total_number1_forward);
        mPontuacaoBackward = (TextView) findViewById(R.id.total_number1_backward);
        mPontuacaoTotal = (TextView) findViewById(R.id.total_number1_digit_span);

        //Botao prosseguir
        mBtnProsseguir = (Button) findViewById(R.id.btn_continuar_digit_span);

        //Preparação das estruturas de dados:
        //Botoes de forward
        verificadores.put(""+mForwardButton1Option1.getId(), false);
        auxAutoCompleteForward.add("" +mForwardButton1Option1.getId());
        verificadores.put(""+mForwardButton2Option1.getId(), false);
        auxAutoCompleteForward.add("" +mForwardButton2Option1.getId());

        verificadores.put(""+mForwardButton1Option2.getId(), false);
        auxAutoCompleteForward.add("" +mForwardButton1Option2.getId());
        verificadores.put(""+mForwardButton2Option2.getId(), false);
        auxAutoCompleteForward.add("" +mForwardButton2Option2.getId());

        verificadores.put(""+mForwardButton1Option3.getId(), false);
        auxAutoCompleteForward.add("" +mForwardButton1Option3.getId());
        verificadores.put(""+mForwardButton2Option3.getId(), false);
        auxAutoCompleteForward.add("" +mForwardButton2Option3.getId());

        verificadores.put(""+mForwardButton1Option4.getId(), false);
        auxAutoCompleteForward.add("" +mForwardButton1Option4.getId());
        verificadores.put(""+mForwardButton2Option4.getId(), false);
        auxAutoCompleteForward.add("" +mForwardButton2Option4.getId());

        verificadores.put(""+mForwardButton1Option5.getId(), false);
        auxAutoCompleteForward.add("" +mForwardButton1Option5.getId());
        verificadores.put(""+mForwardButton2Option5.getId(), false);
        auxAutoCompleteForward.add("" +mForwardButton2Option5.getId());

        verificadores.put(""+mForwardButton1Option6.getId(), false);
        auxAutoCompleteForward.add("" +mForwardButton1Option6.getId());
        verificadores.put(""+mForwardButton2Option6.getId(), false);
        auxAutoCompleteForward.add("" +mForwardButton2Option6.getId());

        verificadores.put(""+mForwardButton1Option7.getId(), false);
        auxAutoCompleteForward.add("" +mForwardButton1Option7.getId());
        verificadores.put(""+mForwardButton2Option7.getId(), false);
        auxAutoCompleteForward.add("" +mForwardButton2Option7.getId());

        verificadores.put(""+mForwardButton1Option8.getId(), false);
        auxAutoCompleteForward.add("" +mForwardButton1Option8.getId());
        verificadores.put(""+mForwardButton2Option8.getId(), false);
        auxAutoCompleteForward.add("" +mForwardButton2Option8.getId());


        //Botoes de nomeação
        verificadores.put("" + mBackwardButton1Option1.getId(), false);
        auxAutoCompleteBackward.add("" + mBackwardButton1Option1.getId());
        verificadores.put("" + mBackwardButton2Option1.getId(), false);
        auxAutoCompleteBackward.add("" + mBackwardButton2Option1.getId());

        verificadores.put("" + mBackwardButton1Option2.getId(), false);
        auxAutoCompleteBackward.add("" + mBackwardButton1Option2.getId());
        verificadores.put("" + mBackwardButton2Option2.getId(), false);
        auxAutoCompleteBackward.add("" + mBackwardButton2Option2.getId());

        verificadores.put("" + mBackwardButton1Option3.getId(), false);
        auxAutoCompleteBackward.add("" + mBackwardButton1Option3.getId());
        verificadores.put("" + mBackwardButton2Option3.getId(), false);
        auxAutoCompleteBackward.add("" + mBackwardButton2Option3.getId());

        verificadores.put("" + mBackwardButton1Option3.getId(), false);
        auxAutoCompleteBackward.add("" + mBackwardButton1Option3.getId());
        verificadores.put("" + mBackwardButton2Option3.getId(), false);
        auxAutoCompleteBackward.add("" + mBackwardButton2Option3.getId());

        verificadores.put("" + mBackwardButton1Option4.getId(), false);
        auxAutoCompleteBackward.add("" + mBackwardButton1Option4.getId());
        verificadores.put("" + mBackwardButton2Option4.getId(), false);
        auxAutoCompleteBackward.add("" + mBackwardButton2Option4.getId());

        verificadores.put("" + mBackwardButton1Option5.getId(), false);
        auxAutoCompleteBackward.add("" + mBackwardButton1Option5.getId());
        verificadores.put("" + mBackwardButton2Option5.getId(), false);
        auxAutoCompleteBackward.add("" + mBackwardButton2Option5.getId());

        verificadores.put("" + mBackwardButton1Option6.getId(), false);
        auxAutoCompleteBackward.add("" + mBackwardButton1Option6.getId());
        verificadores.put("" + mBackwardButton2Option6.getId(), false);
        auxAutoCompleteBackward.add("" + mBackwardButton2Option6.getId());

        verificadores.put("" + mBackwardButton1Option7.getId(), false);
        auxAutoCompleteBackward.add("" + mBackwardButton1Option7.getId());
        verificadores.put("" + mBackwardButton2Option7.getId(), false);
        auxAutoCompleteBackward.add("" + mBackwardButton2Option7.getId());


        //Indica qual sequencia de imagens está
        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);

            if(participante.getDigitSpanObject() != null) {
                verificadores = participante.getDigitSpanObject().getVerificadores();
                totalForward = participante.getDigitSpanObject().getValorTotalForward();
                totalBackward = participante.getDigitSpanObject().getValorTotalBackward();
                totalTotal = totalForward+totalBackward;

                mPontuacaoForward.setText(""+totalForward);
                mPontuacaoBackward.setText(""+totalBackward);
                mPontuacaoTotal.setText(""+totalTotal);

                autoComplete();
            }

            mBtnProsseguir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registrar(participante);
                    Intent intent = new Intent(getBaseContext(), BaleLobbyActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    startActivity(intent);
                }
            });

        }

    }

    public void onClickForward(View v) {
        Button button = (Button) v;

        if(!verificadores.get(""+v.getId())) {
            v.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            verificadores.put("" + v.getId(), true);
            atualizaVeficadoresDeNivelForward();
            atualizaTotalForward(true);
        } else {
            v.getBackground().clearColorFilter();
            verificadores.put("" + v.getId(), false);
            atualizaVeficadoresDeNivelForward();
            atualizaTotalForward(false);
        }
    }

    public void onClickBackward(View v) {
        Button button = (Button) v;
        if(!verificadores.get(""+v.getId())) {
            v.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            verificadores.put("" + v.getId(), true);
            atualizaVeficadoresDeNivelBackward();
            atualizaTotalBackward(true);
        } else {
            v.getBackground().clearColorFilter();
            verificadores.put("" + v.getId(), false);
            atualizaVeficadoresDeNivelBackward();
            atualizaTotalBackward(false);
        }
    }

    public void atualizaVeficadoresDeNivelForward() {
        if(!verificadores.get(mForwardButton1Option1.getId()+"") && !verificadores.get(mForwardButton2Option1.getId()+"")) {
            mForwardButton1Option2.setEnabled(false);
            mForwardButton2Option2.setEnabled(false);
        } else {
            mForwardButton1Option2.setEnabled(true);
            mForwardButton2Option2.setEnabled(true);
            if (!verificadores.get(mForwardButton1Option2.getId() + "") && !verificadores.get(mForwardButton2Option2.getId() + "")) {
                mForwardButton1Option3.setEnabled(false);
                mForwardButton2Option3.setEnabled(false);
            } else {
                mForwardButton1Option3.setEnabled(true);
                mForwardButton2Option3.setEnabled(true);
                if (!verificadores.get(mForwardButton1Option3.getId() + "") && !verificadores.get(mForwardButton2Option3.getId() + "")) {
                    mForwardButton1Option4.setEnabled(false);
                    mForwardButton2Option4.setEnabled(false);
                } else {
                    mForwardButton1Option4.setEnabled(true);
                    mForwardButton2Option4.setEnabled(true);
                    if (!verificadores.get(mForwardButton1Option4.getId() + "") && !verificadores.get(mForwardButton2Option4.getId() + "")) {
                        mForwardButton1Option5.setEnabled(false);
                        mForwardButton2Option5.setEnabled(false);
                    } else {
                        mForwardButton1Option5.setEnabled(true);
                        mForwardButton2Option5.setEnabled(true);
                        if (!verificadores.get(mForwardButton1Option5.getId() + "") && !verificadores.get(mForwardButton2Option5.getId() + "")) {
                            mForwardButton1Option6.setEnabled(false);
                            mForwardButton2Option6.setEnabled(false);
                        } else {
                            mForwardButton1Option6.setEnabled(true);
                            mForwardButton2Option6.setEnabled(true);
                            if (!verificadores.get(mForwardButton1Option6.getId() + "") && !verificadores.get(mForwardButton2Option6.getId() + "")) {
                                mForwardButton1Option7.setEnabled(false);
                                mForwardButton2Option7.setEnabled(false);
                            } else {
                                mForwardButton1Option7.setEnabled(true);
                                mForwardButton2Option7.setEnabled(true);
                                if (!verificadores.get(mForwardButton1Option7.getId() + "") && !verificadores.get(mForwardButton2Option7.getId() + "")) {
                                    mForwardButton1Option8.setEnabled(false);
                                    mForwardButton2Option8.setEnabled(false);
                                } else {
                                    mForwardButton1Option8.setEnabled(true);
                                    mForwardButton2Option8.setEnabled(true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void atualizaVeficadoresDeNivelBackward() {
        if(!verificadores.get(mBackwardButton1Option1.getId()+"") && !verificadores.get(mBackwardButton2Option1.getId()+"")) {
            mBackwardButton1Option2.setEnabled(false);
            mBackwardButton2Option2.setEnabled(false);
        } else {
            mBackwardButton1Option2.setEnabled(true);
            mBackwardButton2Option2.setEnabled(true);
            if (!verificadores.get(mBackwardButton1Option2.getId() + "") && !verificadores.get(mBackwardButton2Option2.getId() + "")) {
                mBackwardButton1Option3.setEnabled(false);
                mBackwardButton2Option3.setEnabled(false);
            } else {
                mBackwardButton1Option3.setEnabled(true);
                mBackwardButton2Option3.setEnabled(true);
                if (!verificadores.get(mBackwardButton1Option3.getId() + "") && !verificadores.get(mBackwardButton2Option3.getId() + "")) {
                    mBackwardButton1Option4.setEnabled(false);
                    mBackwardButton2Option4.setEnabled(false);
                } else {
                    mBackwardButton1Option4.setEnabled(true);
                    mBackwardButton2Option4.setEnabled(true);
                    if (!verificadores.get(mBackwardButton1Option4.getId() + "") && !verificadores.get(mBackwardButton2Option4.getId() + "")) {
                        mBackwardButton1Option5.setEnabled(false);
                        mBackwardButton2Option5.setEnabled(false);
                    } else {
                        mBackwardButton1Option5.setEnabled(true);
                        mBackwardButton2Option5.setEnabled(true);
                        if (!verificadores.get(mBackwardButton1Option5.getId() + "") && !verificadores.get(mBackwardButton2Option5.getId() + "")) {
                            mBackwardButton1Option6.setEnabled(false);
                            mBackwardButton2Option6.setEnabled(false);
                        } else {
                            mBackwardButton1Option6.setEnabled(true);
                            mBackwardButton2Option6.setEnabled(true);
                            if (!verificadores.get(mBackwardButton1Option6.getId() + "") && !verificadores.get(mBackwardButton2Option6.getId() + "")) {
                                mBackwardButton1Option7.setEnabled(false);
                                mBackwardButton2Option7.setEnabled(false);
                            } else {
                                mBackwardButton1Option7.setEnabled(true);
                                mBackwardButton2Option7.setEnabled(true);
                            }
                        }
                    }
                }
            }
        }
    }

    public void atualizaTotalForward(boolean ref) {
        if(ref)
            totalForward++;
        else
            totalForward--;
        mPontuacaoForward.setText(""+totalForward);
        totalTotal = totalBackward+totalForward;
        mPontuacaoTotal.setText(""+totalTotal);
    }

    public void atualizaTotalBackward(boolean ref) {
        if(ref)
            totalBackward++;
        else
            totalBackward--;
        mPontuacaoBackward.setText(""+totalBackward);
        totalTotal = totalBackward+totalForward;
        mPontuacaoTotal.setText(""+totalTotal);
    }

    public void autoComplete() {
        Button button;
        atualizaVeficadoresDeNivelForward();
        atualizaVeficadoresDeNivelBackward();
        for(String x : auxAutoCompleteForward) {
            button = (Button) findViewById(Integer.parseInt(x));
            autoCompleteButtonsIndicacao(button);
        }
        for(String x : auxAutoCompleteBackward) {
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

    public Participante registrar(Participante participante) {

        if(participante.getDigitSpanObject() == null) {
            DigitSpanObject digitSpanObject = new DigitSpanObject();
            digitSpanObject.setVerificadores(verificadores);
            digitSpanObject.setValorTotalForward(totalForward);
            digitSpanObject.setValorTotalBackward(totalBackward);
            participante.setDigitSpanObject(digitSpanObject);
        } else {
            DigitSpanObject digitSpanObject = participante.getDigitSpanObject();
            digitSpanObject.setVerificadores(verificadores);
            digitSpanObject.setValorTotalForward(totalForward);
            digitSpanObject.setValorTotalBackward(totalBackward);
            participante.setDigitSpanObject(digitSpanObject);
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
