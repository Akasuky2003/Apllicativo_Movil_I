package com.example.proyectoobregon.Controlador;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.proyectoobregon.Adaptador.AdaptadorCliente;
import com.example.proyectoobregon.ClientesActivity;
import com.example.proyectoobregon.Dao.ClientesDAO;
import com.example.proyectoobregon.Database.AppDB;
import com.example.proyectoobregon.Entidades.Clientes;
import com.example.proyectoobregon.LoginActivity;
import com.example.proyectoobregon.MenuPrincipalActivity;
import com.example.proyectoobregon.R;
import com.example.proyectoobregon.ToastPersonalizado;

import java.util.List;

import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;

public class ListadoClientes extends Fragment {

    ListView lvClientes;
    Object posicion = null;
    View vista;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ListadoClientes() {

    }

    public static ListadoClientes newInstance(String param1, String param2) {
        ListadoClientes fragment = new ListadoClientes();
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

        vista = inflater.inflate(R.layout.fragment_listado_clientes, container, false);

        lvClientes = vista.findViewById(R.id.lvClientesFragment);

        //esto se uso sin bd
        //CargarCiudad();
        //TraerCiudad();

        List<Clientes> lista =
                AppDB.getInstancia(ListadoClientes.this.getContext())
                        .clientesbdDao().ListarClientes();

        AdaptadorCliente adapter = new AdaptadorCliente(
                ListadoClientes.this.getContext(),
                R.layout.diseno_fila_clientes,
                lista
        );


        lvClientes.setAdapter(adapter);


        lvClientes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {

                posicion = lvClientes.getItemAtPosition(position);

                int codigocliente = lista.get(position).getCod_cliente();


                showWarningAlertDialog("¿Estas seguro de eliminar al cliente con codigo "
                        + codigocliente + "?", codigocliente);


                return false;

            }
        });

        return  vista;
    }


    void showWarningAlertDialog(String Title, int codigocliente) {


        AlertDialog.Builder builder = new AlertDialog.Builder(ListadoClientes.this.getContext(), R.style.AlertDialogTheme);
        View view = LayoutInflater.from(ListadoClientes.this.getContext()).inflate(
                R.layout.layout_warning_dialog, (ConstraintLayout)vista.findViewById(R.id.layoutDialogContainer)
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
                        ListadoClientes.this.getContext(),
                        "Espere un Momento",
                        "Eliminando Cliente",
                        true
                );

                Thread hilo = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            Thread.sleep(2000);

                            //runOnUiThread();
                        } catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        pd.dismiss();
                    }
                });
                hilo.start();

                MotionToast.Companion.darkToast(ListadoClientes.this.getActivity(),"SUCCESS","Cliente eliminado correctamente",
                        MotionToastStyle.DELETE,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(ListadoClientes.this.getContext(), www.sanju.motiontoast.R.font.helvetica_regular));


                AppDB.getInstancia(ListadoClientes.this.getContext())
                        .clientesbdDao().EliminarClienteCodigo(codigocliente);

                Intent i = new Intent(ListadoClientes.this.getContext(),
                        ClientesActivity.class);
                startActivity(i);


            }
        });
        view.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

                MotionToast.Companion.darkToast(ListadoClientes.this.getActivity(),"ALERTA","Operacion Cancelada",
                        MotionToastStyle.INFO,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(ListadoClientes.this.getContext(), www.sanju.motiontoast.R.font.helvetica_regular));
            }
        });



        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();

    }

    /*
    private void TraerCiudad() {

        dao = new ClientesDAO();
        //
        AdaptadorCliente adapter = new AdaptadorCliente(
                ListadoClientes.this.getContext(), R.layout.diseno_fila_clientes,
                dao.Listar_Clientes()
        );
        //

        lvClientes.setAdapter(adapter);

    }

    private void CargarCiudad() {

        dao = new ClientesDAO();
        dao.grabarCliente(new Clientes(101, "Cristofer Mejia",
                "cristofer@gmail.com","Av.Capac 1130", "978319841",
                "75886652", R.drawable.recurso1));
        dao.grabarCliente(new Clientes(102, "Cristofer Mejia",
                "cristofer@gmail.com","Av.Capac 1130", "978319841",
                "75886652", R.drawable.recurso2));
        dao.grabarCliente(new Clientes(103, "Cristofer Mejia",
                "cristofer@gmail.com","Av.Capac 1130", "978319841",
                "75886652", R.drawable.recurso3));
    }
    */

}