package com.example.proyectoobregon.Controlador;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.proyectoobregon.Fragment.ActivityInterface;
import com.example.proyectoobregon.Fragment.AgregarProducto;
import com.example.proyectoobregon.Fragment.ListarProducto;
import com.example.proyectoobregon.Fragment.ModificarProducto;

public class ProductoController extends FragmentPagerAdapter {

    int numoftabs;
    ActivityInterface i;

    private ListarProducto listarProducto = new ListarProducto();
    private AgregarProducto agregarProducto = new AgregarProducto();
    private ModificarProducto modificarProducto = new ModificarProducto();

    public ProductoController(@NonNull FragmentManager fm, int behavior, ActivityInterface i)
    {
        super(fm, behavior);
        this.numoftabs = behavior;
        this.i = i;
        listarProducto.setInterface(i);
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        switch (position){
            case 0:
                return listarProducto;
            case 1:
                return  agregarProducto;
            case 2:
                return  modificarProducto;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numoftabs;
    }

    public ListarProducto getListarProducto() {
        return listarProducto;
    }

    public AgregarProducto getAgregarProducto() {
        return agregarProducto;
    }

    public ModificarProducto getModificarProducto() {
        return modificarProducto;
    }
}
