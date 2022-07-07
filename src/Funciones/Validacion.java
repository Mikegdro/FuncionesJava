package Funciones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Funciones para validar cualquier tipo de documento de identificación valido
 * en España, una contraseña y un usuario.
 * @author Migue
 */
public class Validacion {
    
    /**
     * Separa los NIF/NIE de los CIF, el NIE lo pasa como un DNI pero cambiandole
     * el dígito inicial
     *
     * @param nif NIF/CIF/NIE a validar
     * @return boolean
     */
    public static boolean validarId(String nif) {
        Pattern NIE = Pattern.compile("^([XYZ])([0-9]{6,7})([A-Z])$");
        Pattern CIF = Pattern.compile("^([A-W][^a-z])([0-9]{6,7})([A-Z]?)$");
        Pattern NIF = Pattern.compile("^([0-9]{8})([A-Z])$");
        Matcher matchNIE = NIE.matcher(nif);
        Matcher matchCIF = CIF.matcher(nif);
        Matcher matchNIF = NIF.matcher(nif);

        boolean validez;
        char inicio = nif.charAt(0);

        //comprobar si es nif o nie
        if(matchNIF.matches()){
            validez = validarNif(nif);
        }else if(matchNIE.matches()){
            String nie;
            switch (inicio) {
                case 'X' ->{
                    nie = "0" + nif.substring(1);
                }
                case 'Y' -> {
                    nie = "1" + nif.substring(1);
                }
                default->{
                    nie = "2" + nif.substring(1);    
                }    
            }
            validez = validarNif(nie);
        }else if(matchCIF.matches()){
            validez = validarCif(nif);
        }else{
            validez = false;
        }
        return validez;
    }

    /**
     * Valida el NIF y el NIE, con un patrón para la estructura y con un
     * algoritmo para la letra de control.
     * @param nif NIF/NIE de entrada
     * @return boolean
     */
    private static boolean validarNif(String nif){
        Pattern dni = Pattern.compile("([XYZ]?)([0-9]{1,9})([A-Za-z])");
        Matcher d = dni.matcher(nif);

        String cadenaValidadora = "TRWAGMYFPDXBNJZSQVHLCKE";
        boolean valido = false;

        int numero = Integer.parseInt(nif.substring(0, 8)) % 23;

        if(nif.endsWith(Character.toString(cadenaValidadora.charAt(numero)))){
            valido = true;
        }

        return d.matches() && valido;
    }

    /**
     * Valida el CIF de una empresa algorítmicamente, tanto con letra al final
     * como sin letra al final.
     * @param cif CIF a validar
     * @return boolean
     */
    private static boolean validarCif(String cif){
        //Variables
        String caracteres = "ABCDEFGHIJ";
        int sumaPares = 0;
        int sumaImpares = 0;
        boolean validez;
        char charFinal = cif.charAt(8);
        Pattern cifVal = Pattern.compile("^([A-W][^a-z])([0-9]{6,7})([A-Z]?)$");
        Matcher m = cifVal.matcher(cif);

        /*
         * Bucle que valida un CIF, separa los pares de los impares, los pares los suma en una variable,
         * los impares los multiplica por 2 y si son de 2 digitos, suma esos digitos y el resultado lo
         * suma a una variable.
         */
        for(int i=1; i < 8; i++){
            var conversionCharEntero = Integer.parseInt(Character.toString(cif.charAt(i)));
            if(i%2 == 0){
                sumaPares += conversionCharEntero;
            }else{
                if(conversionCharEntero * 2 >= 10){
                    int digito1 = (conversionCharEntero * 2) / 10;
                    int digito2 = (conversionCharEntero * 2) % 10;
                    sumaImpares += digito1 + digito2;
                }else{
                    sumaImpares += conversionCharEntero * 2;
                }
            }
        }

        String suma = "" + (sumaPares + sumaImpares);
        int digito = Integer.parseInt(Character.toString(suma.charAt(suma.length()-1)));
        digito = 10 - digito;

        /*
         * Un CIF puede acabar en número o caracter, si acaba en caracter, el número final ha de coincidir
         * con la letra correcta del String [caracteres], y si es un digito, el cálculo ha de coincidir
         * con el digito final del CIF.
         */
        if(Character.isAlphabetic(charFinal)){
            digito--;
            validez = Character.toString(caracteres.charAt(digito)).equals(Character.toString(charFinal));
        }else{
            String digitoFinal = ""+digito;
            validez = Character.toString(charFinal).equals(digitoFinal);
        }
        return validez && m.matches();
    }

    /**
     * Utiliza una regex con positive LookAhead, mínimo 2 dígitos, 1 minúscula 1
     * mayúscula, 8 carácteres y máximo 20 carácteres. No acepta espacios.
     *
     * @param password Contraseña que recibe y valida
     * @return boolean
     */
    public static boolean validarPassword(String password) {
        Pattern pass = Pattern.compile("^(?=.+[0-9]{2,})(?=.+[a-z])(?=.+[A-Z])(?=\\S+$).{8,20}$");
        Matcher p = pass.matcher(password);
        return p.matches();
    }

    /**
     * Utiliza una regex con positive lookahead, 1 minúscula, 1 mayúscula y
     * mínimo 10 carácteres, máximo 100.
     *
     * @param titular Nombre y apellidos a validar
     * @return boolean
     */
    public static boolean validarTitular(String titular) {
        Pattern nombre = Pattern.compile("^(?=.+[a-zA-Z]).{10,100}$");
        Matcher m = nombre.matcher(titular);
        return m.matches();
    }
}
