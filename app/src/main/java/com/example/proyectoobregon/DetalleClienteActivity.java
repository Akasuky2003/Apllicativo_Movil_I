package com.example.proyectoobregon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.proyectoobregon.Adaptador.AdaptadorCliente;
import com.example.proyectoobregon.Controlador.ListadoClientes;
import com.example.proyectoobregon.Controlador.ModificarCliente;
import com.example.proyectoobregon.Database.AppDB;
import com.example.proyectoobregon.Entidades.Clientes;
import com.example.proyectoobregon.Entidades.Producto;

import java.util.List;

import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;

public class DetalleClienteActivity extends AppCompatActivity {


    EditText edtNombres, edtCorreo, edtDireccion, edtCelular, edtDni, edtCodigo;
    Button btnModificar, btnCancelar;
    int codigo_cliente;
    private Clientes clienteactualizar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cliente);


        edtCodigo = findViewById(R.id.edtDetalleCodigo);
        edtNombres = findViewById(R.id.edtDetalleNombre);
        edtCorreo = findViewById(R.id.edtDetalleCorreo);
        edtDireccion = findViewById(R.id.edtDetalleDireccion);
        edtCelular = findViewById(R.id.edtDetalleCelular);
        edtDni = findViewById(R.id.edtDetalleDni);
        btnModificar = findViewById(R.id.btnDetalleModificar);
        btnCancelar = findViewById(R.id.btnDetalleCancelar);


        codigo_cliente = Integer.parseInt(getIntent().getStringExtra("codigo"));
        edtCodigo.setText("CL"+ getIntent().getStringExtra("codigo"));
        edtNombres.setText(getIntent().getStringExtra("nombre"));
        edtCorreo.setText(getIntent().getStringExtra("correo"));
        edtDireccion.setText(getIntent().getStringExtra("direccion"));
        edtCelular.setText(getIntent().getStringExtra("celular"));
        edtDni.setText(getIntent().getStringExtra("dni"));


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtCodigo.setText("");
                edtNombres.setText("");
                edtCorreo.setText("");
                edtDireccion.setText("");
                edtCelular.setText("");
                edtDni.setText("");
                clienteactualizar = null;

                String text = "Se Limpiaron los Campos";
                String title = "INFO";

                MotionToast.Companion.darkToast(DetalleClienteActivity.this,title,text,
                        MotionToastStyle.WARNING,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(DetalleClienteActivity.this, www.sanju.motiontoast.R.font.helvetica_regular));

                finish();

            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    AppDB.getInstancia(DetalleClienteActivity.this)
                            .clientesbdDao().ActualizarCliente(codigo_cliente, edtNombres.getText().toString()
                            , edtCorreo.getText().toString(), edtDireccion.getText().toString(), edtCelular.getText().toString(), edtDni.getText().toString());

                    String text = "Cliente Actualizado Correctamente";
                    String title = "Operacion Exitosa";

                    MotionToast.Companion.darkToast(DetalleClienteActivity.this,title,text,
                            MotionToastStyle.SUCCESS,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(DetalleClienteActivity.this, www.sanju.motiontoast.R.font.helvetica_regular));


                    Intent i = new Intent(DetalleClienteActivity.this, ClientesActivity.class);
                    startActivity(i);

                }
                catch ( Exception error){
                    Log.e("error1234ga", error.getMessage());
                }



            }
        });


    }
    @Override
    public void onResume() {
        super.onResume();


        /*
        Intent i = new Intent(DetalleClienteActivity.this, DetalleClienteActivity.class);
        startActivity(i);*/

    }

}