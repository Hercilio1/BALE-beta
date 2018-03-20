package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Narrativa;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Observacoes.ObservacoesActivity;
import com.example.hercilio.appwithfirebase.Objetos.NarrativaObject;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hercilio on 02/03/2018.
 */

public class NarrativaActivity extends AppCompatActivity {

    //Imagem Principal:
    private ImageView mImageViewPrincipal;

    //Estrutura:
    private RadioGroup mRadioEstruturaQuest1, mRadioEstruturaQuest2, mRadioEstruturaQuest3, mRadioEstruturaQuest4, mRadioEstruturaQuest5, mRadioEstruturaQuest6;
    //Desempenho Linguistico I:
    private RadioGroup mRadioDesempling1Quest1, mRadioDesempling1Quest2, mRadioDesempling1Quest3, mRadioDesempling1Quest4;
    //Desempenho Linguistico II:
    private RadioGroup mRadioDesempling2Quest1, mRadioDesempling2Quest2, mRadioDesempling2Quest3;

    //Check Estrutura:
    private int checkedRadioEstruturaQuest1, checkedRadioEstruturaQuest2, checkedRadioEstruturaQuest3, checkedRadioEstruturaQuest4, checkedRadioEstruturaQuest5, checkedRadioEstruturaQuest6;
    //Check Desempenho Linguistico I:
    private int checkedRadioDesempling1Quest1, checkedRadioDesempling1Quest2, checkedRadioDesempling1Quest3, checkedRadioDesempling1Quest4;
    //Check Desempenho Linguistico II:
    private int checkedRadioDesempling2Quest1, checkedRadioDesempling2Quest2, checkedRadioDesempling2Quest3;

    //Totais textViews:
    private TextView mTotalNumero1Estrutura, mTotalNumero1Desempling1, mTotalNumero1Desempling2;

    //Variaveis dos valores totais:
    private int valorTotalEstrutura, valorTotalDesempling1, valorTotalDesempling2;

    //Parciais Estrutura:
    private int parcialEstruturaQuest1, parcialEstruturaQuest2, parcialEstruturaQuest3, parcialEstruturaQuest4, parcialEstruturaQuest5, parcialEstruturaQuest6;
    //Parciais Desempenho Linguistico I:
    private int parcialDesempling1Quest1, parcialDesempling1Quest2, parcialDesempling1Quest3, parcialDesempling1Quest4;
    //Parciais Desempenho Linguistico II:
    private int parcialDesempling2Quest1, parcialDesempling2Quest2, parcialDesempling2Quest3;

    //Dicionario que armazena os radiobuttons selecionados:
    private Map<String, Integer> verificadores = new HashMap<>();

    //Botão validador caso não haja o pressionamento de algum radio button
    private boolean validaRadioButtons;

    //Botao continuar:
    private Button mBtnContinuar;

    //Variável que auxilia na verificacao se o teste já está finalizado
    private boolean isFinalizado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_narrativa);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //Imagem da historia
        mImageViewPrincipal = (ImageView) findViewById(R.id.imageview_narrativa);

        //Estrutura
        mRadioEstruturaQuest1 = (RadioGroup) findViewById(R.id.narrativa_estrutura_analise_1_radio);
        mRadioEstruturaQuest2 = (RadioGroup) findViewById(R.id.narrativa_estrutura_analise_2_radio);
        mRadioEstruturaQuest3 = (RadioGroup) findViewById(R.id.narrativa_estrutura_analise_3_radio);
        mRadioEstruturaQuest4 = (RadioGroup) findViewById(R.id.narrativa_estrutura_analise_4_radio);
        mRadioEstruturaQuest5 = (RadioGroup) findViewById(R.id.narrativa_estrutura_analise_5_radio);
        mRadioEstruturaQuest6 = (RadioGroup) findViewById(R.id.narrativa_estrutura_analise_6_radio);

        //Desempenho Linguistico I:
        mRadioDesempling1Quest1 = (RadioGroup) findViewById(R.id.narrativa_desempling1_analise_1_radio);
        mRadioDesempling1Quest2 = (RadioGroup) findViewById(R.id.narrativa_desempling1_analise_2_radio);
        mRadioDesempling1Quest3 = (RadioGroup) findViewById(R.id.narrativa_desempling1_analise_3_radio);
        mRadioDesempling1Quest4 = (RadioGroup) findViewById(R.id.narrativa_desempling1_analise_4_radio);

        //Desempenho Linguistico II:
        mRadioDesempling2Quest1 = (RadioGroup) findViewById(R.id.narrativa_desempling2_analise_1_radio);
        mRadioDesempling2Quest2 = (RadioGroup) findViewById(R.id.narrativa_desempling2_analise_2_radio);
        mRadioDesempling2Quest3 = (RadioGroup) findViewById(R.id.narrativa_desempling2_analise_3_radio);

        //Totais:
        mTotalNumero1Estrutura = (TextView) findViewById(R.id.narrativa_estrutura_total_number1);
        mTotalNumero1Desempling1 = (TextView) findViewById(R.id.narrativa_desempling1_total_number1);
        mTotalNumero1Desempling2 = (TextView) findViewById(R.id.narrativa_desempling2_total_number1);

        //Botao continuar:
        mBtnContinuar = (Button) findViewById(R.id.btn_continuar_narrativa);

        //Adiciona intereção com imagem
        mImageViewPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ImagemExpandidaNarrativaActivity.class);
                startActivity(intent);
            }
        });

        //TODO: completar até o final do método
        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);
            isFinalizado = participante.isFinalizado();

            if (participante.getNarrativaObject() != null && participante.getNarrativaObject().getVerificadores() != null) {
                verificadores = participante.getNarrativaObject().getVerificadores();
                autoComplete(participante);
            }

            mBtnContinuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!isFinalizado)
                        registrar(participante);
                    if(!validaRadioButtons) {
                        Intent intent = new Intent(getBaseContext(), BaleLobbyActivity.class);
                        intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                        startActivity(intent);
                    } else {
                        if (validaRadioButtons) {

                            AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());

                            alert.setTitle("Atenção");
                            alert.setMessage("Você não pressionou algum botão necessário para pesquisa. Favor pressiona-lo(s) para presseguir");

                            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    validaRadioButtons = true;
                                }
                            });

                            AlertDialog dialog = alert.create();
                            alert.show();
                        }
                    }
                }
            });
        }
    }

    public void autoComplete(Participante participante) {
        int[] aux = {11,12,13,14,15,16,21,22,23,24,31,32,33};
        for(int i=0; i<aux.length; i++) {
            switch (aux[i]) {
                //Estrutura:
                case 11:
                    mRadioEstruturaQuest1.check(mRadioEstruturaQuest1.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioEstruturaQuest1, 1));
                    break;
                case 12:
                    mRadioEstruturaQuest2.check(mRadioEstruturaQuest2.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioEstruturaQuest2, 1));
                    break;
                case 13:
                    mRadioEstruturaQuest3.check(mRadioEstruturaQuest3.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioEstruturaQuest3, 1));
                    break;
                case 14:
                    mRadioEstruturaQuest4.check(mRadioEstruturaQuest4.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioEstruturaQuest4, 1));
                    break;
                case 15:
                    mRadioEstruturaQuest5.check(mRadioEstruturaQuest5.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioEstruturaQuest5, 1));
                    break;
                case 16:
                    mRadioEstruturaQuest6.check(mRadioEstruturaQuest6.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioEstruturaQuest6, 1));
                    break;

                //Desempenho Linguistico I:
                case 21:
                    mRadioDesempling1Quest1.check(mRadioDesempling1Quest1.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioDesempling1Quest1, 2));
                    break;
                case 22:
                    mRadioDesempling1Quest2.check(mRadioDesempling1Quest2.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioDesempling1Quest2, 2));
                    break;
                case 23:
                    mRadioDesempling1Quest3.check(mRadioDesempling1Quest3.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioDesempling1Quest3, 2));
                    break;
                case 24:
                    mRadioDesempling1Quest4.check(mRadioDesempling1Quest4.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioDesempling1Quest4, 0));
                    break;

                //Desempenho Linguistico II:
                case 31:
                    mRadioDesempling2Quest1.check(mRadioDesempling2Quest1.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioDesempling2Quest1, 2));
                    break;
                case 32:
                    mRadioDesempling2Quest2.check(mRadioDesempling2Quest2.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioDesempling2Quest2, 2));
                    break;
                case 33:
                    mRadioDesempling2Quest3.check(mRadioDesempling2Quest3.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioDesempling2Quest3, 2));
                    break;

                default: break;
            }
        }
    }

    public void registrar(Participante participante) {
        ArrayList<Integer> verificaValidade = new ArrayList<>();

        //Estrutura:
        checkedRadioEstruturaQuest1= (int) onFrequenciaRadioButtonClicked(mRadioEstruturaQuest1, 1);
        if(checkedRadioEstruturaQuest1 == -1)
            verificaValidade.add(11);
        armazenaDadoNoDicionario(11, checkedRadioEstruturaQuest1);

        checkedRadioEstruturaQuest2= (int) onFrequenciaRadioButtonClicked(mRadioEstruturaQuest2, 1);
        if(checkedRadioEstruturaQuest2 == -1)
            verificaValidade.add(12);
        armazenaDadoNoDicionario(12, checkedRadioEstruturaQuest2);

        checkedRadioEstruturaQuest3= (int) onFrequenciaRadioButtonClicked(mRadioEstruturaQuest3, 1);
        if(checkedRadioEstruturaQuest3 == -1)
            verificaValidade.add(13);
        armazenaDadoNoDicionario(13, checkedRadioEstruturaQuest3);

        checkedRadioEstruturaQuest4= (int) onFrequenciaRadioButtonClicked(mRadioEstruturaQuest4, 1);
        if(checkedRadioEstruturaQuest4 == -1)
            verificaValidade.add(14);
        armazenaDadoNoDicionario(14, checkedRadioEstruturaQuest4);

        checkedRadioEstruturaQuest5= (int) onFrequenciaRadioButtonClicked(mRadioEstruturaQuest5, 1);
        if(checkedRadioEstruturaQuest5 == -1)
            verificaValidade.add(15);
        armazenaDadoNoDicionario(15, checkedRadioEstruturaQuest5);

        checkedRadioEstruturaQuest6= (int) onFrequenciaRadioButtonClicked(mRadioEstruturaQuest6, 1);
        if(checkedRadioEstruturaQuest6 == -1)
            verificaValidade.add(16);
        armazenaDadoNoDicionario(16, checkedRadioEstruturaQuest6);

        //Desempenho Linguistico I:
        checkedRadioDesempling1Quest1 = (int) onFrequenciaRadioButtonClicked(mRadioDesempling1Quest1, 2);
        if(checkedRadioDesempling1Quest1 == -1)
            verificaValidade.add(21);
        armazenaDadoNoDicionario(21, checkedRadioDesempling1Quest1);

        checkedRadioDesempling1Quest2 = (int) onFrequenciaRadioButtonClicked(mRadioDesempling1Quest2, 2);
        if(checkedRadioDesempling1Quest2 == -1)
            verificaValidade.add(22);
        armazenaDadoNoDicionario(22, checkedRadioDesempling1Quest2);

        checkedRadioDesempling1Quest3 = (int) onFrequenciaRadioButtonClicked(mRadioDesempling1Quest3, 2);
        if(checkedRadioDesempling1Quest3 == -1)
            verificaValidade.add(23);
        armazenaDadoNoDicionario(23, checkedRadioDesempling1Quest3);

        checkedRadioDesempling1Quest4 = (int) onFrequenciaRadioButtonClicked(mRadioDesempling1Quest4, 0);
        if(checkedRadioDesempling1Quest4 == -1)
            verificaValidade.add(24);
        armazenaDadoNoDicionario(24, checkedRadioDesempling1Quest4);

        //Desempenho Linguistico II:
        checkedRadioDesempling2Quest1 = (int) onFrequenciaRadioButtonClicked(mRadioDesempling2Quest1, 2);
        if(checkedRadioDesempling2Quest1 == -1)
            verificaValidade.add(31);
        armazenaDadoNoDicionario(31, checkedRadioDesempling2Quest1);

        checkedRadioDesempling2Quest2 = (int) onFrequenciaRadioButtonClicked(mRadioDesempling2Quest2, 2);
        if(checkedRadioDesempling2Quest2 == -1)
            verificaValidade.add(32);
        armazenaDadoNoDicionario(32, checkedRadioDesempling2Quest2);

        checkedRadioDesempling2Quest3 = (int) onFrequenciaRadioButtonClicked(mRadioDesempling2Quest3, 2);
        if(checkedRadioDesempling2Quest3 == -1)
            verificaValidade.add(33);
        armazenaDadoNoDicionario(33, checkedRadioDesempling2Quest3);

        if(!verificaValidade.isEmpty()) {
            validaRadioButtons = true;
        } else if (participante != null) {
            validaRadioButtons = false;
            alteraDadosFirebase(participante);
        }

    }

    public long onFrequenciaRadioButtonClicked(RadioGroup mRadio, int ref) {

        long avaliacao = -1;
        String checkedFrequencia;
        int selectedRadioId = mRadio.getCheckedRadioButtonId();
        if (selectedRadioId != -1) {
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioId);
            checkedFrequencia = selectedRadioButton.getText().toString();
            if(ref == 0) {
                if (checkedFrequencia.equals(getResources().getString(R.string.narrativa_nenhum))) {
                    avaliacao = 0;
                } else if (checkedFrequencia.equals(getResources().getString(R.string.narrativa_um))) {
                    avaliacao = 1;
                } else if (checkedFrequencia.equals(getResources().getString(R.string.narrativa_dois))) {
                    avaliacao = 2;
                } else if(checkedFrequencia.equals(getResources().getString(R.string.narrativa_todos))) {
                    avaliacao = 3;
                }
            } else if (ref == 1) {
                if (checkedFrequencia.equals(getResources().getString(R.string.narrativa_nao))) {
                    avaliacao = 0;
                } else if (checkedFrequencia.equals(getResources().getString(R.string.narrativa_parcialmente))) {
                    avaliacao = 1;
                } else if (checkedFrequencia.equals(getResources().getString(R.string.narrativa_sim))) {
                    avaliacao = 2;
                }
            } else {
                if (checkedFrequencia.equals(getResources().getString(R.string.narrativa_sim))) {
                    avaliacao = 0;
                } else if (checkedFrequencia.equals(getResources().getString(R.string.narrativa_parcialmente))) {
                    avaliacao = 1;
                } else if (checkedFrequencia.equals(getResources().getString(R.string.narrativa_nao))) {
                    avaliacao = 2;
                }
            }
        }
        return avaliacao;
    }

    public void armazenaDadoNoDicionario(int index, Integer selecao) {
        verificadores.put(""+index, selecao);
        somaValoresTotais(index, selecao);
    }

    public void somaValoresTotais(int ref, int selecao) {
        if (selecao != -1) {
            switch (ref) {
                //Estrutura
                case 11:
                    valorTotalEstrutura -= parcialEstruturaQuest1;
                    parcialEstruturaQuest1 = selecao;
                    valorTotalEstrutura += parcialEstruturaQuest1;
                    break;
                case 12:
                    valorTotalEstrutura -= parcialEstruturaQuest2;
                    parcialEstruturaQuest2 = selecao;
                    valorTotalEstrutura += parcialEstruturaQuest2;
                    break;
                case 13:
                    valorTotalEstrutura -= parcialEstruturaQuest3;
                    parcialEstruturaQuest3 = selecao;
                    valorTotalEstrutura += parcialEstruturaQuest3;
                    break;
                case 14:
                    valorTotalEstrutura -= parcialEstruturaQuest4;
                    parcialEstruturaQuest4 = selecao;
                    valorTotalEstrutura += parcialEstruturaQuest4;
                    break;
                case 15:
                    valorTotalEstrutura -= parcialEstruturaQuest5;
                    parcialEstruturaQuest5 = selecao;
                    valorTotalEstrutura += parcialEstruturaQuest5;
                    break;
                case 16:
                    valorTotalEstrutura -= parcialEstruturaQuest6;
                    parcialEstruturaQuest6 = selecao;
                    valorTotalEstrutura += parcialEstruturaQuest6;
                    break;

                //Desempenho Linguistico I:
                case 21:
                    valorTotalDesempling1 -= parcialDesempling1Quest1;
                    parcialDesempling1Quest1 = selecao;
                    valorTotalDesempling1 += parcialDesempling1Quest1;
                    break;
                case 22:
                    valorTotalDesempling1 -= parcialDesempling1Quest2;
                    parcialDesempling1Quest2 = selecao;
                    valorTotalDesempling1 += parcialDesempling1Quest2;
                    break;
                case 23:
                    valorTotalDesempling1 -= parcialDesempling1Quest3;
                    parcialDesempling1Quest3 = selecao;
                    valorTotalDesempling1 += parcialDesempling1Quest3;
                    break;
                case 24:
                    valorTotalDesempling1 -= parcialDesempling1Quest4;
                    parcialDesempling1Quest4 = selecao;
                    valorTotalDesempling1 += parcialDesempling1Quest4;
                    break;

                //Dseempenho Linguistico II:
                case 31:
                    valorTotalDesempling2 -= parcialDesempling2Quest1;
                    parcialDesempling2Quest1 = selecao;
                    valorTotalDesempling2 += parcialDesempling2Quest1;
                    break;
                case 32:
                    valorTotalDesempling2 -= parcialDesempling2Quest2;
                    parcialDesempling2Quest2 = selecao;
                    valorTotalDesempling2 += parcialDesempling2Quest2;
                    break;
                case 33:
                    valorTotalDesempling2 -= parcialDesempling2Quest3;
                    parcialDesempling2Quest3 = selecao;
                    valorTotalDesempling2 += parcialDesempling2Quest3;
                    break;
                default:
                    break;
            }
            mTotalNumero1Estrutura.setText("" + valorTotalEstrutura);
            mTotalNumero1Desempling1.setText("" + valorTotalDesempling1);
            mTotalNumero1Desempling2.setText("" + valorTotalDesempling2);
        }
    }

    public void onClickRadio(View v) {
        if(!isFinalizado) {
            //Estrutura:
            if (v.getId() == mRadioEstruturaQuest1.getChildAt(0).getId()
                    || v.getId() == mRadioEstruturaQuest1.getChildAt(1).getId()
                    || v.getId() == mRadioEstruturaQuest1.getChildAt(2).getId())
                somaValoresTotais(11, (int) onFrequenciaRadioButtonClicked(mRadioEstruturaQuest1, 1));

            else if (v.getId() == mRadioEstruturaQuest2.getChildAt(0).getId()
                    || v.getId() == mRadioEstruturaQuest2.getChildAt(1).getId()
                    || v.getId() == mRadioEstruturaQuest2.getChildAt(2).getId())
                somaValoresTotais(12, (int) onFrequenciaRadioButtonClicked(mRadioEstruturaQuest2, 1));

            else if (v.getId() == mRadioEstruturaQuest3.getChildAt(0).getId()
                    || v.getId() == mRadioEstruturaQuest3.getChildAt(1).getId()
                    || v.getId() == mRadioEstruturaQuest3.getChildAt(2).getId())
                somaValoresTotais(13, (int) onFrequenciaRadioButtonClicked(mRadioEstruturaQuest3, 1));

            else if (v.getId() == mRadioEstruturaQuest4.getChildAt(0).getId()
                    || v.getId() == mRadioEstruturaQuest4.getChildAt(1).getId()
                    || v.getId() == mRadioEstruturaQuest4.getChildAt(2).getId())
                somaValoresTotais(14, (int) onFrequenciaRadioButtonClicked(mRadioEstruturaQuest4, 1));

            else if (v.getId() == mRadioEstruturaQuest5.getChildAt(0).getId()
                    || v.getId() == mRadioEstruturaQuest5.getChildAt(1).getId()
                    || v.getId() == mRadioEstruturaQuest5.getChildAt(2).getId())
                somaValoresTotais(15, (int) onFrequenciaRadioButtonClicked(mRadioEstruturaQuest5, 1));

            else if (v.getId() == mRadioEstruturaQuest6.getChildAt(0).getId()
                    || v.getId() == mRadioEstruturaQuest6.getChildAt(1).getId()
                    || v.getId() == mRadioEstruturaQuest6.getChildAt(2).getId())
                somaValoresTotais(16, (int) onFrequenciaRadioButtonClicked(mRadioEstruturaQuest6, 1));

                //Desempenho Linguistico I:
            else if (v.getId() == mRadioDesempling1Quest1.getChildAt(0).getId()
                    || v.getId() == mRadioDesempling1Quest1.getChildAt(1).getId()
                    || v.getId() == mRadioDesempling1Quest1.getChildAt(2).getId())
                somaValoresTotais(21, (int) onFrequenciaRadioButtonClicked(mRadioDesempling1Quest1, 2));

            else if (v.getId() == mRadioDesempling1Quest2.getChildAt(0).getId()
                    || v.getId() == mRadioDesempling1Quest2.getChildAt(1).getId()
                    || v.getId() == mRadioDesempling1Quest2.getChildAt(2).getId())
                somaValoresTotais(22, (int) onFrequenciaRadioButtonClicked(mRadioDesempling1Quest2, 2));

            else if (v.getId() == mRadioDesempling1Quest3.getChildAt(0).getId()
                    || v.getId() == mRadioDesempling1Quest3.getChildAt(1).getId()
                    || v.getId() == mRadioDesempling1Quest3.getChildAt(2).getId())
                somaValoresTotais(23, (int) onFrequenciaRadioButtonClicked(mRadioDesempling1Quest3, 2));

            else if (v.getId() == mRadioDesempling1Quest4.getChildAt(0).getId()
                    || v.getId() == mRadioDesempling1Quest4.getChildAt(1).getId()
                    || v.getId() == mRadioDesempling1Quest4.getChildAt(2).getId()
                    || v.getId() == mRadioDesempling1Quest4.getChildAt(3).getId())
                somaValoresTotais(24, (int) onFrequenciaRadioButtonClicked(mRadioDesempling1Quest4, 0));

                //Desempenho Linguistico II:
            else if (v.getId() == mRadioDesempling2Quest1.getChildAt(0).getId()
                    || v.getId() == mRadioDesempling2Quest1.getChildAt(1).getId()
                    || v.getId() == mRadioDesempling2Quest1.getChildAt(2).getId())
                somaValoresTotais(31, (int) onFrequenciaRadioButtonClicked(mRadioDesempling2Quest1, 2));

            else if (v.getId() == mRadioDesempling2Quest2.getChildAt(0).getId()
                    || v.getId() == mRadioDesempling2Quest2.getChildAt(1).getId()
                    || v.getId() == mRadioDesempling2Quest2.getChildAt(2).getId())
                somaValoresTotais(32, (int) onFrequenciaRadioButtonClicked(mRadioDesempling2Quest2, 2));

            else if (v.getId() == mRadioDesempling2Quest3.getChildAt(0).getId()
                    || v.getId() == mRadioDesempling2Quest3.getChildAt(1).getId()
                    || v.getId() == mRadioDesempling2Quest3.getChildAt(2).getId())
                somaValoresTotais(33, (int) onFrequenciaRadioButtonClicked(mRadioDesempling2Quest3, 2));
        }
    }

    public void alteraDadosFirebase(Participante participante) {
        if(participante.getNarrativaObject() == null) {
            NarrativaObject narrativaObject = new NarrativaObject();
            narrativaObject.setVerificadores(verificadores);
            narrativaObject.setValorTotalEstrutura(valorTotalEstrutura);
            narrativaObject.setValorTotalDesempLing1(valorTotalDesempling1);
            narrativaObject.setValorTotalDesempLing2(valorTotalDesempling2);
            participante.setNarrativaObject(narrativaObject);
        } else {
            NarrativaObject aux = participante.getNarrativaObject();
            aux.setVerificadores(verificadores);
            aux.setValorTotalEstrutura(valorTotalEstrutura);
            aux.setValorTotalDesempLing1(valorTotalDesempling1);
            aux.setValorTotalDesempLing2(valorTotalDesempling2);
            participante.setNarrativaObject(aux);
        }

        atualizaPorcentagem(participante);

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

    public void atualizaPorcentagem(Participante participante) {
        int numeroDeVerf = 2;
        int numeroDeVerfConcluidos = 0;

        if(participante.getNarrativaObject().getVerificadores() != null) {
            numeroDeVerfConcluidos += 1;
        }
        if(participante.getNarrativaObject().getObservacoes() != null) {
            numeroDeVerfConcluidos += 1;
        }

        int porcentagem = (100*numeroDeVerfConcluidos)/numeroDeVerf;

        participante.getNarrativaObject().setPorcentagem(porcentagem);
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
        Intent intentFromList = getIntent();
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
                    strings[0] = "NarrativaActivity";

                    if (!isFinalizado)
                        registrar(participante);

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
