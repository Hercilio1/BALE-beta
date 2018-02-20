package com.example.hercilio.appwithfirebase;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.hercilio.appwithfirebase.Funcionalidades.Bateria.Lobby.BaleLobbyFragment;
import com.example.hercilio.appwithfirebase.Funcionalidades.Login.LoginActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Pesquisas.PesquisasFragment;
import com.example.hercilio.appwithfirebase.Funcionalidades.Usuarios.CadastraUsuarioActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Usuarios.UsuariosFragment;
import com.example.hercilio.appwithfirebase.Objetos.Participante;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Hercilio on 14/12/2017.
 */

public class AdminActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    private static String keyAdmin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final String[] passwordAdmin = (String[]) intentFromList.getSerializableExtra(CadastraUsuarioActivity.EXTRA_ADMIN_USER);

            Intent intent = new Intent(this, CadastraUsuarioActivity.class);
            intent.putExtra(CadastraUsuarioActivity.EXTRA_ADMIN_USER, passwordAdmin);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_cadastrar_usuario:
                displayView(1);
                break;
            case R.id.nav_lista_usuarios:
                displayView(2);
                break;
            case R.id.nav_pesquisas:
                displayView(3);
                break;
            case R.id.nav_logout:
                displayView(4);
                break;
            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void displayView(int viewId) {

        String title = getString(R.string.app_name);

        Fragment fragment = null;

        switch (viewId) {
            case 1:
                Toast.makeText(this, "Cadastra o user", Toast.LENGTH_SHORT);
                break;
            case 2:
                fragment = new UsuariosFragment();
                title = "Usuários";
                break;
            case 3:
                fragment = new PesquisasFragment();
                title = "Pesquisas";
                break;
            case 4:
                finish();
                Intent intent = new Intent(this, LoginActivity.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(intent);
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame_menu, fragment, title);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


    }


    //    public void onListFragmentInteraction(Pesquisa item) {
//        Intent intent = new Intent(getApplicationContext(), BateriaActivity.class);
//        intent.putExtra(EXTRA_PK_PESQUISA, Integer.toString(item.getPk_pesquisa()));
//        intent.putExtra(EXTRA_PESQUISA, item);
//        // requestCode - int: If >= 0, this code will be returned in onActivityResult() when the activity exits
//        startActivityForResult(intent, BATERIA_REQUEST);
//    }
}
