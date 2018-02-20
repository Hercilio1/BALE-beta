package com.example.hercilio.appwithfirebase.Funcionalidades.Usuarios;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Login.LoginActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Pesquisas.CadastroParticipanteActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Pesquisas.PesquisasAdapter;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.Objetos.UserDados;
import com.example.hercilio.appwithfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Hercilio on 19/02/2018.
 */

public class UsuariosFragment  extends Fragment {

    //Responsavel pela recyclerView
    private UsuariosAdapter mUsuarioAdapter;
    private ArrayList<UserDados> items = new ArrayList<>();
    private RecyclerView mRecyclerView;
    //private OnListFragmentInteractionListener mListener;
    //Responsavel pelo btn de cadastro de participante
    private FloatingActionButton btnCadastrarUsuario;
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
        FirebaseAuth auth = FirebaseAuth.getInstance();
        mParticipanteDatabaseReference = mFirebaseDatabase.getReference().child("users");

        final OnListFragmentInteractionListener selecionarItemView = new OnListFragmentInteractionListener() {
            @Override
            public void onListFragmentInteraction(UserDados item) {
                Intent intent = new Intent(getActivity(), BaleLobbyActivity.class);
                intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, (item));
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

//        //Responsavel por ler o banco de dados
//        mChildEventListener = new ChildEventListener() {
//            //Método que lê os valores de determinada key
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                UserDados userDados = dataSnapshot.child("UserDados").getValue(UserDados.class);
//                if(userDados != null) {
//                    items.add(userDados);
//                    mUsuarioAdapter = new UsuariosAdapter(getActivity(), items);
//                    mUsuarioAdapter.setListener(selecionarItemView);
//                    mRecyclerView.setAdapter(mUsuarioAdapter);
//                }
//            }
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {}
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
//            @Override
//            public void onCancelled(DatabaseError databaseError) {}
//        };
//        mParticipanteDatabaseReference.addChildEventListener(mChildEventListener);
    }
}
