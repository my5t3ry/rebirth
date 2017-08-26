package de.my5t3ry.persistence;

import de.my5t3ry.domain.ProjectsFolder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by: sascha.bast
 * since: 26.08.17
 */
public interface ProjectFolderRepository extends JpaRepository<ProjectsFolder,Long>{
}
