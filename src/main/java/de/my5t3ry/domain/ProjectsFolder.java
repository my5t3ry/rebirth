package de.my5t3ry.domain;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

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

    public ProjectsFolder(final String folderPath) {
        this.folderPath = folderPath;
    }

    public Long getId() {
        return id;
    }
}
