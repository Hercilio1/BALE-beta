package com.example.hercilio.appwithfirebase.Funcionalidades.Usuarios;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hercilio.appwithfirebase.Objetos.UserDados;
import com.example.hercilio.appwithfirebase.R;

import java.util.ArrayList;
import java.util.List;

import at.grabner.circleprogress.CircleProgressView;

/**
 * Created by Hercilio on 19/02/2018.
 */

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.UserDadosItemView> {

    private Activity activity;
    private List<UsuariosFragment.IdWithUserDados> items = new ArrayList<>();
    private com.example.hercilio.appwithfirebase.Funcionalidades.Usuarios.OnListFragmentInteractionListener mListener;

    public void setListener(com.example.hercilio.appwithfirebase.Funcionalidades.Usuarios.OnListFragmentInteractionListener onListener) {
        mListener = onListener;
    }

    /**
     * Contrutor.
     *
     * @param activity recebe o contexto
     * @param items recebe a lista de pesquisas
     */
    public UsuariosAdapter(Activity activity, ArrayList<UsuariosFragment.IdWithUserDados> items) {
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
    public UsuariosAdapter.UserDadosItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.usuario_item, parent, false);
        return new UsuariosAdapter.UserDadosItemView(view);
    }

    /**
     *
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final UsuariosAdapter.UserDadosItemView holder, int position) {
        holder.mItem = items.get(position).getUserDados();
        holder.mIdView.setText(items.get(position).getUserDados().getNome());
        //holder.mContentView.setText(mValues.get(position).nomeExaminador);
        holder.mContentView.setText(items.get(position).getUserDados().getNroDeParticipantesEntrevistados());

//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mItem);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Classe que irá criar o visual da recyclerview
     */
    class UserDadosItemView extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public UserDados mItem;

        UserDadosItemView(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.usuario_name);
            mContentView = (TextView) view.findViewById(R.id.usuario_content);

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
                    mListener.onListFragmentInteraction(items.get(getAdapterPosition()));
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }


}
