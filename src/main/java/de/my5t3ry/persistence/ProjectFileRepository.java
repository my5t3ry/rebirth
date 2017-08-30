package de.my5t3ry.persistence;

import de.my5t3ry.domain.projects.ProjectFile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by: sascha.bast
 * since: 29.08.17
 */
public interface ProjectFileRepository extends JpaRepository<ProjectFile, Long> {
}
