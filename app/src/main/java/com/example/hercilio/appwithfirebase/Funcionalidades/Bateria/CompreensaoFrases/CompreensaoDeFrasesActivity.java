package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.CompreensaoFrases;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.hercilio.appwithfirebase.Objetos.Pesquisa;
import com.example.hercilio.appwithfirebase.R;

import java.util.ArrayList;

/**
 * Created by Hercilio on 31/01/2018.
 */

public class CompreensaoDeFrasesActivity extends AppCompatActivity {
    // Hold a reference to the current animator,
    // so that it can be canceled mid-way.
    private Animator mCurrentAnimator;

    // The system "short" animation time duration, in milliseconds. This
    // duration is ideal for subtle animations or animations that occur
    // very frequently.
    private int mShortAnimationDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compreensao_frases);

    }

    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), FraseExpandidaActivity.class);
        startActivity(intent);
    }

}
