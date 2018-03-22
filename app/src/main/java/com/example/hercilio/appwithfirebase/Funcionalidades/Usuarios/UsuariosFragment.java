package com.example.hercilio.appwithfirebase.Funcionalidades.Usuarios;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.hercilio.appwithfirebase.AdminActivity;
import com.example.hercilio.appwithfirebase.Objetos.UserDados;
import com.example.hercilio.appwithfirebase.R;
import com.example.hercilio.appwithfirebase.UsersActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Hercilio on 19/02/2018.
 */

public class UsuariosFragment extends Fragment implements SearchView.OnQueryTextListener {
    public static class IdWithUserDados implements Serializable{
        private String idUser;
        private UserDados userDados;

        public IdWithUserDados () {}

        public IdWithUserDados(String idUser, UserDados userDados) {
            this.idUser = idUser;
            this.userDados = userDados;
        }

        public String getIdUser() {
            return idUser;
        }
        public UserDados getUserDados() {
            return userDados;
        }
    }

    public static final String EXTRA_USER_FRAGMENT_FOR_PESQUISA_FRAGMENT = "UserFragForPesquisaFrag";

    //Responsavel pela recyclerView
    private UsuariosAdapter mUsuarioAdapter;
    private ArrayList<UsuariosFragment.IdWithUserDados> items = new ArrayList<>();
    private RecyclerView mRecyclerView;

    //Responsavel pelo btn de cadastro de participante
    private FloatingActionButton btnCadastrarUsuario;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mUsuarioDatabaseReference;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_usuarios, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Popula RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_usuarios);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        //Cria o caminho que garantirá o acesso somente aos participantes do usuário logado
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        mUsuarioDatabaseReference = mFirebaseDatabase.getReference().child("users");

        final OnListFragmentInteractionListener selecionarItemView = new OnListFragmentInteractionListener() {
            @Override
            public void onListFragmentInteraction(IdWithUserDados item) {
                Intent intent = new Intent(getActivity(), AdminActivity.class);
                intent.putExtra(EXTRA_USER_FRAGMENT_FOR_PESQUISA_FRAGMENT, item);
                startActivity(intent);
            }
        };

        //Realiza função de cadastrar participante
        btnCadastrarUsuario = (FloatingActionButton) view.findViewById(R.id.btn_cadastrar_usuario);
        btnCadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentFromList = getActivity().getIntent();
                if (intentFromList != null) {
                    final String[] passwordAdmin = (String[]) intentFromList.getSerializableExtra(CadastraUsuarioActivity.EXTRA_ADMIN_USER);

                    Intent intent = new Intent(getActivity(), CadastraUsuarioActivity.class);
                    intent.putExtra(CadastraUsuarioActivity.EXTRA_ADMIN_USER, passwordAdmin);
                    startActivity(intent);
                }

            }
        });

        ValueEventListener eventListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        if(ds.getKey().equals(auth.getCurrentUser().getUid()))
                            continue;
                        UserDados userDados = ds.child("UserDados").getValue(UserDados.class);
                        IdWithUserDados idWithUserDados = new IdWithUserDados(ds.getKey(), userDados);
                        items.add(idWithUserDados);
                    }
                    mUsuarioAdapter = new UsuariosAdapter(getActivity(), items);
                    mUsuarioAdapter.setListener(selecionarItemView);
                    mRecyclerView.setAdapter(mUsuarioAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        mUsuarioDatabaseReference.addValueEventListener(eventListener);
    }

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
        mUsuarioAdapter.getFilter().filter(newText);
        return false;
    }
}
