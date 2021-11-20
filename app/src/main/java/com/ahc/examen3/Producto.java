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

    public int getId()
    {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCosto()
    {
        return costo;
    }

    public void setCosto(int costo) { this.costo = costo; }

    public String getFoto()
    {
        return foto;
    }

    public void setFoto(String foto) { this.foto = foto; }
}
