package com.example.proyectoobregon.Controlador;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.proyectoobregon.Fragment.AgregarUsuario;
import com.example.proyectoobregon.Fragment.ListarUsuario;
import com.example.proyectoobregon.Fragment.ModificarUsuario;

public class UsuarioController extends FragmentPagerAdapter
{
    int numoftabs;

    private ListarUsuario listarUsuario = new ListarUsuario();
    private AgregarUsuario agregarUsuario = new AgregarUsuario();
    private ModificarUsuario modificarUsuario = new ModificarUsuario();

    public UsuarioController(@NonNull FragmentManager fm, int behavior)
    {
        super(fm, behavior);
        this.numoftabs = behavior;
    }

    //CLIENTES
    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return  listarUsuario;
            case 1:
                return  agregarUsuario;
            case 2:
                return  modificarUsuario;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numoftabs;
    }

    public ListarUsuario getListarUsuario() {
        return listarUsuario;
    }

    public AgregarUsuario getAgregarUsuario() {
        return agregarUsuario;
    }

    public ModificarUsuario getModificarUsuario() {
        return modificarUsuario;
    }
}
