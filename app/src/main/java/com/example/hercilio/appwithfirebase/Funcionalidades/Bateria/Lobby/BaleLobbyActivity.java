package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hercilio.appwithfirebase.AdminActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.HabitosDeLeituraEscritra.HabitosLeituraEscritaActivity;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;

/**
 * Created by Hercilio on 26/12/2017.
 */

public class BaleLobbyActivity extends AppCompatActivity {

    public static final String EXTRA_PARTICIPANTE = "participante";

    private MenuItem baleLobbyHomeBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bale_lobby);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(EXTRA_PARTICIPANTE);

            if (savedInstanceState == null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container_bale_lobby, BaleLobbyFragment.newInstance(participante), "BaleLobby")
                        .commit();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            Intent intentFromList = getIntent();
            if (intentFromList != null) {
                final Participante participante = (Participante) intentFromList.getSerializableExtra(EXTRA_PARTICIPANTE);
                Intent intent = new Intent(this, AdminActivity.class);
                startActivity(intent);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
