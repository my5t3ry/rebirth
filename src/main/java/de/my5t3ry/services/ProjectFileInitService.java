package de.my5t3ry.services;

import de.my5t3ry.domain.projects.ProjectFileFactory;
import de.my5t3ry.persistence.ProjectFileRepository;
import de.my5t3ry.utils.GZipFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * created by: sascha.bast
 * since: 30.08.17
 */
@Service
public class ProjectFileInitService {


    @Autowired
    private ProjectFileRepository projectFileRepository;

    @Autowired
    private ProjectFileFactory projectFileFactory;


    public void parseAndSaveAbletonFile(final File file) {
        final GZipFile gZipFile = new GZipFile(file);
        final File outputFile = new File("/var/tmp/" + file.getName());
        gZipFile.decompress(outputFile);
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(new FileInputStream(outputFile));
            projectFileRepository.save(projectFileFactory.build(document, file.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {


        }
    }
}
