package com.example.hercilio.appwithfirebase.Funcionalidades.Pesquisas;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Usuarios.CadastraUsuarioActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Usuarios.UsuariosFragment;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;
import com.example.hercilio.appwithfirebase.UsersActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.support.v7.widget.SearchView;

import java.util.ArrayList;

/**
 * Created by Hercilio on 14/12/2017.
 */

public class PesquisasFragment extends Fragment implements SearchView.OnQueryTextListener{

    //Responsavel pela recyclerView
    private PesquisasAdapter mPesquisasAdapter;
    private ArrayList<Participante> items = new ArrayList<>();
    private RecyclerView mRecyclerView;
    //Responsavel pelo btn de cadastro de participante
    private FloatingActionButton btnCadastrarParticipante;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mParticipanteDatabaseReference;
    private ChildEventListener mChildEventListener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_pesquisas, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Popula RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        btnCadastrarParticipante = (FloatingActionButton) view.findViewById(R.id.btn_cadastrar_participante);

        //Cria o caminho que garantirá o acesso somente aos participantes do usuário logado
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();

        Intent intentFromList = getActivity().getIntent();
        if (intentFromList != null) {
            final UsuariosFragment.IdWithUserDados keyIdUsuarioListado =
                    (UsuariosFragment.IdWithUserDados) intentFromList.getSerializableExtra(UsuariosFragment.EXTRA_USER_FRAGMENT_FOR_PESQUISA_FRAGMENT);
            if(keyIdUsuarioListado != null) {
                mParticipanteDatabaseReference = mFirebaseDatabase.getReference().child("users").child(keyIdUsuarioListado.getIdUser()).child("participantes");
                btnCadastrarParticipante.setVisibility(View.INVISIBLE);
                intentFromList.removeExtra(UsuariosFragment.EXTRA_USER_FRAGMENT_FOR_PESQUISA_FRAGMENT);
            } else {
                mParticipanteDatabaseReference = mFirebaseDatabase.getReference().child("users").child(auth.getCurrentUser().getUid()).child("participantes");
                btnCadastrarParticipante.setVisibility(View.VISIBLE);
            }
        }


        //Realiza função de cadastrar participante
        btnCadastrarParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CadastroParticipanteActivity.class);
                startActivity(intent);
            }
        });

        //Responsavel por ler o banco de dados
        mChildEventListener = new ChildEventListener() {
            //Método que lê os valores de determinada key
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Participante participante = dataSnapshot.getValue(Participante.class);
                if(!participante.isFinalizado()) {
                    items.add(participante);
                    mPesquisasAdapter = new PesquisasAdapter(getActivity(), items);
                    mPesquisasAdapter.setListener(selecionarItemView);
                    mRecyclerView.setAdapter(mPesquisasAdapter);
                }
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        mParticipanteDatabaseReference.addChildEventListener(mChildEventListener);
    }

    private final OnListFragmentInteractionListener selecionarItemView = new OnListFragmentInteractionListener() {
        @Override
        public void onListFragmentInteraction(Participante item) {
            Intent intent = new Intent(getActivity(), BaleLobbyActivity.class);
            intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, (item));
            startActivity(intent);
        }
    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view, menu);


        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getActivity().getComponentName()));

        if(getActivity() instanceof UsersActivity)
            menu.getItem(0).setIcon(R.drawable.ic_search_white_24dp);

        searchView.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu,inflater);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(final String newText) {
        mPesquisasAdapter.getFilter().filter(newText);
        return false;
    }

}
