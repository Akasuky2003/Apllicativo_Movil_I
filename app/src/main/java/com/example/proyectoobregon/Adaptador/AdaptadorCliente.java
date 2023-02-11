package com.example.proyectoobregon.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proyectoobregon.Controlador.ListadoClientes;
import com.example.proyectoobregon.Entidades.Clientes;
import com.example.proyectoobregon.R;

import java.util.List;

public class AdaptadorCliente extends ArrayAdapter<Clientes> {

    Context mi_contexto;
    int mi_layout;
    List<Clientes> mi_lista;



    public AdaptadorCliente(@NonNull Context context,
                            int resource,
                            @NonNull List<Clientes> objects) {
        super(context, resource, objects);

        //
        mi_contexto = context;
        mi_layout = resource;
        mi_lista = objects;
    }


    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent){

        View mi_vista = LayoutInflater.from(mi_contexto).inflate(mi_layout, null);

        TextView nombre = mi_vista.findViewById(R.id.tvNombreCliente);
        TextView codigo = mi_vista.findViewById(R.id.tvCodigoCliente);
        TextView direccion = mi_vista.findViewById(R.id.tvDireccionCliente);
        TextView celular = mi_vista.findViewById(R.id.tvCelCliente);
        TextView dni = mi_vista.findViewById(R.id.tvDniCliente);
        TextView correo = mi_vista.findViewById(R.id.tvCorreoCliente);
        ImageView imagen = mi_vista.findViewById(R.id.imgCliente);

        Clientes obj = mi_lista.get(position);


        nombre.setText(obj.getNombres());
        codigo.setText("Codigo: CL" + obj.getCod_cliente());
        direccion.setText("Direccion: "+ obj.getDireccion());
        celular.setText("Celular: "+ obj.getCelular());
        dni.setText("Dni: "+ obj.getDni());
        correo.setText("Correo: "+ obj.getCorreo());
        imagen.setImageResource(obtenerImagen(obj.getCod_cliente()));


        return mi_vista;
    }

    int obtenerImagen(int codigo)
    {
        String recurso = "drawable";
        String paquete = mi_contexto.getPackageName();
        String nombre = "recurso"+codigo;
        //
        int rpta = mi_contexto.getResources()
                .getIdentifier(nombre, recurso, paquete);
        //
        return rpta;
    }


}
