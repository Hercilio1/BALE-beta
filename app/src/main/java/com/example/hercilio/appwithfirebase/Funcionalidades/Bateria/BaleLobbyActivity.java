package com.example.hercilio.appwithfirebase.Funcionalidades.Bateria;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.hercilio.appwithfirebase.R;

/**
 * Created by Hercilio on 26/12/2017.
 */

public class BaleLobbyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bale_lobby);

    }

    private class ClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            Intent intent = new Intent(getApplicationContext(), HabitosLeituraEscritaActivity.class);
            startActivity(intent);
//            switch (view.getId())
//            {
//
//                //handle multiple view click events
//            }
        }
    }

//    @Override
//    public void onClick(View view) {
//
////        switch (view.getId())
////        {
////            case 5 :
////                break;
////            default:
////                break;
////        }
//    }
}
