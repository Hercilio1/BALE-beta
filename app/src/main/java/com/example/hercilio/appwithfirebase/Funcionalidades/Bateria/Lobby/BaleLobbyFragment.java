package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.HabitosLeituraEscritaActivity;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Hercilio on 24/01/2018.
 */

public class BaleLobbyFragment extends Fragment {

    private Participante mParticipante;

    public static final String EXTRA_PARTICIPANTE = "participante";

    public static BaleLobbyFragment newInstance(Participante participante) {

        final Bundle args = new Bundle();

        args.putSerializable(EXTRA_PARTICIPANTE, participante);

        final BaleLobbyFragment fragment = new BaleLobbyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bale_lobby, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mParticipante = (Participante) getArguments().getSerializable(EXTRA_PARTICIPANTE);

        RelativeLayout rlyHLE = (RelativeLayout) getActivity().findViewById(R.id.rly_habitos_leitura_escrita);

        rlyHLE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HabitosLeituraEscritaActivity.class);
                intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, mParticipante);
                startActivity(intent);
            }
        });
    }
}
