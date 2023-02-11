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

import com.example.proyectoobregon.Adaptador.AdaptadorTrabajador;
import com.example.proyectoobregon.ClientesActivity;
import com.example.proyectoobregon.Database.AppDB;
import com.example.proyectoobregon.Entidades.Trabajadores;
import com.example.proyectoobregon.R;
import com.example.proyectoobregon.ToastPersonalizado;
import com.example.proyectoobregon.TrabajadorActivity;

import java.util.List;

import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListadoTrabajadorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListadoTrabajadorFragment extends Fragment {

    ListView lvTrabajadores;
    Object posicion=null;
    View vista;
    int num=0;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    public ListadoTrabajadorFragment() {
        // Required empty public constructor
    }

    public static ListadoTrabajadorFragment newInstance(String param1, String param2) {
        ListadoTrabajadorFragment fragment = new ListadoTrabajadorFragment();
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
        vista = inflater.inflate(R.layout.fragment_listado_trabajador, container, false);

        lvTrabajadores = vista.findViewById(R.id.lvTrabajadoresFragment);



        List<Trabajadores> lista =
                AppDB.getInstancia(ListadoTrabajadorFragment.this.getContext())
                        .trabajadoresbdDao().ListarTrabajadores();

        AdaptadorTrabajador adapter=new AdaptadorTrabajador(
                ListadoTrabajadorFragment.this.getContext(),
                R.layout.diseno_fila_trabajadores,
                lista
        );

        lvTrabajadores.setAdapter(adapter);
        lvTrabajadores.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                posicion = lvTrabajadores.getItemAtPosition(position);

                int codigotrabajadores = lista.get(position).getCod_trabajadores();



                //String title = "¿Estas seguro de eliminar al Trabajador con codigo "
                  //      + codigotrabajadores + "?"

                showWarningAlertDialog("¿Estas seguro de eliminar al Trabajador con codigo "
                        + codigotrabajadores + "?", codigotrabajadores);
                return false;

            }
        });

        return  vista;

    }

    void showWarningAlertDialog(String Title, int codigotrabajador) {


        AlertDialog.Builder builder = new AlertDialog.Builder(ListadoTrabajadorFragment.this.getContext(), R.style.AlertDialogTheme);
        View view = LayoutInflater.from(ListadoTrabajadorFragment.this.getContext()).inflate(
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
                        ListadoTrabajadorFragment.this.getContext(),
                        "Espere un Momento",
                        "Eliminando Trabajador",
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

                MotionToast.Companion.darkToast(ListadoTrabajadorFragment.this.getActivity(),"SUCCESS","Cliente eliminado correctamente",
                        MotionToastStyle.DELETE,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(ListadoTrabajadorFragment.this.getContext(), www.sanju.motiontoast.R.font.helvetica_regular));



                AppDB.getInstancia(ListadoTrabajadorFragment.this.getContext())
                        .trabajadoresbdDao().EliminarTrabajadoresCodigo(codigotrabajador);

                Intent i = new Intent(ListadoTrabajadorFragment.this.getContext(), TrabajadorActivity.class);
                startActivity(i);



            }
        });
        view.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

                MotionToast.Companion.darkToast(ListadoTrabajadorFragment.this.getActivity(),"ALERTA","Operacion Cancelada",
                        MotionToastStyle.INFO,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(ListadoTrabajadorFragment.this.getContext(), www.sanju.motiontoast.R.font.helvetica_regular));
            }
        });



        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();

    }




    void menu(String Title, int Codigotrabajadores) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(ListadoTrabajadorFragment.this.getContext());
        builder1.setTitle(Title);
        builder1.setCancelable(false);


        builder1.setPositiveButton(
                "No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String text = "Proceso Cancelado";
                        ToastPersonalizado.showToastAlert(getContext(),text);

                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        ProgressDialog pd = ProgressDialog.show(
                                ListadoTrabajadorFragment.this.getContext(),
                                "Espere un Momento",
                                "Eliminando Trabajador",
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

                        AppDB.getInstancia(ListadoTrabajadorFragment.this.getContext())
                                .trabajadoresbdDao().EliminarTrabajadoresCodigo(Codigotrabajadores);
                        Intent i = new Intent(ListadoTrabajadorFragment.this.getContext(), TrabajadorActivity.class);
                        startActivity(i);

                    }
                });

        builder1.show();

    }

}