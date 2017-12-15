package com.example.hercilio.appwithfirebase.Pesquisas;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hercilio.appwithfirebase.R;

import com.example.hercilio.appwithfirebase.Pesquisas.PesquisasFragment.OnListFragmentInteractionListener;

import java.util.ArrayList;

import at.grabner.circleprogress.CircleProgressView;

/**
 * Created by Hercilio on 15/12/2017.
 */

public class PesquisasAdapter extends RecyclerView.Adapter<PesquisasAdapter.PesquisaItemView> {

    private Activity activity;
    private ArrayList<String> items = new ArrayList<>();
    private OnListFragmentInteractionListener mListener;

    /**
     * Contrutor.
     *
     * @param activity recebe o contexto
     * @param items recebe a lista de pesquisas
     * @param listener
     */
    public PesquisasAdapter(Activity activity, ArrayList<String> items, OnListFragmentInteractionListener listener) {
        this.activity = activity;
        this.items = items;
        this.mListener = listener;
    }

    /**
     * Infla o layout com a célula para o recyclerview
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public PesquisasAdapter.PesquisaItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pesquisa_item, parent, false);
        return new PesquisasAdapter.PesquisaItemView(view);
    }

    /**
     *
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final PesquisaItemView holder, int position) {
        holder.mItem = items.get(position);
        holder.mIdView.setText(items.get(position));
        //holder.mContentView.setText(mValues.get(position).nomeExaminador);
        holder.mContentView.setText(items.get(position));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class PesquisaItemView extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final CircleProgressView mCircleProgressView;
        public String mItem;

        PesquisaItemView(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            mCircleProgressView = (CircleProgressView) view.findViewById(R.id.circleView);

//            btnFavoritar.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Menor menorItem = items.get(getAdapterPosition());
//                    mOnMenorCancelarAdocaoListener.OnMenorItemSelected(menorItem, getAdapterPosition());
//                }
//            });
//
//            rlCell.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Menor menorItem = items.get(getAdapterPosition());
//                    mOnMenorSelectedListener.OnMenorItemSelected(menorItem, getAdapterPosition());
//                }
//            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }


}
