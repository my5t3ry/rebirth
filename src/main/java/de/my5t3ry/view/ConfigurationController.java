package de.my5t3ry.view;

import de.my5t3ry.domain.projects.ProjectsFolder;
import de.my5t3ry.domain.projects.ProjectsFolderCellFactory;
import de.my5t3ry.persistence.ProjectFolderRepository;
import de.my5t3ry.services.ProjectFileInitService;
import de.my5t3ry.services.ProjectPathParser;
import de.my5t3ry.task.PathScanTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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

    @FXML
    public ListView projectsFolderList;

    @Autowired
    private ProjectFileInitService projectFileInitService;

    @Autowired
    private ProjectFolderRepository projectsFolderRepository;

    @Autowired
    private ProjectPathParser projectPathParser;

    @FXML
    AnchorPane configurationRoot;

    ObservableList<ProjectsFolder> projectFolders = FXCollections.observableArrayList();

    @FXML
    Button dataButton;
    private Runnable onClose;

    public Runnable getOnClose() {
        return onClose;
    }

    @Override
    public void init() {
        projectsFolderList.setItems(projectFolders);
        projectsFolderListCellFactory();
    }

    private void projectsFolderListCellFactory() {
        projectsFolderList.setCellFactory(new ProjectsFolderCellFactory());
    }

    @Override
    public AnchorPane getRoot() {
        return configurationRoot;
    }


    @FXML
    public void onSelectDataButton(final ActionEvent event) {
        DirectoryChooser chooser = new DirectoryChooser();
        File dir = chooser.showDialog(configurationRoot.getScene().getWindow());                                                            
        ProgressForm pForm = new ProgressForm();
        final List<File> abletonFiles = projectPathParser.parsePath(dir);
        final PathScanTask pathScanTask = new PathScanTask(abletonFiles, projectFileInitService);

        pForm.activateProgressBar(pathScanTask);

        pathScanTask.setOnSucceeded(successEvent -> {
            pForm.getDialogStage().close();
            dataButton.setDisable(false);
            projectsFolderRepository.save(new ProjectsFolder(dir.getAbsolutePath()));
            refreshProjectsFileList();
        });

        dataButton.setDisable(true);
        pForm.getDialogStage().show();

        Thread thread = new Thread(pathScanTask);
        thread.start();
        if (dir == null) {
            return;
        }
    }

    private void refreshProjectsFileList() {
        projectFolders.clear();
        projectFolders.addAll(projectsFolderRepository.findAll());
    }

}
