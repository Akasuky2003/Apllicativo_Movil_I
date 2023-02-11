package com.example.proyectoobregon.Controlador;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerControllerTrabajadores extends FragmentPagerAdapter {
    int numoftabs;

    public PagerControllerTrabajadores(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numoftabs = behavior;
    }
    //Trabajador

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return  new ListadoTrabajadorFragment();
            case 1:
                return  new AgregarTrabajadorFragment();
            case 2:
                return  new ModificarTrabajadorFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numoftabs;
    }
}
