package com.example.proyectoobregon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.proyectoobregon.Controlador.ProductoController;
import com.example.proyectoobregon.Entidades.Producto;
import com.example.proyectoobregon.Fragment.ActivityInterface;
import com.example.proyectoobregon.Fragment.ModificarProducto;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class ProductoActivity extends AppCompatActivity implements ActivityInterface
{
    private TabLayout tablayoutProducto;
    private ViewPager viewpagerProducto;
    private TabItem tab1, tab2, tab3;
    private ProductoController pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        enlazarControles();
        //Toast.makeText(this, this + "", Toast.LENGTH_SHORT).show();

        pagerAdapter = new ProductoController(getSupportFragmentManager(), tablayoutProducto.getTabCount(), this);

        viewpagerProducto.setAdapter(pagerAdapter);

        tablayoutProducto.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewpagerProducto.setCurrentItem(tab.getPosition());
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

        viewpagerProducto.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayoutProducto));

    }

    private void enlazarControles()
    {
        tablayoutProducto = findViewById(R.id.tablayoutProducto);
        viewpagerProducto = findViewById(R.id.viewpagerProducto);
        tab1 = findViewById(R.id.tabListadoProducto);
        tab2 = findViewById(R.id.tabAgregarProducto);
        tab3 = findViewById(R.id.tabModificarProducto);
    }


    @Override
    public void moverActivity(Producto producto)
    {
        viewpagerProducto.setCurrentItem(2);
        ModificarProducto modificarProducto1 = pagerAdapter.getModificarProducto();
        modificarProducto1.llenarCamposActualizar(producto);
    }

    public void agregarRecargarListadoProducto()
    {
        viewpagerProducto.setCurrentItem(0);
        pagerAdapter.getListarProducto().llenarListView();
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
                i = new Intent(ProductoActivity.this , MenuPrincipalActivity.class);
                break;
        }
        if (i !=null)
            startActivity(i);

        return true;
    }


}