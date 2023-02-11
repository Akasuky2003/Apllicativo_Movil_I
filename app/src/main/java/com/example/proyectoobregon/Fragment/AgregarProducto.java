package com.example.proyectoobregon.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectoobregon.Database.AppDB;
import com.example.proyectoobregon.Entidades.Producto;
import com.example.proyectoobregon.ProductoActivity;
import com.example.proyectoobregon.R;
import com.example.proyectoobregon.ToastPersonalizado;
import com.github.dhaval2404.imagepicker.ImagePicker;

import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgregarProducto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgregarProducto extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AgregarProducto() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgregarProducto.
     */
    // TODO: Rename and change types and number of parameters
    public static AgregarProducto newInstance(String param1, String param2) {
        AgregarProducto fragment = new AgregarProducto();
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

    private EditText edtIngresarNombreProducto, edtIngresarCategoria, edtIngresarMarca, edtIngresarStock, edtIngresarPrecio;
    private Button btnNuevoProducto, btnGrabarProducto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_agregar_producto, container, false);
        enlazarControles(root);



        btnGrabarProducto.setOnClickListener(this);
        btnNuevoProducto.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnGrabarProducto:
            {
                /*
                ImagePicker.with(this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();*/

                if (!validarCampos()){
                    String text = "Campos sin Completar";
                    String title = "ALERTA";
                    MotionToast.Companion.darkToast(getActivity(),title,text,
                            MotionToastStyle.ERROR,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(getContext(), www.sanju.motiontoast.R.font.helvetica_regular));
                    return;
                }

                AppDB.getInstancia(getContext()).productoDAO().insertProducto(producto);
                String text = "Se Registrò un Nuevo Producto";
                String title = "Operacion Exitosa";
                //ToastPersonalizado.showToastLock(getContext(), "Se Registrò un Nuevo Producto");
                MotionToast.Companion.darkToast(getActivity(),title,text,
                        MotionToastStyle.SUCCESS,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(getContext(), www.sanju.motiontoast.R.font.helvetica_regular));

                //startActivity(new Intent(getContext(), ProductoActivity.class)); // Provoca saturación ya que se llama al mismo activity cada vez que guardas
                ((ProductoActivity)getActivity()).agregarRecargarListadoProducto(); // mejor optimizado :)
                limpiar();
                break;
            }
            case R.id.btnNuevoProducto:
            {
                limpiar();

                String text = "Se Limpiaron los Campos Correctamente";
                String title = "INFO";

                MotionToast.Companion.darkToast(getActivity(),title,text,
                        MotionToastStyle.WARNING,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(getContext(), www.sanju.motiontoast.R.font.helvetica_regular));

                //ToastPersonalizado.showToastLock(getContext(), "Se Limpiaron los Campos");
                break;
            }
        }
    }

    private void enlazarControles(View view)
    {
        edtIngresarNombreProducto = view.findViewById(R.id.edtIngresarNombreProducto);
        edtIngresarCategoria = view.findViewById(R.id.edtIngresarCategoria);
        edtIngresarMarca = view.findViewById(R.id.edtIngresarMarca);
        edtIngresarStock = view.findViewById(R.id.edtIngresarStock);
        edtIngresarPrecio = view.findViewById(R.id.edtIngresarPrecio);
        btnNuevoProducto = view.findViewById(R.id.btnNuevoProducto);
        btnGrabarProducto = view.findViewById(R.id.btnGrabarProducto);
    }


    private void limpiar() {
        edtIngresarNombreProducto.setText("");
        edtIngresarCategoria.setText("");
        edtIngresarMarca.setText("");
        edtIngresarStock.setText("");
        edtIngresarPrecio.setText("");
    }

    private Producto producto = new Producto();

    private boolean validarCampos()
    {
        boolean retorno = true;

        String nombreProducto = edtIngresarNombreProducto.getText().toString().trim();
        String categoriaProducto = edtIngresarCategoria.getText().toString().trim();
        String marcaProducto = edtIngresarMarca.getText().toString().trim();
        String stockProducto = edtIngresarStock.getText().toString().trim();
        String precioProducto = edtIngresarPrecio.getText().toString().trim();

        edtIngresarNombreProducto.setError(null);
        edtIngresarCategoria.setError(null);
        edtIngresarMarca.setError(null);
        edtIngresarStock.setError(null);
        edtIngresarPrecio.setError(null);

        if (nombreProducto.equals(""))
        {
            edtIngresarNombreProducto.setError("Por favor, ingrese el nombre del producto");
            retorno = false;
        }
        if (categoriaProducto.equals(""))
        {
            edtIngresarCategoria.setError("Por favor, ingrese la categoria del producto");
            retorno = false;
        }
        if (marcaProducto.equals(""))
        {
            edtIngresarMarca.setError("Por favor, ingrese la marca del producto");
            retorno = false;
        }
        if (stockProducto.equals(""))
        {
            edtIngresarStock.setError("Por favor, ingrese la categoria del producto");
            retorno = false;
        }
        if (precioProducto.equals(""))
        {
            edtIngresarPrecio.setError("Por favor, ingrese la categoria del producto");
            retorno = false;
        }

        if (!retorno) return false; // Retornamos false si entro a alguna condicional de validación y se establecio el error
        // Así evitamos que grabe a pesar de que no lleno todos o algunos campos :)


        producto.setNombre(nombreProducto);
        producto.setCategoria(categoriaProducto);
        producto.setMarca(marcaProducto);
        producto.setStock(Integer.parseInt(stockProducto));
        producto.setPrecio(Double.parseDouble(precioProducto));
        return true;
    }
}