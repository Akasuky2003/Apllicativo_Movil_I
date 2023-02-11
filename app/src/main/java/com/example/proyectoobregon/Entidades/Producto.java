package com.example.proyectoobregon.Entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Producto
{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int cod_producto;
    private String nombre;
    private String categoria;
    private String marca;

    @ColumnInfo(defaultValue = "0")
    private int stock;

    @ColumnInfo(defaultValue = "0.0")
    private double precio;

    @ColumnInfo(defaultValue = "true")
    private boolean estado;

    public Producto() {
    }

    public Producto(int cod_producto, String nombre, String categoria, String marca, int stock, double precio, boolean estado)
    {
        this.cod_producto = cod_producto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.marca = marca;
        this.stock = stock;
        this.precio = precio;
        this.estado = estado;
    }

    public int getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(int cod_producto) {
        this.cod_producto = cod_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
