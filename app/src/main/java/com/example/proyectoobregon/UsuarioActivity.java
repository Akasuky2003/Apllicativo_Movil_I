package com.example.proyectoobregon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.proyectoobregon.Controlador.UsuarioController;
import com.example.proyectoobregon.Entidades.Login;
import com.example.proyectoobregon.Fragment.ModificarUsuario;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class UsuarioActivity extends AppCompatActivity {


    private TabLayout tablayoutUsuario;
    private ViewPager viewpagerUsuario;
    private TabItem tab1, tab2, tab3;
    private UsuarioController pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        enlazarControles();

        pagerAdapter = new UsuarioController(getSupportFragmentManager(), tablayoutUsuario.getTabCount());

        viewpagerUsuario.setAdapter(pagerAdapter);

        tablayoutUsuario.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpagerUsuario.setCurrentItem(tab.getPosition());
                if (tab.getPosition()==0){
                    //ToastPersonalizado.showToastLock(ProductoActivity.this, "Se Registrò un Nuevo Producto");
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

        viewpagerUsuario.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayoutUsuario));
    }

    private void enlazarControles()
    {
        tablayoutUsuario = findViewById(R.id.tablayoutUsuario);
        viewpagerUsuario = findViewById(R.id.viewpagerUsuario);
        tab1 = findViewById(R.id.tabListadoProducto);
        tab2 = findViewById(R.id.tabAgregarProducto);
        tab3 = findViewById(R.id.tabModificarProducto);
    }


    public void moverActivity(Login login)
    {
        viewpagerUsuario.setCurrentItem(2);
        ModificarUsuario modificarUsuario = pagerAdapter.getModificarUsuario();
        modificarUsuario.llenarCamposActualizar(login);

    }

    public void agregarRecargarListadoUsuario()
    {
        viewpagerUsuario.setCurrentItem(0);
        pagerAdapter.getListarUsuario().llenarListView();
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
                i = new Intent(UsuarioActivity.this , MenuPrincipalActivity.class);
                break;
        }
        if (i !=null)
            startActivity(i);

        return true;
    }
}