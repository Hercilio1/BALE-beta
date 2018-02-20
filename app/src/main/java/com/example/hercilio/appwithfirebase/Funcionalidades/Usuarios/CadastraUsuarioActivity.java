package com.example.hercilio.appwithfirebase.Funcionalidades.Usuarios;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hercilio.appwithfirebase.Objetos.UserDados;
import com.example.hercilio.appwithfirebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hercilio on 19/02/2018.
 */

public class CadastraUsuarioActivity extends AppCompatActivity {

    private EditText mNomeUsuario;
    private EditText mEmail;
    private EditText mSenha, mConfirmSenha;
    private Button mBtnCadastrar;
    private CheckBox mAdmin;
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        mNomeUsuario = (EditText) findViewById(R.id.input_nome_usuario);
        mEmail = (EditText) findViewById(R.id.input_email);
        mSenha = (EditText) findViewById(R.id.input_senha);
        mConfirmSenha = (EditText) findViewById(R.id.input_confirm_senha);
        mAdmin = (CheckBox) findViewById(R.id.checkBox_usuario_admin);

        mBtnCadastrar = (Button) findViewById(R.id.btn_usuario_register);

        mBtnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verficaSenha(mSenha.getText().toString(), mConfirmSenha.getText().toString()))
                    cadatrarUsuario();

                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                Log.d("APÓS REGISTRAR =>", "id do databaseReference  ==> " + mAuth.getCurrentUser().getUid());

            }
        });
    }

    public void cadatrarUsuario(){

        String email = mEmail.getText().toString();
        String password = mSenha.getText().toString();


        CreateRequest request = new CreateRequest()
                .setEmail("user@example.com")
                .setEmailVerified(false)
                .setPassword("secretPassword")
                .setPhoneNumber("+11234567890")
                .setDisplayName("John Doe")
                .setPhotoUrl("http://www.example.com/12345678/photo.png")
                .setDisabled(false);

        Task<UserRecord> task = FirebaseAuth.getInstance().createUser(request)
                .addOnSuccessListener(userRecord -> {
                    // See the UserRecord reference doc for the contents of userRecord.
                    System.out.println("Successfully created new user: " + userRecord.getUid());
                })
                .addOnFailureListener(e -> {
                    System.err.println("Error creating new user: " + e.getMessage());
                });

        UserDados userDados = new UserDados(mNomeUsuario.getText().toString(), "0 Participantes", mAdmin.isChecked());

        DatabaseReference userRef = rootRef.child("users/" + task.getResult().getUser().getUid());
        userRef.child("UserDados").setValue(userDados);


        FirebaseAuth auth = FirebaseAuth.getInstance();
        Log.d("Após operação =>", "id do databaseReference  ==> " + auth.getCurrentUser().getUid());

    }

    public boolean verficaSenha(String senha1, String senha2) {
        if(!senha1.equals(senha2)) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            alert.setTitle("Senhas não conferem!");
            alert.setMessage("Favor redigitar a senha em ambos campos.");

            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });

            AlertDialog dialog = alert.create();
            alert.show();
            return false;
        } else
            return true;
    }
}
