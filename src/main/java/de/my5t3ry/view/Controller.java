package de.my5t3ry.view;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * created by: sascha.bast
 * since: 27.08.17
 */
public abstract class Controller implements Initializable {

    public abstract AnchorPane getRoot();

    public abstract void init() ;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (Platform.isFxApplicationThread()) {
            init();
        } else {
            Platform.runLater(this::init);
        }
    }
}
