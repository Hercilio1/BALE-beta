package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.AssociacaoSemantica;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Nomeacao.NomeacaoAdapter;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;

import java.util.Map;

/**
 * Created by Hercilio on 10/03/2018.
 */

public class AssociacaoSemanticaAdapter extends RecyclerView.Adapter<AssociacaoSemanticaAdapter.AssociacaoSemanticaItemView> {

    private Activity activity;
    private Integer[] items = {R.drawable.assoc_seman_modelo, R.drawable.assoc_seman_fig_a, R.drawable.assoc_seman_fig_b
                , R.drawable.assoc_seman_fig_c, R.drawable.assoc_seman_fig_d, R.drawable.assoc_seman_fig_e
                , R.drawable.assoc_seman_fig_f, R.drawable.assoc_seman_fig_g, R.drawable.assoc_seman_fig_h
                , R.drawable.assoc_seman_fig_i, R.drawable.assoc_seman_fig_j, R.drawable.assoc_seman_fig_k
                , R.drawable.assoc_seman_fig_l};

    private String[] dicas = {"modelo: maiô - bermuda", "maçã – uva", "óculos – livro","rato – gato"
                , "pente – espelho", "árvore – maçã", "xícara – chaleira"
                , "morcego – coruja", "mala – camisa", "borboleta – lagarta"
                , "cadeado – bicicleta", "elefante – gorila", "isqueiro – vela"};

    private com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.AssociacaoSemantica.OnListFragmentInteractionListener mListener;

    public void setListener(com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.AssociacaoSemantica.OnListFragmentInteractionListener onListener) {
        mListener = onListener;
    }

    /**
     * Contrutor.
     *
     * @param activity recebe o contexto
     */
    public AssociacaoSemanticaAdapter(Activity activity) {
        this.activity = activity;
    }


    @Override
    public AssociacaoSemanticaItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.associacao_semantica_item, parent, false);
        return new AssociacaoSemanticaAdapter.AssociacaoSemanticaItemView(view);
    }


    @Override
    public void onBindViewHolder(AssociacaoSemanticaAdapter.AssociacaoSemanticaItemView holder, int position) {
        holder.mImageView.setImageResource(items[position]);
        holder.mImageView.setTag(items[position]);
        holder.mImageView.setBackgroundColor(Color.LTGRAY);

        holder.mTextView.setText(dicas[position]);
        holder.mTextView.setTag(dicas[position]);

        holder.mRadioGroup.setTag(dicas[position]);
        holder.mRadioGroup.setVisibility(View.VISIBLE);
        holder.mRadioGroup.clearCheck();

        if(dicas[position].equals("modelo: maiô - bermuda")) {
            holder.mRadioGroup.setVisibility(View.INVISIBLE);
        }
          else {
            Intent intentFromList = activity.getIntent();
            if (intentFromList != null) {
                final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);

                if (participante.getAssociacaoSemanticaObject() != null) {
                    Map<String, Integer> verificadores = participante.getAssociacaoSemanticaObject().getVerificadores();
                    if (verificadores.containsKey(dicas[position])) {
                        holder.mRadioGroup.check(holder.mRadioGroup.getChildAt(verificadores.get(dicas[position])).getId());
                        mListener.onRadioFragmentInteraction();
                    }
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
    class AssociacaoSemanticaItemView extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageButton mImageView;
        public final TextView mTextView;
        public final RadioGroup mRadioGroup;


        AssociacaoSemanticaItemView(final View view) {
            super(view);
            mView = view;
            mImageView = (ImageButton) view.findViewById(R.id.grid_item_assiciacao_semantica_img);
            mTextView = (TextView) view.findViewById(R.id.grid_item_assiciacao_semantica_tv);
            mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImageView.setPadding(35, 35, 35, 35);
            mRadioGroup = (RadioGroup) view.findViewById(R.id.grid_item_assiciacao_semantica_rg);

            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onListFragmentInteraction(mImageView);
                }
            });

        }
    }


}
