package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hercilio.appwithfirebase.AdminActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.HabitosDeLeituraEscritra.HabitosLeituraEscritaActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Observacoes.ObservacoesActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Usuarios.CadastraUsuarioActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Usuarios.UsuariosAdapter;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.Objetos.UserDados;
import com.example.hercilio.appwithfirebase.R;
import com.example.hercilio.appwithfirebase.UsersActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Hercilio on 26/12/2017.
 */

public class BaleLobbyActivity extends AppCompatActivity {

    public static final String EXTRA_PARTICIPANTE = "participante";

    private boolean isComplete, isFinalizado;

    private final Intent[] intentHome = new Intent[1];;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bale_lobby);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(EXTRA_PARTICIPANTE);

            if(participante.getPorcentagem() == 100 && !participante.isFinalizado())
                isComplete = true;
            if(participante.isFinalizado())
                isFinalizado = true;
            else
                atualizaPorcentagem(participante);

            if (savedInstanceState == null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container_bale_lobby, BaleLobbyFragment.newInstance(participante), "BaleLobby")
                        .commit();
            }


            //Cria o caminho que garantirá o acesso somente aos participantes do usuário logado
            FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
            FirebaseAuth auth = FirebaseAuth.getInstance();
            DatabaseReference mParticipanteDatabaseReference = mFirebaseDatabase.getReference().child("users").child(auth.getCurrentUser().getUid()).child("UserDados");


            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    UserDados userDados = dataSnapshot.getValue(UserDados.class);
                    if(userDados.isAdmin())
                        intentHome[0] = new Intent(getBaseContext(), AdminActivity.class);
                    else
                        intentHome[0] = new Intent(getBaseContext(), UsersActivity.class);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {}
            };
            mParticipanteDatabaseReference.addListenerForSingleValueEvent(valueEventListener);
        }
    }

    public void atualizaPorcentagem(Participante participante) {
        int numeroDeVerf = 11 * 100;
        int numeroDeVerfConcluidos = 0;

        if(participante.getHleObject() != null)
            numeroDeVerfConcluidos += participante.getHleObject().getPorcentagem();

        if(participante.getCompFrasesObject() != null)
            numeroDeVerfConcluidos += participante.getCompFrasesObject().getPorcentagem();

        if(participante.getMemEpObject() != null)
            numeroDeVerfConcluidos += participante.getMemEpObject().getPorcentagem();

        if(participante.getCompVerbalObject() != null)
            numeroDeVerfConcluidos += participante.getCompVerbalObject().getPorcentagem();

        if(participante.getInformacaoDiscLivreObject() != null)
            numeroDeVerfConcluidos += participante.getInformacaoDiscLivreObject().getPorcentagem();

        if(participante.getNarrativaObject() != null)
            numeroDeVerfConcluidos += participante.getNarrativaObject().getPorcentagem();

        if(participante.getFluenciaVerbalObject() != null)
            numeroDeVerfConcluidos += participante.getFluenciaVerbalObject().getPorcentagem();

        if(participante.getNomeacaoObject() != null)
            numeroDeVerfConcluidos += participante.getNomeacaoObject().getPorcentagem();

        if(participante.getDigitSpanObject() != null)
            numeroDeVerfConcluidos += participante.getDigitSpanObject().getPorcentagem();

        if(participante.getAssociacaoSemanticaObject() != null)
            numeroDeVerfConcluidos += participante.getAssociacaoSemanticaObject().getPorcentagem();

        if(participante.getConhecimentoSemanticoObject() != null)
            numeroDeVerfConcluidos += participante.getConhecimentoSemanticoObject().getPorcentagem();

        int porcentagem = (100*numeroDeVerfConcluidos)/numeroDeVerf;

        participante.setPorcentagem(porcentagem);

        //Cria o caminho que garantirá o acesso somente aos participantes do usuário logado
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        DatabaseReference mParticipanteDatabaseReference = mFirebaseDatabase.getReference().child("users").child(auth.getCurrentUser().getUid()).child("participantes");

        mParticipanteDatabaseReference.child(participante.getCpf()).setValue(participante);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        //Instanciação das referências.
        MenuItem baleLobbyFinalizarBtn = menu.findItem(R.id.bale_lobby_finalizar_btn);
        MenuItem baleLobbyReativarBtn = menu.findItem(R.id.bale_lobby_retestar_btn);
        if(isComplete) {
            baleLobbyFinalizarBtn.setVisible(true);
            baleLobbyReativarBtn.setVisible(false);
        }
        if(isFinalizado) {
            baleLobbyReativarBtn.setVisible(true);
            baleLobbyFinalizarBtn.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();

        final Intent intent = intentHome[0];

        Intent intentFromList = getIntent();
        //Faz as transações
        switch (itemId) {
            case android.R.id.home:
                startActivity(intent);
                return true;

            case R.id.bale_lobby_finalizar_btn:
                if (intentFromList != null) {
                    final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);

                    AlertDialog.Builder alert = new AlertDialog.Builder(this);

                    alert.setTitle("Atenção");
                    alert.setMessage("Os resultados da bateria realizada não poderão ser alterados. Você tem certeza que deseja finalizar a pesquisa?");

                    alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            participante.setFinalizado(true);

                            FirebaseDatabase mFirebaseDatabase;
                            final DatabaseReference mParticipanteDatabaseReference;
                            mFirebaseDatabase = FirebaseDatabase.getInstance();
                            final FirebaseAuth auth = FirebaseAuth.getInstance();
                            mParticipanteDatabaseReference = mFirebaseDatabase.getReference().child("users").child(auth.getCurrentUser().getUid()).child("participantes");
                            mParticipanteDatabaseReference.child(participante.getCpf()).setValue(participante);

                            startActivity(intent);
                        }
                    });

                    alert.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    AlertDialog dialog = alert.create();
                    alert.show();
                }
                return true;

            case R.id.bale_lobby_retestar_btn:
                if (intentFromList != null) {
                    final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);

                    AlertDialog.Builder alert = new AlertDialog.Builder(this);

                    alert.setTitle("Atenção");
                    alert.setMessage("Os resultados da bateria realizada poderão ser alterados. Você tem certeza que deseja reativar a pesquisa?");

                    alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            participante.setFinalizado(false);

                            FirebaseDatabase mFirebaseDatabase;
                            final DatabaseReference mParticipanteDatabaseReference;
                            mFirebaseDatabase = FirebaseDatabase.getInstance();
                            final FirebaseAuth auth = FirebaseAuth.getInstance();
                            mParticipanteDatabaseReference = mFirebaseDatabase.getReference().child("users").child(auth.getCurrentUser().getUid()).child("participantes");
                            mParticipanteDatabaseReference.child(participante.getCpf()).setValue(participante);

                            startActivity(intent);
                        }
                    });

                    alert.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    AlertDialog dialog = alert.create();
                    alert.show();
                }
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
