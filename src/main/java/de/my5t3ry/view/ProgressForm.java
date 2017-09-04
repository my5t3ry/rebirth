package de.my5t3ry.view;

import de.my5t3ry.task.PathScanTask;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.application.Application.launch;

/**
 * created by: sascha.bast
 * since: 27.08.17
 */
public class ProgressForm {
    private final Stage dialogStage;
    private final ProgressBar pb = new ProgressBar();
    private final Label label = new Label();

    public ProgressForm() {
        dialogStage = new Stage();
        GridPane root = new GridPane();
        root.setPadding(new Insets(20));
        root.addColumn(0, pb);
        root.addColumn(0, label);
        dialogStage.initStyle(StageStyle.UTILITY);
        dialogStage.setResizable(false);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        pb.setProgress(-1F);
        Scene scene = new Scene(root, 300, 100);
        dialogStage.sizeToScene();
        dialogStage.setScene(scene);
    }

    public void activateProgressBar(final PathScanTask task) {
        pb.progressProperty().bind(task.progressProperty());
        label.textProperty().bind(task.messageProperty());
        dialogStage.show();
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}