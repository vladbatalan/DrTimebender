package PaooGame.Physics;

/**
 * PVector is a 2D vector.
 * It can be interpreted in at least two ways:
 *  - As a Force vector: Velocity, Acceleration, Resultant Force
 *  - As a Point on the map: the posision Vector.
 *
 * @author      Batalan Vlad
 * @since 1.0
 */

public class PVector {

    /**
     * The x coordonate of the PVector.
     */
    private float x;

    /**
     * The y coordonate of the PVector.
     */
    private float y;

    /**
     * Default constructor. All the coordonates are initialised with 0.
     */
    public PVector(){
        x = 0;
        y = 0;
    }

    /**
     * Parametered constructor.
     * @param x
     *  The x coordonate of the PVector.
     * @param y
     *  The y coordonate of the PVector.
     */
    public PVector(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * Copy constructor.
     * @param v
     *  The vector that holds the values of the coordonates.
     */
    public PVector(PVector v){
        this.x = v.getX();
        this.y = v.getY();
    }

    /**
     * Getter for the x coordonate.
     * @return
     *  The x coordonate is returned.
     */
    public float getX(){
        return x;
    }

    /**
     * Getter for the y coordonate.
     * @return
     *  The y coordonate is returned.
     */
    public float getY(){
        return y;
    }

    /**
     * Setter for the x coordonate
     * @param x
     *  The value of the x coordonate.
     */
    public void setX(float x){
        this.x = x;
    }

    /**
     * Setter for the y coordonate
     * @param y
     *  The value of the y coordonate.
     */
    public void setY(float y){
        this.y = y;
    }

    /**
     * The clone function is used to create a copy of the PVector.
     * @return
     *  A clone of the object.
     * @throws CloneNotSupportedException
     *  If clonning is not supported
     */
    @Override
    public PVector clone() throws CloneNotSupportedException {
        return (PVector) super.clone();
    }

    public PVector add(PVector v){
        PVector result = new PVector();
        result.x = this.x + v.x;
        result.y = this.y + v.y;
        return result;
    }
    public PVector sub(PVector v){
        PVector result = new PVector();
        result.x = this.x - v.x;
        result.y = this.y - v.y;
        return result;
    }

    public float abs(){
        float result;
        result = (float)Math.sqrt(this.x * this.x + this.y*this.y);
        return result;
    }
    public float distanceTo(PVector pct){
        return (float)Math.sqrt((x - pct.getX()) * (x - pct.getX()) + (y - pct.getY()) * (y - pct.getY()));
    }

    public PVector scalarMultiply(float a){
        return new PVector(this.getX()*a, this.getY()*a);
    }

    public PVector setInMapBounds(int width, int height, PVector bounds){
        PVector result = new PVector(this);
        if(this.getX() < 0) result.setX(0);
        if(this.getY() < 0) result.setY(0);
        if(this.getX() + width > bounds.getX())
            result.setX((bounds.getX() - width - 2));
        if(this.getY() + height > bounds.getY())
            result.setY(bounds.getY() - height - 2);
        return result;
    }


    public String toString(){
        return "("+ x +", "+ y +")";
    }


}
