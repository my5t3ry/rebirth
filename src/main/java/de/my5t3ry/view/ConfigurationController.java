package de.my5t3ry.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;

import java.io.File;

/**
 * created by: sascha.bast
 * since: 27.08.17
 */
@org.springframework.stereotype.Controller
public class ConfigurationController extends Controller {
    @FXML
    AnchorPane configurationRoot;

    @FXML
    Button dataButton;
    private Runnable onClose;

    public Runnable getOnClose() {
        return onClose;
    }

    @Override
    public void init() {
    
    }


    @Override
    public AnchorPane getRoot() {
        return configurationRoot;
    }


    @FXML
    public void onSelectDataButton(ActionEvent event) {
        DirectoryChooser chooser = new DirectoryChooser();
//        chooser.setInitialDirectory(Datastore.getInstance().getDataDir().toFile());
        File dir = chooser.showDialog(configurationRoot.getScene().getWindow());
        if (dir == null) {
            return;
        }
//        Datastore.getInstance().setDataDir(Paths.get(dir.getAbsolutePath()));
    }
}
