package code;

/**
 * Generic class for a data structure triplet.
 * @param <T1>
 * @param <T2>
 * @param <T3>
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
     * Return de first object in the triplet.
     * @return Object1
     */
    public T1 get1(){
        return Object1;
    }
    
    /**
     * Return de second object in the triplet.
     * @return Object2
     */
    public T2 get2(){
        return Object2;
    }
    
    /**
     * Return de third object in the triplet.
     * @return Object3
     */
    public T3 get3(){
        return Object3;
    }    
    
    /**
     * Update the first Object in the triplet
     * @param Objet1
     */
    public void set1(T1 Objet1) {
        this.Object1 = Objet1;
    }
    
    /**
     * Update the second Object in the triplet.
     * @param Object2
     */
    public void set2(T2 Objet2) {
        this.Object2 = Objet2;
    }
    
    /**
     * Update the third Object in the triplet.
     * @param nuevo objeto a utilizar.
     */
    public void set3(T3 Objet3) {
        this.Object3 = Objet3;
    }
    
    /**
     * Set all objects in the triplet as null.
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