package com.example.hercilio.appwithfirebase.Funcionalidades.Pesquisas;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;


import java.util.ArrayList;
import java.util.List;

import at.grabner.circleprogress.CircleProgressView;

/**
 * Created by Hercilio on 15/12/2017.
 */

public class PesquisasAdapter extends RecyclerView.Adapter<PesquisasAdapter.PesquisaItemView> implements Filterable {

    private Activity activity;
    private List<Participante> items = new ArrayList<>();
    private List<Participante> mFilteredList;
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
        this.mFilteredList = items;
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
        holder.mItem = mFilteredList.get(position);
        holder.mIdView.setText(mFilteredList.get(position).getNomeCompleto());
        String cpf = mFilteredList.get(position).getCpf();
        if(cpf.length() == 11) {
            StringBuffer sb = new StringBuffer(cpf);
            sb.insert(3, '.');
            sb.insert(7, '.');
            sb.insert(11, '-');
            cpf = sb.toString();
        }
        holder.mContentView.setText(cpf);
        holder.mCircleProgressView.setValue(mFilteredList.get(position).getPorcentagem());
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilteredList = items;
                } else {
                    ArrayList<Participante> filteredList = new ArrayList<>();
                    for (Participante participante : items) {
                        if (participante.getNomeCompleto().toString().contains(charString) || participante.getCpf().toString().contains(charString)) {
                            filteredList.add(participante);
                        }
                    }
                    mFilteredList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<Participante>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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
