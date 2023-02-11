package com.example.proyectoobregon.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proyectoobregon.Entidades.Trabajadores;

import java.util.List;

@Dao
public interface TrabajadoresbdDao {
    @Insert
    void InsertarTrabajadores(Trabajadores objtrab);

    @Query("Update Trabajadores set nombres= :nombres , apellido_paterno= :apellidoPatero, apellido_materno=:apellidoMaterno," +
            " cod_dni =:dni ,correo=:Correo , direccion=:Direcion,celular=:telefono,cargo=:Cargo,sexo=:SEXO where cod_trabajadores = :Codigo")
    void ActualizarTrabajadores(int Codigo, String nombres, String apellidoPatero, String apellidoMaterno
            , int dni,String Correo,String Direcion ,int telefono, String Cargo, String SEXO);



    @Query("delete from Trabajadores where cod_trabajadores = :codigo")
    int EliminarTrabajadoresCodigo(int codigo);

    @Query("select * from Trabajadores")
    List<Trabajadores> ListarTrabajadores();

    @Query("select * from Trabajadores where cod_trabajadores = :codigo")
    Trabajadores BuscarTrabajadoresCodigo(int codigo);

}
