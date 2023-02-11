package com.example.proyectoobregon.Dao;

import com.example.proyectoobregon.ClientesActivity;
import com.example.proyectoobregon.Entidades.Clientes;

import java.util.ArrayList;
import java.util.List;

public class ClientesDAO {

    ClientesbdDao daobd;


    private static List<Clientes> lista = new ArrayList<>();

    public List<Clientes> Listar_Clientes(){
        return lista;
    }

    public String grabarCliente(Clientes clientes)
    {
        if (!validarCodigo(clientes.getCod_cliente()))
            return "Ya existe el codigo de cliente!";

        lista.add(clientes);
        return clientes.getNombres() + " fue agregado correctamente";
    }

    private boolean validarCodigo(int codigo)
    {
        for (Clientes v: lista)
        {
            if (v.getCod_cliente() == codigo) return false;
        }

        return true;
    }


    public Clientes ClienteBuscar(int codigo){
        for (Clientes clie: daobd.ListarClientes()) {
            if (clie.getCod_cliente() == codigo)
                return clie;
        }
        //
        return null;
    }
    /*
    public String ClienteEliminar(int codigo)
    {
        Clientes buscado = ClienteBuscar(codigo);
        if (buscado!=null)
        {
            daobd.EliminarCliente(buscado);
            //
            return "Alumno Eliminado correctamente";
        }
        //
        return "Alumno No Encontrado";
    }

    public String ClienteEliminarCodigo(int codigo)
    {
        daobd.EliminarClienteCodigo(codigo);

        return "Cliente Eliminado";
    }

    public List<Clientes> ClienteListar()
    {
        return lista;
    }
*/
}
