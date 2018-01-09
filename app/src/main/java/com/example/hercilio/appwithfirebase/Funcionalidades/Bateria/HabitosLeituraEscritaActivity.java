
package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria;

/**
 * Created by Hercilio on 26/12/2017.
 */

        import android.support.design.widget.FloatingActionButton;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;

        import android.app.ProgressDialog;
        import android.content.ContentValues;
        import android.content.Intent;
        import android.database.Cursor;
        import android.os.AsyncTask;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.LinearLayout;
        import android.widget.ProgressBar;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.hercilio.appwithfirebase.Funcionalidades.Login.LoginActivity;
        import com.example.hercilio.appwithfirebase.Funcionalidades.Pesquisas.PesquisasAdapter;
        import com.example.hercilio.appwithfirebase.Objetos.HabitosLeituraEscritaObject;
        import com.example.hercilio.appwithfirebase.Objetos.Participante;
        import com.example.hercilio.appwithfirebase.Objetos.Pesquisa;
        import com.example.hercilio.appwithfirebase.R;
        import com.google.android.gms.common.api.GoogleApiClient;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.ChildEventListener;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

        import java.util.ArrayList;

public class HabitosLeituraEscritaActivity extends AppCompatActivity {

    /*ArrayList<String> frequencias;
    ArrayList<String> tipoTempo;
    ArrayList<String> tipoEdicao;
    ArrayList<String> tipoLeitura;
    ArrayList<String> tipoEscrita;*/

    private int mLeituraRevista;
    private int mLeituraJornais;
    private int mLeituraLivros;
    private int mLeituraRedes;
    private int mEscritoMensagens;
    private int mEscritoLiterarios;
    private int mEscritoNaoLiterarios;
    private int mEscritoOutros;

    /* Barra de progresso */
    private View mProgressView;

    LinearLayout linearLayout;

    /* Views */
    /* RadioGroup */
    /* Leitura */
    private RadioGroup mRadioRevistasAtualFreq;
    private RadioGroup mRadioRevistasPassadoFreq;
    private RadioGroup mRadioJornaisAtualFreq;
    private RadioGroup mRadioJornaisPassadoFreq;
    private RadioGroup mRadioLivrosAtualFreq;
    private RadioGroup mRadioLivrosPassadoFreq;
    private RadioGroup mRadioRedesAtualFreq;
    private RadioGroup mRadioRedesPassadoFreq;
    /* Escrita */
    private RadioGroup mRadioMensagensAtualFreq;
    private RadioGroup mRadioMensagensPassadoFreq;
    private RadioGroup mRadioLiterariosAtualFreq;
    private RadioGroup mRadioLiterariosPassadoFreq;
    private RadioGroup mRadioNaoLiterariosAtualFreq;
    private RadioGroup mRadioNaoLiterariosPassadoFreq;
    private RadioGroup mRadioOutrosAtualFreq;
    private RadioGroup mRadioOutrosPassadoFreq;
    /* Atual */
    private RadioGroup mRadioRevistasAtual;
    private RadioGroup mRadioJornaisAtual;
    private RadioGroup mRadioLivrosAtual;
    private RadioGroup mRadioRedesAtual;
    private RadioGroup mRadioMensagensAtual;
    private RadioGroup mRadioLiterariosAtual;
    private RadioGroup mRadioNaoLiterariosAtual;
    private RadioGroup mRadioOutrosAtual;
    /* Passado */
    private RadioGroup mRadioRevistasPassado;
    private RadioGroup mRadioJornaisPassado;
    private RadioGroup mRadioLivrosPassado;
    private RadioGroup mRadioRedesPassado;
    private RadioGroup mRadioMensagensPassado;
    private RadioGroup mRadioLiterariosPassado;
    private RadioGroup mRadioNaoLiterariosPassado;
    private RadioGroup mRadioOutrosPassado;
    /* Uma vez que cada opção de frequência equivale a um valor, as seguintes variáveis foram criadas: */
    /* RadioButtons */
    /* Leitura */
    private int checkedLeitRevistasAtualFreq;
    private int checkedLeitJornaisAtualFreq;
    private int checkedLeitLivrosAtualFreq;
    private int checkedLeitRedesAtualFreq;
    private int checkedEscrMensagensAtualFreq;
    private int checkedEscrLiterariosAtualFreq;
    private int checkedEscrNaoLiterariosAtualFreq;
    private int checkedEscrOutrosAtualFreq;
    /* Escrita */
    private int checkedLeitRevistasPassadoFreq;
    private int checkedLeitJornaisPassadoFreq;
    private int checkedLeitLivrosPassadoFreq;
    private int checkedLeitRedesPassadoFreq;
    private int checkedEscrMensagensPassadoFreq;
    private int checkedEscrLiterariosPassadoFreq;
    private int checkedEscrNaoLiterariosPassadoFreq;
    private int checkedEscrOutrosPassadoFreq;
    /* Atual */
    private int checkedLeitRevistasAtual;
    private int checkedLeitJornaisAtual;
    private int checkedLeitLivrosAtual;
    private int checkedLeitRedesAtual;
    private int checkedEscrMensagensAtual;
    private int checkedEscrLiterariosAtual;
    private int checkedEscrNaoLiterariosAtual;
    private int checkedEscrOutrosAtual;
    /* Passado */
    private int checkedLeitRevistasPassado;
    private int checkedLeitJornaisPassado;
    private int checkedLeitLivrosPassado;
    private int checkedLeitRedesPassado;
    private int checkedEscrMensagensPassado;
    private int checkedEscrLiterariosPassado;
    private int checkedEscrNaoLiterariosPassado;
    private int checkedEscrOutrosPassado;

    /* Totais */
    private TextView totalLeituraAtual;
    private TextView totalLeituraPassado;
    private TextView totalLeitura;
    private TextView totalEscritaAtual;
    private TextView totalEscritaPassado;
    private TextView totalEscrita;

    /*Botão de continuar*/
    private Button mBtnContinuar;

    /* Vetores com posição de acordo com os tipos de leitura ou escrita.
    * Leitura:
    * [0] Revistas
    * [1] Jornais
    * [2] Livros
    * [3] Redes
    * Escrita:
    * [0] Mensagens
    * [1] Literários
    * [2] Não-Literários
    * [3] Outros */
    private int[] vetorTotalLeituraAtual;
    private int[] vetorTotalLeituraPassado;
    private int[] vetorTotalLeitura;
    private int[] vetorTotalEscritaAtual;
    private int[] vetorTotalEscritaPassado;
    private int[] vetorTotalEscrita;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private ProgressDialog progressDialog;

    private ArrayList<String> respostas;
    private static String pk_pesquisa = null;
    private static String pk_usuario = null;
    private static Pesquisa pesquisa = null;

    /**
     * Necessário para capturar o Participante da vez
     * @param savedInstanceState
     */
    private final String EXTRA_PARTICIPANTE = "participante";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitos_leitura_escrita);

        mProgressView = findViewById(R.id.habitos_progress);


        /* Frequências */
        mRadioRevistasAtualFreq = (RadioGroup) findViewById(R.id.radio_revistas_atual);
        mRadioRevistasPassadoFreq = (RadioGroup) findViewById(R.id.radio_revistas_passado);
        mRadioJornaisAtualFreq = (RadioGroup) findViewById(R.id.radio_jornais_atual);
        mRadioJornaisPassadoFreq = (RadioGroup) findViewById(R.id.radio_jornais_passado);
        mRadioLivrosAtualFreq = (RadioGroup) findViewById(R.id.radio_livros_atual);
        mRadioLivrosPassadoFreq = (RadioGroup) findViewById(R.id.radio_livros_passado);
        mRadioRedesAtualFreq = (RadioGroup) findViewById(R.id.radio_redes_atual);
        mRadioRedesPassadoFreq = (RadioGroup) findViewById(R.id.radio_redes_passado);
        mRadioMensagensAtualFreq = (RadioGroup) findViewById(R.id.radio_mensagens_atual);
        mRadioMensagensPassadoFreq = (RadioGroup) findViewById(R.id.radio_mensagens_passado);
        mRadioLiterariosAtualFreq = (RadioGroup) findViewById(R.id.radio_literarios_atual);
        mRadioLiterariosPassadoFreq = (RadioGroup) findViewById(R.id.radio_literarios_passado);
        mRadioNaoLiterariosAtualFreq = (RadioGroup) findViewById(R.id.radio_naoliterarios_atual);
        mRadioNaoLiterariosPassadoFreq = (RadioGroup) findViewById(R.id.radio_naoliterarios_passado);
        mRadioOutrosAtualFreq = (RadioGroup) findViewById(R.id.radio_outros_atual);
        mRadioOutrosPassadoFreq = (RadioGroup) findViewById(R.id.radio_outros_passado);
        /* Atual */
        mRadioRevistasAtual = (RadioGroup) findViewById(R.id.radio_atual_revistas);
        mRadioJornaisAtual = (RadioGroup) findViewById(R.id.radio_atual_jornais);
        mRadioLivrosAtual = (RadioGroup) findViewById(R.id.radio_atual_livros);
        mRadioRedesAtual = (RadioGroup) findViewById(R.id.radio_atual_redes);
        mRadioMensagensAtual = (RadioGroup) findViewById(R.id.radio_atual_mensagens);
        mRadioLiterariosAtual = (RadioGroup) findViewById(R.id.radio_atual_literarios);
        mRadioNaoLiterariosAtual = (RadioGroup) findViewById(R.id.radio_atual_naoliterarios);
        mRadioOutrosAtual = (RadioGroup) findViewById(R.id.radio_atual_outros);
        /* Passado */
        mRadioRevistasPassado = (RadioGroup) findViewById(R.id.radio_passado_revistas);
        mRadioJornaisPassado = (RadioGroup) findViewById(R.id.radio_passado_jornais);
        mRadioLivrosPassado = (RadioGroup) findViewById(R.id.radio_passado_livros);
        mRadioRedesPassado = (RadioGroup) findViewById(R.id.radio_passado_redes);
        mRadioMensagensPassado = (RadioGroup) findViewById(R.id.radio_passado_mensagens);
        mRadioLiterariosPassado = (RadioGroup) findViewById(R.id.radio_passado_literarios);
        mRadioNaoLiterariosPassado = (RadioGroup) findViewById(R.id.radio_passado_naoliterarios);
        mRadioOutrosPassado = (RadioGroup) findViewById(R.id.radio_passado_outros);
        /*Botão de continuar*/
        mBtnContinuar = (Button) findViewById(R.id.btn_continuar);

        /* Totais */
        totalLeituraAtual = (TextView) findViewById(R.id.habitos_leit_total_atual_participante);
        totalLeituraPassado = (TextView) findViewById(R.id.habitos_leit_total_passado_participante);
        totalLeitura = (TextView) findViewById(R.id.habitos_leit_total_participante);
        totalEscritaAtual = (TextView) findViewById(R.id.habitos_escr_total_atual_participante);
        totalEscritaPassado = (TextView) findViewById(R.id.habitos_escr_total_passado_participante);
        totalEscrita = (TextView) findViewById(R.id.habitos_escr_total_participante);
        /* Vetores */
        vetorTotalLeituraAtual = new int[4];
        vetorTotalLeituraPassado = new int[4];
        vetorTotalLeitura = new int[2]; // Esse vetor possui 2 posições: [0] total atual e [1] total passado
        vetorTotalEscritaAtual = new int[4];
        vetorTotalEscritaPassado = new int[4];
        vetorTotalEscrita = new int[2]; // Esse vetor possui 2 posições: [0] total atual e [1] total passado


        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(EXTRA_PARTICIPANTE);

            if(participante.getHleObject() != null) {
                autoComplete(participante.getHleObject());
            } else {
                mBtnContinuar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        registrar(participante.getHleObject());
                    }
                });
            }
        }

    }

    public void autoComplete(HabitosLeituraEscritaObject hleObject) {
        ArrayList<HabitosLeituraEscritaObject.Perguntas> perguntas = hleObject.getPerguntas();
        for(HabitosLeituraEscritaObject.Perguntas x : perguntas) {
            switch (x.getDescricao()) {
                case "mRadioRevistasAtualFreq":
                    mRadioRevistasAtualFreq.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioRevistasPassadoFreq":
                    mRadioRevistasPassadoFreq.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioJornaisAtualFreq":
                    mRadioJornaisAtualFreq.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioJornaisPassadoFreq":
                    mRadioJornaisPassadoFreq.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioLivrosAtualFreq":
                    mRadioLivrosAtualFreq.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioLivrosPassadoFreq":
                    mRadioLivrosPassadoFreq.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioRedesAtualFreq":
                    mRadioRedesAtualFreq.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioRedesPassadoFreq":
                    mRadioRedesPassadoFreq.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioMensagensAtualFreq":
                    mRadioMensagensAtualFreq.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioMensagensPassadoFreq":
                    mRadioMensagensPassadoFreq.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioLiterariosAtualFreq":
                    mRadioLiterariosAtualFreq.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioLiterariosPassadoFreq":
                    mRadioLiterariosPassadoFreq.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioNaoLiterariosAtualFreq":
                    mRadioNaoLiterariosAtualFreq.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioNaoLiterariosPassadoFreq":
                    mRadioNaoLiterariosPassadoFreq.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioOutrosAtualFreq":
                    mRadioOutrosAtualFreq.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioOutrosPassadoFreq":
                    mRadioOutrosPassadoFreq.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioRevistasAtual":
                    mRadioRevistasAtual.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioJornaisAtual":
                    mRadioJornaisAtual.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioLivrosAtual":
                    mRadioLivrosAtual.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioRedesAtual":
                    mRadioRedesAtual.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioMensagensAtual":
                    mRadioMensagensAtual.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioLiterariosAtual":
                    mRadioLiterariosAtual.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioNaoLiterariosAtual":
                    mRadioNaoLiterariosAtual.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioOutrosAtual":
                    mRadioOutrosAtual.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioRevistasPassado":
                    mRadioRevistasPassado.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioJornaisPassado":
                    mRadioJornaisPassado.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioLivrosPassado":
                    mRadioLivrosPassado.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioRedesPassado":
                    mRadioRedesPassado.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioMensagensPassado":
                    mRadioMensagensPassado.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioLiterariosPassado":
                    mRadioLiterariosPassado.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioNaoLiterariosPassado":
                    mRadioNaoLiterariosPassado.check(isCheckedAutoComplete(x));
                    break;
                case "mRadioOutrosPassado":
                    mRadioOutrosPassado.check(isCheckedAutoComplete(x));
                    break;
                default: break;
            }
        }
    }

    public int isCheckedAutoComplete(HabitosLeituraEscritaObject.Perguntas alternativa) {
        if(alternativa.isOpcaoUmSelecionada())
            return 0;
        else if(alternativa.isOpcaoDoisSelecionada())
            return 1;
        else if(alternativa.isOpcaoDoisSelecionada())
            return 2;
        else if(alternativa.isOpcaoQuatroSelecionada())
            return 3;
        else
            return 4;
    }

    public void alteraTotalLeituraAtual(View view) {
        // O botão está selecionado?
        boolean checked = ((RadioButton) view).isChecked();
        int total = 0;

        // Marcar qual botão foi clicado
        // mGenderChosen guarda a opção, valor que será inserido no banco de dados.
        switch (view.getId()) {
            /* Frequência 4 */
            case R.id.radio_revistas_atual_4:
            case R.id.radio_jornais_atual_4:
            case R.id.radio_livros_atual_4:
            case R.id.radio_redes_atual_4: {
                if (checked) {
                    switch (view.getId()) {
                        case R.id.radio_revistas_atual_4: {
                            vetorTotalLeituraAtual[0] = Integer
                                    .parseInt(getResources().getString(R.string.valor_4));
                            break;
                        }
                        case R.id.radio_jornais_atual_4: {
                            vetorTotalLeituraAtual[1] = Integer
                                    .parseInt(getResources().getString(R.string.valor_4));
                            break;
                        }
                        case R.id.radio_livros_atual_4: {
                            vetorTotalLeituraAtual[2] = Integer
                                    .parseInt(getResources().getString(R.string.valor_4));
                            break;
                        }
                        case R.id.radio_redes_atual_4: {
                            vetorTotalLeituraAtual[3] = Integer
                                    .parseInt(getResources().getString(R.string.valor_4));
                            break;
                        }
                    }
                    for (Integer valor : vetorTotalLeituraAtual)
                        total = total + valor;
                    totalLeituraAtual.setText(Integer.toString(total));
                    alteraTotalLeitura(total, true);
                }
                break;
            }
            /* Frequência 3 */
            case R.id.radio_revistas_atual_3:
            case R.id.radio_jornais_atual_3:
            case R.id.radio_livros_atual_3:
            case R.id.radio_redes_atual_3: {
                if (checked) {
                    switch (view.getId()) {
                        case R.id.radio_revistas_atual_3: {
                            vetorTotalLeituraAtual[0] = Integer
                                    .parseInt(getResources().getString(R.string.valor_3));
                            break;
                        }
                        case R.id.radio_jornais_atual_3: {
                            vetorTotalLeituraAtual[1] = Integer
                                    .parseInt(getResources().getString(R.string.valor_3));
                            break;
                        }
                        case R.id.radio_livros_atual_3: {
                            vetorTotalLeituraAtual[2] = Integer
                                    .parseInt(getResources().getString(R.string.valor_3));
                            break;
                        }
                        case R.id.radio_redes_atual_3: {
                            vetorTotalLeituraAtual[3] = Integer
                                    .parseInt(getResources().getString(R.string.valor_3));
                            break;
                        }
                    }
                    for (Integer valor : vetorTotalLeituraAtual)
                        total = total + valor;
                    totalLeituraAtual.setText(Integer.toString(total));
                    alteraTotalLeitura(total, true);
                }
                break;
            }
            /* Frequência 2 */
            case R.id.radio_revistas_atual_2:
            case R.id.radio_jornais_atual_2:
            case R.id.radio_livros_atual_2:
            case R.id.radio_redes_atual_2: {
                if (checked) {
                    switch (view.getId()) {
                        case R.id.radio_revistas_atual_2: {
                            vetorTotalLeituraAtual[0] = Integer
                                    .parseInt(getResources().getString(R.string.valor_2));
                            break;
                        }
                        case R.id.radio_jornais_atual_2: {
                            vetorTotalLeituraAtual[1] = Integer
                                    .parseInt(getResources().getString(R.string.valor_2));
                            break;
                        }
                        case R.id.radio_livros_atual_2: {
                            vetorTotalLeituraAtual[2] = Integer
                                    .parseInt(getResources().getString(R.string.valor_2));
                            break;
                        }
                        case R.id.radio_redes_atual_2: {
                            vetorTotalLeituraAtual[3] = Integer
                                    .parseInt(getResources().getString(R.string.valor_2));
                            break;
                        }
                    }
                    for (Integer valor : vetorTotalLeituraAtual)
                        total = total + valor;
                    totalLeituraAtual.setText(Integer.toString(total));
                    alteraTotalLeitura(total, true);
                }
                break;
            }
            /* Frequência 1 */
            case R.id.radio_revistas_atual_1:
            case R.id.radio_jornais_atual_1:
            case R.id.radio_livros_atual_1:
            case R.id.radio_redes_atual_1: {
                if (checked) {
                    switch (view.getId()) {
                        case R.id.radio_revistas_atual_1: {
                            vetorTotalLeituraAtual[0] = Integer
                                    .parseInt(getResources().getString(R.string.valor_1));
                            break;
                        }
                        case R.id.radio_jornais_atual_1: {
                            vetorTotalLeituraAtual[1] = Integer
                                    .parseInt(getResources().getString(R.string.valor_1));
                            break;
                        }
                        case R.id.radio_livros_atual_1: {
                            vetorTotalLeituraAtual[2] = Integer
                                    .parseInt(getResources().getString(R.string.valor_1));
                            break;
                        }
                        case R.id.radio_redes_atual_1: {
                            vetorTotalLeituraAtual[3] = Integer
                                    .parseInt(getResources().getString(R.string.valor_1));
                            break;
                        }
                    }
                    for (Integer valor : vetorTotalLeituraAtual)
                        total = total + valor;
                    totalLeituraAtual.setText(Integer.toString(total));
                    alteraTotalLeitura(total, true);
                }
                break;
            }
            /* Frequência 0 */
            case R.id.radio_revistas_atual_0:
            case R.id.radio_jornais_atual_0:
            case R.id.radio_livros_atual_0:
            case R.id.radio_redes_atual_0: {
                if (checked) {
                    switch (view.getId()) {
                        case R.id.radio_revistas_atual_0: {
                            vetorTotalLeituraAtual[0] = Integer
                                    .parseInt(getResources().getString(R.string.valor_0));
                            break;
                        }
                        case R.id.radio_jornais_atual_0: {
                            vetorTotalLeituraAtual[1] = Integer
                                    .parseInt(getResources().getString(R.string.valor_0));
                            break;
                        }
                        case R.id.radio_livros_atual_0: {
                            vetorTotalLeituraAtual[2] = Integer
                                    .parseInt(getResources().getString(R.string.valor_0));
                            break;
                        }
                        case R.id.radio_redes_atual_0: {
                            vetorTotalLeituraAtual[3] = Integer
                                    .parseInt(getResources().getString(R.string.valor_0));
                            break;
                        }
                    }
                    for (Integer valor : vetorTotalLeituraAtual)
                        total = total + valor;
                    totalLeituraAtual.setText(Integer.toString(total));
                    alteraTotalLeitura(total, true);
                }
                break;
            }
        }
    }

    public void alteraTotalLeituraPassado(View view) {
        // O botão está selecionado?
        boolean checked = ((RadioButton) view).isChecked();
        int total = 0;

        // Marcar qual botão foi clicado
        // mGenderChosen guarda a opção, valor que será inserido no banco de dados.
        switch (view.getId()) {
            /* Frequência 4*/
            case R.id.radio_revistas_passado_4:
            case R.id.radio_jornais_passado_4:
            case R.id.radio_livros_passado_4:
            case R.id.radio_redes_passado_4: {
                if (checked) {
                    switch (view.getId()) {
                        case R.id.radio_revistas_passado_4: {
                            vetorTotalLeituraPassado[0] = Integer
                                    .parseInt(getResources().getString(R.string.valor_4));
                            break;
                        }
                        case R.id.radio_jornais_passado_4: {
                            vetorTotalLeituraPassado[1] = Integer
                                    .parseInt(getResources().getString(R.string.valor_4));
                            break;
                        }
                        case R.id.radio_livros_passado_4: {
                            vetorTotalLeituraPassado[2] = Integer
                                    .parseInt(getResources().getString(R.string.valor_4));
                            break;
                        }
                        case R.id.radio_redes_passado_4: {
                            vetorTotalLeituraPassado[3] = Integer
                                    .parseInt(getResources().getString(R.string.valor_4));
                            break;
                        }
                    }
                    for (Integer valor : vetorTotalLeituraPassado)
                        total = total + valor;
                    totalLeituraPassado.setText(Integer.toString(total));
                    alteraTotalLeitura(total, false);
                }
                break;
            }
            /* Frequência 3*/
            case R.id.radio_revistas_passado_3:
            case R.id.radio_jornais_passado_3:
            case R.id.radio_livros_passado_3:
            case R.id.radio_redes_passado_3: {
                if (checked) {
                    switch (view.getId()) {
                        case R.id.radio_revistas_passado_3: {
                            vetorTotalLeituraPassado[0] = Integer
                                    .parseInt(getResources().getString(R.string.valor_3));
                            break;
                        }
                        case R.id.radio_jornais_passado_3: {
                            vetorTotalLeituraPassado[1] = Integer
                                    .parseInt(getResources().getString(R.string.valor_3));
                            break;
                        }
                        case R.id.radio_livros_passado_3: {
                            vetorTotalLeituraPassado[2] = Integer
                                    .parseInt(getResources().getString(R.string.valor_3));
                            break;
                        }
                        case R.id.radio_redes_passado_3: {
                            vetorTotalLeituraPassado[3] = Integer
                                    .parseInt(getResources().getString(R.string.valor_3));
                            break;
                        }
                    }
                    for (Integer valor : vetorTotalLeituraPassado)
                        total = total + valor;
                    totalLeituraPassado.setText(Integer.toString(total));
                    alteraTotalLeitura(total, false);
                }
                break;
            }
            /* Frequência 2 */
            case R.id.radio_revistas_passado_2:
            case R.id.radio_jornais_passado_2:
            case R.id.radio_livros_passado_2:
            case R.id.radio_redes_passado_2: {
                if (checked) {
                    switch (view.getId()) {
                        case R.id.radio_revistas_passado_2: {
                            vetorTotalLeituraPassado[0] = Integer
                                    .parseInt(getResources().getString(R.string.valor_2));
                            break;
                        }
                        case R.id.radio_jornais_passado_2: {
                            vetorTotalLeituraPassado[1] = Integer
                                    .parseInt(getResources().getString(R.string.valor_2));
                            break;
                        }
                        case R.id.radio_livros_passado_2: {
                            vetorTotalLeituraPassado[2] = Integer
                                    .parseInt(getResources().getString(R.string.valor_2));
                            break;
                        }
                        case R.id.radio_redes_passado_2: {
                            vetorTotalLeituraPassado[3] = Integer
                                    .parseInt(getResources().getString(R.string.valor_2));
                            break;
                        }
                    }
                    for (Integer valor : vetorTotalLeituraPassado)
                        total = total + valor;
                    totalLeituraPassado.setText(Integer.toString(total));
                    alteraTotalLeitura(total, false);
                }
                break;
            }
            /* Frequência 1 */
            case R.id.radio_revistas_passado_1:
            case R.id.radio_jornais_passado_1:
            case R.id.radio_livros_passado_1:
            case R.id.radio_redes_passado_1: {
                if (checked) {
                    switch (view.getId()) {
                        case R.id.radio_revistas_passado_1: {
                            vetorTotalLeituraPassado[0] = Integer
                                    .parseInt(getResources().getString(R.string.valor_1));
                            break;
                        }
                        case R.id.radio_jornais_passado_1: {
                            vetorTotalLeituraPassado[1] = Integer
                                    .parseInt(getResources().getString(R.string.valor_1));
                            break;
                        }
                        case R.id.radio_livros_passado_1: {
                            vetorTotalLeituraPassado[2] = Integer
                                    .parseInt(getResources().getString(R.string.valor_1));
                            break;
                        }
                        case R.id.radio_redes_passado_1: {
                            vetorTotalLeituraPassado[3] = Integer
                                    .parseInt(getResources().getString(R.string.valor_1));
                            break;
                        }
                    }
                    for (Integer valor : vetorTotalLeituraPassado)
                        total = total + valor;
                    totalLeituraPassado.setText(Integer.toString(total));
                    alteraTotalLeitura(total, false);
                }
                break;
            }
            /* Frequência 0 */
            case R.id.radio_revistas_passado_0:
            case R.id.radio_jornais_passado_0:
            case R.id.radio_livros_passado_0:
            case R.id.radio_redes_passado_0: {
                if (checked) {
                    switch (view.getId()) {
                        case R.id.radio_revistas_passado_0: {
                            vetorTotalLeituraPassado[0] = Integer
                                    .parseInt(getResources().getString(R.string.valor_0));
                            break;
                        }
                        case R.id.radio_jornais_passado_0: {
                            vetorTotalLeituraPassado[1] = Integer
                                    .parseInt(getResources().getString(R.string.valor_0));
                            break;
                        }
                        case R.id.radio_livros_passado_0: {
                            vetorTotalLeituraPassado[2] = Integer
                                    .parseInt(getResources().getString(R.string.valor_0));
                            break;
                        }
                        case R.id.radio_redes_passado_0: {
                            vetorTotalLeituraPassado[3] = Integer
                                    .parseInt(getResources().getString(R.string.valor_0));
                            break;
                        }
                    }
                    for (Integer valor : vetorTotalLeituraPassado)
                        total = total + valor;
                    totalLeituraPassado.setText(Integer.toString(total));
                    alteraTotalLeitura(total, false);
                }
                break;
            }
        }
    }

    public void alteraTotalLeitura(int valorTotalTemporal, boolean ehAtual) {
        int valorTotalLeitura = 0;
        if (ehAtual) {
            vetorTotalLeitura[0] = valorTotalTemporal;
        } else {
            vetorTotalLeitura[1] = valorTotalTemporal;
        }
        for (Integer valor : vetorTotalLeitura)
            valorTotalLeitura = valorTotalLeitura + valor;
        totalLeitura.setText(Integer.toString(valorTotalLeitura));
    }

    public void alteraTotalEscritaAtual(View view) {
        // O botão está selecionado?
        boolean checked = ((RadioButton) view).isChecked();
        int total = 0;

        // Marcar qual botão foi clicado
        // mGenderChosen guarda a opção, valor que será inserido no banco de dados.
        switch (view.getId()) {
            /* Frequência 4 */
            case R.id.radio_mensagens_atual_4:
            case R.id.radio_literarios_atual_4:
            case R.id.radio_naoliterarios_atual_4:
            case R.id.radio_outros_atual_4: {
                if (checked) {
                    switch (view.getId()) {
                        case R.id.radio_mensagens_atual_4: {
                            vetorTotalEscritaAtual[0] = Integer
                                    .parseInt(getResources().getString(R.string.valor_4));
                            break;
                        }
                        case R.id.radio_literarios_atual_4: {
                            vetorTotalEscritaAtual[1] = Integer
                                    .parseInt(getResources().getString(R.string.valor_4));
                            break;
                        }
                        case R.id.radio_naoliterarios_atual_4: {
                            vetorTotalEscritaAtual[2] = Integer
                                    .parseInt(getResources().getString(R.string.valor_4));
                            break;
                        }
                        case R.id.radio_outros_atual_4: {
                            vetorTotalEscritaAtual[3] = Integer
                                    .parseInt(getResources().getString(R.string.valor_4));
                            break;
                        }
                    }
                    for (Integer valor : vetorTotalEscritaAtual)
                        total = total + valor;
                    totalEscritaAtual.setText(Integer.toString(total));
                    alteraTotalEscrita(total, true);
                }
                break;
            }

            /* Frequência 3 */
            case R.id.radio_mensagens_atual_3:
            case R.id.radio_literarios_atual_3:
            case R.id.radio_naoliterarios_atual_3:
            case R.id.radio_outros_atual_3: {
                if (checked) {
                    switch (view.getId()) {
                        case R.id.radio_mensagens_atual_3: {
                            vetorTotalEscritaAtual[0] = Integer
                                    .parseInt(getResources().getString(R.string.valor_3));
                            break;
                        }
                        case R.id.radio_literarios_atual_3: {
                            vetorTotalEscritaAtual[1] = Integer
                                    .parseInt(getResources().getString(R.string.valor_3));
                            break;
                        }
                        case R.id.radio_naoliterarios_atual_3: {
                            vetorTotalEscritaAtual[2] = Integer
                                    .parseInt(getResources().getString(R.string.valor_3));
                            break;
                        }
                        case R.id.radio_outros_atual_3: {
                            vetorTotalEscritaAtual[3] = Integer
                                    .parseInt(getResources().getString(R.string.valor_3));
                            break;
                        }
                    }
                    for (Integer valor : vetorTotalEscritaAtual)
                        total = total + valor;
                    totalEscritaAtual.setText(Integer.toString(total));
                    alteraTotalEscrita(total, true);
                }
                break;
            }
            /* Frequência 2 */
            case R.id.radio_mensagens_atual_2:
            case R.id.radio_literarios_atual_2:
            case R.id.radio_naoliterarios_atual_2:
            case R.id.radio_outros_atual_2: {
                if (checked) {
                    switch (view.getId()) {
                        case R.id.radio_mensagens_atual_2: {
                            vetorTotalEscritaAtual[0] = Integer
                                    .parseInt(getResources().getString(R.string.valor_2));
                            break;
                        }
                        case R.id.radio_literarios_atual_2: {
                            vetorTotalEscritaAtual[1] = Integer
                                    .parseInt(getResources().getString(R.string.valor_2));
                            break;
                        }
                        case R.id.radio_naoliterarios_atual_2: {
                            vetorTotalEscritaAtual[2] = Integer
                                    .parseInt(getResources().getString(R.string.valor_2));
                            break;
                        }
                        case R.id.radio_outros_atual_2: {
                            vetorTotalEscritaAtual[3] = Integer
                                    .parseInt(getResources().getString(R.string.valor_2));
                            break;
                        }
                    }
                    for (Integer valor : vetorTotalEscritaAtual)
                        total = total + valor;
                    totalEscritaAtual.setText(Integer.toString(total));
                    alteraTotalEscrita(total, true);
                }
                break;
            }

            /* Frequência 1 */
            case R.id.radio_mensagens_atual_1:
            case R.id.radio_literarios_atual_1:
            case R.id.radio_naoliterarios_atual_1:
            case R.id.radio_outros_atual_1: {
                if (checked) {
                    switch (view.getId()) {
                        case R.id.radio_mensagens_atual_1: {
                            vetorTotalEscritaAtual[0] = Integer
                                    .parseInt(getResources().getString(R.string.valor_1));
                            break;
                        }
                        case R.id.radio_literarios_atual_1: {
                            vetorTotalEscritaAtual[1] = Integer
                                    .parseInt(getResources().getString(R.string.valor_1));
                            break;
                        }
                        case R.id.radio_naoliterarios_atual_1: {
                            vetorTotalEscritaAtual[2] = Integer
                                    .parseInt(getResources().getString(R.string.valor_1));
                            break;
                        }
                        case R.id.radio_outros_atual_1: {
                            vetorTotalEscritaAtual[3] = Integer
                                    .parseInt(getResources().getString(R.string.valor_1));
                            break;
                        }
                    }
                    for (Integer valor : vetorTotalEscritaAtual)
                        total = total + valor;
                    totalEscritaAtual.setText(Integer.toString(total));
                    alteraTotalEscrita(total, true);
                }
                break;
            }

            /* Frequência 0 */
            case R.id.radio_mensagens_atual_0:
            case R.id.radio_literarios_atual_0:
            case R.id.radio_naoliterarios_atual_0:
            case R.id.radio_outros_atual_0: {
                if (checked) {
                    switch (view.getId()) {
                        case R.id.radio_mensagens_atual_0: {
                            vetorTotalEscritaAtual[0] = Integer
                                    .parseInt(getResources().getString(R.string.valor_0));
                            break;
                        }
                        case R.id.radio_literarios_atual_0: {
                            vetorTotalEscritaAtual[1] = Integer
                                    .parseInt(getResources().getString(R.string.valor_0));
                            break;
                        }
                        case R.id.radio_naoliterarios_atual_0: {
                            vetorTotalEscritaAtual[2] = Integer
                                    .parseInt(getResources().getString(R.string.valor_0));
                            break;
                        }
                        case R.id.radio_outros_atual_0: {
                            vetorTotalEscritaAtual[3] = Integer
                                    .parseInt(getResources().getString(R.string.valor_0));
                            break;
                        }
                    }
                    for (Integer valor : vetorTotalEscritaAtual)
                        total = total + valor;
                    totalEscritaAtual.setText(Integer.toString(total));
                    alteraTotalEscrita(total, true);
                }
                break;
            }
        }
    }

    public void alteraTotalEscritaPassado(View view) {
        // O botão está selecionado?
        boolean checked = ((RadioButton) view).isChecked();
        int total = 0;

        // Marcar qual botão foi clicado
        // mGenderChosen guarda a opção, valor que será inserido no banco de dados.
        switch (view.getId()) {
            /* Frequência 4*/
            case R.id.radio_mensagens_passado_4:
            case R.id.radio_literarios_passado_4:
            case R.id.radio_naoliterarios_passado_4:
            case R.id.radio_outros_passado_4: {
                if (checked) {
                    switch (view.getId()) {
                        case R.id.radio_mensagens_passado_4: {
                            vetorTotalEscritaPassado[0] = Integer
                                    .parseInt(getResources().getString(R.string.valor_4));
                            break;
                        }
                        case R.id.radio_literarios_passado_4: {
                            vetorTotalEscritaPassado[1] = Integer
                                    .parseInt(getResources().getString(R.string.valor_4));
                            break;
                        }
                        case R.id.radio_naoliterarios_passado_4: {
                            vetorTotalEscritaPassado[2] = Integer
                                    .parseInt(getResources().getString(R.string.valor_4));
                            break;
                        }
                        case R.id.radio_outros_passado_4: {
                            vetorTotalEscritaPassado[3] = Integer
                                    .parseInt(getResources().getString(R.string.valor_4));
                            break;
                        }
                    }
                    for (Integer valor : vetorTotalEscritaPassado)
                        total = total + valor;
                    totalEscritaPassado.setText(Integer.toString(total));
                    alteraTotalEscrita(total, false);
                }
                break;
            }
            /* Frequência 3*/
            case R.id.radio_mensagens_passado_3:
            case R.id.radio_literarios_passado_3:
            case R.id.radio_naoliterarios_passado_3:
            case R.id.radio_outros_passado_3: {
                if (checked) {
                    switch (view.getId()) {
                        case R.id.radio_mensagens_passado_3: {
                            vetorTotalEscritaPassado[0] = Integer
                                    .parseInt(getResources().getString(R.string.valor_3));
                            break;
                        }
                        case R.id.radio_literarios_passado_3: {
                            vetorTotalEscritaPassado[1] = Integer
                                    .parseInt(getResources().getString(R.string.valor_3));
                            break;
                        }
                        case R.id.radio_naoliterarios_passado_3: {
                            vetorTotalEscritaPassado[2] = Integer
                                    .parseInt(getResources().getString(R.string.valor_3));
                            break;
                        }
                        case R.id.radio_outros_passado_3: {
                            vetorTotalEscritaPassado[3] = Integer
                                    .parseInt(getResources().getString(R.string.valor_3));
                            break;
                        }
                    }
                    for (Integer valor : vetorTotalEscritaPassado)
                        total = total + valor;
                    totalEscritaPassado.setText(Integer.toString(total));
                    alteraTotalEscrita(total, false);
                }
                break;
            }
            /* Frequência 2 */
            case R.id.radio_mensagens_passado_2:
            case R.id.radio_literarios_passado_2:
            case R.id.radio_naoliterarios_passado_2:
            case R.id.radio_outros_passado_2: {
                if (checked) {
                    switch (view.getId()) {
                        case R.id.radio_mensagens_passado_2: {
                            vetorTotalEscritaPassado[0] = Integer
                                    .parseInt(getResources().getString(R.string.valor_2));
                            break;
                        }
                        case R.id.radio_literarios_passado_2: {
                            vetorTotalEscritaPassado[1] = Integer
                                    .parseInt(getResources().getString(R.string.valor_2));
                            break;
                        }
                        case R.id.radio_naoliterarios_passado_2: {
                            vetorTotalEscritaPassado[2] = Integer
                                    .parseInt(getResources().getString(R.string.valor_2));
                            break;
                        }
                        case R.id.radio_outros_passado_2: {
                            vetorTotalEscritaPassado[3] = Integer
                                    .parseInt(getResources().getString(R.string.valor_2));
                            break;
                        }
                    }
                    for (Integer valor : vetorTotalEscritaPassado)
                        total = total + valor;
                    totalEscritaPassado.setText(Integer.toString(total));
                    alteraTotalEscrita(total, false);
                }
                break;
            }
            /* Frequência 1 */
            case R.id.radio_mensagens_passado_1:
            case R.id.radio_literarios_passado_1:
            case R.id.radio_naoliterarios_passado_1:
            case R.id.radio_outros_passado_1: {
                if (checked) {
                    switch (view.getId()) {
                        case R.id.radio_mensagens_passado_1: {
                            vetorTotalEscritaPassado[0] = Integer
                                    .parseInt(getResources().getString(R.string.valor_1));
                            break;
                        }
                        case R.id.radio_literarios_passado_1: {
                            vetorTotalEscritaPassado[1] = Integer
                                    .parseInt(getResources().getString(R.string.valor_1));
                            break;
                        }
                        case R.id.radio_naoliterarios_passado_1: {
                            vetorTotalEscritaPassado[2] = Integer
                                    .parseInt(getResources().getString(R.string.valor_1));
                            break;
                        }
                        case R.id.radio_outros_passado_1: {
                            vetorTotalEscritaPassado[3] = Integer
                                    .parseInt(getResources().getString(R.string.valor_1));
                            break;
                        }
                    }
                    for (Integer valor : vetorTotalEscritaPassado)
                        total = total + valor;
                    totalEscritaPassado.setText(Integer.toString(total));
                    alteraTotalEscrita(total, false);
                }
                break;
            }
            /* Frequência 0 */
            case R.id.radio_mensagens_passado_0:
            case R.id.radio_literarios_passado_0:
            case R.id.radio_naoliterarios_passado_0:
            case R.id.radio_outros_passado_0: {
                if (checked) {
                    switch (view.getId()) {
                        case R.id.radio_mensagens_passado_0: {
                            vetorTotalEscritaPassado[0] = Integer
                                    .parseInt(getResources().getString(R.string.valor_0));
                            break;
                        }
                        case R.id.radio_literarios_passado_0: {
                            vetorTotalEscritaPassado[1] = Integer
                                    .parseInt(getResources().getString(R.string.valor_0));
                            break;
                        }
                        case R.id.radio_naoliterarios_passado_0: {
                            vetorTotalEscritaPassado[2] = Integer
                                    .parseInt(getResources().getString(R.string.valor_0));
                            break;
                        }
                        case R.id.radio_outros_passado_0: {
                            vetorTotalEscritaPassado[3] = Integer
                                    .parseInt(getResources().getString(R.string.valor_0));
                            break;
                        }
                    }
                    for (Integer valor : vetorTotalEscritaPassado)
                        total = total + valor;
                    totalEscritaPassado.setText(Integer.toString(total));
                    alteraTotalEscrita(total, false);
                }
                break;
            }
        }
    }

    public void alteraTotalEscrita(int valorTotalTemporal, boolean ehAtual) {
        int valorTotalEscrita = 0;
        if (ehAtual) {
            vetorTotalEscrita[0] = valorTotalTemporal;
        } else {
            vetorTotalEscrita[1] = valorTotalTemporal;
        }
        for (Integer valor : vetorTotalEscrita)
            valorTotalEscrita = valorTotalEscrita + valor;
        totalEscrita.setText(Integer.toString(valorTotalEscrita));
    }

    /* Método criado desta forma uma vez que necessita de um parâmetro */
    public int onFrequenciaRadioButtonClicked(RadioGroup mRadio) {
        int checkedHabito = 0;
        String checkedFrequencia;
        int selectedRadioId = mRadio.getCheckedRadioButtonId();
        if (selectedRadioId != -1) {
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioId);
            checkedFrequencia = selectedRadioButton.getText().toString();
            if (checkedFrequencia.equals(getResources().getString(R.string.habitos_freq_4))) {
                checkedHabito = 4;
            } else {
                if (checkedFrequencia.equals(getResources().getString(R.string.habitos_freq_3))) {
                    checkedHabito = 3;
                } else {
                    if (checkedFrequencia.equals(getResources().getString(R.string.habitos_freq_2))) {
                        checkedHabito = 2;
                    } else {
                        if (checkedFrequencia.equals(getResources().getString(R.string.habitos_freq_1))) {
                            checkedHabito = 1;
                        }
                    }
                }
            }
        }
        return checkedHabito;
    }

    /* Formato da mídia (impresso ou digital)...? */
    public String onFormatoRadioButtonClicked(RadioGroup mRadio) {
        int selectedRadioId = mRadio.getCheckedRadioButtonId();
        if (selectedRadioId != -1) {
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioId);
            return selectedRadioButton.getText().toString();
        }
        return null;
    }

    public void atualizaPorcentagemPesquisa() {
        pesquisa.calculaPorcentagemTotal();
    }

    public void registrar(HabitosLeituraEscritaObject habitosLeituraEscritaObject) {
        try {
        /* Leitura */
        /* Talvez valha a pena criar uma classe "Resposta" */
            checkedLeitRevistasAtualFreq = onFrequenciaRadioButtonClicked(mRadioRevistasAtualFreq);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioRevistasAtualFreq", checkedLeitRevistasAtualFreq);

            checkedLeitRevistasPassadoFreq = onFrequenciaRadioButtonClicked(mRadioRevistasPassadoFreq);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioRevistasPassadoFreq", checkedLeitRevistasPassadoFreq);

            checkedLeitJornaisAtualFreq = onFrequenciaRadioButtonClicked(mRadioJornaisAtualFreq);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioJornaisAtualFreq", checkedLeitJornaisAtualFreq);

            checkedLeitJornaisPassadoFreq = onFrequenciaRadioButtonClicked(mRadioJornaisPassadoFreq);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioJornaisPassadoFreq", checkedLeitJornaisPassadoFreq);

            checkedLeitLivrosAtualFreq = onFrequenciaRadioButtonClicked(mRadioLivrosAtualFreq);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioLivrosAtualFreq", checkedLeitLivrosAtualFreq);

            checkedLeitLivrosPassadoFreq = onFrequenciaRadioButtonClicked(mRadioLivrosPassadoFreq);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioLivrosPassadoFreq", checkedLeitLivrosPassadoFreq);

            checkedLeitRedesAtualFreq = onFrequenciaRadioButtonClicked(mRadioRedesAtualFreq);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioRedesAtualFreq", checkedLeitRedesAtualFreq);

            checkedLeitRedesPassadoFreq = onFrequenciaRadioButtonClicked(mRadioRedesPassadoFreq);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioRedesPassadoFreq", checkedLeitRedesPassadoFreq);

        /* Escrita */
            checkedEscrMensagensAtualFreq = onFrequenciaRadioButtonClicked(mRadioMensagensAtualFreq);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioMensagensAtualFreq", checkedEscrMensagensAtualFreq);

            checkedEscrMensagensPassadoFreq = onFrequenciaRadioButtonClicked(mRadioMensagensPassadoFreq);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioMensagensPassadoFreq", checkedEscrMensagensPassadoFreq);

            checkedEscrLiterariosAtualFreq = onFrequenciaRadioButtonClicked(mRadioLiterariosAtualFreq);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioLiterariosAtualFreq", checkedEscrLiterariosAtualFreq);

            checkedEscrLiterariosPassadoFreq = onFrequenciaRadioButtonClicked(mRadioLiterariosPassadoFreq);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioLiterariosPassadoFreq", checkedEscrLiterariosPassadoFreq);

            checkedEscrNaoLiterariosAtualFreq = onFrequenciaRadioButtonClicked(mRadioNaoLiterariosAtualFreq);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioNaoLiterariosAtualFreq", checkedEscrNaoLiterariosAtualFreq);

            checkedEscrNaoLiterariosPassadoFreq = onFrequenciaRadioButtonClicked(mRadioNaoLiterariosPassadoFreq);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioNaoLiterariosPassadoFreq", checkedEscrNaoLiterariosPassadoFreq);

            checkedEscrOutrosAtualFreq = onFrequenciaRadioButtonClicked(mRadioOutrosAtualFreq);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioOutrosAtualFreq", checkedEscrOutrosAtualFreq);

            checkedEscrOutrosPassadoFreq = onFrequenciaRadioButtonClicked(mRadioOutrosPassadoFreq);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioOutrosPassadoFreq", checkedEscrOutrosPassadoFreq);

        /* Atual */
            checkedLeitRevistasAtual = onFrequenciaRadioButtonClicked(mRadioRevistasAtual);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioRevistasAtual", checkedLeitRevistasAtual);

            checkedLeitJornaisAtual = onFrequenciaRadioButtonClicked(mRadioJornaisAtual);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioJornaisAtual", checkedLeitJornaisAtual);

            checkedLeitLivrosAtual = onFrequenciaRadioButtonClicked(mRadioLivrosAtual);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioLivrosAtual", checkedLeitLivrosAtual);

            checkedLeitRedesAtual = onFrequenciaRadioButtonClicked(mRadioRedesAtual);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioRedesAtual", checkedLeitRedesAtual);

            checkedEscrMensagensAtual = onFrequenciaRadioButtonClicked(mRadioMensagensAtual);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioMensagensAtual", checkedEscrMensagensAtual);

            checkedEscrLiterariosAtual = onFrequenciaRadioButtonClicked(mRadioLiterariosAtual);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioLiterariosAtual", checkedEscrLiterariosAtual);

            checkedEscrNaoLiterariosAtual = onFrequenciaRadioButtonClicked(mRadioNaoLiterariosAtual);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioNaoLiterariosAtual", checkedEscrNaoLiterariosAtual);

            checkedEscrOutrosAtual = onFrequenciaRadioButtonClicked(mRadioOutrosAtual);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioOutrosAtual", checkedEscrOutrosAtual);

        /* Passado */
            checkedLeitRevistasPassado = onFrequenciaRadioButtonClicked(mRadioRevistasPassado);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioRevistasPassado", checkedLeitRevistasPassado);

            checkedLeitJornaisPassado = onFrequenciaRadioButtonClicked(mRadioJornaisPassado);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioJornaisPassado", checkedLeitJornaisPassado);

            checkedLeitLivrosPassado = onFrequenciaRadioButtonClicked(mRadioLivrosPassado);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioLivrosPassado", checkedLeitLivrosPassado);

            checkedLeitRedesPassado = onFrequenciaRadioButtonClicked(mRadioRedesPassado);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioRedesPassado", checkedLeitRedesPassado);

            checkedEscrMensagensPassado = onFrequenciaRadioButtonClicked(mRadioMensagensPassado);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioMensagensPassado", checkedEscrMensagensPassado);

            checkedEscrLiterariosPassado = onFrequenciaRadioButtonClicked(mRadioLiterariosPassado);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioLiterariosPassado", checkedEscrLiterariosPassado);

            checkedEscrNaoLiterariosPassado = onFrequenciaRadioButtonClicked(mRadioNaoLiterariosPassado);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioNaoLiterariosPassado", checkedEscrNaoLiterariosPassado);

            checkedEscrOutrosPassado = onFrequenciaRadioButtonClicked(mRadioOutrosPassado);
            traduzRadioButtonSelecionado(habitosLeituraEscritaObject, "mRadioOutrosPassado", checkedEscrOutrosPassado);


        } catch (Exception e) {
            Toast.makeText(HabitosLeituraEscritaActivity.this, R.string.toast_radiobutton, Toast.LENGTH_LONG).show();
            return;
        }

//        try {
//            progressDialog = ProgressDialog.show(HabitosLeituraEscritaActivity.this, null,
//                    getResources().getText(R.string.examinador_progress_message), true, false);
//            new RegistrarBancoTask().execute((Void) null);
//        } catch (Exception e) {
//            progressDialog.dismiss();
//            Toast.makeText(HabitosLeituraEscritaActivity.this, R.string.toast_registrar, Toast.LENGTH_LONG).show();
//        }
    }

    public void traduzRadioButtonSelecionado(HabitosLeituraEscritaObject habitosLeituraEscritaObject, String descricao, int selecao) {
        switch (selecao) {
            case 0:
                habitosLeituraEscritaObject.criaPergunta(descricao, true, false, false, false, false);
                break;
            case 1:
                habitosLeituraEscritaObject.criaPergunta(descricao, false, true, false, false, false);
                break;
            case 2:
                habitosLeituraEscritaObject.criaPergunta(descricao, false, false,true,false, false);
                break;
            case 3:
                habitosLeituraEscritaObject.criaPergunta(descricao, false, false,false,true, false);
                break;
            case 4:
                habitosLeituraEscritaObject.criaPergunta(descricao, false, false,false,false, true);
                break;
            default:
                break;
        }
    }

    public void alteraDadosFirebase(HabitosLeituraEscritaObject habitosLeituraEscritaObject) {
        final HabitosLeituraEscritaObject hleObject = habitosLeituraEscritaObject;
        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference mParticipanteDatabaseReference;
        ChildEventListener mChildEventListener;
        //Cria o caminho que garantirá o acesso somente aos participantes do usuário logado
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        mParticipanteDatabaseReference = mFirebaseDatabase.getReference().child("users").child(auth.getCurrentUser().getUid()).child("participantes");

        //Responsavel por ler o banco de dados
        mChildEventListener = new ChildEventListener() {
            //Método que lê os valores de determinada key
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Participante participante = dataSnapshot.getValue(Participante.class);
                participante.setHleObject(hleObject);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mParticipanteDatabaseReference.addChildEventListener(mChildEventListener);
        //-------------------------------
    }
}

//    public class RegistrarBancoTask extends AsyncTask<Void, Void, Boolean> {
//
//        private ArrayList<ContentValues> vetorContentValues;
//
//        public RegistrarBancoTask() {
//            vetorContentValues = new ArrayList<>();
//        }
//
//        @Override
//        protected Boolean doInBackground(Void... params) {
//            try {
//                Cursor perguntasCursor = getContentResolver().query(
//                        DatabaseContract.PerguntasEntry.CONTENT_URI,
//                        new String[]{DatabaseContract.PerguntasEntry._ID, DatabaseContract.PerguntasEntry.COLUMN_CONTEUDO},
//                        DatabaseContract.PerguntasEntry.COLUMN_ETAPA + " LIKE ?",
//                        new String[]{"Hábitos%"},
//                        null);
//                if (perguntasCursor != null) {
//                    /* TODO: Não deixar a etapa constantemente como sendo a primeira da pesquisa.
//                    Aqui, a etapa de Leitura e Escrita está chumbada como 0 (1ª etapa).
//                    No futuro, a ordem das etapas pode mudar,
//                    ou seja, este número deverá ser uma variável.
//                    Lembrando que a ordem das etapas deve ser alterada no ArrayList da classe Pesquisa, também.*/
//                    int j = 0;
//                    Log.d("in ", "Cursor");
//                    pesquisa.getTestBoolean();
//
//                    while (perguntasCursor.moveToNext()) {
//                        criaRespostasBanco(perguntasCursor);
//
//                        Log.d("in", "MoveNext");
//
//                        /* Essa parte do código a seguir está impedindo o fluxo da bateria.
//                        * TODO: Descobrir como arrumar esse erro! */
//                        pesquisa.getPerguntasPorEtapa().get(0).set(j, false);
//                        //Log.d("Participan: " , (pesquisa.getPerguntasPorEtapa().get(0).get(0).toString()));
//                        j++;
//                    }
//                    for (ContentValues respostaBanco : vetorContentValues) {
//                        getContentResolver().insert(DatabaseContract.PesquisasEntry.CONTENT_URI, respostaBanco);
//                    }
//                    perguntasCursor.close();
//                }
//            } catch (Exception e) {
//                return false;
//            }
//            return true;
//        }
//
//        @Override
//        protected void onPostExecute(final Boolean success) {
//            progressDialog.dismiss();
//            // TODO: !
//            if (!success) {
//                atualizaPorcentagemPesquisa();
//                Intent intent = new Intent(getApplicationContext(), CompreensaoFraseActivity.class);
//                intent.putExtra(LoginActivity.EXTRA_PK_USUARIO, pk_usuario);
//                intent.putExtra(BateriaActivity.EXTRA_PK_PESQUISA, pk_pesquisa);
//                intent.putExtra(ExaminadorMainActivity.EXTRA_PESQUISA, pesquisa);
//                startActivity(intent);
//                finish();
//            } else {
//                Toast.makeText(HabitosLeituraEscritaActivity.this, R.string.toast_registrar, Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        protected void onCancelled() {
//            progressDialog.dismiss();
//        }
//
//        public void criaContentValues(String pk_pergunta, String resposta) {
//            ContentValues respostaBanco = new ContentValues();
//            respostaBanco.put("id_pesquisa", pk_pesquisa);
//            respostaBanco.put("id_pergunta", pk_pergunta);
//            respostaBanco.put("resposta", resposta);
//            vetorContentValues.add(respostaBanco);
//            Log.d("VetorContent: ", vetorContentValues.get(0).toString());
//        }
//
//        public void criaRespostasBanco(Cursor perguntasCursor) {
//            /* As perguntas são separadas da seguinte forma:
//            *   l. Leitura
//            *
//            *   1.1. REVISTAS
//            *   1.1.1. Atual
//            *   1.1.1.1 Frequência
//            *   1.1.2. Passado
//            *   1.1.2.1. Frequência
//            *
//            *   1.2. JORNAIS
//            *   1.2.1. Atual
//            *   1.2.1.1 Frequência
//            *   1.2.2. Passado
//            *   1.2.2.1. Frequência
//            *
//            *   1.3. LIVROS
//            *   1.3.1. Atual
//            *   1.3.1.1 Frequência
//            *   1.3.2. Passado
//            *   1.3.2.1. Frequência
//            *
//            *   1.4. REDES
//            *   1.4.1. Atual
//            *   1.4.1.1 Frequência
//            *   1.4.2. Passado
//            *   1.4.2.1. Frequência
//            *
//            *   2. Escrita
//            *
//            *   1.1. MENSAGENS
//            *   1.1.1. Atual
//            *   1.1.1.1 Frequência
//            *   1.1.2. Passado
//            *   1.1.2.1. Frequência
//            *
//            *   1.2. LITERÁRIOS
//            *   1.2.1. Atual
//            *   1.2.1.1 Frequência
//            *   1.2.2. Passado
//            *   1.2.2.1. Frequência
//            *
//            *   1.3. NÃO LITERÁRIOS
//            *   1.3.1. Atual
//            *   1.3.1.1 Frequência
//            *   1.3.2. Passado
//            *   1.3.2.1. Frequência
//            *
//            *   1.4. OUTROS
//            *   1.4.1. Atual
//            *   1.4.1.1 Frequência
//            *   1.4.2. Passado
//            *   1.4.2.1. Frequência
//            * */
//            String pergunta = perguntasCursor.getString(1);
//            /* Leitura */
//
//            if (pergunta.contains(getResources().getString(R.string.bateria_habitos_leitura))) {
//                // Leitura...
//                if (pergunta.contains(getResources().getString(R.string.habitos_leit_revista))) {
//                    // Leitura Revistas...
//                    if (pergunta.contains(getResources().getString(R.string.habitos_atual))) {
//                        // Leitura Revistas Atual...
//                        if (pergunta.contains(getResources().getString(R.string.habitos_frequencia)
//                        )) {
//                            // Leitura Revistas Atual Frequência
//                            criaContentValues(
//                                    perguntasCursor.getString(0),
//                                    Integer.toString(checkedLeitRevistasAtualFreq));
//                        } else {
//                            // Leitura Revistas Atual
//                            criaContentValues(
//                                    perguntasCursor.getString(0), checkedLeitRevistasAtual);
//                        }
//                    } else {
//                        // Leitura Revistas Passado...
//                        if (pergunta.contains(getResources().getString(R.string.habitos_frequencia)
//                        )) {
//                            // Leitura Revistas Passado Frequência
//                            criaContentValues(
//                                    perguntasCursor.getString(0),
//                                    Integer.toString(checkedLeitRevistasPassadoFreq));
//                        } else {
//                            // Leitura Revistas Passado
//                            criaContentValues(
//                                    perguntasCursor.getString(0), checkedLeitRevistasPassado);
//                        }
//                    }
//                } else {
//                    if (pergunta.contains(getResources().getString(R.string.habitos_leit_jornal))) {
//                        // Leitura Jornais...
//                        if (pergunta.contains(getResources().getString(R.string.habitos_atual))) {
//                            // Leitura Jornais Atual...
//                            if (pergunta.contains(getResources().getString(R.string.habitos_frequencia)
//                            )) {
//                                // Leitura Jornais Atual Frequência
//                                criaContentValues(
//                                        perguntasCursor.getString(0),
//                                        Integer.toString(checkedLeitJornaisAtualFreq));
//                            } else {
//                                // Leitura Jornais Atual
//                                criaContentValues(
//                                        perguntasCursor.getString(0), checkedLeitJornaisAtual);
//                            }
//                        } else {
//                            // Leitura Jornais Passado...
//                            if (pergunta.contains(getResources().getString(R.string.habitos_frequencia)
//                            )) {
//                                // Leitura Jornais Passado Frequência
//                                criaContentValues(
//                                        perguntasCursor.getString(0),
//                                        Integer.toString(checkedLeitJornaisPassadoFreq));
//                            } else {
//                                // Leitura Jornais Passado
//                                criaContentValues(
//                                        perguntasCursor.getString(0), checkedLeitJornaisPassado);
//                            }
//                        }
//                    } else {
//                        if (pergunta.contains(getResources().getString(R.string.habitos_leit_livro))) {
//                            // Leitura Livros...
//                            if (pergunta.contains(getResources().getString(R.string.habitos_atual))) {
//                                // Leitura Livros Atual...
//                                if (pergunta.contains(getResources().getString(R.string.habitos_frequencia)
//                                )) {
//                                    // Leitura Livros Atual Frequência
//                                    criaContentValues(
//                                            perguntasCursor.getString(0),
//                                            Integer.toString(checkedLeitLivrosAtualFreq));
//                                } else {
//                                    // Leitura Livros Atual
//                                    criaContentValues(
//                                            perguntasCursor.getString(0), checkedLeitLivrosAtual);
//                                }
//                            } else {
//                                // Leitura Livros Passado...
//                                if (pergunta.contains(getResources().getString(R.string.habitos_frequencia)
//                                )) {
//                                    // Leitura Livros Passado Frequência
//                                    criaContentValues(
//                                            perguntasCursor.getString(0),
//                                            Integer.toString(checkedLeitLivrosPassadoFreq));
//                                } else {
//                                    // Leitura Livros Passado
//                                    criaContentValues(
//                                            perguntasCursor.getString(0), checkedLeitLivrosPassado);
//                                }
//                            }
//                        } else {
//                            // Leitura Redes...
//                            if (pergunta.contains(getResources().getString(R.string.habitos_atual))) {
//                                // Leitura Redes Atual...
//                                if (pergunta.contains(getResources().getString(R.string.habitos_frequencia)
//                                )) {
//                                    // Leitura Redes Atual Frequência
//                                    criaContentValues(
//                                            perguntasCursor.getString(0),
//                                            Integer.toString(checkedLeitRedesAtualFreq));
//                                } else {
//                                    // Leitura Redes Atual
//                                    criaContentValues(
//                                            perguntasCursor.getString(0), checkedLeitRedesAtual);
//                                }
//                            } else {
//                                // Leitura Redes Passado...
//                                if (pergunta.contains(getResources().getString(R.string.habitos_frequencia)
//                                )) {
//                                    // Leitura Redes Passado Frequência
//                                    criaContentValues(
//                                            perguntasCursor.getString(0),
//                                            Integer.toString(checkedLeitRedesPassadoFreq));
//                                } else {
//                                    // Leitura Redes Passado
//                                    criaContentValues(
//                                            perguntasCursor.getString(0), checkedLeitRedesPassado);
//                                }
//                            }
//                        }
//                    }
//                }
//            /* Escrita */
//            } else {
//                if (pergunta.contains(getResources().getString(R.string.habitos_escrita))) {
//                    // Escrita...
//                    if (pergunta.contains(getResources().getString(R.string.habitos_escr_mensagem))) {
//                        // Escrita Mensagens...
//                        if (pergunta.contains(getResources().getString(R.string.habitos_atual))) {
//                            // Escrita Mensagens Atual...
//                            if (pergunta.contains(getResources().getString(R.string.habitos_frequencia)
//                            )) {
//                                // Escrita Mensagens Atual Frequência
//                                criaContentValues(
//                                        perguntasCursor.getString(0),
//                                        Integer.toString(checkedEscrMensagensAtualFreq));
//                            } else {
//                                // Escrita Mensagens Atual
//                                criaContentValues(
//                                        perguntasCursor.getString(0), checkedEscrMensagensAtual);
//                            }
//                        } else {
//                            // Escrita Mensagens Passado...
//                            if (pergunta.contains(getResources().getString(R.string.habitos_frequencia)
//                            )) {
//                                // Escrita Mensagens Passado Frequência
//                                criaContentValues(
//                                        perguntasCursor.getString(0),
//                                        Integer.toString(checkedEscrMensagensPassadoFreq));
//                            } else {
//                                // Escrita Mensagens Passado
//                                criaContentValues(
//                                        perguntasCursor.getString(0), checkedEscrMensagensPassado);
//                            }
//                        }
//                    } else {
//                        if (pergunta.contains(getResources().getString(R.string.habitos_escr_literario))) {
//                            // Escrita Literarios...
//                            if (pergunta.contains(getResources().getString(R.string.habitos_atual))) {
//                                // Escrita Literarios Atual...
//                                if (pergunta.contains(getResources().getString(R.string.habitos_frequencia)
//                                )) {
//                                    // Escrita Literarios Atual Frequência
//                                    criaContentValues(
//                                            perguntasCursor.getString(0),
//                                            Integer.toString(checkedEscrLiterariosAtualFreq));
//                                } else {
//                                    // Escrita Literarios Atual
//                                    criaContentValues(
//                                            perguntasCursor.getString(0), checkedEscrLiterariosAtual);
//                                }
//                            } else {
//                                // Escrita Literarios Passado...
//                                if (pergunta.contains(getResources().getString(R.string.habitos_frequencia)
//                                )) {
//                                    // Escrita Literarios Passado Frequência
//                                    criaContentValues(
//                                            perguntasCursor.getString(0),
//                                            Integer.toString(checkedEscrLiterariosPassadoFreq));
//                                } else {
//                                    // Escrita Literarios Passado
//                                    criaContentValues(
//                                            perguntasCursor.getString(0), checkedEscrLiterariosPassado);
//                                }
//                            }
//                        } else {
//                            if (pergunta.contains(getResources().getString(R.string.habitos_escr_naoliterario))) {
//                                // Escrita Não Literários...
//                                if (pergunta.contains(getResources().getString(R.string.habitos_atual))) {
//                                    // Escrita Não Literários Atual...
//                                    if (pergunta.contains(getResources().getString(R.string.habitos_frequencia)
//                                    )) {
//                                        // Escrita Não Literários Atual Frequência
//                                        criaContentValues(
//                                                perguntasCursor.getString(0),
//                                                Integer.toString(checkedEscrNaoLiterariosAtualFreq));
//                                    } else {
//                                        // Escrita Não Literários Atual
//                                        criaContentValues(
//                                                perguntasCursor.getString(0), checkedEscrNaoLiterariosAtual);
//                                    }
//                                } else {
//                                    // Escrita Não Literários Passado...
//                                    if (pergunta.contains(getResources().getString(R.string.habitos_frequencia)
//                                    )) {
//                                        // Escrita Não Literários Passado Frequência
//                                        criaContentValues(
//                                                perguntasCursor.getString(0),
//                                                Integer.toString(checkedEscrNaoLiterariosPassadoFreq));
//                                    } else {
//                                        // Escrita Não Literários Passado
//                                        criaContentValues(
//                                                perguntasCursor.getString(0), checkedEscrNaoLiterariosPassado);
//                                    }
//                                }
//                            } else {
//                                // Escrita Outros...
//                                if (pergunta.contains(getResources().getString(R.string.habitos_atual))) {
//                                    // Escrita Outros Atual...
//                                    if (pergunta.contains(getResources().getString(R.string.habitos_frequencia)
//                                    )) {
//                                        // Escrita Outros Atual Frequência
//                                        criaContentValues(
//                                                perguntasCursor.getString(0),
//                                                Integer.toString(checkedEscrOutrosAtualFreq));
//                                    } else {
//                                        // Escrita Outros Atual
//                                        criaContentValues(
//                                                perguntasCursor.getString(0), checkedEscrOutrosAtual);
//                                    }
//                                } else {
//                                    // Escrita Outros Passado...
//                                    if (pergunta.contains(getResources().getString(R.string.habitos_frequencia)
//                                    )) {
//                                        // Escrita Outros Passado Frequência
//                                        criaContentValues(
//                                                perguntasCursor.getString(0),
//                                                Integer.toString(checkedEscrOutrosPassadoFreq));
//                                    } else {
//                                        // Escrita Outros Passado
//                                        criaContentValues(
//                                                perguntasCursor.getString(0), checkedEscrOutrosPassado);
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            Log.d("HLEA - Respostas", Integer.toString(vetorContentValues.size()));
//        }
//
//        public ArrayList<ContentValues> getVetorContentValues() {
//            return vetorContentValues;
//        }
//
//        public void setVetorContentValues(ArrayList<ContentValues> vetorContentValues) {
//            this.vetorContentValues = vetorContentValues;
//        }
//    }
//
//    /*@Override
//    public void onStart() {
//        super.onStart();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "HabitosLeituraEscrita Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app URL is correct.
//                Uri.parse("android-app://com.example.app.bale.bale/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(client, viewAction);
//    }*/
//
//    /*private RadioGroup createRadioButton() {
//        final RadioButton[] radioButtons = new RadioButton[frequencias.size()];
//        RadioGroup radioGroup = new RadioGroup(this);
//        radioGroup.setOrientation(RadioGroup.HORIZONTAL); // Ou RadioGroup.VERTICAL
//        for (int i = 0; i < frequencias.size(); i++) {
//            radioButtons[i] = new RadioButton(this);
//            radioButtons[i].setText(frequencias.get(i));
//            radioGroup.addView(radioButtons[i]);
//        }
//        return radioGroup;
//        for (String leitura : tipoLeitura) {
//             for (String tempo : tipoTempo) {
//                 radioButtons[]
//             }
//        }
//    }*/
//
//    /*private void geraHabitos(ArrayList<String> tipoHabitos) {
//        for (String tl : tipoHabitos) {
//            // Exemplo: Revista
//            TextView tipoHabitosView = new TextView(this);
//            tipoHabitosView.setText(tl.toString());
//            linearLayout.addView(tipoHabitosView);
//            for (String tt : tipoTempo) {
//                // Exemplo: Atual
//                TextView tipoTempoView = new TextView(this);
//                tipoTempoView.setText(tt.toString());
//                linearLayout.addView(tipoTempoView);
//                linearLayout.addView(createRadioButton());
//            }
//        }
//    }
//
//    // (Escrito a mão pois) Depois será coletado do Banco de Dados
//    private ArrayList<String> geraFrequencia() {
//        frequencias = new ArrayList<>();
//        frequencias.add("Todos os dias");
//        frequencias.add("Alguns dias por semana");
//        frequencias.add("Uma vez por semana");
//        frequencias.add("Rarsmente");
//        frequencias.add("Nunca");
//        return frequencias;
//    }
//
//    private ArrayList<String> geraTipoTempo() {
//        tipoTempo = new ArrayList<>();
//        tipoTempo.add("Atual");
//        tipoTempo.add("Passado");
//        return tipoTempo;
//    }
//
//    private ArrayList<String> geraTipoEdicao() {
//        tipoEdicao = new ArrayList<>();
//        tipoEdicao.add("Digital");
//        tipoEdicao.add("Impresso");
//        return tipoEdicao;
//    }
//
//    private ArrayList<String> geraTipoLeitura() {
//        tipoLeitura = new ArrayList<>();
//        tipoLeitura.add("Revistas");
//        tipoLeitura.add("Jornais");
//        tipoLeitura.add("Livros");
//        tipoLeitura.add("Redes Sociais");
//        return tipoLeitura;
//    }
//
//    private ArrayList<String> geraTipoEscrita() {
//        tipoEscrita = new ArrayList<>();
//        tipoEscrita.add("Textos/Mensagens");
//        tipoEscrita.add("Textos Literários");
//        tipoEscrita.add("Textos Não Literários");
//        tipoEscrita.add("Outros");
//        return tipoEscrita;
//    }*/
//
//}
