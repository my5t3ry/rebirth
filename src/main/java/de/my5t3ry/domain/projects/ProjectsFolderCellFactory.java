package de.my5t3ry.domain.projects;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * created by: sascha.bast
 * since: 04.09.17
 */
public class ProjectsFolderCellFactory implements Callback<ListView<ProjectsFolder>, ListCell<ProjectsFolder>> {
    @Override
    public ListCell<ProjectsFolder> call(ListView<ProjectsFolder> p) {
        ListCell<ProjectsFolder> cell = new ListCell<ProjectsFolder>() {
            @Override
            protected void updateItem(ProjectsFolder projectsFolder, boolean bln) {
                super.updateItem(projectsFolder, bln);
                if (projectsFolder != null) {
                    setText(projectsFolder.getPath());
                }
            }
        };
        return cell;
    }
}
