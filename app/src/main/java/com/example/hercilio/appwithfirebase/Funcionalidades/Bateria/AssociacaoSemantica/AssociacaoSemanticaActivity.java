package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.AssociacaoSemantica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Nomeacao.ImagemExpandidaNomeacaoActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Nomeacao.NomeacaoAdapter;
import com.example.hercilio.appwithfirebase.Objetos.AssociacaoSemanticaObject;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hercilio on 10/03/2018.
 */

public class AssociacaoSemanticaActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Button mBtnContinuar;

    private Map<String, Integer> verificadores = new HashMap<>();

    private int total;

    private TextView mTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_associacao_semantica);

        //Rotina da recyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_associacao_semantica);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        //Rotina do adapter
        AssociacaoSemanticaAdapter associacaoSemanticaAdapter = new AssociacaoSemanticaAdapter(this);
        associacaoSemanticaAdapter.setListener(selecionarItemView);
        mRecyclerView.setAdapter(associacaoSemanticaAdapter);

        mTotal = (TextView) findViewById(R.id.total_number1_associacao_semantica);

        mBtnContinuar = (Button) findViewById(R.id.button_associacao_semantica);


        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);

            if(participante.getAssociacaoSemanticaObject() != null) {
                verificadores = participante.getAssociacaoSemanticaObject().getVerificadores();
                atualizaTotal();
            }

            mBtnContinuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registrar(participante);
                    Intent intent = new Intent(getBaseContext(),  BaleLobbyActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    startActivity(intent);
                }
            });
        }

    }

    final OnListFragmentInteractionListener selecionarItemView = new OnListFragmentInteractionListener() {
        @Override
        public void onListFragmentInteraction(ImageButton item) {
            Intent intentFromList = getIntent();
            if (intentFromList != null) {
                Integer[] ref = new Integer[1];
                ref[0] = (Integer) item.getTag();

                Intent intent = new Intent(getBaseContext(), ImagemExpandidaAssociacaoSemanticaActivity.class);
                intent.putExtra(ImagemExpandidaNomeacaoActivity.IMAGEM_EXPANDIDA, ref);
                startActivity(intent);
            }

        }

        @Override
        public void onRadioFragmentInteraction() {
            atualizaTotal();
        }


    };

    public void onClickRadio(View v) {
        RadioButton radioButton = (RadioButton) v;
        RadioGroup item = (RadioGroup) radioButton.getParent();

        if(onFrequenciaRadioButtonClicked(item) == 0) {
            verificadores.put(item.getTag().toString(), 0);
        } else if(onFrequenciaRadioButtonClicked(item) == 1) {
            verificadores.put(item.getTag().toString(), 1);
        } else if(onFrequenciaRadioButtonClicked(item) == 2) {
            verificadores.put(item.getTag().toString(), 2);
        }
        atualizaTotal();
        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);
            registrar(participante);
        }
    }

    public void atualizaTotal() {
        String[] dicas = {"maçã – uva", "óculos – livro","rato – gato"
                , "pente – espelho", "árvore – maçã", "xícara – chaleira"
                , "morcego – coruja", "mala – camisa", "borboleta – lagarta"
                , "cadeado – bicicleta", "elefante – gorila", "isqueiro – vela"};
        total = 0;
        for(String x : dicas) {
            if(verificadores.containsKey(x))
                total += verificadores.get(x);
        }
        mTotal.setText(""+total);
    }

    public long onFrequenciaRadioButtonClicked(RadioGroup mRadio) {

        long avaliacao = -1;
        String checkedFrequencia;
        int selectedRadioId = mRadio.getCheckedRadioButtonId();
        if (selectedRadioId != -1) {
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioId);
            checkedFrequencia = selectedRadioButton.getText().toString();

            if (checkedFrequencia.equals(getResources().getString(R.string.associacao_semantica_errou))) {
                avaliacao = 0;
            } else if (checkedFrequencia.equals(getResources().getString(R.string.associacao_semantica_parcialmente))) {
                avaliacao = 1;
            } else if (checkedFrequencia.equals(getResources().getString(R.string.associacao_semantica_acertou))) {
                avaliacao = 2;
            }

        }
        return avaliacao;
    }



    public void registrar(Participante participante) {
        AssociacaoSemanticaObject associacaoSemanticaObject;
        if (participante.getAssociacaoSemanticaObject() == null)
            associacaoSemanticaObject = new AssociacaoSemanticaObject();
        else
            associacaoSemanticaObject = participante.getAssociacaoSemanticaObject();

        associacaoSemanticaObject.setVerificadores(verificadores);
        associacaoSemanticaObject.setValorTotal(total);
        participante.setAssociacaoSemanticaObject(associacaoSemanticaObject);


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
