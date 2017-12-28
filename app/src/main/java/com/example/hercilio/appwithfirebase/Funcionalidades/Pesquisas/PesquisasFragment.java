package com.example.hercilio.appwithfirebase.Funcionalidades.Pesquisas;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Hercilio on 14/12/2017.
 */

public class PesquisasFragment extends Fragment {

    //Responsavel pela recyclerView
    private PesquisasAdapter mPesquisasAdapter;
    private ArrayList<Participante> items = new ArrayList<>();
    private RecyclerView mRecyclerView;
//    private OnListFragmentInteractionListener mListener;
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
        return inflater.inflate(R.layout.fragment_pesquisas, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Popula RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        final OnListFragmentInteractionListener selecionarItemView = new OnListFragmentInteractionListener() {
            @Override
            public void onListFragmentInteraction(Participante item) {
                Intent intent = new Intent(getActivity(), BaleLobbyActivity.class);
                startActivity(intent);
            }
        };

        //Cria o caminho que garantirá o acesso somente aos participantes do usuário logado
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        mParticipanteDatabaseReference = mFirebaseDatabase.getReference().child("users").child(auth.getCurrentUser().getUid()).child("participantes");

        //Responsavel por ler o banco de dados
        mChildEventListener = new ChildEventListener() {
            //Método que lê os valores de determinada key
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Participante participante = dataSnapshot.getValue(Participante.class);
                items.add(participante);
                mPesquisasAdapter = new PesquisasAdapter(getActivity(), items);
                mPesquisasAdapter.setListener(selecionarItemView);
                mRecyclerView.setAdapter(mPesquisasAdapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mParticipanteDatabaseReference.addChildEventListener(mChildEventListener);
        //-------------------------------

        //Realiza função de cadastrar participante
        btnCadastrarParticipante = (FloatingActionButton) view.findViewById(R.id.btn_cadastrar_participante);
        btnCadastrarParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"CLASUDHAIOSHD", Toast.LENGTH_SHORT);
                Intent intent = new Intent(getActivity(), CadastroParticipanteActivity.class);
                startActivity(intent);
            }
        });

    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnListFragmentInteractionListener) {
//            mListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
}
