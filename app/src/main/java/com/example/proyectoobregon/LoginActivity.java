package com.example.proyectoobregon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoobregon.Database.AppDB;
import com.example.proyectoobregon.Entidades.Login;

import java.util.List;

public class LoginActivity extends AppCompatActivity
{
    private TextView tvErrorLogin;
    private EditText edtContrasenia, edtUsuario;
    private Button btnIngresarLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        enlazarControles();

        Login logininsertar = new Login();
        //logininsertar.setUsuario("");
        //logininsertar.setContrasenia("");
        try {
            AppDB.getInstancia(LoginActivity.this).loginDAO().insertarLogin(logininsertar);
        }
        catch (Exception e)
        {
            Log.e("mensaje", e.getMessage());
        }



        btnIngresarLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String user = edtUsuario.getText().toString();
                String pass = edtContrasenia.getText().toString();

                Login login = AppDB.getInstancia(LoginActivity.this).loginDAO().obtenerLogin(user, pass);
                if (login == null)
                {
                    tvErrorLogin.setVisibility(View.VISIBLE);
                    tvErrorLogin.setText("Datos Incorrectos!");
                    return;
                    //Toast.makeText(LoginActivity.this, "Datos Incorrectos!", Toast.LENGTH_SHORT).show();
                }
                
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
        });



    }

    private void enlazarControles()
    {
        tvErrorLogin = findViewById(R.id.tvErrorLogin);
        edtUsuario = findViewById(R.id.edtUsuario);
        edtContrasenia = findViewById(R.id.edtContrasenia);
        btnIngresarLogin = findViewById(R.id.btnIngresarLogin);
    }
}