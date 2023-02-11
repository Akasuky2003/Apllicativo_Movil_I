package com.example.proyectoobregon.Controlador;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.proyectoobregon.ClientesActivity;
import com.example.proyectoobregon.Database.AppDB;
import com.example.proyectoobregon.Entidades.Clientes;
import com.example.proyectoobregon.Entidades.Trabajadores;
import com.example.proyectoobregon.R;
import com.example.proyectoobregon.ToastPersonalizado;
import com.example.proyectoobregon.TrabajadorActivity;
import com.example.proyectoobregon.Utilitarios.Variables;

import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgregarTrabajadorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgregarTrabajadorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AgregarTrabajadorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgregarTrabajadorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgregarTrabajadorFragment newInstance(String param1, String param2) {
        AgregarTrabajadorFragment fragment = new AgregarTrabajadorFragment();
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


    EditText edtNombres,edtApellidoPaterno,edtApellidoMaterno,edtDni,edtCorreo,edtTelefono,edtDirecion;
    Spinner spinGenero,spinCargo;
    Button btnNuevo,btnGraba;
    View vista;
    ToastPersonalizado myToast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        vista= inflater.inflate(R.layout.fragment_agregar_trabajador, container, false);
        EnlazarControles();
        ArrayAdapter<String> adapterspin = new ArrayAdapter<String>(
                AgregarTrabajadorFragment.this.getContext(),
                android.R.layout.simple_dropdown_item_1line,
                Variables.vGenero
        );
        spinGenero.setAdapter(adapterspin);
        ArrayAdapter<String> adapterspinu = new ArrayAdapter<String>(
                AgregarTrabajadorFragment.this.getContext(),
                android.R.layout.simple_dropdown_item_1line,
                Variables.vCargo
        );
        spinCargo.setAdapter(adapterspinu);

        //Botones
        btnGraba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GrabarTrabajador();

            }
        });
        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtNombres.setText("");
                edtApellidoPaterno.setText("");
                edtApellidoMaterno.setText("");
                edtDni.setText("");
                edtDirecion.setText("");
                edtTelefono.setText("");


                spinGenero.setSelection(0);
                spinCargo.setSelection(0);

                String text = "Campos Limpiados Correctamente";
                String title = "INFO";

                MotionToast.Companion.darkToast(getActivity(),title,text,
                        MotionToastStyle.WARNING,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(getContext(), www.sanju.motiontoast.R.font.helvetica_regular));

            }
        });

        return vista;
    }
    private void GrabarTrabajador(){


        Trabajadores objtrab = new Trabajadores(edtNombres.getText().toString(),
                edtApellidoPaterno.getText().toString(),
                edtApellidoMaterno.getText().toString(),
                Integer.parseInt(edtDni.getText().toString()),
                edtCorreo.getText().toString(),
                edtDirecion.getText().toString(),


                Integer.parseInt(edtTelefono.getText().toString()),
                spinCargo.getSelectedItem().toString(),
                spinGenero.getSelectedItem().toString()

        );

        /*
        if (!validarCampos()){
            String text = "Campos sin Completar";
            String title = "ALERTA";
            MotionToast.Companion.darkToast(getActivity(),title,text,
                    MotionToastStyle.ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(getContext(), www.sanju.motiontoast.R.font.helvetica_regular));
            return;
        }*/


        AppDB.getInstancia(getContext())
                .trabajadoresbdDao()
                .InsertarTrabajadores(objtrab);


        String text = "Se registrò un Nuevo Trabajador";
        MotionToast.Companion.darkToast(getActivity(),"Operacion Exitosa",text,
                MotionToastStyle.SUCCESS,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(getContext(), www.sanju.motiontoast.R.font.helvetica_regular));

        Intent i =new Intent(
                AgregarTrabajadorFragment.this.getContext(),
                TrabajadorActivity.class
        );
        //
        startActivity(i);
    }


    private void EnlazarControles(){
        edtNombres=vista.findViewById(R.id.edtNombresTrabajadoresGrabar);
        edtApellidoPaterno=vista.findViewById(R.id.edtApellidoPaternoTrabajador);
        edtApellidoMaterno=vista.findViewById(R.id.edtApellidoMaternoTrabajador);
        edtDni=vista.findViewById(R.id.edtDniTrabajador);
        edtCorreo=vista.findViewById(R.id.edtCorreoelectronicoTrabajador);
        edtDirecion=vista.findViewById(R.id.edtDirecionTrabajador);
        edtTelefono=vista.findViewById(R.id.edtNumberTrabajador);



        spinCargo=vista.findViewById(R.id.spinCargoTrabajador);
        spinGenero=vista.findViewById(R.id.spinSexoTrabajador);

        btnGraba=vista.findViewById(R.id.btnGrabarTrabajador);
        btnNuevo=vista.findViewById(R.id.btnNuevoTrabajador);
    }

    private Trabajadores trab = new Trabajadores();

    private boolean validarCampos()
    {
        boolean retorno = true;

        String nomTrab = edtNombres.getText().toString().trim();
        String appPatTrab  = edtApellidoPaterno.getText().toString().trim();
        String appMatTrab  = edtApellidoMaterno.getText().toString().trim();
        int dniTrab  = Integer.parseInt(edtDni.getText().toString().trim());
        String correoTrab  = edtCorreo.getText().toString().trim();
        String direcTrab  = edtDirecion.getText().toString().trim();
        int celuTrab  = Integer.parseInt(edtTelefono.getText().toString().trim());


        edtNombres.setError(null);
        edtApellidoPaterno.setError(null);
        edtApellidoMaterno.setError(null);
        edtDni.setError(null);
        edtCorreo.setError(null);
        edtDirecion.setError(null);
        edtTelefono.setError(null);

        if (nomTrab.equals(""))
        {
            edtNombres.setError("Por favor, ingrese el nombre del trabajador");
            retorno = false;
        }
        if (appPatTrab.equals(""))
        {
            edtApellidoPaterno.setError("Por favor, ingrese el apellido paterno del trabajador");
            retorno = false;
        }
        if (appMatTrab.equals(""))
        {
            edtApellidoMaterno.setError("Por favor, ingrese el apellido materno del trabajador");
            retorno = false;
        }
        if (dniTrab == 0)
        {
            edtDni.setError("Por favor, ingrese el dni del trabajador");
            retorno = false;
        }
        if (correoTrab.equals(""))
        {
            edtCorreo.setError("Por favor, ingrese el correo del trabajador");
            retorno = false;
        }
        if (direcTrab.equals(""))
        {
            edtDirecion.setError("Por favor, ingrese la direccion del trabajador");
            retorno = false;
        }
        if (celuTrab==0)
        {
            edtTelefono.setError("Por favor, ingrese el celular del trabajador");
            retorno = false;
        }


        if (!retorno) return false; // Retornamos false si entro a alguna condicional de validación y se establecio el error
        // Así evitamos que grabe a pesar de que no lleno todos o algunos campos :)


        trab.setNombres(nomTrab);
        trab.setApellido_paterno(appPatTrab);
        trab.setApellido_materno(appMatTrab);
        trab.setDni(dniTrab);
        trab.setCorreo(correoTrab);
        trab.setDireccion(direcTrab);
        trab.setCelular(celuTrab);

        return true;
    }
}