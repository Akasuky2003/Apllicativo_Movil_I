package com.example.proyectoobregon.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proyectoobregon.Entidades.Clientes;

import java.util.List;

@Dao
public interface ClientesbdDao {



    @Insert
    void InsertarClientes(Clientes objclie);

    @Query("Update clientes set nombres = :nombres, correo = :correo, direccion = :direccion, " +
            "celular = :celular, dni = :dni where cod_cliente = :codigo")
    void ActualizarCliente(int codigo, String nombres, String correo, String direccion, String celular, String dni);

    @Delete
    void EliminarCliente(Clientes objclie);




    @Query("delete from Clientes where cod_cliente = :codigo")
    void EliminarClienteCodigo(int codigo);

    @Query("select * from Clientes")
    List<Clientes> ListarClientes();


    @Query("select * from Clientes where nombres = :nomcliente")
    List<Clientes> ListarClienteNombre(String nomcliente);

    @Query("select * from Clientes where cod_cliente = :codigo")
    Clientes BuscarClienteCodigo(int codigo);



}
