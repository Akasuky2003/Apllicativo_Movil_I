package com.example.proyectoobregon.Controlador;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoobregon.Adaptador.AdaptadorCliente;
import com.example.proyectoobregon.ClientesActivity;
import com.example.proyectoobregon.Dao.ClientesDAO;
import com.example.proyectoobregon.Dao.ClientesbdDao;
import com.example.proyectoobregon.Database.AppDB;
import com.example.proyectoobregon.DetalleClienteActivity;
import com.example.proyectoobregon.Entidades.Clientes;
import com.example.proyectoobregon.R;
import com.example.proyectoobregon.ToastPersonalizado;

import java.util.ArrayList;
import java.util.List;


public class ModificarCliente extends Fragment {

    ListView lvModificarClientes;
    Object posicion = null;
    View vista;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ModificarCliente() {
        // Required empty public constructor
    }


    public static ModificarCliente newInstance(String param1, String param2) {
        ModificarCliente fragment = new ModificarCliente();
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


        vista = inflater.inflate(R.layout.fragment_modificar_cliente, container, false);
        lvModificarClientes = vista.findViewById(R.id.lvModClientes);



        List<Clientes> lista =
                AppDB.getInstancia(ModificarCliente.this.getContext())
                        .clientesbdDao().ListarClientes();

        AdaptadorCliente adapter = new AdaptadorCliente(
                ModificarCliente.this.getContext(),
                R.layout.diseno_fila_clientes,
                lista
        );


        lvModificarClientes.setAdapter(adapter);



        lvModificarClientes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {

                posicion = lvModificarClientes.getItemAtPosition(position);

                int codigocliente = lista.get(position).getCod_cliente();

                Intent intent = new Intent(new Intent(ModificarCliente.this.getContext(),
                        DetalleClienteActivity.class));
                intent.putExtra("codigo", String.valueOf(codigocliente));
                intent.putExtra("nombre", String.valueOf(lista.get(position).getNombres()));
                intent.putExtra("correo", String.valueOf(lista.get(position).getCorreo()));
                intent.putExtra("direccion", String.valueOf(lista.get(position).getDireccion()));
                intent.putExtra("celular", String.valueOf(lista.get(position).getCelular()));
                intent.putExtra("dni", String.valueOf(lista.get(position).getDni()));

                startActivity(intent);


                return false;

            }
        });


        return vista;
    }

    @Override
    public void onResume() {
        super.onResume();

        List<Clientes> lista =
                AppDB.getInstancia(ModificarCliente.this.getContext())
                        .clientesbdDao().ListarClientes();

        AdaptadorCliente adapter = new AdaptadorCliente(
                ModificarCliente.this.getContext(),
                R.layout.diseno_fila_clientes,
                lista
        );

        lvModificarClientes.setAdapter(adapter);

    }
}