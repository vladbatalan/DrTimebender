package PaooGame.Physics;

import PaooGame.Tiles.Map;

import java.awt.*;

public class PVector {
    private float x;
    private float y;

    public PVector(){
        x = 0;
        y = 0;
    }

    public PVector(float x, float y){
        this.x = x;
        this.y = y;
    }

    public PVector(PVector v){
        this.x = v.getX();
        this.y = v.getY();
    }

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public void setX(float x){
        this.x = x;
    }
    public void setY(float y){
        this.y = y;
    }

    @Override
    public PVector clone() throws CloneNotSupportedException {
        PVector clone = (PVector) super.clone();
        PVector ret = new PVector(this);
        return ret;
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
