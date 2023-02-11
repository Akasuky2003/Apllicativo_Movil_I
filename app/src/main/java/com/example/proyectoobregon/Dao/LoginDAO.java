package com.example.proyectoobregon.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proyectoobregon.Entidades.Login;

import java.util.List;

@Dao
public interface LoginDAO
{
    @Query("SELECT * FROM Login")
    List<Login> obtenerLogins();

    @Query("SELECT * FROM login where usuario = :usuario And contrasenia = :contrasenia")
    Login obtenerLogin(String usuario, String contrasenia);

    @Insert
    void insertarLogin(Login login);

    @Update
    void updateLogin(Login login);

    @Delete
    void deleteLogin(Login login);


}
