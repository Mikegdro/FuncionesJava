
package Funciones;

/**
 * Funciones para validar datos de una cuenta bancaria como el CC o obtener sus
 * digitos de control
 * @author Migue
 */
public class DatosCC {
    
    /**
     * Se le pasan la entidad, oficina y numCuenta, para que calcule los números
     * de control.
     *
     * @param entidad Entidad bancaria
     * @param oficina Oficina bancaria
     * @param numCuenta Número de cuenta del usuario
     * @return DC Dígitos de control
     */
    public static String obtenerDigitosControl(String entidad, String oficina, String numCuenta) {
        //Variables
        int [] pesos = {1, 2, 4, 8, 5, 10, 9, 7, 3, 6};
        int sumad1 = 0;
        int sumad2 = 0;
        String cad1 = "00" + entidad + oficina;

        //Recorre el array multiplicando por los pesos y los mete en su variable
        for (int i = 0; i < pesos.length; i++) {
            sumad1 +=  Integer.parseInt(Character.toString(cad1.charAt(i))) * pesos[i];
            sumad2 +=  Integer.parseInt(Character.toString(numCuenta.charAt(i))) * pesos[i];
        }

        //Realiza los calculos necesarios y cambia la variable a 0 o 1 si son 10 o 11
        sumad1 = 11 - sumad1 % 11;
        sumad1 = (sumad1 == 10) ? 1 : sumad1;
        sumad1 = (sumad1 == 11) ? 0 : sumad1;
        System.out.println(sumad1);

        sumad2 = 11 - sumad2 % 11;
        sumad2 = (sumad2 == 10) ? 1 : sumad2;
        sumad2 = (sumad2 == 11) ? 0 : sumad2;
        System.out.println(sumad2);

        //Devuelve los números de control
        String DC = "" + sumad1 + sumad2;
        return DC;
    }

    /**
     * Recibe un CCC de cuenta, y llama a la función obtenerDigitosControl, esta
     * devuelve esos digitos calculados, y los compara con los de la cuenta.
     *
     * @param CCC Cuenta del usuario compuesta
     * @return boolean
     */
    public static boolean comprobarCCC(String CCC) {
        String DC = obtenerDigitosControl(CCC.substring(0, 4), CCC.substring(4, 8), CCC.substring(10));
        return DC.equals(CCC.substring(8, 10));
    }

}
