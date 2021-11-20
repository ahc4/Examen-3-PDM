package com.ahc.examen3;

public class Producto
{
    int id, costo;
    String nombre, foto;

    public Producto(int id, String nombre, int costo, String foto)
    {
        this.id = id;
        this.nombre = nombre;
        this.costo = costo;
        this.foto = foto;
    }

    public Producto() {}

    public int getID()
    {
        return id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public int getCosto()
    {
        return costo;
    }

    public String getFoto()
    {
        return foto;
    }

}
