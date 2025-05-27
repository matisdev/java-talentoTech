package com.claseuno.talento;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class practica {
    public static void main(String[] args) {

        // System.out.println("Ingrese nombres... ");
        /*
         * String datosIngresados = sc.nextLine();
         * 
         * System.out.println("Ingrese su edad...");
         * String edadIngresada = sc.nextLine();
         * 
         * int edadToInt = Integer.parseInt(edadIngresada);
         * 
         * System.out.println("El nombre ingresado por el usuario es: " +
         * datosIngresados);
         * 
         * System.out.println("La edad ingresada por el usuario es: " + edadToInt);
         */

        List<String> names = new ArrayList<>();

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println(
                    "MENU: \n0. Presione cualquier tecla para continuar\n1. 'salir' para terminar el programa");
            while (!sc.nextLine().equalsIgnoreCase("salir")){
                for (int i = 0; i < 10; i++) {
                    System.out.printf("Ingrese nombre %d/%d ", i + 1, 10);
                    String name = sc.nextLine().trim();

                    if (!name.isEmpty()) {
                        names.add(name);
                    } else {
                        System.out.println("No ingreso ningun nombre, intente nuevamente");
                        i--;
                    }
                }
                System.out.println("\nNombres ingresados:");
                names.forEach(System.out::println);

                System.out.println("Presione 'salir' para terminar el programa.");

            }
        }

    }
}