package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.FluenciaVerbal;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Objetos.FluenciaVerbalObject;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by Hercilio on 04/03/2018.
 */

public class FluenciaVerbalActivity extends AppCompatActivity {

    //Cronometros Animais
    private Chronometer mTimerAnimais0p15, mTimerAnimais16p30, mTimerAnimais31p45, mTimerAnimais46p60;
    //TextViews dos cronometros de animais
    private TextView mTextViewTimerAnimais0p15, mTextViewTimerAnimais16p30, mTextViewTimerAnimais31p45, mTextViewTimerAnimais46p60;
    //EditTexts Animais
    private EditText mEditTextAnimais0p15, mEditTextAnimais16p30, mEditTextAnimais31p45, mEditTextAnimais46p60;
    //Time Stamp Animais
    private long timeStampAnimais0p15, timeStampAnimais16p30, timeStampAnimais31p45, timeStampAnimais46p60;

    //Cronometros palavras
    private Chronometer mTimerPalavras0p15, mTimerPalavras16p30, mTimerPalavras31p45, mTimerPalavras46p60;
    //TextViews dos cronometros de plavras
    private TextView mTextViewTimerPalavras0p15, mTextViewTimerPalavras16p30, mTextViewTimerPalavras31p45, mTextViewTimerPalavras46p60;
    //EditTexts palavras
    private EditText mEditTextPalavras0p15, mEditTextPalavras16p30, mEditTextPalavras31p45, mEditTextPalavras46p60;
    //Time Stamp Palavras
    private long timeStampPalavras0p15, timeStampPalavras16p30, timeStampPalavras31p45, timeStampPalavras46p60;

    //Botao Continuar
    private Button mBtnContinuar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fluencia_verbal);

        //Cronometros Animais
        mTimerAnimais0p15 = (Chronometer) findViewById(R.id.fluencia_verbal_animais_timer1);
        mTimerAnimais16p30 = (Chronometer) findViewById(R.id.fluencia_verbal_animais_timer2);
        mTimerAnimais31p45 = (Chronometer) findViewById(R.id.fluencia_verbal_animais_timer3);
        mTimerAnimais46p60 = (Chronometer) findViewById(R.id.fluencia_verbal_animais_timer4);
        //TextViews dos cronometros de animais
        mTextViewTimerAnimais0p15 = (TextView) findViewById(R.id.fluencia_verbal_animais_timer_tv1);
        mTextViewTimerAnimais16p30 = (TextView) findViewById(R.id.fluencia_verbal_animais_timer_tv2);
        mTextViewTimerAnimais31p45 = (TextView) findViewById(R.id.fluencia_verbal_animais_timer_tv3);
        mTextViewTimerAnimais46p60 = (TextView) findViewById(R.id.fluencia_verbal_animais_timer_tv4);
        //EditTexts Animais
        mEditTextAnimais0p15 = (EditText) findViewById(R.id.fluencia_Verbal_animais_et_1);
        mEditTextAnimais16p30 = (EditText) findViewById(R.id.fluencia_Verbal_animais_et_2);
        mEditTextAnimais31p45 = (EditText) findViewById(R.id.fluencia_Verbal_animais_et_3);
        mEditTextAnimais46p60 = (EditText) findViewById(R.id.fluencia_Verbal_animais_et_4);

        //Cronometros palavras
        mTimerPalavras0p15 = (Chronometer) findViewById(R.id.fluencia_verbal_palavras_timer1);
        mTimerPalavras16p30 = (Chronometer) findViewById(R.id.fluencia_verbal_palavras_timer2);
        mTimerPalavras31p45 = (Chronometer) findViewById(R.id.fluencia_verbal_palavras_timer3);
        mTimerPalavras46p60 = (Chronometer) findViewById(R.id.fluencia_verbal_palavras_timer4);
        //TextViews dos cronometros de palavras
        mTextViewTimerPalavras0p15 = (TextView) findViewById(R.id.fluencia_verbal_palavras_timer_tv1);
        mTextViewTimerPalavras16p30 = (TextView) findViewById(R.id.fluencia_verbal_palavras_timer_tv2);
        mTextViewTimerPalavras31p45 = (TextView) findViewById(R.id.fluencia_verbal_palavras_timer_tv3);
        mTextViewTimerPalavras46p60 = (TextView) findViewById(R.id.fluencia_verbal_palavras_timer_tv4);
        //EditTexts palavras
        mEditTextPalavras0p15 = (EditText) findViewById(R.id.fluencia_Verbal_palavras_et_1);
        mEditTextPalavras16p30 = (EditText) findViewById(R.id.fluencia_Verbal_palavras_et_2);
        mEditTextPalavras31p45 = (EditText) findViewById(R.id.fluencia_Verbal_palavras_et_3);
        mEditTextPalavras46p60 = (EditText) findViewById(R.id.fluencia_Verbal_palavras_et_4);

        //botao continuar
        mBtnContinuar = (Button) findViewById(R.id.btn_continuar_fluencia_verbal);

        mEditTextAnimais0p15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timeStampAnimais0p15 = SystemClock.elapsedRealtime() + (15 * 1000);
                timeStampAnimais16p30 = timeStampAnimais0p15 + (15 * 1000);
                timeStampAnimais31p45 = timeStampAnimais16p30 + (15 * 1000);
                timeStampAnimais46p60 = timeStampAnimais31p45 + (15 * 1000);
                startAnimalExecution(mTimerAnimais0p15, timeStampAnimais0p15, 0);
            }
        });

        mEditTextPalavras0p15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timeStampPalavras0p15 = SystemClock.elapsedRealtime() + (15 * 1000);
                timeStampPalavras16p30 = timeStampPalavras0p15 + (15 * 1000);
                timeStampPalavras31p45 = timeStampPalavras16p30 + (15 * 1000);
                timeStampPalavras46p60 = timeStampPalavras31p45 + (15 * 1000);
                startPalavrasExecution(mTimerPalavras0p15, timeStampPalavras0p15, 0);
            }
        });

        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);

            if(participante.getFluenciaVerbalObject() != null) {
                autoComplete(participante);
            }

            mBtnContinuar.setOnClickListener(new View.OnClickListener() {
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

    public void startAnimalExecution(Chronometer timer, final long timeStamp, final int ref) {

        switch (ref) {
            case 0:
                mTextViewTimerAnimais0p15.setVisibility(View.INVISIBLE);
                timer.setVisibility(View.VISIBLE);
                timer.setBase(SystemClock.elapsedRealtime());
                break;
            case 1:
                mTextViewTimerAnimais16p30.setVisibility(View.INVISIBLE);
                timer.setVisibility(View.VISIBLE);
                timer.setBase(SystemClock.elapsedRealtime() - (15*1000));
                break;
            case 2:
                mTextViewTimerAnimais31p45.setVisibility(View.INVISIBLE);
                timer.setVisibility(View.VISIBLE);
                timer.setBase(SystemClock.elapsedRealtime() - (30*1000));
                break;
            case 3:
                mTextViewTimerAnimais46p60.setVisibility(View.INVISIBLE);
                timer.setVisibility(View.VISIBLE);
                timer.setBase(SystemClock.elapsedRealtime() - (45*1000));
                break;
            default:
                break;
        }


        timer.start();
        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long elapsedTime = SystemClock.elapsedRealtime() - timeStamp;
                if (elapsedTime >= 0) {
                    chronometer.stop();
                    switch (ref) {
                        case 0:
                            mTextViewTimerAnimais0p15.setVisibility(View.VISIBLE);
                            chronometer.setVisibility(View.INVISIBLE);
                            mEditTextAnimais16p30.requestFocus();
                            startAnimalExecution(mTimerAnimais16p30, timeStampAnimais16p30, 1);
                            break;
                        case 1:
                            mTextViewTimerAnimais16p30.setVisibility(View.VISIBLE);
                            chronometer.setVisibility(View.INVISIBLE);
                            mEditTextAnimais31p45.requestFocus();
                            startAnimalExecution(mTimerAnimais31p45, timeStampAnimais31p45, 2);
                            break;
                        case 2:
                            mTextViewTimerAnimais31p45.setVisibility(View.VISIBLE);
                            chronometer.setVisibility(View.INVISIBLE);
                            mEditTextAnimais46p60.requestFocus();
                            startAnimalExecution(mTimerAnimais46p60, timeStampAnimais46p60, 3);
                            break;
                        default:
                            mTextViewTimerAnimais46p60.setVisibility(View.VISIBLE);
                            chronometer.setVisibility(View.INVISIBLE);
                            break;
                    }
                }
            }
        });

    }

    public void startPalavrasExecution(Chronometer timer, final long timeStamp, final int ref) {

        switch (ref) {
            case 0:
                mTextViewTimerPalavras0p15.setVisibility(View.INVISIBLE);
                timer.setVisibility(View.VISIBLE);
                timer.setBase(SystemClock.elapsedRealtime());
                break;
            case 1:
                mTextViewTimerPalavras16p30.setVisibility(View.INVISIBLE);
                timer.setVisibility(View.VISIBLE);
                timer.setBase(SystemClock.elapsedRealtime() - (15*1000));
                break;
            case 2:
                mTextViewTimerPalavras31p45.setVisibility(View.INVISIBLE);
                timer.setVisibility(View.VISIBLE);
                timer.setBase(SystemClock.elapsedRealtime() - (30*1000));
                break;
            case 3:
                mTextViewTimerPalavras46p60.setVisibility(View.INVISIBLE);
                timer.setVisibility(View.VISIBLE);
                timer.setBase(SystemClock.elapsedRealtime() - (45*1000));
                break;
            default:
                break;
        }

        timer.start();
        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long elapsedTime = SystemClock.elapsedRealtime() - timeStamp;
                if (elapsedTime >= 0) {
                    chronometer.stop();
                    switch (ref) {
                        case 0:
                            mTextViewTimerPalavras0p15.setVisibility(View.VISIBLE);
                            chronometer.setVisibility(View.INVISIBLE);
                            mEditTextPalavras16p30.requestFocus();
                            startPalavrasExecution(mTimerPalavras16p30, timeStampPalavras16p30, 1);
                            break;
                        case 1:
                            mTextViewTimerPalavras16p30.setVisibility(View.VISIBLE);
                            chronometer.setVisibility(View.INVISIBLE);
                            mEditTextPalavras31p45.requestFocus();
                            startPalavrasExecution(mTimerPalavras31p45, timeStampPalavras31p45, 2);
                            break;
                        case 2:
                            mTextViewTimerPalavras31p45.setVisibility(View.VISIBLE);
                            chronometer.setVisibility(View.INVISIBLE);
                            mEditTextPalavras46p60.requestFocus();
                            startPalavrasExecution(mTimerPalavras46p60, timeStampPalavras46p60, 3);
                            break;
                        default:
                            mTextViewTimerPalavras46p60.setVisibility(View.VISIBLE);
                            chronometer.setVisibility(View.INVISIBLE);
                            break;
                    }
                }
            }
        });
    }

    public void registrar(Participante participante) {

        if(participante.getFluenciaVerbalObject() == null) {
            FluenciaVerbalObject fluenciaVerbalObject = new FluenciaVerbalObject();
            //Animais
            fluenciaVerbalObject.setAnimais00s15s(mEditTextAnimais0p15.getText().toString());
            fluenciaVerbalObject.setAnimais16s30s(mEditTextAnimais16p30.getText().toString());
            fluenciaVerbalObject.setAnimais31s45s(mEditTextAnimais31p45.getText().toString());
            fluenciaVerbalObject.setAnimais46s60s(mEditTextAnimais46p60.getText().toString());
            //Palavras
            fluenciaVerbalObject.setPalavras00s15s(mEditTextPalavras0p15.getText().toString());
            fluenciaVerbalObject.setPalavras16s30s(mEditTextPalavras16p30.getText().toString());
            fluenciaVerbalObject.setPalavras31s45s(mEditTextPalavras31p45.getText().toString());
            fluenciaVerbalObject.setPalavras46s60s(mEditTextPalavras46p60.getText().toString());

            participante.setFluenciaVerbalObject(fluenciaVerbalObject);
        } else {
            FluenciaVerbalObject fluenciaVerbalObject = participante.getFluenciaVerbalObject();
            //Animais
            fluenciaVerbalObject.setAnimais00s15s(mEditTextAnimais0p15.getText().toString());
            fluenciaVerbalObject.setAnimais16s30s(mEditTextAnimais16p30.getText().toString());
            fluenciaVerbalObject.setAnimais31s45s(mEditTextAnimais31p45.getText().toString());
            fluenciaVerbalObject.setAnimais46s60s(mEditTextAnimais46p60.getText().toString());
            //Palavras
            fluenciaVerbalObject.setPalavras00s15s(mEditTextPalavras0p15.getText().toString());
            fluenciaVerbalObject.setPalavras16s30s(mEditTextPalavras16p30.getText().toString());
            fluenciaVerbalObject.setPalavras31s45s(mEditTextPalavras31p45.getText().toString());
            fluenciaVerbalObject.setPalavras46s60s(mEditTextPalavras46p60.getText().toString());

            participante.setFluenciaVerbalObject(fluenciaVerbalObject);
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
    }

    public void autoComplete (Participante participante) {
        mEditTextAnimais0p15.setText(participante.getFluenciaVerbalObject().getAnimais00s15s());
        mEditTextAnimais16p30.setText(participante.getFluenciaVerbalObject().getAnimais16s30s());
        mEditTextAnimais31p45.setText(participante.getFluenciaVerbalObject().getAnimais31s45s());
        mEditTextAnimais46p60.setText(participante.getFluenciaVerbalObject().getAnimais46s60s());

        mEditTextPalavras0p15.setText(participante.getFluenciaVerbalObject().getPalavras00s15s());
        mEditTextPalavras16p30.setText(participante.getFluenciaVerbalObject().getPalavras16s30s());
        mEditTextPalavras31p45.setText(participante.getFluenciaVerbalObject().getPalavras31s45s());
        mEditTextPalavras46p60.setText(participante.getFluenciaVerbalObject().getPalavras46s60s());
    }


}
