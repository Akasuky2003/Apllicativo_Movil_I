package com.example.proyectoobregon.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ObregonDBHelper extends SQLiteOpenHelper {

    //Creacion de tablas
   /*
    String Tabla_Clientes =
            "create table if not exists clientes("+
                    "cod_cliente integer primary key autoincrement,"+
                    "nombres varchar(30),"+
                    "correo varchar(30),"+
                    "direccion varchar(30),"+
                    "celular varchar(30),"+
                    "dni varchar(30),"+
                    "imagen integer);"; */





    public ObregonDBHelper(@Nullable Context context) {
        super(context, "BDFERRETERIA", null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
           /*
        db.execSQL(Tabla_Clientes);

        db.execSQL("insert into clientes(nombres, correo, direccion, celular, dni, imagen) " +
                "values('Cristofer Mejia','cristofer@gmail.com','Av.Capac 1130', '978319841', '75886652',1)");
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
