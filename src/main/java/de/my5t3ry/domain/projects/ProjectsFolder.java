package de.my5t3ry.domain.projects;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * created by: sascha.bast
 * since: 26.08.17
 */
@Entity
public class ProjectsFolder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String folderPath;
    private String path;

    public ProjectsFolder(final String folderPath) {
        this.folderPath = folderPath;
    }

    public Long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }
}
