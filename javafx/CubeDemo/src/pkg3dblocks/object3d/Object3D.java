/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dblocks.object3d;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Narayan
 */
public abstract class Object3D extends StackPane{
    


    protected DoubleProperty width;
    protected DoubleProperty height;
    protected DoubleProperty depth;
    
    
    /**
     *  Constructor for making 3D Object
     */
    Object3D(){
        width = new SimpleDoubleProperty();
        height = new SimpleDoubleProperty();
        depth = new SimpleDoubleProperty();
    }
    
    
    /**
     * Refreshes the Node
     */
    protected void refresh(){
        getChildren().clear();
        getObject(width.get(),height.get(),depth.get());
    }
    
    /**
     * This function is used for making the Node
     * @param width
     * @param height
     * @param depth
     * @return
     */
    protected abstract Object3D getObject(double width,double height,double depth);
    
    /**
     * Get mid value from two values
     * 
     * @param d
     * @param d2
     * @return 
     */
    protected static double getMid(double d, double d2){
        return (d+d2)/2.0;
    }
    
    
    /**
     * Calculate the Distance between two co-ordinates
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return 
     */
    protected static double getDistanceFromPoints(double x1, double y1,double x2, double y2){
        double dy = y2-y1;
        double dx = x1-x2;
        
        double distance = Math.sqrt(Math.pow(dy,2)+Math.pow(dx,2)); 
        System.out.println("DistanceX:"+String.format("%.2f",distance));
        return distance;
    }
    
    /**
     * Getting the degree of angle of any slope of two co-ordinates
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return 
     */
    protected static double getAngleFromPoints(double x1,double y1, double x2, double y2){
        double slope = (y2-y1)/(x2-x1);
        
        double angle = Math.toDegrees(Math.atan(slope)); 
        System.out.println("Angle:"+angle);
        return angle;
    }
    
    
    
    //ACCESSOR METHODS
    
    public double getObjectWidth() {
        return width.get();
    }

    public void setObjectWidth(double width) {
        this.width.set(width);
    }

    public double getObjectHeight() {
        return height.get();
    }

    public void setObjectHeight(double height) {
        this.height.set(height);
    }

    public double getObjectDepth() {
        return depth.get();
    }

    public void setObjectDepth(double depth) {
        this.depth.set(depth);
        
    }
    
    
    public DoubleProperty objectWidthProperty(){
        return width;
    }
    
    public DoubleProperty objectHeightProperty(){
        return height;
    }
    
    public DoubleProperty objectDepthProperty(){
        return depth;
    }
    
}
