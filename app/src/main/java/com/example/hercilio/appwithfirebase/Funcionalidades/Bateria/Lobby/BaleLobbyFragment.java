package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoFrases.CompreensaoDeFrasesActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoVerbal.CompreensaoVerbalLobbyActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.HabitosDeLeituraEscritra.HabitosLeituraEscritaActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.InformacaoDiscursolivre.InformacaoDiscursolivreLobbyActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.MemoriaEpisodica.MemoriaEpisodicaLobbyActivity;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;

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
        RelativeLayout rlyCompFrases = (RelativeLayout) getActivity().findViewById(R.id.rly_compreensao_frases);
        RelativeLayout rlyMemEp = (RelativeLayout) getActivity().findViewById(R.id.rly_memoria_episodica);
        RelativeLayout rlyCompVerbal = (RelativeLayout) getActivity().findViewById(R.id.rly_compreensao_verbal);
        RelativeLayout rlyInfDiscNarr = (RelativeLayout) getActivity().findViewById(R.id.rly_informacao_discursolivre);

        rlyHLE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HabitosLeituraEscritaActivity.class);
                intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, mParticipante);
                startActivity(intent);
            }
        });

        rlyCompFrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CompreensaoDeFrasesActivity.class);
                intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, mParticipante);
                startActivity(intent);
            }
        });

        rlyMemEp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MemoriaEpisodicaLobbyActivity.class);
                intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, mParticipante);
                startActivity(intent);
            }
        });

        rlyCompVerbal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CompreensaoVerbalLobbyActivity.class);
                intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, mParticipante);
                startActivity(intent);
            }
        });

        rlyInfDiscNarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InformacaoDiscursolivreLobbyActivity.class);
                intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, mParticipante);
                startActivity(intent);
            }
        });
    }
}
