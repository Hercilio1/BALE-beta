package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoFrases;

/**
 * Created by Hercilio on 31/01/2018.
 */

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.hercilio.appwithfirebase.R;

/**
 * Created by 14202151 on 27/04/2017.
 */

public class FraseExpandidaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_verbal_terceira_avaliacao);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            this.onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
