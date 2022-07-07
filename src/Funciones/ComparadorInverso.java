
package Funciones;

import java.util.Comparator;

public class ComparadorInverso implements Comparator {

    /**
     * También se puede implementar un metodo INVERSE, pero no lo he probado
     * así que mejor es usar esto por ahora.
     * 
     * Con ints se puede simplemente poner un paréntesis más y un negativo de-
     * lante.
     * 
     * Se usa una negación delante de todo el conjunto, para que aunque se 
     * comparen String, se invierta el resultado.
     * 
     * @param o1
     * @param o2
     * @return 
     */
    @Override
    public int compare(Object o1, Object o2) {
        return -(((int)o1)-((int)o2));
    }

}
