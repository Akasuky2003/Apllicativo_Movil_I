package com.example.proyectoobregon.Fragment;

import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.proyectoobregon.Database.AppDB;
import com.example.proyectoobregon.Entidades.Login;
import com.example.proyectoobregon.R;
import com.example.proyectoobregon.ToastPersonalizado;
import com.example.proyectoobregon.UsuarioActivity;

import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;

public class AgregarUsuario extends Fragment implements View.OnClickListener {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public AgregarUsuario() {
        // Required empty public constructor
    }


    public static AgregarUsuario newInstance(String param1, String param2) {
        AgregarUsuario fragment = new AgregarUsuario();
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



    private EditText edtIngresarUser, edtIngresarPassword;
    private Button btnNuevoLogin, btnGrabarLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_agregar_usuario, container, false);
        enlazarControles(vista);

        btnNuevoLogin.setOnClickListener(this);
        btnGrabarLogin.setOnClickListener(this);

        return vista;
    }

    private void enlazarControles(View vista)
    {
        edtIngresarUser = vista.findViewById(R.id.edtIngresarUser);
        edtIngresarPassword = vista.findViewById(R.id.edtIngresarPassword);
        btnNuevoLogin = vista.findViewById(R.id.btnNuevoLogin);
        btnGrabarLogin = vista.findViewById(R.id.btnGrabarLogin);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnGrabarLogin:
            {

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

                AppDB.getInstancia(getContext()).loginDAO().insertarLogin(login);
                String text = "Se Registrò un Nuevo Usuario";
                String title = "Operacion Exitosa";
                //ToastPersonalizado.showToastLock(getContext(), "Se Registrò un Nuevo Producto");
                MotionToast.Companion.darkToast(getActivity(),title,text,
                        MotionToastStyle.SUCCESS,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(getContext(), www.sanju.motiontoast.R.font.helvetica_regular));

                //startActivity(new Intent(getContext(), ProductoActivity.class)); // Provoca saturación ya que se llama al mismo activity cada vez que guardas
                ((UsuarioActivity)getActivity()).agregarRecargarListadoUsuario(); // mejor optimizado :)
                limpiar();
                break;
            }
            case R.id.btnNuevoLogin:
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

    private void limpiar() {
        edtIngresarUser.setText("");
        edtIngresarPassword.setText("");
    }

    private Login login = new Login();

    private boolean validarCampos()
    {
        boolean retorno = true;

        String userUsuario = edtIngresarUser.getText().toString().trim();
        String passwordUsuario = edtIngresarPassword.getText().toString().trim();

        edtIngresarUser.setError(null);
        edtIngresarPassword.setError(null);

        if (userUsuario.equals(""))
        {
            edtIngresarUser.setError("Por favor, ingrese el usuario");
            retorno = false;
        }
        if (passwordUsuario.equals(""))
        {
            edtIngresarPassword.setError("Por favor, ingrese la contraseña");
            retorno = false;
        }


        if (!retorno) return false; // Retornamos false si entro a alguna condicional de validación y se establecio el error
        // Así evitamos que grabe a pesar de que no lleno todos o algunos campos :)


        login.setUsuario(userUsuario);
        login.setContrasenia(passwordUsuario);

        return true;
    }
}