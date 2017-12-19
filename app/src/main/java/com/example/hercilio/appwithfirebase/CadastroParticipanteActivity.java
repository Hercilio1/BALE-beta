package com.example.hercilio.appwithfirebase;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Hercilio on 19/12/2017.
 */

public class CadastroParticipanteActivity extends AppCompatActivity {

    private EditText mNomeCompleto;
    private TextView mDataNasc;
    private RadioGroup mSexo;
    private EditText mCelular;
    private EditText mEscolaridade;
    private RadioGroup mDinamicaManual;
    private EditText mProfissao;
    private EditText mLinguaMaterna;
    private EditText mOutrasLinguas;

    private static final Calendar calendar = Calendar.getInstance();
    private static Date dobDate;
    private static SimpleDateFormat dateFormat;
    private static String dateFormatted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_participante);

        mNomeCompleto = (EditText) findViewById(R.id.input_participante_nome_completo);
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
        mLinguaMaterna = (EditText) findViewById(R.id.input_participante_idioma_materno);
        mOutrasLinguas = (EditText) findViewById(R.id.input_participante_outros_idiomas);

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

}
