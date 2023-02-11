package com.example.proyectoobregon.Entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = "usuario", unique = true)})
public class Login
{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int cod_usuario;

    @NonNull
    private String usuario;

    @NonNull
    private String contrasenia;

    public Login() {
    }

    public Login(int cod_usuario, String usuario, String contrasenia) {
        this.cod_usuario = cod_usuario;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public int getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
