package com.example.proyectoobregon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.proyectoobregon.Controlador.PagerController;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class ClientesActivity extends AppCompatActivity {

    TabLayout tabLayoutCliente;
    ViewPager viewPagerCliente;
    TabItem tab1, tab2, tab3;
    PagerController pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        EnlazarControles();

        pagerAdapter = new PagerController(getSupportFragmentManager(), tabLayoutCliente.getTabCount());
        viewPagerCliente.setAdapter(pagerAdapter);
        tabLayoutCliente.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerCliente.setCurrentItem(tab.getPosition());
                if (tab.getPosition()==0){
                    pagerAdapter.notifyDataSetChanged();
                }
                if (tab.getPosition()==1){
                    pagerAdapter.notifyDataSetChanged();
                }
                if (tab.getPosition()==2){
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPagerCliente.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutCliente));

    }



    private void EnlazarControles() {
        tabLayoutCliente = findViewById(R.id.tablayoutCliente);
        viewPagerCliente = findViewById(R.id.viewpagerCliente);
        tab1 = findViewById(R.id.tabListadoCliente);
        tab2 = findViewById(R.id.tabAgregarCliente);
        tab3 = findViewById(R.id.tabModificarCliente);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_obregon,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i = null;
        switch (item.getItemId())
        {

            case R.id.listarJuego:
                i = new Intent(ClientesActivity.this , MenuPrincipalActivity.class);
                break;
        }
        if (i !=null)
            startActivity(i);

        return true;
    }
}