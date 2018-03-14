package com.example.hercilio.appwithfirebase.Funcionalidades.Pesquisas;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;


import java.util.ArrayList;
import java.util.List;

import at.grabner.circleprogress.CircleProgressView;

/**
 * Created by Hercilio on 15/12/2017.
 */

public class PesquisasAdapter extends RecyclerView.Adapter<PesquisasAdapter.PesquisaItemView> {

    private Activity activity;
    private List<Participante> items = new ArrayList<>();
    private OnListFragmentInteractionListener mListener;

    public void setListener(OnListFragmentInteractionListener onListener) {
        mListener = onListener;
    }

    /**
     * Contrutor.
     *
     * @param activity recebe o contexto
     * @param items recebe a lista de pesquisas
     */
    public PesquisasAdapter(Activity activity, ArrayList<Participante> items) {
        this.activity = activity;
        this.items = items;
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
        holder.mIdView.setText(items.get(position).getNomeCompleto());
        holder.mContentView.setText(items.get(position).getCpf());
        holder.mCircleProgressView.setText(items.get(position).getPorcentagem()+"");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Classe que irá criar o visual da recyclerview
     */
    class PesquisaItemView extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final CircleProgressView mCircleProgressView;
        public Participante mItem;

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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Participante menorItem = items.get(getAdapterPosition());
                    mListener.onListFragmentInteraction(menorItem);
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }


}
