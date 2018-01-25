package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.HabitosLeituraEscritaActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Login.LoginFragment;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Hercilio on 26/12/2017.
 */

public class BaleLobbyActivity extends AppCompatActivity {

    public static final String EXTRA_PARTICIPANTE = "participante";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bale_lobby);
//        getSupportActionBar().hide();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragment_container_bale_lobby, new BaleLobbyFragment())
//                .commit();

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
}
