/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dblocks.object3d;

import javafx.scene.Node;

/**
 *
 * @author Narayan
 */
public class Object3dUtils {
    private Node item;
    
   
    
  
    public static Object3D get3DRectangle(double width,double height,double depth){
        Rectangle3D rect = new Rectangle3D(width,height,depth);
        return rect;
    }
    
}
