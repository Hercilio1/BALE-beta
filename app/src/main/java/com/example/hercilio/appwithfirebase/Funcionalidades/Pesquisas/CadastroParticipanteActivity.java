package com.example.hercilio.appwithfirebase.Funcionalidades.Pesquisas;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Usuarios.UsuariosAdapter;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.Objetos.UserDados;
import com.example.hercilio.appwithfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Hercilio on 19/12/2017.
 */

public class CadastroParticipanteActivity extends AppCompatActivity {

    private EditText mNomeCompleto;
    private EditText mCpf;
    private TextView mDataNasc;
    private RadioGroup mSexo;
    private EditText mCelular;
    private EditText mEscolaridade;
    private RadioGroup mDinamicaManual;
    private EditText mProfissao;
    private CheckBox mEhAposentado;
    private EditText mLinguaMaterna;
    private EditText mOutrasLinguas;

    private static final Calendar calendar = Calendar.getInstance();
    private static Date dobDate;
    private static SimpleDateFormat dateFormat;
    private static String dateFormatted;

    private Button cadastrar;

    private PesquisasAdapter mPesquisaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_participante);

        mNomeCompleto = (EditText) findViewById(R.id.input_participante_nome_completo);
        mCpf = (EditText) findViewById(R.id.input_participante_cpf);
        //Utilizado para gerir a data de nascimento
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        mDataNasc = (TextView) findViewById(R.id.input_participante_data_nasc_edit);
        calendar.add(Calendar.DATE, 0);
        dateFormatted = dateFormat.format(calendar.getTime());
        mDataNasc.setText(dateFormatted);
        mDataNasc.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 showDatePickerDialog(view);
             }
         });
        //----------------------------------------
        mSexo = (RadioGroup) findViewById(R.id.radio_participante_sexo);
        mCelular = (EditText) findViewById(R.id.input_participante_telefone);
        mEscolaridade = (EditText) findViewById(R.id.input_participante_escolariade);
        mDinamicaManual = (RadioGroup) findViewById(R.id.radio_participante_dinamica_mao);
        mProfissao = (EditText) findViewById(R.id.input_participante_profissao);
        mEhAposentado = (CheckBox) findViewById(R.id.checkBox_participante_aposentado);
        mLinguaMaterna = (EditText) findViewById(R.id.input_participante_idioma_materno);
        mOutrasLinguas = (EditText) findViewById(R.id.input_participante_outros_idiomas);

        cadastrar = (Button) findViewById(R.id.btn_participante_register);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadatrarParticipante();

            }
        });
    }

    /**
     * Captura os valores das views do layout e realiza o cadastro no banco de dados.
     */
    private void cadatrarParticipante(){
        String nomeCompleto = mNomeCompleto.getText().toString();
        String cpf = mCpf.getText().toString();
        cpf = cpf.replaceAll("[^0-9]", "");
        //Adapta a data de nascimento
        SimpleDateFormat databaseFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataNasc;
        if(dobDate != null)
            dataNasc = databaseFormat.format(dobDate);
        else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            alert.setTitle("Atenção");
            alert.setMessage("A data inserida é invalida. Por favor seleicone outra!");

            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    return;
                }
            });
            AlertDialog dialog = alert.create();
            alert.show();
            return;
        }
        //---------------------------
        //Pegar o radiobutton do sexc
        int selectedIdSexo = mSexo.getCheckedRadioButtonId();
        RadioButton sexoDeterminado = (RadioButton) findViewById(selectedIdSexo);
        String sexo = sexoDeterminado.getText().toString();
        //---------------------------
        String celular = mCelular.getText().toString();
        String escolaridade = mEscolaridade.getText().toString();
        //Pegar o radiobutton da dinamica manual
        int selectedIdDinamicaManual = mDinamicaManual.getCheckedRadioButtonId();
        RadioButton dinamicaManualDeterminada = (RadioButton) findViewById(selectedIdDinamicaManual);
        String dinamicaManual = dinamicaManualDeterminada.getText().toString();
        //---------------------------
        String profissao = mProfissao.getText().toString();
        boolean ehAposentado = mEhAposentado.isChecked();
        String linguaMaterna = mLinguaMaterna.getText().toString();
        String outrosIdiomas = mOutrasLinguas.getText().toString();

        //Cria o participante com base nas caracteristicas dele
        Participante participante = new Participante(nomeCompleto, cpf, dataNasc, sexo, celular, escolaridade,
                dinamicaManual, profissao, ehAposentado, linguaMaterna, outrosIdiomas);


        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        DatabaseReference userRef = rootRef.child("users/" + auth.getCurrentUser().getUid() + "/participantes");
        userRef.child(cpf).setValue(participante);
        atualizaUserDados();

        Toast.makeText(this, "Participante " + nomeCompleto + " cadastrado com sucesso!", Toast.LENGTH_SHORT);

        Intent intent = new Intent(this, BaleLobbyActivity.class);
        intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
        startActivity(intent);
    }

    /**
     * Irá chamar o método responsável por modular o TV da data de nascimento.
     *
     * @param view
     */
    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    /**
     * Ira modular o TextView da data de nascimento para aceitar o formato padrão.
     */
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        DatePicker datePicker;
        DatePickerDialog datePickerDialog;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);

            // Insere datas limites (data atual > data de nascimento > 1/1/1916
            Calendar auxCalendar = Calendar.getInstance();
            datePickerDialog.getDatePicker().setMaxDate(auxCalendar.getTimeInMillis());

            auxCalendar.set(Calendar.YEAR, 1916);
            auxCalendar.set(Calendar.DAY_OF_YEAR, 1);
            long minDoB = auxCalendar.getTimeInMillis();
            datePickerDialog.getDatePicker().setMinDate(minDoB);

            return datePickerDialog;
            
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            calendar.set(year, month, day);
            dobDate = calendar.getTime();
            ((TextView) getActivity().findViewById(R.id.input_participante_data_nasc_edit)).setText(dateFormat.format(dobDate));
        }
    }

    public void atualizaUserDados(){

        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        final DatabaseReference mUsuarioDatabaseReference = mFirebaseDatabase.getReference().child("users").child(auth.getCurrentUser().getUid()).child("UserDados");
        final ValueEventListener eventListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                UserDados userDados = dataSnapshot.getValue(UserDados.class);
                userDados.setNroDeParticipantesEntrevistados(userDados.getNroDeParticipantesEntrevistados() + 1);
                mUsuarioDatabaseReference.setValue(userDados);
                return;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        mUsuarioDatabaseReference.addListenerForSingleValueEvent(eventListener);

    }

}
