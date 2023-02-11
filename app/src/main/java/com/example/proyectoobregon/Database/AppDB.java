package com.example.proyectoobregon.Database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.proyectoobregon.Dao.ClientesbdDao;
import com.example.proyectoobregon.Dao.LoginDAO;
import com.example.proyectoobregon.Dao.ProductoDAO;
import com.example.proyectoobregon.Dao.TrabajadoresbdDao;
import com.example.proyectoobregon.Entidades.Clientes;
import com.example.proyectoobregon.Entidades.Login;
import com.example.proyectoobregon.Entidades.Producto;
import com.example.proyectoobregon.Entidades.Trabajadores;

@Database(entities = {Clientes.class, Login.class, Producto.class, Trabajadores.class}, version = 1, exportSchema = false)
public  abstract class AppDB extends RoomDatabase {

    private static AppDB Instancia;

    public abstract ClientesbdDao clientesbdDao();
    public abstract TrabajadoresbdDao trabajadoresbdDao();
    public abstract LoginDAO loginDAO();
    public abstract ProductoDAO productoDAO();

    public static AppDB getInstancia(Context contexto)
    {
        // si es la 1ra vez que se instancia a esta clase
        if (Instancia == null)
        {
            // crearemos el room
            Instancia = Room.databaseBuilder(
                    contexto,
                    AppDB.class,
                    "ObregonFerreteriaBDPruebamm")
                    .allowMainThreadQueries()
                    .build();
        }
        return Instancia;
    }

}
