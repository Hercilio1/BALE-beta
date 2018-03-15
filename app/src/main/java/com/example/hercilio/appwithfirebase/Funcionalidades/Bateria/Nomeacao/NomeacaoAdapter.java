package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Nomeacao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Pesquisas.*;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import at.grabner.circleprogress.CircleProgressView;

/**
 * Created by Hercilio on 05/03/2018.
 */

public class NomeacaoAdapter extends RecyclerView.Adapter<NomeacaoAdapter.NomeacaoItemView> {

    private Activity activity;
    private Integer[] items = {R.drawable.a1_chave_de_fenda, R.drawable.a2_esquilo, R.drawable.a3_regador, R.drawable.a4_rinocerounte
            , R.drawable.b1_chaved_e_fenda, R.drawable.b2_pinguim, R.drawable.b3_escova_de_dentes, R.drawable.b4_aguia
            , R.drawable.c1_serrote, R.drawable.c2_avestruz, R.drawable.c3_alicate, R.drawable.c4_canguru
            , R.drawable.d1_lixeira, R.drawable.d2_urso, R.drawable.d3_pente, R.drawable.d4_pavao
            , R.drawable.e1_pincel, R.drawable.e2_cisne, R.drawable.e3_sofa, R.drawable.e4_jacare
            , R.drawable.f1_escova, R.drawable.f2_pera, R.drawable.f3_tesoura, R.drawable.f4_milho
            , R.drawable.g1_machado, R.drawable.g2_sapo, R.drawable.g3_motocicleta, R.drawable.g4_abacaxi
            , R.drawable.h1_carta, R.drawable.h2_tartaruga, R.drawable.h3_helicoptero, R.drawable.h4_coruja
            , R.drawable.i1_mala, R.drawable.i2_elefante, R.drawable.i3_barril, R.drawable.i4_tigre
            , R.drawable.j1_gravata, R.drawable.j2_maca, R.drawable.j3_vela, R.drawable.j4_zebra
            , R.drawable.k1_guarda_chuva, R.drawable.k2_galinha, R.drawable.k3_copo, R.drawable.k4_tomate
            , R.drawable.l1_piano, R.drawable.l2_pato, R.drawable.l3_caminhao, R.drawable.l4_cachorro
            , R.drawable.m1_sino, R.drawable.m2_banana, R.drawable.m3_galo, R.drawable.m4_trem
            , R.drawable.n1_cesta, R.drawable.n2_cavalo, R.drawable.n3_aviao, R.drawable.n4_vaca
            , R.drawable.o1_onibus, R.drawable.o2_rato, R.drawable.o3_chave, R.drawable.o4_peixe};

    private String[] strings = {"a1_chave_de_fenda", "a2_esquilo", "a3_regador", "a4_rinocerounte"
            , "b1_chaved_e_fenda", "b2_pinguim", "b3_escova_de_dentes", "b4_aguia"
            , "c1_serrote", "c2_avestruz", "c3_alicate", "c4_canguru"
            , "d1_lixeira", "d2_urso", "d3_pente", "d4_pavao"
            , "e1_pincel", "e2_cisne", "e3_sofa", "e4_jacare"
            , "f1_escova", "f2_pera", "f3_tesoura", "f4_milho"
            , "g1_machado", "g2_sapo", "g3_motocicleta", "g4_abacaxi"
            , "h1_carta", "h2_tartaruga", "h3_helicoptero", "h4_coruja"
            , "i1_mala", "i2_elefante", "i3_barril", "i4_tigre"
            , "j1_gravata", "j2_maca", "j3_vela", "j4_zebra"
            , "k1_guarda_chuva", "k2_galinha", "k3_copo", "k4_tomate"
            , "l1_piano", "l2_pato", "l3_caminhao", "l4_cachorro"
            , "m1_sino", "m2_banana", "m3_galo", "m4_trem"
            , "n1_cesta", "n2_cavalo", "n3_aviao", "n4_vaca"
            , "o1_onibus", "o2_rato", "o3_chave", "o4_peixe"};

    private com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Nomeacao.OnListFragmentInteractionListener mListener;

    public void setListener(com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Nomeacao.OnListFragmentInteractionListener onListener) {
        mListener = onListener;
    }

    /**
     * Contrutor.
     *
     * @param activity recebe o contexto
     */
    public NomeacaoAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public NomeacaoAdapter.NomeacaoItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nomeacao_item, parent, false);
        return new NomeacaoAdapter.NomeacaoItemView(view);
    }

    @Override
    public void onBindViewHolder(NomeacaoItemView holder, int position) {
        holder.mImageView.setImageResource(items[position]);
        holder.mImageView.setTag(items[position]);
        holder.mImageView.setBackgroundColor(Color.LTGRAY);

        holder.mTextView.setTag(strings[position]);

        Intent intentFromList = activity.getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);

            if(participante.getNomeacaoObject() != null) {
                Map<String, String> verificadores = participante.getNomeacaoObject().getVerificadores();
                if (verificadores != null) {
                    holder.mTextView.setText(verificadores.get(strings[position]));
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    /**
     * Classe que irá criar o visual da recyclerview
     */
    class NomeacaoItemView extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageButton mImageView;
        public final TextView mTextView;

        NomeacaoItemView(View view) {
            super(view);
            mView = view;
            mImageView = (ImageButton) view.findViewById(R.id.grid_item_nomeacao_img);
            mTextView = (TextView) view.findViewById(R.id.grid_item_nomeacao_tv);
            mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImageView.setPadding(35, 35, 35, 35);

            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onListFragmentInteraction(mImageView, mTextView);
                }
            });
        }
    }



}

