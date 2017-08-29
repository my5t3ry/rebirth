package de.my5t3ry.services;

import de.my5t3ry.utils.GZipFile;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * created by: sascha.bast
 * since: 27.08.17
 */
@Service
public class ProjectPathParser {
    public void parsePath(final File dir) {
        getAbletonProjectFiles(dir).forEach(file -> parseAbletonFile(file));

    }

    private List<File> getAbletonProjectFiles(final File dir) {

        final String[] validExtension = {"als"};
        return new ArrayList<>(FileUtils.listFiles(dir, validExtension, true));
    }

    private void parseAbletonFile(final File file) {
        try {
            final GZipFile gZipFile = new GZipFile(file);
            final File outputFile = new File("/var/tmp/" + file.getName());
            gZipFile.decompress(outputFile);
            String xmlString = FileUtils.readFileToString(outputFile);
            JSONObject xmlJSONObj = XML.toJSONObject(xmlString);


            String jsonPrettyPrintString = xmlJSONObj.toString();
            System.out.println(jsonPrettyPrintString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
