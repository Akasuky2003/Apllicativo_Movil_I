package com.example.proyectoobregon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.CaseMap;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoobregon.Controlador.ListadoClientes;
import com.example.proyectoobregon.Database.AppDB;

import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;

public class MenuPrincipalActivity extends AppCompatActivity {

    LinearLayout llpagos, llayuda, llsalir;
    ConstraintLayout clClientes, clProductos,clTrabajadores, clUsuarios, clContacto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        EnlazarCOntroles();

        llsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showWarningAlertDialog();

                /*
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MenuPrincipalActivity.this);
                builder1.setCancelable(false);
                builder1.setTitle("¿Esta seguro que quiere Cerrar Sesion?");


                builder1.setPositiveButton(
                        "No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                Intent i = new Intent(MenuPrincipalActivity.this,
                                        LoginActivity.class);
                                startActivity(i);

                            }
                        });
                builder1.show();*/


            }
        });
        llayuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MenuPrincipalActivity.this, AyudaActivity.class));
            }
        });
        llpagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ESTO REDIRIGE AL ACTIVTY DE PAGOS (ALQUILER MENSUAL POR LA APLICACION)
            }
        });

        clClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuPrincipalActivity.this, ClientesActivity.class);
                startActivity(i);
            }
        });

        clProductos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MenuPrincipalActivity.this, ProductoActivity.class));
            }
        });
        clUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipalActivity.this, UsuarioActivity.class));

            }
        });

        clTrabajadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipalActivity.this, TrabajadorActivity.class));

            }
        });


    }

    private void EnlazarCOntroles() {
        llpagos = findViewById(R.id.llPagos);
        llayuda = findViewById(R.id.llAyuda);
        llsalir = findViewById(R.id.llSalir);

        clClientes = findViewById(R.id.clClientes);
        clProductos = findViewById(R.id.clProductos);
        clTrabajadores = findViewById(R.id.clTrabajadores);
        clUsuarios = findViewById(R.id.clUsuarios);
        clContacto = findViewById(R.id.clContacto);
    }

    public void showWarningAlertDialog(){


        AlertDialog.Builder builder = new AlertDialog.Builder(MenuPrincipalActivity.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(MenuPrincipalActivity.this).inflate(
                R.layout.layout_warning_dialog, (ConstraintLayout)findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText("Warning");
        ((TextView) view.findViewById(R.id.textMessage)).setText("¿Esta seguro que quiere Cerrar Sesion?");
        ((Button) view.findViewById(R.id.buttonYes)).setText("Yes");
        ((Button) view.findViewById(R.id.buttonNo)).setText("No");
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.warning);

        final AlertDialog alertDialog = builder.create();
        view.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

                ProgressDialog pd = ProgressDialog.show(
                        MenuPrincipalActivity.this,
                        "Espere un Momento",
                        "Cerrando Sesion",
                        true
                );

                Thread hilo = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            Thread.sleep(1000);

                            //runOnUiThread();
                        } catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        pd.dismiss();
                    }
                });
                hilo.start();

                MotionToast.Companion.darkToast(MenuPrincipalActivity.this,"Success","Sesion Cerrada Exitosamente",
                        MotionToastStyle.SUCCESS,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(MenuPrincipalActivity.this, www.sanju.motiontoast.R.font.helvetica_regular));


                Intent i = new Intent(MenuPrincipalActivity.this,
                        LoginActivity.class);
                startActivity(i);


            }
        });
        view.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

                MotionToast.Companion.darkToast(MenuPrincipalActivity.this,"INFO","Operacion Cancelada",
                        MotionToastStyle.INFO,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(MenuPrincipalActivity.this, www.sanju.motiontoast.R.font.helvetica_regular));
            }
        });



        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();

    }




    //ESTO ES PARA SUCCESS
    public void showSuccessAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuPrincipalActivity.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(MenuPrincipalActivity.this).inflate(
                R.layout.layout_success_dialog, (ConstraintLayout)findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText("Success Title");
        ((TextView) view.findViewById(R.id.textMessage)).setText("Este es una mensaje largo");
        ((Button) view.findViewById(R.id.buttonAction)).setText("Okay");
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.done);

        final AlertDialog alertDialog = builder.create();
        view.findViewById(R.id.buttonAction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

                MotionToast.Companion.darkToast(MenuPrincipalActivity.this,"Sucess","Se proceso",
                        MotionToastStyle.SUCCESS,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(MenuPrincipalActivity.this, www.sanju.motiontoast.R.font.helvetica_regular));
            }
        });

        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();

    }





}