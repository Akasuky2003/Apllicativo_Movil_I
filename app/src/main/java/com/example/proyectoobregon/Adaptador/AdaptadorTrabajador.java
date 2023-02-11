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

import com.example.proyectoobregon.Entidades.Trabajadores;
import com.example.proyectoobregon.R;

import java.util.List;

public class AdaptadorTrabajador extends ArrayAdapter<Trabajadores> {
    Context mi_contexto;
    int mi_layout;
    List<Trabajadores> mi_lista;

    public AdaptadorTrabajador(@NonNull Context context, int resource, @NonNull List<Trabajadores> objects) {
        super(context, resource,objects);
        mi_contexto = context;
        mi_layout = resource;
        mi_lista = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mi_vista = LayoutInflater.from(mi_contexto).inflate(mi_layout, null);

        TextView codigo=mi_vista.findViewById(R.id.tvCod_trabajadores);
        TextView nombres=mi_vista.findViewById(R.id.tvNombresTrabajadores);
        TextView apellidoPaterno=mi_vista.findViewById(R.id.tvApellidoPaternoTrabajadores);
        TextView apellidoMaterno=mi_vista.findViewById(R.id.tvApellidoMaterno);
        TextView dni=mi_vista.findViewById(R.id.tvDniTrabajador);
        TextView direcion=mi_vista.findViewById(R.id.tvDirecionTrabajador);
        TextView telefono=mi_vista.findViewById(R.id.tvTelefonoTrabajador);
        TextView correo=mi_vista.findViewById(R.id.tvCorreoTrabajador);
        TextView cargo=mi_vista.findViewById(R.id.tvCargotrabajador);
        TextView genero=mi_vista.findViewById(R.id.tvSexoTrabajador);
        ImageView imagen = mi_vista.findViewById(R.id.lvimgTrabajador);

        Trabajadores obj = mi_lista.get(position);

        codigo.setText("codigo: "+obj.getCod_trabajadores());
        nombres.setText("nombres: "+obj.getNombres());
        apellidoPaterno.setText("Apellido Paterno: "+obj.getApellido_paterno());
        apellidoMaterno.setText("Apellido Materno: "+obj.getApellido_materno());
        dni.setText("Numero DNI: "+obj.getDni());
        direcion.setText("Direcion: "+obj.getDireccion());
        telefono.setText("Telefono: "+obj.getCelular());
        correo.setText("Correo: "+obj.getCorreo());
        cargo.setText("Cargo: "+obj.getCargo());
        genero.setText("Genero: "+obj.getSexo());
        imagen.setImageResource(obtenerImagen(obj.getCod_trabajadores()));


        return  mi_vista;
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
