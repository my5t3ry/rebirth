package de.my5t3ry.view;

import de.my5t3ry.task.PathScanTask;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
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
    private String currentCaptionInfo;
    private final Label label = new Label();

    public ProgressForm() {
        dialogStage = new Stage();
        dialogStage.initStyle(StageStyle.UTILITY);
        dialogStage.setResizable(false);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        label.setText("Paring projects");
        pb.setProgress(-1F);
        final HBox hb = new HBox();
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(pb, label);


        Scene scene = new Scene(hb);
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

    public void setCurrentCaptionInfo(final String currentCaptionInfo) {
        this.label.setText(currentCaptionInfo);
    }
}