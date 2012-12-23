/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dblocks.object3d;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.scene.DepthTest;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

/**
 *
 * @author Narayan
 */
public class Rectangle3D extends Object3D{
    
    
    
    public Rectangle3D(double w,double h,double d){
        setObjectWidth(w);
        setObjectHeight(h);
        setObjectDepth(d);
        
        width.addListener(new InvalidationListener(){

            @Override
            public void invalidated(Observable o) {
                refresh();
                
                
            }
            
        });
        height.addListener(new InvalidationListener(){

            @Override
            public void invalidated(Observable o) {
                refresh();
            }
            
        });
        depth.addListener(new InvalidationListener(){

            @Override
            public void invalidated(Observable o) {
                refresh();
            }
            
        });
        refresh();
    }
    
    
    
    @Override
    protected Object3D getObject(double width,double height,double depth){
            double r = (Math.random()*1);
            double g = (Math.random()*1);
            double b = (Math.random()*1);
       
        
        for(int i = 0 ; i<6; i++){
            final Rectangle rt = new Rectangle();
            rt.setWidth(width);
            rt.setHeight(height); 
            //rt.setStroke(Color.web("ba1010"));
            rt.setStrokeWidth(1);  
            
            
            Color color = Color.color(r, g, b);
            
            Light.Point light = new Light.Point();
            light.setX(100);
            light.setY(100);
            light.setZ(50);
           // Light.Distant light = new Light.Distant();
//            light.setAzimuth(-135.0);
//            light.setColor(color); 
            
            Lighting lighting = new Lighting();
            lighting.setLight(light);
            lighting.setSurfaceScale(5.0);
            
            double shade = 1;
            
            RadialGradient rd = new RadialGradient(15,0, 0, 0, 0.2,true, CycleMethod.REFLECT,
                                    new Stop(0, color.brighter()),new Stop(1, color.darker()));
            
//            RadialGradient rd = new RadialGradient(15,0, 0, 0, 0.2,true, CycleMethod.REFLECT,
//                                    new Stop(0, Color.web("#5b0606")),new Stop(1, Color.web("#d31b1b")));
//            RadialGradient rd = new RadialGradient(15,0, 0, 0, 0.2,true, CycleMethod.REFLECT,
//                                    new Stop(0, Color.rgb(r, g, b).brighter()),new Stop(1, Color.rgb(r, g, b).darker()));
            rt.setFill(color);
            rt.setOnMouseClicked(new EventHandler<MouseEvent>(){

                @Override
                public void handle(MouseEvent t) {
                    //item = (Node)t.getSource();
                    //item = rt;
                    System.out.println(rt);
                }
                
            });
           
//            rt.setFill(rd); 
            rt.setEffect(lighting); 
            rt.setTranslateZ(depth); 
            rt.setDepthTest(DepthTest.DISABLE);
            rt.setSmooth(true);  
            getChildren().add(rt);
            switch(i){
                case 5: 
                        //BACK
                        rt.setTranslateZ(0); 
                        //rt.setFill(color.deriveColor(0.0, 1.0, (1 - 0.50*shade), 1.0));
                        //rt.setFill(Color.PINK);
                        break;
                
                
                case 3: //RIGHT     
                        rt.setTranslateZ((depth*0.5));
                        rt.getTransforms().add( new Rotate(90,depth/2,height/2,0,Rotate.Y_AXIS));                        
                        rt.setWidth(depth);
                        rt.setTranslateX((width*0.5)); 
                        
                        //rt.setFill(color.deriveColor(0.0, 1.0, (1 - 0.53*shade), 1.0));
                        //rt.setTranslateX(width*0.6); 
                        
                        break;
                
                case 4: //up
                        rt.setTranslateZ((depth*0.5));     
                        rt.setTranslateY(-(height*0.5));
                        //rt.setTranslateY(-(depth*0.5)); 
                        //rt.setFill(color.deriveColor(0.0, 1.0, (1 - 0.52*shade), 1.0));
                        rt.getTransforms().add(new Rotate(270,width/2,depth/2,0,Rotate.X_AXIS));
                        rt.setHeight(depth);
//                        rt.setFill(Color.ALICEBLUE);
                        break;
                    
                case 1: //down
                        
                        rt.getTransforms().add(new Rotate(270,width/2,depth/2,0,Rotate.X_AXIS));
                        rt.setTranslateZ(depth*0.5);    
                        rt.setHeight(depth);
                        rt.setTranslateY(height*0.5);
                        //rt.setHeight(width*0.2);
                        //rt.setFill(color.deriveColor(0.0, 1.0, (1 - 0.54*shade), 1.0));
                        break;
                case 2: //LEFT
                        rt.setTranslateZ((depth*0.5));     
                        rt.getTransforms().add(new Rotate(90,depth/2,height/2,0,Rotate.Y_AXIS));
                        
                        rt.setWidth(depth);
                        
                        rt.setTranslateX(-(width*0.5-1)); 
                        //rt.setFill(color.deriveColor(0.0, 1.0, (1 - 0.51*shade), 1.0));
                        break;
                
            }
        }
        
        
        
        //temp=drawPane;
        //temp.setDepthTest(DepthTest.ENABLE);
        return this;
    }
}
