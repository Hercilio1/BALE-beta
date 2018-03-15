package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.CircularPropagation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.AssociacaoSemantica.AssociacaoSemanticaActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoFrases.CompreensaoDeFrases1Activity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoVerbal.CompreensaoVerbalLobbyActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.ConhecimentoSemantico.ConhecimentoSemanticoActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.DigitSpan.DigitSpanActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.FluenciaVerbal.FluenciaVerbalActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.HabitosDeLeituraEscritra.HabitosLeituraEscritaActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.InformacaoDiscursolivre.InformacaoDiscursolivreLobbyActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.MemoriaEpisodica.MemoriaEpisodicaLobbyActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Narrativa.NarrativaActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Nomeacao.NomeacaoActivity;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;

import at.grabner.circleprogress.CircleProgressView;

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
        RelativeLayout rlyInfDisc = (RelativeLayout) getActivity().findViewById(R.id.rly_informacao_discursolivre);
        RelativeLayout rlyNarrativa = (RelativeLayout)  getActivity().findViewById(R.id.rly_narrativa);
        RelativeLayout rlyFluenciaVerbal = (RelativeLayout) getActivity().findViewById(R.id.rly_fluencia_verbal);
        RelativeLayout rlyNomeacao = (RelativeLayout) getActivity().findViewById(R.id.rly_nomeacao);
        RelativeLayout rlyDigitSpan = (RelativeLayout) getActivity().findViewById(R.id.rly_digit_span);
        RelativeLayout rlyAssocSemant = (RelativeLayout) getActivity().findViewById(R.id.rly_associacao_semantica);
        RelativeLayout rlyConhecimentoSemantico = (RelativeLayout) getActivity().findViewById(R.id.rly_conhecimento_semantico);

        CircleProgressView cpvHLE = (CircleProgressView) getActivity().findViewById(R.id.circleView_hle);
        cpvHLE.setValue(mParticipante.getHleObject().getPorcentagem());

        CircleProgressView cpvCompFrases = (CircleProgressView) getActivity().findViewById(R.id.circleView_comp_frases);
        cpvCompFrases.setValue(mParticipante.getCompFrasesObject().getPorcentagem());

        CircleProgressView cpvMemEp = (CircleProgressView) getActivity().findViewById(R.id.circleView_mem_ep);
        cpvMemEp.setValue(mParticipante.getMemEpObject().getPorcentagem());

        CircleProgressView cpvCompVerbal = (CircleProgressView) getActivity().findViewById(R.id.circleView_comp_verbal);
        cpvCompVerbal.setValue(mParticipante.getCompVerbalObject().getPorcentagem());

        CircleProgressView cpvInfDisc = (CircleProgressView) getActivity().findViewById(R.id.circleView_informacao_discursolivre);
        cpvInfDisc.setValue(mParticipante.getInformacaoDiscLivreObject().getPorcentagem());

        CircleProgressView cpvNarrativa = (CircleProgressView) getActivity().findViewById(R.id.circleView_narrativa);
        cpvNarrativa.setValue(mParticipante.getNarrativaObject().getPorcentagem());

        CircleProgressView cpvFluenciaVerbal = (CircleProgressView) getActivity().findViewById(R.id.circleView_fluencia_verbal);
        cpvFluenciaVerbal.setValue(mParticipante.getFluenciaVerbalObject().getPorcentagem());

        CircleProgressView cpvNomeacao = (CircleProgressView) getActivity().findViewById(R.id.circleView_nomeacao);
        cpvNomeacao.setValue(mParticipante.getNomeacaoObject().getPorcentagem());

        CircleProgressView cpvDigitSpan = (CircleProgressView) getActivity().findViewById(R.id.circleView_digit_span);
        cpvDigitSpan.setValue(mParticipante.getDigitSpanObject().getPorcentagem());

        CircleProgressView cpvAssocSemant = (CircleProgressView) getActivity().findViewById(R.id.circleView_associacao_semantica);
        cpvAssocSemant.setValue(mParticipante.getAssociacaoSemanticaObject().getPorcentagem());

        CircleProgressView cpvConhecimentoSemantico = (CircleProgressView) getActivity().findViewById(R.id.circleView_conhecimento_semantico);
        cpvConhecimentoSemantico.setValue(mParticipante.getConhecimentoSemanticoObject().getPorcentagem());


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
                Intent intent = new Intent(getActivity(), CompreensaoDeFrases1Activity.class);
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

        rlyInfDisc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InformacaoDiscursolivreLobbyActivity.class);
                intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, mParticipante);
                startActivity(intent);
            }
        });

        rlyNarrativa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NarrativaActivity.class);
                intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, mParticipante);
                startActivity(intent);
            }
        });

        rlyFluenciaVerbal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FluenciaVerbalActivity.class);
                intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, mParticipante);
                startActivity(intent);
            }
        });

        rlyNomeacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NomeacaoActivity.class);
                intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, mParticipante);
                startActivity(intent);
            }
        });

        rlyDigitSpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DigitSpanActivity.class);
                intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, mParticipante);
                startActivity(intent);
            }
        });

        rlyAssocSemant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AssociacaoSemanticaActivity.class);
                intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, mParticipante);
                startActivity(intent);
            }
        });

        rlyConhecimentoSemantico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ConhecimentoSemanticoActivity.class);
                intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, mParticipante);
                startActivity(intent);
            }
        });
    }
}
