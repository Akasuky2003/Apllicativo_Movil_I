package com.example.proyectoobregon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.proyectoobregon.Database.AppDB;
import com.example.proyectoobregon.Utilitarios.Variables;

import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;

public class DetalleTrabajadorActivity extends AppCompatActivity {

    EditText edtCodigo,edtNombres,edtApellidoPaterno,edtApellidoMaterno,edtDni,edtCorreo,edtTelefono,edtDirecion;
    Spinner spinGenero,spinCargo;
    int codigo_TRABAJADOR;
    Button btnmodifiCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_trabajador);

        EnlazarContorles();
        ArrayAdapter<String> adapterspin = new ArrayAdapter<String>(
                DetalleTrabajadorActivity.this,
                android.R.layout.simple_dropdown_item_1line,
                Variables.vGenero
        );
        spinGenero.setAdapter(adapterspin);
        ArrayAdapter<String> adapterspinu = new ArrayAdapter<String>(
                DetalleTrabajadorActivity.this,
                android.R.layout.simple_dropdown_item_1line,
                Variables.vCargo
        );
        spinCargo.setAdapter(adapterspinu);

        codigo_TRABAJADOR=Integer.parseInt(getIntent().getStringExtra("Codigo"));
        edtCodigo.setText("Cl"+getIntent().getStringExtra("Codigo"));
        edtNombres.setText(getIntent().getStringExtra("Nombre"));
        edtApellidoPaterno.setText(getIntent().getStringExtra("Apellido_Paterno"));
        edtApellidoMaterno.setText(getIntent().getStringExtra("Apellido_Materno"));
        edtDni.setText(getIntent().getStringExtra("DNI"));
        edtDirecion.setText(getIntent().getStringExtra("Direccion"));
        edtTelefono.setText(getIntent().getStringExtra("Telefono"));




        btnmodifiCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    AppDB.getInstancia(DetalleTrabajadorActivity.this)
                            .trabajadoresbdDao().ActualizarTrabajadores(codigo_TRABAJADOR,edtNombres.getText().toString(),edtApellidoPaterno.getText().toString(),edtApellidoMaterno.getText().toString(),Integer.parseInt(edtDni.getText().toString())
                            ,edtCorreo.getText().toString(),edtDirecion.getText().toString(),Integer.parseInt(edtTelefono.getText().toString()),spinCargo.getSelectedItem().toString(),spinGenero.getSelectedItem().toString());

                    String text = "Trabajador Modificado Correctamente";
                    String title = "Operacion Exitosa";

                    MotionToast.Companion.darkToast(DetalleTrabajadorActivity.this,title,text,
                            MotionToastStyle.SUCCESS,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(DetalleTrabajadorActivity.this, www.sanju.motiontoast.R.font.helvetica_regular));


                    Intent i = new Intent(DetalleTrabajadorActivity.this, TrabajadorActivity.class);
                    startActivity(i);
                    finish();
                }
                catch ( Exception error){
                    Log.e("error1234ga", error.getMessage());
                }


            }
        });
    }
    private void EnlazarContorles(){
        edtCodigo=findViewById(R.id.edtDetalleCodigo);
        edtNombres=findViewById(R.id.edtDetalleNombre);
        edtApellidoPaterno=findViewById(R.id.edtDetalleApellidoPaterno);
        edtApellidoMaterno=findViewById(R.id.edtDetalleApellidoMaterno);
        edtDni=findViewById(R.id.edtDetalleDNI);
        edtCorreo=findViewById(R.id.edtDetalleCorreoElectronicoTrabjador);
        edtTelefono=findViewById(R.id.edtDetalleTelefono);
        edtDirecion=findViewById(R.id.edtDetalleDirecion);


        spinGenero=findViewById(R.id.spinDetalleGenero);
        spinCargo=findViewById(R.id.spinDetalleCargo);
        btnmodifiCar=findViewById(R.id.btnModficarTrabajador);

    }

}