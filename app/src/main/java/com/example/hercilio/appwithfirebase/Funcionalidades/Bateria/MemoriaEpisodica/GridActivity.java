package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.MemoriaEpisodica;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hercilio.appwithfirebase.R;

/**
 * Created by Hercilio on 26/02/2018.
 */

public class GridActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoria_eposodica_segunda_fase);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_grid_mem_ep);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        recyclerView.setAdapter(new GridAdapter());
        recyclerView.setHasFixedSize(true);
//        recyclerView.addItemDecoration(new GridDecoration(this));
    }
}
