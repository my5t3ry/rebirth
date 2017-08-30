package de.my5t3ry.domain.projects;

import de.my5t3ry.domain.device.Device;
import de.my5t3ry.domain.device.DeviceManufacturer;

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

    @ManyToMany(cascade = CascadeType.PERSIST)
    protected List<Device> internalDevices;

    @ManyToMany(cascade = CascadeType.PERSIST)
    protected List<Device> externalDevices;

    @ManyToMany(cascade = CascadeType.PERSIST)
    protected List<DeviceManufacturer> manufacturers;

    protected Integer groupTracks;
    protected Integer midiTracks;
    protected Integer audioTracks;

    public Integer getTotalTracks() {
        return groupTracks + midiTracks + audioTracks;
    }
}
