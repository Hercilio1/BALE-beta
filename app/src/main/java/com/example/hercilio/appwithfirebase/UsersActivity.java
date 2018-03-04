package com.example.hercilio.appwithfirebase;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hercilio.appwithfirebase.Funcionalidades.Login.LoginActivity;
import com.example.hercilio.appwithfirebase.Funcionalidades.Pesquisas.PesquisasFragment;
import com.example.hercilio.appwithfirebase.Funcionalidades.Usuarios.UsuariosFragment;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Hercilio on 14/12/2017.
 */

public class UsersActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private TabLayout mSlidingTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_user);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_normal_user);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container_normal_user);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        // Cria a barra de abas para as diferentes páginas
        mSlidingTabLayout = (TabLayout) findViewById(R.id.tab_examinador_normal_user);
        mSlidingTabLayout.setTabGravity(mSlidingTabLayout.GRAVITY_FILL);
        mSlidingTabLayout.setupWithViewPager(mViewPager);
        mSlidingTabLayout.getTabAt(0).setText(R.string.tab_pesquisas_em_andamento);
        mSlidingTabLayout.getTabAt(1).setText(R.string.tab_pesquisas_finalizadas);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_examinator_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        FirebaseAuth.getInstance().signOut();
        startActivity(intent);
        return true;
    }




    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return (position == 0 ? new PesquisasFragment() : new UsuariosFragment());
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
            }
            return null;
        }
    }
}
