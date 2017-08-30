package de.my5t3ry.task;

import de.my5t3ry.services.ProjectFileInitService;
import javafx.concurrent.Task;

import java.io.File;
import java.util.List;

/**
 * created by: sascha.bast
 * since: 30.08.17
 */
public class PathScanTask extends Task<Void> {
    private final List<File> abletonFiles;
    private ProjectFileInitService projectFileInitService;

    public PathScanTask(final List<File> abletonFiles, final ProjectFileInitService projectFileInitService) {
        this.projectFileInitService = projectFileInitService;
        this.abletonFiles = abletonFiles;
    }

    @Override
    protected Void call() throws Exception {
        abletonFiles.forEach(file -> {
            updateProgress(abletonFiles.indexOf(file), abletonFiles.size());
            updateMessage("parse: " + file.getAbsolutePath());
            projectFileInitService.parseAndSaveAbletonFile(file);
        });
        return null;
    }

}
