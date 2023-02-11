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

import com.example.proyectoobregon.Entidades.Producto;
import com.example.proyectoobregon.R;

import java.util.List;

public class AdaptadorProducto extends ArrayAdapter<Producto>
{
    Context mi_contexto;
    int mi_layout;
    List<Producto> mi_lista;

    public AdaptadorProducto(@NonNull Context context, int resource, @NonNull List<Producto> objects)
    {
        super(context, resource, objects);

        mi_contexto = context;
        mi_layout = resource;
        mi_lista = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        //return super.getView(position, convertView, parent);
        View mi_vista = LayoutInflater.from(mi_contexto).inflate(mi_layout, null);

        TextView codigo = mi_vista.findViewById(R.id.tvCodigoProducto);
        TextView stock = mi_vista.findViewById(R.id.tvStockProducto);
        TextView precio = mi_vista.findViewById(R.id.tvPrecioProducto);
        TextView categoria = mi_vista.findViewById(R.id.tvCategoriaProducto);
        TextView marca = mi_vista.findViewById(R.id.tvMarcaProducto);
        TextView nombre = mi_vista.findViewById(R.id.tvNombreProducto);
        ImageView imagen = mi_vista.findViewById(R.id.imgProducto);

        Producto producto = mi_lista.get(position);

        codigo.setText("Código: " + producto.getCod_producto());
        stock.setText("Stock: " + producto.getStock());
        precio.setText("Precio: " + producto.getPrecio());
        categoria.setText("Categoria: " + producto.getCategoria());
        marca.setText("Marca: " + producto.getMarca());
        nombre.setText(producto.getNombre().toUpperCase());
        //imagen.setImageResource(R.drawable.logo);
        imagen.setImageResource(obtenerImagen(producto.getCod_producto()));
        return mi_vista;
    }

    int obtenerImagen(int codigo)
    {
        String recurso = "drawable";
        String paquete = mi_contexto.getPackageName();
        String nombre = "producto"+codigo;
        //
        int rpta = mi_contexto.getResources()
                .getIdentifier(nombre, recurso, paquete);
        //
        return rpta;
    }

}
