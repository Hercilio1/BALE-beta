package com.example.hercilio.appwithfirebase.Funcionalidades.Usuarios;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.hercilio.appwithfirebase.AdminActivity;
import com.example.hercilio.appwithfirebase.Objetos.UserDados;
import com.example.hercilio.appwithfirebase.R;
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

public class UsuariosFragment extends Fragment {
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
    //private OnListFragmentInteractionListener mListener;
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
}
