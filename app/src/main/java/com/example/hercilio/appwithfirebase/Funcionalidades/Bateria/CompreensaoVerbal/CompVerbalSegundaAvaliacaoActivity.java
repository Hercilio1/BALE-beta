package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoVerbal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
 * Created by Hercilio on 09/02/2018.
 */

public class CompVerbalSegundaAvaliacaoActivity extends AppCompatActivity {

    //Label 3:
    private RadioGroup mRadioPergunta1Label3, mRadioPergunta2Label3;
    //Laber 4:
    private RadioGroup mRadioPergunta1Label4, mRadioPergunta2Label4, mRadioPergunta3Label4, mRadioPergunta4Label4, mRadioPergunta5Label4;
    //Check label 3:
    private int checkedRadioPergunta1Label3, checkedRadioPergunta2Label3;
    //Check label 4:
    private int checkedRadioPergunta1Label4, checkedRadioPergunta2Label4, checkedRadioPergunta3Label4, checkedRadioPergunta4Label4, checkedRadioPergunta5Label4;
    //Variaveis dos valores totais:
    private int valorTotalLabel3, valorTotalLabel4;
    //Parciais Label 3:
    private int pergunta1Label3, pergunta2Label3;
    //Parciais Label 4:
    private int pergunta1Label4, pergunta2Label4, pergunta3Label4, pergunta4Label4, pergunta5Label4;
    //Dicionario que armazena os radiobuttons selecionados:
    private Map<String, Integer> verificadores = new HashMap<>();
    //Totais:
    private TextView mTotalNumero1Label3, mTotalNumero1Label4;


    //Botao continuar:
    private Button btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_verbal_segunda_avaliacao);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //Label 3:
        mRadioPergunta1Label3 = (RadioGroup) findViewById(R.id.radio_pergunta1_label3);
        mRadioPergunta2Label3 = (RadioGroup) findViewById(R.id.radio_pergunta2_label3);

        //Label 4:
        mRadioPergunta1Label4 = (RadioGroup) findViewById(R.id.radio_pergunta1_label4);
        mRadioPergunta2Label4 = (RadioGroup) findViewById(R.id.radio_pergunta2_label4);
        mRadioPergunta3Label4 = (RadioGroup) findViewById(R.id.radio_pergunta3_label4);
        mRadioPergunta4Label4 = (RadioGroup) findViewById(R.id.radio_pergunta4_label4);
        mRadioPergunta5Label4 = (RadioGroup) findViewById(R.id.radio_pergunta5_label4);

        //Totais:
        mTotalNumero1Label3 = (TextView) findViewById(R.id.total_number1_label_3);
        mTotalNumero1Label4 = (TextView) findViewById(R.id.total_number1_label_4);


        //Botao continuar:
        btnContinuar = (Button) findViewById(R.id.btn_continuar_comp_verbal_segunda_avaliacao_btn);


        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);

            if (participante.getCompVerbalObject() != null && participante.getCompVerbalObject().getSegundaAvaliacao() != null) {
                verificadores = participante.getCompVerbalObject().getSegundaAvaliacao();
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

    public void autoComplete(Participante participante) {
        int[] aux = {13,23,14,24,34,44,54};
        for(int i=0; i<aux.length; i++) {
            switch (aux[i]) {
                case 13:
                    mRadioPergunta1Label3.check(mRadioPergunta1Label3.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioPergunta1Label3, false));
                    break;
                case 23:
                    mRadioPergunta2Label3.check(mRadioPergunta2Label3.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioPergunta2Label3, false));
                    break;
                case 14:
                    mRadioPergunta1Label4.check(mRadioPergunta1Label4.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioPergunta1Label4, true));
                    break;
                case 24:
                    mRadioPergunta2Label4.check(mRadioPergunta2Label4.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioPergunta2Label4, true));
                    break;
                case 34:
                    mRadioPergunta3Label4.check(mRadioPergunta3Label4.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioPergunta3Label4, false));
                    break;
                case 44:
                    mRadioPergunta4Label4.check(mRadioPergunta4Label4.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioPergunta4Label4, false));
                    break;
                case 54:
                    mRadioPergunta5Label4.check(mRadioPergunta5Label4.getChildAt(verificadores.get(""+aux[i])).getId());
                    somaValoresTotais(aux[i], (int) onFrequenciaRadioButtonClicked(mRadioPergunta5Label4, false));
                    break;
                default: break;
            }
        }
    }

    public void registrar(Participante participante) {
        checkedRadioPergunta1Label3 = (int) onFrequenciaRadioButtonClicked(mRadioPergunta1Label3, false);
        aramzenaDadoNoDicionario(13, checkedRadioPergunta1Label3);

        checkedRadioPergunta2Label3 = (int) onFrequenciaRadioButtonClicked(mRadioPergunta2Label3, false);
        aramzenaDadoNoDicionario(23, checkedRadioPergunta2Label3);

        checkedRadioPergunta1Label4 = (int) onFrequenciaRadioButtonClicked(mRadioPergunta1Label4, true);
        aramzenaDadoNoDicionario(14, checkedRadioPergunta1Label4);

        checkedRadioPergunta2Label4 = (int) onFrequenciaRadioButtonClicked(mRadioPergunta2Label4, true);
        aramzenaDadoNoDicionario(24, checkedRadioPergunta2Label4);

        checkedRadioPergunta3Label4 = (int) onFrequenciaRadioButtonClicked(mRadioPergunta3Label4, false);
        aramzenaDadoNoDicionario(34, checkedRadioPergunta3Label4);

        checkedRadioPergunta4Label4 = (int) onFrequenciaRadioButtonClicked(mRadioPergunta4Label4, false);
        aramzenaDadoNoDicionario(44, checkedRadioPergunta4Label4);

        checkedRadioPergunta5Label4 = (int) onFrequenciaRadioButtonClicked(mRadioPergunta5Label4, false);
        aramzenaDadoNoDicionario(54, checkedRadioPergunta5Label4);

        if(participante != null) {
            alteraDadosFirebase(participante);
        }

    }

    public long onFrequenciaRadioButtonClicked(RadioGroup mRadio, boolean ref) {

        long avaliacao = 2;
        String checkedFrequencia;
        int selectedRadioId = mRadio.getCheckedRadioButtonId();
        if (selectedRadioId != -1) {
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioId);
            checkedFrequencia = selectedRadioButton.getText().toString();
            if(ref) {
                if (checkedFrequencia.equals(getResources().getString(R.string.comp_verbal_segunda_avaliacao_errou))) {
                    avaliacao = 0;
                } else if (checkedFrequencia.equals(getResources().getString(R.string.comp_verbal_segunda_avaliacao_acertou))) {
                    avaliacao = 1;
                }
            } else {
                if (checkedFrequencia.equals(getResources().getString(R.string.comp_verbal_segunda_avaliacao_errou))) {
                    avaliacao = 0;
                } else if (checkedFrequencia.equals(getResources().getString(R.string.comp_verbal_segunda_avaliacao_acertou_parte))) {
                    avaliacao = 1;
                } else if (checkedFrequencia.equals(getResources().getString(R.string.comp_verbal_segunda_avaliacao_acertou))) {
                    avaliacao = 2;
                }
            }
        }
        return avaliacao;
    }

    public void aramzenaDadoNoDicionario(int index, Integer selecao) {
        verificadores.put(""+index, selecao);
        somaValoresTotais(index, selecao);
    }

     public int somaValoresTotais(int ref, int selecao) {
         switch (ref) {
             case 13:
                 valorTotalLabel3 -= pergunta1Label3;
                 pergunta1Label3 = selecao;
                 valorTotalLabel3 += pergunta1Label3;
                 break;
             case 23:
                 valorTotalLabel3 -= pergunta2Label3;
                 pergunta2Label3 = selecao;
                 valorTotalLabel3 += pergunta2Label3;
                 break;
             case 14:
                 valorTotalLabel4 -= pergunta1Label4;
                 pergunta1Label4 = selecao;
                 valorTotalLabel4 += pergunta1Label4;
                 break;
             case 24:
                 valorTotalLabel4 -= pergunta2Label4;
                 pergunta2Label4 = selecao;
                 valorTotalLabel4 += pergunta2Label4;
                 break;
             case 34:
                 valorTotalLabel4 -= pergunta3Label4;
                 pergunta3Label4 = selecao;
                 valorTotalLabel4 += pergunta3Label4;
                 break;
             case 44:
                 valorTotalLabel4 -= pergunta4Label4;
                 pergunta4Label4 = selecao;
                 valorTotalLabel4 += pergunta4Label4;
                 break;
             case 54:
                 valorTotalLabel4 -= pergunta5Label4;
                 pergunta5Label4 = selecao;
                 valorTotalLabel4 += pergunta5Label4;
                 break;
             default:
                 break;
         }
         mTotalNumero1Label3.setText(""+valorTotalLabel3);
         mTotalNumero1Label4.setText(""+valorTotalLabel4);
         return selecao;
     }

     public void onClickRadio(View v) {



         if(v.getId() == mRadioPergunta1Label3.getChildAt(0).getId()
                 || v.getId() == mRadioPergunta1Label3.getChildAt(1).getId()
                 || v.getId() == mRadioPergunta1Label3.getChildAt(2).getId())
             somaValoresTotais(13, (int) onFrequenciaRadioButtonClicked(mRadioPergunta1Label3, false));

         else if(v.getId() == mRadioPergunta2Label3.getChildAt(0).getId()
                 || v.getId() == mRadioPergunta2Label3.getChildAt(1).getId()
                 || v.getId() == mRadioPergunta2Label3.getChildAt(2).getId())
             somaValoresTotais(23, (int) onFrequenciaRadioButtonClicked(mRadioPergunta2Label3, false));

         else if(v.getId() == mRadioPergunta1Label4.getChildAt(0).getId()
                 || v.getId() == mRadioPergunta1Label4.getChildAt(1).getId())
             somaValoresTotais(14, (int) onFrequenciaRadioButtonClicked(mRadioPergunta1Label4, true));

         else if(v.getId() == mRadioPergunta2Label4.getChildAt(0).getId()
                 || v.getId() == mRadioPergunta2Label4.getChildAt(1).getId())
             somaValoresTotais(24, (int) onFrequenciaRadioButtonClicked(mRadioPergunta2Label4, true));

         else if(v.getId() == mRadioPergunta3Label4.getChildAt(0).getId()
                 || v.getId() == mRadioPergunta3Label4.getChildAt(1).getId()
                 || v.getId() == mRadioPergunta3Label4.getChildAt(2).getId())
             somaValoresTotais(34, (int) onFrequenciaRadioButtonClicked(mRadioPergunta3Label4, false));
         else if(v.getId() == mRadioPergunta4Label4.getChildAt(0).getId()
                 || v.getId() == mRadioPergunta4Label4.getChildAt(1).getId()
                 || v.getId() == mRadioPergunta4Label4.getChildAt(2).getId())
             somaValoresTotais(44, (int) onFrequenciaRadioButtonClicked(mRadioPergunta4Label4, false));
         else if(v.getId() == mRadioPergunta5Label4.getChildAt(0).getId()
                 || v.getId() == mRadioPergunta5Label4.getChildAt(1).getId()
                 || v.getId() == mRadioPergunta5Label4.getChildAt(2).getId())
              somaValoresTotais(54, (int) onFrequenciaRadioButtonClicked(mRadioPergunta5Label4, false));
     }

     public void alteraDadosFirebase(Participante participante) {
         if(participante.getCompVerbalObject() == null) {
             CompreensaoVerbalObject compVerbalObj = new CompreensaoVerbalObject();
             compVerbalObj.atualizaSegundaAvaliacao(verificadores);
             compVerbalObj.setValorTotalLabel3(valorTotalLabel3);
             compVerbalObj.setValorTotalLabel4(valorTotalLabel4);
             participante.setCompVerbalObject(compVerbalObj);
         } else {
             participante.getCompVerbalObject().atualizaSegundaAvaliacao(verificadores);
             participante.getCompVerbalObject().setValorTotalLabel3(valorTotalLabel3);
             participante.getCompVerbalObject().setValorTotalLabel3(valorTotalLabel4);
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

}
