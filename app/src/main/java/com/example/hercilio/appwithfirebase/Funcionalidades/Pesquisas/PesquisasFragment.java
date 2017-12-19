package com.example.hercilio.appwithfirebase.Funcionalidades.Pesquisas;

import android.content.Context;
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
import android.widget.Button;
import android.widget.Toast;

import com.example.hercilio.appwithfirebase.CadastroParticipanteActivity;
import com.example.hercilio.appwithfirebase.R;

import java.util.ArrayList;

/**
 * Created by Hercilio on 14/12/2017.
 */

public class PesquisasFragment extends Fragment {

    //Responsavel pela recyclerView
    private PesquisasAdapter mPesquisasAdapter;
    private ArrayList<String> items = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private OnListFragmentInteractionListener mListener;
    //Responsavel pelo btn de cadastro de participante
    private FloatingActionButton btnCadastrarParticipante;


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

        items.add("Ricardo1");
        items.add("Ricardo2");
        items.add("Ricardo3");
        items.add("Ricardo4");

        mPesquisasAdapter = new PesquisasAdapter(getActivity(), items, mListener);

        mRecyclerView.setAdapter(mPesquisasAdapter);

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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        // MUDAR STRING POR PESQUISA
        void onListFragmentInteraction(String item);
    }

}
