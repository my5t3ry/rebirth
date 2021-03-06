package de.my5t3ry.domain.projects;

import de.my5t3ry.domain.device.Device;
import de.my5t3ry.domain.device.DeviceManufacturer;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private final String MANUFACTURER = ".//LiveSet//Tracks//DeviceChain//Devices//AuPluginDevice//PluginDesc//AuPluginInfo/Manufacturer";
    private final String GROUP_TRACKS = "count(.//LiveSet//Tracks//GroupTrack)";
    private final String MIDI_TRACKS = "count(.//LiveSet//Tracks//MidiTrack)";
    private final String AUDIO_TRACKS = "count(.//LiveSet//Tracks//AudioTrack)";
//    private final String CREATOR = ".//at";




    public final ProjectFile build(final Document doc, final String name) {
        final ProjectFile result = new ProjectFile();
        result.name = name;
        result.internalDevices = getDevices(doc, INTERNAL_DEVICES_PATH, new InternalDeviceNameExtractor());
        final List<Device> externalDevice = getDevices(doc, EXTERNAL_VST, new ExternalDeviceNameExtractor());
        externalDevice.addAll(getDevices(doc, EXTERNAL_AU, new ExternalDeviceNameExtractor()));
        result.externalDevices = externalDevice;
        result.manufacturers = getManufacturers(doc);
        result.groupTracks = getTrackCount(doc, GROUP_TRACKS);
        result.midiTracks = getTrackCount(doc, MIDI_TRACKS);
        result.audioTracks = getTrackCount(doc, AUDIO_TRACKS);
        return result;
    }

    private Integer getTrackCount(final Document doc, final String path) {
        try {
            return ((Double) xPath.compile(path).evaluate(doc, XPathConstants.NUMBER)).intValue();
        } catch (XPathExpressionException e) {
            throw new IllegalStateException("Could not read Ableton File", e);
        }
    }


    private List<DeviceManufacturer> getManufacturers(final Document doc) {
        final Set<DeviceManufacturer> result = new HashSet<>();
        try {
            final NodeList nodeList = (NodeList) xPath.compile(MANUFACTURER).evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                final String manufacturerName = nodeList.item(i).getAttributes().item(0).getNodeValue();
                result.add(new DeviceManufacturer(manufacturerName));
            }
        } catch (XPathExpressionException e) {
            throw new IllegalStateException("Could not read Ableton File", e);
        }
        return new ArrayList<>(result);
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
            throw new IllegalStateException("Could not read Ableton File", e);
        }
        return result;
    }
}
