/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.PrintWriter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



/**
 *
 * @author Omar
 */
public class FileManager extends Application {
    
    public static File lol = new File("firstRun");
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        if (lol.exists()){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        Parent root = loader.load();
        MainController controller = (MainController)loader.getController();
        controller.init(primaryStage);
        Scene scene = new Scene(root, 375, 245);
        
        primaryStage.setTitle("File Manager Login");
        primaryStage.setScene(scene);
        primaryStage.show();
       }
        
        else {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginFirstTime.fxml"));
        Parent root = loader.load();
        MainController controller = (MainController)loader.getController();
        controller.init(primaryStage);
        Scene scene = new Scene(root, 375, 245);
        
        primaryStage.setTitle("File Manager Login First Time");
        primaryStage.setScene(scene);
        primaryStage.show();
//        if (lol.exists() == false){
//             PrintWriter lolW = new PrintWriter(lol);
//             lolW.print(MainController.Passwordft.getText());
//         }
    }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
