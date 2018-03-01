package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.MemoriaEpisodica;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import com.example.hercilio.appwithfirebase.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hercilio on 26/02/2018.
 */

public class GridActivity extends AppCompatActivity {
    //Dicionario que armazena os botoes de controle selecionados:
    Map<String, Boolean> verificadorBotoesDeControle = new HashMap<>();

    //Dicionario que armazena os botoes selecionados:
    private Map<String, Boolean> verificadores = new HashMap<>();

    //Auxiliano auto complete
    private ArrayList<String> auxAutoComIndicacao = new ArrayList<>();
    private ArrayList<String> auxAutoCompleteNomeacao = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoria_eposodica_segunda_fase);

        GridAdapter gridAdapter = new GridAdapter(this);

        GridView gridview = (GridView) findViewById(R.id.gridview_mem_ep);
        gridAdapter.setListener(selecionarItemView);
        gridview.setAdapter(gridAdapter);

        //Inserindo no discionário a chave para os botoes de controle:
        verificadorBotoesDeControle.put(""+R.id.btn_mem_ep_com_pista, false);
        verificadorBotoesDeControle.put(""+R.id.btn_mem_ep_sem_pista, false);

        //Inserindo no discionário a chave para os botoes:
        verificadores.put(""+R.drawable.a_morango, false);
        verificadores.put(""+R.drawable.a_igreja, false);
        verificadores.put(""+R.drawable.a_garfo, false);
        verificadores.put(""+R.drawable.a_violao, false);
        verificadores.put(""+R.drawable.b_domino, false);
        verificadores.put(""+R.drawable.b_lapis, false);
        verificadores.put(""+R.drawable.b_formiga, false);
        verificadores.put(""+R.drawable.b_cama, false);
        verificadores.put(""+R.drawable.c_martelo, false);
        verificadores.put(""+R.drawable.c_tubarao, false);
        verificadores.put(""+R.drawable.c_orelha, false);
        verificadores.put(""+R.drawable.c_vassoura, false);
        verificadores.put(""+R.drawable.d_cenoura, false);
        verificadores.put(""+R.drawable.d_calca, false);
        verificadores.put(""+R.drawable.d_bicicleta, false);
        verificadores.put(""+R.drawable.d_macaco, false);


    }

    final OnListFragmentInteractionListener selecionarItemView = new OnListFragmentInteractionListener() {
        @Override
        public void onListFragmentInteraction(ImageButton item) {
            verificaTipo(item);
        }
    };

    /*
     * ColorPrimaty 			=> r:129 g:175 b:145
     * ColorAssent  			=> r:230 g:172 b:39
     *
     * clickedPrinc_compVerval => r:108 g:76 b:0
     * clickedSecun_compVerval => r:42 g:84 b:33
     */

    public void verificaTipo(ImageButton imageButton) {
        if(verificadorBotoesDeControle.get(""+R.id.btn_mem_ep_sem_pista)) {
            if(verificadores.get(""+imageButton.getTag())) {
                imageButton.setBackgroundColor(Color.LTGRAY);
                verificadores.put(""+imageButton.getTag(), false);
            } else {
                imageButton.setBackgroundColor(Color.rgb(129, 175, 145));
                verificadores.put(""+imageButton.getTag(), true);
            }
        }
        else if(verificadorBotoesDeControle.get(""+R.id.btn_mem_ep_com_pista)) {
            if(verificadores.get(""+imageButton.getTag())) {
                imageButton.setBackgroundColor(Color.LTGRAY);
                verificadores.put(""+imageButton.getTag(), false);
            } else {
                imageButton.setBackgroundColor(Color.rgb(230, 172, 39));
                verificadores.put(""+imageButton.getTag(), true);
            }
        }
    }

    public void OnclickSemPista(View v) {
        Button mBtnComPista = (Button) findViewById(R.id.btn_mem_ep_com_pista);
        verificadorBotoesDeControle.put(""+R.id.btn_mem_ep_com_pista, false);
        verificadorBotoesDeControle.put("" + v.getId(), true);
        //colorPrimary
        mBtnComPista.getBackground().setColorFilter(getResources().getColor(R.color.clickedPrinc_compVerval), PorterDuff.Mode.SRC_ATOP);
        //clickedSecun_compVerval
        v.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
    }

    public void OnclickComPista(View v) {
        Button mBtnSemPista = (Button) findViewById(R.id.btn_mem_ep_sem_pista);
        verificadorBotoesDeControle.put(""+R.id.btn_mem_ep_sem_pista, false);
        verificadorBotoesDeControle.put("" + v.getId(), true);
        //ColorPrimary
        mBtnSemPista.getBackground().setColorFilter(getResources().getColor(R.color.clickedSecun_compVerval), PorterDuff.Mode.SRC_ATOP);
        //clickedPrinc_compVerval
        v.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
    }


}
