package com.example.proyectoobregon.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.proyectoobregon.Database.AppDB;
import com.example.proyectoobregon.Entidades.Login;
import com.example.proyectoobregon.R;
import com.example.proyectoobregon.ToastPersonalizado;
import com.example.proyectoobregon.UsuarioActivity;

public class ModificarUsuario extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public ModificarUsuario() {
        // Required empty public constructor
    }


    public static ModificarUsuario newInstance(String param1, String param2) {
        ModificarUsuario fragment = new ModificarUsuario();
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



    private EditText edtIngresarUserEditar, edtIngresarPasswordEditar;
    private Button btnCancelarLogin, btnActualizarLogin;
    private TextView tvCodigoLoginEditar;
    private Login loginActualizar = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_modificar_usuario, container, false);
        enlazarControles(vista);

        btnCancelarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarCampos();
                ToastPersonalizado.showToastLock(getContext(), "Se limpiaron los campos");
            }
        });

        btnActualizarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (loginActualizar == null)
                {
                    ToastPersonalizado.showToastLock(getContext(), "No hay un producto seleccionado del listado!");
                    return;
                }
                else if (!validarCampos()) return;

                loginActualizar.setUsuario(edtIngresarUserEditar.getText().toString().trim());
                loginActualizar.setContrasenia(edtIngresarPasswordEditar.getText().toString().trim());
                AppDB.getInstancia(getContext()).loginDAO().updateLogin(loginActualizar);
                ((UsuarioActivity)getActivity()).agregarRecargarListadoUsuario();
                ToastPersonalizado.showToastLock(getContext(), "Se actualizo correctamente");
                limpiarCampos();
            }
        });

        return vista;
    }

    private void enlazarControles(View vista)
    {
        tvCodigoLoginEditar = vista.findViewById(R.id.tvCodigoLoginEditar);
        edtIngresarUserEditar = vista.findViewById(R.id.edtIngresarUserEditar);
        edtIngresarPasswordEditar = vista.findViewById(R.id.edtIngresarPasswordEditar);
        btnCancelarLogin = vista.findViewById(R.id.btnCancelarLogin);
        btnActualizarLogin = vista.findViewById(R.id.btnActualizarLogin);
    }

    public void llenarCamposActualizar(Login login)
    {
        tvCodigoLoginEditar.setText("Código: " + login.getCod_usuario());
        edtIngresarUserEditar.setText(login.getUsuario());
        edtIngresarPasswordEditar.setText(login.getContrasenia());
        loginActualizar = login;
    }

    private void limpiarCampos() {
        tvCodigoLoginEditar.setText("Código: ");
        edtIngresarUserEditar.setText("");
        edtIngresarPasswordEditar.setText("");
        loginActualizar = null;
    }

    private boolean validarCampos()
    {
        boolean retorno = true;

        String userUsuario = edtIngresarUserEditar.getText().toString().trim();
        String passwordUsuario = edtIngresarPasswordEditar.getText().toString().trim();

        edtIngresarUserEditar.setError(null);
        edtIngresarPasswordEditar.setError(null);

        if (userUsuario.equals(""))
        {
            edtIngresarUserEditar.setError("Por favor, ingrese el usuario");
            retorno = false;
        }
        if (passwordUsuario.equals(""))
        {
            edtIngresarPasswordEditar.setError("Por favor, ingrese la contraseña");
            retorno = false;
        }


        return retorno;
    }
}