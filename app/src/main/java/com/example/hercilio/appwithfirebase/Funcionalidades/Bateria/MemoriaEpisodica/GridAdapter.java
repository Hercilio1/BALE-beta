package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.MemoriaEpisodica;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;

import java.util.ArrayList;
import java.util.List;

import at.grabner.circleprogress.CircleProgressView;

/**
 * Created by Hercilio on 26/02/2018.
 */

public class GridAdapter extends BaseAdapter {
    private Context mContext;

    public GridAdapter(Context c) {
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

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageButton imageButton;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageButton = new ImageButton(mContext);
            imageButton.setLayoutParams(new GridView.LayoutParams(250, 250));
            imageButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageButton.setPadding(35, 35, 35, 35);
        } else {
            imageButton = (ImageButton) convertView;
        }

        imageButton.setImageResource(stringImages[position]);
        return imageButton;
    }

    Integer[] stringImages = {R.drawable.a_morango, R.drawable.a_igreja, R.drawable.a_garfo, R.drawable.a_violao
            , R.drawable.b_domino, R.drawable.b_lapis, R.drawable.b_formiga, R.drawable.b_cama
            , R.drawable.c_martelo, R.drawable.c_tubarao, R.drawable.c_orelha, R.drawable.c_vassoura
            , R.drawable.d_cenoura, R.drawable.d_calca, R.drawable.d_bicicleta, R.drawable.d_macaco};
}

