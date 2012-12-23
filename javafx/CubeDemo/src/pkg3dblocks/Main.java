/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dblocks;


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pkg3dblocks.object3d.Rectangle3D;

/**
 *
 * @author msi
 */
public class Main extends Application {
    
    
    
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        AnchorPane root = (AnchorPane)FXMLLoader.load(Main.class.getResource("MainFX.fxml"));
        StackPane p = new StackPane();
        
        
        
        Rectangle3D rect = new Rectangle3D(100,100,100);
        p.getChildren().add(rect); 
        
        
        Scene scene = new Scene(root);
//        ScenicView.show(scene); 
        primaryStage.setTitle("3D Object");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
