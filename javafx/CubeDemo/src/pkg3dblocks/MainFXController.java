/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dblocks;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import pkg3dblocks.object3d.Object3D;
import pkg3dblocks.object3d.Object3dUtils;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class MainFXController implements Initializable {

 
  
    
   
    @FXML
    private StackPane drawPane;
    
  
    
    private PerspectiveCamera camera;
    
    
    protected static double getDistanceFromPoints(double x1, double y1,double x2, double y2){
        double dy = y2-y1;
        double dx = x1-x2;
        
        double distance = Math.sqrt(Math.pow(dy,2)+Math.pow(dx,2)); 
        //System.out.println("DistanceX:"+String.format("%.2f",distance));
        return distance;
    }
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Object3D p = Object3dUtils.get3DRectangle(100,100,100);
        drawPane.getChildren().add(p); 
        
        /*
        sceneProperty().addListener(new ChangeListener<Scene>(){

            @Override
            public void changed(ObservableValue<? extends Scene> ov, Scene t, Scene t1) {
                if(t1 !=null){
                    camera = new PerspectiveCamera();
                    camera.setFieldOfView(25);
                }
            }
            
        });
        */
        
        
    }    
    
    
    public void dragStart(MouseEvent e){
//        System.out.println("Started Drag");
        startx = e.getX();
        starty =e.getY();
        
    }
    
    
    public void dragEnd(MouseEvent e){
        x = 0;
        y = 0;
        startx = 0;
        starty = 0;
                
    }
    static double startx = 0;
    static double starty = 0;
    static double x = 0;
    static double y = 0;
    
    
    public void drag(MouseEvent e){
        
        if(e.isSecondaryButtonDown()){
            x = e.getX();
            y = e.getY();

            
            double distx = getDistanceFromPoints(x,0,startx,0);
            double disty = getDistanceFromPoints(0,y,0,starty);
            supportXAxis(drawPane,(y-starty)/30);
            supportYAxis(drawPane,-(x-startx)/30);
        }
        
    }
    
    public void supportXAxis(StackPane n,double value){
        if(n!=null){
            Rotate r = new Rotate(0,Rotate.X_AXIS);
            r.pivotXProperty().bind(n.widthProperty().multiply(0.5)); 
            r.pivotYProperty().bind(n.heightProperty().multiply(0.5)); 
            if(n.getTransforms().size()>0){
                boolean found = false;
                for(Transform trans: n.getTransforms()){
                    if(trans instanceof Rotate ){
                        Rotate rt = (Rotate)trans;
                        if(rt.getAxis().equals(Rotate.X_AXIS)){
                            rt.setAngle(rt.getAngle()+value); 
                            found = true;
                        }
                    }

                }
                if(!found){

                    n.getTransforms().add(r);
                }
            }else{

                n.getTransforms().add(r);
            }

        }
    }
    
    public void supportYAxis(StackPane n,double value){
        
        if(n!=null){
            Rotate r = new Rotate(0,Rotate.Y_AXIS);
            r.pivotXProperty().bind(drawPane.widthProperty().multiply(0.5)); 
            r.pivotYProperty().bind(drawPane.heightProperty().multiply(0.5));
            if(n.getTransforms().size()>0){
                boolean found = false;
                for(Transform trans: n.getTransforms()){

                    if(trans instanceof Rotate ){
                        Rotate rt = (Rotate)trans;
                        if(rt.getAxis().equals(Rotate.Y_AXIS)){
                            rt.setAngle(rt.getAngle()+value);
                            found = true;
                        }


                    }
                }
                if(!found){
                    n.getTransforms().add(r);
                }
            }else                    
            n.getTransforms().add(r);
//            System.out.println("Y:"+value);
        }
            
    }
    
    
    public void supportZAxis(StackPane n, boolean inc){
        if(n!=null){
            Rotate r = new Rotate(0,Rotate.Z_AXIS);
            r.pivotXProperty().bind(drawPane.widthProperty().multiply(0.5)); 
            r.pivotYProperty().bind(drawPane.heightProperty().multiply(0.5));

            if(n.getTransforms().size()>0){
                boolean found = false;
                for(Transform trans: n.getTransforms()){
                    if(trans instanceof Rotate ){
                        Rotate rt = (Rotate)trans;
                        if(rt.getAxis().equals(Rotate.Z_AXIS)){
                            if( inc){
                                rt.setAngle(rt.getAngle()+1);
                            }else if(rt.getAngle()>0 && !inc){
                                rt.setAngle(rt.getAngle()-1);
                            }
                            found = true;
                        }
                    }
                }
                if(!found){
                    n.getTransforms().add(r);
                }
            }else                    
              n.getTransforms().add(r); 
//            System.out.println("Z:"+value);
        }
    }
 
    
    public void clear(ActionEvent evt){
        drawPane.getChildren().clear();
    }
    
    
    
    
    
 
    
}
