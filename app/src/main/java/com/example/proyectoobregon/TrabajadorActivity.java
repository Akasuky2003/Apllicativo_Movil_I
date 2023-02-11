package com.example.proyectoobregon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.proyectoobregon.Controlador.PagerControllerTrabajadores;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class TrabajadorActivity extends AppCompatActivity {

    TabLayout tabTrabajadores;
    ViewPager viewPagerTrabajador;
    TabItem tab1, tab2,tab3;
    PagerControllerTrabajadores pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabajador);

        EnlazarControles();

        pagerAdapter=new PagerControllerTrabajadores(getSupportFragmentManager(),tabTrabajadores.getTabCount());
        viewPagerTrabajador.setAdapter(pagerAdapter);

        tabTrabajadores.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerTrabajador.setCurrentItem(tab.getPosition());
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
        viewPagerTrabajador.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabTrabajadores));

    }
    private void EnlazarControles(){
        tabTrabajadores=findViewById(R.id.tablayoutTrabajador);
        viewPagerTrabajador=findViewById(R.id.viewpagerTrabajadoresA);
        tab1=findViewById(R.id.tabListadoTrabajadores);
        tab2=findViewById(R.id.tabAgregarTrabajadores);
        tab3=findViewById(R.id.tabModificarTrabajadores);
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
                i = new Intent(TrabajadorActivity.this , MenuPrincipalActivity.class);
                break;
        }
        if (i !=null)
            startActivity(i);

        return true;
    }


}