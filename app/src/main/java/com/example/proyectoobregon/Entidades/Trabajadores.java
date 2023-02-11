package com.example.proyectoobregon.Entidades;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Trabajadores")
public class Trabajadores {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cod_trabajadores")
    @NonNull
    private int cod_trabajadores;
    //@ColumnInfo(name = "cod_usuario")
    //@NonNull
    private int cod_usuario;
    @ColumnInfo(name = "nombres")

    private String nombres;
    @ColumnInfo(name = "apellido_paterno")

    private String apellido_paterno;
    @ColumnInfo(name = "apellido_materno")

    private String apellido_materno;
    @ColumnInfo(name = "cod_dni")

    private int dni;
    @ColumnInfo(name = "correo")

    private String correo;
    @ColumnInfo(name = "direccion")

    private String direccion;
    @ColumnInfo(name = "celular")

    private int celular;
    @ColumnInfo(name = "cargo")

    private String cargo;
    @ColumnInfo(name = "sexo")
    private String sexo;

    public Trabajadores() {
    }

    public Trabajadores(String nombres, String apellido_paterno, String apellido_materno, int dni, String correo, String direccion, int celular, String cargo, String sexo) {
        this.nombres = nombres;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.dni = dni;
        this.correo = correo;
        this.direccion = direccion;
        this.celular = celular;
        this.cargo = cargo;
        this.sexo = sexo;
    }

    public Trabajadores(int cod_trabajadores, int cod_usuario, String nombres, String apellido_paterno, String apellido_materno, int dni, String correo, String direccion, int celular, String cargo, String sexo) {
        this.cod_trabajadores = cod_trabajadores;
        //this.cod_usuario = cod_usuario;
        this.nombres = nombres;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.dni = dni;
        this.correo = correo;
        this.direccion = direccion;
        this.celular = celular;
        this.cargo = cargo;
        this.sexo = sexo;
    }

    public Trabajadores(String nombres, String apellido_paterno, String apellido_materno, String toString, int parseInt, String direccion, int celular, String cargo, String sexo) {
    }

    public int getCod_trabajadores() {
        return cod_trabajadores;
    }

    public void setCod_trabajadores(int cod_trabajadores) {
        this.cod_trabajadores = cod_trabajadores;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }
}
