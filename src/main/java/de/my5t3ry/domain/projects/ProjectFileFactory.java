package de.my5t3ry.domain.projects;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.util.HashSet;
import java.util.Set;

/**
 * created by: sascha.bast
 * since: 29.08.17
 */
@Service
public class ProjectFileFactory {

    private final XPath xPath = XPathFactory.newInstance().newXPath();

    public final ProjectFile build(final Document doc) {
        final ProjectFile result = new ProjectFile();
        result.internalDevices = getExternalPlugins(doc);
        return result;
    }

    public Set<String> getExternalPlugins(final Document doc) {
        final Set<String> result = new HashSet<>();
        try {
            final NodeList nodeList = (NodeList) xPath.compile(".//LiveSet//Tracks//DeviceChain//Devices[1]/*").evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                result.add(nodeList.item(i).getNodeName());
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return result;
    }
}
