/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dblocks;

import javafx.scene.layout.StackPane;

/**
 *
 * @author msi
 */
public class Helper3D {
    
    public static double getTopPos(StackPane n){
        if(n == null){
            return 0;
        }
        System.out.println("Height:"+n.getPrefHeight());
        return n.getPrefHeight();
    }
    
}
