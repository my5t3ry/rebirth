package de.my5t3ry.services;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;

/**
 * created by: sascha.bast
 * since: 27.08.17
 */
@Service
public class ProjectPathParser {



    public ArrayList parsePath(final File dir) {
        final String[] validExtension = {"als"};
        return new ArrayList<>(FileUtils.listFiles(dir, validExtension, true));

    }


}
