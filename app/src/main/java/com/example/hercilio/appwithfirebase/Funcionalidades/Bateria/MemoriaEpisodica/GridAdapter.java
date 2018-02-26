package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.MemoriaEpisodica;

import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridItemView> {

    Integer[] stringImages = {R.drawable.a_morango, R.drawable.a_igreja, R.drawable.a_garfo, R.drawable.a_violao
            , R.drawable.b_domino, R.drawable.b_lapis, R.drawable.b_formiga, R.drawable.b_cama
            , R.drawable.c_martelo, R.drawable.c_tubarao, R.drawable.c_orelha, R.drawable.c_vassoura
            , R.drawable.d_cenoura, R.drawable.d_calca, R.drawable.d_bicicleta, R.drawable.d_macaco};

    private List<Integer> images;

    public GridAdapter() {
        int count = stringImages.length;
        images = new ArrayList<Integer>(count);
        for (int i = 0; i < count; ++i) {
            images.add(stringImages[i]);
        }
    }

    @Override
    public GridAdapter.GridItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new GridAdapter.GridItemView(view);
    }


    @Override
    public void onBindViewHolder(final GridAdapter.GridItemView holder, final int position) {
        final int label = images.get(position);
        holder.mImageView.setImageResource(R.drawable.a_garfo);
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        holder.mImageView.getContext(), label, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    class GridItemView extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImageView;
        public final CircleProgressView mCircleProgressView;
        public ImageView mItem;

        GridItemView(View view) {
            super(view);
            mView = view;
            mImageView = (ImageView) view.findViewById(R.id.grid_item_view_im);
            mCircleProgressView = (CircleProgressView) view.findViewById(R.id.circleView);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                }
//            });
        }
    }
}
