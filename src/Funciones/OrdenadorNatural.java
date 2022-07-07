
package Funciones;

/**
 * Aqui tenemos una clase de ejemplo en la que implementamos el comparable, 
 * como vemos tiene un atributo por el cual vamos a establecer el orden natural
 * de nuestra clase. 
 * 
 * Solamente tenemos que implementar el método compareTo que 
 * en este caso al ser de un int con devolver una resta, si es negativo será menor
 * si es 0 será igual y si es positivo será mayor.
 * 
 * En caso de ser otra clase deberemos estipular nosotros y hacer una cadena de ifs,
 * en el caso de ser String es tan fácil como devolver una compareTo de los dos
 * Strings.
 * @author Migue
 */

public class OrdenadorNatural implements Comparable {

    private int atributoEjemplo;
    
    public OrdenadorNatural(){
        atributoEjemplo = 0;
    }
    
    @Override
    public int compareTo(Object o) {
        return this.getAtributoEjemplo()-((OrdenadorNatural)o).getAtributoEjemplo();
    }
    
    public int getAtributoEjemplo(){
        return this.atributoEjemplo;
    }
}
