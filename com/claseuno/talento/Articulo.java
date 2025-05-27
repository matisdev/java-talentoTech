package com.claseuno.talento;

/*TODO
 * ENCAPSULAR LOS DATOS SENSIBLES
 * PRIVATE ID, PRIVATE NOMBRE
 * METODO DE ACCESO
 */
public class Articulo {
    private static int count = 0;
    private int id;
    private String nombre;
    private double precio;

    public Articulo(String nombre, double precio) {
        this.id = count++;
        this.nombre = nombre;
        this.precio = precio;
    }

    void mostrarInfo() {
        System.out.println("ID: " + id + " | " + "Nombre: " + nombre + " | " + "Precio: $" + precio);
    }

    public int getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setPrecio(double precio) {  
        this.precio = precio;
    }
}
