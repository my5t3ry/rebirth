package de.my5t3ry;

import de.my5t3ry.config.AppConfiguration;
import de.my5t3ry.utils.SpringFxmlLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

@SpringBootApplication
@Service
public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
     public void start(Stage primaryStage) throws Exception {
         AnnotationConfigApplicationContext context
                 = new AnnotationConfigApplicationContext(AppConfiguration.class);

         SpringFxmlLoader loader = new SpringFxmlLoader(context);
         Parent parent = (Parent) loader.load("/fxml/hello.fxml");
         primaryStage.setScene(new Scene(parent, 1000, 900));
         primaryStage.setTitle("Your Title");
         primaryStage.show();
     }
}
