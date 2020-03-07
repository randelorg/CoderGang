/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainmainbank;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Randel P. Reyes
 */
public class MainMainBank extends Application{

    /**
     * @param args
     */
    
    public static void main(String[] args) 
    {
        launch(args);
    }
     
    @Override
    public void start(Stage primaryStage) 
    {
        try
        {   
            Parent root = FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
            Scene scene = new Scene(root,600,396);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Banking System v.2");
            primaryStage.setResizable(false);
            primaryStage.show();
        }
        catch(IOException e){}
    }

}
