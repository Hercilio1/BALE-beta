package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.InformacaoDiscursolivre;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Objetos.InformacaoDiscursoLivreObject;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hercilio on 15/02/2018.
 */

public class DiscursoLivreActivity extends AppCompatActivity {

    //Label 1:
    private RadioGroup mRadioLabel1Quest1, mRadioLabel1Quest2, mRadioLabel1Quest3, mRadioLabel1Quest4, mRadioLabel1Quest5;
    //Laber 2:
    private RadioGroup mRadioLabel2Quest1, mRadioLabel2Quest2, mRadioLabel2Quest3, mRadioLabel2Quest4;

    //Check label 1:
    private int checkedRadioLabel1Quest1, checkedRadioLabel1Quest2, checkedRadioLabel1Quest3, checkedRadioLabel1Quest4, checkedRadioLabel1Quest5;
    //Check label 2:
    private int checkedRadioLabel2Quest1, checkedRadioLabel2Quest2, checkedRadioLabel2Quest3, checkedRadioLabel2Quest4;

    //Variaveis dos valores totais label 1:
    private int valorTotalLabel1;
    private int valorLabel1Quest1, valorLabel1Quest2, valorLabel1Quest3, valorLabel1Quest4, valorLabel1Quest5;
    //Variaveis dos valores totais label 2:
    private int valorTotalLabel2;
    private int valorLabel2Quest1, valorLabel2Quest2, valorLabel2Quest3, valorLabel2Quest4;

    //Dicionario que armazena os radiobuttons selecionados:
    private Map<String, Integer> verificadores = new HashMap<>();

    //Total text view:
    private TextView mTotalLabel1, mTotalLabel2;

    //Botão validador caso não haja o pressionamento de algum radio button
    private boolean validaRadioButtons;

    //Botao continuar:
    private Button btnContinuar;

    //Variável que auxilia na verificacao se o teste já está finalizado
    private boolean isFinalizado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discurso_livre);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //Label 1:
        mRadioLabel1Quest1 = (RadioGroup) findViewById(R.id.radio_discursolivre_label1_quest1);
        mRadioLabel1Quest2 = (RadioGroup) findViewById(R.id.radio_discursolivre_label1_quest2);
        mRadioLabel1Quest3 = (RadioGroup) findViewById(R.id.radio_discursolivre_label1_quest3);
        mRadioLabel1Quest4 = (RadioGroup) findViewById(R.id.radio_discursolivre_label1_quest4);
        mRadioLabel1Quest5 = (RadioGroup) findViewById(R.id.radio_discursolivre_label1_quest5);

        //Label 2:
        mRadioLabel2Quest1 = (RadioGroup) findViewById(R.id.radio_discursolivre_label2_quest1);
        mRadioLabel2Quest2 = (RadioGroup) findViewById(R.id.radio_discursolivre_label2_quest2);
        mRadioLabel2Quest3 = (RadioGroup) findViewById(R.id.radio_discursolivre_label2_quest3);
        mRadioLabel2Quest4 = (RadioGroup) findViewById(R.id.radio_discursolivre_label2_quest4);

        //Totais:
        mTotalLabel1 = (TextView) findViewById(R.id.discursolivre_total_number1_label1);
        mTotalLabel2 = (TextView) findViewById(R.id.discursolivre_total_number1_label2);

        //Botao continuar:
        btnContinuar = (Button) findViewById(R.id.btn_continuar_discursolivre);


        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);
            isFinalizado = participante.isFinalizado();

            if (participante.getInformacaoDiscLivreObject() != null && participante.getInformacaoDiscLivreObject().getDiscursoLivre() != null) {
                verificadores = participante.getInformacaoDiscLivreObject().getDiscursoLivre();
                autoComplete();
            }

            btnContinuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!isFinalizado)
                        registrar(participante);
                    if(!validaRadioButtons) {
                        Intent intent = new Intent(getBaseContext(), InformacaoDiscursolivreLobbyActivity.class);
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

    public void registrar(Participante participante) {
        ArrayList<Integer> verificaValidade = new ArrayList<>();

        //Label 1:
        checkedRadioLabel1Quest1 = (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest1, false);
        if(checkedRadioLabel1Quest1 == -1)
            verificaValidade.add(11);
        armazenaDadoNoDicionario(11, checkedRadioLabel1Quest1);

        checkedRadioLabel1Quest2 = (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest2, false);
        if(checkedRadioLabel1Quest2 == -1)
            verificaValidade.add(12);
        armazenaDadoNoDicionario(12, checkedRadioLabel1Quest2);

        checkedRadioLabel1Quest3 = (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest3, false);
        if(checkedRadioLabel1Quest3 == -1)
            verificaValidade.add(13);
        armazenaDadoNoDicionario(13, checkedRadioLabel1Quest3);

        checkedRadioLabel1Quest4 = (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest4, false);
        if(checkedRadioLabel1Quest4 == -1)
            verificaValidade.add(14);
        armazenaDadoNoDicionario(14, checkedRadioLabel1Quest4);

        checkedRadioLabel1Quest5 = (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest5, false);
        if(checkedRadioLabel1Quest5 == -1)
            verificaValidade.add(15);
        armazenaDadoNoDicionario(15, checkedRadioLabel1Quest5);

        //Label2:
        checkedRadioLabel2Quest1 = (int) onFrequenciaRadioButtonClicked(mRadioLabel2Quest1, true);
        if(checkedRadioLabel2Quest1 == -1)
            verificaValidade.add(21);
        armazenaDadoNoDicionario(21, checkedRadioLabel2Quest1);

        checkedRadioLabel2Quest2 = (int) onFrequenciaRadioButtonClicked(mRadioLabel2Quest2, true);
        if(checkedRadioLabel2Quest2 == -1)
            verificaValidade.add(22);
        armazenaDadoNoDicionario(22, checkedRadioLabel2Quest2);

        checkedRadioLabel2Quest3 = (int) onFrequenciaRadioButtonClicked(mRadioLabel2Quest3, true);
        if(checkedRadioLabel2Quest3 == -1)
            verificaValidade.add(23);
        armazenaDadoNoDicionario(23, checkedRadioLabel2Quest3);

        checkedRadioLabel2Quest4 = (int) onFrequenciaRadioButtonClicked(mRadioLabel2Quest4, false);
        if(checkedRadioLabel2Quest4 == -1)
            verificaValidade.add(24);
        armazenaDadoNoDicionario(24, checkedRadioLabel2Quest4);

        if(!verificaValidade.isEmpty()) {
            validaRadioButtons = true;
        } else if (participante != null) {
            validaRadioButtons = false;
            alteraDadosFirebase(participante);
        }

    }

    public long onFrequenciaRadioButtonClicked(RadioGroup mRadio, boolean ref) {

        long avaliacao = -1;
        String checkedFrequencia;
        int selectedRadioId = mRadio.getCheckedRadioButtonId();
        if (selectedRadioId != -1) {
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioId);
            checkedFrequencia = selectedRadioButton.getText().toString();
            if(ref) {
                if (checkedFrequencia.equals(getResources().getString(R.string.informacao_sim))) {
                    avaliacao = 0;
                } else if (checkedFrequencia.equals(getResources().getString(R.string.informacao_nao))) {
                    avaliacao = 1;
                }
            } else {
                if (checkedFrequencia.equals(getResources().getString(R.string.informacao_sim))) {
                    avaliacao = 0;
                } else if (checkedFrequencia.equals(getResources().getString(R.string.informacao_semelhante))) {
                    avaliacao = 1;
                } else if (checkedFrequencia.equals(getResources().getString(R.string.informacao_nao))) {
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
                //Label 1:
                case 11:
                    valorTotalLabel1 -= valorLabel1Quest1;
                    valorLabel1Quest1 = selecao;
                    valorTotalLabel1 += valorLabel1Quest1;
                    break;
                case 12:
                    valorTotalLabel1 -= valorLabel1Quest2;
                    valorLabel1Quest2 = selecao;
                    valorTotalLabel1 += valorLabel1Quest2;
                    break;
                case 13:
                    valorTotalLabel1 -= valorLabel1Quest3;
                    valorLabel1Quest3 = selecao;
                    valorTotalLabel1 += valorLabel1Quest3;
                    break;
                case 14:
                    valorTotalLabel1 -= valorLabel1Quest4;
                    valorLabel1Quest4 = selecao;
                    valorTotalLabel1 += valorLabel1Quest4;
                    break;
                case 15:
                    valorTotalLabel1 -= valorLabel1Quest5;
                    valorLabel1Quest5 = selecao;
                    valorTotalLabel1 += valorLabel1Quest5;
                    break;

                //Label 2:
                case 21:
                    valorTotalLabel2 -= valorLabel2Quest1;
                    valorLabel2Quest1 = selecao;
                    valorTotalLabel2 += valorLabel2Quest1;
                    break;
                case 22:
                    valorTotalLabel2 -= valorLabel2Quest2;
                    valorLabel2Quest2 = selecao;
                    valorTotalLabel2 += valorLabel2Quest2;
                    break;
                case 23:
                    valorTotalLabel2 -= valorLabel2Quest3;
                    valorLabel2Quest3 = selecao;
                    valorTotalLabel2 += valorLabel2Quest3;
                    break;
                case 24:
                    valorTotalLabel2 -= valorLabel2Quest4;
                    valorLabel2Quest4 = selecao;
                    valorTotalLabel2 += valorLabel2Quest4;
                    break;
                default:
                    break;
            }
            mTotalLabel1.setText("" + valorTotalLabel1);
            mTotalLabel2.setText("" + valorTotalLabel2);
        }
    }

    public void onClickRadio(View v) {
        if(!isFinalizado) {
            //Label 1:
            if (v.getId() == mRadioLabel1Quest1.getChildAt(0).getId()
                    || v.getId() == mRadioLabel1Quest1.getChildAt(1).getId()
                    || v.getId() == mRadioLabel1Quest1.getChildAt(2).getId())
                somaValoresTotais(11, (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest1, false));
            else if (v.getId() == mRadioLabel1Quest2.getChildAt(0).getId()
                    || v.getId() == mRadioLabel1Quest2.getChildAt(1).getId()
                    || v.getId() == mRadioLabel1Quest2.getChildAt(2).getId())
                somaValoresTotais(12, (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest2, false));
            else if (v.getId() == mRadioLabel1Quest3.getChildAt(0).getId()
                    || v.getId() == mRadioLabel1Quest3.getChildAt(1).getId()
                    || v.getId() == mRadioLabel1Quest3.getChildAt(2).getId())
                somaValoresTotais(13, (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest3, false));
            else if (v.getId() == mRadioLabel1Quest4.getChildAt(0).getId()
                    || v.getId() == mRadioLabel1Quest4.getChildAt(1).getId()
                    || v.getId() == mRadioLabel1Quest4.getChildAt(2).getId())
                somaValoresTotais(14, (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest4, false));
            else if (v.getId() == mRadioLabel1Quest5.getChildAt(0).getId()
                    || v.getId() == mRadioLabel1Quest5.getChildAt(1).getId()
                    || v.getId() == mRadioLabel1Quest5.getChildAt(2).getId())
                somaValoresTotais(15, (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest5, false));

            //Label 2:
            if (v.getId() == mRadioLabel2Quest1.getChildAt(0).getId()
                    || v.getId() == mRadioLabel2Quest1.getChildAt(1).getId())
                somaValoresTotais(21, (int) onFrequenciaRadioButtonClicked(mRadioLabel2Quest1, true));
            if (v.getId() == mRadioLabel2Quest2.getChildAt(0).getId()
                    || v.getId() == mRadioLabel2Quest2.getChildAt(1).getId())
                somaValoresTotais(22, (int) onFrequenciaRadioButtonClicked(mRadioLabel2Quest2, true));
            if (v.getId() == mRadioLabel2Quest3.getChildAt(0).getId()
                    || v.getId() == mRadioLabel2Quest3.getChildAt(1).getId())
                somaValoresTotais(23, (int) onFrequenciaRadioButtonClicked(mRadioLabel2Quest3, true));
            else if (v.getId() == mRadioLabel2Quest4.getChildAt(0).getId()
                    || v.getId() == mRadioLabel2Quest4.getChildAt(1).getId()
                    || v.getId() == mRadioLabel2Quest4.getChildAt(2).getId())
                somaValoresTotais(24, (int) onFrequenciaRadioButtonClicked(mRadioLabel2Quest4, false));
        }
    }

    public void autoComplete() {
        int[] aux = {11,12,13,14,15,21,22,23,24};
        for(int i=0; i<aux.length; i++) {
            switch (aux[i]) {
                case 11:
                    mRadioLabel1Quest1.check(mRadioLabel1Quest1.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest1, false));
                    break;
                case 12:
                    mRadioLabel1Quest2.check(mRadioLabel1Quest2.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest2, false));
                    break;
                case 13:
                    mRadioLabel1Quest3.check(mRadioLabel1Quest3.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest3, false));
                    break;
                case 14:
                    mRadioLabel1Quest4.check(mRadioLabel1Quest4.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest4, false));
                    break;
                case 15:
                    mRadioLabel1Quest5.check(mRadioLabel1Quest5.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioLabel1Quest5, true));
                    break;
                case 21:
                    mRadioLabel2Quest1.check(mRadioLabel2Quest1.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioLabel2Quest1, true));
                    break;
                case 22:
                    mRadioLabel2Quest2.check(mRadioLabel2Quest2.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioLabel2Quest2, true));
                    break;
                case 23:
                    mRadioLabel2Quest3.check(mRadioLabel2Quest3.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioLabel2Quest3, true));
                    break;
                case 24:
                    mRadioLabel2Quest4.check(mRadioLabel2Quest4.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioLabel2Quest4, false));
                    break;
                default: break;
            }
        }
    }

    public void alteraDadosFirebase(Participante participante) {
        InformacaoDiscursoLivreObject infDiscNarrObj;

        if(participante.getInformacaoDiscLivreObject() == null)
            infDiscNarrObj = new InformacaoDiscursoLivreObject();
        else
            infDiscNarrObj = participante.getInformacaoDiscLivreObject();

        infDiscNarrObj.atualizaDiscursoLivre(verificadores);
        infDiscNarrObj.setValorTotalDiscursoLivreEstrutura(valorTotalLabel1);
        infDiscNarrObj.setValorTotalDiscursoLivreDesempenho(valorTotalLabel2);
        participante.setInformacaoDiscLivreObject(infDiscNarrObj);

        new InformacaoDiscursolivreLobbyActivity().atualizaPorcentagem(participante);

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
