package com.claseuno.talento;

import java.util.Scanner;

public class Arrays {
    public static void main(String[] args) {
        int[] arrayNumber = new int[5];

        System.out.println("Ingrese 5 numeros");
        try (Scanner sc = new Scanner(System.in);) {

            for (int i = 0; i < 5; i++) {
                System.out.print("Ingrese numero " + (i+1) + ": " );
                arrayNumber[i] = sc.nextInt();
            }

            System.out.println("Numero ingresados: ");
            for (int i : arrayNumber) {
                System.out.println(i);
            }
        }

    }
}
