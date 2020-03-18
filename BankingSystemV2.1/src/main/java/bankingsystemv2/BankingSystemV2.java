/*
 * @Title:
 * @Description:
 * 
 * @Author:
 * @Date Created:
 */
package bankingsystemv2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 * @author Ivan N. Roncesvalles
 */
public class BankingSystemV2 extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
            Scene scene = new Scene(root,600,396);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Banking System v.2");
            primaryStage.setResizable(false);
            primaryStage.show();

            Files f = new Files();
            f.readFiles();

            for(Client c: Files.clientAL)
            {
                Bank.ldClient.add(c);
            }
            for(Teller t: Files.tellerAL)
            {
                Bank.ldTeller.add(t);
            }
            primaryStage.setOnCloseRequest(e -> {
                Files.tellerAL.clear();
                Files.clientAL.clear();
                for(Client c: Bank.ldClient)
                {
                    Files.clientAL.add(c);
                }
                for(Teller t: Bank.ldTeller)
                {
                    Files.tellerAL.add(t);
                }
                f.saveFiles();
            });
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
