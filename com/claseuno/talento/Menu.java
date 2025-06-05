package com.claseuno.talento;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {


        try( Scanner sc = new Scanner(System.in);){

        int opcion=0;
        List<Articulo> articulos =  new ArrayList<Articulo>();

        do {
            System.out.println("*MENU*\n1. Crear un articulo nuevo\n2. Cosultar un articulo\n3. Listar articulos\n4. Modificar un articulo\n5. Borrar un articulo\n6. Salir");

            try{

                opcion = Integer.parseInt(sc.nextLine());
    
                switch (opcion) {
                    case 1 -> crearArticulo(articulos, sc);
                    case 2 -> consultarArticulo(articulos,sc);
                    case 3 -> listarArticulos(articulos);
                    case 4 -> modificarArticulo(articulos, sc);
                    case 5 -> eliminarArticulo(articulos, sc); 
                    default -> {
                        if (opcion != 6) {
                            System.out.println("Opcion incorrecta.");
                        }
                    }
                }
            } catch(NumberFormatException e) {
                System.out.println("Debe ingresar un numero valido.");
            }
        } while (opcion != 6);

        System.out.println("Programa finalizado.");
        
        } 

    }

    public static void crearArticulo(List<Articulo> articulos, Scanner sc){
        //solicitar nombre
        System.out.printf("Ingresa nombre del articulo: ");
        String articuloNombre = sc.nextLine().trim();

        while(articuloNombre.isEmpty()) {
            System.out.printf("Debe ingresar un nombre: ");
            articuloNombre = sc.nextLine().trim();
        }

        //Solicitar precio
        System.out.printf("Ingrese precio $: ");
        boolean precioValido = false;
        int articuloPrecio = 0;

        //validacion de numeros
        while(!precioValido){
            String inputPrecio = sc.nextLine();

            if(inputPrecio.isEmpty()){
                System.out.println("Debe ingresar un precio");
            };

            //Manejo de excepciones para entradas no numericas(try-catch)
            try {
                articuloPrecio = Integer.parseInt(inputPrecio);

                if(articuloPrecio == 0 ){
                    System.out.printf("El precio debe ser mayor a 0.\nIngrese precio: ");
                } else if(articuloPrecio < 0){
                    System.out.printf("El precio debe ser un numero positivo. \nIngrese precio: ");
                } else {
                    precioValido = true;
                }

            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero valido\nIngrese precio: ");
            }
            
        }

        Articulo newArticulo = new Articulo(articuloNombre, articuloPrecio);
        articulos.add(newArticulo);
    }

    public static void consultarArticulo(List<Articulo> articulos, Scanner sc){
        if (articulos.size()>0) {
            listarArticulos(articulos);
        }else{
            System.out.println("No hay articulos.");
            return;
        }

        //solicitar el numero del articulo
        System.out.println("Que articulo desea consultar?(Ingrese numero)");
      
        boolean numeroValido=false;
        int index = 0;

        //Validaciones numericas
        while (!numeroValido) {
            String input = sc.nextLine().trim();

            if (input.isEmpty()){
                System.out.println("Debe ingresar un el ID del articulo");
            }
            
            try {
                index = Integer.parseInt(input);
                if (index < 0) {
                    System.out.println("El numero debe ser mayor o igual 0;");
                }else{
                    numeroValido = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero valido\nIngrese ID: ");
            }
        }
        for (Articulo articulo : articulos) {
            if(articulo.getId() == index){
                articulo.mostrarInfo();
                return;
            }
        } 
        System.out.println("Articulo no encontrado");
    }

    public static void listarArticulos(List<Articulo> articulos) {
        System.out.println("Lista de articulos");

        if(articulos.size()>0){
            for (Articulo articulo : articulos) {
                articulo.mostrarInfo();
            }
        } else {
            System.out.println("No hay articulos para mostrar.");
        }
    }

    public static void modificarArticulo(List<Articulo> articulos, Scanner sc) {
        System.out.println("Modificar un articulo");
        listarArticulos(articulos);

        int idModificar =Integer.parseInt(sc.nextLine());
        Articulo articuloModificar = null;
        for (Articulo articulo : articulos) {
            System.out.println("input id "+idModificar);
            if(articulo.getId() == idModificar){
                articuloModificar = articulo;
                break;
            };
        }

        if (articuloModificar == null) {
            System.out.println("No se encontro el articulo");
            return;
        }

        System.out.println("Nuevo nombre (Dejar vacio para no cambiar): ");
        String nombreNuevo = sc.nextLine();
        if(!nombreNuevo.trim().isEmpty()){
            articuloModificar.setNombre(nombreNuevo);
        }

        System.out.println("Nuevo Precio (Dejar vacio para no cambiar): ");
        String precioNuevo = sc.nextLine();
        if (!precioNuevo.trim().isEmpty()){
            try {
                int intPrecioNuevo = Integer.parseInt(precioNuevo); 
                if(intPrecioNuevo>0){
                    articuloModificar.setPrecio(intPrecioNuevo);
                }else {
                    System.out.println("El precio debe ser mayor a 0. No se modifico el precio.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Numero invalido. No se modifico el precio.");
            }
        }

        System.out.println("Articulo modificado: ");
        articuloModificar.mostrarInfo();

    }

    public static void eliminarArticulo(List<Articulo> articulos, Scanner sc) {
        
        if (articulos.size()>0) {
            System.out.println("Que articulo desea eliminar? (ingrese ID)");
            listarArticulos(articulos);
        }else{
            System.out.println("No hay articulos.");
            return;
        }
        
        int index = 0;
        boolean numeroValido = false;

        while (!numeroValido) {
            String input = sc.nextLine();

            try {
                index = Integer.parseInt(input);
                
                if(index >=0 && index < articulos.size()){
                    numeroValido = true;
                } else {
                    System.out.println("ID fuera del rango");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero");
            } catch (NullPointerException e) {
                System.out.println("Debe ingresar un ID valido");
            }
        }
        // buscamos sobre los articulos
        // variable lambda debe ser final no puede cambiar
        final int idAEliminar = index;
        
        // metodo removeIf
        // lambda -> recorre cada articulo hasta coincidir con el id a eliminar
        Articulo articuloEliminado = null;
        for (Articulo articulo : articulos) {
            if(articulo.getId() == idAEliminar){
                articuloEliminado = articulo;
            }
        }

        articulos.removeIf(articulo -> articulo.getId() == idAEliminar);
        System.out.printf("Articulo eliminado -> ");
        articuloEliminado.mostrarInfo();
        //articulos.remove(Integer.parseInt(index) - 1);11
        
    }
}

