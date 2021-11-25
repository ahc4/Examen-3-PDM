package com.ahc.examen3;

public class Producto
{
    // Atributos del objeto
    String id, nombre, costo, foto;

    // Constructor con parámetros
    public Producto(String id, String nombre, String costo, String foto)
    {
        this.id = id;
        this.nombre = nombre;
        this.costo = costo;
        this.foto = foto;
    }

    // Constructor sin parámetros
    public Producto() {}

    // Getters y Setters
    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCosto() { return costo; }

    public void setCosto(String costo) { this.costo = costo; }

    public String getFoto() { return foto; }

    public void setFoto(String foto) { this.foto = foto; }
}
