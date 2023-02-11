package com.example.proyectoobregon.Fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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

import com.example.proyectoobregon.Adaptador.AdaptadorUsuario;
import com.example.proyectoobregon.Database.AppDB;
import com.example.proyectoobregon.Entidades.Login;
import com.example.proyectoobregon.Entidades.Producto;
import com.example.proyectoobregon.R;
import com.example.proyectoobregon.ToastPersonalizado;
import com.example.proyectoobregon.UsuarioActivity;

import java.util.List;

import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;


public class ListarUsuario extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public ListarUsuario() {
        // Required empty public constructor
    }


    public static ListarUsuario newInstance(String param1, String param2) {
        ListarUsuario fragment = new ListarUsuario();
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



    private ListView lvLogin;
    View vista;
    String estado = "dsd";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_listar_usuario, container, false);
        lvLogin = vista.findViewById(R.id.lvLogin);
        llenarListView();

        lvLogin.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Login login = (Login) lvLogin.getItemAtPosition(position);

                if (estado != null){

                    ((UsuarioActivity)getActivity()).moverActivity(login);

                }

            }
        });

        lvLogin.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                Login login = (Login) lvLogin.getItemAtPosition(position);
                //AppDB.getInstancia(getContext()).loginDAO().deleteLogin(login);
                //llenarListView();

                estado = null;
                showWarningAlertDialog("¿Estas seguro de eliminar el producto?", login);

                //ToastPersonalizado.showToastLock(getContext(), "Se eliminó correctamente");
                return false;
            }
        });

        return vista;
    }

    public void llenarListView()
    {
        List<Login> lista = AppDB.getInstancia(getContext()).loginDAO().obtenerLogins();
        AdaptadorUsuario adapter = new AdaptadorUsuario(getContext(), R.layout.activity_fila_usuario, lista);
        lvLogin.setAdapter(adapter);
    }


    void showWarningAlertDialog(String Title, Login login) {


        AlertDialog.Builder builder = new AlertDialog.Builder(ListarUsuario.this.getContext(), R.style.AlertDialogTheme);
        View view = LayoutInflater.from(ListarUsuario.this.getContext()).inflate(
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
                        ListarUsuario.this.getContext(),
                        "Espere un Momento",
                        "Eliminando Usuario",
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


                MotionToast.Companion.darkToast(ListarUsuario.this.getActivity(),"SUCCESS","Usuario eliminado correctamente",
                        MotionToastStyle.DELETE,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(ListarUsuario.this.getContext(), www.sanju.motiontoast.R.font.helvetica_regular));


                AppDB.getInstancia(getContext()).loginDAO().deleteLogin(login);

                llenarListView();
            }
        });
        view.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

                estado = "xdd";

                MotionToast.Companion.darkToast(ListarUsuario.this.getActivity(),"ALERTA","Operacion Cancelada",
                        MotionToastStyle.INFO,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(ListarUsuario.this.getContext(), www.sanju.motiontoast.R.font.helvetica_regular));
            }
        });



        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();

    }


}