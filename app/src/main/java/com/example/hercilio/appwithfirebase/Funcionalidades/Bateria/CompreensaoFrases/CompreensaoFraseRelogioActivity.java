package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoFrases;


import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.hercilio.appwithfirebase.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by 14202151 on 18/05/2017.
 */

public class CompreensaoFraseRelogioActivity extends AppCompatActivity {


    private ProgressDialog progressDialog;
    private Button mBtnContinuar;
    private byte [] bImg;

    private static final int CAMERA_REQUEST = 1888;
    ImageView mimageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compreensao_frases_relogio);

        mimageView = (ImageView) this.findViewById(R.id.image_from_camera);
        mBtnContinuar = (Button) findViewById(R.id.btn_continuarRelogio);
        mBtnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });
    }

    public void registrar() {
    }

    public void takeImageFromCamera(View view) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            mimageView.setImageBitmap(mphoto);
            //Transforma a imagem em vetor de byte
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            mphoto.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
            bImg = outputStream.toByteArray();
        }
    }
}







