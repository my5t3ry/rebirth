package de.my5t3ry.domain.projects;

import de.my5t3ry.domain.device.Device;
import de.my5t3ry.domain.projects.utils.ExternalDeviceNameExtractor;
import de.my5t3ry.domain.projects.utils.IExtractDeviceNames;
import de.my5t3ry.domain.projects.utils.InternalDeviceNameExtractor;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * created by: sascha.bast
 * since: 29.08.17
 */
@Service
public class ProjectFileFactory {

    private final XPath xPath = XPathFactory.newInstance().newXPath();
    private final String INTERNAL_DEVICES_PATH = ".//LiveSet//Tracks//DeviceChain//Devices[1]/*";
    private final String EXTERNAL_VST = ".//LiveSet//Tracks//DeviceChain//Devices//PluginDevice//PluginDesc//VstPluginInfo//PlugName";
    private final String EXTERNAL_AU = ".//LiveSet//Tracks//DeviceChain//Devices//AuPluginDevice//PluginDesc//AuPluginInfo/Name";

    public final ProjectFile build(final Document doc) {
        final ProjectFile result = new ProjectFile();
        result.internalDevices = getDevices(doc, INTERNAL_DEVICES_PATH, new InternalDeviceNameExtractor());
        final List<Device> externalDevice = getDevices(doc, EXTERNAL_VST, new ExternalDeviceNameExtractor());
        externalDevice.addAll(getDevices(doc, EXTERNAL_AU, new ExternalDeviceNameExtractor()));
        result.externalDevices = externalDevice;
        return result;
    }

    public List<Device> getDevices(final Document doc, final String path, final IExtractDeviceNames deviceNameExtractor) {
        final List<Device> result = new ArrayList();
        try {
            final NodeList nodeList = (NodeList) xPath.compile(path).evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                final String deviceName = deviceNameExtractor.extractName(nodeList.item(i));
                final Device device = new Device(deviceName);
                if (result.contains(device)) {
                    result.get(result.indexOf(device)).addDevice();
                } else {
                    result.add(device);
                }
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return result;
    }
}
