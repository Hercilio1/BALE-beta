package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoFrases;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyActivity;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.example.hercilio.appwithfirebase.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

/**
 * Created by 14202151 on 18/05/2017.
 */

public class CompreensaoDeFrasesRelogioActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mFotoRelogioStoregeReference;
    private DatabaseReference mParticipanteDatabaseReference;

    private ProgressDialog progressDialog;
    private Button mBtnContinuar;
    private byte [] bImg;

    private static final int CAMERA_REQUEST = 1888;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compreensao_frases_relogio);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseStorage = FirebaseStorage.getInstance();

        mImageView = (ImageView) this.findViewById(R.id.image_from_camera);
        mBtnContinuar = (Button) findViewById(R.id.btn_continuarRelogio);

        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);

            boolean isPhoto = participante.getCompFrasesObject().getFotoRelogio() != null;
            if(isPhoto) {
                autoComplete(participante);
            }

            mBtnContinuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                if(participante.getCompFrasesObject().getFotoRelogio() != null) {
                    atualizaPorcentagem(participante);
                    registrar(participante);
                } else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());

                    alert.setTitle("Atenção");
                    alert.setMessage("A imagem não foi armazena no Banco de Dados. Por favor, espere mais alguns instantes e continue!");

                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });

                    AlertDialog dialog = alert.create();
                    alert.show();
                }

                }
            });

            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(participante.getCompFrasesObject().getFotoRelogio() != null) {
                        Intent intent = new Intent(getBaseContext(), ImagemExpandidaCompFrasesActivity.class);
                        intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
                        startActivity(intent);
                    } else {
                        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());

                        alert.setTitle("Atenção");
                        alert.setMessage("A imagem não foi armazena no Banco de Dados. Por favor, espere mais alguns instantes e continue!");

                        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });

                        AlertDialog dialog = alert.create();
                        alert.show();
                    }
                }
            });

        }
    }

    public void autoComplete(Participante participante) {
        Glide.with(mImageView.getContext()).load(participante.getCompFrasesObject().getFotoRelogio()).into(mImageView);
    }

    public void registrar(Participante participante) {
        Intent intent = new Intent(this, BaleLobbyActivity.class);
        intent.putExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE, participante);
        startActivity(intent);
    }

    public void takeImageFromCamera(View view) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            mImageView.setImageBitmap(mphoto);
            //Transforma a imagem em vetor de byte
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            mphoto.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
            bImg = outputStream.toByteArray();

            Intent intentFromList = getIntent();
            if (intentFromList != null) {
                final Participante participante = (Participante) intentFromList.getSerializableExtra(BaleLobbyActivity.EXTRA_PARTICIPANTE);
                if (!participante.isFinalizado()) {
                    final FirebaseAuth auth = FirebaseAuth.getInstance();
                    mFotoRelogioStoregeReference = mFirebaseStorage.getReference().child("users").child(auth.getCurrentUser().getUid()).child("participantes").child(participante.getCpf()).child("foto_relogio");
                    mParticipanteDatabaseReference = mFirebaseDatabase.getReference().child("users").child(auth.getCurrentUser().getUid()).child("participantes");

                    Uri selectedImageUri = data.getData();
                    mFotoRelogioStoregeReference.putBytes(bImg).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            participante.getCompFrasesObject().setFotoRelogio(downloadUrl.toString());
                            mParticipanteDatabaseReference.child(participante.getCpf()).setValue(participante);
                            return;
                        }
                    });
                }
            }
        }
    }

    public void atualizaPorcentagem(Participante participante) {
        int numeroDeVerf = 3;
        int numeroDeVerfConcluidos = 0;

        if(participante.getCompFrasesObject().getValorFinal1() >=0) {
            numeroDeVerfConcluidos += 1;
        }
        if(participante.getCompFrasesObject().getValorFinal2() >=0) {
            numeroDeVerfConcluidos += 1;
        }
        if(participante.getCompFrasesObject().getFotoRelogio() != null) {
            numeroDeVerfConcluidos += 1;
        }

        int porcentagem = (100*numeroDeVerfConcluidos)/numeroDeVerf;

        participante.getCompFrasesObject().setPorcentagem(porcentagem);

        FirebaseDatabase mFirebaseDatabase;
        final DatabaseReference mParticipanteDatabaseReference;

        //Cria o caminho que garantirá o acesso somente aos participantes do usuário logado
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        mParticipanteDatabaseReference = mFirebaseDatabase.getReference().child("users").child(auth.getCurrentUser().getUid()).child("participantes");
        mParticipanteDatabaseReference.child(participante.getCpf()).setValue(participante);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            this.onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}







