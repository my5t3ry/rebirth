package de.my5t3ry.domain.projects;

import javax.persistence.*;
import java.util.List;

/**
 * created by: sascha.bast
 * since: 29.08.17
 */
@Entity
public class ProjectFile {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    protected String name;
    @ElementCollection
    protected List<String> vsts;

    protected Integer tracks;

    


}
