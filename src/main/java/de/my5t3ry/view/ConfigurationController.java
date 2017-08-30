package de.my5t3ry.view;

import de.my5t3ry.services.ProjectFileInitService;
import de.my5t3ry.services.ProjectPathParser;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;

/**
 * created by: sascha.bast
 * since: 27.08.17
 */
@org.springframework.stereotype.Controller
public class ConfigurationController extends Controller {

    @Autowired
    private ProjectFileInitService projectFileInitService;

    @Autowired
    private ProjectPathParser projectPathParser;

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
        File dir = chooser.showDialog(configurationRoot.getScene().getWindow());
        ProgressForm pForm = new ProgressForm();
        final List<File> abletonFiles = projectPathParser.parsePath(dir);

        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
                abletonFiles.forEach(file -> {
                    updateProgress(abletonFiles.indexOf(file), abletonFiles.size());
                    projectFileInitService.parseAndSaveAbletonFile(file);
                });
                return null;
            }
        };

        pForm.activateProgressBar(task);

        task.setOnSucceeded(successEvent -> {
            pForm.getDialogStage().close();
            dataButton.setDisable(false);
        });

        dataButton.setDisable(true);
        pForm.getDialogStage().show();

        Thread thread = new Thread(task);
        thread.start();
        if (dir == null) {
            return;
        }
    }
}
