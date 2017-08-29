package de.my5t3ry.domain.projects;

import org.json.JSONObject;

/**
 * created by: sascha.bast
 * since: 29.08.17
 */
public class ProjectFileFactory {

    final ProjectFile build(final JSONObject jsonObject) {
        final ProjectFile result = new ProjectFile();
        result.tracks = 12;
        return result;
    }
}
