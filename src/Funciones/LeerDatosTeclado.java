package Funciones;

import java.util.Scanner;

/**
 * Funciones para validar la entrada con un Scanner por parte del usuario
 *
 * @author Migue
 */
public class LeerDatosTeclado {

    public static int leerEntero(String mensaje) {
        int numero;
        Scanner input = new Scanner(System.in);
        System.out.println(mensaje);
        while (!input.hasNextInt()) {
            System.out.println("Ese número no me sirve");
            input.next();
        }
        numero = input.nextInt();
        input.close();
        return numero;
    }

    public static int leerEntero(String mensaje, int minimo) {
        int numero;
        Scanner input = new Scanner(System.in);

        System.out.println(mensaje);

        do {
            while (!input.hasNextInt()) {
                System.out.println("Ese número no me sirve");
                input.next();
            }
            numero = input.nextInt();
        } while (numero < minimo);

        input.close();
        return numero;
    }

    public static int leerEntero(String mensaje, int minimo, int maximo) {
        int numero;
        Scanner input = new Scanner(System.in);

        System.out.println(mensaje);

        do {
            while (!input.hasNextInt()) {
                System.out.println("Ese número no me sirve");
                input.next();
            }
            numero = input.nextInt();
        } while (numero > maximo || numero < minimo);

        input.close();
        return numero;
    }

    public static double leerDouble(String mensaje) {
        double numero;
        Scanner input = new Scanner(System.in);
        
        System.out.println(mensaje);
        
        while (!input.hasNextDouble()) {
            System.out.println("Ese número no me sirve");
            input.next();
        }
        numero = input.nextDouble();
        
        input.close();
        return numero;
    }

    public static double leerDouble(String mensaje, double minimo) {
        double numero;
        Scanner input = new Scanner(System.in);
        
        System.out.println(mensaje);

        do {
            while (!input.hasNextDouble()) {
                System.out.println("Ese número no me sirve");
                input.next();
            }
            numero = input.nextDouble();
        } while (numero < minimo);
        
        input.close();
        return numero;
    }

    public static float leerFloat(String mensaje, float minimo, float maximo) {
        Scanner input = new Scanner(System.in);
        float numero;
        
        System.out.println(mensaje);
        
        do {
            while (!input.hasNextFloat()) {
                System.out.println("Ese número no me sirve");
                input.next();
            }
            numero = input.nextFloat();
        } while (!(numero < minimo) && !(numero > maximo));
        
        input.close();
        return numero;
    }

    public static float leerFloat(String mensaje, float minimo) {
        Scanner input = new Scanner(System.in);
        float numero;
        
        System.out.println(mensaje);
        
        do {
            while (!input.hasNextFloat()) {
                System.out.println("Ese número no me sirve");
                input.next();
            }
            numero = input.nextFloat();
        } while (numero < minimo);
        
        input.close();
        return numero;
    }

    public static String leerString(String mensaje, String condicion1, String condicion2) {
        Scanner input = new Scanner(System.in);
        String recu;
        
        System.out.println(mensaje);
        
        do {
            recu = input.next().toLowerCase();
        } while (!recu.equals(condicion1) && !recu.equals(condicion2));

        input.close();
        return recu;
    }

    public static String leerString(String mensaje, String condicion1) {
        Scanner input = new Scanner(System.in);
        String recu;
        
        System.out.println(mensaje);
        
        do {
            recu = input.next().toLowerCase();
        } while (!recu.equals(condicion1));

        input.close();
        return recu;
    }

    public static String leerString(String mensaje, String condicion1, String condicion2, String condicion3) {
        Scanner input = new Scanner(System.in);
        String recu;
        
        System.out.println(mensaje);
        
        do {
            recu = input.next().toLowerCase();
        } while (!recu.equals(condicion1) && !recu.equals(condicion2) && !recu.equals(condicion3));

        input.close();
        return recu;
    }

    public static String leerString(String mensaje) {
        Scanner input = new Scanner(System.in);
        String recu;
        System.out.println(mensaje);
        recu = input.nextLine();

        input.close();
        return recu;
    }

    public static char leerChar(String mensaje, char condicion1, char condicion2) {
        Scanner input = new Scanner(System.in);
        char letra;

        System.out.println(mensaje);
        do {
            letra = input.next().charAt(0);
        } while (letra != condicion1 && letra != condicion2);

        input.close();
        return letra;
    }

    public static char leerChar(String mensaje) {
        Scanner input = new Scanner(System.in);

        char letra;
        System.out.println(mensaje);
        letra = input.nextLine().charAt(0);

        input.close();
        return letra;
    }
}
