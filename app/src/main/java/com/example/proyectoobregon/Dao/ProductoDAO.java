package com.example.proyectoobregon.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proyectoobregon.Entidades.Producto;

import java.util.List;

@Dao
public interface ProductoDAO
{
    @Query("SELECT * FROM Producto")
    public List<Producto> listProducts();

    @Query("SELECT * FROM Producto where cod_producto = :codigo")
    public Producto listProduct(int codigo);

    @Insert
    public void insertProducto(Producto producto);

    @Update()
    public void updateProducto(Producto producto);

    @Query("UPDATE Producto SET nombre = :nombres, categoria = :categoria, marca = :marca, stock = :stock, precio = :precio where cod_producto = :cod_producto")
    public void updateProductoQuery(int cod_producto, String nombres, String categoria, String marca, int stock, double precio);

    @Delete
    public void deleteProducto(Producto producto);
}
