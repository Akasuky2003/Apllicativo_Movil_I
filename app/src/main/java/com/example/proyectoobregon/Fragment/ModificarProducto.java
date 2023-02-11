package com.example.proyectoobregon.Fragment;

import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.proyectoobregon.Database.AppDB;
import com.example.proyectoobregon.Entidades.Producto;
import com.example.proyectoobregon.ProductoActivity;
import com.example.proyectoobregon.R;
import com.example.proyectoobregon.ToastPersonalizado;

import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;


public class ModificarProducto extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ModificarProducto() {
        // Required empty public constructor
    }


    public static ModificarProducto newInstance(String param1, String param2) {
        ModificarProducto fragment = new ModificarProducto();
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

    private EditText edtIngresarNombreProductoEditar, edtIngresarCategoriaEditar, edtIngresarMarcaEditar, edtIngresarStockEditar, edtIngresarPrecioEditar;
    private Button btnCancelarProducto, btnActualizarProducto;
    private TextView tvCodigoProductoEditar;
    private Producto productoActualizar = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_modificar_producto2, container, false);
        enlazarControles(vista);
        btnCancelarProducto.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                limpiarCampos();
                //ToastPersonalizado.showToastLock(getContext(), "Se limpiaron los campos");
                String text = "Se Limpiaron los Campos";
                String title = "INFO";

                MotionToast.Companion.darkToast(getActivity(),title,text,
                        MotionToastStyle.WARNING,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(getContext(), www.sanju.motiontoast.R.font.helvetica_regular));
            }
        });

        btnActualizarProducto.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (productoActualizar == null)
                {
                    String text = "No hay un producto seleccionado del listado!";
                    String title = "ALERTA";
                    //ToastPersonalizado.showToastLock(getContext(), "No hay un producto seleccionado del listado!");

                    MotionToast.Companion.darkToast(getActivity(),title,text,
                            MotionToastStyle.ERROR,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(getContext(), www.sanju.motiontoast.R.font.helvetica_regular));
                    return;
                }
                else if (!validarCampos()) return;


                /*AppDB.getInstancia(getContext()).productoDAO().updateProductoQuery(productoActualizar.getCod_producto(), productoActualizar.getNombre(), productoActualizar.getCategoria(),
                                                                              productoActualizar.getMarca(), productoActualizar.getStock(), productoActualizar.getPrecio());*/
                AppDB.getInstancia(getContext()).productoDAO().updateProductoQuery(productoActualizar.getCod_producto(), edtIngresarNombreProductoEditar.getText().toString(), edtIngresarCategoriaEditar.getText().toString(),
                        edtIngresarMarcaEditar.getText().toString(), Integer.parseInt(edtIngresarStockEditar.getText().toString()), Double.parseDouble(edtIngresarPrecioEditar.getText().toString()));
                ((ProductoActivity)getActivity()).agregarRecargarListadoProducto();


                String text = "Se actualizo correctamente";
                String title = "Operacion Exitosa";

                MotionToast.Companion.darkToast(getActivity(),title,text,
                        MotionToastStyle.SUCCESS,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(getContext(), www.sanju.motiontoast.R.font.helvetica_regular));

                //ToastPersonalizado.showToastLock(getContext(), "Se actualizo correctamente");
                limpiarCampos();
            }
        });

        return vista;
    }

    private void enlazarControles(View vistape)
    {
        tvCodigoProductoEditar = vistape.findViewById(R.id.tvCodigoProductoEditar);
        edtIngresarNombreProductoEditar = vistape.findViewById(R.id.edtIngresarNombreProductoEditar);
        edtIngresarCategoriaEditar = vistape.findViewById(R.id.edtIngresarCategoriaEditar);
        edtIngresarMarcaEditar = vistape.findViewById(R.id.edtIngresarMarcaEditar);
        edtIngresarStockEditar = vistape.findViewById(R.id.edtIngresarStockEditar);
        edtIngresarPrecioEditar = vistape.findViewById(R.id.edtIngresarPrecioEditar);
        btnCancelarProducto = vistape.findViewById(R.id.btnCancelarProducto);
        btnActualizarProducto = vistape.findViewById(R.id.btnActualizarProducto);
    }

    /*
     * Esto servirá para llenar los campos de Modificar Producto según el cliente cuando elige el producto a modificar en el listado de Productos.
     */

    public void llenarCamposActualizar(Producto producto)
    {
        tvCodigoProductoEditar.setText("Código: " + producto.getCod_producto() + "");
        edtIngresarNombreProductoEditar.setText(producto.getNombre());
        edtIngresarCategoriaEditar.setText(producto.getCategoria());
        edtIngresarMarcaEditar.setText(producto.getMarca());
        edtIngresarStockEditar.setText(producto.getStock()+"");
        edtIngresarPrecioEditar.setText(producto.getPrecio()+"");
        productoActualizar = producto;
    }

    private void limpiarCampos()
    {
        tvCodigoProductoEditar.setText("Código: ");
        edtIngresarNombreProductoEditar.setText("");
        edtIngresarCategoriaEditar.setText("");
        edtIngresarMarcaEditar.setText("");
        edtIngresarStockEditar.setText("");
        edtIngresarPrecioEditar.setText("");
        productoActualizar = null;
    }

    private boolean validarCampos()
    {
        boolean retorno = true;

        String nombreProducto = edtIngresarNombreProductoEditar.getText().toString().trim();
        String categoriaProducto = edtIngresarCategoriaEditar.getText().toString().trim();
        String marcaProducto = edtIngresarMarcaEditar.getText().toString().trim();
        String stockProducto = edtIngresarStockEditar.getText().toString().trim();
        String precioProducto = edtIngresarPrecioEditar.getText().toString().trim();

        edtIngresarNombreProductoEditar.setError(null);
        edtIngresarCategoriaEditar.setError(null);
        edtIngresarMarcaEditar.setError(null);
        edtIngresarStockEditar.setError(null);
        edtIngresarPrecioEditar.setError(null);

        if (nombreProducto.equals(""))
        {
            edtIngresarNombreProductoEditar.setError("Por favor, ingrese el nombre del producto");
            retorno = false;
        }
        if (categoriaProducto.equals(""))
        {
            edtIngresarCategoriaEditar.setError("Por favor, ingrese la categoria del producto");
            retorno = false;
        }
        if (marcaProducto.equals(""))
        {
            edtIngresarMarcaEditar.setError("Por favor, ingrese la marca del producto");
            retorno = false;
        }
        if (stockProducto.equals(""))
        {
            edtIngresarStockEditar.setError("Por favor, ingrese la stock del producto");
            retorno = false;
        }
        if (precioProducto.equals(""))
        {
            edtIngresarPrecioEditar.setError("Por favor, ingrese la precio del producto");
            retorno = false;
        }

        return retorno;
    }
}