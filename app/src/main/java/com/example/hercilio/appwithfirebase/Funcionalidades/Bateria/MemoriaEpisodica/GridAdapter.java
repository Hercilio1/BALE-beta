package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.MemoriaEpisodica;

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
 * Created by Hercilio on 26/02/2018.
 */

public class GridAdapter extends BaseAdapter {

    private Context mContext;
    private com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.MemoriaEpisodica.OnListFragmentInteractionListener mListener;

    public GridAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return drawableImages.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }


    public void setListener(com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.MemoriaEpisodica.OnListFragmentInteractionListener onListener) {
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
        imageButton.setImageResource(drawableImages[position]);
        imageButton.setTag(imagesString[position]);
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

    Integer[] drawableImages = {R.drawable.a_morango, R.drawable.a_igreja, R.drawable.a_garfo, R.drawable.a_violao
            , R.drawable.b_domino, R.drawable.b_lapis, R.drawable.b_formiga, R.drawable.b_cama
            , R.drawable.c_martelo, R.drawable.c_tubarao, R.drawable.c_orelha, R.drawable.c_vassoura
            , R.drawable.d_cenoura, R.drawable.d_calca, R.drawable.d_bicicleta, R.drawable.d_macaco};

    String[] imagesString = {"a_morango", "a_igreja", "a_garfo", "a_violao"
            , "b_domino","b_lapis", "b_formiga", "b_cama"
            , "c_martelo", "c_tubarao", "c_orelha", "c_vassoura"
            , "d_cenoura", "d_calca", "d_bicicleta", "d_macaco"};


}

