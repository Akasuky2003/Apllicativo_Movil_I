package com.example.proyectoobregon.Entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Clientes")
public class Clientes {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cod_cliente")
    @NonNull
    private int cod_cliente;

    @ColumnInfo(name = "nombres")
    private String nombres;

    @ColumnInfo(name = "correo")
    private String correo;

    @ColumnInfo(name = "direccion")
    private String direccion;

    @ColumnInfo(name = "celular")
    private String celular;

    @ColumnInfo(name = "dni")
    private String dni;

    public Clientes() {
    }

    public Clientes(String nombres, String correo, String direccion, String celular, String dni) {
        this.nombres = nombres;
        this.correo = correo;
        this.direccion = direccion;
        this.celular = celular;
        this.dni = dni;
    }

    public Clientes(int cod_cliente, String nombres, String correo, String direccion, String celular, String dni) {
        this.cod_cliente = cod_cliente;
        this.nombres = nombres;
        this.correo = correo;
        this.direccion = direccion;
        this.celular = celular;
        this.dni = dni;
    }

    public int getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

}
