package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Observacoes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Objetos.CompreensaoVerbalObject;
import com.example.hercilio.appwithfirebase.Objetos.InformacaoDiscursoLivreObject;
import com.example.hercilio.appwithfirebase.Objetos.NarrativaObject;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hercilio on 13/03/2018.
 */

public class ObservacoesActivity extends AppCompatActivity {

    public static final String ACTIVITY_LISTENER = "String[]";

    private TextView mTitle;
    private EditText mObservacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mTitle = (TextView) findViewById(R.id.title_observacoes);
        mObservacoes = (EditText) findViewById(R.id.observacoes_edit_text);

        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);
            final String[] indicador = (String[]) intentFromList.getSerializableExtra(ACTIVITY_LISTENER);
            switch (indicador[0]) {
                case "CompreensaoVerbalLobbyActivity":
                    mTitle.setText(R.string.bateria_tipopergunta_compverbal);
                    if(participante.getCompVerbalObject() != null && participante.getCompVerbalObject().getObservacoes() != null)
                        mObservacoes.setText(participante.getCompVerbalObject().getObservacoes());
                    break;
                case "InformacaoDiscursolivreLobbyActivity":
                    mTitle.setText("Informação e Discurso Livre");
                    if(participante.getInformacaoDiscLivreObject() != null && participante.getInformacaoDiscLivreObject().getObservacoes() != null)
                        mObservacoes.setText(participante.getInformacaoDiscLivreObject().getObservacoes());
                    break;
                case "NarrativaActivity":
                    mTitle.setText("Narrativa");
                    if(participante.getNarrativaObject() != null && participante.getNarrativaObject().getObservacoes() != null)
                        mObservacoes.setText(participante.getNarrativaObject().getObservacoes());
                    break;
                default:
                    break;
            }
        }

    }

    public void registrarCompVerbal(Participante participante) {
        CompreensaoVerbalObject compreensaoVerbalObject;
        if (participante.getCompVerbalObject() == null)
            compreensaoVerbalObject = new CompreensaoVerbalObject();
        else
            compreensaoVerbalObject = participante.getCompVerbalObject();

        compreensaoVerbalObject.setObservacoes(mObservacoes.getText().toString());
        participante.setCompVerbalObject(compreensaoVerbalObject);

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

    public void registrarInfDiscLivre(Participante participante) {
        InformacaoDiscursoLivreObject informacaoDiscursoLivreObject;
        if (participante.getInformacaoDiscLivreObject() == null)
            informacaoDiscursoLivreObject = new InformacaoDiscursoLivreObject();
        else
            informacaoDiscursoLivreObject = participante.getInformacaoDiscLivreObject();

        informacaoDiscursoLivreObject.setObservacoes(mObservacoes.getText().toString());
        participante.setInformacaoDiscLivreObject(informacaoDiscursoLivreObject);

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

    public void registrarNarrativa(Participante participante) {
        NarrativaObject narrativaObject;
        if (participante.getNarrativaObject() == null)
            narrativaObject = new NarrativaObject();
        else
            narrativaObject = participante.getNarrativaObject();

        narrativaObject.setObservacoes(mObservacoes.getText().toString());
        participante.setNarrativaObject(narrativaObject);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.obeservacoes_button, menu);
        //Instanciação das referências.
        MenuItem baleLobbyHomeBtn = menu.findItem(R.id.bale_observacoes_button_concluir);
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
        switch (id) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
            case R.id.bale_observacoes_button_concluir:

                Intent intentFromList = getIntent();
                if (intentFromList != null) {
                    final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);

                    switch (mTitle.getText().toString()) {
                        case "Compreensão Verbal":
                            registrarCompVerbal(participante);
                            break;
                        case "Informação e Discurso Livre":
                            registrarInfDiscLivre(participante);
                            break;
                        case "Narrativa":
                            registrarNarrativa(participante);
                            break;
                        default:
                            break;
                    }

                    Intent intent = new Intent(this, BaleLobbyActivity.class);
                    intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                    startActivity(intent);
                }
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
