
package Funciones;

import java.util.Comparator;

/**
 * En el caso de String se puede devolver un compareTo
 * que devuelve el número que alfabéticamente es mayor.
 * 
 * Si es un número se puede devolver una resta entre ellos obj1-obj2
 * que sera negativo si obj2 es mayor, 0 si son iguales o positivo si
 * obj1 es mayor.
 * 
 * Para invocarlo se debe de hacer un Collections.sort(objecto, new Comparador<>())
 * Cambiar el objecto String a antojo para comparar cualquier tipo de objeto y su
 * ordenación.
 * @author Migue
 */
public class Comparador implements Comparator<String> {

    @Override
    public int compare(String obj1, String obj2){
        return obj1.compareTo(obj2);
    }
    
}
