package com.example.proyectoobregon.Controlador;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.proyectoobregon.Adaptador.AdaptadorTrabajador;
import com.example.proyectoobregon.Database.AppDB;
import com.example.proyectoobregon.DetalleTrabajadorActivity;
import com.example.proyectoobregon.Entidades.Trabajadores;
import com.example.proyectoobregon.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModificarTrabajadorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModificarTrabajadorFragment extends Fragment {

    ListView lvModifiCarTrabajador;
    Object posicion = null;
    View vista;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ModificarTrabajadorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ModificarTrabajadorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ModificarTrabajadorFragment newInstance(String param1, String param2) {
        ModificarTrabajadorFragment fragment = new ModificarTrabajadorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_modificar_trabajador, container, false);
        lvModifiCarTrabajador= vista.findViewById(R.id.lvModTrabajadores);



        List<Trabajadores> lista =
                AppDB.getInstancia(ModificarTrabajadorFragment.this.getContext())
                        .trabajadoresbdDao().ListarTrabajadores();

        AdaptadorTrabajador adapter=new AdaptadorTrabajador(
                ModificarTrabajadorFragment.this.getContext(),
                R.layout.diseno_fila_trabajadores,
                lista
        );


        lvModifiCarTrabajador.setAdapter(adapter);
        lvModifiCarTrabajador.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                posicion = lvModifiCarTrabajador.getItemAtPosition(position);
                int codigotrabajadores = lista.get(position).getCod_trabajadores();

                Intent intent = new Intent(new Intent(ModificarTrabajadorFragment.this.getContext(),
                        DetalleTrabajadorActivity.class));
                intent.putExtra("Codigo",String.valueOf(codigotrabajadores));
                intent.putExtra("Nombre",String.valueOf(lista.get(position).getNombres()));
                intent.putExtra("Apellido_Paterno",String.valueOf(lista.get(position).getApellido_paterno()));
                intent.putExtra("Apellido_Materno",String.valueOf(lista.get(position).getApellido_materno()));
                intent.putExtra("DNI",String.valueOf(lista.get(position).getDni()));
                intent.putExtra("Direccion",String.valueOf(lista.get(position).getDireccion()));
                intent.putExtra("Telefono",String.valueOf(lista.get(position).getCelular()));
                intent.putExtra("Cargo",String.valueOf(lista.get(position).getCargo()));
                intent.putExtra("Genero",String.valueOf(lista.get(position).getSexo()));
                startActivity(intent);
                return false;
            }
        });
        return vista;
    }
    @Override
    public void onResume() {
        super.onResume();

        List<Trabajadores> lista =
                AppDB.getInstancia(ModificarTrabajadorFragment.this.getContext())
                        .trabajadoresbdDao().ListarTrabajadores();

        AdaptadorTrabajador adapter = new AdaptadorTrabajador(
                ModificarTrabajadorFragment.this.getContext(),
                R.layout.diseno_fila_trabajadores,
                lista
        );

        lvModifiCarTrabajador.setAdapter(adapter);

    }
}