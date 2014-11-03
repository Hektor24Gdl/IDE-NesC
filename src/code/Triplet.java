package code;

/**
 * Clase generica para almacenar una terna de informacion. 
 */
public class Triplet<T1,T2, T3> {
    private T1 Object1;
    private T2 Object2;
    private T3 Object3;
    
    public Triplet(T1 objet1, T2 objet2, T3 objet3){
        this.Object1 = objet1;
        this.Object2 = objet2;
        this.Object3 = objet3;
    }
    
    /**
     * Metodo que devuelve el Objeto1 de la Tripleta.
     * @return primer objeto de la terna.
     */
    public T1 get1(){
        return Object1;
    }
    
    /**
     * Metodo que devuelve el Objeto2 de la Tripleta.
     * @return segundo objeto de la terna.
     */
    public T2 get2(){
        return Object2;
    }
    
    /**
     * Metodo que devuelve el Objeto3 de la Tripleta.
     * @return tercer objeto de la terna.
     */
    public T3 get3(){
        return Object3;
    }    
    
    /**
     * Metodo que cambia el Objeto1 de la Tripleta por otro.
     * @param nuevo objeto a utilizar.
     */
    public void set1(T1 Objet1) {
        this.Object1 = Objet1;
    }
    
    /**
     * Metodo que cambia el Objeto2 de la Tripleta por otro.
     * @param nuevo objeto a utilizar.
     */
    public void set2(T2 Objet2) {
        this.Object2 = Objet2;
    }
    
    /**
     * Metodo que cambia el Objeto3 de la Tripleta por otro.
     * @param nuevo objeto a utilizar.
     */
    public void set3(T3 Objet3) {
        this.Object3 = Objet3;
    }
    
    /**
     * Asigna en null los elementos de la Tripleta
     */
    public void clear() {
        Object1 = null;
        Object2 = null;
        Object3 = null;
    }

    @Override
    public String toString() {
        return Object1.toString()+"\n"+Object2.toString()+"\n"+Object3.toString();
    }
}