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

import com.example.proyectoobregon.Entidades.Login;
import com.example.proyectoobregon.R;

import java.util.List;

public class AdaptadorUsuario extends ArrayAdapter<Login>
{
    Context mi_contexto;
    int mi_layout;
    List<Login> lista;

    public AdaptadorUsuario(@NonNull Context context, int resource, @NonNull List<Login> objects)
    {
        super(context, resource, objects);

        mi_contexto = context;
        mi_layout = resource;
        lista = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View mi_vista = LayoutInflater.from(mi_contexto).inflate(mi_layout, null);

        TextView codigo = mi_vista.findViewById(R.id.tvCodigoUsuario);
        TextView usuario = mi_vista.findViewById(R.id.tvUserUsuario);
        TextView password = mi_vista.findViewById(R.id.tvPasswordUsuario);
        ImageView imagen = mi_vista.findViewById(R.id.imgUsuario);

        Login login = lista.get(position);

        codigo.setText("Código: " + login.getCod_usuario());
        usuario.setText("Usuario: " + login.getUsuario());
        password.setText("Contraseña: " + login.getContrasenia());
        imagen.setImageResource(R.drawable.logo);

        return mi_vista;
    }
}
