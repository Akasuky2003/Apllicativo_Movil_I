package com.example.proyectoobregon.Controlador;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectoobregon.ClientesActivity;
import com.example.proyectoobregon.Database.AppDB;
import com.example.proyectoobregon.Entidades.Clientes;
import com.example.proyectoobregon.Entidades.Login;
import com.example.proyectoobregon.R;
import com.example.proyectoobregon.ToastPersonalizado;

import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;


public class AgregarCliente extends Fragment {

    EditText edtNombre, edtCorreo, edtDireccion, edtCelular, edtDni;
    Button btnNuevo, btnGrabar;
    View vista;
    ToastPersonalizado myToast;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AgregarCliente() {
        // Required empty public constructor
    }

    public static AgregarCliente newInstance(String param1, String param2) {
        AgregarCliente fragment = new AgregarCliente();
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

        vista = inflater.inflate(R.layout.fragment_agregar_cliente, container, false);

        //
        EnlazarControles();
        //

        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    grabarCliente();

            }
        });
        //

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtNombre.setText("");
                edtCorreo.setText("");
                edtDireccion.setText("");
                edtCelular.setText("");
                edtDni.setText("");

                /* CREAR ToastPersonalizado
                myToast=new ToastPersonalizado(getContext());
                myToast.setBackgroundColor(Color.parseColor("#000033"),30);
                myToast.setIcon(ContextCompat.getDrawable(getContext(),R.drawable.icon6));
                */





                /*
                MotionToast.Companion.darkColorToast(getActivity(),title,text,
                        MotionToastStyle.INFO,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(getContext(), www.sanju.motiontoast.R.font.helvetica_regular));
*/

                String text = "Campos Limpiados Correctamente";
                String title = "INFO";

                MotionToast.Companion.darkToast(getActivity(),title,text,
                        MotionToastStyle.WARNING,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(getContext(), www.sanju.motiontoast.R.font.helvetica_regular));



                /*NO ME GUSTA
                MotionToast.Companion.createColorToast(getActivity(),title,text,
                        MotionToastStyle.WARNING,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(getContext(), www.sanju.motiontoast.R.font.helvetica_regular));
                 */
                 /*NO ME GUSTA
                MotionToast.Companion.createToast(getActivity(),title,text,
                        MotionToastStyle.WARNING,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(getContext(), www.sanju.motiontoast.R.font.helvetica_regular));
                 */

                //ToastPersonalizado.showToastInfo(getContext(),text);

                /*
                Toast.makeText(getContext(),
                        "Campos Limpiados Correctamente", Toast.LENGTH_LONG).show();
                */


            }
        });



        //
        return vista;
    }

    private void grabarCliente() {

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


        AppDB.getInstancia(AgregarCliente.this.getContext())
                .clientesbdDao()
                .InsertarClientes(clie);
        //
        String text = "Se registrò un Nuevo Cliente";
        MotionToast.Companion.darkToast(getActivity(),"Operacion Exitosa",text,
                MotionToastStyle.SUCCESS,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(getContext(), www.sanju.motiontoast.R.font.helvetica_regular));

        Intent i =new Intent(
                AgregarCliente.this.getContext(),
                ClientesActivity.class
        );
        //
        startActivity(i);

    }

    private void EnlazarControles() {

        edtNombre = vista.findViewById(R.id.edtNombreCliente);
        edtCorreo = vista.findViewById(R.id.edtCorreoCliente);
        edtDireccion = vista.findViewById(R.id.edtDireccionCliente);
        edtCelular = vista.findViewById(R.id.edtCelularCliente);
        edtDni = vista.findViewById(R.id.edtDniCliente);

        btnNuevo = vista.findViewById(R.id.btnNuevoCliente);
        btnGrabar = vista.findViewById(R.id.btnGrabarCliente);
    }



    private Clientes clie = new Clientes();

    private boolean validarCampos()
    {
        boolean retorno = true;

        String nomCli = edtNombre.getText().toString().trim();
        String correoCli  = edtCorreo.getText().toString().trim();
        String direcCli  = edtDireccion.getText().toString().trim();
        String celCli  = edtCelular.getText().toString().trim();
        String dniCli  = edtDni.getText().toString().trim();


        edtNombre.setError(null);
        edtCorreo.setError(null);
        edtDireccion.setError(null);
        edtCelular.setError(null);
        edtDni.setError(null);

        if (nomCli.equals(""))
        {
            edtNombre.setError("Por favor, ingrese el nombre del cliente");
            retorno = false;
        }
        if (correoCli.equals(""))
        {
            edtCorreo.setError("Por favor, ingrese el correo del cliente");
            retorno = false;
        }
        if (direcCli.equals(""))
        {
            edtDireccion.setError("Por favor, ingrese la direccion del cliente");
            retorno = false;
        }
        if (celCli.equals(""))
        {
            edtCelular.setError("Por favor, ingrese el celular del cliente");
            retorno = false;
        }
        if (dniCli.equals(""))
        {
            edtDni.setError("Por favor, ingrese el dni del cliente");
            retorno = false;
        }


        if (!retorno) return false; // Retornamos false si entro a alguna condicional de validación y se establecio el error
        // Así evitamos que grabe a pesar de que no lleno todos o algunos campos :)


        clie.setNombres(nomCli);
        clie.setCorreo(correoCli);
        clie.setDireccion(direcCli);
        clie.setCelular(celCli);
        clie.setDni(dniCli);

        return true;
    }
}