package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Nomeacao;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.hercilio.appwithfirebase.R;

/**
 * Created by Hercilio on 05/03/2018.
 */

public class NomeacaoAdapter extends BaseAdapter {

    private Context mContext;
    private com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Nomeacao.OnListFragmentInteractionListener mListener;

    public NomeacaoAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return stringImages.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }


    public void setListener(com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Nomeacao.OnListFragmentInteractionListener onListener) {
        mListener = onListener;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        final ImageButton imageButton;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageButton = new ImageButton(mContext);
            imageButton.setLayoutParams(new GridView.LayoutParams(250, 250));
            imageButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageButton.setPadding(35, 35, 35, 35);
        } else {
            imageButton = (ImageButton) convertView;
        }

        /*
         * ColorPrimaty 			=> r:129 g:175 b:145
         * ColorAssent  			=> r:230 g:172 b:39
         *
         * clickedPrinc_compVerval => r:108 g:76 b:0
         * clickedSecun_compVerval => r:42 g:84 b:33
         */
        imageButton.setImageResource(stringImages[position]);
        imageButton.setTag(""+stringImages[position]);
        imageButton.setBackgroundColor(Color.LTGRAY);
        mListener.onListFragmentInteraction(imageButton, false);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onListFragmentInteraction(imageButton, true);
            }
        });
        return imageButton;
    }

    Integer[] stringImages = {R.drawable.a1_chave_de_fenda, R.drawable.a2_esquilo, R.drawable.a3_regador, R.drawable.a4_rinocerounte
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

}

