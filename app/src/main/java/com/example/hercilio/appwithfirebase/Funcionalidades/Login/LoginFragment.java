package com.example.hercilio.appwithfirebase.Funcionalidades.Login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hercilio.appwithfirebase.AdminActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Pesquisas.PesquisasAdapter;
import com.example.hercilio.appwithfirebase.Funcionalidades.Usuarios.CadastraUsuarioActivity;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.Objetos.UserDados;
import com.example.hercilio.appwithfirebase.R;
import com.example.hercilio.appwithfirebase.UsersActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginFragment extends Fragment {

    public LoginFragment() {
    }

    private Button entrarButton;
    private EditText emailEditText;
    private EditText senhaEditText;
    private TextView esqueceuSenhaTextView;
    private FirebaseAuth mAuth;
    private final String  TAG = "ERRO - Login: ";

    private boolean adminLoginConfirm;

    private ProgressBar progressBar;
    private ProgressDialog mProgress;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Método que verifica se, ao logar, o usuário está logado ou não.
     */
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    /**
     * Instancia objetos dos layout fragment_login e cria as rotinas
     * de clique nos botões do layout.
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        emailEditText = (EditText) view.findViewById(R.id.email);
        senhaEditText = (EditText) view.findViewById(R.id.password);
        entrarButton = (Button) view.findViewById(R.id.email_sign_in_button);
        esqueceuSenhaTextView = (TextView) view.findViewById(R.id.text_forgotten_password);
        progressBar = (ProgressBar) view.findViewById(R.id.login_progress);

        mProgress = new ProgressDialog(getContext());
        mProgress.setTitle("Processando...");
        mProgress.setMessage("Por favor, espere...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);


        entrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });

        FirebaseUser currentUser = mAuth.getCurrentUser();

        //Caso o usuário seja admin ele deve preencher o cabeçalho de login tada vez
//        if(currentUser != null) {
//            adminLoginConfirm = true;
//        }

        //Rotina para recuperar senha:
        if(currentUser == null) {
            esqueceuSenhaTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doForgotPassword();
                }
            });
        }
    }

    /**
     * Realiza a autenticação do login do usuário.
     */
    private void doLogin() {
        try {
            final String email = emailEditText.getText().toString();
            final String senha = senhaEditText.getText().toString();
            mProgress.show();
            if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(senha)) {
                mAuth.signInWithEmailAndPassword(email, senha)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(getActivity(), "Falha no Login.",
                                            Toast.LENGTH_SHORT).show();
                                    mProgress.dismiss();
                                    updateUI(null);
                                }
                            }
                        });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    /**
     * Realiza recuperação de senha.
     */
    public void doForgotPassword() {
        final Dialog dialogForgotPassword = new Dialog(this.getActivity());
        dialogForgotPassword.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogForgotPassword.setCancelable(true);
        dialogForgotPassword.setContentView(R.layout.dialog_esqueceu_senha);

        Button recuperarSenhaButton = (Button) dialogForgotPassword.findViewById(R.id.recuperar_senha_button);
        recuperarSenhaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Adicionar chamada backend
                dialogForgotPassword.dismiss();
            }
        });
        dialogForgotPassword.show();
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            mProgress.show();
            verificaAdmin();
        }
    }


    public void verificaAdmin(){
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        DatabaseReference mParticipanteDatabaseReference = mFirebaseDatabase.getReference().child("users").child(auth.getCurrentUser().getUid());

        mParticipanteDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.getKey().equals("UserDados")) {
                    UserDados userDados = dataSnapshot.getValue(UserDados.class);
                    if (userDados.isAdmin()) {
                        FirebaseUser currentUser = mAuth.getCurrentUser();
                        if(adminLoginConfirm) {
                            adminLoginConfirm = false;
                            mAuth.signOut();
                            mProgress.dismiss();

                            AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

                            alert.setTitle("Atenção");
                            alert.setMessage("Este dispositivo estava conectado a uma conta Admin. Por favor, faça o Login novamente!");

                            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });

                            AlertDialog dialog = alert.create();
                            alert.show();
                            return;
                        }
                        String[] auxKeyAdmin = new String[1];
                        auxKeyAdmin[0] = senhaEditText.getText().toString();
                        Intent intent = new Intent(getActivity().getBaseContext(), AdminActivity.class);
                        intent.putExtra(CadastraUsuarioActivity.EXTRA_ADMIN_USER, auxKeyAdmin);
                        startActivity(intent);
                        return;
                    } else {
                        Intent intent = new Intent(getActivity(), UsersActivity.class);
                        startActivity(intent);
                    }
                }
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

    }

}