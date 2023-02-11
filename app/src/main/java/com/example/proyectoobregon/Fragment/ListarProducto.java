package com.example.proyectoobregon.Fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoobregon.Adaptador.AdaptadorProducto;
import com.example.proyectoobregon.ClientesActivity;
import com.example.proyectoobregon.Controlador.ListadoClientes;
import com.example.proyectoobregon.Database.AppDB;
import com.example.proyectoobregon.Entidades.Producto;
import com.example.proyectoobregon.R;
import com.example.proyectoobregon.ToastPersonalizado;

import java.util.List;

import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;

public class ListarProducto extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ListarProducto() {
        // Required empty public constructor
    }

    public static ListarProducto newInstance(String param1, String param2) {
        ListarProducto fragment = new ListarProducto();
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

    private ActivityInterface activityIntercae;

    public void setInterface(ActivityInterface activityIntercae) {
        this.activityIntercae = activityIntercae;
    }

    private ListView lvproductos;
    View root;
    String estado = "dsd";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_listar_producto, container, false);
        lvproductos = root.findViewById(R.id.lvProductos);
        llenarListView();

        lvproductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Producto producto = (Producto) lvproductos.getItemAtPosition(position);
                if (estado != null){

                    activityIntercae.moverActivity(producto);

                }
            }
        });

        lvproductos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                Producto producto = (Producto) lvproductos.getItemAtPosition(position);
                //AppDB.getInstancia(getContext()).productoDAO().deleteProducto(producto);

                estado = null;
                showWarningAlertDialog("¿Estas seguro de eliminar el producto?", producto);


                //ToastPersonalizado.showToastLock(getContext(), "Se eliminó correctamente");

                return false;
            }
        });

        return root;
    }

    public void llenarListView()
    {
        List<Producto> lista = AppDB.getInstancia(getContext()).productoDAO().listProducts();
        // Log.e("listaProducto", ""+lista.size()); // Log
        AdaptadorProducto adapter = new AdaptadorProducto(getContext(), R.layout.activity_fila_producto, lista);
        lvproductos.setAdapter(adapter);
    }


    void showWarningAlertDialog(String Title, Producto producto) {


        AlertDialog.Builder builder = new AlertDialog.Builder(ListarProducto.this.getContext(), R.style.AlertDialogTheme);
        View view = LayoutInflater.from(ListarProducto.this.getContext()).inflate(
                R.layout.layout_warning_dialog, (ConstraintLayout)root.findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText("Alerta");
        ((TextView) view.findViewById(R.id.textMessage)).setText(Title);
        ((Button) view.findViewById(R.id.buttonYes)).setText("Yes");
        ((Button) view.findViewById(R.id.buttonNo)).setText("No");
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.warning);

        final AlertDialog alertDialog = builder.create();
        view.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

                ProgressDialog pd = ProgressDialog.show(
                        ListarProducto.this.getContext(),
                        "Espere un Momento",
                        "Eliminando Producto",
                        true
                );

                Thread hilo = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            Thread.sleep(1300);

                            //runOnUiThread();
                        } catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        pd.dismiss();
                    }
                });
                hilo.start();

                estado = "xdd";


                MotionToast.Companion.darkToast(ListarProducto.this.getActivity(),"SUCCESS","Producto eliminado correctamente",
                        MotionToastStyle.DELETE,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(ListarProducto.this.getContext(), www.sanju.motiontoast.R.font.helvetica_regular));


                AppDB.getInstancia(getContext()).productoDAO().deleteProducto(producto);

                llenarListView();
            }
        });
        view.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

                estado = "xdd";

                MotionToast.Companion.darkToast(ListarProducto.this.getActivity(),"ALERTA","Operacion Cancelada",
                        MotionToastStyle.INFO,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(ListarProducto.this.getContext(), www.sanju.motiontoast.R.font.helvetica_regular));
            }
        });



        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();

    }





}