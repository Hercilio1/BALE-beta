package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.MemoriaEpisodica;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoVerbal.CompVerbalPrimeiraAvaliacaoActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoVerbal.CompVerbalSegundaAvaliacaoActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoVerbal.CompVerbalTerceiraAvaliacaoActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hercilio on 27/02/2018.
 */

public class MemoriaEpisodicaLobbyActivity extends AppCompatActivity {

    public static final String IDENTIFICA_FASE = "Integer[]";
    public static final String IDENTIFICA_INTERVALO = "Boolean";

    private Button mBtnPrimeiraFase, mBtnSegundaFase, mBtnTerceiraFase, mBtnQuartaFase, mBtnQuintaFase, mBtnContinuar;

    private long timeStampSegundaFase = 0, timeStampTerceiraFase = 0, timeStampQuartaFase = 0, timeStampQuintaFase = 0;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoria_episodica_lobby);



        mBtnPrimeiraFase = (Button) findViewById(R.id.button_for_mem_ep_fase_1);
        mBtnSegundaFase = (Button) findViewById(R.id.button_for_mem_ep_fase_2);
        mBtnTerceiraFase = (Button) findViewById(R.id.button_for_mem_ep_fase_3);
        mBtnQuartaFase = (Button) findViewById(R.id.button_for_mem_ep_fase_4);
        mBtnQuintaFase = (Button) findViewById(R.id.button_for_mem_ep_fase_5);

        mBtnContinuar = (Button) findViewById(R.id.btn_continuar_mem_ep_lobby);



        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);
            final boolean[] bool = (boolean[]) intentFromList.getSerializableExtra(IDENTIFICA_INTERVALO);

            if(participante.getMemEpObject() != null) {
                if (participante.getMemEpObject().getTimeStampSegundaFase() != 0
                        && timeStampSegundaFase == 0) {
                    timeStampSegundaFase = participante.getMemEpObject().getTimeStampSegundaFase();
                }
                if (participante.getMemEpObject().getTimeStampTerceiraFase() != 0
                        && timeStampTerceiraFase == 0) {
                    timeStampTerceiraFase = participante.getMemEpObject().getTimeStampTerceiraFase();
                }
                if (participante.getMemEpObject().getTimeStampQuartaFase() != 0
                        && timeStampQuartaFase == 0) {
                    timeStampQuartaFase = participante.getMemEpObject().getTimeStampQuartaFase();
                }
                if (participante.getMemEpObject().getTimeStampQuintaFase() != 0
                        && timeStampQuintaFase == 0) {
                    timeStampQuintaFase = participante.getMemEpObject().getTimeStampQuintaFase();
                }
            }

            /****************
             * SEGUNDA FASE *
             ****************/
            final Chronometer cr1 = (Chronometer) findViewById(R.id.cronometro_button_for_mem_ep_fase_2);
            //Transição para a segunda fase
            if(timeStampSegundaFase != 0 && (participante.getMemEpObject().getSegundaFaseComDica() != null || verificaElapseTime(timeStampSegundaFase))) {

                cr1.setVisibility(View.INVISIBLE);
                mBtnSegundaFase.setText("SEGUNDA FASE");
                mBtnSegundaFase.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                mBtnSegundaFase.setEnabled(true);

            } else if(timeStampSegundaFase != 0) {
                cr1.setBase(SystemClock.elapsedRealtime() + (timeStampSegundaFase - SystemClock.elapsedRealtime()));
                cr1.setCountDown(true);
                cr1.start();
                cr1.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        long elapsedTime = SystemClock.elapsedRealtime() - timeStampSegundaFase;
                        if (elapsedTime >= 0) {
                            cr1.stop();
                            cr1.setVisibility(View.INVISIBLE);
                            mBtnSegundaFase.setText("SEGUNDA FASE");
                            mBtnSegundaFase.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                            mBtnSegundaFase.setEnabled(true);
                        }
                    }
                });

            } else if(!mBtnSegundaFase.isEnabled() && bool != null && bool[0]) {

                timeStampSegundaFase = SystemClock.elapsedRealtime() + (20* 1000);
                participante.getMemEpObject().setTimeStampSegundaFase(timeStampSegundaFase);
                registraTimeStampNoBanco(participante);
                cr1.setBase(timeStampSegundaFase);
                cr1.setCountDown(true);
                cr1.start();
                cr1.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        long elapsedTime = SystemClock.elapsedRealtime() - timeStampSegundaFase;
                        if (elapsedTime >= 0) {
                            cr1.stop();
                            cr1.setVisibility(View.INVISIBLE);
                            mBtnSegundaFase.setText("SEGUNDA FASE");
                            mBtnSegundaFase.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                            mBtnSegundaFase.setEnabled(true);
                        }
                    }
                });
            }

            /****************
             * TERCEIRA FASE *
             ****************/
            final Chronometer cr2 = (Chronometer) findViewById(R.id.cronometro_button_for_mem_ep_fase_3);
            //Transição para a segunda fase
            if(timeStampTerceiraFase != 0 && (participante.getMemEpObject().getTerceiraFaseComDica() != null || verificaElapseTime(timeStampTerceiraFase))) {

                cr2.setVisibility(View.INVISIBLE);
                mBtnTerceiraFase.setText("TERCEIRA FASE");
                mBtnTerceiraFase.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                mBtnTerceiraFase.setEnabled(true);

            } else if(timeStampTerceiraFase != 0) {
                cr2.setBase(SystemClock.elapsedRealtime() + (timeStampTerceiraFase - SystemClock.elapsedRealtime()));
                cr2.setCountDown(true);
                cr2.start();
                cr2.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        long elapsedTime = SystemClock.elapsedRealtime() - timeStampTerceiraFase;
                        if (elapsedTime >= 0) {
                            cr2.stop();
                            cr2.setVisibility(View.INVISIBLE);
                            mBtnTerceiraFase.setText("TERCEIRA FASE");
                            mBtnTerceiraFase.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                            mBtnTerceiraFase.setEnabled(true);
                        }
                    }
                });

            } else if(!mBtnTerceiraFase.isEnabled() && bool != null && bool[1]) {

                timeStampTerceiraFase = SystemClock.elapsedRealtime() + (20* 1000);
                participante.getMemEpObject().setTimeStampTerceiraFase(timeStampTerceiraFase);
                registraTimeStampNoBanco(participante);
                cr2.setBase(timeStampTerceiraFase);
                cr2.setCountDown(true);
                cr2.start();
                cr2.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        long elapsedTime = SystemClock.elapsedRealtime() - timeStampTerceiraFase;
                        if (elapsedTime >= 0) {
                            cr2.stop();
                            cr2.setVisibility(View.INVISIBLE);
                            mBtnTerceiraFase.setText("TERCEIRA FASE");
                            mBtnTerceiraFase.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                            mBtnTerceiraFase.setEnabled(true);
                        }
                    }
                });
            }

            /****************
             * QUARTA FASE *
             ****************/
            final Chronometer cr3 = (Chronometer) findViewById(R.id.cronometro_button_for_mem_ep_fase_4);
            //Transição para a segunda fase
            if(timeStampQuartaFase != 0 && (participante.getMemEpObject().getQuartaFaseComDica() != null || verificaElapseTime(timeStampQuartaFase))) {

                cr3.setVisibility(View.INVISIBLE);
                mBtnQuartaFase.setText("QUARTA FASE");
                mBtnQuartaFase.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                mBtnQuartaFase.setEnabled(true);

            } else if(timeStampQuartaFase != 0) {
                cr3.setBase(SystemClock.elapsedRealtime() + (timeStampQuartaFase - SystemClock.elapsedRealtime()));
                cr3.setCountDown(true);
                cr3.start();
                cr3.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        long elapsedTime = SystemClock.elapsedRealtime() - timeStampQuartaFase;
                        if (elapsedTime >= 0) {
                            cr3.stop();
                            cr3.setVisibility(View.INVISIBLE);
                            mBtnQuartaFase.setText("QUARTA FASE");
                            mBtnQuartaFase.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                            mBtnQuartaFase.setEnabled(true);
                        }
                    }
                });

            } else if(!mBtnQuartaFase.isEnabled() && bool != null && bool[2]) {

                timeStampQuartaFase = SystemClock.elapsedRealtime() + (20* 1000);
                participante.getMemEpObject().setTimeStampQuartaFase(timeStampQuartaFase);
                registraTimeStampNoBanco(participante);
                cr3.setBase(timeStampQuartaFase);
                cr3.setCountDown(true);
                cr3.start();
                cr3.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        long elapsedTime = SystemClock.elapsedRealtime() - timeStampQuartaFase;
                        if (elapsedTime >= 0) {
                            cr3.stop();
                            cr3.setVisibility(View.INVISIBLE);
                            mBtnQuartaFase.setText("QUARTA FASE");
                            mBtnQuartaFase.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                            mBtnQuartaFase.setEnabled(true);
                        }
                    }
                });
            }

            /****************
             * QUINTA FASE *
             ****************/
            final Chronometer cr4 = (Chronometer) findViewById(R.id.cronometro_button_for_mem_ep_fase_5);
            //Transição para a segunda fase
            if(timeStampQuintaFase != 0 && (participante.getMemEpObject().getQuintaFaseComDica() != null || verificaElapseTime(timeStampQuintaFase) || verificaElapseTimeQuintoTimeStamp())) {

                cr4.setVisibility(View.INVISIBLE);
                mBtnQuintaFase.setText("QUINTA FASE");
                mBtnQuintaFase.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                mBtnQuintaFase.setEnabled(true);

            } else if(timeStampQuintaFase != 0) {
                cr4.setBase(SystemClock.elapsedRealtime() + (timeStampQuintaFase - SystemClock.elapsedRealtime()));
                cr4.setCountDown(true);
                cr4.start();
                cr4.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        long elapsedTime = SystemClock.elapsedRealtime() - timeStampQuintaFase;
                        if (elapsedTime >= 0) {
                            cr4.stop();
                            cr4.setVisibility(View.INVISIBLE);
                            mBtnQuintaFase.setText("QUINTA FASE");
                            mBtnQuintaFase.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                            mBtnQuintaFase.setEnabled(true);
                        }
                    }
                });

            } else if(!mBtnQuintaFase.isEnabled() && bool != null && bool[3]) {

                timeStampQuintaFase = SystemClock.elapsedRealtime() + (1200*1000);
                participante.getMemEpObject().setTimeStampQuintaFase(timeStampQuintaFase);
                registraTimeStampNoBanco(participante);
                cr4.setBase(timeStampQuintaFase);
                cr4.setCountDown(true);
                cr4.start();
                cr4.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        long elapsedTime = SystemClock.elapsedRealtime() - timeStampQuintaFase;
                        if (elapsedTime >= 0) {
                            cr4.stop();
                            cr4.setVisibility(View.INVISIBLE);
                            mBtnQuintaFase.setText("QUINTA FASE");
                            mBtnQuintaFase.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                            mBtnQuintaFase.setEnabled(true);
                        }
                    }
                });
            }


            //Transiçao para a primeira fase
            mBtnPrimeiraFase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Integer identificador[] = new Integer[1];
                    identificador[0] = 1;

                    Intent intent = new Intent(getBaseContext(), MemoriaEpisodicaPrimeiraFaseActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    intent.putExtra(MemoriaEpisodicaPrimeiraFaseActivity.IDENTIFICADOR_DA_SEQUENCIA_MEM_EP, identificador);
                    startActivity(intent);


                }
            });

            mBtnSegundaFase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer identificador[] = new Integer[1];
                    identificador[0] = 1;

                    Intent intent = new Intent(getBaseContext(), GridActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    intent.putExtra(IDENTIFICA_FASE, identificador);
                    startActivity(intent);
                }
            });


            mBtnTerceiraFase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer identificador[] = new Integer[1];
                    identificador[0] = 2;

                    Intent intent = new Intent(getBaseContext(), GridActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    intent.putExtra(IDENTIFICA_FASE, identificador);
                    startActivity(intent);
                }
            });

            mBtnQuartaFase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer identificador[] = new Integer[1];
                    identificador[0] = 3;

                    Intent intent = new Intent(getBaseContext(), GridActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    intent.putExtra(IDENTIFICA_FASE, identificador);
                    startActivity(intent);
                }
            });

            mBtnQuintaFase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer identificador[] = new Integer[1];
                    identificador[0] = 4;

                    Intent intent = new Intent(getBaseContext(), GridActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    intent.putExtra(IDENTIFICA_FASE, identificador);
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

    public boolean verificaElapseTime(long timeStamp) {
        long elapsedTime =  - timeStamp;
        if (elapsedTime >= 0)
            return true;
        return false;
    }

    public boolean verificaElapseTimeQuintoTimeStamp() {
        if(SystemClock.elapsedRealtime() < (timeStampQuintaFase - (1200*1000)) || SystemClock.elapsedRealtime() > timeStampQuintaFase)
            return true;
        return false;
    }

    public void registraTimeStampNoBanco(Participante participante) {
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
